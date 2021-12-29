package com.animania.addons.extra.common.BlockEntity;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.handler.ExtraAddonBlockHandler;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.common.tileentity.handler.ItemHandlerHamsterWheel;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.client.handler.AnimationHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.AnimatedBlockEntity;

import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockEntityHamsterWheel extends AnimatedBlockEntity implements ITickable
{
	private static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(BlockEntityHamsterWheel.class);

	static
	{
		animHandler.addAnim(Animania.MODID, "anim_hamster_wheel", "model_hamster_wheel", true);
		animHandler.addAnim(Animania.MODID, "hamster_run", "hamster", true);
	}

	private boolean isRunning;
	private EntityHamster hamster;
	private CompoundTag hamsterNBT;
	private ItemHandlerHamsterWheel itemHandler;
	private int timer;
	private EnergyStorage power;
	private int nbtSyncTimer;

	public BlockEntityHamsterWheel()
	{
		this.itemHandler = new ItemHandlerHamsterWheel();
		this.power = new EnergyStorage(ExtraConfig.settings.hamsterWheelCapacity);
	}

	@Override
	public void update()
	{
		if (this.hamster == null && this.hamsterNBT != null)
		{
			this.hamster = new EntityHamster(level);
			this.hamster.readFromNBT(this.hamsterNBT);
		}

		if (this.hamster != null)
		{
			this.isRunning = true;
			this.markDirty();
		}
		else
		{
			this.isRunning = false;
		}

		if (this.isRunning)
		{
			this.power.receiveEnergy(ExtraConfig.settings.hamsterWheelRFGeneration, false);
			this.timer++;
			// this.markDirty();

		}

		if (this.timer >= ExtraConfig.settings.hamsterWheelUseTime)
		{
			if (!this.itemHandler.getStackInSlot(0).isEmpty())
			{
				ItemStack stack = this.itemHandler.getStackInSlot(0).copy();
				stack.shrink(1);
				this.itemHandler.setStackInSlot(0, stack);
			}
			else
			{
				this.ejectHamster();
				this.isRunning = false;
			}
			this.timer = 0;
			// this.markDirty();

		}

		for (Direction facing : Direction.VALUES)
		{
			BlockEntity tile = this.getSurroundingTE(facing);
			if (tile != null)
			{
				if (ModList.get().isLoaded("redstoneflux") && tile instanceof IEnergyReceiver)
				{
					IEnergyReceiver reciever = (IEnergyReceiver) this.getSurroundingTE(facing);
					int recieved = reciever.receiveEnergy(facing.getOpposite(), this.getEnergy(), false);
					this.power.extractEnergy(recieved, false);
				}
				else if (tile.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
				{
					IEnergyStorage energyStorage = tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
					if (energyStorage.canReceive())
					{
						int recieved = energyStorage.receiveEnergy(this.getEnergy(), false);
						this.power.extractEnergy(recieved, false);
					}
				}

			}
			this.markDirty();
		}

		this.updateAnims();
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public EntityHamster getHamster()
	{
		return this.hamster;
	}

	public void setHamster(EntityHamster hamster)
	{
		this.hamster = hamster;
	}

	public int getTimer()
	{
		return this.timer;
	}

	public void setTimer(int timer)
	{
		this.timer = timer;
	}

	public int getEnergy()
	{
		return this.power.getEnergyStored();
	}

	public void ejectHamster()
	{
		if (this.hamster != null && !level.isClientSide)
		{
			if (this.findPositionForHamster())
			{
				AnimaniaHelper.spawnEntity(level, this.hamster);
				this.hamster.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);
				// this.hamster.setWatered(false);
				this.hamster.setFed(false);
				this.hamster = null;
				this.hamsterNBT = null;
			}
			else
			{
				level.playSound(pos.getX(), pos.getY(), pos.getZ(), ExtraAddonSoundHandler.hamsterDeath, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
				this.hamster = null;
				this.hamsterNBT = null;
			}
		}
	}

	public EnergyStorage getPower()
	{
		return this.power;
	}

	private boolean findPositionForHamster()
	{
		for (Direction e : Direction.VALUES)
		{
			BlockPos pos = this.pos;
			pos = pos.offset(e);
			BlockState state = level.getBlockState(pos);

			if (!state.isOpaqueCube() && !state.isFullCube() && state.getBlock() != ExtraAddonBlockHandler.blockHamsterWheel)
			{
				this.hamster.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				return true;
			}
		}
		return false;
	}

	@Override
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		CompoundTag tag = super.writeToNBT(compound);
		tag.putInt("energy", this.power.getEnergyStored());
		tag.putBoolean("running", this.isRunning);
		tag.putInt("timer", this.timer);
		CompoundTag hamster = new CompoundTag();
		CompoundTag items = this.itemHandler.serializeNBT();
		tag.put("hamster", this.hamster == null ? hamster : this.hamster.writeToNBT(hamster));
		tag.put("items", items);
		return tag;

	}

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.power = new EnergyStorage(ExtraConfig.settings.hamsterWheelCapacity);
		this.power.receiveEnergy(compound.getInteger("energy"), false);
		CompoundTag hamster = compound.getCompoundTag("hamster");
		if (!hamster.equals(new CompoundTag()))
			this.hamsterNBT = hamster;
		else
		{
			// Delete the hamster so this side does not restart the wheel
			this.hamsterNBT = null;
			this.hamster = null;
		}
		this.isRunning = compound.getBoolean("running");
		this.timer = compound.getInteger("timer");
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));

	}

	@Override
	public boolean shouldRefresh(Level level, BlockPos pos, BlockState oldState, BlockState newState)
	{

		return oldState.getBlock() != newState.getBlock();

	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateBlockEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		if (this.blockType != null && this.pos != null)
			this.level.notifyBlockUpdate(this.pos, this.blockType.defaultBlockState(), this.blockType.defaultBlockState(), 1);

	}

	@Override
	@Nullable
	public SPacketUpdateBlockEntity getUpdatePacket()
	{
		CompoundTag tagCompound = new CompoundTag();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateBlockEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.writeToNBT(new CompoundTag());
	}

	private BlockEntity getSurroundingTE(Direction facing)
	{
		BlockPos pos = this.pos.offset(facing);
		return level.getBlockEntity(pos);
	}

	public ItemHandlerHamsterWheel getItemHandler()
	{
		return this.itemHandler;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{

		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
	{

		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.itemHandler;

		if (capability == CapabilityEnergy.ENERGY)
			return (T) this.power;

		return super.getCapability(capability, facing);

	}

	@Override
	public void markDirty()
	{

		// Added nbtSyncTimer to lower network usage
		this.nbtSyncTimer++;
		if (this.nbtSyncTimer > 50)
		{
			super.markDirty();
			AnimaniaHelper.sendBlockEntityUpdate(this);
			this.nbtSyncTimer = 0;
		}
	}

	@Override
	public AnimationHandler<BlockEntityHamsterWheel> getAnimationHandler()
	{
		return animHandler;
	}

	private void updateAnims()
	{
		if (this.isLevelRemote())
		{
			if (this.isRunning && !this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_hamster_wheel", this))
			{
				this.getAnimationHandler().startAnimation(Animania.MODID, "anim_hamster_wheel", this);
				this.getAnimationHandler().startAnimation(Animania.MODID, "hamster_run", this);
			}
			else if (!this.isRunning && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_hamster_wheel", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_hamster_wheel", this);
				this.getAnimationHandler().stopAnimation(Animania.MODID, "hamster_run", this);
			}
		}
	}
}

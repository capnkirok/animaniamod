package com.animania.addons.extra.common.tileentity;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.handler.ExtraAddonBlockHandler;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.common.tileentity.handler.ItemHandlerHamsterWheel;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.AnimatedTileEntity;

import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityHamsterWheel extends AnimatedTileEntity implements ITickable
{
	private static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(TileEntityHamsterWheel.class);

	static
	{
		animHandler.addAnim(Animania.MODID, "anim_hamster_wheel", "model_hamster_wheel", true);
		animHandler.addAnim(Animania.MODID, "hamster_run", "hamster", true);
	}

	private boolean isRunning;
	private EntityHamster hamster;
	private CompoundNBT hamsterNBT;
	private ItemHandlerHamsterWheel itemHandler;
	private int timer;
	private EnergyStorage power;
	private int nbtSyncTimer;

	public TileEntityHamsterWheel()
	{
		this.itemHandler = new ItemHandlerHamsterWheel();
		this.power = new EnergyStorage(ExtraConfig.settings.hamsterWheelCapacity);
	}

	@Override
	public void update()
	{
		if (hamster == null && hamsterNBT != null)
		{
			hamster = new EntityHamster(world);
			hamster.readFromNBT(hamsterNBT);
		}

		if (hamster != null)
		{
			isRunning = true;
			this.markDirty();
		} else
		{
			isRunning = false;
		}

		if (isRunning)
		{
			this.power.receiveEnergy(ExtraConfig.settings.hamsterWheelRFGeneration, false);
			timer++;
			// this.markDirty();

		}

		if (timer >= ExtraConfig.settings.hamsterWheelUseTime)
		{
			if (!itemHandler.getStackInSlot(0).isEmpty())
			{
				ItemStack stack = itemHandler.getStackInSlot(0).copy();
				stack.shrink(1);
				itemHandler.setStackInSlot(0, stack);
			} else
			{
				ejectHamster();
				isRunning = false;
			}
			timer = 0;
			// this.markDirty();

		}

		for (EnumFacing facing : EnumFacing.VALUES)
		{
			TileEntity tile = getSurroundingTE(facing);
			if (tile != null)
			{
				if (Loader.isModLoaded("redstoneflux") && tile instanceof IEnergyReceiver)
				{
					IEnergyReceiver reciever = (IEnergyReceiver) getSurroundingTE(facing);
					int recieved = reciever.receiveEnergy(facing.getOpposite(), this.getEnergy(), false);
					this.power.extractEnergy(recieved, false);
				} else if (tile.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()))
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
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public EntityHamster getHamster()
	{
		return hamster;
	}

	public void setHamster(EntityHamster hamster)
	{
		this.hamster = hamster;
	}

	public int getTimer()
	{
		return timer;
	}

	public void setTimer(int timer)
	{
		this.timer = timer;
	}

	public int getEnergy()
	{
		return power.getEnergyStored();
	}

	public void ejectHamster()
	{
		if (hamster != null && !world.isRemote)
		{
			if (findPositionForHamster())
			{
			AnimaniaHelper.spawnEntity(	world, hamster);
				hamster.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);
				// this.hamster.setWatered(false);
				this.hamster.setFed(false);
				this.hamster = null;
				this.hamsterNBT = null;
			} else
			{
				world.playSound(pos.getX(), pos.getY(), pos.getZ(), ExtraAddonSoundHandler.hamsterDeath, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
				this.hamster = null;
				this.hamsterNBT = null;
			}
		}
	}

	public EnergyStorage getPower()
	{
		return power;
	}

	private boolean findPositionForHamster()
	{
		for (EnumFacing e : EnumFacing.VALUES)
		{
			BlockPos pos = this.pos;
			pos = pos.offset(e);
			IBlockState state = world.getBlockState(pos);

			if (!state.isOpaqueCube() && !state.isFullCube() && state.getBlock() != ExtraAddonBlockHandler.blockHamsterWheel)
			{
				this.hamster.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				return true;
			}
		}
		return false;
	}

	@Override
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		CompoundNBT tag = super.writeToNBT(compound);
		tag.putInteger("energy", power.getEnergyStored());
		tag.putBoolean("running", isRunning);
		tag.putInteger("timer", this.timer);
		CompoundNBT hamster = new CompoundNBT();
		CompoundNBT items = this.itemHandler.serializeNBT();
		tag.putTag("hamster", ((this.hamster == null) ? hamster : this.hamster.writeToNBT(hamster)));
		tag.putTag("items", items);
		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.power = new EnergyStorage(ExtraConfig.settings.hamsterWheelCapacity);
		this.power.receiveEnergy(compound.getInteger("energy"), false);
		CompoundNBT hamster = compound.getCompoundTag("hamster");
		if (!hamster.equals(new CompoundNBT()))
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
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{

		return (oldState.getBlock() != newState.getBlock());

	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		if (this.blockType != null && this.pos != null)
			this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 1);

	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		CompoundNBT tagCompound = new CompoundNBT();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		return this.writeToNBT(new CompoundNBT());
	}

	private TileEntity getSurroundingTE(EnumFacing facing)
	{
		BlockPos pos = this.pos.offset(facing);
		return world.getTileEntity(pos);
	}

	public ItemHandlerHamsterWheel getItemHandler()
	{
		return itemHandler;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{

		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;

		if (capability == CapabilityEnergy.ENERGY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
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
		nbtSyncTimer++;
		if (nbtSyncTimer > 50)
		{
			super.markDirty();
			AnimaniaHelper.sendTileEntityUpdate(this);
			nbtSyncTimer = 0;
		}
	}

	@Override
	public AnimationHandler<TileEntityHamsterWheel> getAnimationHandler()
	{
		return animHandler;
	}

	private void updateAnims()
	{
		if (this.isWorldRemote())
		{
			if (this.isRunning && !this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_hamster_wheel", this))
			{
				this.getAnimationHandler().startAnimation(Animania.MODID, "anim_hamster_wheel", this);
				this.getAnimationHandler().startAnimation(Animania.MODID, "hamster_run", this);
			} else if (!this.isRunning && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_hamster_wheel", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_hamster_wheel", this);
				this.getAnimationHandler().stopAnimation(Animania.MODID, "hamster_run", this);
			}
		}
	}
}

package com.animania.addons.farm.common.BlockEntity;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.tileentity.handler.FluidHandlerBeehive;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.client.handler.AnimationHandler;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.AnimatedBlockEntity;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class BlockEntityHive extends AnimatedBlockEntity implements ITickable
{

	private static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(BlockEntityHive.class);

	static
	{
		animHandler.addAnim(Animania.MODID, "anim_bees_wild", "model_wild_hive", true);
		animHandler.addAnim(Animania.MODID, "anim_bees", "model_bee_hive", true);

	}

	public int nextHoney = 400;
	public FluidHandlerBeehive fluidHandler;
	private boolean isRunning;
	private int nbtSyncTimer;

	public BlockEntityHive()
	{
		this.fluidHandler = new FluidHandlerBeehive(5000);
	}

	@Override
	public void update()
	{
		this.nextHoney--;
		if (this.nextHoney < 1)
		{
			Biome b = level.getBiome(pos);
			boolean isCorrectBiome = false;
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.hiveValidBiomeTypes))
				if (BiomeDictionary.hasType(b, t))
					isCorrectBiome = true;

			if (isCorrectBiome)
			{
				int filled = this.fluidHandler.fill(new FluidStack(FarmAddonBlockHandler.fluidHoney, 25), true);

				if (this.getBlockType() == FarmAddonBlockHandler.blockHive)
					this.nextHoney = FarmConfig.settings.hivePlayermadeHoneyRate + Animania.RANDOM.nextInt(100);
				else
					this.nextHoney = FarmConfig.settings.hiveWildHoneyRate + Animania.RANDOM.nextInt(100);

				if (filled > 0)
					this.markDirty();
			}
		}

		if (this.blockType == FarmAddonBlockHandler.blockWildHive && Animania.RANDOM.nextInt(10) == 0)
		{
			List<Player> players = AnimaniaHelper.getEntitiesInRange(Player.class, 2, this.level, pos);
			for (Player p : players)
			{
				if (Animania.RANDOM.nextInt(3) == 0)
					p.attackEntityFrom(DamageSourceHandler.beeDamage, 2.5f);
			}
		}

		this.updateAnims();
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

	@Override
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		CompoundTag tag = super.writeToNBT(compound);
		CompoundTag fluid = new CompoundTag();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.putInteger("nextHoney", this.nextHoney);
		tag.putTag("fluid", fluid);
		return tag;

	}

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.fluidHandler = new FluidHandlerBeehive(5000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.nextHoney = compound.getInteger("nextHoney");
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public boolean shouldRefresh(Level level, BlockPos pos, BlockState oldState, BlockState newState)
	{
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidHandler;

		return super.getCapability(capability, facing);

	}

	public boolean isRunning()
	{
		return this.isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public Block getHiveType()
	{
		return this.level.getBlockState(pos).getBlock();
	}

	@Override
	public void markDirty()
	{
		this.nbtSyncTimer++;
		if (this.nbtSyncTimer > 50)
		{
			super.markDirty();
			AnimaniaHelper.sendBlockEntityUpdate(this);
			this.nbtSyncTimer = 0;
		}
	}

	@Override
	public AnimationHandler<BlockEntityHive> getAnimationHandler()
	{
		return animHandler;
	}

	private void updateAnims()
	{

		if (this.isLevelRemote())
		{

			if (this.level.getBlockState(pos).getBlock() == FarmAddonBlockHandler.blockWildHive)
			{
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_bees_wild", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_bees_wild", this);
				}
			}
			else if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_bees", this))
			{
				this.getAnimationHandler().startAnimation(Animania.MODID, "anim_bees", this);
			}
		}
	}

}

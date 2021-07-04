package com.animania.addons.farm.common.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.tileentity.handler.FluidHandlerBeehive;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.AnimatedTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityHive extends AnimatedTileEntity implements ITickable
{

	private static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(TileEntityHive.class);

	static
	{
		animHandler.addAnim(Animania.MODID, "anim_bees_wild", "model_wild_hive", true);
		animHandler.addAnim(Animania.MODID, "anim_bees", "model_bee_hive", true);

	}

	public int nextHoney = 400;
	public FluidHandlerBeehive fluidHandler;
	private boolean isRunning;
	private int nbtSyncTimer;

	public TileEntityHive()
	{
		this.fluidHandler = new FluidHandlerBeehive(5000);
	}

	@Override
	public void update()
	{
		nextHoney--;
		if (nextHoney < 1)
		{
			Biome b = world.getBiome(pos);
			boolean isCorrectBiome = false;
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.hiveValidBiomeTypes))
				if (BiomeDictionary.hasType(b, t))
					isCorrectBiome = true;

			if (isCorrectBiome)
			{
				int filled = fluidHandler.fill(new FluidStack(FarmAddonBlockHandler.fluidHoney, 25), true);

				if (this.getBlockType() == FarmAddonBlockHandler.blockHive)
					nextHoney = FarmConfig.settings.hivePlayermadeHoneyRate + Animania.RANDOM.nextInt(100);
				else
					nextHoney = FarmConfig.settings.hiveWildHoneyRate + Animania.RANDOM.nextInt(100);

				if (filled > 0)
					this.markDirty();
			}
		}

		if (this.blockType == FarmAddonBlockHandler.blockWildHive)
		{
			if (Animania.RANDOM.nextInt(10) == 0)
			{
				List<EntityPlayer> players = AnimaniaHelper.getEntitiesInRange(EntityPlayer.class, 2, this.world, pos);
				for (EntityPlayer p : players)
				{
					if (Animania.RANDOM.nextInt(3) == 0)
						p.attackEntityFrom(DamageSourceHandler.beeDamage, 2.5f);
				}
			}
		}

		this.updateAnims();
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

	@Override
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		CompoundNBT tag = super.writeToNBT(compound);
		CompoundNBT fluid = new CompoundNBT();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.setInteger("nextHoney", nextHoney);
		tag.setTag("fluid", fluid);
		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.fluidHandler = new FluidHandlerBeehive(5000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.nextHoney = compound.getInteger("nextHoney");
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return (oldState.getBlock() != newState.getBlock());
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidHandler;

		return super.getCapability(capability, facing);

	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setRunning(boolean isRunning)
	{
		this.isRunning = isRunning;
	}

	public Block getHiveType()
	{
		return this.world.getBlockState(pos).getBlock();
	}

	@Override
	public void markDirty()
	{
		nbtSyncTimer++;
		if (nbtSyncTimer > 50)
		{
			super.markDirty();
			AnimaniaHelper.sendTileEntityUpdate(this);
			nbtSyncTimer = 0;
		}
	}

	@Override
	public AnimationHandler<TileEntityHive> getAnimationHandler()
	{
		return animHandler;
	}

	private void updateAnims()
	{

		if (this.isWorldRemote())
		{

			if (this.world.getBlockState(pos).getBlock() == FarmAddonBlockHandler.blockWildHive)
			{
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_bees_wild", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_bees_wild", this);
				}
			} else
			{
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_bees", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_bees", this);
				}
			}
		}
	}

}

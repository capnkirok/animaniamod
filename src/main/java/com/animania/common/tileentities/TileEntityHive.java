package com.animania.common.tileentities;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.common.blocks.BlockHive;
import com.animania.common.blocks.BlockHive.HiveVariant;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.FluidHandlerBeehive;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityHive extends TileEntity implements ITickable
{

	public int nextHoney = 400;
	public FluidHandlerBeehive fluidHandler;
	private Random rand = new Random();

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
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.gameRules.hiveValidBiomeTypes))
				if (BiomeDictionary.hasType(b, t))
					isCorrectBiome = true;

			if (isCorrectBiome)
			{
				int filled = fluidHandler.fill(new FluidStack(BlockHandler.fluidHoney, 25), true);

				if (this.getBlockType() == BlockHandler.blockHive)
					if (this.world.getBlockState(pos).getValue(BlockHive.VARIANT) == HiveVariant.WILD)
						nextHoney = AnimaniaConfig.gameRules.hiveWildHoneyRate + rand.nextInt(100);
					else
						nextHoney = AnimaniaConfig.gameRules.hivePlayermadeHoneyRate + rand.nextInt(100);

				if (filled > 0)
					this.markDirty();
			}
		}
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
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		NBTTagCompound fluid = new NBTTagCompound();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.setInteger("nextHoney", nextHoney);
		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.fluidHandler = new FluidHandlerBeehive(5000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.nextHoney = compound.getInteger("nextHoney");
	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		AnimaniaHelper.sendTileEntityUpdate(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidHandler;

		return super.getCapability(capability, facing);

	}
}

package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.tileentities.handler.FluidHandlerCheeseMold;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityCheeseMold extends TileEntity implements ITickable
{

	private FluidHandlerCheeseMold fluidHandler;
	private int progress;
	
	public TileEntityCheeseMold()
	{
		fluidHandler = new FluidHandlerCheeseMold();
	}

	@Override
	public void update()
	{
		if(this.fluidHandler.getFluid() != null && this.fluidHandler.getFluid().amount >= 1000)
			progress++;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidHandler;

		return super.getCapability(capability, facing);

	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());

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


	public FluidHandlerCheeseMold getFluidHandler()
	{
		return fluidHandler;
	}

	public int getProgress()
	{
		return progress;
	}

	public void setProgress(int progress)
	{
		this.progress = progress;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		NBTTagCompound fluid = new NBTTagCompound();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.setTag("fluid", fluid);

		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.fluidHandler = new FluidHandlerCheeseMold();
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
	}
	

}

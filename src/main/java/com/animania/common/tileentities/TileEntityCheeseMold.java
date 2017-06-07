package com.animania.common.tileentities;

import com.animania.common.tileentities.handler.FluidHandlerCheeseMold;
import com.animania.config.AnimaniaConfig;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

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
	
	

}

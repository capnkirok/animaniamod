package com.animania.common.tileentities.handler;

import com.animania.common.handler.BlockHandler;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidHandlerBeehive extends FluidTank
{

	public FluidHandlerBeehive(int capacity)
	{
		super(capacity);
	}

	 @Override
	    public boolean canFillFluidType(FluidStack fluid) {
	        return fluid.getFluid() == BlockHandler.fluidHoney;
	    }
}

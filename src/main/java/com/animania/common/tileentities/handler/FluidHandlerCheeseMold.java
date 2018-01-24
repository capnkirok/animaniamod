package com.animania.common.tileentities.handler;

import com.animania.common.handler.BlockHandler;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidHandlerCheeseMold extends FluidTank
{

	public FluidHandlerCheeseMold()
	{
		super(1000);
	}
	
	@Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluid.getFluid() == BlockHandler.fluidMilkFriesian || fluid.getFluid() == BlockHandler.fluidMilkHolstein || fluid.getFluid() == BlockHandler.fluidMilkJersey || fluid.getFluid() == BlockHandler.fluidMilkSheep || fluid.getFluid() == BlockHandler.fluidMilkGoat || fluid.getFluid() == FluidRegistry.WATER;
    }


}

package com.animania.common.blockentities.handler;

import com.animania.common.handler.BlockHandler;

import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerTrough extends FluidTank
{

	public FluidHandlerTrough(int capacity)
	{
		super(capacity);
	}

	@Override
	public boolean isFluidValid(FluidStack stack) {
		return fluid.getFluid() == Fluids.WATER || fluid.getFluid() == BlockHandler.fluidSlop;
	}

}

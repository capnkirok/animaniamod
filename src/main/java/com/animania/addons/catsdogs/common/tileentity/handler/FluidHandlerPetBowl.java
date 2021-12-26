package com.animania.addons.catsdogs.common.BlockEntity.handler;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerPetBowl extends FluidTank
{

	public FluidHandlerPetBowl(int capacity)
	{
		super(capacity);
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid)
	{
		return fluid.getFluid() == FluidRegistry.WATER;
	}

}

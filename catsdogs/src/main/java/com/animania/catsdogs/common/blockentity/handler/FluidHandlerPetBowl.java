package com.animania.catsdogs.common.blockentity.handler;

import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerPetBowl extends FluidTank
{

	public FluidHandlerPetBowl(int capacity)
	{
		super(capacity);
	}

	@Override
	public boolean isFluidValid(FluidStack fluid)
	{
		return fluid.getFluid() == Fluids.WATER;
	}

}

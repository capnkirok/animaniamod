package com.animania.common.tileentities.handler;

import com.animania.common.handler.BlockHandler;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerTrough extends FluidTank
{

    public FluidHandlerTrough(int capacity) {
        super(capacity);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluid.getFluid() == FluidRegistry.WATER || fluid.getFluid() == BlockHandler.fluidSlop;
    }

}

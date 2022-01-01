package common.tileentity.handler;

import common.handler.FarmAddonBlockHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerBeehive extends FluidTank
{

	public FluidHandlerBeehive(int capacity)
	{
		super(capacity);
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid)
	{
		return fluid.getFluid() == FarmAddonBlockHandler.fluidHoney;
	}
}

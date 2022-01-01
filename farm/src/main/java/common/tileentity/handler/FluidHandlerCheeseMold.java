package common.tileentity.handler;

import common.handler.FarmAddonBlockHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidHandlerCheeseMold extends FluidTank
{

	public FluidHandlerCheeseMold()
	{
		super(1000);
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid)
	{
		return fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian || fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein || fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey || fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep || fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat || fluid.getFluid() == FluidRegistry.WATER;
	}

}

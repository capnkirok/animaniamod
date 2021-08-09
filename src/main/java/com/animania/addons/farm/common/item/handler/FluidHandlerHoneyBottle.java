package com.animania.addons.farm.common.item.handler;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class FluidHandlerHoneyBottle extends FluidHandlerItemStack.SwapEmpty
{

	public FluidHandlerHoneyBottle(ItemStack stack)
	{
		super(stack, new ItemStack(Items.GLASS_BOTTLE), 1000);
	}
	
	@Override
	public boolean canFillFluidType(FluidStack fluid)
	{
        return fluid.getFluid() == FarmAddonBlockHandler.fluidHoney;
	}

	

}

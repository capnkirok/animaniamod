package com.animania.common.items.handler;

import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        return fluid.getFluid() == BlockHandler.fluidHoney;
	}

	

}

package com.animania.common.items.handler;

import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class FluidHandlerJar extends FluidBucketWrapper
{

	public FluidHandlerJar(ItemStack container)
	{
		super(container);
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid)
	{
		return fluid.getFluid() == BlockHandler.fluidHoney;
	}

	@Override
	public ItemStack getContainer()
	{
		return container;
	}

	protected void setFluid(@Nullable FluidStack fluidStack)
	{
		if(fluidStack != null)
		{
		NBTTagCompound tag = new NBTTagCompound();
        fluidStack.writeToNBT(tag);
        container.setTagCompound(tag);
		}
		else 
			container = new ItemStack(ItemHandler.honeyJar);
        
	}

}

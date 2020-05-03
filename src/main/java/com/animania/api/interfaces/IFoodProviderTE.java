package com.animania.api.interfaces;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFoodProviderTE
{
	public boolean canConsume(@Nullable Set<ItemStack> fooditems, @Nullable Fluid[] fluids);

	public boolean canConsume(@Nullable FluidStack fluid, @Nullable Set<ItemStack> fooditems);

	public void consumeSolidOrLiquid(int liquidAmount, int itemAmount);

	public void consumeSolid(int amount);

	public void consumeLiquid(int amount);

}

package com.animania.api.interfaces;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFoodProviderTE
{
	public boolean canConsume(@Nullable Set<ItemStack> fooditems, @Nullable Fluid[] fluids);

	public boolean canConsume(@Nullable FluidStack fluid, @Nullable Set<ItemStack> fooditems);

	public void consumeSolidOrLiquid(int liquidAmount, int itemAmount);

	public void consumeSolid(int amount);

	public void consumeLiquid(int amount);

}

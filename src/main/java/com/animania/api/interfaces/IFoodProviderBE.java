package com.animania.api.interfaces;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFoodProviderBE
{
	boolean canConsume(@Nullable Set<ItemStack> fooditems, @Nullable Fluid[] fluids);

	boolean canConsume(@Nullable FluidStack fluid, @Nullable Set<ItemStack> fooditems);

	void consumeSolidOrLiquid(int liquidAmount, int itemAmount);

	void consumeSolid(int amount);

	void consumeLiquid(int amount);

}

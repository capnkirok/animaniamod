package com.animania.addons.farm.compat;

import javax.annotation.Nonnull;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

@JEIPlugin
public class FarmAddonJEICompat extends BlankModPlugin
{

	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		ItemStack milkHolstein = FluidUtil.getFilledBucket(new FluidStack(FarmAddonBlockHandler.fluidMilkHolstein, Fluid.BUCKET_VOLUME));
		ItemStack milkFriesian = FluidUtil.getFilledBucket(new FluidStack(FarmAddonBlockHandler.fluidMilkFriesian, Fluid.BUCKET_VOLUME));
		ItemStack milkGoat = FluidUtil.getFilledBucket(new FluidStack(FarmAddonBlockHandler.fluidMilkGoat, Fluid.BUCKET_VOLUME));
		ItemStack milkSheep = FluidUtil.getFilledBucket(new FluidStack(FarmAddonBlockHandler.fluidMilkSheep, Fluid.BUCKET_VOLUME));

		registry.addDescription(new ItemStack(FarmAddonItemHandler.truffle), "text.jei.truffle");

		registry.addDescription(milkHolstein, "text.jei.milkholstein");
		registry.addDescription(milkFriesian, "text.jei.milkfriesian");
		registry.addDescription(milkGoat, "text.jei.milkgoat");
		registry.addDescription(milkSheep, "text.jei.milksheep");
		registry.addDescription(new ItemStack(FarmAddonItemHandler.salt), "text.jei.salt");
	}

}

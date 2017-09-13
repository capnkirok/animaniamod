package com.animania.compat.jei;

import javax.annotation.Nonnull;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

@JEIPlugin
public class JEICompat extends BlankModPlugin
{

	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		ItemStack slopBucket = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidSlop, Fluid.BUCKET_VOLUME));
		ItemStack milkHolstein = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidMilkHolstein, Fluid.BUCKET_VOLUME));
		ItemStack milkFriesian = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidMilkFriesian, Fluid.BUCKET_VOLUME));
		ItemStack milkGoat = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidMilkGoat, Fluid.BUCKET_VOLUME));
		ItemStack milkSheep = FluidUtil.getFilledBucket(new FluidStack(BlockHandler.fluidMilkSheep, Fluid.BUCKET_VOLUME));

		registry.addDescription(new ItemStack(BlockHandler.blockTrough), "text.jei.trough");
		registry.addDescription(new ItemStack(BlockHandler.blockMud), "text.jei.mud");
		registry.addDescription(new ItemStack(Items.WHEAT_SEEDS), "text.jei.seeds");
		registry.addDescription(new ItemStack(ItemHandler.truffle), "text.jei.truffle");
		registry.addDescription(new ItemStack(BlockHandler.blockNest), "text.jei.nest");
		registry.addDescription(slopBucket, "text.jei.slop");
		registry.addDescription(milkHolstein, "text.jei.milkholstein");
		registry.addDescription(milkFriesian, "text.jei.milkfriesian");
		registry.addDescription(milkGoat, "text.jei.milkgoat");
		registry.addDescription(milkSheep, "text.jei.milksheep");
		registry.addDescription(new ItemStack(ItemHandler.salt), "text.jei.salt");

	}

}

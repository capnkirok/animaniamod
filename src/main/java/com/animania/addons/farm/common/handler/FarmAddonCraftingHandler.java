package com.animania.addons.farm.common.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.registries.GameRegistry;

public class FarmAddonCraftingHandler
{
	/**
	 * Register Recipes
	 */
	public static void init()
	{
		ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkHolstein);
		ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkFriesian);
		ItemStack milkGoat = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkGoat);
		ItemStack milkSheep = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkSheep);

		// Smelting Recipes

		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeBeef, new ItemStack(FarmAddonItemHandler.cookedPrimeBeef, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeSteak, new ItemStack(FarmAddonItemHandler.cookedPrimeSteak, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimePork, new ItemStack(FarmAddonItemHandler.cookedPrimePork, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeBacon, new ItemStack(FarmAddonItemHandler.cookedPrimeBacon, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeChicken, new ItemStack(FarmAddonItemHandler.cookedPrimeChicken, 1), .3F);

		GameRegistry.addSmelting(Items.EGG, new ItemStack(FarmAddonItemHandler.plainOmelette, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.brownEgg, new ItemStack(FarmAddonItemHandler.plainOmelette, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeMutton, new ItemStack(FarmAddonItemHandler.cookedPrimeMutton, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawChevon, new ItemStack(FarmAddonItemHandler.cookedChevon, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawPrimeChevon, new ItemStack(FarmAddonItemHandler.cookedPrimeChevon, 1), .3F);
		GameRegistry.addSmelting(FarmAddonItemHandler.rawHorse, new ItemStack(FarmAddonItemHandler.cookedHorse, 1), .3F);

	}
}

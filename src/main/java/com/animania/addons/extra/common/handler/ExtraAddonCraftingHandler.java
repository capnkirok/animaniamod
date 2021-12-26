package com.animania.addons.extra.common.handler;

import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.cookedFrogLegs;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.cookedPeacock;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.cookedPrimePeacock;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.cookedPrimeRabbit;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.peacockEggBlue;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.peacockEggWhite;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.rawFrogLegs;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.rawPeacock;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.rawPrimePeacock;
import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.rawPrimeRabbit;

import com.animania.common.helper.RegistryHelper.RItem;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ExtraAddonCraftingHandler
{
	/**
	 * Register Recipes
	 */
	public static void init()
	{
		GameRegistry.addSmelting(rawFrogLegs, new ItemStack(cookedFrogLegs, 1), .3F);
		GameRegistry.addSmelting(rawPrimeRabbit, new ItemStack(cookedPrimeRabbit, 1), .3F);

		RItem plainOmelette = RItem.getByNameOrId("animania:plain_omelette");

		if (plainOmelette != null)
		{
			GameRegistry.addSmelting(peacockEggBlue, new ItemStack(plainOmelette, 1), .3F);
			GameRegistry.addSmelting(peacockEggWhite, new ItemStack(plainOmelette, 1), .3F);
		}

		GameRegistry.addSmelting(rawPeacock, new ItemStack(cookedPeacock, 1), .3F);
		GameRegistry.addSmelting(rawPrimePeacock, new ItemStack(cookedPrimePeacock, 1), .3F);

	}
}

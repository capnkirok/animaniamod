package common.handler;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.animania.addons.extra.common.handler.ExtraAddonItemHandler.*;

public class ExtraAddonCraftingHandler
{
	/**
	 * Register Recipes
	 */
	public static void init()
	{
		GameRegistry.addSmelting(rawFrogLegs, new ItemStack(cookedFrogLegs, 1), .3F);
		GameRegistry.addSmelting(rawPrimeRabbit, new ItemStack(cookedPrimeRabbit, 1), .3F);

		Item plainOmelette = Item.getByNameOrId("animania:plain_omelette");

		if (plainOmelette != null)
		{
			GameRegistry.addSmelting(peacockEggBlue, new ItemStack(plainOmelette, 1), .3F);
			GameRegistry.addSmelting(peacockEggWhite, new ItemStack(plainOmelette, 1), .3F);
		}

		GameRegistry.addSmelting(rawPeacock, new ItemStack(cookedPeacock, 1), .3F);
		GameRegistry.addSmelting(rawPrimePeacock, new ItemStack(cookedPrimePeacock, 1), .3F);

	}
}

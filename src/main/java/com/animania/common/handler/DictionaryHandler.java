package com.animania.common.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class DictionaryHandler
{

	public static void init()
	{

		OreDictionary.registerOre("blockMud", BlockHandler.blockMud);

		OreDictionary.registerOre("cropCarrot", Items.CARROT);
		OreDictionary.registerOre("cropPotato", Items.POTATO);
		OreDictionary.registerOre("cropBeet", Items.BEETROOT);
		OreDictionary.registerOre("bread", Items.BREAD);
		OreDictionary.registerOre("listAllsugar", Items.SUGAR);
		OreDictionary.registerOre("listAllseed", Items.WHEAT_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.MELON_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.BEETROOT_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.PUMPKIN_SEEDS);

		OreDictionary.registerOre("listAllchickenraw", Items.CHICKEN);
		OreDictionary.registerOre("listAllbeefraw", Items.BEEF);
		OreDictionary.registerOre("listAllporkraw", Items.PORKCHOP);
		OreDictionary.registerOre("listAllchickencooked", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("listAllbeefcooked", Items.COOKED_BEEF);
		OreDictionary.registerOre("listAllporkcooked", Items.COOKED_PORKCHOP);

		OreDictionary.registerOre("blockWool", new ItemStack(Blocks.WOOL, 1, Short.MAX_VALUE));

		// Additions 1.2.1
		OreDictionary.registerOre("dyeBlack", new ItemStack(Items.DYE, 1, 0));
		OreDictionary.registerOre("dyeRed", new ItemStack(Items.DYE, 1, 1));
		OreDictionary.registerOre("dyeGreen", new ItemStack(Items.DYE, 1, 2));
		OreDictionary.registerOre("dyeBrown", new ItemStack(Items.DYE, 1, 3));
		OreDictionary.registerOre("dyeBlue", new ItemStack(Items.DYE, 1, 4));
		OreDictionary.registerOre("dyePurple", new ItemStack(Items.DYE, 1, 5));
		OreDictionary.registerOre("dyeCyan", new ItemStack(Items.DYE, 1, 6));
		OreDictionary.registerOre("dyeLightGray", new ItemStack(Items.DYE, 1, 7));
		OreDictionary.registerOre("dyeGray", new ItemStack(Items.DYE, 1, 8));
		OreDictionary.registerOre("dyePink", new ItemStack(Items.DYE, 1, 9));
		OreDictionary.registerOre("dyeLime", new ItemStack(Items.DYE, 1, 10));
		OreDictionary.registerOre("dyeYellow", new ItemStack(Items.DYE, 1, 11));
		OreDictionary.registerOre("dyeLightBlue", new ItemStack(Items.DYE, 1, 12));
		OreDictionary.registerOre("dyeMagenta", new ItemStack(Items.DYE, 1, 13));
		OreDictionary.registerOre("dyeOrange", new ItemStack(Items.DYE, 1, 14));
		OreDictionary.registerOre("dyeWhite", new ItemStack(Items.DYE, 1, 15));
	}
}

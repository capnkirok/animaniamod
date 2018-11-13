package com.animania.common.handler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.oredict.OreDictionary;

public class DictionaryHandler
{

	public static void init() {

			
		
		
		OreDictionary.registerOre("egg", ItemHandler.brownEgg);
		OreDictionary.registerOre("egg", ItemHandler.peacockEggBlue);
		OreDictionary.registerOre("egg", ItemHandler.peacockEggWhite);
		OreDictionary.registerOre("egg", ItemHandler.peacockEggWhite);
		OreDictionary.registerOre("listAllegg", ItemHandler.brownEgg);
		OreDictionary.registerOre("listAllegg", ItemHandler.peacockEggBlue);
		OreDictionary.registerOre("listAllegg", ItemHandler.peacockEggWhite);
		OreDictionary.registerOre("salt", ItemHandler.salt);
		OreDictionary.registerOre("listAllsalt", ItemHandler.salt);
		OreDictionary.registerOre("dustSalt", ItemHandler.salt);
		OreDictionary.registerOre("itemSalt", ItemHandler.salt);
		OreDictionary.registerOre("foodSalt", ItemHandler.salt);
		OreDictionary.registerOre("wool", new ItemStack(BlockHandler.blockAnimaniaWool, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("foodHoney", ItemHandler.honeyJar);
		OreDictionary.registerOre("foodHoneydrop", ItemHandler.honeyJar);
		OreDictionary.registerOre("dropHoney", ItemHandler.honeyJar);

		
		OreDictionary.registerOre("blockMud", BlockHandler.blockMud);
		
		
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeMutton);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeRabbit);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawChevon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeChevon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHorse);

		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeMutton);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeRabbit);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedChevon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeChevon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHorse);

		// Raw Prime Meats
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeChicken);
		OreDictionary.registerOre("listAllchickenraw", ItemHandler.rawPrimeChicken);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimePork);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawPrimePork);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawPrimeBacon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeBeef);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawPrimeBeef);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawPrimeSteak); 
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeSteak); 
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawFrogLegs);

		// Cooked Prime Meats
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeChicken);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimePork);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeBeef);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedFrogLegs);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedPrimeBeef);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedPrimeSteak);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedPrimePork);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedPrimeBacon);
		OreDictionary.registerOre("listAllchickencooked", ItemHandler.cookedPrimeChicken);

		OreDictionary.registerOre("foodCheese", ItemHandler.cheeseWedgeFriesian);
		OreDictionary.registerOre("foodCheese", ItemHandler.cheeseWedgeHolstein);
		OreDictionary.registerOre("foodCheese", ItemHandler.cheeseWedgeGoat);
		OreDictionary.registerOre("foodCheese", ItemHandler.cheeseWedgeSheep);
		OreDictionary.registerOre("foodCheese", ItemHandler.cheeseWedgeJersey);
		
		OreDictionary.registerOre("cropCarrot", Items.CARROT);
		OreDictionary.registerOre("cropPotato", Items.POTATO);
		OreDictionary.registerOre("cropBeet", Items.BEETROOT);
		OreDictionary.registerOre("bread", Items.BREAD);
		OreDictionary.registerOre("listAllsugar", Items.SUGAR);
		OreDictionary.registerOre("listAllseed", Items.WHEAT_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.MELON_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.BEETROOT_SEEDS);
		OreDictionary.registerOre("listAllseed", Items.PUMPKIN_SEEDS);
		
		//Bacon
		OreDictionary.registerOre("foodBaconCooked", ItemHandler.cookedPrimeBacon);

		// Additions 1.0.4.8
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimePork);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeBeef);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimePork);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeBeef);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedFrogLegs);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeMutton);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeRabbit);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawChevon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPrimeChevon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeMutton);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeRabbit);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedChevon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPrimeChevon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawHorse);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedHorse);
		OreDictionary.registerOre("listAllchickenraw", Items.CHICKEN);
		OreDictionary.registerOre("listAllbeefraw", Items.BEEF);
		OreDictionary.registerOre("listAllporkraw", Items.PORKCHOP);
		OreDictionary.registerOre("listAllchickencooked", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("listAllbeefcooked", Items.COOKED_BEEF);
		OreDictionary.registerOre("listAllporkcooked", Items.COOKED_PORKCHOP);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherBlue);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherWhite);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherCharcoal);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherOpal);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherPeach);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherPurple);
		OreDictionary.registerOre("feather", ItemHandler.peacockFeatherTaupe);
		
		OreDictionary.registerOre("blockWool", new ItemStack(Blocks.WOOL, 1, Short.MAX_VALUE));
		OreDictionary.registerOre("blockWool", new ItemStack(BlockHandler.blockAnimaniaWool, 1, Short.MAX_VALUE));
		
		//Additions 1.2.1
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

		// TODO
		// add ferret eating meats ore dict

	}
}

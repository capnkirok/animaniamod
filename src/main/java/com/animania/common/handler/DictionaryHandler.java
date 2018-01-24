package com.animania.common.handler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.oredict.OreDictionary;

public class DictionaryHandler
{

	public static void init() {

		ItemStack milkHolstein = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein);
		ItemStack milkFriesian = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
		ItemStack milkJersey = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkJersey);
		ItemStack milkGoat = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkGoat);
		ItemStack milkSheep = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkSheep);

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
		OreDictionary.registerOre("blockWool", new ItemStack(BlockHandler.blockAnimaniaWool, 1, OreDictionary.WILDCARD_VALUE));
		
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawAngusBeef);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawAngusSteak);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawDurocBacon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawDurocPork);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHampshireBacon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHampshirePork);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHerefordBeef);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHerefordSteak);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawLargeBlackBacon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawLargeBlackPork);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawLonghornBeef);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawLonghornSteak);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawOldSpotBacon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawOldSpotPork);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawOrpingtonChicken);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPlymouthRockChicken);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawWyandotteChicken);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawMutton);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawRabbit);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawChevon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawPrimeChevon);
		OreDictionary.registerOre("listAllmeatraw", ItemHandler.rawHorse);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawAngusBeef);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawAngusSteak);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawHerefordBeef);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawHerefordSteak);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawLonghornBeef);
		OreDictionary.registerOre("listAllbeefraw", ItemHandler.rawLonghornSteak);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawDurocBacon);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawDurocPork);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawHampshireBacon);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawHampshirePork);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawLargeBlackBacon);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawLargeBlackPork);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawOldSpotBacon);
		OreDictionary.registerOre("listAllporkraw", ItemHandler.rawOldSpotPork);
		OreDictionary.registerOre("listAllchickenraw", ItemHandler.rawOrpingtonChicken);
		OreDictionary.registerOre("listAllchickenraw", ItemHandler.rawPlymouthRockChicken);
		OreDictionary.registerOre("listAllchickenraw", ItemHandler.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllchickenraw", ItemHandler.rawWyandotteChicken);

		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedAngusRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedAngusSteak);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedDurocBacon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedDurocRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHampshireBacon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHampshireRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHerefordRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHerefordSteak);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedLargeBlackBacon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedLargeBlackRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedLonghornRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedLonghornSteak);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedOldSpotBacon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedOldSpotRoast);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedOrpingtonChicken);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPlymouthRockChicken);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedWyandotteChicken);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedMutton);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedRabbit);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedChevon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedPrimeChevon);
		OreDictionary.registerOre("listAllmeatcooked", ItemHandler.cookedHorse);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedAngusRoast);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedAngusSteak);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedHerefordRoast);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedHerefordSteak);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedLonghornRoast);
		OreDictionary.registerOre("listAllbeefcooked", ItemHandler.cookedLonghornSteak);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedDurocBacon);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedDurocRoast);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedHampshireBacon);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedHampshireRoast);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedLargeBlackBacon);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedLargeBlackRoast);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedOldSpotBacon);
		OreDictionary.registerOre("listAllporkcooked", ItemHandler.cookedOldSpotRoast);
		OreDictionary.registerOre("listAllchickencooked", ItemHandler.cookedOrpingtonChicken);
		OreDictionary.registerOre("listAllchickencooked", ItemHandler.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("listAllchickencooked", ItemHandler.cookedPlymouthRockChicken);
		OreDictionary.registerOre("listAllchickencooked", ItemHandler.cookedWyandotteChicken);

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

		OreDictionary.registerOre("cropCarrot", Items.CARROT);
		OreDictionary.registerOre("cropPotato", Items.POTATO);
		OreDictionary.registerOre("cropBeet", Items.BEETROOT);
		OreDictionary.registerOre("bread", Items.BREAD);
		OreDictionary.registerOre("listAllsugar", Items.SUGAR);
		OreDictionary.registerOre("listAllseed", Items.WHEAT_SEEDS);

		// Additions 1.0.4.8
		OreDictionary.registerOre("foodMeats", ItemHandler.rawAngusBeef);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawAngusSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawDurocBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawDurocPork);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawHampshireBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawHampshirePork);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawHerefordBeef);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawHerefordSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawLargeBlackBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawLargeBlackPork);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawLonghornBeef);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawLonghornSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawOldSpotBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawOldSpotPork);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawOrpingtonChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawPlymouthRockChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawWyandotteChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.rawRhodeIslandRedChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedAngusRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedAngusSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedDurocBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedDurocRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedHampshireBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedHampshireRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedHerefordRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedHerefordSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedLargeBlackBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedLargeBlackRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedLonghornRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedLonghornSteak);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedOldSpotBacon);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedOldSpotRoast);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedOrpingtonChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedPlymouthRockChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedRhodeIslandRedChicken);
		OreDictionary.registerOre("foodMeats", ItemHandler.cookedWyandotteChicken);
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

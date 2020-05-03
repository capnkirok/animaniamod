package com.animania.addons.farm.common.handler;

import static com.animania.addons.farm.common.handler.FarmAddonBlockHandler.blockAnimaniaWool;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.brownEgg;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cheeseWedgeFriesian;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cheeseWedgeGoat;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cheeseWedgeHolstein;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cheeseWedgeJersey;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cheeseWedgeSheep;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedChevon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedHorse;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeBacon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeBeef;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeChevon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeChicken;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeMutton;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimePork;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.cookedPrimeSteak;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.honeyJar;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawChevon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawHorse;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeBacon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeBeef;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeChevon;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeChicken;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeMutton;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimePork;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.rawPrimeSteak;
import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.salt;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FarmAddonOredictHandler
{

	public static void init()
	{
		OreDictionary.registerOre("egg", brownEgg);

		OreDictionary.registerOre("listAllegg", brownEgg);

		OreDictionary.registerOre("salt", salt);
		OreDictionary.registerOre("listAllsalt", salt);
		OreDictionary.registerOre("dustSalt", salt);
		OreDictionary.registerOre("itemSalt", salt);
		OreDictionary.registerOre("foodSalt", salt);
		OreDictionary.registerOre("wool", new ItemStack(blockAnimaniaWool, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("foodHoney", honeyJar);
		OreDictionary.registerOre("foodHoneydrop", honeyJar);
		OreDictionary.registerOre("dropHoney", honeyJar);

		OreDictionary.registerOre("listAllmeatraw", rawPrimeMutton);
		OreDictionary.registerOre("listAllmeatraw", rawChevon);
		OreDictionary.registerOre("listAllmeatraw", rawPrimeChevon);
		OreDictionary.registerOre("listAllmeatraw", rawHorse);

		OreDictionary.registerOre("listAllmeatcooked", cookedPrimeMutton);

		OreDictionary.registerOre("listAllmeatcooked", cookedChevon);
		OreDictionary.registerOre("listAllmeatcooked", cookedPrimeChevon);
		OreDictionary.registerOre("listAllmeatcooked", cookedHorse);

		// Raw Prime Meats
		OreDictionary.registerOre("listAllmeatraw", rawPrimeChicken);
		OreDictionary.registerOre("listAllchickenraw", rawPrimeChicken);
		OreDictionary.registerOre("listAllmeatraw", rawPrimePork);
		OreDictionary.registerOre("listAllporkraw", rawPrimePork);
		OreDictionary.registerOre("listAllporkraw", rawPrimeBacon);
		OreDictionary.registerOre("listAllmeatraw", rawPrimeBeef);
		OreDictionary.registerOre("listAllbeefraw", rawPrimeBeef);
		OreDictionary.registerOre("listAllbeefraw", rawPrimeSteak);
		OreDictionary.registerOre("listAllmeatraw", rawPrimeSteak);

		// Cooked Prime Meats
		OreDictionary.registerOre("listAllmeatcooked", cookedPrimeChicken);
		OreDictionary.registerOre("listAllmeatcooked", cookedPrimePork);
		OreDictionary.registerOre("listAllmeatcooked", cookedPrimeBeef);

		OreDictionary.registerOre("listAllbeefcooked", cookedPrimeBeef);
		OreDictionary.registerOre("listAllbeefcooked", cookedPrimeSteak);
		OreDictionary.registerOre("listAllporkcooked", cookedPrimePork);
		OreDictionary.registerOre("listAllporkcooked", cookedPrimeBacon);
		OreDictionary.registerOre("listAllchickencooked", cookedPrimeChicken);

		OreDictionary.registerOre("foodCheese", cheeseWedgeFriesian);
		OreDictionary.registerOre("foodCheese", cheeseWedgeHolstein);
		OreDictionary.registerOre("foodCheese", cheeseWedgeGoat);
		OreDictionary.registerOre("foodCheese", cheeseWedgeSheep);
		OreDictionary.registerOre("foodCheese", cheeseWedgeJersey);

		// Bacon
		OreDictionary.registerOre("foodBaconCooked", cookedPrimeBacon);

		// Additions 1.0.4.8
		OreDictionary.registerOre("foodMeats", rawPrimeChicken);
		OreDictionary.registerOre("foodMeats", rawPrimePork);
		OreDictionary.registerOre("foodMeats", rawPrimeBeef);
		OreDictionary.registerOre("foodMeats", rawPrimeSteak);
		OreDictionary.registerOre("foodMeats", rawPrimeBacon);
		OreDictionary.registerOre("foodMeats", cookedPrimeChicken);
		OreDictionary.registerOre("foodMeats", cookedPrimePork);
		OreDictionary.registerOre("foodMeats", cookedPrimeBacon);
		OreDictionary.registerOre("foodMeats", cookedPrimeBeef);
		OreDictionary.registerOre("foodMeats", cookedPrimeSteak);
		OreDictionary.registerOre("foodMeats", rawPrimeMutton);
		OreDictionary.registerOre("foodMeats", rawChevon);
		OreDictionary.registerOre("foodMeats", rawPrimeChevon);
		OreDictionary.registerOre("foodMeats", cookedPrimeMutton);
		OreDictionary.registerOre("foodMeats", cookedChevon);
		OreDictionary.registerOre("foodMeats", cookedPrimeChevon);
		OreDictionary.registerOre("foodMeats", rawHorse);
		OreDictionary.registerOre("foodMeats", cookedHorse);

		OreDictionary.registerOre("blockWool", new ItemStack(blockAnimaniaWool, 1, Short.MAX_VALUE));

	}

}

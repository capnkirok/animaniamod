package com.animania.addons.extra.common.handler;

import net.minecraftforge.oredict.OreDictionary;

public class ExtraAddonOredictHandler
{
	public static void init()
	{
		OreDictionary.registerOre("listAllmeatraw", ExtraAddonItemHandler.rawPrimeRabbit);
		OreDictionary.registerOre("listAllmeatcooked", ExtraAddonItemHandler.cookedPrimeRabbit);
		OreDictionary.registerOre("listAllmeatraw", ExtraAddonItemHandler.rawFrogLegs);
		OreDictionary.registerOre("listAllmeatcooked", ExtraAddonItemHandler.cookedFrogLegs);
		OreDictionary.registerOre("egg", ExtraAddonItemHandler.peacockEggBlue);
		OreDictionary.registerOre("egg", ExtraAddonItemHandler.peacockEggWhite);
		OreDictionary.registerOre("egg", ExtraAddonItemHandler.peacockEggWhite);
		OreDictionary.registerOre("listAllegg", ExtraAddonItemHandler.peacockEggBlue);
		OreDictionary.registerOre("listAllegg", ExtraAddonItemHandler.peacockEggWhite);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherBlue);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherWhite);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherCharcoal);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherOpal);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherPeach);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherPurple);
		OreDictionary.registerOre("feather", ExtraAddonItemHandler.peacockFeatherTaupe);
		OreDictionary.registerOre("foodMeats", ExtraAddonItemHandler.cookedFrogLegs);
		OreDictionary.registerOre("foodMeats", ExtraAddonItemHandler.rawPrimeRabbit);
		OreDictionary.registerOre("foodMeats", ExtraAddonItemHandler.cookedPrimeRabbit);
	}
}

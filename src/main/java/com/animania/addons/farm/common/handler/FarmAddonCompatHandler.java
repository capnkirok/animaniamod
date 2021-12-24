package com.animania.addons.farm.common.handler;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class FarmAddonCompatHandler
{
	public static void preInit()
	{
		if (ModList.get().isLoaded("waila"))
			FMLInterModComms.sendMessage("waila", "register", "com.animania.addons.farm.compat.FarmAddonWailaCompat.registerWaila");

	}
}

package com.animania.addons.farm.common.handler;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class FarmAddonCompatHandler
{
	public static void preInit()
	{
		if (Loader.isModLoaded("waila"))
			FMLInterModComms.sendMessage("waila", "register", "com.animania.addons.farm.compat.FarmAddonWailaCompat.registerWaila");

	}
}

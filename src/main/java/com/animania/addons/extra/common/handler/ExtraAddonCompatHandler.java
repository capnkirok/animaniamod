package com.animania.addons.extra.common.handler;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class ExtraAddonCompatHandler
{
	public static void preInit()
	{
		if (ModList.get().isLoaded("waila"))
			FMLInterModComms.sendMessage("waila", "register", "com.animania.addons.extra.compat.ExtraAddonWailaCompat.registerWaila");
	}
}

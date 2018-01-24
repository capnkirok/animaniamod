package com.animania.common.handler;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatHandler
{

	public static void preInit()
	{

		FMLInterModComms.sendMessage("waila", "register", "com.animania.compat.waila.WailaCompat.registerWaila");

		if (Loader.isModLoaded("theoneprobe"))
			FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.animania.compat.top.TOPCompat");

	}

}

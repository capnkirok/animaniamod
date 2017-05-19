package com.animania.common.handler;

import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatHandler
{

	public static void preInit()
	{
		
		FMLInterModComms.sendMessage("waila", "register", "com.animania.compat.waila.WailaCompat.registerWaila");
		
	}
	
	
	
}

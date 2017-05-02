package com.animania.events;

import com.animania.Animania;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigChangeEventHandler {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals("animania")){
			Animania.syncConfig();
			System.out.println("Animania Configuration Updated");
		}
	}
}
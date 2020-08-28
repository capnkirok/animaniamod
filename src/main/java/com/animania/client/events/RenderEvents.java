package com.animania.client.events;

import com.animania.Animania;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@EventBusSubscriber(modid = Animania.MODID)
public class RenderEvents
{
	public static long ticks = 0;

	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent event)
	{
		ticks++;
	}
}

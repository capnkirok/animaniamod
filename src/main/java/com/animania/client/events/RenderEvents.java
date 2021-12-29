package com.animania.client.events;

import com.animania.Animania;
import com.animania.client.render.item.RenderAnimatedEgg;
import com.animania.manual.components.CraftingComponent;
import com.animania.manual.components.EntityComponent;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Animania.MODID)
public class RenderEvents
{
	public static long ticks = 0;

	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent event)
	{
		if (event.side == LogicalSide.CLIENT)
		{
			ticks++;
			RenderAnimatedEgg.renderTimer += 0.004f;
			if (RenderAnimatedEgg.renderTimer > 1f)
				RenderAnimatedEgg.renderTimer = 0;

			CraftingComponent.ITEM_TIMER++;
			if (CraftingComponent.ITEM_TIMER > 20)
				CraftingComponent.ITEM_TIMER = 0;

			if (EntityComponent.renderTimer >= 80)
				EntityComponent.renderTimer = 0;
			EntityComponent.renderTimer++;

		}
	}
}

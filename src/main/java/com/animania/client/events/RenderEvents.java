package com.animania.client.events;

import com.animania.Animania;
import com.animania.client.render.item.RenderAnimatedEgg;
import com.animania.manual.components.CraftingComponent;
import com.animania.manual.components.EntityComponent;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Animania.MODID)
public class RenderEvents
{
	public static long ticks = 0;

	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent event)
	{
		if (event.side == Dist.CLIENT)
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

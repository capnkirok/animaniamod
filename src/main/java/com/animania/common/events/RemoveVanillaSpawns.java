package com.animania.common.events;

import java.util.List;

import com.animania.config.AnimaniaConfig;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveVanillaSpawns {
	@SubscribeEvent(priority = EventPriority.NORMAL)

	public void onEntitySpawn(WorldEvent.PotentialSpawns event) {

		List spawns = event.getList();

		for (int i = event.getList().size() - 1; i >= 0; i--) {

			if (AnimaniaConfig.gameRules.replaceVanillaCows
					&& event.getList().get(i).entityClass.getSimpleName().equals("EntityCow")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaChickens
					&& event.getList().get(i).entityClass.getSimpleName().equals("EntityChicken")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaPigs
					&& event.getList().get(i).entityClass.getSimpleName().equals("EntityPig")) {
				event.getList().remove(i);
			}

		}

	}

}
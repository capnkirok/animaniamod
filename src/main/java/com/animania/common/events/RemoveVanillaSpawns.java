package com.animania.common.events;

import java.util.List;

import com.animania.config.AnimaniaConfig;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveVanillaSpawns
{

	//Removes initial spawns
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEntitySpawn(WorldEvent.PotentialSpawns event) {
		List spawns = event.getList();
		for (int i = event.getList().size() - 1; i >= 0; i--) {
			if (AnimaniaConfig.gameRules.replaceVanillaCows && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntityCow") && !event.getList().get(i).entityClass.getName().contains("com.animania.common.entities.cows.EntityAnimaniaCow")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntityChicken")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntityPig")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaSheep && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntitySheep")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaRabbits && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntityRabbit")) {
				event.getList().remove(i);
			} else if (AnimaniaConfig.gameRules.replaceVanillaHorses && event.getList().get(i).entityClass.getName().contains("net.minecraft.entity.passive.EntityHorse")) {
				event.getList().remove(i);
			}
		}
	}



	/*
	//Removes additional spawns
	@SubscribeEvent
	public void onEntitySpawnEvent(LivingSpawnEvent.CheckSpawn event) {

		if (AnimaniaConfig.gameRules.replaceVanillaCows && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntityCow")) {
			event.setCanceled(true);
		} else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntityChicken")) {
			event.setCanceled(true);
		} else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntityPig")) {
			event.setCanceled(true);
		} else if (AnimaniaConfig.gameRules.replaceVanillaSheep && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntitySheep")) {
			event.setCanceled(true);
		} else if (AnimaniaConfig.gameRules.replaceVanillaRabbits && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntityRabbit")) {
			event.setCanceled(true);
		} else if (AnimaniaConfig.gameRules.replaceVanillaHorses && event.getEntity().getClass().toString().contains("net.minecraft.entity.passive.EntityHorse")) {
			event.setCanceled(true);

		}
	}
	*/


}

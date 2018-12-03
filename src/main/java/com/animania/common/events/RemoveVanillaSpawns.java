package com.animania.common.events;

import java.util.Iterator;

import com.animania.config.AnimaniaConfig;

import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveVanillaSpawns
{

	//Removes initial spawns
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onEntitySpawn(WorldEvent.PotentialSpawns event) {
		for(Iterator<SpawnListEntry> iter = event.getList().iterator(); iter.hasNext(); )
		{
			String className = iter.next().entityClass.getName();
			if (AnimaniaConfig.gameRules.replaceVanillaCows && (className.equals("net.minecraft.entity.passive.EntityCow") || className.contains("net.minecraft.entity.passive.EntityMooshroom")))
				iter.remove();
			else if (AnimaniaConfig.gameRules.replaceVanillaChickens && className.equals("net.minecraft.entity.passive.EntityChicken"))
				iter.remove();
			else if(AnimaniaConfig.gameRules.replaceVanillaPigs && className.equals("net.minecraft.entity.passive.EntityPig"))
				iter.remove();
			else if(AnimaniaConfig.gameRules.replaceVanillaSheep && className.equals("net.minecraft.entity.passive.EntitySheep"))
				iter.remove();
			else if(AnimaniaConfig.gameRules.replaceVanillaRabbits && className.equals("net.minecraft.entity.passive.EntityRabbit"))
				iter.remove();
			else if(AnimaniaConfig.gameRules.replaceVanillaHorses && className.equals("net.minecraft.entity.passive.EntityHorse"))
				iter.remove();
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

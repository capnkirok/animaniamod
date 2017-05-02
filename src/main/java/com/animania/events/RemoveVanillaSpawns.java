package com.animania.events;

import java.util.List;

import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

import com.animania.Animania;
import com.animania.entities.cows.EntityBullFriesian;
import com.animania.entities.cows.EntityCalfFriesian;
import com.animania.entities.cows.EntityCowFriesian;

public class RemoveVanillaSpawns {
	@SubscribeEvent(priority = EventPriority.NORMAL)

	public void onEntitySpawn(WorldEvent.PotentialSpawns event)
	{
		
		List spawns = event.getList();

		for (int i = event.getList().size() - 1; i >= 0; i--) {

			if (Animania.replaceVanillaCows && event.getList().get(i).entityClass.getSimpleName().equals("EntityCow")) {
				event.getList().remove(i);
			} else if (Animania.replaceVanillaChickens && event.getList().get(i).entityClass.getSimpleName().equals("EntityChicken")) {
				event.getList().remove(i);
			} else if (Animania.replaceVanillaPigs && event.getList().get(i).entityClass.getSimpleName().equals("EntityPig")) {
				event.getList().remove(i);
			}

		}


	}


}
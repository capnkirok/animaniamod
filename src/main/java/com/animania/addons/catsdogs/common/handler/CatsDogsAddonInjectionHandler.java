package com.animania.addons.catsdogs.common.handler;

import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityFemaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.DogGermanShepherd.EntityMaleGermanShepherd;
import com.animania.addons.catsdogs.common.entity.canids.ai.AnimalAIGetDogHerded;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.world.entity.PathfinderMob;

public class CatsDogsAddonInjectionHandler
{
	public static String ID = "catsdogs";

	public static void preInit()
	{
		AddonInjectionHandler.addInjection(ID, "addHerdingBehavior", args -> {
			PathfinderMob entity = (PathfinderMob) args[0];
			int priority = (int) args[1];

			entity.tasks.addTask(priority, new AnimalAIGetDogHerded(entity, EntityMaleGermanShepherd.class));
			entity.tasks.addTask(priority, new AnimalAIGetDogHerded(entity, EntityFemaleGermanShepherd.class));

			return null;

		});
	}
}

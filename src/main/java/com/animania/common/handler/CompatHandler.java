package com.animania.common.handler;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class CompatHandler
{
	public static final String THEONEPROBE_ID = "theoneprobe";

	public static void preInit()
	{
		if (ModList.get().isLoaded("waila"))
			FMLInterModComms.sendMessage("waila", "register", "com.animania.compat.waila.WailaCompat.registerWaila");

		if (ModList.get().isLoaded(THEONEPROBE_ID))
			FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.animania.compat.top.TOPCompat");

		if (ModList.get().isLoaded("morph"))
		{

			// Chickens
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>CrowTime>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>>CrowDuration>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>EggLayTime>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>Laid>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>LaidTimer>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.chickens.EntityAnimaniaChicken>Watered>null");

			// Cows
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Pregnant>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.cows.EntityAnimaniaCow>Fighting>null");

			// Goats
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Spooked>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Sheared>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Color>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.goats.EntityAnimaniaGoat>Pregnant>null");

			// Hamsters
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>IsSitting>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>InBall>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>foodStackCount>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>BallColor>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>IsTamed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>IsRiding>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.EntityHamster>Age>null");

			// Horses
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Pregnant>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.horses.EntityAnimaniaHorse>Variant>null");

			// Peacocks
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.peacocks.EntityAnimaniaPeacock>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.peacocks.EntityAnimaniaPeacock>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.peacocks.EntityAnimaniaPeacock>LaidTimer>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.peacocks.EntityAnimaniaPeacock>Laid>null");

			// Pigs
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Muddy>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Played>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Saddle>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>Pregnant>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.pigs.EntityAnimaniaPig>SplashTimer>null");

			// Rabbits
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>Pregnant>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>ColorNumber>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit>RabbitType>null");

			// Sheep
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Fed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Watered>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Handfed>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Spooked>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Sheared>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Color>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Fertile>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Gestation>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>HasKids>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>MateUUID>null");
			FMLInterModComms.sendMessage("morph", "nbt_modifier", "com.animania.common.entities.sheep.EntityAnimaniaSheep>Pregnant>null");

		}

	}

}

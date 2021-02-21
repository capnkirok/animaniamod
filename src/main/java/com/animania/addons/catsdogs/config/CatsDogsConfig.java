package com.animania.addons.catsdogs.config;

import com.animania.Animania;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = Animania.MODID, name = "animania_cats_dogs")
public class CatsDogsConfig
{
	@Config.Name("catsdogs")
	public static CatsDogsConfig.Settings catsdogs = new CatsDogsConfig.Settings();

	public static class Settings
	{
		@Comment("Food that cats can eat")
		public String[] catFood = new String[] { "minecraft:fish" };

		@Comment("Cat Bed Block Preferred")
		public String catBed = "animania:cat_bed_1";

		@Comment("Cat Bed Block Backup")
		public String catBed2 = "animania:cat_bed_2";

		@Comment("Spawn limit for cats")
		public int spawnLimitCats = 20;

		@Comment("Spawn limit for dogs")
		public int spawnLimitDogs = 20;

		@Comment("Spawn probability for cats")
		public int spawnProbabilityCats = 4;

		@Comment("Spawn probability for dogs")
		public int spawnProbabilityDogs = 5;

		@Comment("Number of potential Dog families per chunk")
		public int numberDogFamilies = 2;

		@Comment("Number of potential Cat families per chunk")
		public int numberCatFamilies = 2;

		@Comment("Food that dogs can eat")
		public String[] dogFood = new String[] { "listAllbeefraw" };

		@Comment("Items that can go in a pet bowl")
		public String[] petBowlFood = new String[] { "minecraft:fish", "listAllbeefraw", "animania:hamster_food" };

		@Comment("Dog Bed Block Preferred")
		public String dogBed = "animania:dog_pillow";

		@Comment("Dog Bed Block Backup")
		public String dogBed2 = "animania:block_straw";

		@Comment("BiomeDictionary types for spawning Wolves")
		public String[] wolfBiomeTypes = new String[] { "MOUNTAIN", "FOREST", "SNOWY", "COLD" };

		@Comment("BiomeDictionary types for spawning Foxes")
		public String[] foxBiomeTypes = new String[] { "FOREST", "SNOWY", "COLD" };

		@Comment("BiomeDictionary types for spawning Ocelots")
		public String[] ocelotBiomeTypes = new String[] { "HOT", "JUNGLE", "SAVANNA" };

		@Comment("Whether vanilla wolves are replaced by Animania")
		public boolean replaceVanillaWolves = true;

		@Comment("Whether vanilla ocelots are replaced by Animania")
		public boolean replaceVanillaOcelots = true;
	}

}

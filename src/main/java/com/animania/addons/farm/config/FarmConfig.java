package com.animania.addons.farm.config;

import com.animania.Animania;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = Animania.MODID, name = "animania_farm")
public class FarmConfig
{
	@Config.Name("farm")
	public static FarmConfig.Settings settings = new FarmConfig.Settings();

	public static class Settings
	{
		@Comment("Spawning and Breeding")
		public SpawningAndBreeding spawning_and_breeding = new SpawningAndBreeding();

		@Comment("Allow eggs to be thrown")
		public boolean allowEggThrowing = false;

		@Comment("Ticks that a Cheese takes to mature")
		public int cheeseMaturityTime = 24000;

		@Comment("Female Cows Milkable at Spawn")
		public boolean cowsMilkableAtSpawn = false;

		@Comment("Can sleep using Wagon")
		public boolean sleepAllowedWagon = true;

		@Comment("Disable salt creation using cheese mold")
		public boolean disableSaltCreation = false;

		@Comment("Disable cart and wagon")
		public boolean disableRollingVehicles = false;

		@Comment("If chickens need to lay eggs in a nest or not")
		public boolean chickensDropEggs = false;

		@Comment("Allow Beehives during world gen")
		public boolean hiveSpawning = true;

		@Comment("Frequency of Beehives (1-10)")
		public int hiveSpawningFrequency = 3;

		@Comment("Honey creation rate for wild hives")
		public int hiveWildHoneyRate = 700;

		@Comment("Honey creation rate for playermade hives")
		public int hivePlayermadeHoneyRate = 450;

		@Comment("Valid Biome Types for hive. Types can be seen here:\nhttps://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/src/main/java/net/minecraftforge/common/BiomeDictionary.java")
		public String[] hiveValidBiomeTypes = new String[] { "JUNGLE", "CONIFEROUS", "SWAMP", "FOREST", "PLAINS", };

		@Comment("Food Items that chickens can eat (use # for meta)")
		public String[] chickenFood = { "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem" };

		@Comment("Food Items that cows can eat (use # for meta)")
		public String[] cowFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem" };

		@Comment("Food Items that goats can eat (use # for meta)")
		public String[] goatFood = { "minecraft:wheat", "minecraft:string", "minecraft:stick", "minecraft:apple", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem" };

		@Comment("Food Items that horses can eat (use # for meta)")
		public String[] horseFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "minecraft:apple", "minecraft:carrot" };

		@Comment("Food Items that sheep can eat (use # for meta)")
		public String[] sheepFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem" };

		@Comment("Food Items that pigs can eat (use # for meta)")
		public String[] pigFood = { "minecraft:carrot", "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:bread" };

		@Comment("Chicken Bed Block Preferred")
		public String chickenBed = "animania:block_straw";

		@Comment("Chicken Bed Block Backup")
		public String chickenBed2 = "minecraft:grass";

		@Comment("Cow Bed Block Preferred")
		public String cowBed = "animania:block_straw";

		@Comment("Cow Bed Block Backup")
		public String cowBed2 = "minecraft:grass";

		@Comment("Goat Bed Block Preferred")
		public String goatBed = "animania:block_straw";

		@Comment("Goat Bed Block Backup")
		public String goatBed2 = "minecraft:grass";

		@Comment("Horse Bed Block Preferred")
		public String horseBed = "animania:block_straw";

		@Comment("Horse Bed Block Backup")
		public String horseBed2 = "minecraft:grass";

		@Comment("Pig Bed Block Preferred")
		public String pigBed = "animania:block_straw";

		@Comment("Pig Bed Block Backup")
		public String pigBed2 = "minecraft:grass";

		@Comment("Sheep Bed Block Preferred")
		public String sheepBed = "animania:block_straw";

		@Comment("Sheep Bed Block Backup")
		public String sheepBed2 = "minecraft:grass";

		@Comment("Whether roosters attack other roosters to defend their territory")
		public boolean roostersFight = false;
	}

	public static class SpawningAndBreeding
	{

		@Comment("Remove vanilla Cows")
		public boolean replaceVanillaCows = true;

		@Comment("Remove vanilla Pigs")
		public boolean replaceVanillaPigs = true;

		@Comment("Remove vanilla Chickens")
		public boolean replaceVanillaChickens = true;

		@Comment("Remove vanilla Sheep")
		public boolean replaceVanillaSheep = true;

		@Comment("Remove vanilla Horses")
		public boolean replaceVanillaHorses = false;

		@Comment("Allow Squids to Spawn in Fresh Water")
		public boolean spawnFreshWaterSquids = true;

		@Comment("Spawn Animania Chickens in world")
		public boolean spawnAnimaniaChickens = true;

		@Comment("Spawn Animania Cows in world")
		public boolean spawnAnimaniaCows = true;

		@Comment("Spawn Animania Pigs in world")
		public boolean spawnAnimaniaPigs = true;

		@Comment("Spawn Animania Horses in world")
		public boolean spawnAnimaniaHorses = true;

		@Comment("Spawn Animania Goats in world")
		public boolean spawnAnimaniaGoats = true;

		@Comment("Spawn Animania Sheep in world")
		public boolean spawnAnimaniaSheep = true;

		@Comment("Spawn probability Cows in loaded chunks")
		public int spawnProbabilityCows = 9;

		@Comment("Spawn probability Horses in loaded chunks")
		public int spawnProbabilityHorses = 8;

		@Comment("Spawn probability Pigs in loaded chunks")
		public int spawnProbabilityPigs = 9;

		@Comment("Spawn probability Chickens in loaded chunks")
		public int spawnProbabilityChickens = 9;

		@Comment("Spawn probability Goats in loaded chunks")
		public int spawnProbabilityGoats = 8;

		@Comment("Spawn probability Sheep in loaded chunks")
		public int spawnProbabilitySheep = 8;

		@Comment("Number of potential Cow families per chunk")
		public int numberCowFamilies = 2;

		@Comment("Number of potential Pig families per chunk")
		public int numberPigFamilies = 2;

		@Comment("Number of potential Chicken families per chunk")
		public int numberChickenFamilies = 2;

		@Comment("Number of potential Horse families per chunk")
		public int numberHorseFamilies = 2;

		@Comment("Number of potential Goat families per chunk")
		public int numberGoatFamilies = 1;

		@Comment("Number of potential Sheep families per chunk")
		public int numberSheepFamilies = 3;

		@Comment("Spawn limit for Cows in loaded chunks")
		public int spawnLimitCows = 40;

		@Comment("Spawn limit for Pigs in loaded chunks")
		public int spawnLimitPigs = 40;

		@Comment("Spawn limit for Chickens in loaded chunks")
		public int spawnLimitChickens = 40;

		@Comment("Spawn limit for Horses in loaded chunks")
		public int spawnLimitHorses = 40;

		@Comment("Spawn limit for Goats in loaded chunks")
		public int spawnLimitGoats = 40;

		@Comment("Spawn limit for Sheep in loaded chunks")
		public int spawnLimitSheep = 40;

		// CHICKENS
		@Comment("BiomeDictionary types for spawning Plymouth Rock Chickens")
		public String[] chickenPlymouthRockBiomeTypes = new String[] { "MOUNTAIN", };
		@Comment("BiomeDictionary types for spawning Leghorn Chickens")
		public String[] chickenLeghornBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Orpington Chickens")
		public String[] chickenOrpingtonBiomeTypes = new String[] { "JUNGLE", "SWAMP", };
		@Comment("BiomeDictionary types for spawning Wyandotte Chickens")
		public String[] chickenWyandotteBiomeTypes = new String[] { "FOREST", };
		@Comment("BiomeDictionary types for spawning Rhode Island Red Chickens")
		public String[] chickenRhodeIslandRedBiomeTypes = new String[] { "FOREST", };

		// COWS
		@Comment("BiomeDictionary types for spawning Holstein Cows")
		public String[] cowHolsteinBiomeTypes = new String[] { "FOREST", };
		@Comment("BiomeDictionary types for spawning Friesian Cows")
		public String[] cowFriesianBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Angus Cows")
		public String[] cowAngusBiomeTypes = new String[] { "JUNGLE", "MESA", "SWAMP", };
		@Comment("BiomeDictionary types for spawning Hereford Cows")
		public String[] cowHerefordBiomeTypes = new String[] { "MOUNTAIN", "HILLS", };
		@Comment("BiomeDictionary types for spawning Highland Cows")
		public String[] cowHighlandBiomeTypes = new String[] { "MOUNTAIN", "HILLS" };
		@Comment("BiomeDictionary types for spawning Jersey Cows")
		public String[] cowJerseyBiomeTypes = new String[] { "WASTELAND", "SWAMP", };
		@Comment("BiomeDictionary types for spawning Longhorn Cows")
		public String[] cowLonghornBiomeTypes = new String[] { "SAVANNA", };
		@Comment("BiomeDictionary types for spawning Mooshroom Cows")
		public String[] cowMooshroomBiomeTypes = new String[] { "MUSHROOM", "MAGICAL" };

		// HORSES
		@Comment("BiomeDictionary types for spawning Draft Horses")
		public String[] draftHorseBiomeTypes = new String[] { "PLAINS", "SAVANNA", "MESA", };

		// PIGS
		@Comment("BiomeDictionary types for spawning Yorkshire Pigs")
		public String[] pigYorkshireBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Old Spot Pigs")
		public String[] pigOldSpotBiomeTypes = new String[] { "FOREST", };
		@Comment("BiomeDictionary types for spawning Large Black Pigs")
		public String[] pigLargeBlackBiomeTypes = new String[] { "SWAMP", "DENSE", };
		@Comment("BiomeDictionary types for spawning Large White Pigs")
		public String[] pigLargeWhiteBiomeTypes = new String[] { "FOREST", };
		@Comment("BiomeDictionary types for spawning Duroc Pigs")
		public String[] pigDurocBiomeTypes = new String[] { "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Hampshire Pigs")
		public String[] pigHampshireBiomeTypes = new String[] { "MOUNTAIN", "HILLS", };

		// GOATS
		@Comment("BiomeDictionary types for spawning Alpine Goats")
		public String[] goatAlpineBiomeTypes = new String[] { "MOUNTAIN", "HILLS", };
		@Comment("BiomeDictionary types for spawning Angora Goats")
		public String[] goatAngoraBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Fainting Goats")
		public String[] goatFaintingBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Kiko Goats")
		public String[] goatKikoBiomeTypes = new String[] { "MOUNTAIN", "HILLS", };
		@Comment("BiomeDictionary types for spawning Kinder Goats")
		public String[] goatKinderBiomeTypes = new String[] { "SAVANNA", "MESA", };
		@Comment("BiomeDictionary types for spawning Nigerian Dwarf Goats")
		public String[] goatNigerianDwarfBiomeTypes = new String[] { "SANDY", };
		@Comment("BiomeDictionary types for spawning Pygmy Goats")
		public String[] goatPygmyBiomeTypes = new String[] { "SAVANNA", "MESA", };

		// SHEEP
		@Comment("BiomeDictionary types for spawning Dorset Sheep")
		public String[] sheepDorsetBiomeTypes = new String[] { "HILLS", };
		@Comment("BiomeDictionary types for spawning Friesian Sheep")
		public String[] sheepFriesianBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Jacob Sheep")
		public String[] sheepJacobBiomeTypes = new String[] { "FOREST", };
		@Comment("BiomeDictionary types for spawning Merino Sheep")
		public String[] sheepMerinoBiomeTypes = new String[] { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Suffolk Sheep")
		public String[] sheepSuffolkBiomeTypes = new String[] { "SAVANNA", "MESA", };
		@Comment("BiomeDictionary types for spawning Dorper Sheep")
		public String[] sheepDorperBiomeTypes = new String[] { "SAVANNA", };

	}

}

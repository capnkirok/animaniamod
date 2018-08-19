package com.animania.config;

import net.minecraftforge.common.config.Config.Comment;

public class CommonConfig
{


	public static class GameRules
	{

		@Comment("Foods give bonus effects")
		public boolean foodsGiveBonusEffects = true;

		@Comment("Show mod update notification at startup")
		public boolean showModUpdateNotification = true;

		@Comment("Show male parts (modesty flag)")
		public boolean showParts = true;

		@Comment("Show particles when animals are unhappy")
		public boolean showUnhappyParticles = true;

		@Comment("Remove vanilla Cows")
		public boolean replaceVanillaCows = true;

		@Comment("Remove vanilla Pigs")
		public boolean replaceVanillaPigs = true;

		@Comment("Remove vanilla Chickens")
		public boolean replaceVanillaChickens     = true;
		
		@Comment("Remove vanilla Rabbits")
		public boolean replaceVanillaRabbits     = true;
		
		@Comment("Remove vanilla Sheep")
		public boolean replaceVanillaSheep     = true;
		
		@Comment("Remove vanilla Horses")
		public boolean replaceVanillaHorses     = false;

		@Comment("Allow eggs to be thrown")
		public boolean allowEggThrowing = false;

		@Comment("Allow dispensers to place seeds")
		public boolean allowSeedDispenserPlacement = true;

		@Comment("Shift-Right-Click for Seed Placement")
		public boolean shiftSeedPlacement = false;

		@Comment("Animals starve to death when not fed and watered")
		public boolean animalsStarve = false;

		@Comment("Allow random mobs to spawn on Animania animals")
		public boolean allowMobRiding = true;

		@Comment("Allow the trough to be automated with hoppers/pipes")
		public boolean allowTroughAutomation = true;

		@Comment("Multiplier for reducing fall damage when animals are leashed")
		public float fallDamageReduceMultiplier = 0.45f;

		@Comment("Capacity of RF that the Hamster Wheel has")
		public int hamsterWheelCapacity = 200000;

		@Comment("RF/tick that the Hamster wheel generates while in use")
		public int hamsterWheelRFGeneration = 20;

		@Comment("Ticks that the hamster stays in the wheel before it needs more food")
		public int hamsterWheelUseTime = 2000;

		@Comment("Ticks that a Cheese takes to mature")
		public int cheeseMaturityTime = 24000;
		
		@Comment("Female Cows Milkable at Spawn")
		public boolean cowsMilkableAtSpawn = false;
		
		@Comment("Water blocks removed after large animals drink")
		public boolean waterRemovedAfterDrinking = true;
		
		@Comment("Plant blocks removed after animal eats")
		public boolean plantsRemovedAfterEating = true;
		
		@Comment("Animals do not have to eat or drink")
		public boolean ambianceMode = false;
		
		@Comment("Animals sleep")
		public boolean animalsSleep = true;
		
		@Comment("Can sleep using Wagon")
		public boolean sleepAllowedWagon = true;

		@Comment("Animals can attack others")
		public boolean animalsCanAttackOthers = true;
		
		@Comment("Disable salt creation using cheese mold")
		public boolean disableSaltCreation = false;
		
		@Comment("Disable cart and wagon")
		public boolean disableRollingVehicles = false;
		
		@Comment("Food Items that can be placed in the trough (use # for meta)")
		public String[] troughFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem", "minecraft:apple", "minecraft:carrot" , "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "biomesoplenty:turnip_seeds", "minecraft:apple", "minecraft:egg", "animania:brown_egg"};
		
		@Comment("AI Tick Countdown Timer (increase for higher performance)")
		public int ticksBetweenAIFirings = 100;
		
		@Comment("Tamed animals teleport to player)")
		public boolean tamedAnimalsTeleport = false;
	
		@Comment("Fancy entity spawn eggs")
		public boolean fancyEggs = true;
		
		@Comment("Allow Beehives during world gen")
		public boolean beehiveSpawning = true;
		
		@Comment("Frequency of Beehives (1-10)")
		public int beehiveSpawningFrequency = 5;
		
		public boolean fancyEggs = false;
		
		@Comment("Honey creation rate for wild hives")
		public int hiveWildHoneyRate = 450;
		
		@Comment("Honey creation rate for playermade hives")
		public int hivePlayermadeHoneyRate = 250;
		
		@Comment("Valid Biome Types for hive. Types can be seen here:\nhttps://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/src/main/java/net/minecraftforge/common/BiomeDictionary.java")
		public String[] hiveValidBiomeTypes = new String[]
		{
				"JUNGLE",
				"CONIFEROUS",
				"LUSH",
				"FOREST",
				"PLAINS",
		};
	}

	public static class Spawn
	{
		@Comment("Spawn Animania Chickens in world")
		public boolean spawnAnimaniaChickens = true;
		@Comment("Spawn Animania Cows in world")
		public boolean spawnAnimaniaCows = true;
		@Comment("Spawn Animania Pigs in world")
		public boolean spawnAnimaniaPigs = true;
		@Comment("Spawn Animania Rodents in world")
		public boolean spawnAnimaniaRodents = true;
		@Comment("Spawn Animania Peacocks in world")
		public boolean spawnAnimaniaPeacocks = true;
		@Comment("Spawn Animania Horses in world")
		public boolean spawnAnimaniaHorses = true;
		@Comment("Spawn Animania Amphibians in world")
		public boolean spawnAnimaniaAmphibians = true;
		@Comment("Spawn Animania Goats in world")
		public boolean spawnAnimaniaGoats = true;
		@Comment("Spawn Animania Sheep in world")
		public boolean spawnAnimaniaSheep    = true;
		@Comment("Spawn Animania Rabbits in world")
		public boolean spawnAnimaniaRabbits    = true;
		@Comment("Allow Squids tp Spawn in Fresh Water")
		public boolean spawnFreshWaterSquids    = false;
		
		@Comment("Spawn probability Cows in loaded chunks")
		public int spawnProbabilityCows = 8;
		@Comment("Spawn probability Horses in loaded chunks")
		public int spawnProbabilityHorses = 6;
		@Comment("Spawn probability Pigs in loaded chunks")
		public int spawnProbabilityPigs = 8;
		@Comment("Spawn probability Chickens in loaded chunks")
		public int spawnProbabilityChickens = 8;
		@Comment("Spawn probability Hedgehogs in loaded chunks")
		public int spawnProbabilityHedgehogs = 8;
		@Comment("Spawn probability Ferrets in loaded chunks")
		public int spawnProbabilityFerrets = 8;
		@Comment("Spawn probability Hamsters in loaded chunks")
		public int spawnProbabilityHamsters = 8;
		@Comment("Spawn probability Peacocks in loaded chunks")
		public int spawnProbabilityPeacocks = 8;
		@Comment("Spawn probability Amphibians in loaded chunks")
		public int spawnProbabilityAmphibians = 6;
		@Comment("Spawn probability Goats in loaded chunks")
		public int spawnProbabilityGoats = 6;
		@Comment("Spawn probability Sheep in loaded chunks")
		public int spawnProbabilitySheep = 8;
		@Comment("Spawn probability Rabbits in loaded chunks")
		public int spawnProbabilityRabbits = 6;
	
		@Comment("Number of potential Cow families per chunk")
		public int numberCowFamilies = 1;
		@Comment("Number of potential Pig families per chunk")
		public int numberPigFamilies = 1;
		@Comment("Number of potential Chicken families per chunk")
		public int numberChickenFamilies = 1;
		@Comment("Number of potential Horse families per chunk")
		public int numberHorseFamilies = 1;
		@Comment("Number of potential Goat families per chunk")
		public int numberGoatFamilies = 1;
		@Comment("Number of potential Sheep families per chunk")
		public int numberSheepFamilies = 1;
		@Comment("Number of potential Rabbit families per chunk")
		public int numberRabbitFamilies = 1;
				
		@Comment("Spawn limit for Cows in loaded chunks")
		public int spawnLimitCows = 40;
		@Comment("Spawn limit for Pigs in loaded chunks")
		public int spawnLimitPigs = 40;
		@Comment("Spawn limit for Chickens in loaded chunks")
		public int spawnLimitChickens = 40;
		@Comment("Spawn limit for Hedgehogs in loaded chunks")
		public int spawnLimitHedgehogs = 40;
		@Comment("Spawn limit for Ferrets in loaded chunks")
		public int spawnLimitFerrets = 40;
		@Comment("Spawn limit for Hamsters in loaded chunks")
		public int spawnLimitHamsters = 40;
		@Comment("Spawn limit for Peacocks in loaded chunks")
		public int spawnLimitPeacocks = 40;
		@Comment("Spawn limit for Amphibians in loaded chunks")
		public int spawnLimitAmphibians = 40;
		@Comment("Spawn limit for Horses in loaded chunks")
		public int spawnLimitHorses = 40;
		@Comment("Spawn limit for Goats in loaded chunks")
		public int spawnLimitGoats = 40;
		@Comment("Spawn limit for Sheep in loaded chunks")
		public int spawnLimitSheep = 40;
		@Comment("Spawn limit for Rabbits in loaded chunks")
		public int spawnLimitRabbits = 40;
		
		
	}

	public static class CareAndFeeding
	{
		@Comment("Ticks before next incremental growth")
		public int childGrowthTick = 200;
		@Comment("Ticks between feedings")
		public int feedTimer = 12000;
		@Comment("Ticks between drinking water")
		public int waterTimer = 8000;
		@Comment("Ticks between playing")
		public int playTimer = 12000;
		@Comment("Ticks between laying eggs")
		public int laidTimer = 2000;
		@Comment("Ticks between dropping feathers")
		public int featherTimer = 12000;
		@Comment("Ticks between birthings")
		public int gestationTimer = 20000;
		@Comment("Mammals mate/breed only after hand-feeding")
		public boolean manualBreeding = false;
		@Comment("Ticks before wool regrowth after shearing")
		public int woolRegrowthTimer = 8000;
		@Comment("Ticks between animals taking starvation damage")
		public int starvationTimer = 400;
		@Comment("Egg hatch chance (1/x)")
		public int eggHatchChance = 2;
		@Comment("Ticks between using Salt Lick")
		public int saltLickTick = 8000;
		@Comment("Maximum uses of the salt lick")
		public int saltLickMaxUses = 200;
		
		@Comment("Food Items that chickens can eat (use # for meta)")
		public String[] chickenFood = { "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem"  };
		
		@Comment("Food Items that cows can eat (use # for meta)")
		public String[] cowFood = { "minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem"  };
	
		@Comment("Food Items that ferrets can eat (use # for meta)")
		public String[] ferretFood = { "minecraft:mutton", "minecraft:egg", "animania:brown_egg", "animania:peacock_egg_blue", "animania:peacock_egg_white", "animania:prime_mutton", "animania:prime_rabbit", "minecraft:rabbit", "minecraft:chicken", "animania_prime_chicken"};
		
		@Comment("Food Items that goats can eat (use # for meta)")
		public String[] goatFood = { "minecraft:wheat", "minecraft:string", "minecraft:stick", "minecraft:apple", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem"  };
	
		@Comment("Food Items that hamsters can eat (use # for meta)")
		public String[] hamsterFood = { "animania:hamster_food", "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem", "minecraft:apple"};
		
		@Comment("Food Items that hedgehogs can eat (use # for meta)")
		public String[] hedgehogFood = { "minecraft:carrot", "minecraft:beetroot", "minecraft:egg", "animania:brown_egg", "animania:peacock_egg_blue", "animania:peacock_egg_white", "animania:prime_mutton", "animania:prime_rabbit", "minecraft:rabbit", "minecraft:chicken", "animania_prime_chicken", "minecraft:apple"};
		
		@Comment("Food Items that horses can eat (use # for meta)")
		public String[] horseFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "minecraft:apple", "minecraft:carrot" };
	
		@Comment("Food Items that peacocks can eat (use # for meta)")
		public String[] peacockFood = { "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem"  };
		
		@Comment("Food Items that pigs can eat (use # for meta)")
		public String[] pigFood = { "minecraft:carrot", "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:bread"};
	
		@Comment("Food Items that sheep can eat (use # for meta)")
		public String[] sheepFood = { "minecraft:wheat", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem"};
		
		@Comment("Food Items that rabbits can eat (use # for meta)")
		public String[] rabbitFood = { "minecraft:wheat", "minecraft:carrot", "minecraft:beetroot", "minecraft:apple"};
		
		@Comment("Chicken Bed Block Preferred")
		public String chickenBed = "animania:block_straw";
		
		@Comment("Chicken Bed Block Backup")
		public String chickenBed2 = "minecraft:grass";
		
		@Comment("Cow Bed Block Preferred")
		public String cowBed = "animania:block_straw";
		
		@Comment("Cow Bed Block Backup")
		public String cowBed2 = "minecraft:grass";
		
		@Comment("Ferret Bed Block Preferred")
		public String ferretBed = "animania:block_straw";
		
		@Comment("Ferret Bed Block Backup")
		public String ferretBed2 = "minecraft:grass";
		
		@Comment("Goat Bed Block Preferred")
		public String goatBed = "animania:block_straw";
		
		@Comment("Goat Bed Block Backup")
		public String goatBed2 = "minecraft:grass";
		
		@Comment("Hamster Bed Block Preferred")
		public String hamsterBed = "animania:block_straw";
		
		@Comment("Hamster Bed Block Backup")
		public String hamsterBed2 = "";
		
		@Comment("Hedgehog Bed Block Preferred")
		public String hedgehogBed = "animania:block_straw";
		
		@Comment("Hedgehog Bed Block Backup")
		public String hedgehogBed2 = "minecraft:grass";
		
		@Comment("Horse Bed Block Preferred")
		public String horseBed = "animania:block_straw";
		
		@Comment("Horse Bed Block Backup")
		public String horseBed2 = "minecraft:grass";
		
		@Comment("Peacock Bed Block Preferred")
		public String peacockBed = "animania:block_straw";
		
		@Comment("Peacock Bed Block Backup")
		public String peacockBed2 = "minecraft:grass";
		
		@Comment("Pig Bed Block Preferred")
		public String pigBed = "animania:block_straw";
		
		@Comment("Pig Bed Block Backup")
		public String pigBed2 = "minecraft:grass";
		
		@Comment("Rabbit Bed Block Preferred")
		public String rabbitBed = "animania:block_straw";
		
		@Comment("Rabbit Bed Block Backup")
		public String rabbitBed2 = "minecraft:grass";
		
		@Comment("Sheep Bed Block Preferred")
		public String sheepBed = "animania:block_straw";
		
		@Comment("Sheep Bed Block Backup")
		public String sheepBed2 = "minecraft:grass";
		
	}

	public static class Drops
	{
		@Comment("Enable Animal Drops from Config")
		public boolean customMobDrops = true;
		@Comment("Enable Animals Drop their custom meat types")
		public boolean oldMeatDrops = false;
		@Comment("Set Custom Chicken Drop (if enabled)")
		public String chickenDrop = "animania:raw_prime_chicken";
		@Comment("Set Custom Chicken Secondary Drop")
		public String chickenDrop2 = "minecraft:feather";
		@Comment("Set Custom Chicken Secondary Drop Amount")
		public int chickenDrop2Amount = 1;
		@Comment("Set Custom Pig Drop (if enabled)")
		public String pigDrop = "animania:raw_prime_pork";
		@Comment("Set Custom Pig Secondary Drop")
		public String pigDrop2 = "";
		@Comment("Set Custom Pig Secondary Drop Amount")
		public int pigDrop2Amount = 1;
		@Comment("Set Custom Cow Drop (if enabled)")
		public String cowDrop = "animania:raw_prime_beef";
		@Comment("Set Custom Cow Secondary Drop")
		public String cowDrop2 = "minecraft:leather";
		@Comment("Set Custom Cow Secondary Drop Amount")
		public int cowDrop2Amount = 1;
		@Comment("Set Custom  Mooshroom Drop (if enabled)")
		public String mooshroomDrop = "animania:raw_prime_beef";
		@Comment("Set Custom Mooshroom Secondary Drop")
		public String mooshroomDrop2 = "minecraft:leather";
		@Comment("Set Custom Mooshroom Secondary Drop Amount")
		public int mooshroomDrop2Amount = 1;
		@Comment("Set Custom Horse Drop (if enabled)")
		public String horseDrop = "";
		@Comment("Set Custom Horse Secondary Drop")
		public String horseDrop2 = "minecraft:leather";
		@Comment("Set Custom Horse Secondary Drop Amount")
		public int horseDrop2Amount = 1;
		@Comment("Set Custom Blue Peacock Drop (if enabled)")
		public String peacockBlueDrop = "animania:blue_peacock_feather";
		@Comment("Set Custom Charcoal Peacock Drop (if enabled)")
		public String peacockCharcoalDrop = "animania:charcoal_peacock_feather";
		@Comment("Set Custom Opal Peacock Drop (if enabled)")
		public String peacockOpalDrop = "animania:opal_peacock_feather";
		@Comment("Set Custom Peach Peacock Drop (if enabled)")
		public String peacockPeachDrop = "animania:peach_peacock_feather";
		@Comment("Set Custom Purple Peacock Drop (if enabled)")
		public String peacockPurpleDrop = "animania:purple_peacock_feather";
		@Comment("Set Custom Taupe Peacock Drop (if enabled)")
		public String peacockTaupeDrop = "animania:taupe_peacock_feather";
		@Comment("Set Custom White Peacock Drop (if enabled)")
		public String peacockWhiteDrop = "animania:white_peacock_feather";
		@Comment("Set Custom Peacock Secondary Drop")
		public String peacockDrop2 = "animania:raw_peacock";
		@Comment("Set Custom Peacock Secondary Drop Amount")
		public int peacockDrop2Amount = 1;
		
		@Comment("Set Custom Ferret Drop (if enabled)")
		public String ferretDrop = "";
		@Comment("Set Custom Ferret Secondary Drop")
		public String ferretDrop2 = "";
		@Comment("Set Custom Ferret Secondary Drop Amount")
		public int ferretDrop2Amount = 1;
		
		@Comment("Set Custom Hamster Drop (if enabled)")
		public String hamsterDrop = "animania:hamster_food";
		@Comment("Set Custom Hamster Secondary Drop")
		public String hamsterDrop2 = "";
		@Comment("Set Custom Hamster Secondary Drop Amount")
		public int hamsterDrop2Amount = 1;
		
		@Comment("Set Custom Hedgehog Drop (if enabled)")
		public String hedgehogDrop = "";
		@Comment("Set Custom Hedgehog Secondary Drop")
		public String hedgehogDrop2 = "";
		@Comment("Set Custom Hedgehog Secondary Drop Amount")
		public int hedgehogDrop2Amount = 1;
		
		@Comment("Set Custom Frog Drop")
		public String frogDrop = "animania:raw_frog_legs";
		@Comment("Set Custom Frog Secondary Drop")
		public String frogDrop2 = "";
		@Comment("Set Custom frog Secondary Drop Amount")
		public int frogDrop2Amount = 1;
		
		@Comment("Set Custom Toad Drop")
		public String toadDrop = "";
		@Comment("Set Custom Toad Secondary Drop")
		public String toadDrop2 = "";
		@Comment("Set Custom Toad Secondary Drop Amount")
		public int toadDrop2Amount = 1;
		
		@Comment("Set Custom Dart Frog Drop")
		public String dartFrogDrop = "";
		@Comment("Set Custom Dart Frog Secondary Drop")
		public String dartFrogDrop2 = "";
		@Comment("Set Custom Dart Frog Secondary Drop Amount")
		public int dartFrogDrop2Amount = 1;
		
		@Comment("Set Custom Goat Drop")
		public String goatDrop = "animania:raw_chevon";
		@Comment("Set Custom Goat Secondary Drop")
		public String goatDrop2 = "";
		@Comment("Set Custom Goat Secondary Drop Amount")
		public int goatDrop2Amount = 1;
		
		@Comment("Set Custom Sheep Drop")
		public String sheepDrop = "animania:raw_prime_mutton";
		@Comment("Set Custom Sheep Secondary Drop")
		public String sheepDrop2 = "";
		@Comment("Set Custom Sheep Secondary Drop Amount")
		public int sheepDrop2Amount = 1;
		
		@Comment("Set Custom Rabbit Drop")
		public String  rabbitDrop = "animania:raw_prime_rabbit";
		@Comment("Set Custom Rabbit Secondary Drop")
		public String rabbitDrop2 = "minecraft:rabbit_foot";
		@Comment("Set Custom Rabbit Secondary Drop Amount")
		public int rabbitDrop2Amount = 1;
		public String rabbitDrop3 = "minecraft:rabbit_hide";
		@Comment("Set Custom Rabbit Third Drop Amount")
		public int rabbitDrop3Amount = 1;
		
		
		@Comment("Allow Animania Chickens to drop Eggs")
		public Boolean chickensDropEggs = false;
		@Comment("Allow Animania Chickens to drop Feathers")
		public Boolean chickensDropFeathers = true;
	}

	public static class FoodValues
	{
		@Comment("Food Value Overrides")
		public String[] foodValueOverrides = new String[] {};
	}

}

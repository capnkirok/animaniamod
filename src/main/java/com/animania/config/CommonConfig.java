package com.animania.config;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

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

		@Comment("Show particles when animals are hungry or thirsty")
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
		public String[] troughFood = {"minecraft:wheat", "simplecorn:corncob", "harvestcraft:barleyitem", "harvestcraft:oatsitem", "harvestcraft:ryeitem", "harvestcraft:cornitem", "minecraft:apple", "minecraft:carrot" , "minecraft:beetroot", "minecraft:potato", "minecraft:poisonous_potato", "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "biomesoplenty:turnip_seeds", "minecraft:egg", "animania:brown_egg", "listAllbeefraw", "minecraft:fish"};

		@Comment("AI Tick Countdown Timer (increase for higher performance)")
		public int ticksBetweenAIFirings = 100;

		@Comment("Tamed animals teleport to player)")
		public boolean tamedAnimalsTeleport = true;

		@RequiresMcRestart
		@Comment("Fancy entity spawn eggs")
		public boolean fancyEggs = false;

		@Comment("If fancy entity eggs rotate or not")
		public boolean fancyEggsRotate = false;

		@Comment("Allow Beehives during world gen")
		public boolean hiveSpawning = true;

		@Comment("Frequency of Beehives (1-10)")
		public int hiveSpawningFrequency = 3;

		@Comment("Honey creation rate for wild hives")
		public int hiveWildHoneyRate = 700;

		@Comment("Honey creation rate for playermade hives")
		public int hivePlayermadeHoneyRate = 450;

		@Comment("Valid Biome Types for hive. Types can be seen here:\nhttps://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/src/main/java/net/minecraftforge/common/BiomeDictionary.java")
		public String[] hiveValidBiomeTypes = new String[]
				{
						"JUNGLE",
						"CONIFEROUS",
						"SWAMP",
						"FOREST",
						"PLAINS",
				};

		@RequiresMcRestart
		@Comment("Eat animania food anytime")
		public boolean eatFoodAnytime = true;
		
		@Comment("If chickens and other birds shed their feathers naturally")
		public boolean chickensDropFeathers = true;
		
		@Comment("If chickens need to lay eggs in a nest or not")
		public boolean chickensDropEggs = false;
		
		@Comment("Range that Animals search blocks (like troughs or food blocks) in")
		public int aiBlockSearchRange = 16;
		
		@Comment("Range that the animal cap applies for around each animal")
		public int animalCapSearchRange = 80;
		
		@Comment("Animals that are spawned in the world and have never been interacted with (put on a leash, fed by hand, trough, lured with food) will not eat or breed.")
		public boolean requireAnimalInteractionForAI = true;
		
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
		@Comment("Allow Squids to Spawn in Fresh Water")
		public boolean spawnFreshWaterSquids    = true;

		@Comment("Spawn probability Cows in loaded chunks")
		public int spawnProbabilityCows = 9;
		@Comment("Spawn probability Horses in loaded chunks")
		public int spawnProbabilityHorses = 8;
		@Comment("Spawn probability Pigs in loaded chunks")
		public int spawnProbabilityPigs = 9;
		@Comment("Spawn probability Chickens in loaded chunks")
		public int spawnProbabilityChickens = 9;
		@Comment("Spawn probability Hedgehogs in loaded chunks")
		public int spawnProbabilityHedgehogs = 8;
		@Comment("Spawn probability Ferrets in loaded chunks")
		public int spawnProbabilityFerrets = 8;
		@Comment("Spawn probability Hamsters in loaded chunks")
		public int spawnProbabilityHamsters = 8;
		@Comment("Spawn probability Peacocks in loaded chunks")
		public int spawnProbabilityPeacocks = 8;
		@Comment("Spawn probability Amphibians in loaded chunks")
		public int spawnProbabilityAmphibians = 8;
		@Comment("Spawn probability Goats in loaded chunks")
		public int spawnProbabilityGoats = 8;
		@Comment("Spawn probability Sheep in loaded chunks")
		public int spawnProbabilitySheep = 8;
		@Comment("Spawn probability Rabbits in loaded chunks")
		public int spawnProbabilityRabbits = 8;

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
		@Comment("Number of potential Rabbit families per chunk")
		public int numberRabbitFamilies = 2;

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

	public static class SpawnLocations
	{

		//CHICKENS
		@Comment("BiomeDictionary types for spawning Plymouth Rock Chickens")
		public String[] chickenPlymouthRockBiomeTypes = new String[]
				{
						"MOUNTAIN",
				};
		@Comment("BiomeDictionary types for spawning Leghorn Chickens")
		public String[] chickenLeghornBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Orpington Chickens")
		public String[] chickenOrpingtonBiomeTypes = new String[]
				{
						"JUNGLE",
						"SWAMP",
				};
		@Comment("BiomeDictionary types for spawning Wyandotte Chickens")
		public String[] chickenWyandotteBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Rhode Island Red Chickens")
		public String[] chickenRhodeIslandRedBiomeTypes = new String[]
				{
						"FOREST",
				};

		//COWS
		@Comment("BiomeDictionary types for spawning Holstein Cows")
		public String[] cowHolsteinBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Friesian Cows")
		public String[] cowFriesianBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Angus Cows")
		public String[] cowAngusBiomeTypes = new String[]
				{
						"JUNGLE",
						"MESA",
						"SWAMP",
				};
		@Comment("BiomeDictionary types for spawning Hereford Cows")
		public String[] cowHerefordBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS",
				};
		@Comment("BiomeDictionary types for spawning Highland Cows")
		public String[] cowHighlandBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS"
				};
		@Comment("BiomeDictionary types for spawning Jersey Cows")
		public String[] cowJerseyBiomeTypes = new String[]
				{
						"WASTELAND",
						"SWAMP",
				};
		@Comment("BiomeDictionary types for spawning Longhorn Cows")
		public String[] cowLonghornBiomeTypes = new String[]
				{
						"SAVANNA",
				};
		@Comment("BiomeDictionary types for spawning Mooshroom Cows")
		public String[] cowMooshroomBiomeTypes = new String[]
				{
						"MUSHROOM",
						"MAGICAL"
				};

		//FROGS
		@Comment("BiomeDictionary types for spawning Toads")
		public String[] toadBiomeTypes = new String[]
				{
						"SWAMP",
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Frogs")
		public String[] frogBiomeTypes = new String[]
				{
						"SWAMP",
						"RIVER"
				};
		@Comment("BiomeDictionary types for spawning Dart Frogs")
		public String[] dartFrogBiomeTypes = new String[]
				{
						"JUNGLE",
						"FOREST",
				};

		//HORSES
		@Comment("BiomeDictionary types for spawning Draft Horses")
		public String[] draftHorseBiomeTypes = new String[]
				{
						"PLAINS",
						"SAVANNA",
						"MESA",
				};

		//RODENTS
		@Comment("BiomeDictionary types for spawning Hamsters")
		public String[] hamsterBiomeTypes = new String[]
				{
						"BEACH",
						"SANDY",
				};
		@Comment("BiomeDictionary types for spawning Grey Ferrets")
		public String[] ferretGrayBiomeTypes = new String[]
				{
						"SAVANNA",
				};
		@Comment("BiomeDictionary types for spawning White Ferrets")
		public String[] ferretWhiteBiomeTypes = new String[]
				{
						"SAVANNA",
				};
		@Comment("BiomeDictionary types for spawning Hedgehogs")
		public String[] hedgehogBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Albino Hedgehogs")
		public String[] hedgehogAlbinoBiomeTypes = new String[]
				{
						"SWAMP",
				};

		//PIGS
		@Comment("BiomeDictionary types for spawning Yorkshire Pigs")
		public String[] pigYorkshireBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Old Spot Pigs")
		public String[] pigOldSpotBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Large Black Pigs")
		public String[] pigLargeBlackBiomeTypes = new String[]
				{
						"SWAMP",
						"DENSE",
				};
		@Comment("BiomeDictionary types for spawning Large White Pigs")
		public String[] pigLargeWhiteBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Duroc Pigs")
		public String[] pigDurocBiomeTypes = new String[]
				{
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Hampshire Pigs")
		public String[] pigHampshireBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS",
				};

		//GOATS
		@Comment("BiomeDictionary types for spawning Alpine Goats")
		public String[] goatAlpineBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS",
				};
		@Comment("BiomeDictionary types for spawning Angora Goats")
		public String[] goatAngoraBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Fainting Goats")
		public String[] goatFaintingBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Kiko Goats")
		public String[] goatKikoBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS",
				};
		@Comment("BiomeDictionary types for spawning Kinder Goats")
		public String[] goatKinderBiomeTypes = new String[]
				{
						"SAVANNA",
						"MESA",
				};
		@Comment("BiomeDictionary types for spawning Nigerian Dwarf Goats")
		public String[] goatNigerianDwarfBiomeTypes = new String[]
				{
						"SANDY",
				};
		@Comment("BiomeDictionary types for spawning Pygmy Goats")
		public String[] goatPygmyBiomeTypes = new String[]
				{
						"SAVANNA",
						"MESA",
				};

		//SHEEP
		@Comment("BiomeDictionary types for spawning Dorset Sheep")
		public String[] sheepDorsetBiomeTypes = new String[]
				{
						"HILLS",
				};
		@Comment("BiomeDictionary types for spawning Friesian Sheep")
		public String[] sheepFriesianBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Jacob Sheep")
		public String[] sheepJacobBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Merino Sheep")
		public String[] sheepMerinoBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Suffolk Sheep")
		public String[] sheepSuffolkBiomeTypes = new String[]
				{
						"SAVANNA",
						"MESA",
				};
		@Comment("BiomeDictionary types for spawning Dorper Sheep")
		public String[] sheepDorperBiomeTypes = new String[]
				{
						"SAVANNA",
				};

		//RABBITS
		@Comment("BiomeDictionary types for spawning Cottontail Rabbits")
		public String[] rabbitCottontailBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Chinchilla Rabbits")
		public String[] rabbitChinchillaBiomeTypes = new String[]
				{
						"SAVANNA",
				};
		@Comment("BiomeDictionary types for spawning Dutch Rabbits")
		public String[] rabbitDutchBiomeTypes = new String[]
				{
						"PLAINS",
				};
		@Comment("BiomeDictionary types for spawning Havana Rabbits")
		public String[] rabbitHavanaBiomeTypes = new String[]
				{
						"MOUNTAIN",
						"HILLS",
				};
		@Comment("BiomeDictionary types for spawning Jack Rabbits")
		public String[] rabbitJackBiomeTypes = new String[]
				{
						"SAVANNA",
						"SANDY",
				};
		@Comment("BiomeDictionary types for spawning New Zealand Rabbits")
		public String[] rabbitNewZealandBiomeTypes = new String[]
				{
						"FOREST",
				};
		@Comment("BiomeDictionary types for spawning Rex Rabbits")
		public String[] rabbitRexBiomeTypes = new String[]
				{
						"SAVANNA",
				};
		@Comment("BiomeDictionary types for spawning Lop Rabbits")
		public String[] rabbitLopBiomeTypes = new String[]
				{
						"PLAINS",
						"FOREST",
				};

		//PEAFOWL
		@Comment("BiomeDictionary types for spawning Charcoal Peafowl")
		public String[] peafowlCharcoalBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Opal Peafowl")
		public String[] peafowlOpalBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Peach Peafowl")
		public String[] peafowlPeachBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Purple Peafowl")
		public String[] peafowlPurpleBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Taupe Peafowl")
		public String[] peafowlTaupeBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning Blue Peafowl")
		public String[] peafowlBlueBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};
		@Comment("BiomeDictionary types for spawning White Peafowl")
		public String[] peafowlWhiteBiomeTypes = new String[]
				{
						"SWAMP",
						"JUNGLE",
				};


	}

	public static class CareAndFeeding
	{
		@Comment("Ticks before next incremental growth")
		public int childGrowthTick = 200;
		@Comment("Ticks between feedings")
		public int feedTimer = 12000;
		@Comment("Ticks between drinking water")
		public int waterTimer = 12000; 
		@Comment("Ticks between playing")
		public int playTimer = 12000;
		@Comment("Ticks between laying eggs")
		public int laidTimer = 2000;
		@Comment("Ticks between dropping feathers")
		public int featherTimer = 12000;
		@Comment("Ticks between birthings")
		public int gestationTimer = 20000;
		@Comment("Mammals mate only after hand-feeding")
		public boolean feedToBreed = true;
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

		@Comment("Animals won't breed if there are more than the specified amount of animals of their type in a 30 block range.")
		public int entityBreedingLimit = 15;

		@Comment("If male animals can have multiple mates.")
		public boolean malesMateMultipleFemales = false;
		
		@Comment("Chance that multiple animals will be born. Gets multiplied with itself after every child. Set to 0 to disable.")
		public double birthMultipleChance = 0.1;
		
		@Comment("Chance that a female animal will lose its baby, if it is hungry and thirsty. Set to 0 to disable")
		public double animalLossChance = 0.0;

	}

	public static class FoodValues
	{
		@Comment("Food Value Overrides. Format: modid:name(hunger,saturationMultiplier) Example: animania:cheese_omelette(5,0.8)")
		public String[] foodValueOverrides = new String[] {};
	}

}

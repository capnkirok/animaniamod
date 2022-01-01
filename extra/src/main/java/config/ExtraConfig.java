package config;

import com.animania.Animania;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = Animania.MODID, name = "animania_extra")
public class ExtraConfig
{
	@Config.Name("extra")
	public static ExtraConfig.Settings settings = new Settings();

	public static class Settings
	{
		@Comment("Spawning and Breeding")
		public SpawningAndBreeding spawning_and_breeding = new SpawningAndBreeding();

		@Comment("Remove vanilla Rabbits")
		public boolean replaceVanillaRabbits = true;

		@Comment("Capacity of RF that the Hamster Wheel has")
		public int hamsterWheelCapacity = 200000;

		@Comment("RF/tick that the Hamster wheel generates while in use")
		public int hamsterWheelRFGeneration = 20;

		@Comment("Ticks that the hamster stays in the wheel before it needs more food")
		public int hamsterWheelUseTime = 2000;

		@Comment("Food Items that ferrets can eat (use # for meta)")
		public String[] ferretFood = { "minecraft:mutton", "minecraft:egg", "animania:brown_egg", "animania:peacock_egg_blue", "animania:peacock_egg_white", "animania:prime_mutton", "animania:prime_rabbit", "minecraft:rabbit", "minecraft:chicken", "animania_prime_chicken" };

		@Comment("Food Items that hamsters can eat (use # for meta)")
		public String[] hamsterFood = { "animania:hamster_food", "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem", "minecraft:apple" };

		@Comment("Food Items that hedgehogs can eat (use # for meta)")
		public String[] hedgehogFood = { "minecraft:carrot", "minecraft:beetroot", "minecraft:egg", "animania:brown_egg", "animania:peacock_egg_blue", "animania:peacock_egg_white", "animania:prime_mutton", "animania:prime_rabbit", "minecraft:rabbit", "minecraft:chicken", "animania_prime_chicken", "minecraft:apple" };

		@Comment("Food Items that peacocks can eat (use # for meta)")
		public String[] peacockFood = { "minecraft:wheat_seeds", "minecraft:melon_seeds", "minecraft:beetroot_seeds", "minecraft:pumpkin_seeds", "simplecorn:corncob", "biomesoplenty:turnip_seeds", "harvestcraft:cornitem" };

		@Comment("Food Items that rabbits can eat (use # for meta)")
		public String[] rabbitFood = { "minecraft:wheat", "minecraft:carrot", "minecraft:beetroot", "minecraft:apple" };

		@Comment("Ferret Bed Block Preferred")
		public String ferretBed = "animania:block_straw";

		@Comment("Ferret Bed Block Backup")
		public String ferretBed2 = "minecraft:grass";

		@Comment("Hamster Bed Block Preferred")
		public String hamsterBed = "animania:block_straw";

		@Comment("Hamster Bed Block Backup")
		public String hamsterBed2 = "";

		@Comment("Hedgehog Bed Block Preferred")
		public String hedgehogBed = "animania:block_straw";

		@Comment("Hedgehog Bed Block Backup")
		public String hedgehogBed2 = "minecraft:grass";

		@Comment("Peacock Bed Block Preferred")
		public String peacockBed = "animania:block_straw";

		@Comment("Peacock Bed Block Backup")
		public String peacockBed2 = "minecraft:grass";

		@Comment("Rabbit Bed Block Preferred")
		public String rabbitBed = "animania:block_straw";

		@Comment("Rabbit Bed Block Backup")
		public String rabbitBed2 = "minecraft:grass";

	}

	public static class SpawningAndBreeding
	{
		@Comment("Number of potential Rabbit families per chunk")
		public int numberRabbitFamilies = 2;

		@Comment("Spawn Animania Rodents in level")
		public boolean spawnAnimaniaRodents = true;
		@Comment("Spawn Animania Peacocks in level")
		public boolean spawnAnimaniaPeacocks = true;

		@Comment("Spawn Animania Amphibians in level")
		public boolean spawnAnimaniaAmphibians = true;

		@Comment("Spawn Animania Rabbits in level")
		public boolean spawnAnimaniaRabbits = true;

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

		@Comment("Spawn probability Rabbits in loaded chunks")
		public int spawnProbabilityRabbits = 8;

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

		@Comment("Spawn limit for Rabbits in loaded chunks")
		public int spawnLimitRabbits = 40;

		// FROGS
		@Comment("BiomeDictionary types for spawning Toads")
		public String[] toadBiomeTypes = { "SWAMP", "FOREST", };
		@Comment("BiomeDictionary types for spawning Frogs")
		public String[] frogBiomeTypes = { "SWAMP", "RIVER" };
		@Comment("BiomeDictionary types for spawning Dart Frogs")
		public String[] dartFrogBiomeTypes = { "JUNGLE", "FOREST", };

		// RODENTS
		@Comment("BiomeDictionary types for spawning Hamsters")
		public String[] hamsterBiomeTypes = { "BEACH", "SANDY", };
		@Comment("BiomeDictionary types for spawning Grey Ferrets")
		public String[] ferretGrayBiomeTypes = { "SAVANNA", };
		@Comment("BiomeDictionary types for spawning White Ferrets")
		public String[] ferretWhiteBiomeTypes = { "SAVANNA", };
		@Comment("BiomeDictionary types for spawning Hedgehogs")
		public String[] hedgehogBiomeTypes = { "FOREST", };
		@Comment("BiomeDictionary types for spawning Albino Hedgehogs")
		public String[] hedgehogAlbinoBiomeTypes = { "SWAMP", };

		// RABBITS
		@Comment("BiomeDictionary types for spawning Cottontail Rabbits")
		public String[] rabbitCottontailBiomeTypes = { "FOREST", };
		@Comment("BiomeDictionary types for spawning Chinchilla Rabbits")
		public String[] rabbitChinchillaBiomeTypes = { "SAVANNA", };
		@Comment("BiomeDictionary types for spawning Dutch Rabbits")
		public String[] rabbitDutchBiomeTypes = { "PLAINS", };
		@Comment("BiomeDictionary types for spawning Havana Rabbits")
		public String[] rabbitHavanaBiomeTypes = { "MOUNTAIN", "HILLS", };
		@Comment("BiomeDictionary types for spawning Jack Rabbits")
		public String[] rabbitJackBiomeTypes = { "SAVANNA", "SANDY", };
		@Comment("BiomeDictionary types for spawning New Zealand Rabbits")
		public String[] rabbitNewZealandBiomeTypes = { "FOREST", };
		@Comment("BiomeDictionary types for spawning Rex Rabbits")
		public String[] rabbitRexBiomeTypes = { "SAVANNA", };
		@Comment("BiomeDictionary types for spawning Lop Rabbits")
		public String[] rabbitLopBiomeTypes = { "PLAINS", "FOREST", };

		// PEAFOWL
		@Comment("BiomeDictionary types for spawning Charcoal Peafowl")
		public String[] peafowlCharcoalBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Opal Peafowl")
		public String[] peafowlOpalBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Peach Peafowl")
		public String[] peafowlPeachBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Purple Peafowl")
		public String[] peafowlPurpleBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Taupe Peafowl")
		public String[] peafowlTaupeBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning Blue Peafowl")
		public String[] peafowlBlueBiomeTypes = { "SWAMP", "JUNGLE", };
		@Comment("BiomeDictionary types for spawning White Peafowl")
		public String[] peafowlWhiteBiomeTypes = { "SWAMP", "JUNGLE", };

	}
}

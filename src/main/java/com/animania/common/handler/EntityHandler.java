package com.animania.common.handler;

import java.util.List;
import java.util.Set;

import com.animania.common.entities.EntityGender;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHighland;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullJersey;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityBullMooshroom;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHighland;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfJersey;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCalfMooshroom;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHighland;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowJersey;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.cows.EntityCowMooshroom;
import com.animania.common.entities.goats.EntityBuckAlpine;
import com.animania.common.entities.goats.EntityBuckAngora;
import com.animania.common.entities.goats.EntityBuckFainting;
import com.animania.common.entities.goats.EntityBuckKiko;
import com.animania.common.entities.goats.EntityBuckKinder;
import com.animania.common.entities.goats.EntityBuckNigerianDwarf;
import com.animania.common.entities.goats.EntityBuckPygmy;
import com.animania.common.entities.goats.EntityDoeAlpine;
import com.animania.common.entities.goats.EntityDoeAngora;
import com.animania.common.entities.goats.EntityDoeFainting;
import com.animania.common.entities.goats.EntityDoeKiko;
import com.animania.common.entities.goats.EntityDoeKinder;
import com.animania.common.entities.goats.EntityDoeNigerianDwarf;
import com.animania.common.entities.goats.EntityDoePygmy;
import com.animania.common.entities.goats.EntityKidAlpine;
import com.animania.common.entities.goats.EntityKidAngora;
import com.animania.common.entities.goats.EntityKidFainting;
import com.animania.common.entities.goats.EntityKidKiko;
import com.animania.common.entities.goats.EntityKidKinder;
import com.animania.common.entities.goats.EntityKidNigerianDwarf;
import com.animania.common.entities.goats.EntityKidPygmy;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.EntityPeachickOpal;
import com.animania.common.entities.peacocks.EntityPeachickPeach;
import com.animania.common.entities.peacocks.EntityPeachickPurple;
import com.animania.common.entities.peacocks.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.EntityPeacockOpal;
import com.animania.common.entities.peacocks.EntityPeacockPeach;
import com.animania.common.entities.peacocks.EntityPeacockPurple;
import com.animania.common.entities.peacocks.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.EntityPeafowlTaupe;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HamsterType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckRex;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeRex;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitDutch;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitLop;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitNewZealand;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitRex;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.EntityEweDorper;
import com.animania.common.entities.sheep.EntityEweDorset;
import com.animania.common.entities.sheep.EntityEweFriesian;
import com.animania.common.entities.sheep.EntityEweJacob;
import com.animania.common.entities.sheep.EntityEweMerino;
import com.animania.common.entities.sheep.EntityEweSuffolk;
import com.animania.common.entities.sheep.EntityLambDorper;
import com.animania.common.entities.sheep.EntityLambDorset;
import com.animania.common.entities.sheep.EntityLambFriesian;
import com.animania.common.entities.sheep.EntityLambJacob;
import com.animania.common.entities.sheep.EntityLambMerino;
import com.animania.common.entities.sheep.EntityLambSuffolk;
import com.animania.common.entities.sheep.EntityRamDorper;
import com.animania.common.entities.sheep.EntityRamDorset;
import com.animania.common.entities.sheep.EntityRamFriesian;
import com.animania.common.entities.sheep.EntityRamJacob;
import com.animania.common.entities.sheep.EntityRamMerino;
import com.animania.common.entities.sheep.EntityRamSuffolk;
import com.animania.common.entities.sheep.SheepType;
import com.animania.common.helper.RegistryHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Lists;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler
{

	public static void preInit()
	{

		int entityID = 0;

		// HORSES
		RegistryHelper.Entities.registerAnimal(EntityMareDraftHorse.class, "mare_draft", entityID++, HorseType.DRAFT, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityStallionDraftHorse.class, "stallion_draft", entityID++, HorseType.DRAFT, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFoalDraftHorse.class, "foal_draft", entityID++, HorseType.DRAFT, EntityGender.CHILD);

		int maxFam = AnimaniaConfig.spawn.numberHorseFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaHorses)
		{
			EntityRegistry.addSpawn(EntityMareDraftHorse.class, AnimaniaConfig.spawn.spawnProbabilityHorses, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			EntityRegistry.addSpawn(EntityMareDraftHorse.class, AnimaniaConfig.spawn.spawnProbabilityHorses, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
		}

		// RODENTS
		RegistryHelper.Entities.registerAnimal(EntityHamster.class, "hamster", entityID++, HamsterType.STANDARD, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityFerretGrey.class, "ferret_grey", entityID++, FerretType.GREY, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFerretWhite.class, "ferret_white", entityID++, FerretType.WHITE, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityHedgehog.class, "hedgehog", entityID++, HedgehogType.NORMAL, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityHedgehogAlbino.class, "hedgehog_albino", entityID++, HedgehogType.ALBINO, EntityGender.NONE);
		if (AnimaniaConfig.spawn.spawnAnimaniaRodents)
		{
			EntityRegistry.addSpawn(EntityHamster.class, AnimaniaConfig.spawn.spawnProbabilityHamsters, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.BEACH));
			EntityRegistry.addSpawn(EntityHamster.class, AnimaniaConfig.spawn.spawnProbabilityHamsters, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			EntityRegistry.addSpawn(EntityFerretGrey.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			EntityRegistry.addSpawn(EntityFerretWhite.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			EntityRegistry.addSpawn(EntityHedgehog.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			EntityRegistry.addSpawn(EntityHedgehogAlbino.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));

		}

		// PIGS
		RegistryHelper.Entities.registerAnimal(EntityPigletYorkshire.class, "piglet_yorkshire", entityID++, PigType.YORKSHIRE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowYorkshire.class, "sow_yorkshire", entityID++, PigType.YORKSHIRE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogYorkshire.class, "hog_yorkshire", entityID++, PigType.YORKSHIRE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPigletOldSpot.class, "piglet_old_spot", entityID++, PigType.OLD_SPOT, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowOldSpot.class, "sow_old_spot", entityID++, PigType.OLD_SPOT, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogOldSpot.class, "hog_old_spot", entityID++, PigType.OLD_SPOT, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPigletLargeBlack.class, "piglet_large_black", entityID++, PigType.LARGE_BLACK, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowLargeBlack.class, "sow_large_black", entityID++, PigType.LARGE_BLACK, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogLargeBlack.class, "hog_large_black", entityID++, PigType.LARGE_BLACK, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPigletLargeWhite.class, "piglet_large_white", entityID++, PigType.LARGE_WHITE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowLargeWhite.class, "sow_large_white", entityID++, PigType.LARGE_WHITE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogLargeWhite.class, "hog_large_white", entityID++, PigType.LARGE_WHITE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPigletHampshire.class, "piglet_hampshire", entityID++, PigType.HAMPSHIRE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowHampshire.class, "sow_hampshire", entityID++, PigType.HAMPSHIRE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogHampshire.class, "hog_hampshire", entityID++, PigType.HAMPSHIRE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPigletDuroc.class, "piglet_duroc", entityID++, PigType.DUROC, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntitySowDuroc.class, "sow_duroc", entityID++, PigType.DUROC, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityHogDuroc.class, "hog_duroc", entityID++, PigType.DUROC, EntityGender.MALE);

		maxFam = AnimaniaConfig.spawn.numberPigFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaPigs)
		{
			EntityRegistry.addSpawn(EntitySowYorkshire.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			EntityRegistry.addSpawn(EntitySowOldSpot.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			EntityRegistry.addSpawn(EntitySowLargeBlack.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, Biomes.BIRCH_FOREST);
			EntityRegistry.addSpawn(EntitySowLargeBlack.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			EntityRegistry.addSpawn(EntitySowLargeWhite.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			EntityRegistry.addSpawn(EntitySowDuroc.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			EntityRegistry.addSpawn(EntitySowHampshire.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			EntityRegistry.addSpawn(EntitySowHampshire.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES);
		}

		// CHICKENS
		RegistryHelper.Entities.registerAnimal(EntityChickLeghorn.class, "chick_leghorn", entityID++, ChickenType.LEGHORN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityHenLeghorn.class, "hen_leghorn", entityID++, ChickenType.LEGHORN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRoosterLeghorn.class, "rooster_leghorn", entityID++, ChickenType.LEGHORN, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityChickOrpington.class, "chick_orpington", entityID++, ChickenType.ORPINGTON, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityHenOrpington.class, "hen_orpington", entityID++, ChickenType.ORPINGTON, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRoosterOrpington.class, "rooster_orpington", entityID++, ChickenType.ORPINGTON, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityChickPlymouthRock.class, "chick_plymouth_rock", entityID++, ChickenType.PLYMOUTH_ROCK, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityHenPlymouthRock.class, "hen_plymouth_rock", entityID++, ChickenType.PLYMOUTH_ROCK, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRoosterPlymouthRock.class, "rooster_plymouth_rock", entityID++, ChickenType.PLYMOUTH_ROCK, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityChickRhodeIslandRed.class, "chick_rhode_island_red", entityID++, ChickenType.RHODE_ISLAND_RED, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityHenRhodeIslandRed.class, "hen_rhode_island_red", entityID++, ChickenType.RHODE_ISLAND_RED, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRoosterRhodeIslandRed.class, "rooster_rhode_island_red", entityID++, ChickenType.RHODE_ISLAND_RED, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityChickWyandotte.class, "chick_wyandotte", entityID++, ChickenType.WYANDOTTE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityHenWyandotte.class, "hen_wyandotte", entityID++, ChickenType.WYANDOTTE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRoosterWyandotte.class, "rooster_wyandotte", entityID++, ChickenType.WYANDOTTE, EntityGender.MALE);

		maxFam = AnimaniaConfig.spawn.numberChickenFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}
		if (AnimaniaConfig.spawn.spawnAnimaniaChickens)
		{
			EntityRegistry.addSpawn(EntityHenPlymouthRock.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			EntityRegistry.addSpawn(EntityHenPlymouthRock.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS);
			EntityRegistry.addSpawn(EntityHenLeghorn.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			//EntityRegistry.addSpawn(EntityHenLeghorn.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, Biomes.PLAINS);
			EntityRegistry.addSpawn(EntityHenOrpington.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			EntityRegistry.addSpawn(EntityHenOrpington.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			EntityRegistry.addSpawn(EntityHenWyandotte.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
		}

		// COWS
		RegistryHelper.Entities.registerAnimal(EntityCalfAngus.class, "calf_angus", entityID++, CowType.ANGUS, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowAngus.class, "cow_angus", entityID++, CowType.ANGUS, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullAngus.class, "bull_angus", entityID++, CowType.ANGUS, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfFriesian.class, "calf_friesian", entityID++, CowType.FRIESIAN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowFriesian.class, "cow_friesian", entityID++, CowType.FRIESIAN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullFriesian.class, "bull_friesian", entityID++, CowType.FRIESIAN, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfHereford.class, "calf_hereford", entityID++, CowType.HEREFORD, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowHereford.class, "cow_hereford", entityID++, CowType.HEREFORD, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullHereford.class, "bull_hereford", entityID++, CowType.HEREFORD, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfHolstein.class, "calf_holstein", entityID++, CowType.HOLSTEIN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowHolstein.class, "cow_holstein", entityID++, CowType.HOLSTEIN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullHolstein.class, "bull_holstein", entityID++, CowType.HOLSTEIN, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfLonghorn.class, "calf_longhorn", entityID++, CowType.LONGHORN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowLonghorn.class, "cow_longhorn", entityID++, CowType.LONGHORN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullLonghorn.class, "bull_longhorn", entityID++, CowType.LONGHORN, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfHighland.class, "calf_highland", entityID++, CowType.HIGHLAND, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowHighland.class, "cow_highland", entityID++, CowType.HIGHLAND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullHighland.class, "bull_highland", entityID++, CowType.HIGHLAND, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfJersey.class, "calf_jersey", entityID++, CowType.JERSEY, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowJersey.class, "cow_jersey", entityID++, CowType.JERSEY, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullJersey.class, "bull_jersey", entityID++, CowType.JERSEY, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityCalfMooshroom.class, "calf_mooshroom", entityID++, CowType.MOOSHROOM, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityCowMooshroom.class, "cow_mooshroom", entityID++, CowType.MOOSHROOM, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityBullMooshroom.class, "bull_mooshroom", entityID++, CowType.MOOSHROOM, EntityGender.MALE);

		
		maxFam = AnimaniaConfig.spawn.numberCowFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaCows)
		{
			EntityRegistry.addSpawn(EntityCowHolstein.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			EntityRegistry.addSpawn(EntityCowFriesian.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
			EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.LUSH));
			EntityRegistry.addSpawn(EntityCowLonghorn.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			EntityRegistry.addSpawn(EntityCowHereford.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			EntityRegistry.addSpawn(EntityCowHereford.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			EntityRegistry.addSpawn(EntityCowHighland.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			EntityRegistry.addSpawn(EntityCowHighland.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			EntityRegistry.addSpawn(EntityCowJersey.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.WASTELAND));
			EntityRegistry.addSpawn(EntityCowJersey.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.LUSH));
			EntityRegistry.addSpawn(EntityCowMooshroom.class, AnimaniaConfig.spawn.spawnProbabilityCows + 4, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MUSHROOM));
			EntityRegistry.addSpawn(EntityCowMooshroom.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MAGICAL));
			EntityRegistry.addSpawn(EntityCowMooshroom.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, maxFam, EnumCreatureType.CREATURE, Biomes.MUSHROOM_ISLAND);
			
		}

		// AMPHIBIANS
		RegistryHelper.Entities.registerAnimal(EntityToad.class, "toad", entityID++, AmphibianType.TOAD, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFrogs.class, "frog", entityID++, AmphibianType.FROG, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityDartFrogs.class, "dartfrog", entityID++, AmphibianType.DART_FROG, EntityGender.NONE);

		if (AnimaniaConfig.spawn.spawnAnimaniaAmphibians)
		{
			EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.RIVER));
			RegistryHelper.Entities.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
		}

		// GOATS
		RegistryHelper.Entities.registerAnimal(EntityKidAlpine.class, "kid_alpine", entityID++, GoatType.ALPINE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckAlpine.class, "buck_alpine", entityID++, GoatType.ALPINE, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeAlpine.class, "doe_alpine", entityID++, GoatType.ALPINE, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidAngora.class, "kid_angora", entityID++, GoatType.ANGORA, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckAngora.class, "buck_angora", entityID++, GoatType.ANGORA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeAngora.class, "doe_angora", entityID++, GoatType.ANGORA, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidFainting.class, "kid_fainting", entityID++, GoatType.FAINTING, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckFainting.class, "buck_fainting", entityID++, GoatType.FAINTING, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeFainting.class, "doe_fainting", entityID++, GoatType.FAINTING, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidKiko.class, "kid_kiko", entityID++, GoatType.KIKO, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckKiko.class, "buck_kiko", entityID++, GoatType.KIKO, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeKiko.class, "doe_kiko", entityID++, GoatType.KIKO, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidKinder.class, "kid_kinder", entityID++, GoatType.KINDER, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckKinder.class, "buck_kinder", entityID++, GoatType.KINDER, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeKinder.class, "doe_kinder", entityID++, GoatType.KINDER, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidNigerianDwarf.class, "kid_nigerian_dwarf", entityID++, GoatType.NIGERIAN_DWARF, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckNigerianDwarf.class, "buck_nigerian_dwarf", entityID++, GoatType.NIGERIAN_DWARF, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoeNigerianDwarf.class, "doe_nigerian_dwarf", entityID++, GoatType.NIGERIAN_DWARF, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityKidPygmy.class, "kid_pygmy", entityID++, GoatType.PYGMY, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityBuckPygmy.class, "buck_pygmy", entityID++, GoatType.PYGMY, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityDoePygmy.class, "doe_pygmy", entityID++, GoatType.PYGMY, EntityGender.FEMALE);

		maxFam = AnimaniaConfig.spawn.numberGoatFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}


		if (AnimaniaConfig.spawn.spawnAnimaniaGoats)
		{	
			RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityDoeAngora.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityDoeFainting.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
			RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SANDY));
			RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
		}

		// SHEEP

		RegistryHelper.Entities.registerAnimal(EntityLambFriesian.class, "lamb_friesian", entityID++, SheepType.FRIESIAN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweFriesian.class, "ewe_friesian", entityID++, SheepType.FRIESIAN, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamFriesian.class, "ram_friesian", entityID++, SheepType.FRIESIAN, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityLambSuffolk.class, "lamb_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweSuffolk.class, "ewe_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamSuffolk.class, "ram_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityLambDorper.class, "lamb_dorper", entityID++, SheepType.DORPER, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweDorper.class, "ewe_dorper", entityID++, SheepType.DORPER, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamDorper.class, "ram_dorper", entityID++, SheepType.DORPER, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityLambDorset.class, "lamb_dorset", entityID++, SheepType.DORSET, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweDorset.class, "ewe_dorset", entityID++, SheepType.DORSET, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamDorset.class, "ram_dorset", entityID++, SheepType.DORSET, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityLambMerino.class, "lamb_merino", entityID++, SheepType.MERINO, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweMerino.class, "ewe_merino", entityID++, SheepType.MERINO, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamMerino.class, "ram_merino", entityID++, SheepType.MERINO, EntityGender.FEMALE);

		RegistryHelper.Entities.registerAnimal(EntityLambJacob.class, "lamb_jacob", entityID++, SheepType.JACOB, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweJacob.class, "ewe_jacob", entityID++, SheepType.JACOB, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRamJacob.class, "ram_jacob", entityID++, SheepType.JACOB, EntityGender.FEMALE);

		maxFam = AnimaniaConfig.spawn.numberSheepFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaSheep)
		{
			RegistryHelper.Entities.addSpawn(EntityEweDorset.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityEweFriesian.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweJacob.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			RegistryHelper.Entities.addSpawn(EntityEweMerino.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweMerino.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityEweSuffolk.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
			RegistryHelper.Entities.addSpawn(EntityEweSuffolk.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityEweDorper.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));

		}

		// RABBITS


		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckCottontail.class, "buck_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeCottontail.class, "doe_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitCottontail.class, "kit_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckChinchilla.class, "buck_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeChinchilla.class, "doe_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitChinchilla.class, "kit_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckDutch.class, "buck_dutch", entityID++, RabbitType.DUTCH, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeDutch.class, "doe_dutch", entityID++, RabbitType.DUTCH, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitDutch.class, "kit_dutch", entityID++, RabbitType.DUTCH, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckHavana.class, "buck_havana", entityID++, RabbitType.HAVANA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeHavana.class, "doe_havana", entityID++, RabbitType.HAVANA, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitHavana.class, "kit_havana", entityID++, RabbitType.HAVANA, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckJack.class, "buck_jack", entityID++, RabbitType.JACK, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeJack.class, "doe_jack", entityID++, RabbitType.JACK, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitJack.class, "kit_jack", entityID++, RabbitType.JACK, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckNewZealand.class, "buck_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeNewZealand.class, "doe_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitNewZealand.class, "kit_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckRex.class, "buck_rex", entityID++, RabbitType.REX, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeRex.class, "doe_rex", entityID++, RabbitType.REX, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitRex.class, "kit_rex", entityID++, RabbitType.REX, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(EntityRabbitBuckLop.class, "buck_lop", entityID++, RabbitType.LOP, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitDoeLop.class, "doe_lop", entityID++, RabbitType.LOP, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRabbitKitLop.class, "kit_lop", entityID++, RabbitType.LOP, EntityGender.CHILD);

		maxFam = AnimaniaConfig.spawn.numberRabbitFamilies;
		if (maxFam < 1) {
			maxFam = 1;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaRabbits)
		{
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeCottontail.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeChinchilla.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SNOWY));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeDutch.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeHavana.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeHavana.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SANDY));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeNewZealand.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeRex.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeLop.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeLop.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));
		}

		// PEACOCKS (NEW)
		RegistryHelper.Entities.registerAnimal(EntityPeachickCharcoal.class, "peachick_charcoal", entityID++, PeacockType.CHARCOAL, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlCharcoal.class, "peahen_charcoal", entityID++, PeacockType.CHARCOAL, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockCharcoal.class, "peacock_charcoal", entityID++, PeacockType.CHARCOAL, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickOpal.class, "peachick_opal", entityID++, PeacockType.OPAL, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlOpal.class, "peahen_opal", entityID++, PeacockType.OPAL, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockOpal.class, "peacock_opal", entityID++, PeacockType.OPAL, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickPeach.class, "peachick_peach", entityID++, PeacockType.PEACH, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlPeach.class, "peahen_peach", entityID++, PeacockType.PEACH, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockPeach.class, "peacock_peach", entityID++, PeacockType.PEACH, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickPurple.class, "peachick_purple", entityID++, PeacockType.PURPLE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlPurple.class, "peahen_purple", entityID++, PeacockType.PURPLE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockPurple.class, "peacock_purple", entityID++, PeacockType.PURPLE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickTaupe.class, "peachick_taupe", entityID++, PeacockType.TAUPE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlTaupe.class, "peahen_taupe", entityID++, PeacockType.TAUPE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockTaupe.class, "peacock_taupe", entityID++, PeacockType.TAUPE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickBlue.class, "peachick_blue", entityID++, PeacockType.BLUE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlBlue.class, "peahen_blue", entityID++, PeacockType.BLUE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockBlue.class, "peacock_blue", entityID++, PeacockType.BLUE, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityPeachickWhite.class, "peachick_white", entityID++, PeacockType.WHITE, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityPeafowlWhite.class, "peahen_white", entityID++, PeacockType.WHITE, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityPeacockWhite.class, "peacock_white", entityID++, PeacockType.WHITE, EntityGender.MALE);

		if (AnimaniaConfig.spawn.spawnAnimaniaPeacocks)
		{
			RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

		}

		// MOVING ENTITIES
		if (!AnimaniaConfig.gameRules.disableRollingVehicles) {
			RegistryHelper.Entities.registerEntity(EntityWagon.class, "wagon", entityID++, 40, 1, true);
			RegistryHelper.Entities.registerEntity(EntityCart.class, "cart", entityID++, 40, 1, true);
		}
	}

	private static Biome[] getBiomes(BiomeDictionary.Type type)
	{
		List<Biome> criteriaMet = Lists.newArrayList();
		for (Biome b : Biome.REGISTRY)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
			if (types.contains(type))
			{
				criteriaMet.add(b);
			}
		}

		return criteriaMet.toArray(new Biome[criteriaMet.size()]);
	}

}
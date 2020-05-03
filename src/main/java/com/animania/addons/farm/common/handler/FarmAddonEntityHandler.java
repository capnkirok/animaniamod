package com.animania.addons.farm.common.handler;

import static com.animania.common.handler.EntityHandler.entityID;

import java.util.List;
import java.util.Set;

import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityChickLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenType;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCalfAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCowAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCalfFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCowFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCalfHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCowHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCalfHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCowHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCalfHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCowHolstein;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityBullJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCalfJersey;
import com.animania.addons.farm.common.entity.cows.CowJersey.EntityCowJersey;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCalfLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.addons.farm.common.entity.cows.CowType;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityKidAlpine;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityKidAngora;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityDoeFainting;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityKidFainting;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityBuckKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityDoeKiko;
import com.animania.addons.farm.common.entity.goats.GoatKiko.EntityKidKiko;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityBuckKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityDoeKinder;
import com.animania.addons.farm.common.entity.goats.GoatKinder.EntityKidKinder;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatNigerianDwarf.EntityKidNigerianDwarf;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityDoePygmy;
import com.animania.addons.farm.common.entity.goats.GoatPygmy.EntityKidPygmy;
import com.animania.addons.farm.common.entity.goats.GoatType;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseType;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityHogDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityPigletDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityPigletHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntitySowHampshire;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityPigletLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntityPigletLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityPigletOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigType;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityPigletYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.addons.farm.common.entity.pullables.EntityTiller;
import com.animania.addons.farm.common.entity.pullables.EntityWagon;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityEweDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityLambDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorper.EntityRamDorper;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityLambDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityLambJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityLambMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepType;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper;
import com.google.common.collect.Lists;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class FarmAddonEntityHandler
{

	/**
	 * Register Entities
	 */
	public static void preInit()
	{
		// HORSES
		RegistryHelper.Entities.registerAnimal(EntityMareDraftHorse.class, "mare_draft", entityID++, HorseType.DRAFT, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityStallionDraftHorse.class, "stallion_draft", entityID++, HorseType.DRAFT, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(EntityFoalDraftHorse.class, "foal_draft", entityID++, HorseType.DRAFT, EntityGender.CHILD);

		int maxFam = FarmConfig.settings.spawning_and_breeding.numberHorseFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.draftHorseBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityMareDraftHorse.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityHorses, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
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

		maxFam = FarmConfig.settings.spawning_and_breeding.numberPigFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaPigs)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigYorkshireBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowYorkshire.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigOldSpotBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowOldSpot.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigLargeBlackBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowLargeBlack.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigLargeWhiteBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowLargeWhite.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigDurocBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowDuroc.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigHampshireBiomeTypes))
			{
				EntityRegistry.addSpawn(EntitySowHampshire.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
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

		maxFam = FarmConfig.settings.spawning_and_breeding.numberChickenFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaChickens)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenPlymouthRockBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityHenPlymouthRock.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenLeghornBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityHenLeghorn.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenOrpingtonBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityHenOrpington.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenWyandotteBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityHenWyandotte.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenRhodeIslandRedBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

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

		maxFam = FarmConfig.settings.spawning_and_breeding.numberCowFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaCows)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHolsteinBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowHolstein.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowFriesianBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowFriesian.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowAngusBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowAngus.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHerefordBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowHereford.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHighlandBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowHighland.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowJerseyBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowJersey.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowLonghornBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowLonghorn.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowMooshroomBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityCowMooshroom.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
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

		maxFam = FarmConfig.settings.spawning_and_breeding.numberGoatFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaGoats)
		{

			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatAlpineBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeAlpine.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatAngoraBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeAngora.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatFaintingBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeFainting.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatKikoBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeKiko.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatKinderBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeKinder.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatNigerianDwarfBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoeNigerianDwarf.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.goatPygmyBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityDoePygmy.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

		}

		// SHEEP

		RegistryHelper.Entities.registerAnimal(EntityLambFriesian.class, "lamb_friesian", entityID++, SheepType.FRIESIAN, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweFriesian.class, "ewe_friesian", entityID++, SheepType.FRIESIAN, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamFriesian.class, "ram_friesian", entityID++, SheepType.FRIESIAN, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityLambSuffolk.class, "lamb_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweSuffolk.class, "ewe_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamSuffolk.class, "ram_suffolk", entityID++, SheepType.SUFFOLK, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityLambDorper.class, "lamb_dorper", entityID++, SheepType.DORPER, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweDorper.class, "ewe_dorper", entityID++, SheepType.DORPER, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamDorper.class, "ram_dorper", entityID++, SheepType.DORPER, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityLambDorset.class, "lamb_dorset", entityID++, SheepType.DORSET, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweDorset.class, "ewe_dorset", entityID++, SheepType.DORSET, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamDorset.class, "ram_dorset", entityID++, SheepType.DORSET, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityLambMerino.class, "lamb_merino", entityID++, SheepType.MERINO, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweMerino.class, "ewe_merino", entityID++, SheepType.MERINO, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamMerino.class, "ram_merino", entityID++, SheepType.MERINO, EntityGender.MALE);

		RegistryHelper.Entities.registerAnimal(EntityLambJacob.class, "lamb_jacob", entityID++, SheepType.JACOB, EntityGender.CHILD);
		RegistryHelper.Entities.registerAnimal(EntityEweJacob.class, "ewe_jacob", entityID++, SheepType.JACOB, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(EntityRamJacob.class, "ram_jacob", entityID++, SheepType.JACOB, EntityGender.MALE);

		maxFam = FarmConfig.settings.spawning_and_breeding.numberSheepFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaSheep)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweDorset.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepFriesianBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweFriesian.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepJacobBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweJacob.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepMerinoBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweMerino.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepSuffolkBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweSuffolk.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorperBiomeTypes))
			{
				EntityRegistry.addSpawn(EntityEweDorper.class, FarmConfig.settings.spawning_and_breeding.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

		}

		// MOVING ENTITIES
		if (!FarmConfig.settings.disableRollingVehicles)
		{
			RegistryHelper.Entities.registerEntity(EntityWagon.class, "wagon", entityID++, 40, 1, true);
			RegistryHelper.Entities.registerEntity(EntityCart.class, "cart", entityID++, 40, 1, true);
			RegistryHelper.Entities.registerEntity(EntityTiller.class, "tiller", entityID++, 40, 1, true);
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

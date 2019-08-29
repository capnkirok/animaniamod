package com.animania.common.handler;

import java.util.List;
import java.util.Set;

import com.animania.api.data.EntityGender;
import com.animania.common.entities.amphibians.AmphibianType;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityChickLeghorn;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.common.entities.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.ChickenOrpington.EntityChickOrpington;
import com.animania.common.entities.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.common.entities.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityChickWyandotte;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.common.entities.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.common.entities.cows.CowAngus.EntityBullAngus;
import com.animania.common.entities.cows.CowAngus.EntityCalfAngus;
import com.animania.common.entities.cows.CowAngus.EntityCowAngus;
import com.animania.common.entities.cows.CowFriesian.EntityBullFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCalfFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCowFriesian;
import com.animania.common.entities.cows.CowHereford.EntityBullHereford;
import com.animania.common.entities.cows.CowHereford.EntityCalfHereford;
import com.animania.common.entities.cows.CowHereford.EntityCowHereford;
import com.animania.common.entities.cows.CowHighland.EntityBullHighland;
import com.animania.common.entities.cows.CowHighland.EntityCalfHighland;
import com.animania.common.entities.cows.CowHighland.EntityCowHighland;
import com.animania.common.entities.cows.CowHolstein.EntityBullHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCalfHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCowHolstein;
import com.animania.common.entities.cows.CowJersey.EntityBullJersey;
import com.animania.common.entities.cows.CowJersey.EntityCalfJersey;
import com.animania.common.entities.cows.CowJersey.EntityCowJersey;
import com.animania.common.entities.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.common.entities.cows.CowLonghorn.EntityCalfLonghorn;
import com.animania.common.entities.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.common.entities.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCalfMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.common.entities.goats.GoatAlpine.EntityDoeAlpine;
import com.animania.common.entities.goats.GoatAlpine.EntityKidAlpine;
import com.animania.common.entities.goats.GoatAngora.EntityBuckAngora;
import com.animania.common.entities.goats.GoatAngora.EntityDoeAngora;
import com.animania.common.entities.goats.GoatAngora.EntityKidAngora;
import com.animania.common.entities.goats.GoatFainting.EntityBuckFainting;
import com.animania.common.entities.goats.GoatFainting.EntityDoeFainting;
import com.animania.common.entities.goats.GoatFainting.EntityKidFainting;
import com.animania.common.entities.goats.GoatKiko.EntityBuckKiko;
import com.animania.common.entities.goats.GoatKiko.EntityDoeKiko;
import com.animania.common.entities.goats.GoatKiko.EntityKidKiko;
import com.animania.common.entities.goats.GoatKinder.EntityBuckKinder;
import com.animania.common.entities.goats.GoatKinder.EntityDoeKinder;
import com.animania.common.entities.goats.GoatKinder.EntityKidKinder;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityBuckNigerianDwarf;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityDoeNigerianDwarf;
import com.animania.common.entities.goats.GoatNigerianDwarf.EntityKidNigerianDwarf;
import com.animania.common.entities.goats.GoatPygmy.EntityBuckPygmy;
import com.animania.common.entities.goats.GoatPygmy.EntityDoePygmy;
import com.animania.common.entities.goats.GoatPygmy.EntityKidPygmy;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.common.entities.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.common.entities.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.common.entities.horses.HorseType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeachickBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeacockBlue;
import com.animania.common.entities.peacocks.PeafowlBlue.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeachickOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeacockOpal;
import com.animania.common.entities.peacocks.PeafowlOpal.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeachickPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeacockPeach;
import com.animania.common.entities.peacocks.PeafowlPeach.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeachickPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeacockPurple;
import com.animania.common.entities.peacocks.PeafowlPurple.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeachickWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeacockWhite;
import com.animania.common.entities.peacocks.PeafowlWhite.EntityPeafowlWhite;
import com.animania.common.entities.pigs.PigDuroc.EntityHogDuroc;
import com.animania.common.entities.pigs.PigDuroc.EntityPigletDuroc;
import com.animania.common.entities.pigs.PigDuroc.EntitySowDuroc;
import com.animania.common.entities.pigs.PigHampshire.EntityHogHampshire;
import com.animania.common.entities.pigs.PigHampshire.EntityPigletHampshire;
import com.animania.common.entities.pigs.PigHampshire.EntitySowHampshire;
import com.animania.common.entities.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.common.entities.pigs.PigLargeBlack.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.common.entities.pigs.PigLargeWhite.EntityHogLargeWhite;
import com.animania.common.entities.pigs.PigLargeWhite.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.PigLargeWhite.EntitySowLargeWhite;
import com.animania.common.entities.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntityPigletOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntityPigletYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityTiller;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.common.entities.rodents.FerretType;
import com.animania.common.entities.rodents.HamsterType;
import com.animania.common.entities.rodents.HedgehogType;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitCottonail.EntityRabbitKitCottontail;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.common.entities.rodents.rabbits.RabbitDutch.EntityRabbitKitDutch;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.RabbitHavana.EntityRabbitKitHavana;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.common.entities.rodents.rabbits.RabbitJack.EntityRabbitKitJack;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.common.entities.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitNewZealand.EntityRabbitKitNewZealand;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.common.entities.rodents.rabbits.RabbitRex.EntityRabbitKitRex;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.SheepDorper.EntityEweDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityLambDorper;
import com.animania.common.entities.sheep.SheepDorper.EntityRamDorper;
import com.animania.common.entities.sheep.SheepDorset.EntityEweDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityLambDorset;
import com.animania.common.entities.sheep.SheepDorset.EntityRamDorset;
import com.animania.common.entities.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.common.entities.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.common.entities.sheep.SheepJacob.EntityEweJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityLambJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityRamJacob;
import com.animania.common.entities.sheep.SheepMerino.EntityEweMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityLambMerino;
import com.animania.common.entities.sheep.SheepMerino.EntityRamMerino;
import com.animania.common.entities.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.common.entities.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.common.entities.sheep.SheepType;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Lists;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler
{

	public static int entityID = 0;

	
	public static void preInit()
	{


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
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.draftHorseBiomeTypes)) {
				EntityRegistry.addSpawn(EntityMareDraftHorse.class, AnimaniaConfig.spawn.spawnProbabilityHorses, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
		}

		// RODENTS
		RegistryHelper.Entities.registerAnimal(EntityHamster.class, "hamster", entityID++, HamsterType.STANDARD, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityFerretGrey.class, "ferret_grey", entityID++, FerretType.GREY, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFerretWhite.class, "ferret_white", entityID++, FerretType.WHITE, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityHedgehog.class, "hedgehog", entityID++, HedgehogType.NORMAL, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityHedgehogAlbino.class, "hedgehog_albino", entityID++, HedgehogType.ALBINO, EntityGender.NONE);
		if (AnimaniaConfig.spawn.spawnAnimaniaRodents)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.hamsterBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHamster.class, AnimaniaConfig.spawn.spawnProbabilityHamsters, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.ferretGrayBiomeTypes)) {
				EntityRegistry.addSpawn(EntityFerretGrey.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.ferretWhiteBiomeTypes)) {
				EntityRegistry.addSpawn(EntityFerretWhite.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.hedgehogBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHedgehog.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.hedgehogAlbinoBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHedgehogAlbino.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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

		maxFam = AnimaniaConfig.spawn.numberPigFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaPigs)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigYorkshireBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowYorkshire.class, AnimaniaConfig.spawn.spawnProbabilityPigs/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigOldSpotBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowOldSpot.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigLargeBlackBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowLargeBlack.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigLargeWhiteBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowLargeWhite.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigDurocBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowDuroc.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.pigHampshireBiomeTypes)) {
				EntityRegistry.addSpawn(EntitySowHampshire.class, AnimaniaConfig.spawn.spawnProbabilityPigs, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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

		maxFam = AnimaniaConfig.spawn.numberChickenFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}
		
		if (AnimaniaConfig.spawn.spawnAnimaniaChickens)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenPlymouthRockBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHenPlymouthRock.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenLeghornBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHenLeghorn.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenOrpingtonBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHenOrpington.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenWyandotteBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHenWyandotte.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.chickenRhodeIslandRedBiomeTypes)) {
				EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, AnimaniaConfig.spawn.spawnProbabilityChickens, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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

		
		maxFam = AnimaniaConfig.spawn.numberCowFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}
		
		if (AnimaniaConfig.spawn.spawnAnimaniaCows)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHolsteinBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowHolstein.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowFriesianBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowFriesian.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowAngusBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHerefordBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowHereford.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowHighlandBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowHighland.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowJerseyBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowJersey.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowLonghornBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowLonghorn.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.cowMooshroomBiomeTypes)) {
				EntityRegistry.addSpawn(EntityCowMooshroom.class, AnimaniaConfig.spawn.spawnProbabilityCows, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
		}

		// AMPHIBIANS
		RegistryHelper.Entities.registerAnimal(EntityToad.class, "toad", entityID++, AmphibianType.TOAD, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFrogs.class, "frog", entityID++, AmphibianType.FROG, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityDartFrogs.class, "dartfrog", entityID++, AmphibianType.DART_FROG, EntityGender.NONE, false);

		if (AnimaniaConfig.spawn.spawnAnimaniaAmphibians)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.toadBiomeTypes)) {
				EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.frogBiomeTypes)) {
				EntityRegistry.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.dartFrogBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDartFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
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

		maxFam = AnimaniaConfig.spawn.numberGoatFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}


		if (AnimaniaConfig.spawn.spawnAnimaniaGoats)
		{	
			
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatAlpineBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatAngoraBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeAngora.class, AnimaniaConfig.spawn.spawnProbabilityGoats/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatFaintingBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeFainting.class, AnimaniaConfig.spawn.spawnProbabilityGoats/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatKikoBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatKinderBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatNigerianDwarfBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.goatPygmyBiomeTypes)) {
				EntityRegistry.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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

		maxFam = AnimaniaConfig.spawn.numberSheepFamilies;
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaSheep)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorsetBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweDorset.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepFriesianBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweFriesian.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepJacobBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweJacob.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepMerinoBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweMerino.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepSuffolkBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweSuffolk.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.sheepDorperBiomeTypes)) {
				EntityRegistry.addSpawn(EntityEweDorper.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
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
		if (maxFam < 2) {
			maxFam = 2;
		}

		if (AnimaniaConfig.spawn.spawnAnimaniaRabbits)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitCottontailBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeCottontail.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitChinchillaBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeChinchilla.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitDutchBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeDutch.class, AnimaniaConfig.spawn.spawnProbabilityRabbits/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitHavanaBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeHavana.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitJackBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitNewZealandBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeNewZealand.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitRexBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeRex.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.rabbitLopBiomeTypes)) {
				EntityRegistry.addSpawn(EntityRabbitDoeLop.class, AnimaniaConfig.spawn.spawnProbabilityRabbits/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
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
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlCharcoalBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlOpalBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlPeachBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlPurpleBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlTaupeBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlBlueBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(AnimaniaConfig.spawnLocations.peafowlWhiteBiomeTypes)) {
				EntityRegistry.addSpawn(EntityPeacockWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeafowlWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				EntityRegistry.addSpawn(EntityPeachickWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks/2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			
		}

		// MOVING ENTITIES
		if (!AnimaniaConfig.gameRules.disableRollingVehicles) {
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

		if (BiomeDictionary.getBiomes(type).isEmpty()) {
			System.out.println(type.getName() + I18n.translateToLocal("text.error.invalidbiometype"));
		}
		
		return criteriaMet.toArray(new Biome[criteriaMet.size()]);
	}
	
	

}
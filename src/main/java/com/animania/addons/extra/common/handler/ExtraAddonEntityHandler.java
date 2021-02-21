package com.animania.addons.extra.common.handler;

import static com.animania.common.handler.EntityHandler.entityID;

import java.util.List;
import java.util.Set;

import com.animania.addons.extra.common.entity.amphibians.AmphibianType;
import com.animania.addons.extra.common.entity.amphibians.EntityDartFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.peafowl.PeacockType;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeachickBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeacockBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeachickCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeachickOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeacockOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeafowlOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeachickPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeacockPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeafowlPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeachickPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeacockPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeafowlPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeachickTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeachickWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeacockWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeafowlWhite;
import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.addons.extra.common.entity.rodents.EntityFerretWhite;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogAlbino;
import com.animania.addons.extra.common.entity.rodents.FerretType;
import com.animania.addons.extra.common.entity.rodents.HamsterType;
import com.animania.addons.extra.common.entity.rodents.HedgehogType;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitKitRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitType;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper;
import com.google.common.collect.Lists;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ExtraAddonEntityHandler
{
	public static void preInit()
	{
		int maxFam = 3;

		// RODENTS
		RegistryHelper.Entities.registerAnimal(EntityHamster.class, "hamster", entityID++, HamsterType.STANDARD, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityFerretGrey.class, "ferret_grey", entityID++, FerretType.GREY, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFerretWhite.class, "ferret_white", entityID++, FerretType.WHITE, EntityGender.NONE);

		RegistryHelper.Entities.registerAnimal(EntityHedgehog.class, "hedgehog", entityID++, HedgehogType.NORMAL, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityHedgehogAlbino.class, "hedgehog_albino", entityID++, HedgehogType.ALBINO, EntityGender.NONE);
		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.hamsterBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityHamster.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityHamsters, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.ferretGrayBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityFerretGrey.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityFerrets, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.ferretWhiteBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityFerretWhite.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityFerrets, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.hedgehogBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityHedgehog.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityHedgehogs, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.hedgehogAlbinoBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityHedgehogAlbino.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityHedgehogs, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
		}

		// AMPHIBIANS
		RegistryHelper.Entities.registerAnimal(EntityToad.class, "toad", entityID++, AmphibianType.TOAD, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityFrogs.class, "frog", entityID++, AmphibianType.FROG, EntityGender.NONE);
		RegistryHelper.Entities.registerAnimal(EntityDartFrogs.class, "dartfrog", entityID++, AmphibianType.DART_FROG, EntityGender.NONE, false);

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaAmphibians)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.toadBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityToad.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.frogBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityFrogs.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.dartFrogBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityDartFrogs.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityAmphibians + 10, 2, 2, EnumCreatureType.CREATURE, getBiomes(t));
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

		maxFam = ExtraConfig.settings.spawning_and_breeding.numberRabbitFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRabbits)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeCottontail.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeChinchilla.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitDutchBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeDutch.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeHavana.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitNewZealandBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeNewZealand.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitRexBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeRex.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitLopBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityRabbitDoeLop.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaPeacocks)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlCharcoalBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlOpalBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPeachBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPurpleBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlTaupeBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 1, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlBlueBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockBlue.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlBlue.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickBlue.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.peafowlWhiteBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(EntityPeacockWhite.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeafowlWhite.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
				RegistryHelper.Entities.addSpawn(EntityPeachickWhite.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityPeacocks / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}

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

		if (BiomeDictionary.getBiomes(type).isEmpty())
		{
			System.out.println(type.getName() + I18n.translateToLocal("text.error.invalidbiometype"));
		}

		return criteriaMet.toArray(new Biome[criteriaMet.size()]);
	}

}
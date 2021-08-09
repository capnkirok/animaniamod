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
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityKitChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityKitCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.RabbitEntityKitDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityKitHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityKitJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityKitLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.RabbitEntityKitNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityDoeRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.RabbitEntityKitRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitType;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper;
import com.google.common.collect.Lists;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

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
		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckCottontail.class, "buck_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeCottontail.class, "doe_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitCottontail.class, "kit_cottontail", entityID++, RabbitType.COTTONTAIL, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckChinchilla.class, "buck_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeChinchilla.class, "doe_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitChinchilla.class, "kit_chinchilla", entityID++, RabbitType.CHINCHILLA, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckDutch.class, "buck_dutch", entityID++, RabbitType.DUTCH, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeDutch.class, "doe_dutch", entityID++, RabbitType.DUTCH, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitDutch.class, "kit_dutch", entityID++, RabbitType.DUTCH, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckHavana.class, "buck_havana", entityID++, RabbitType.HAVANA, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeHavana.class, "doe_havana", entityID++, RabbitType.HAVANA, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitHavana.class, "kit_havana", entityID++, RabbitType.HAVANA, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckJack.class, "buck_jack", entityID++, RabbitType.JACK, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeJack.class, "doe_jack", entityID++, RabbitType.JACK, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitJack.class, "kit_jack", entityID++, RabbitType.JACK, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckNewZealand.class, "buck_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeNewZealand.class, "doe_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitNewZealand.class, "kit_new_zealand", entityID++, RabbitType.NEW_ZEALAND, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckRex.class, "buck_rex", entityID++, RabbitType.REX, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeRex.class, "doe_rex", entityID++, RabbitType.REX, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitRex.class, "kit_rex", entityID++, RabbitType.REX, EntityGender.CHILD);

		RegistryHelper.Entities.registerAnimal(RabbitEntityBuckLop.class, "buck_lop", entityID++, RabbitType.LOP, EntityGender.MALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityDoeLop.class, "doe_lop", entityID++, RabbitType.LOP, EntityGender.FEMALE);
		RegistryHelper.Entities.registerAnimal(RabbitEntityKitLop.class, "kit_lop", entityID++, RabbitType.LOP, EntityGender.CHILD);

		maxFam = ExtraConfig.settings.spawning_and_breeding.numberRabbitFamilies;
		if (maxFam < 2)
		{
			maxFam = 2;
		}

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRabbits)
		{
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeCottontail.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeChinchilla.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitDutchBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeDutch.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeHavana.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeJack.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitNewZealandBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeNewZealand.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitRexBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeRex.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
			}
			for (Type t : AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitLopBiomeTypes))
			{
				RegistryHelper.Entities.addSpawn(RabbitEntityDoeLop.class, ExtraConfig.settings.spawning_and_breeding.spawnProbabilityRabbits / 2, 2, maxFam, EnumCreatureType.CREATURE, getBiomes(t));
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
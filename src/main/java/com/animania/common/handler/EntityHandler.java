package com.animania.common.handler;

import java.util.List;
import java.util.Set;

import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
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
import com.animania.common.entities.peacocks.EntityPeachickCharcoal;
import com.animania.common.entities.peacocks.EntityPeachickOpal;
import com.animania.common.entities.peacocks.EntityPeachickPeach;
import com.animania.common.entities.peacocks.EntityPeachickPurple;
import com.animania.common.entities.peacocks.EntityPeachickTaupe;
import com.animania.common.entities.peacocks.EntityPeacockCharcoal;
import com.animania.common.entities.peacocks.EntityPeacockOpal;
import com.animania.common.entities.peacocks.EntityPeacockPeach;
import com.animania.common.entities.peacocks.EntityPeacockPurple;
import com.animania.common.entities.peacocks.EntityPeacockTaupe;
import com.animania.common.entities.peacocks.EntityPeafowlCharcoal;
import com.animania.common.entities.peacocks.EntityPeafowlOpal;
import com.animania.common.entities.peacocks.EntityPeafowlPeach;
import com.animania.common.entities.peacocks.EntityPeafowlPurple;
import com.animania.common.entities.peacocks.EntityPeafowlTaupe;
import com.animania.common.entities.props.EntityWagon;
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
import com.animania.common.helper.RegistryHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Lists;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler
{

	public static void preInit()
	{


		int entityID = 0;


		//AMPHIBIANS
		if (AnimaniaConfig.spawn.spawnAnimaniaAmphibians) {

			RegistryHelper.Entities.register(EntityToad.class, "toad", entityID++, 64, 3, true);
			EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.SWAMP));
			EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.register(EntityFrogs.class, "frog", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 1, EnumCreatureType.AMBIENT,
					getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT,
					getBiomes(BiomeDictionary.Type.RIVER));
			RegistryHelper.Entities.register(EntityDartFrogs.class, "dartfrog", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.JUNGLE));
		}

		//GOATS

		if (AnimaniaConfig.spawn.spawnAnimaniaGoats)
		{
			RegistryHelper.Entities.register(EntityKidAlpine.class, "kid_alpine", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckAlpine.class, "buck_alpine", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeAlpine.class, "doe_alpine", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));

			RegistryHelper.Entities.register(EntityKidAngora.class, "kid_angora", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckAngora.class, "buck_angora", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeAngora.class, "doe_angora", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeAngora.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));

			RegistryHelper.Entities.register(EntityKidFainting.class, "kid_fainting", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckFainting.class, "buck_fainting", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeFainting.class, "doe_fainting", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeFainting.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));

			RegistryHelper.Entities.register(EntityKidKiko.class, "kid_kiko", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckKiko.class, "buck_kiko", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeKiko.class, "doe_kiko", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
			RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));

			RegistryHelper.Entities.register(EntityKidKinder.class, "kid_kinder", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckKinder.class, "buck_kinder", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeKinder.class, "doe_kinder", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));

			RegistryHelper.Entities.register(EntityKidNigerianDwarf.class, "kid_nigerian_dwarf", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckNigerianDwarf.class, "buck_nigerian_dwarf", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoeNigerianDwarf.class, "doe_nigerian_dwarf", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SANDY));

			RegistryHelper.Entities.register(EntityKidPygmy.class, "kid_pygmy", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityBuckPygmy.class, "buck_pygmy", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityDoePygmy.class, "doe_pygmy", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
		}

		//SHEEP
		if (AnimaniaConfig.spawn.spawnAnimaniaSheep) {
			RegistryHelper.Entities.register(EntityLambFriesian.class, "lamb_friesian", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweFriesian.class, "ewe_friesian", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamFriesian.class, "ram_friesian", entityID++, 64, 3, true);

			RegistryHelper.Entities.register(EntityLambSuffolk.class, "lamb_suffolk", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweSuffolk.class, "ewe_suffolk", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamSuffolk.class, "ram_suffolk", entityID++, 64, 3, true);
			
			RegistryHelper.Entities.register(EntityLambDorper.class, "lamb_dorper", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweDorper.class, "ewe_dorper", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamDorper.class, "ram_dorper", entityID++, 64, 3, true);
			
			RegistryHelper.Entities.register(EntityLambDorset.class, "lamb_dorset", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweDorset.class, "ewe_dorset", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamDorset.class, "ram_dorset", entityID++, 64, 3, true);
			
			RegistryHelper.Entities.register(EntityLambMerino.class, "lamb_merino", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweMerino.class, "ewe_merino", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamMerino.class, "ram_merino", entityID++, 64, 3, true);
			
			RegistryHelper.Entities.register(EntityLambJacob.class, "lamb_jacob", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityEweJacob.class, "ewe_jacob", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRamJacob.class, "ram_jacob", entityID++, 64, 3, true);

			RegistryHelper.Entities.addSpawn(EntityEweDorset.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweDorset.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityEweFriesian.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweJacob.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweMerino.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityEweMerino.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityEweSuffolk.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
			RegistryHelper.Entities.addSpawn(EntityEweSuffolk.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			RegistryHelper.Entities.addSpawn(EntityEweDorper.class, AnimaniaConfig.spawn.spawnProbabilitySheep, 1, AnimaniaConfig.spawn.numberSheepFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
			
			
		}
		
		//RABBITS
		if (AnimaniaConfig.spawn.spawnAnimaniaRabbits) {
			RegistryHelper.Entities.register(EntityRabbitBuckCottontail.class, "buck_cottontail", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeCottontail.class, "doe_cottontail", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitCottontail.class, "kit_cottontail", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeCottontail.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeCottontail.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));

			RegistryHelper.Entities.register(EntityRabbitBuckChinchilla.class, "buck_chinchilla", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeChinchilla.class, "doe_chinchilla", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitChinchilla.class, "kit_chinchilla", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeChinchilla.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SNOWY));


			RegistryHelper.Entities.register(EntityRabbitBuckDutch.class, "buck_dutch", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeDutch.class, "doe_dutch", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitDutch.class, "kit_dutch", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeDutch.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));


			RegistryHelper.Entities.register(EntityRabbitBuckHavana.class, "buck_havana", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeHavana.class, "doe_havana", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitHavana.class, "kit_havana", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeHavana.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeHavana.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));

			RegistryHelper.Entities.register(EntityRabbitBuckJack.class, "buck_jack", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeJack.class, "doe_jack", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitJack.class, "kit_jack", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SANDY));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeJack.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));

			RegistryHelper.Entities.register(EntityRabbitBuckNewZealand.class, "buck_new_zealand", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeNewZealand.class, "doe_new_zealand", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitNewZealand.class, "kit_new_zealand", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeNewZealand.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeNewZealand.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));

			RegistryHelper.Entities.register(EntityRabbitBuckRex.class, "buck_rex", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeRex.class, "doe_rex", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitRex.class, "kit_rex", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeRex.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));

			RegistryHelper.Entities.register(EntityRabbitBuckLop.class, "buck_lop", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitDoeLop.class, "doe_lop", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityRabbitKitLop.class, "kit_lop", entityID++, 64, 3, true);
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeLop.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));
			RegistryHelper.Entities.addSpawn(EntityRabbitDoeLop.class, AnimaniaConfig.spawn.spawnProbabilityRabbits, 1, AnimaniaConfig.spawn.numberRabbitFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.FOREST));

		}

		// PEACOCKS (NEW)
		if (AnimaniaConfig.spawn.spawnAnimaniaPeacocks) { 

			RegistryHelper.Entities.register(EntityPeachickCharcoal.class, "peachick_charcoal", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeafowlCharcoal.class, "peahen_charcoal", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeacockCharcoal.class, "peacock_charcoal", entityID++, 64, 3, true);

			RegistryHelper.Entities.register(EntityPeachickOpal.class, "peachick_opal", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeafowlOpal.class, "peahen_opal", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeacockOpal.class, "peacock_opal", entityID++, 64, 3, true);

			RegistryHelper.Entities.register(EntityPeachickPeach.class, "peachick_peach", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeafowlPeach.class, "peahen_peach", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeacockPeach.class, "peacock_peach", entityID++, 64, 3, true);

			RegistryHelper.Entities.register(EntityPeachickPurple.class, "peachick_purple", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeafowlPurple.class, "peahen_purple", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeacockPurple.class, "peacock_purple", entityID++, 64, 3, true);

			RegistryHelper.Entities.register(EntityPeachickTaupe.class, "peachick_taupe", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeafowlTaupe.class, "peahen_taupe", entityID++, 64, 3, true);
			RegistryHelper.Entities.register(EntityPeacockTaupe.class, "peacock_taupe", entityID++, 64, 3, true);


			RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

			RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
			RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
			RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

		}

		//MOVING ENTITIES
	//	RegistryHelper.Entities.register(EntityWagon.class, "wagon", entityID++, 40, 1, true);

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
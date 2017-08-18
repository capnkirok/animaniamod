package com.animania.common.handler;

import java.util.List;
import java.util.Set;

import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.goats.EntityBuckAlpine;
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

	public static void preInit() {

		/*
		 * // COWS if (AnimaniaConfig.spawn.spawnAnimaniaCows) {
		 * RegistryHelper.Entities.register(EntityCowHolstein.class,
		 * "animania.CowHolstein", AnimaniaConfig.entity.CowHolsteinID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityBullHolstein.class,
		 * "animania.BullHolstein", AnimaniaConfig.entity.BullHolsteinID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityCalfHolstein.class,
		 * "animania.CalfHolstein", AnimaniaConfig.entity.CalfHolsteinID, 64, 3,
		 * true); RegistryHelper.Entities.addSpawn(EntityCowHolstein.class,
		 * AnimaniaConfig.spawn.spawnProbabilityCows, 1,
		 * AnimaniaConfig.spawn.numberCowFamilies, EnumCreatureType.CREATURE,
		 * Biomes.FOREST);
		 *
		 * RegistryHelper.Entities.register(EntityCowFriesian.class,
		 * "animania.CowFriesian", AnimaniaConfig.entity.CowFriesianID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityBullFriesian.class,
		 * "animania.BullFriesian", AnimaniaConfig.entity.BullFriesianID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityCalfFriesian.class,
		 * "animania.CalfFriesian", AnimaniaConfig.entity.CalfFriesianID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityCowFriesian.class,
		 * AnimaniaConfig.spawn.spawnProbabilityCows, 1,
		 * AnimaniaConfig.spawn.numberCowFamilies, EnumCreatureType.CREATURE,
		 * Biomes.PLAINS);
		 *
		 * RegistryHelper.Entities.register(EntityCowAngus.class,
		 * "animania.CowAngus", AnimaniaConfig.entity.CowAngusID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityBullAngus.class,
		 * "animania.BullAngus", AnimaniaConfig.entity.BullAngusID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityCalfAngus.class,
		 * "animania.CalfAngus", AnimaniaConfig.entity.CalfAngusID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityCowAngus.class,
		 * AnimaniaConfig.spawn.spawnProbabilityCows, 1,
		 * AnimaniaConfig.spawn.numberCowFamilies, EnumCreatureType.CREATURE,
		 * Biomes.JUNGLE, Biomes.MESA, Biomes.MUSHROOM_ISLAND);
		 *
		 * RegistryHelper.Entities.register(EntityCowLonghorn.class,
		 * "animania.CowLonghorn", AnimaniaConfig.entity.CowLonghornID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityBullLonghorn.class,
		 * "animania.BullLonghorn", AnimaniaConfig.entity.BullLonghornID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityCalfLonghorn.class,
		 * "animania.CalfLonghorn", AnimaniaConfig.entity.CalfLonghornID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityCowLonghorn.class,
		 * AnimaniaConfig.spawn.spawnProbabilityCows, 1,
		 * AnimaniaConfig.spawn.numberCowFamilies, EnumCreatureType.CREATURE,
		 * Biomes.SAVANNA);
		 *
		 * RegistryHelper.Entities.register(EntityCowHereford.class,
		 * "animania.CowHereford", AnimaniaConfig.entity.CowHerefordID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityBullHereford.class,
		 * "animania.BullHereford", AnimaniaConfig.entity.BullHerefordID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityCalfHereford.class,
		 * "animania.CalfHereford", AnimaniaConfig.entity.CalfHerefordID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityCowHereford.class,
		 * AnimaniaConfig.spawn.spawnProbabilityCows, 1,
		 * AnimaniaConfig.spawn.numberCowFamilies, EnumCreatureType.CREATURE,
		 * Biomes.FOREST_HILLS); }
		 *
		 * // CHICKENS if (AnimaniaConfig.spawn.spawnAnimaniaChickens) {
		 * RegistryHelper.Entities.register(EntityHenPlymouthRock.class,
		 * "animania.HenPlymouthRock", AnimaniaConfig.entity.HenPlymouthRockID,
		 * 64, 3, true); EntityRegistry.addSpawn(EntityHenPlymouthRock.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHens, 1,
		 * AnimaniaConfig.spawn.numberChickenFamilies,
		 * EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS_WITH_TREES,
		 * Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_EDGE,
		 * Biomes.FOREST_HILLS);
		 *
		 * RegistryHelper.Entities.register(EntityRoosterPlymouthRock.class,
		 * "animania.RoosterPlymouthRock",
		 * AnimaniaConfig.entity.RoosterPlymouthRockID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityChickPlymouthRock.class,
		 * "animania.ChickPlymouthRock",
		 * AnimaniaConfig.entity.ChickPlymouthRockID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHenLeghorn.class,
		 * "animania.HenLeghorn", AnimaniaConfig.entity.HenLeghornID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityHenLeghorn.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHens, 1,
		 * AnimaniaConfig.spawn.numberChickenFamilies,
		 * EnumCreatureType.CREATURE, Biomes.PLAINS);
		 *
		 * RegistryHelper.Entities.register(EntityRoosterLeghorn.class,
		 * "animania.RoosterLeghorn", AnimaniaConfig.entity.RoosterLeghornID,
		 * 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityChickLeghorn.class,
		 * "animania.ChickLeghorn", AnimaniaConfig.entity.ChickLeghornID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityHenOrpington.class,
		 * "animania.HenOrpington", AnimaniaConfig.entity.HenOrpingtonID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityHenOrpington.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHens, 1,
		 * AnimaniaConfig.spawn.numberChickenFamilies,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 *
		 * RegistryHelper.Entities.register(EntityRoosterOrpington.class,
		 * "animania.RoosterOrpington",
		 * AnimaniaConfig.entity.RoosterOrpingtonID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityChickOrpington.class,
		 * "animania.ChickOrpington", AnimaniaConfig.entity.ChickOrpingtonID,
		 * 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHenWyandotte.class,
		 * "animania.HenWyandotte", AnimaniaConfig.entity.HenWyandotteID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityHenWyandotte.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHens, 1,
		 * AnimaniaConfig.spawn.numberChickenFamilies,
		 * EnumCreatureType.CREATURE, Biomes.FOREST);
		 *
		 * RegistryHelper.Entities.register(EntityRoosterWyandotte.class,
		 * "animania.RoosterWyandotte",
		 * AnimaniaConfig.entity.RoosterWyandotteID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityChickWyandotte.class,
		 * "animania.ChickWyandotte", AnimaniaConfig.entity.ChickWyandotteID,
		 * 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHenRhodeIslandRed.class,
		 * "animania.HenRhodeIslandRed",
		 * AnimaniaConfig.entity.HenRhodeIslandRedID, 64, 3, true);
		 * EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHens, 1,
		 * AnimaniaConfig.spawn.numberChickenFamilies,
		 * EnumCreatureType.CREATURE, Biomes.FOREST);
		 *
		 * RegistryHelper.Entities.register(EntityRoosterRhodeIslandRed.class,
		 * "animania.RoosterRhodeIslandRed",
		 * AnimaniaConfig.entity.RoosterRhodeIslandRedID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityChickRhodeIslandRed.class,
		 * "animania.ChickRhodeIslandRed",
		 * AnimaniaConfig.entity.ChickRhodeIslandRedID, 64, 3, true); }
		 *
		 * // PIGS if (AnimaniaConfig.spawn.spawnAnimaniaPigs) {
		 * RegistryHelper.Entities.register(EntitySowYorkshire.class,
		 * "animania.SowYorkshire", AnimaniaConfig.entity.SowYorkshireID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityHogYorkshire.class,
		 * "animania.HogYorkshire", AnimaniaConfig.entity.HogYorkshireID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletYorkshire.class,
		 * "animania.PigletYorkshire", AnimaniaConfig.entity.PigletYorkshireID,
		 * 64, 3, true); EntityRegistry.addSpawn(EntitySowYorkshire.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.PLAINS);
		 *
		 * RegistryHelper.Entities.register(EntitySowOldSpot.class,
		 * "animania.SowOldSpot", AnimaniaConfig.entity.SowOldSpotID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityHogOldSpot.class,
		 * "animania.HogOldSpot", AnimaniaConfig.entity.HogOldSpotID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletOldSpot.class,
		 * "animania.PigletOldSpot", AnimaniaConfig.entity.PigletOldSpotID, 64,
		 * 3, true); EntityRegistry.addSpawn(EntitySowOldSpot.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.FOREST);
		 *
		 * RegistryHelper.Entities.register(EntitySowLargeBlack.class,
		 * "animania.SowLargeBlack", AnimaniaConfig.entity.SowLargeBlackID, 64,
		 * 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHogLargeBlack.class,
		 * "animania.HogLargeBlack", AnimaniaConfig.entity.HogLargeBlackID, 64,
		 * 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletLargeBlack.class,
		 * "animania.PigletLargeBlack",
		 * AnimaniaConfig.entity.PigletLargeBlackID, 64, 3, true);
		 * EntityRegistry.addSpawn(EntitySowLargeBlack.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.SWAMPLAND, Biomes.BIRCH_FOREST);
		 *
		 * RegistryHelper.Entities.register(EntitySowLargeWhite.class,
		 * "animania.SowLargeWhite", AnimaniaConfig.entity.SowLargeWhiteID, 64,
		 * 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHogLargeWhite.class,
		 * "animania.HogLargeWhite", AnimaniaConfig.entity.HogLargeWhiteID, 64,
		 * 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletLargeWhite.class,
		 * "animania.PigletLargeWhite",
		 * AnimaniaConfig.entity.PigletLargeWhiteID, 64, 3, true);
		 * EntityRegistry.addSpawn(EntitySowLargeWhite.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.FOREST_HILLS, Biomes.BIRCH_FOREST_HILLS);
		 *
		 * RegistryHelper.Entities.register(EntitySowDuroc.class,
		 * "animania.SowDuroc", AnimaniaConfig.entity.SowDurocID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityHogDuroc.class,
		 * "animania.HogDuroc", AnimaniaConfig.entity.HogDurocID, 64, 3, true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletDuroc.class,
		 * "animania.PigletDuroc", AnimaniaConfig.entity.PigletDurocID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntitySowDuroc.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.JUNGLE);
		 *
		 * RegistryHelper.Entities.register(EntitySowHampshire.class,
		 * "animania.SowHampshire", AnimaniaConfig.entity.SowHampshireID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityHogHampshire.class,
		 * "animania.HogHampshire", AnimaniaConfig.entity.HogHampshireID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPigletHampshire.class,
		 * "animania.PigletHampshire", AnimaniaConfig.entity.PigletHampshireID,
		 * 64, 3, true); EntityRegistry.addSpawn(EntitySowHampshire.class,
		 * AnimaniaConfig.spawn.spawnProbabilitySows, 1,
		 * AnimaniaConfig.spawn.numberPigFamilies, EnumCreatureType.CREATURE,
		 * Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES); }
		 *
		 * // RODENTS if (AnimaniaConfig.spawn.spawnAnimaniaRodents) {
		 *
		 * RegistryHelper.Entities.register(EntityHamster.class,
		 * "animania.Hamster", AnimaniaConfig.entity.HamsterMaleID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityHamster.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHamsters, 1, 2,
		 * EnumCreatureType.CREATURE, Biomes.BEACH, Biomes.DESERT,
		 * Biomes.DESERT_HILLS);
		 *
		 * RegistryHelper.Entities.register(EntityFerretGrey.class,
		 * "animania.FerretGrey", AnimaniaConfig.entity.FerretMaleGreyID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityFerretWhite.class,
		 * "animania.FerretWhite", AnimaniaConfig.entity.FerretMaleWhiteID, 64,
		 * 3, true);
		 *
		 * EntityRegistry.addSpawn(EntityFerretGrey.class,
		 * AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2,
		 * EnumCreatureType.CREATURE, Biomes.SAVANNA);
		 * EntityRegistry.addSpawn(EntityFerretWhite.class,
		 * AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2,
		 * EnumCreatureType.CREATURE, Biomes.SAVANNA);
		 *
		 * RegistryHelper.Entities.register(EntityHedgehog.class,
		 * "animania.Hedgehog", AnimaniaConfig.entity.HedgehogMaleID, 64, 3,
		 * true); EntityRegistry.addSpawn(EntityHedgehog.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2,
		 * EnumCreatureType.CREATURE, Biomes.FOREST);
		 *
		 * RegistryHelper.Entities.register(EntityHedgehogAlbino.class,
		 * "animania.HedgehogAlbino",
		 * AnimaniaConfig.entity.HedgehogMaleAlbinoID, 64, 3, true);
		 * EntityRegistry.addSpawn(EntityHedgehogAlbino.class,
		 * AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2,
		 * EnumCreatureType.CREATURE, Biomes.SWAMPLAND); }
		 *
		 * // PEACOCKS if (AnimaniaConfig.spawn.spawnAnimaniaPeacocks) {
		 * RegistryHelper.Entities.register(EntityPeacockBlue.class,
		 * "animania.PeacockBlue", AnimaniaConfig.entity.PeacockBlueID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPeafowlBlue.class,
		 * "animania.PeafowlBlue", AnimaniaConfig.entity.PeafowlBlueID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPeachickBlue.class,
		 * "animania.PeachickBlue", AnimaniaConfig.entity.PeachickBlueID, 64, 3,
		 * true);
		 *
		 * EntityRegistry.addSpawn(EntityPeacockBlue.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 * EntityRegistry.addSpawn(EntityPeafowlBlue.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 * EntityRegistry.addSpawn(EntityPeachickBlue.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 *
		 * RegistryHelper.Entities.register(EntityPeacockWhite.class,
		 * "animania.PeacockWhite", AnimaniaConfig.entity.PeacockWhiteID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPeafowlWhite.class,
		 * "animania.PeafowlWhite", AnimaniaConfig.entity.PeafowlWhiteID, 64, 3,
		 * true);
		 *
		 * RegistryHelper.Entities.register(EntityPeachickWhite.class,
		 * "animania.PeachickWhite", AnimaniaConfig.entity.PeachickWhiteID, 64,
		 * 3, true);
		 *
		 * EntityRegistry.addSpawn(EntityPeacockWhite.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 * EntityRegistry.addSpawn(EntityPeafowlWhite.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);
		 * EntityRegistry.addSpawn(EntityPeachickWhite.class,
		 * AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
		 * EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND); }
		 *
		 */

		/*
		 * //HORSES if (AnimaniaConfig.spawn.spawnAnimaniaHorses) {
		 * EntityRegistry.registerModEntity(EntityStallionDraftHorse.class,
		 * "animania.StallionDraftHorse",
		 * AnimaniaConfig.entity.StallionDraftHorseID, this, 64, 3, true); }
		 */

		int entityID = 0;
		RegistryHelper.Entities.register(EntityFrogs.class, "frog", entityID++, 64, 3, true);
		RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 1, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.SWAMP));
		RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.RIVER));
		
		RegistryHelper.Entities.register(EntityToad.class, "toad", entityID++, 64, 3, true);
		EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.SWAMP));
		EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.JUNGLE));
	
		RegistryHelper.Entities.register(EntityDartFrogs.class, "dartfrog", entityID++, 64, 3, true);
		RegistryHelper.Entities.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.JUNGLE));
	
		RegistryHelper.Entities.register(EntityBuckAlpine.class, "buck_alpine", entityID++, 64, 3, true);
		RegistryHelper.Entities.addSpawn(EntityBuckAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1, AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.AMBIENT, getBiomes(BiomeDictionary.Type.MOUNTAIN));

	}

	private static Biome[] getBiomes(BiomeDictionary.Type type) {
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
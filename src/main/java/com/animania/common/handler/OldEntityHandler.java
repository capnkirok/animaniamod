package com.animania.common.handler;

import com.animania.Animania;
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
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
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
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class OldEntityHandler
{

    public static void preInit() {
        // COWS
        if (AnimaniaConfig.spawn.spawnAnimaniaCows) {
            ResourceLocation ch1 = new ResourceLocation("animania:textures/entity/cows/cow_holstein.png");
            EntityRegistry.registerModEntity(ch1, EntityCowHolstein.class, "animania.CowHolstein", AnimaniaConfig.entity.CowHolsteinID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation ch2 = new ResourceLocation("animania:textures/entity/cows/bull_holstein.png");
            EntityRegistry.registerModEntity(ch2, EntityBullHolstein.class, "animania.BullHolstein", AnimaniaConfig.entity.BullHolsteinID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation ch3 = new ResourceLocation("animania:textures/entity/cows/calf_holstein.png");
            EntityRegistry.registerModEntity(ch3, EntityCalfHolstein.class, "animania.CalfHolstein", AnimaniaConfig.entity.CalfHolsteinID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityCowHolstein.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, AnimaniaConfig.spawn.numberCowFamilies,
                    EnumCreatureType.CREATURE, Biomes.FOREST);

            ResourceLocation cf1 = new ResourceLocation("animania:textures/entity/cows/cow_friesian.png");
            EntityRegistry.registerModEntity(cf1, EntityCowFriesian.class, "animania.CowFriesian", AnimaniaConfig.entity.CowFriesianID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation cf2 = new ResourceLocation("animania:textures/entity/cows/bull_friesian.png");
            EntityRegistry.registerModEntity(cf2, EntityBullFriesian.class, "animania.BullFriesian", AnimaniaConfig.entity.BullFriesianID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation cf3 = new ResourceLocation("animania:textures/entity/cows/calf_friesian.png");
            EntityRegistry.registerModEntity(cf3, EntityCalfFriesian.class, "animania.CalfFriesian", AnimaniaConfig.entity.CalfFriesianID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityCowFriesian.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, AnimaniaConfig.spawn.numberCowFamilies,
                    EnumCreatureType.CREATURE, Biomes.PLAINS);

            ResourceLocation ca1 = new ResourceLocation("animania:textures/entity/cows/cow_angus.png");
            EntityRegistry.registerModEntity(ca1, EntityCowAngus.class, "animania.CowAngus", AnimaniaConfig.entity.CowAngusID, Animania.MODID, 64, 3,
                    true);

            ResourceLocation ca2 = new ResourceLocation("animania:textures/entity/cows/bull_angus.png");
            EntityRegistry.registerModEntity(ca2, EntityBullAngus.class, "animania.BullAngus", AnimaniaConfig.entity.BullAngusID, Animania.MODID, 64,
                    3, true);

            ResourceLocation ca3 = new ResourceLocation("animania:textures/entity/cows/calf_angus.png");
            EntityRegistry.registerModEntity(ca3, EntityCalfAngus.class, "animania.CalfAngus", AnimaniaConfig.entity.CalfAngusID, Animania.MODID, 64,
                    3, true);
            EntityRegistry.addSpawn(EntityCowAngus.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, AnimaniaConfig.spawn.numberCowFamilies,
                    EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.MESA, Biomes.MUSHROOM_ISLAND);

            ResourceLocation cl1 = new ResourceLocation("animania:textures/entity/cows/cow_longhorn.png");
            EntityRegistry.registerModEntity(cl1, EntityCowLonghorn.class, "animania.CowLonghorn", AnimaniaConfig.entity.CowLonghornID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation cl2 = new ResourceLocation("animania:textures/entity/cows/bull_longhorn.png");
            EntityRegistry.registerModEntity(cl2, EntityBullLonghorn.class, "animania.BullLonghorn", AnimaniaConfig.entity.BullLonghornID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation cl3 = new ResourceLocation("animania:textures/entity/cows/calf_longhorn.png");
            EntityRegistry.registerModEntity(cl3, EntityCalfLonghorn.class, "animania.CalfLonghorn", AnimaniaConfig.entity.CalfLonghornID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityCowLonghorn.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, AnimaniaConfig.spawn.numberCowFamilies,
                    EnumCreatureType.CREATURE, Biomes.SAVANNA);

            ResourceLocation che1 = new ResourceLocation("animania:textures/entity/cows/cow_hereford.png");
            EntityRegistry.registerModEntity(che1, EntityCowHereford.class, "animania.CowHereford", AnimaniaConfig.entity.CowHerefordID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation che2 = new ResourceLocation("animania:textures/entity/cows/bull_hereford.png");
            EntityRegistry.registerModEntity(che2, EntityBullHereford.class, "animania.BullHereford", AnimaniaConfig.entity.BullHerefordID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation che3 = new ResourceLocation("animania:textures/entity/cows/calf_hereford.png");
            EntityRegistry.registerModEntity(che3, EntityCalfHereford.class, "animania.CalfHereford", AnimaniaConfig.entity.CalfHerefordID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityCowHereford.class, AnimaniaConfig.spawn.spawnProbabilityCows, 1, AnimaniaConfig.spawn.numberCowFamilies,
                    EnumCreatureType.CREATURE, Biomes.FOREST_HILLS);
        }

        // CHICKENS
        if (AnimaniaConfig.spawn.spawnAnimaniaChickens) {
            ResourceLocation chp1 = new ResourceLocation("animania:textures/entity/chickens/hen_specked.png");
            EntityRegistry.registerModEntity(chp1, EntityHenPlymouthRock.class, "animania.HenPlymouthRock", AnimaniaConfig.entity.HenPlymouthRockID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityHenPlymouthRock.class, AnimaniaConfig.spawn.spawnProbabilityHens, 1,
                    AnimaniaConfig.spawn.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.EXTREME_HILLS,
                    Biomes.EXTREME_HILLS_EDGE, Biomes.FOREST_HILLS);

            ResourceLocation chp2 = new ResourceLocation("animania:textures/entity/chickens/rooster_specked.png");
            EntityRegistry.registerModEntity(chp2, EntityRoosterPlymouthRock.class, "animania.RoosterPlymouthRock",
                    AnimaniaConfig.entity.RoosterPlymouthRockID, Animania.MODID, 64, 3, true);

            ResourceLocation chp3 = new ResourceLocation("animania:textures/entity/chickens/chick_specked.png");
            EntityRegistry.registerModEntity(chp3, EntityChickPlymouthRock.class, "animania.ChickPlymouthRock",
                    AnimaniaConfig.entity.ChickPlymouthRockID, Animania.MODID, 64, 3, true);

            ResourceLocation chl1 = new ResourceLocation("animania:textures/entity/chickens/hen_white.png");
            EntityRegistry.registerModEntity(chl1, EntityHenLeghorn.class, "animania.HenLeghorn", AnimaniaConfig.entity.HenLeghornID, Animania.MODID,
                    64, 3, true);
            EntityRegistry.addSpawn(EntityHenLeghorn.class, AnimaniaConfig.spawn.spawnProbabilityHens, 1, AnimaniaConfig.spawn.numberChickenFamilies,
                    EnumCreatureType.CREATURE, Biomes.PLAINS);

            ResourceLocation chl2 = new ResourceLocation("animania:textures/entity/chickens/rooster_white.png");
            EntityRegistry.registerModEntity(chl2, EntityRoosterLeghorn.class, "animania.RoosterLeghorn", AnimaniaConfig.entity.RoosterLeghornID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation chl3 = new ResourceLocation("animania:textures/entity/chickens/chick_white.png");
            EntityRegistry.registerModEntity(chl3, EntityChickLeghorn.class, "animania.ChickLeghorn", AnimaniaConfig.entity.ChickLeghornID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation cho1 = new ResourceLocation("animania:textures/entity/chickens/hen_golden.png");
            EntityRegistry.registerModEntity(cho1, EntityHenOrpington.class, "animania.HenOrpington", AnimaniaConfig.entity.HenOrpingtonID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityHenOrpington.class, AnimaniaConfig.spawn.spawnProbabilityHens, 1,
                    AnimaniaConfig.spawn.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.JUNGLE, Biomes.SWAMPLAND);

            ResourceLocation cho2 = new ResourceLocation("animania:textures/entity/chickens/rooster_golden.png");
            EntityRegistry.registerModEntity(cho2, EntityRoosterOrpington.class, "animania.RoosterOrpington",
                    AnimaniaConfig.entity.RoosterOrpingtonID, Animania.MODID, 64, 3, true);

            ResourceLocation cho3 = new ResourceLocation("animania:textures/entity/chickens/chick_golden.png");
            EntityRegistry.registerModEntity(cho3, EntityChickOrpington.class, "animania.ChickOrpington", AnimaniaConfig.entity.ChickOrpingtonID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation chw1 = new ResourceLocation("animania:textures/entity/chickens/hen_brown.png");
            EntityRegistry.registerModEntity(chw1, EntityHenWyandotte.class, "animania.HenWyandotte", AnimaniaConfig.entity.HenWyandotteID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityHenWyandotte.class, AnimaniaConfig.spawn.spawnProbabilityHens, 1,
                    AnimaniaConfig.spawn.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

            ResourceLocation chw2 = new ResourceLocation("animania:textures/entity/chickens/rooster_brown.png");
            EntityRegistry.registerModEntity(chw2, EntityRoosterWyandotte.class, "animania.RoosterWyandotte",
                    AnimaniaConfig.entity.RoosterWyandotteID, Animania.MODID, 64, 3, true);

            ResourceLocation chw3 = new ResourceLocation("animania:textures/entity/chickens/chick_brown.png");
            EntityRegistry.registerModEntity(chw3, EntityChickWyandotte.class, "animania.ChickWyandotte", AnimaniaConfig.entity.ChickWyandotteID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation chr1 = new ResourceLocation("animania:textures/entity/chickens/hen_red.png");
            EntityRegistry.registerModEntity(chr1, EntityHenRhodeIslandRed.class, "animania.HenRhodeIslandRed",
                    AnimaniaConfig.entity.HenRhodeIslandRedID, Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityHenRhodeIslandRed.class, AnimaniaConfig.spawn.spawnProbabilityHens, 1,
                    AnimaniaConfig.spawn.numberChickenFamilies, EnumCreatureType.CREATURE, Biomes.FOREST);

            ResourceLocation chr2 = new ResourceLocation("animania:textures/entity/chickens/rooster_red.png");
            EntityRegistry.registerModEntity(chr2, EntityRoosterRhodeIslandRed.class, "animania.RoosterRhodeIslandRed",
                    AnimaniaConfig.entity.RoosterRhodeIslandRedID, Animania.MODID, 64, 3, true);

            ResourceLocation chr3 = new ResourceLocation("animania:textures/entity/chickens/chick_red.png");
            EntityRegistry.registerModEntity(chr3, EntityChickRhodeIslandRed.class, "animania.ChickRhodeIslandRed",
                    AnimaniaConfig.entity.ChickRhodeIslandRedID, Animania.MODID, 64, 3, true);

        }

        // PIGS
        if (AnimaniaConfig.spawn.spawnAnimaniaPigs) {
            ResourceLocation py1 = new ResourceLocation("animania:textures/entity/pigs/sow_yorkshire.png");
            EntityRegistry.registerModEntity(py1, EntitySowYorkshire.class, "animania.SowYorkshire", AnimaniaConfig.entity.SowYorkshireID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation py2 = new ResourceLocation("animania:textures/entity/chickens/hog_yorkshire.png");
            EntityRegistry.registerModEntity(py2, EntityHogYorkshire.class, "animania.HogYorkshire", AnimaniaConfig.entity.HogYorkshireID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation py3 = new ResourceLocation("animania:textures/entity/chickens/piglet_yorkshire.png");
            EntityRegistry.registerModEntity(py3, EntityPigletYorkshire.class, "animania.PigletYorkshire", AnimaniaConfig.entity.PigletYorkshireID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowYorkshire.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.PLAINS);

            ResourceLocation pos1 = new ResourceLocation("animania:textures/entity/pigs/sow_old_spot.png");
            EntityRegistry.registerModEntity(pos1, EntitySowOldSpot.class, "animania.SowOldSpot", AnimaniaConfig.entity.SowOldSpotID, Animania.MODID,
                    64, 3, true);

            ResourceLocation pos2 = new ResourceLocation("animania:textures/entity/pigs/hog_old_spot.png");
            EntityRegistry.registerModEntity(pos2, EntityHogOldSpot.class, "animania.HogOldSpot", AnimaniaConfig.entity.HogOldSpotID, Animania.MODID,
                    64, 3, true);

            ResourceLocation pos3 = new ResourceLocation("animania:textures/entity/pigs/piglet_old_spot.png");
            EntityRegistry.registerModEntity(pos3, EntityPigletOldSpot.class, "animania.PigletOldSpot", AnimaniaConfig.entity.PigletOldSpotID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowOldSpot.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.FOREST);

            ResourceLocation plb1 = new ResourceLocation("animania:textures/entity/pigs/sow_large_black.png");
            EntityRegistry.registerModEntity(plb1, EntitySowLargeBlack.class, "animania.SowLargeBlack", AnimaniaConfig.entity.SowLargeBlackID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation plb2 = new ResourceLocation("animania:textures/entity/pigs/hog_large_black.png");
            EntityRegistry.registerModEntity(plb2, EntityHogLargeBlack.class, "animania.HogLargeBlack", AnimaniaConfig.entity.HogLargeBlackID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation plb3 = new ResourceLocation("animania:textures/entity/pigs/piglet_large_black.png");
            EntityRegistry.registerModEntity(plb3, EntityPigletLargeBlack.class, "animania.PigletLargeBlack",
                    AnimaniaConfig.entity.PigletLargeBlackID, Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowLargeBlack.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.SWAMPLAND, Biomes.BIRCH_FOREST);

            ResourceLocation plw1 = new ResourceLocation("animania:textures/entity/pigs/sow_large_white.png");
            EntityRegistry.registerModEntity(plw1, EntitySowLargeWhite.class, "animania.SowLargeWhite", AnimaniaConfig.entity.SowLargeWhiteID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation plw2 = new ResourceLocation("animania:textures/entity/pigs/hog_large_white.png");
            EntityRegistry.registerModEntity(plw2, EntityHogLargeWhite.class, "animania.HogLargeWhite", AnimaniaConfig.entity.HogLargeWhiteID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation plw3 = new ResourceLocation("animania:textures/entity/pigs/piglet_large_white.png");
            EntityRegistry.registerModEntity(plw3, EntityPigletLargeWhite.class, "animania.PigletLargeWhite",
                    AnimaniaConfig.entity.PigletLargeWhiteID, Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowLargeWhite.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.FOREST_HILLS, Biomes.BIRCH_FOREST_HILLS);

            ResourceLocation pd1 = new ResourceLocation("animania:textures/entity/pigs/sow_duroc.png");
            EntityRegistry.registerModEntity(pd1, EntitySowDuroc.class, "animania.SowDuroc", AnimaniaConfig.entity.SowDurocID, Animania.MODID, 64, 3,
                    true);

            ResourceLocation pd2 = new ResourceLocation("animania:textures/entity/pigs/hog_duroc.png");
            EntityRegistry.registerModEntity(pd2, EntityHogDuroc.class, "animania.HogDuroc", AnimaniaConfig.entity.HogDurocID, Animania.MODID, 64, 3,
                    true);

            ResourceLocation pd3 = new ResourceLocation("animania:textures/entity/pigs/piglet_duroc.png");
            EntityRegistry.registerModEntity(pd3, EntityPigletDuroc.class, "animania.PigletDuroc", AnimaniaConfig.entity.PigletDurocID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowDuroc.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.JUNGLE);

            ResourceLocation ph1 = new ResourceLocation("animania:textures/entity/pigs/sow_hampshire.png");
            EntityRegistry.registerModEntity(ph1, EntitySowHampshire.class, "animania.SowHampshire", AnimaniaConfig.entity.SowHampshireID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation ph2 = new ResourceLocation("animania:textures/entity/pigs/hog_hampshire.png");
            EntityRegistry.registerModEntity(ph2, EntityHogHampshire.class, "animania.HogHampshire", AnimaniaConfig.entity.HogHampshireID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation ph3 = new ResourceLocation("animania:textures/entity/pigs/piglet_hampshire.png");
            EntityRegistry.registerModEntity(ph3, EntityPigletHampshire.class, "animania.PigletHampshire", AnimaniaConfig.entity.PigletHampshireID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntitySowHampshire.class, AnimaniaConfig.spawn.spawnProbabilitySows, 1, AnimaniaConfig.spawn.numberPigFamilies,
                    EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES);
        }

        // RODENTS
        if (AnimaniaConfig.spawn.spawnAnimaniaRodents) {

            ResourceLocation ha1 = new ResourceLocation("animania:textures/entity/pigs/hamster_tarou.png");
            EntityRegistry.registerModEntity(ha1, EntityHamster.class, "animania.Hamster", AnimaniaConfig.entity.HamsterMaleID, Animania.MODID, 64, 3,
                    true);
            EntityRegistry.addSpawn(EntityHamster.class, AnimaniaConfig.spawn.spawnProbabilityHamsters, 1, 2, EnumCreatureType.CREATURE, Biomes.BEACH,
                    Biomes.DESERT, Biomes.DESERT_HILLS);

            ResourceLocation fe1 = new ResourceLocation("animania:textures/entity/pigs/ferret_grey.png");
            EntityRegistry.registerModEntity(fe1, EntityFerretGrey.class, "animania.FerretGrey", AnimaniaConfig.entity.FerretMaleGreyID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation fe2 = new ResourceLocation("animania:textures/entity/pigs/ferret_white.png");
            EntityRegistry.registerModEntity(fe2, EntityFerretWhite.class, "animania.FerretWhite", AnimaniaConfig.entity.FerretMaleWhiteID,
                    Animania.MODID, 64, 3, true);

            EntityRegistry.addSpawn(EntityFerretGrey.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE,
                    Biomes.SAVANNA);
            EntityRegistry.addSpawn(EntityFerretWhite.class, AnimaniaConfig.spawn.spawnProbabilityFerrets, 1, 2, EnumCreatureType.CREATURE,
                    Biomes.SAVANNA);

            ResourceLocation he1 = new ResourceLocation("animania:textures/entity/pigs/hedgehog.png");
            EntityRegistry.registerModEntity(he1, EntityHedgehog.class, "animania.Hedgehog", AnimaniaConfig.entity.HedgehogMaleID, Animania.MODID, 64,
                    3, true);
            EntityRegistry.addSpawn(EntityHedgehog.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE,
                    Biomes.FOREST);

            ResourceLocation he2 = new ResourceLocation("animania:textures/entity/pigs/hedgehog_white.png");
            EntityRegistry.registerModEntity(he2, EntityHedgehogAlbino.class, "animania.HedgehogAlbino", AnimaniaConfig.entity.HedgehogMaleAlbinoID,
                    Animania.MODID, 64, 3, true);
            EntityRegistry.addSpawn(EntityHedgehogAlbino.class, AnimaniaConfig.spawn.spawnProbabilityHedgehogs, 1, 2, EnumCreatureType.CREATURE,
                    Biomes.SWAMPLAND);
        }

        // PEACOCKS
        if (AnimaniaConfig.spawn.spawnAnimaniaPeacocks) {
            ResourceLocation pb1 = new ResourceLocation("animania:textures/entity/pigs/peacock_blue.png");
            EntityRegistry.registerModEntity(pb1, EntityPeacockBlue.class, "animania.PeacockBlue", AnimaniaConfig.entity.PeacockBlueID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation pb2 = new ResourceLocation("animania:textures/entity/pigs/peafowl_blue.png");
            EntityRegistry.registerModEntity(pb2, EntityPeafowlBlue.class, "animania.PeafowlBlue", AnimaniaConfig.entity.PeafowlBlueID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation pb3 = new ResourceLocation("animania:textures/entity/pigs/peachick_blue.png");
            EntityRegistry.registerModEntity(pb3, EntityPeachickBlue.class, "animania.PeachickBlue", AnimaniaConfig.entity.PeachickBlueID,
                    Animania.MODID, 64, 3, true);

            EntityRegistry.addSpawn(EntityPeacockBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);
            EntityRegistry.addSpawn(EntityPeafowlBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);
            EntityRegistry.addSpawn(EntityPeachickBlue.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);

            ResourceLocation pw1 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
            EntityRegistry.registerModEntity(pw1, EntityPeacockWhite.class, "animania.PeacockWhite", AnimaniaConfig.entity.PeacockWhiteID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation pw2 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
            EntityRegistry.registerModEntity(pw2, EntityPeafowlWhite.class, "animania.PeafowlWhite", AnimaniaConfig.entity.PeafowlWhiteID,
                    Animania.MODID, 64, 3, true);

            ResourceLocation pw3 = new ResourceLocation("animania:textures/entity/pigs/peacock_white.png");
            EntityRegistry.registerModEntity(pw3, EntityPeachickWhite.class, "animania.PeachickWhite", AnimaniaConfig.entity.PeachickWhiteID,
                    Animania.MODID, 64, 3, true);

            EntityRegistry.addSpawn(EntityPeacockWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);
            EntityRegistry.addSpawn(EntityPeafowlWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);
            EntityRegistry.addSpawn(EntityPeachickWhite.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1, EnumCreatureType.CREATURE,
                    Biomes.JUNGLE, Biomes.SWAMPLAND);
        }

    }

}
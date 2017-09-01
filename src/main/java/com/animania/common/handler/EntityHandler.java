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

        RegistryHelper.Entities.register(EntityWagon.class, "wagon", entityID++, 40, 1, true);

        RegistryHelper.Entities.register(EntityFrogs.class, "frog", entityID++, 64, 3, true);
        RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 1, EnumCreatureType.AMBIENT,
                getBiomes(BiomeDictionary.Type.SWAMP));
        RegistryHelper.Entities.addSpawn(EntityFrogs.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT,
                getBiomes(BiomeDictionary.Type.RIVER));

        RegistryHelper.Entities.register(EntityToad.class, "toad", entityID++, 64, 3, true);
        EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT,
                getBiomes(BiomeDictionary.Type.SWAMP));
        EntityRegistry.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT,
                getBiomes(BiomeDictionary.Type.JUNGLE));

        RegistryHelper.Entities.register(EntityDartFrogs.class, "dartfrog", entityID++, 64, 3, true);
        RegistryHelper.Entities.addSpawn(EntityToad.class, AnimaniaConfig.spawn.spawnProbabilityAmphibians, 1, 2, EnumCreatureType.AMBIENT,
                getBiomes(BiomeDictionary.Type.JUNGLE));

        if (AnimaniaConfig.spawn.spawnAnimaniaGoats)
        {
            RegistryHelper.Entities.register(EntityKidAlpine.class, "kid_alpine", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckAlpine.class, "buck_alpine", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeAlpine.class, "doe_alpine", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
            RegistryHelper.Entities.addSpawn(EntityDoeAlpine.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));

            RegistryHelper.Entities.register(EntityKidAngora.class, "kid_angora", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckAngora.class, "buck_angora", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeAngora.class, "doe_angora", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeAngora.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));

            RegistryHelper.Entities.register(EntityKidFainting.class, "kid_fainting", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckFainting.class, "buck_fainting", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeFainting.class, "doe_fainting", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeFainting.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.PLAINS));

            RegistryHelper.Entities.register(EntityKidKiko.class, "kid_kiko", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckKiko.class, "buck_kiko", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeKiko.class, "doe_kiko", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MOUNTAIN));
            RegistryHelper.Entities.addSpawn(EntityDoeKiko.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.HILLS));

            RegistryHelper.Entities.register(EntityKidKinder.class, "kid_kinder", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckKinder.class, "buck_kinder", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeKinder.class, "doe_kinder", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
            RegistryHelper.Entities.addSpawn(EntityDoeKinder.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));

            RegistryHelper.Entities.register(EntityKidNigerianDwarf.class, "kid_nigerian_dwarf", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckNigerianDwarf.class, "buck_nigerian_dwarf", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoeNigerianDwarf.class, "doe_nigerian_dwarf", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.DRY));
            RegistryHelper.Entities.addSpawn(EntityDoeNigerianDwarf.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SANDY));

            RegistryHelper.Entities.register(EntityKidPygmy.class, "kid_pygmy", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityBuckPygmy.class, "buck_pygmy", entityID++, 64, 3, true);
            RegistryHelper.Entities.register(EntityDoePygmy.class, "doe_pygmy", entityID++, 64, 3, true);
            RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SAVANNA));
            RegistryHelper.Entities.addSpawn(EntityDoePygmy.class, AnimaniaConfig.spawn.spawnProbabilityGoats, 1,
                    AnimaniaConfig.spawn.numberGoatFamilies, EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.MESA));
        }

        // PEACOCKS (NEW)
        if (AnimaniaConfig.spawn.spawnAnimaniaPeacocks)
        {

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

            RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeacockCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeafowlCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeachickCharcoal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

            RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeacockOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeafowlOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeachickOpal.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

            RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeacockPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeafowlPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeachickPeach.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

            RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeacockPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeafowlPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeachickPurple.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

            RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.SWAMP));
            RegistryHelper.Entities.addSpawn(EntityPeacockTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeafowlTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks, 1, 1, EnumCreatureType.CREATURE,
                    getBiomes(BiomeDictionary.Type.JUNGLE));
            RegistryHelper.Entities.addSpawn(EntityPeachickTaupe.class, AnimaniaConfig.spawn.spawnProbabilityPeacocks / 2, 1, 1,
                    EnumCreatureType.CREATURE, getBiomes(BiomeDictionary.Type.JUNGLE));

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
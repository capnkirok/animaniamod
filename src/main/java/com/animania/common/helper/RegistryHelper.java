package com.animania.common.helper;

import com.animania.Animania;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.EntityGender;
import com.animania.common.handler.EntityEggHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RegistryHelper
{

	public static class Item
	{

		// TODO Fix actual bug on this way to load textures
		/**
		 * Registers Render for an Item
		 *
		 * @param item
		 */
		public static void register(net.minecraft.item.Item item)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + item.getUnlocalizedName(), "inventory"));
		}
	}

	public static class Entities
	{
		/**
		 * Register an entity with the specified tracking values.
		 *
		 * @param entityClass
		 *            The entity's class
		 * @param entityName
		 *            The entity's unique name
		 * @param trackingRange
		 *            The range at which MC will send tracking updates
		 * @param updateFrequency
		 *            The frequency of tracking updates
		 * @param sendsVelocityUpdates
		 *            Whether to send velocity information packets as well
		 */
		public static void registerAnimal(Class<? extends net.minecraft.entity.Entity> entityClass, String entityName, int entityID, AnimaniaType type, EntityGender gender)
		{
			ResourceLocation registryName = new ResourceLocation(Animania.MODID, entityName);
            EntityEntry entry = new EntityEntry(entityClass, entityName).setRegistryName(registryName);
            
			EntityRegistry.registerModEntity(registryName, entityClass, registryName.toString(), entityID, Animania.instance, 64, 1, true);
			EntityEggHandler.ENTITY_MAP.put(new AnimalContainer(type, gender), entry);
		}

		/**
		 * Register an entity with the specified tracking values and spawn egg
		 * colours.
		 *
		 * @param entityClass
		 *            The entity's class
		 * @param entityName
		 *            The entity's unique name
		 * @param trackingRange
		 *            The range at which MC will send tracking updates
		 * @param updateFrequency
		 *            The frequency of tracking updates
		 * @param sendsVelocityUpdates
		 *            Whether to send velocity information packets as well
		 * @param eggPrimary
		 *            The spawn egg's primary (background) colour
		 * @param eggSecondary
		 *            The spawn egg's secondary (foreground) colour
		 */
		public static void registerEntity(Class<? extends net.minecraft.entity.Entity> entityClass, String entityName, int entityID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
		{
			ResourceLocation registryName = new ResourceLocation(Animania.MODID, entityName);
			EntityRegistry.registerModEntity(registryName, entityClass, registryName.toString(), entityID, Animania.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		}

		public static void addSpawn(Class<? extends EntityLiving> entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes)
		{
			EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
		}

		/** Helper to copy spawn of creature A to a creature B */
		public static void copySpawns(Class<? extends EntityLiving> classToAdd, EnumCreatureType creatureTypeToAdd, Class<? extends EntityLiving> classToCopy, EnumCreatureType creatureTypeToCopy)
		{
			for (final Biome biome : ForgeRegistries.BIOMES)
				biome.getSpawnableList(creatureTypeToCopy).stream().filter(entry -> entry.entityClass == classToCopy).findFirst().ifPresent(spawnListEntry -> biome.getSpawnableList(creatureTypeToAdd).add(new Biome.SpawnListEntry(classToAdd, spawnListEntry.itemWeight, spawnListEntry.minGroupCount, spawnListEntry.maxGroupCount)));
		}

		public static Biome[] getBiomes(BiomeDictionary.Type type)
		{
			return BiomeDictionary.getBiomes(type).stream().toArray(Biome[]::new);
		}

	}
}

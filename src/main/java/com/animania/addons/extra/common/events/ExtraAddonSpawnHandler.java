package com.animania.addons.extra.common.events;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityDoeJack;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExtraAddonSpawnHandler
{

	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		Entity replacementEntity = null;

		if (ExtraConfig.settings.replaceVanillaRabbits && event.getEntity().getClass().equals(RabbitEntity.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;

			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckCottontail(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeCottontail(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes)) || AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes)[1]))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckJack(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeJack(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckHavana(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeHavana(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityDoeChinchilla(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityBuckChinchilla(worldIn);
					}

				} else if (chooser == 1)
				{
					replacementEntity = new RabbitEntityBuckCottontail(worldIn);

				} else if (chooser == 2)
				{
					replacementEntity = new RabbitEntityDoeCottontail(worldIn);
				}
			}

		}

		if (replacementEntity != null)
		{
			replacementEntity.setPosition(event.getX(), event.getY(), event.getZ());
			AnimaniaHelper.spawnEntity(event.getWorld(), replacementEntity);
		}
	}

	@SubscribeEvent
	public static void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY() + 1, event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRabbits && (event.getEntity() instanceof EntityAnimaniaRabbit && !worldIn.isRemote))
		{
			List<EntityAnimaniaRabbit> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaRabbit.class, 100, event.getEntity().world, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitRabbits)
			{
				event.setResult(Result.DENY);
				// System.out.println("Rabbit Denied Existence!");
			}
		} else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaAmphibians && (event.getEntity() instanceof EntityAmphibian && !worldIn.isRemote))
		{
			List<EntityAmphibian> others = AnimaniaHelper.getEntitiesInRange(EntityAmphibian.class, 100, event.getEntity().world, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitAmphibians)
			{
				event.setResult(Result.DENY);
				// System.out.println("Amphibian Denied Existence!");
			}
		} else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && (event.getEntity() instanceof EntityHamster && !worldIn.isRemote))
		{
			List<EntityHamster> others = AnimaniaHelper.getEntitiesInRange(EntityHamster.class, 100, event.getEntity().world, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitHamsters)
			{
				event.setResult(Result.DENY);
				// System.out.println("Hamster Denied Existence!");
			}
		} else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && (event.getEntity() instanceof EntityFerretBase && !worldIn.isRemote))
		{
			List<EntityFerretBase> others = AnimaniaHelper.getEntitiesInRange(EntityFerretBase.class, 100, event.getEntity().world, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				// System.out.println("Ferret Denied Existence!");
			}
		} else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && (event.getEntity() instanceof EntityHedgehogBase && !worldIn.isRemote))
		{
			List<EntityHedgehogBase> others = AnimaniaHelper.getEntitiesInRange(EntityHedgehogBase.class, 100, event.getEntity().world, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				// System.out.println("Hedgehog Denied Existence!");
			}
		}
	}
}

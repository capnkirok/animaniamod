package common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.RabbitEntityDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.RabbitEntityDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.RabbitEntityDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.RabbitEntityDoeJack;
import com.animania.common.helper.AnimaniaHelper;
import common.entity.amphibians.EntityAmphibian;
import common.entity.rodents.EntityFerretBase;
import common.entity.rodents.EntityHamster;
import common.entity.rodents.EntityHedgehogBase;
import common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import config.ExtraConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ExtraAddonSpawnHandler
{

	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		Level levelIn = event.getLevel();
		Biome biome = event.getLevel().getBiome(pos);
		int chooser = 0;

		Entity replacementEntity = null;

		if (ExtraConfig.settings.replaceVanillaRabbits && event.getEntity().getClass().equals(Rabbit.class) && !levelIn.isClientSide)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;

			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().level, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(3);

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckCottontail(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeCottontail(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes)) || AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes)[1]))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckJack(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeJack(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityBuckHavana(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityDoeHavana(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new RabbitEntityDoeChinchilla(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new RabbitEntityBuckChinchilla(levelIn);
					}

				}
				else if (chooser == 1)
				{
					replacementEntity = new RabbitEntityBuckCottontail(levelIn);

				}
				else if (chooser == 2)
				{
					replacementEntity = new RabbitEntityDoeCottontail(levelIn);
				}
			}

		}

		if (replacementEntity != null)
		{
			replacementEntity.setPosition(event.getX(), event.getY(), event.getZ());
			AnimaniaHelper.spawnEntity(event.getLevel(), replacementEntity);
		}
	}

	@SubscribeEvent
	public static void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY() + 1, event.getZ());
		Level levelIn = event.getLevel();
		Biome biome = event.getLevel().getBiome(pos);

		if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRabbits && event.getEntity() instanceof EntityAnimaniaRabbit && !levelIn.isClientSide)
		{
			List<EntityAnimaniaRabbit> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaRabbit.class, 100, event.getEntity().level, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitRabbits)
			{
				event.setResult(Result.DENY);
				// System.out.println("Rabbit Denied Existence!");
			}
		}
		else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaAmphibians && event.getEntity() instanceof EntityAmphibian && !levelIn.isClientSide)
		{
			List<EntityAmphibian> others = AnimaniaHelper.getEntitiesInRange(EntityAmphibian.class, 100, event.getEntity().level, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitAmphibians)
			{
				event.setResult(Result.DENY);
				// System.out.println("Amphibian Denied Existence!");
			}
		}
		else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && event.getEntity() instanceof EntityHamster && !levelIn.isClientSide)
		{
			List<EntityHamster> others = AnimaniaHelper.getEntitiesInRange(EntityHamster.class, 100, event.getEntity().level, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitHamsters)
			{
				event.setResult(Result.DENY);
				// System.out.println("Hamster Denied Existence!");
			}
		}
		else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && event.getEntity() instanceof EntityFerretBase && !levelIn.isClientSide)
		{
			List<EntityFerretBase> others = AnimaniaHelper.getEntitiesInRange(EntityFerretBase.class, 100, event.getEntity().level, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				// System.out.println("Ferret Denied Existence!");
			}
		}
		else if (ExtraConfig.settings.spawning_and_breeding.spawnAnimaniaRodents && event.getEntity() instanceof EntityHedgehogBase && !levelIn.isClientSide)
		{
			List<EntityHedgehogBase> others = AnimaniaHelper.getEntitiesInRange(EntityHedgehogBase.class, 100, event.getEntity().level, pos);
			if (others.size() > ExtraConfig.settings.spawning_and_breeding.spawnLimitFerrets)
			{
				event.setResult(Result.DENY);
				// System.out.println("Hedgehog Denied Existence!");
			}
		}
	}
}

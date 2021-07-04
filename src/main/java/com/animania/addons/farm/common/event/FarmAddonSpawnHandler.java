package com.animania.addons.farm.common.event;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityHenLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenLeghorn.EntityRoosterLeghorn;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityHenOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenOrpington.EntityRoosterOrpington;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityHenPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenPlymouthRock.EntityRoosterPlymouthRock;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityHenRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenRhodeIslandRed.EntityRoosterRhodeIslandRed;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityHenWyandotte;
import com.animania.addons.farm.common.entity.chickens.ChickenWyandotte.EntityRoosterWyandotte;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCowAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCowFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityCowHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityCowHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCowHolstein;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityCowLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityCowMooshroom;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntityHogDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntityHogHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.EntitySowHampshire;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntityHogLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigLargeBlack.EntitySowLargeBlack;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntityHogOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.EntitySowOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntityHogYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.EntitySowYorkshire;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FarmAddonSpawnHandler
{
	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);
		int chooser = 0;

		LivingEntity replacementEntity = null;

		if (FarmConfig.settings.spawning_and_breeding.replaceVanillaCows && (event.getEntity().getClass().equals(EntityCow.class) || event.getEntity().getClass().equals(EntityMooshroom.class)) && !worldIn.isRemote)
		{
			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHolsteinBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityCowHolstein(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullHolstein(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowLonghornBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityCowLonghorn(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullLonghorn(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHerefordBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityCowHereford(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullHereford(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHighlandBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityCowHighland(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullHighland(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowAngusBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityCowAngus(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullAngus(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowMooshroomBiomeTypes)))
				{

					if (chooser == 1)
					{
						replacementEntity = new EntityCowMooshroom(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityBullMooshroom(worldIn);
					}

				} else if (chooser == 1)
				{
					replacementEntity = new EntityCowFriesian(worldIn);

				} else if (chooser == 2)
				{
					replacementEntity = new EntityBullFriesian(worldIn);

				}
			}

		} else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;
			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().world, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigOldSpotBiomeTypes)))
				{

					if (Animania.RANDOM.nextBoolean())
					{
						if (chooser == 1)
						{

							replacementEntity = new EntitySowOldSpot(worldIn);
						} else if (chooser == 2)
						{
							replacementEntity = new EntityHogOldSpot(worldIn);
						}
					} else
					{

						if (chooser == 1)
						{
							replacementEntity = new EntitySowHampshire(worldIn);
						} else if (chooser == 2)
						{
							replacementEntity = new EntityHogHampshire(worldIn);
						}
					}
				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigDurocBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntitySowDuroc(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityHogDuroc(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigLargeBlackBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntitySowLargeBlack(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityHogLargeBlack(worldIn);
					}
				} else if (chooser == 1)
				{
					replacementEntity = new EntitySowYorkshire(worldIn);

				} else if (chooser == 2)
				{
					replacementEntity = new EntityHogYorkshire(worldIn);

				}
			}

		}

		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote)
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

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenOrpingtonBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenOrpington(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterOrpington(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenPlymouthRockBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenPlymouthRock(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterPlymouthRock(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenRhodeIslandRedBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenRhodeIslandRed(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterRhodeIslandRed(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenWyandotteBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenWyandotte(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterWyandotte(worldIn);
					}

				} else if (chooser == 1)
				{
					replacementEntity = new EntityHenLeghorn(worldIn);

				} else if (chooser == 2)
				{
					replacementEntity = new EntityRoosterLeghorn(worldIn);

				}
			}

		}

		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaSheep && event.getEntity().getClass().equals(EntitySheep.class) && !worldIn.isRemote)
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

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes)))
				{
					int chooser2 = Animania.RANDOM.nextInt(2);
					if (Animania.RANDOM.nextBoolean())
					{
						if (chooser == 1)
						{
							replacementEntity = new EntityEweDorset(worldIn);
						} else if (chooser == 2)
						{
							replacementEntity = new EntityRamDorset(worldIn);
						}
					} else
					{

						if (chooser == 1)
						{
							replacementEntity = new EntityEweFriesian(worldIn);
						} else if (chooser == 2)
						{
							replacementEntity = new EntityRamFriesian(worldIn);
						}
					}
				}

				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepSuffolkBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweSuffolk(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRamSuffolk(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweDorset(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRamDorset(worldIn);
					}

				} else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepMerinoBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweMerino(worldIn);
					} else if (chooser == 2)
					{
						replacementEntity = new EntityRamMerino(worldIn);
					}

				} else if (chooser == 1)
				{
					replacementEntity = new EntityEweFriesian(worldIn);

				} else if (chooser == 2)
				{
					replacementEntity = new EntityRamFriesian(worldIn);

				}
			}

		} else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote)
		{

			boolean toggler = Animania.RANDOM.nextBoolean();
			if (!event.getEntity().hasCustomName() && toggler)
			{
				event.setResult(Result.DENY);
				if (Animania.RANDOM.nextBoolean())
					replacementEntity = new EntityMareDraftHorse(worldIn);
				else
					replacementEntity = new EntityStallionDraftHorse(worldIn);
			}

		}

		if (replacementEntity != null)
		{
			replacementEntity.setPosition(event.getX(), event.getY(), event.getZ());

			Result result = ForgeEventFactory.canEntitySpawn(replacementEntity, event.getWorld(), event.getX(), event.getY(), event.getZ(), event.getSpawner());

			if (result == Result.ALLOW || (result == Result.DEFAULT && (replacementEntity.getCanSpawnHere() && replacementEntity.isNotColliding())))
			{
				if (replacementEntity.isNotColliding())
				{
					AnimaniaHelper.spawnEntity(event.getWorld(), replacementEntity);
				} else
				{
					replacementEntity.setDead();
				}
			}
		}
	}

	@SubscribeEvent
	public static void limitAnimaniaSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY() + 1, event.getZ());
		World worldIn = event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaCows && (event.getEntity() instanceof EntityAnimaniaCow && !worldIn.isRemote))
		{
			List<EntityAnimaniaCow> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitCows)
			{
				event.setResult(Result.DENY);
				// System.out.println("Cow Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaPigs && (event.getEntity() instanceof EntityAnimaniaPig && !worldIn.isRemote))
		{
			List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitPigs)
			{
				event.setResult(Result.DENY);
				// System.out.println("Pig Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaChickens && (event.getEntity() instanceof EntityAnimaniaChicken && !worldIn.isRemote))
		{
			List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitChickens)
			{
				event.setResult(Result.DENY);
				// System.out.println("Chicken Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaSheep && (event.getEntity() instanceof EntityAnimaniaSheep && !worldIn.isRemote))
		{
			List<EntityAnimaniaSheep> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitSheep)
			{
				event.setResult(Result.DENY);
				// System.out.println("Sheep Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaGoats && (event.getEntity() instanceof EntityAnimaniaGoat && !worldIn.isRemote))
		{
			List<EntityAnimaniaGoat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaGoat.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitGoats)
			{
				event.setResult(Result.DENY);
				// System.out.println("Goats Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && (event.getEntity() instanceof EntityAnimaniaHorse && !worldIn.isRemote))
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				// System.out.println("Horse Denied Existence!");
			}
		} else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && (event.getEntity() instanceof EntityAnimaniaHorse && !worldIn.isRemote))
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().world, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				// System.out.println("Horse Denied Existence!");
			}
		}
	}

}

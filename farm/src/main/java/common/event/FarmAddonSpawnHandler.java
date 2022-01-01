package common.event;

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
import com.animania.addons.farm.common.entity.cows.CowAngus.CowEntityAngus;
import com.animania.addons.farm.common.entity.cows.CowAngus.EntityBullAngus;
import com.animania.addons.farm.common.entity.cows.CowFriesian.CowEntityFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowHereford.CowEntityHereford;
import com.animania.addons.farm.common.entity.cows.CowHereford.EntityBullHereford;
import com.animania.addons.farm.common.entity.cows.CowHighland.CowEntityHighland;
import com.animania.addons.farm.common.entity.cows.CowHighland.EntityBullHighland;
import com.animania.addons.farm.common.entity.cows.CowHolstein.CowEntityHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.CowEntityLonghorn;
import com.animania.addons.farm.common.entity.cows.CowLonghorn.EntityBullLonghorn;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.CowEntityMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
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
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.common.helper.AnimaniaHelper;
import common.entity.chickens.EntityAnimaniaChicken;
import common.entity.cows.EntityAnimaniaCow;
import common.entity.goats.EntityAnimaniaGoat;
import common.entity.horses.EntityAnimaniaHorse;
import common.entity.pigs.EntityAnimaniaPig;
import common.entity.sheep.EntityAnimaniaSheep;
import config.FarmConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class FarmAddonSpawnHandler
{
	@SubscribeEvent
	public static void removeSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		Level levelIn = event.getLevel();
		Biome biome = event.getLevel().getBiome(pos);
		int chooser = 0;

		LivingEntity replacementEntity = null;

		if (FarmConfig.settings.spawning_and_breeding.replaceVanillaCows && (event.getEntity().getClass().equals(Cow.class) || event.getEntity().getClass().equals(EntityMooshroom.class)) && !levelIn.isClientSide)
		{
			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().level, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHolsteinBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new CowEntityHolstein(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullHolstein(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowLonghornBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new CowEntityLonghorn(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullLonghorn(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHerefordBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new CowEntityHereford(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullHereford(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowHighlandBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new CowEntityHighland(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullHighland(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowAngusBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new CowEntityAngus(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullAngus(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.cowMooshroomBiomeTypes)))
				{

					if (chooser == 1)
					{
						replacementEntity = new CowEntityMooshroom(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityBullMooshroom(levelIn);
					}

				}
				else if (chooser == 1)
				{
					replacementEntity = new CowEntityFriesian(levelIn);

				}
				else if (chooser == 2)
				{
					replacementEntity = new EntityBullFriesian(levelIn);

				}
			}

		}
		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaPigs && event.getEntity().getClass().equals(PigEntity.class) && !levelIn.isClientSide)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

			chooser = Animania.RANDOM.nextInt(2) + 1;
			List<LivingEntity> otheranimals = AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 100, event.getEntity().level, pos);
			if (otheranimals.size() <= 2)
			{
				chooser = Animania.RANDOM.nextInt(2) + 1;

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigOldSpotBiomeTypes)))
				{

					if (Animania.RANDOM.nextBoolean())
					{
						if (chooser == 1)
						{

							replacementEntity = new EntitySowOldSpot(levelIn);
						}
						else if (chooser == 2)
						{
							replacementEntity = new EntityHogOldSpot(levelIn);
						}
					}
					else if (chooser == 1)
					{
						replacementEntity = new EntitySowHampshire(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityHogHampshire(levelIn);
					}
				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigDurocBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntitySowDuroc(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityHogDuroc(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.pigLargeBlackBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntitySowLargeBlack(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityHogLargeBlack(levelIn);
					}
				}
				else if (chooser == 1)
				{
					replacementEntity = new EntitySowYorkshire(levelIn);

				}
				else if (chooser == 2)
				{
					replacementEntity = new EntityHogYorkshire(levelIn);

				}
			}

		}

		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaChickens && event.getEntity().getClass().equals(ChickenEntity.class) && !levelIn.isClientSide)
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

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenOrpingtonBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenOrpington(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterOrpington(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenPlymouthRockBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenPlymouthRock(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterPlymouthRock(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenRhodeIslandRedBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenRhodeIslandRed(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterRhodeIslandRed(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.chickenWyandotteBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityHenWyandotte(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRoosterWyandotte(levelIn);
					}

				}
				else if (chooser == 1)
				{
					replacementEntity = new EntityHenLeghorn(levelIn);

				}
				else if (chooser == 2)
				{
					replacementEntity = new EntityRoosterLeghorn(levelIn);

				}
			}

		}

		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaSheep && event.getEntity().getClass().equals(SheepEntity.class) && !levelIn.isClientSide)
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

				if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes)))
				{
					int chooser2 = Animania.RANDOM.nextInt(2);
					if (Animania.RANDOM.nextBoolean())
					{
						if (chooser == 1)
						{
							replacementEntity = new EntityEweDorset(levelIn);
						}
						else if (chooser == 2)
						{
							replacementEntity = new EntityRamDorset(levelIn);
						}
					}
					else if (chooser == 1)
					{
						replacementEntity = new EntityEweFriesian(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRamFriesian(levelIn);
					}
				}

				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepSuffolkBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweSuffolk(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRamSuffolk(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepDorsetBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweDorset(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRamDorset(levelIn);
					}

				}
				else if (AnimaniaHelper.hasBiomeType(biome, AnimaniaHelper.getBiomeTypes(FarmConfig.settings.spawning_and_breeding.sheepMerinoBiomeTypes)))
				{
					if (chooser == 1)
					{
						replacementEntity = new EntityEweMerino(levelIn);
					}
					else if (chooser == 2)
					{
						replacementEntity = new EntityRamMerino(levelIn);
					}

				}
				else if (chooser == 1)
				{
					replacementEntity = new EntityEweFriesian(levelIn);

				}
				else if (chooser == 2)
				{
					replacementEntity = new EntityRamFriesian(levelIn);

				}
			}

		}
		else if (FarmConfig.settings.spawning_and_breeding.replaceVanillaHorses && event.getEntity().getClass().equals(HorseEntity.class) && !levelIn.isClientSide)
		{

			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}

		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && event.getEntity().getClass().equals(HorseEntity.class) && !levelIn.isClientSide)
		{

			boolean toggler = Animania.RANDOM.nextBoolean();
			if (!event.getEntity().hasCustomName() && toggler)
			{
				event.setResult(Result.DENY);
				if (Animania.RANDOM.nextBoolean())
					replacementEntity = new EntityMareDraftHorse(levelIn);
				else
					replacementEntity = new EntityStallionDraftHorse(levelIn);
			}

		}

		if (replacementEntity != null)
		{
			replacementEntity.setPosition(event.getX(), event.getY(), event.getZ());

			Result result = ForgeEventFactory.canEntitySpawn(replacementEntity, event.getLevel(), event.getX(), event.getY(), event.getZ(), event.getSpawner());

			if (result == Result.ALLOW || result == Result.DEFAULT && replacementEntity.getCanSpawnHere() && replacementEntity.isNotColliding())
			{
				if (replacementEntity.isNotColliding())
				{
					AnimaniaHelper.spawnEntity(event.getLevel(), replacementEntity);
				}
				else
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
		Level levelIn = event.getLevel();
		Biome biome = event.getLevel().getBiome(pos);

		if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaCows && event.getEntity() instanceof EntityAnimaniaCow && !levelIn.isClientSide)
		{
			List<EntityAnimaniaCow> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitCows)
			{
				event.setResult(Result.DENY);
				// System.out.println("Cow Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaPigs && event.getEntity() instanceof EntityAnimaniaPig && !levelIn.isClientSide)
		{
			List<EntityAnimaniaPig> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitPigs)
			{
				event.setResult(Result.DENY);
				// System.out.println("Pig Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaChickens && event.getEntity() instanceof EntityAnimaniaChicken && !levelIn.isClientSide)
		{
			List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitChickens)
			{
				event.setResult(Result.DENY);
				// System.out.println("Chicken Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaSheep && event.getEntity() instanceof EntityAnimaniaSheep && !levelIn.isClientSide)
		{
			List<EntityAnimaniaSheep> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaSheep.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitSheep)
			{
				event.setResult(Result.DENY);
				// System.out.println("Sheep Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaGoats && event.getEntity() instanceof EntityAnimaniaGoat && !levelIn.isClientSide)
		{
			List<EntityAnimaniaGoat> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaGoat.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitGoats)
			{
				event.setResult(Result.DENY);
				// System.out.println("Goats Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && event.getEntity() instanceof EntityAnimaniaHorse && !levelIn.isClientSide)
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				// System.out.println("Horse Denied Existence!");
			}
		}
		else if (FarmConfig.settings.spawning_and_breeding.spawnAnimaniaHorses && event.getEntity() instanceof EntityAnimaniaHorse && !levelIn.isClientSide)
		{
			List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 100, event.getEntity().level, pos);
			if (others.size() > FarmConfig.settings.spawning_and_breeding.spawnLimitHorses)
			{
				event.setResult(Result.DENY);
				// System.out.println("Horse Denied Existence!");
			}
		}
	}

}

package com.animania.common.events;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
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
import com.animania.common.entities.cows.EntityAnimaniaCow;
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
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitBuckJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeJack;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitChinchilla;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitCottontail;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitHavana;
import com.animania.common.entities.rodents.rabbits.EntityRabbitKitJack;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.EntityEweDorset;
import com.animania.common.entities.sheep.EntityEweFriesian;
import com.animania.common.entities.sheep.EntityEweMerino;
import com.animania.common.entities.sheep.EntityEweSuffolk;
import com.animania.common.entities.sheep.EntityLambDorset;
import com.animania.common.entities.sheep.EntityLambFriesian;
import com.animania.common.entities.sheep.EntityLambMerino;
import com.animania.common.entities.sheep.EntityLambSuffolk;
import com.animania.common.entities.sheep.EntityRamDorset;
import com.animania.common.entities.sheep.EntityRamFriesian;
import com.animania.common.entities.sheep.EntityRamMerino;
import com.animania.common.entities.sheep.EntityRamSuffolk;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventReplaceSpawnAnimals
{
	@SubscribeEvent(priority = EventPriority.NORMAL)

	public void onEntitySpawn(EntityJoinWorldEvent event) {

		BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
		World worldIn = event.getWorld();
		Random rand = new Random();

		if (AnimaniaConfig.gameRules.replaceVanillaCows && event.getEntity().getClass().equals(EntityCow.class) && !worldIn.isRemote) {
			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				event.isCanceled();
				event.getEntity().setDead();

			}

			Biome biome = event.getWorld().getBiome(pos);
			List list = worldIn.loadedEntityList;

			int cowCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaCow) {
					num++;
				}
			}
			cowCount = num;

			//System.out.println("Cow count: " + cowCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaCows && cowCount < AnimaniaConfig.spawn.spawnLimitCows && !event.getEntity().hasCustomName()) {


				int chooser = 0;
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
					chooser = rand.nextInt(10);
				else
					chooser = rand.nextInt(5);
				
				if (BiomeDictionary.hasType(biome, Type.FOREST)) {
					if (chooser <= 2) {
						EntityCowHolstein entity = new EntityCowHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityBullHolstein entity = new EntityBullHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityCalfHolstein entity = new EntityCalfHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.SAVANNA)) {
					if (chooser <= 2) {
						EntityCowLonghorn entity = new EntityCowLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityBullLonghorn entity = new EntityBullLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityCalfLonghorn entity = new EntityCalfLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.HILLS)) {
					if (chooser <= 2) {
						EntityCowHereford entity = new EntityCowHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityBullHereford entity = new EntityBullHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityCalfHereford entity = new EntityCalfHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.LUSH)) {
					if (chooser <= 2) {
						EntityCowAngus entity = new EntityCowAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityBullAngus entity = new EntityBullAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityCalfAngus entity = new EntityCalfAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (chooser <= 2) {
					EntityCowFriesian entity = new EntityCowFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 3) {
					EntityBullFriesian entity = new EntityBullFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 4) {
					EntityCalfFriesian entity = new EntityCalfFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}

			}

		}
		else if (event.getEntity() instanceof EntityAnimaniaCow && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int cowCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaCow) {
					num++;
				}
			}
			cowCount = num;

			//System.out.println("Animania Cow count: " + cowCount);
			EntityAnimaniaCow checkTamed = (EntityAnimaniaCow) event.getEntity();

			if (cowCount >= AnimaniaConfig.spawn.spawnLimitCows && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					//event.setCanceled(true);
					//event.isCanceled();
					//event.getEntity().setDead();
				}
			}
		}
		else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				event.isCanceled();
				event.getEntity().setDead();
			}

			Biome biome = event.getWorld().getBiome(pos);
			List list = worldIn.loadedEntityList;


			int pigCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaPig) {
					num++;
				}
			}
			pigCount = num;

			int chooser = 0;
			if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				chooser = rand.nextInt(10);
			else
				chooser = rand.nextInt(5);

			//System.out.println("Pig count: " + pigCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaPigs && pigCount < AnimaniaConfig.spawn.spawnLimitPigs && !event.getEntity().hasCustomName())
				if (BiomeDictionary.hasType(biome, Type.FOREST)) {
					if (rand.nextBoolean()) {
						if (chooser <= 2) {
							EntitySowOldSpot entity = new EntitySowOldSpot(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 3) {
							EntityHogOldSpot entity = new EntityHogOldSpot(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 4) {
							EntityPigletOldSpot entity = new EntityPigletOldSpot(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
					} else { 

						if (chooser <= 2) {
							EntitySowHampshire entity = new EntitySowHampshire(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 3) {
							EntityHogHampshire entity = new EntityHogHampshire(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 4) {
							EntityPigletHampshire entity = new EntityPigletHampshire(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.JUNGLE)) {
					if (chooser <= 2) {
						EntitySowDuroc entity = new EntitySowDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityHogDuroc entity = new EntityHogDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityPigletDuroc entity = new EntityPigletDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.SWAMP)) {
					if (chooser <= 2) {
						EntitySowLargeBlack entity = new EntitySowLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityHogLargeBlack entity = new EntityHogLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityPigletLargeBlack entity = new EntityPigletLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}

				else if (chooser <= 2) {
					EntitySowYorkshire entity = new EntitySowYorkshire(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 3) {
					EntityHogYorkshire entity = new EntityHogYorkshire(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 4) {
					EntityPigletYorkshire entity = new EntityPigletYorkshire(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}

		}
		else if (event.getEntity() instanceof EntityAnimaniaPig && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int pigCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaPig) {
					num++;
				}
			}
			pigCount = num;

			//System.out.println("Animania Pig count: " + pigCount);

			EntityAnimaniaPig checkTamed = (EntityAnimaniaPig) event.getEntity();
			if (pigCount >= AnimaniaConfig.spawn.spawnLimitPigs && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}


		}
		else if (AnimaniaConfig.gameRules.replaceVanillaSheep && event.getEntity().getClass().equals(EntitySheep.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {

				event.setCanceled(true);
				event.isCanceled();
				event.getEntity().setDead();
			}

			Biome biome = event.getWorld().getBiome(pos);
			List list = worldIn.loadedEntityList;


			int sheepCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaSheep) {
					num++;
				}
			}
			sheepCount = num;

			int chooser = 0;
			if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				chooser = rand.nextInt(10);
			else
				chooser = rand.nextInt(5);

			//System.out.println("Sheep count: " + sheepCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaSheep && sheepCount < AnimaniaConfig.spawn.spawnLimitSheep && !event.getEntity().hasCustomName())
				if (BiomeDictionary.hasType(biome, Type.PLAINS)) {
					int chooser2 = rand.nextInt(2);
					if (chooser2 == 0) {
						if (chooser <= 2) {
							EntityEweDorset entity = new EntityEweDorset(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 3) {
							EntityRamDorset entity = new EntityRamDorset(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 4) {
							EntityLambDorset entity = new EntityLambDorset(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
					} else { 

						if (chooser <= 2) {
							EntityEweFriesian entity = new EntityEweFriesian(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 3) {
							EntityRamFriesian entity = new EntityRamFriesian(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
						else if (chooser == 4) {
							EntityLambFriesian entity = new EntityLambFriesian(worldIn);
							entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
							worldIn.spawnEntity(entity);
						}
					}
				}

				else if (BiomeDictionary.hasType(biome, Type.SAVANNA)) {
					if (chooser <= 2) {
						EntityEweSuffolk entity = new EntityEweSuffolk(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRamSuffolk entity = new EntityRamSuffolk(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityLambSuffolk entity = new EntityLambSuffolk(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.HILLS)) {
					if (chooser <= 2) {
						EntityEweDorset entity = new EntityEweDorset(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRamDorset entity = new EntityRamDorset(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityLambDorset entity = new EntityLambDorset(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.DRY)) {
					if (chooser <= 2) {
						EntityEweMerino entity = new EntityEweMerino(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRamMerino entity = new EntityRamMerino(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityLambMerino entity = new EntityLambMerino(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (chooser <= 2) {
					EntityEweFriesian entity = new EntityEweFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 3) {
					EntityRamFriesian entity = new EntityRamFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 4) {
					EntityLambFriesian entity = new EntityLambFriesian(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}

		}
		else if (event.getEntity() instanceof EntityAnimaniaSheep && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int sheepCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaSheep) {
					num++;
				}
			}
			sheepCount = num;
			//System.out.println("Animania Sheep count: " + sheepCount);

			EntityAnimaniaSheep checkTamed = (EntityAnimaniaSheep) event.getEntity();
			if (sheepCount >= AnimaniaConfig.spawn.spawnLimitSheep && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

		}
		else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote) {
			if (!event.getEntity().hasCustomName() && !event.getEntity().isBeingRidden()) {
				EntityChicken bob = (EntityChicken) event.getEntity();
				if (!bob.isChickenJockey()) { 
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

			Biome biome = event.getWorld().getBiome(pos);
			List list = worldIn.loadedEntityList;

			int chickenCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaChicken) {
					num++;
				}
			}
			chickenCount = num;

			//System.out.println("Chicken count: " + chickenCount);

			int chooser = 0;
			if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				chooser = rand.nextInt(10);
			else
				chooser = rand.nextInt(5);

			if (AnimaniaConfig.spawn.spawnAnimaniaChickens && chickenCount < AnimaniaConfig.spawn.spawnLimitChickens && !event.getEntity().hasCustomName())
				if (BiomeDictionary.hasType(biome, Type.JUNGLE)) {
					if (chooser <= 2) {
						EntityHenOrpington entity = new EntityHenOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRoosterOrpington entity = new EntityRoosterOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityChickOrpington entity = new EntityChickOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.HILLS)) {
					if (chooser <= 2) {
						EntityHenPlymouthRock entity = new EntityHenPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRoosterPlymouthRock entity = new EntityRoosterPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityChickPlymouthRock entity = new EntityChickPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.FOREST)) {
					if (chooser <= 2) {
						EntityHenRhodeIslandRed entity = new EntityHenRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRoosterRhodeIslandRed entity = new EntityRoosterRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityChickRhodeIslandRed entity = new EntityChickRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.FOREST)) {
					if (chooser <= 2) {
						EntityHenWyandotte entity = new EntityHenWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRoosterWyandotte entity = new EntityRoosterWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityChickWyandotte entity = new EntityChickWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (chooser <= 2) {
					EntityHenLeghorn entity = new EntityHenLeghorn(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 3) {
					EntityRoosterLeghorn entity = new EntityRoosterLeghorn(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 4) {
					EntityChickLeghorn entity = new EntityChickLeghorn(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}

		}
		else if (event.getEntity() instanceof EntityAnimaniaChicken && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int chickenCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaChicken) {
					num++;
				}
			}
			chickenCount = num;

			//System.out.println("Chicken count: " + chickenCount);

			EntityAnimaniaChicken checkTamed = (EntityAnimaniaChicken) event.getEntity();
			if (chickenCount >= AnimaniaConfig.spawn.spawnLimitChickens && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
					//System.out.println("Removed animania chicken: " + checkTamed);
				}
			}

		}
		else if (AnimaniaConfig.gameRules.replaceVanillaRabbits && event.getEntity().getClass().equals(EntityRabbit.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				event.isCanceled();
				event.getEntity().setDead();
			}

			Biome biome = event.getWorld().getBiome(pos);
			List list = worldIn.loadedEntityList;

			int rabbitCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaRabbit) {
					num++;
				}
			}
			rabbitCount = num;

			if (AnimaniaConfig.spawn.spawnAnimaniaRabbits && rabbitCount < AnimaniaConfig.spawn.spawnLimitRabbits && !event.getEntity().hasCustomName()) {

				int chooser = 0;
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
					chooser = rand.nextInt(10);
				else
					chooser = rand.nextInt(5);

				if (BiomeDictionary.hasType(biome, Type.FOREST)) {
					if (chooser <= 2) {
						EntityRabbitBuckCottontail entity = new EntityRabbitBuckCottontail(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRabbitDoeCottontail entity = new EntityRabbitDoeCottontail(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityRabbitKitCottontail entity = new EntityRabbitKitCottontail(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.SAVANNA) || BiomeDictionary.hasType(biome, Type.SANDY) || BiomeDictionary.hasType(biome, Type.MESA)) {
					if (chooser <= 2) {
						EntityRabbitBuckJack entity = new EntityRabbitBuckJack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRabbitDoeJack entity = new EntityRabbitDoeJack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityRabbitKitJack entity = new EntityRabbitKitJack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.HILLS) || BiomeDictionary.hasType(biome, Type.MOUNTAIN)) {
					if (chooser <= 2) {
						EntityRabbitBuckHavana entity = new EntityRabbitBuckHavana(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRabbitDoeHavana entity = new EntityRabbitDoeHavana(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityRabbitKitHavana entity = new EntityRabbitKitHavana(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (BiomeDictionary.hasType(biome, Type.SNOWY) || BiomeDictionary.hasType(biome, Type.COLD)) {
					if (chooser <= 2) {
						EntityRabbitDoeChinchilla entity = new EntityRabbitDoeChinchilla(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 3) {
						EntityRabbitBuckChinchilla entity = new EntityRabbitBuckChinchilla(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
					else if (chooser == 4) {
						EntityRabbitKitChinchilla entity = new EntityRabbitKitChinchilla(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
				else if (chooser <= 2) {
					EntityRabbitBuckCottontail entity = new EntityRabbitBuckCottontail(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 3) {
					EntityRabbitDoeCottontail entity = new EntityRabbitDoeCottontail(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}
				else if (chooser == 4) {
					EntityRabbitKitCottontail entity = new EntityRabbitKitCottontail(worldIn);
					entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
					worldIn.spawnEntity(entity);
				}

			}

		}
		else if (event.getEntity() instanceof EntityAnimaniaRabbit && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int rabbitCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaRabbit) {
					num++;
				}
			}
			rabbitCount = num;
			//System.out.println("Animania Rabbit count: " + rabbitCount);

			EntityAnimaniaRabbit checkTamed = (EntityAnimaniaRabbit) event.getEntity();
			if (rabbitCount >= AnimaniaConfig.spawn.spawnLimitRabbits && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

		}
		else if (event.getEntity() instanceof EntityHedgehogBase && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int hedgehogCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityHedgehogBase) {
					num++;
				}
			}
			hedgehogCount = num;
			//System.out.println("Hedgehog count: " + hedgehogCount);

			EntityHedgehogBase checkTamed = (EntityHedgehogBase) event.getEntity();
			if (hedgehogCount >= AnimaniaConfig.spawn.spawnLimitHedgehogs && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

		}
		else if (event.getEntity() instanceof EntityHamster && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int hamsterCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityHamster) {
					num++;
				}
			}
			hamsterCount = num;

			//System.out.println("Hamster count: " + hamsterCount);

			if (hamsterCount >= AnimaniaConfig.spawn.spawnLimitHamsters && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				if (!event.getEntity().hasCustomName()) {
					EntityHamster checkTamed = (EntityHamster) event.getEntity();
					if (!checkTamed.isInLove() && checkTamed.isTamed() && checkTamed.getGrowingAge() == 0) {
						event.setCanceled(true);
						event.isCanceled();
						event.getEntity().setDead();
					}
				}
		}
		else if (event.getEntity() instanceof EntityFerretBase && !worldIn.isRemote) {

			List list = worldIn.loadedEntityList;

			int ferretCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityFerretBase) {
					num++;
				}
			}
			ferretCount = num;

			//System.out.println("Ferret count: " + ferretCount);

			EntityFerretBase checkTamed = (EntityFerretBase) event.getEntity();
			if (ferretCount >= AnimaniaConfig.spawn.spawnLimitFerrets && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}


		}
		else if (event.getEntity() instanceof EntityAmphibian && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int amphibianCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAmphibian) {
					num++;
				}
			}
			amphibianCount = num;

			//System.out.println("Amphibian Count: " + amphibianCount);
			EntityAmphibian checkTamed = (EntityAmphibian) event.getEntity();
			if (amphibianCount >= AnimaniaConfig.spawn.spawnLimitAmphibians && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}


		}
		else if (event.getEntity() instanceof EntityAnimaniaHorse && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int horseCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaHorse) {
					num++;
				}
			}
			horseCount = num;

			//System.out.println("Horse Count: " + horseCount);

			EntityAnimaniaHorse checkTamed = (EntityAnimaniaHorse) event.getEntity();
			if (horseCount >= AnimaniaConfig.spawn.spawnLimitHorses && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

		}
		else if (AnimaniaConfig.gameRules.replaceVanillaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				event.isCanceled();
				event.getEntity().setDead();
			}
		}
		else if (event.getEntity() instanceof EntityAnimaniaPeacock && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int peacockCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaPeacock) {
					num++;
				}
			}
			peacockCount = num;

			//System.out.println("Peacock count: " + peacockCount);

			EntityAnimaniaPeacock checkTamed = (EntityAnimaniaPeacock) event.getEntity();
			if (peacockCount >= AnimaniaConfig.spawn.spawnLimitPeacocks && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}


		}
		else if ((event.getEntity() instanceof EntityAnimaniaGoat) && !worldIn.isRemote) {
			List list = worldIn.loadedEntityList;

			int goatCount = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof EntityAnimaniaGoat) {
					num++;
				}
			}
			goatCount = num;

			//System.out.println("Goat count: " + goatCount);

			EntityAnimaniaGoat checkTamed = (EntityAnimaniaGoat) event.getEntity();
			if (goatCount >= AnimaniaConfig.spawn.spawnLimitGoats && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				if (!checkTamed.hasCustomName() && checkTamed.getAge() == 0) {
					event.setCanceled(true);
					event.isCanceled();
					event.getEntity().setDead();
				}
			}

		}
		else if (event.getEntity().getClass().equals(EntityZombie.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote) {
					EntityZombie ez = (EntityZombie) event.getEntity();
					EntityHenLeghorn entitychicken1 = new EntityHenLeghorn(worldIn);
					entitychicken1.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					entitychicken1.setChickenJockey(true);
					worldIn.spawnEntity(entitychicken1);
					ez.setChild(true);
					ez.startRiding(entitychicken1);

					//System.out.println("zombie");
				}

			}

		}
		else if (event.getEntity().getClass().equals(EntityPigZombie.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote) {
					EntityPigZombie ez = (EntityPigZombie) event.getEntity();
					EntityPigletYorkshire ep = new EntityPigletYorkshire(worldIn);
					ep.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					worldIn.spawnEntity(ep);
					ez.setChild(true);
					ez.startRiding(ep);

					//System.out.println("pig zombie");
				}

			}

		}
		else if (event.getEntity().getClass().equals(EntitySkeleton.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote) {
					EntitySkeleton ez = (EntitySkeleton) event.getEntity();
					EntityBullFriesian ef = new EntityBullFriesian(worldIn);
					ef.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					worldIn.spawnEntity(ef);
					ez.startRiding(ef);
					ef.updatePassenger(ez);

					//System.out.println("skelly");
				}
			}
		} 
	}

	public int randomSign(int value) {

		int sign = 1;
		Random rand = new Random();
		boolean signchk = rand.nextBoolean();
		if (signchk == true)
			sign = sign * -1;
		value = value * sign;
		return value;

	}

}
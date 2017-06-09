package com.animania.common.events;

import java.util.Random;

import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
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
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
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
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventReplaceSpawnAnimals
{
	@SubscribeEvent(priority = EventPriority.NORMAL)

	public void onEntitySpawn(EntityJoinWorldEvent event) {

		BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
		Biome biome = event.getWorld().getBiome(pos);
		World worldIn = event.getWorld();
		Random rand = new Random();

		if (AnimaniaConfig.gameRules.replaceVanillaCows && event.getEntity().getClass().equals(EntityCow.class) && !worldIn.isRemote) {
			if (!event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}

			int cowCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityCowAngus.class) || entity.getClass().equals(EntityBullAngus.class)
						|| entity.getClass().equals(EntityCalfAngus.class) || entity.getClass().equals(EntityCowFriesian.class)
						|| entity.getClass().equals(EntityBullFriesian.class) || entity.getClass().equals(EntityCalfFriesian.class)
						|| entity.getClass().equals(EntityCowHereford.class) || entity.getClass().equals(EntityBullHereford.class)
						|| entity.getClass().equals(EntityCalfHereford.class) || entity.getClass().equals(EntityCowHolstein.class)
						|| entity.getClass().equals(EntityBullHolstein.class) || entity.getClass().equals(EntityCalfHolstein.class)
						|| entity.getClass().equals(EntityCowLonghorn.class) || entity.getClass().equals(EntityBullLonghorn.class)
						|| entity.getClass().equals(EntityCalfLonghorn.class))
					cowCount = cowCount + 1;
			}

			// System.out.println("Cow count: " + cowCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaCows && cowCount < AnimaniaConfig.spawn.spawnLimitCows) {

				int chooser = 0;
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
					chooser = rand.nextInt(10);
				else
					chooser = rand.nextInt(5);

				if (biome.equals(Type.FOREST)) {
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
				else if (biome.equals(Type.SAVANNA)) {
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
				else if (biome.equals(Type.HILLS)) {
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
				else if (biome.equals(Type.LUSH)) {
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
		else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}

			int pigCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {

				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntitySowHampshire.class) || entity.getClass().equals(EntityHogHampshire.class)
						|| entity.getClass().equals(EntityPigletHampshire.class) || entity.getClass().equals(EntitySowLargeBlack.class)
						|| entity.getClass().equals(EntityHogLargeBlack.class) || entity.getClass().equals(EntityPigletLargeBlack.class)
						|| entity.getClass().equals(EntitySowLargeWhite.class) || entity.getClass().equals(EntitySowLargeWhite.class)
						|| entity.getClass().equals(EntitySowLargeWhite.class) || entity.getClass().equals(EntitySowOldSpot.class)
						|| entity.getClass().equals(EntitySowOldSpot.class) || entity.getClass().equals(EntitySowOldSpot.class)
						|| entity.getClass().equals(EntitySowDuroc.class) || entity.getClass().equals(EntitySowDuroc.class)
						|| entity.getClass().equals(EntitySowDuroc.class) || entity.getClass().equals(EntitySowYorkshire.class)
						|| entity.getClass().equals(EntitySowYorkshire.class) || entity.getClass().equals(EntitySowYorkshire.class))
					pigCount = pigCount + 1;
			}

			int chooser = 0;
			if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				chooser = rand.nextInt(10);
			else
				chooser = rand.nextInt(5);

			// System.out.println("Pig count: " + pigCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaPigs && pigCount < AnimaniaConfig.spawn.spawnLimitPigs)
				if (biome.equals(Type.FOREST)) {
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
				}
				else if (biome.equals(Type.JUNGLE)) {
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
				else if (biome.equals(Type.SWAMP)) {
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
				else if (biome.equals(Type.FOREST)) {
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
		else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote) {
			if (!event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}

			int chickenCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityChickLeghorn.class) || entity.getClass().equals(EntityHenLeghorn.class)
						|| entity.getClass().equals(EntityRoosterLeghorn.class) || entity.getClass().equals(EntityChickOrpington.class)
						|| entity.getClass().equals(EntityHenOrpington.class) || entity.getClass().equals(EntityRoosterOrpington.class)
						|| entity.getClass().equals(EntityChickWyandotte.class) || entity.getClass().equals(EntityHenWyandotte.class)
						|| entity.getClass().equals(EntityRoosterWyandotte.class) || entity.getClass().equals(EntityChickRhodeIslandRed.class)
						|| entity.getClass().equals(EntityHenRhodeIslandRed.class) || entity.getClass().equals(EntityRoosterRhodeIslandRed.class)
						|| entity.getClass().equals(EntityChickPlymouthRock.class) || entity.getClass().equals(EntityHenPlymouthRock.class)
						|| entity.getClass().equals(EntityRoosterPlymouthRock.class))
					chickenCount = chickenCount + 1;
			}

			int chooser = 0;
			if (worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				chooser = rand.nextInt(10);
			else
				chooser = rand.nextInt(5);

			// System.out.println("Chicken count: " + chickenCount);

			if (AnimaniaConfig.spawn.spawnAnimaniaChickens && chickenCount < AnimaniaConfig.spawn.spawnLimitChickens)
				if (biome.equals(Type.JUNGLE)) {
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
				else if (biome.equals(Type.HILLS)) {
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
				else if (biome.equals(Type.FOREST)) {
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
				else if (biome.equals(Type.FOREST)) {
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
		else if ((event.getEntity().getClass().equals(EntityHedgehog.class) || event.getEntity().getClass().equals(EntityHedgehogAlbino.class))
				&& !worldIn.isRemote) {

			int hedgehogCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityHedgehog.class) || entity.getClass().equals(EntityHedgehogAlbino.class))
					hedgehogCount++;
			}

			// System.out.println("Hedgehog count: " + hedgehogCount);

			if (hedgehogCount >= AnimaniaConfig.spawn.spawnLimitHedgehogs && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null
					&& !event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}
		}
		else if (event.getEntity().getClass().equals(EntityHamster.class) && !worldIn.isRemote && !event.getEntity().hasCustomName()) {

			int hamsterCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityHamster.class))
					hamsterCount = hamsterCount + 1;
			}

			// System.out.println("Hamster count: " + hamsterCount);

			if (hamsterCount >= AnimaniaConfig.spawn.spawnLimitHamsters && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				if (!event.getEntity().hasCustomName()) {
					event.getEntity().setDead();
					event.setCanceled(true);
					event.isCanceled();
				}
		}
		else if ((event.getEntity().getClass().equals(EntityFerretGrey.class) || event.getEntity().getClass().equals(EntityFerretWhite.class))
				&& !worldIn.isRemote) {

			int ferretCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityFerretGrey.class) || entity.getClass().equals(EntityFerretWhite.class))
					ferretCount = ferretCount + 1;
			}

			// System.out.println("Ferret count: " + ferretCount);

			if (ferretCount >= AnimaniaConfig.spawn.spawnLimitFerrets && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null
					&& !event.getEntity().hasCustomName())
				if (!event.getEntity().hasCustomName()) {
					event.getEntity().setDead();
					event.setCanceled(true);
					event.isCanceled();
				}

		}
		else if ((event.getEntity().getClass().equals(EntityFrogs.class) || event.getEntity().getClass().equals(EntityToad.class) || event.getEntity().getClass().equals(EntityDartFrogs.class))
				&& !worldIn.isRemote) {

			int animalCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityFrogs.class) || entity.getClass().equals(EntityToad.class) || entity.getClass().equals(EntityDartFrogs.class))
					animalCount = animalCount + 1;
			}

			//System.out.println("animal Count: " + animalCount);

			if (animalCount >= AnimaniaConfig.spawn.spawnLimitAmphibians && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				if (!event.getEntity().hasCustomName()) {
					event.getEntity().setDead();
					event.setCanceled(true);
					event.isCanceled();
				}

		}
		else if ((event.getEntity().getClass().equals(EntityStallionDraftHorse.class) || event.getEntity().getClass().equals(EntityMareDraftHorse.class) || event.getEntity().getClass().equals(EntityFoalDraftHorse.class))
				&& !worldIn.isRemote) {

			int animalCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityStallionDraftHorse.class) || entity.getClass().equals(EntityMareDraftHorse.class) || entity.getClass().equals(EntityFoalDraftHorse.class))
					animalCount = animalCount + 1;
			}

			//System.out.println("animal Count: " + animalCount);

			if (animalCount >= AnimaniaConfig.spawn.spawnLimitHorses + 10 && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null)
				if (!event.getEntity().hasCustomName()) {
					event.getEntity().setDead();
					event.setCanceled(true);
					event.isCanceled();
				}

		}
		else if ((event.getEntity().getClass().equals(EntityPeacockBlue.class) || event.getEntity().getClass().equals(EntityPeacockWhite.class)
				|| event.getEntity().getClass().equals(EntityPeafowlBlue.class) || event.getEntity().getClass().equals(EntityPeafowlWhite.class)
				|| event.getEntity().getClass().equals(EntityPeachickBlue.class) || event.getEntity().getClass().equals(EntityPeachickWhite.class))
				&& !worldIn.isRemote) {

			int peacockCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityPeacockBlue.class) || entity.getClass().equals(EntityPeacockWhite.class)
						|| entity.getClass().equals(EntityPeafowlBlue.class) || entity.getClass().equals(EntityPeafowlWhite.class)
						|| entity.getClass().equals(EntityPeachickBlue.class) || entity.getClass().equals(EntityPeachickWhite.class)) {
					EntityLivingBase elb = (EntityLivingBase) entity;
					peacockCount = peacockCount + 1;
				}
			}

			// System.out.println("Peacock count: " + peacockCount);

			if (peacockCount >= AnimaniaConfig.spawn.spawnLimitPeacocks && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null
					&& !event.getEntity().hasCustomName())
				if (!event.getEntity().hasCustomName()) {
					event.getEntity().setDead();
					event.setCanceled(true);
					event.isCanceled();
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
				}
			}
		} 
		else if (event.getEntity() instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer)event.getEntity();	
			final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (props != null) {
				if (props.getMounted()) {
					if (props.getPetType().equals("Hamster")) {
						EntityHamster er = new EntityHamster(player.world);
						er.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
						er.setColorNumber(props.getPetColor());
						er.setCustomNameTag(props.getPetName());
						er.setOwnerId(player.getPersistentID());
						er.setTamed(true);
						er.setIsTamed(true);
						player.world.spawnEntity(er);
						props.setMounted(false);
					} else if (props.getPetType().equals("FerretWhite")) {
						EntityFerretWhite er = new EntityFerretWhite(player.world);
						er.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
						er.setCustomNameTag(props.getPetName());
						er.setOwnerId(player.getPersistentID());
						er.setTamed(true);
						er.setIsTamed(true);
						player.world.spawnEntity(er);
						props.setMounted(false);
					} else if (props.getPetType().equals("FerretGrey")) {
						EntityFerretGrey er = new EntityFerretGrey(player.world);
						er.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
						er.setCustomNameTag(props.getPetName());
						er.setOwnerId(player.getPersistentID());
						er.setTamed(true);
						er.setIsTamed(true);
						player.world.spawnEntity(er);
						props.setMounted(false);
					} else if (props.getPetType().equals("Hedgehog")) {
						EntityHedgehog er = new EntityHedgehog(player.world);
						er.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
						er.setCustomNameTag(props.getPetName());
						er.setOwnerId(player.getPersistentID());
						er.setTamed(true);
						er.setIsTamed(true);
						player.world.spawnEntity(er);
						props.setMounted(false);
					} else if (props.getPetType().equals("HedgehogAlbino")) {
						EntityHedgehogAlbino er = new EntityHedgehogAlbino(player.world);
						er.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
						er.setCustomNameTag(props.getPetName());
						er.setOwnerId(player.getPersistentID());
						er.setTamed(true);
						er.setIsTamed(true);
						player.world.spawnEntity(er);
						props.setMounted(false);
					}
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
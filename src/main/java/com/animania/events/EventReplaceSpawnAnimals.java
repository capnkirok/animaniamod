package com.animania.events;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.animania.Animania;
import com.animania.entities.chickens.EntityChickLeghorn;
import com.animania.entities.chickens.EntityChickOrpington;
import com.animania.entities.chickens.EntityChickPlymouthRock;
import com.animania.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.entities.chickens.EntityChickWyandotte;
import com.animania.entities.chickens.EntityHenLeghorn;
import com.animania.entities.chickens.EntityHenOrpington;
import com.animania.entities.chickens.EntityHenPlymouthRock;
import com.animania.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.entities.chickens.EntityHenWyandotte;
import com.animania.entities.chickens.EntityRoosterLeghorn;
import com.animania.entities.chickens.EntityRoosterOrpington;
import com.animania.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.entities.chickens.EntityRoosterWyandotte;
import com.animania.entities.cows.EntityBullAngus;
import com.animania.entities.cows.EntityBullFriesian;
import com.animania.entities.cows.EntityBullHereford;
import com.animania.entities.cows.EntityBullHolstein;
import com.animania.entities.cows.EntityBullLonghorn;
import com.animania.entities.cows.EntityCalfAngus;
import com.animania.entities.cows.EntityCalfFriesian;
import com.animania.entities.cows.EntityCalfHereford;
import com.animania.entities.cows.EntityCalfHolstein;
import com.animania.entities.cows.EntityCalfLonghorn;
import com.animania.entities.cows.EntityCowAngus;
import com.animania.entities.cows.EntityCowFriesian;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.entities.cows.EntityCowHolstein;
import com.animania.entities.cows.EntityCowLonghorn;
import com.animania.entities.peacocks.EntityPeachickBlue;
import com.animania.entities.peacocks.EntityPeachickWhite;
import com.animania.entities.peacocks.EntityPeacockBlue;
import com.animania.entities.peacocks.EntityPeacockWhite;
import com.animania.entities.peacocks.EntityPeafowlBlue;
import com.animania.entities.peacocks.EntityPeafowlWhite;
import com.animania.entities.pigs.EntityHogDuroc;
import com.animania.entities.pigs.EntityHogHampshire;
import com.animania.entities.pigs.EntityHogLargeBlack;
import com.animania.entities.pigs.EntityHogOldSpot;
import com.animania.entities.pigs.EntityHogYorkshire;
import com.animania.entities.pigs.EntityPigletDuroc;
import com.animania.entities.pigs.EntityPigletHampshire;
import com.animania.entities.pigs.EntityPigletLargeBlack;
import com.animania.entities.pigs.EntityPigletOldSpot;
import com.animania.entities.pigs.EntityPigletYorkshire;
import com.animania.entities.pigs.EntitySowDuroc;
import com.animania.entities.pigs.EntitySowHampshire;
import com.animania.entities.pigs.EntitySowLargeBlack;
import com.animania.entities.pigs.EntitySowLargeWhite;
import com.animania.entities.pigs.EntitySowOldSpot;
import com.animania.entities.pigs.EntitySowYorkshire;
import com.animania.entities.rodents.EntityFerretGrey;
import com.animania.entities.rodents.EntityFerretWhite;
import com.animania.entities.rodents.EntityHamster;
import com.animania.entities.rodents.EntityHedgehog;
import com.animania.entities.rodents.EntityHedgehogAlbino;

public class EventReplaceSpawnAnimals {
	@SubscribeEvent(priority = EventPriority.NORMAL)

	public void onEntitySpawn(EntityJoinWorldEvent event)
	{

		BlockPos pos = new BlockPos(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
		Biome biome = event.getWorld().getBiome(pos);
		World worldIn = event.getWorld();
		Random rand = new Random();

		if(Animania.replaceVanillaCows && event.getEntity().getClass().equals(EntityCow.class) && !worldIn.isRemote) {
			event.getEntity().setDead();
			event.setCanceled(true);
			event.isCanceled();

			int cowCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityCowAngus.class) || entity.getClass().equals(EntityBullAngus.class) || entity.getClass().equals(EntityCalfAngus.class)
						|| entity.getClass().equals(EntityCowFriesian.class) || entity.getClass().equals(EntityBullFriesian.class) || entity.getClass().equals(EntityCalfFriesian.class) 
						|| entity.getClass().equals(EntityCowHereford.class) || entity.getClass().equals(EntityCowHereford.class) || entity.getClass().equals(EntityCowHereford.class)
						|| entity.getClass().equals(EntityCowHolstein.class) || entity.getClass().equals(EntityCowHolstein.class) || entity.getClass().equals(EntityCowHolstein.class) 
						|| entity.getClass().equals(EntityCowLonghorn.class) || entity.getClass().equals(EntityCowLonghorn.class) || entity.getClass().equals(EntityCowLonghorn.class)) {
					cowCount = cowCount + 1;
				}
			}

			//System.out.println("Cow count: " + cowCount);

			if (Animania.spawnAnimaniaCows && cowCount < Animania.spawnLimitCows ) {

				int chooser = 0;
				if(worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
					chooser = rand.nextInt(10);
				} else {
					chooser = rand.nextInt(5);
				}

				if (biome.equals(Type.FOREST)) {
					if (chooser <= 2) { 
						EntityCowHolstein entity = new EntityCowHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityBullHolstein entity = new EntityBullHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityCalfHolstein entity = new EntityCalfHolstein(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.SAVANNA)) {
					if (chooser <= 2) { 
						EntityCowLonghorn entity = new EntityCowLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityBullLonghorn entity = new EntityBullLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityCalfLonghorn entity = new EntityCalfLonghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.HILLS)) {
					if (chooser <= 2) { 
						EntityCowHereford entity = new EntityCowHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityBullHereford entity = new EntityBullHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityCalfHereford entity = new EntityCalfHereford(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.DENSE.LUSH)) {
					if (chooser <= 2) { 
						EntityCowAngus entity = new EntityCowAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityBullAngus entity = new EntityBullAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityCalfAngus entity = new EntityCalfAngus(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else {

					if (chooser <= 2) { 
						EntityCowFriesian entity = new EntityCowFriesian(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityBullFriesian entity = new EntityBullFriesian(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityCalfFriesian entity = new EntityCalfFriesian(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}

			}

		} else if(Animania.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote) {

			event.getEntity().setDead();
			event.setCanceled(true);
			event.isCanceled();

			int pigCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {

				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntitySowHampshire.class) || entity.getClass().equals(EntityHogHampshire.class) || entity.getClass().equals(EntityPigletHampshire.class)
						|| entity.getClass().equals(EntitySowLargeBlack.class) || entity.getClass().equals(EntityHogLargeBlack.class) || entity.getClass().equals(EntityPigletLargeBlack.class) 
						|| entity.getClass().equals(EntitySowLargeWhite.class) || entity.getClass().equals(EntitySowLargeWhite.class) || entity.getClass().equals(EntitySowLargeWhite.class)
						|| entity.getClass().equals(EntitySowOldSpot.class) || entity.getClass().equals(EntitySowOldSpot.class) || entity.getClass().equals(EntitySowOldSpot.class) 
						|| entity.getClass().equals(EntitySowDuroc.class) || entity.getClass().equals(EntitySowDuroc.class) || entity.getClass().equals(EntitySowDuroc.class) 
						|| entity.getClass().equals(EntitySowYorkshire.class) || entity.getClass().equals(EntitySowYorkshire.class) || entity.getClass().equals(EntitySowYorkshire.class)) {
					pigCount = pigCount + 1;
				}
			}

			int chooser = 0;
			if(worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				chooser = rand.nextInt(10);
			} else {
				chooser = rand.nextInt(5);
			}

			//System.out.println("Pig count: " + pigCount);

			if (Animania.spawnAnimaniaPigs && pigCount < Animania.spawnLimitPigs) {
				if (biome.equals(Type.FOREST)) {
					if (chooser <= 2) { 
						EntitySowOldSpot entity = new EntitySowOldSpot(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityHogOldSpot entity = new EntityHogOldSpot(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityPigletOldSpot entity = new EntityPigletOldSpot(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.JUNGLE)) {
					if (chooser <= 2) { 
						EntitySowDuroc entity = new EntitySowDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityHogDuroc entity = new EntityHogDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityPigletDuroc entity = new EntityPigletDuroc(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.WET.SWAMP)) {
					if (chooser <= 2) { 
						EntitySowLargeBlack entity = new EntitySowLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityHogLargeBlack entity = new EntityHogLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityPigletLargeBlack entity = new EntityPigletLargeBlack(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.MOUNTAIN.FOREST)) {
					if (chooser <= 2) { 
						EntitySowHampshire entity = new EntitySowHampshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityHogHampshire entity = new EntityHogHampshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityPigletHampshire entity = new EntityPigletHampshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else {
					if (chooser <= 2) { 
						EntitySowYorkshire entity = new EntitySowYorkshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityHogYorkshire entity = new EntityHogYorkshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityPigletYorkshire entity = new EntityPigletYorkshire(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}
			}

		} else if(Animania.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote) {
			event.getEntity().setDead();
			event.setCanceled(true);
			event.isCanceled();

			int chickenCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityChickLeghorn.class) || entity.getClass().equals(EntityHenLeghorn.class) || entity.getClass().equals(EntityRoosterLeghorn.class)
						|| entity.getClass().equals(EntityChickOrpington.class) || entity.getClass().equals(EntityHenOrpington.class) || entity.getClass().equals(EntityRoosterOrpington.class)
						|| entity.getClass().equals(EntityChickWyandotte.class) || entity.getClass().equals(EntityHenWyandotte.class) || entity.getClass().equals(EntityRoosterWyandotte.class)
						|| entity.getClass().equals(EntityChickRhodeIslandRed.class) || entity.getClass().equals(EntityHenRhodeIslandRed.class) || entity.getClass().equals(EntityRoosterRhodeIslandRed.class)
						|| entity.getClass().equals(EntityChickPlymouthRock.class) || entity.getClass().equals(EntityHenPlymouthRock.class) || entity.getClass().equals(EntityRoosterPlymouthRock.class)) {

					chickenCount = chickenCount + 1;
				}
			}

			int chooser = 0;
			if(worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				chooser = rand.nextInt(10);
			} else {
				chooser = rand.nextInt(5);
			}

			//System.out.println("Chicken count: " + chickenCount);

			if (Animania.spawnAnimaniaChickens && chickenCount < Animania.spawnLimitChickens) {
				if (biome.equals(Type.JUNGLE)) {
					if (chooser <= 2) { 
						EntityHenOrpington entity = new EntityHenOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityRoosterOrpington entity = new EntityRoosterOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityChickOrpington entity = new EntityChickOrpington(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.HILLS)) {
					if (chooser <= 2) { 
						EntityHenPlymouthRock entity = new EntityHenPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityRoosterPlymouthRock entity = new EntityRoosterPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityChickPlymouthRock entity = new EntityChickPlymouthRock(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.FOREST)) {
					if (chooser <= 2) { 
						EntityHenRhodeIslandRed entity = new EntityHenRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityRoosterRhodeIslandRed entity = new EntityRoosterRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityChickRhodeIslandRed entity = new EntityChickRhodeIslandRed(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else if (biome.equals(Type.JUNGLE.FOREST)) {
					if (chooser <= 2) { 
						EntityHenWyandotte entity = new EntityHenWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityRoosterWyandotte entity = new EntityRoosterWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityChickWyandotte entity = new EntityChickWyandotte(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				} else {
					if (chooser <= 2) { 
						EntityHenLeghorn entity = new EntityHenLeghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 3) {
						EntityRoosterLeghorn entity = new EntityRoosterLeghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					} else if (chooser == 4) {
						EntityChickLeghorn entity = new EntityChickLeghorn(worldIn);
						entity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
						worldIn.spawnEntity(entity);
					}
				}

			}  

		} else if((event.getEntity().getClass().equals(EntityHedgehog.class) || event.getEntity().getClass().equals(EntityHedgehogAlbino.class)) && !worldIn.isRemote) {

			int hedgehogCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if(entity.getClass().equals(EntityHedgehog.class) || entity.getClass().equals(EntityHedgehogAlbino.class)) {
					hedgehogCount++;
				}
			}

			//System.out.println("Hedgehog count: " + hedgehogCount);

			if (hedgehogCount >= Animania.spawnLimitHedgehogs && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null && !event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}
		} else if(event.getEntity().getClass().equals(EntityHamster.class) && !worldIn.isRemote && !event.getEntity().hasCustomName()) {

			int hamsterCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if (entity.getClass().equals(EntityHamster.class)) {
					hamsterCount = hamsterCount + 1;
				}
			}

			//System.out.println("Hamster count: " + hamsterCount);

			if (hamsterCount >= Animania.spawnLimitHamsters && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}
		} else if((event.getEntity().getClass().equals(EntityFerretGrey.class) || event.getEntity().getClass().equals(EntityFerretWhite.class)) && !worldIn.isRemote) {

			int ferretCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if(entity.getClass().equals(EntityFerretGrey.class) || entity.getClass().equals(EntityFerretWhite.class)) {
					ferretCount = ferretCount + 1;
				}
			}

			//System.out.println("Ferret count: " + ferretCount);

			if (ferretCount >= Animania.spawnLimitFerrets && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null && !event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}

		} else if((event.getEntity().getClass().equals(EntityPeacockBlue.class) || event.getEntity().getClass().equals(EntityPeacockWhite.class) || event.getEntity().getClass().equals(EntityPeafowlBlue.class) || event.getEntity().getClass().equals(EntityPeafowlWhite.class) || event.getEntity().getClass().equals(EntityPeachickBlue.class) || event.getEntity().getClass().equals(EntityPeachickWhite.class)) && !worldIn.isRemote) {

			int peacockCount = 0;
			int esize = worldIn.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) worldIn.loadedEntityList.get(k);
				if ((entity.getClass().equals(EntityPeacockBlue.class) || entity.getClass().equals(EntityPeacockWhite.class) || entity.getClass().equals(EntityPeafowlBlue.class) || entity.getClass().equals(EntityPeafowlWhite.class) || entity.getClass().equals(EntityPeachickBlue.class) || entity.getClass().equals(EntityPeachickWhite.class))) {
					EntityLivingBase elb = (EntityLivingBase) entity;
					peacockCount = peacockCount + 1;
				}
			}

			//System.out.println("Peacock count: " + peacockCount);

			if (peacockCount >= Animania.spawnLimitPeacocks && worldIn.getClosestPlayerToEntity(event.getEntity(), 5) == null && !event.getEntity().hasCustomName()) {
				event.getEntity().setDead();
				event.setCanceled(true);
				event.isCanceled();
			}

		}


	}



	public int randomSign(int value) {

		int sign = 1;
		Random rand = new Random();
		boolean signchk = rand.nextBoolean();
		if (signchk == true) {
			sign = sign * -1;
		}
		value = value * sign;
		return value;

	}

}
package com.animania.common.events;

import java.util.Random;

import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

		if (AnimaniaConfig.gameRules.replaceVanillaCows && (event.getEntity().getClass().equals(EntityCow.class) || event.getEntity().getClass().equals(EntityMooshroom.class)) && !worldIn.isRemote) {
			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}


		} else if (AnimaniaConfig.gameRules.replaceVanillaPigs && event.getEntity().getClass().equals(EntityPig.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}

		} else if (AnimaniaConfig.gameRules.replaceVanillaChickens && event.getEntity().getClass().equals(EntityChicken.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}

			
		} else if (AnimaniaConfig.gameRules.replaceVanillaSheep && event.getEntity().getClass().equals(EntitySheep.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {

				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}

		} else if (AnimaniaConfig.gameRules.replaceVanillaRabbits && event.getEntity().getClass().equals(EntityRabbit.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}

		} else if (AnimaniaConfig.gameRules.replaceVanillaHorses && event.getEntity().getClass().equals(EntityHorse.class) && !worldIn.isRemote) {

			if (!event.getEntity().hasCustomName()) {
				event.setCanceled(true);
				//event.isCanceled();
				//event.getEntity().setDead();
			}


		} else if (event.getEntity().getClass().equals(EntityZombie.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60) {
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

		} else if (event.getEntity().getClass().equals(EntityPigZombie.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60) {
					EntityPigZombie ez = (EntityPigZombie) event.getEntity();
					EntityPigletYorkshire ep = new EntityPigletYorkshire(worldIn);
					ep.setLocationAndAngles(ez.posX, ez.posY, ez.posZ, ez.rotationYaw, 0.0F);
					worldIn.spawnEntity(ep);
					ez.setChild(true);
					ez.startRiding(ep);

					//System.out.println("pig zombie");
				}

			}

		} else if (event.getEntity().getClass().equals(EntitySkeleton.class) && AnimaniaConfig.gameRules.allowMobRiding) {

			if (worldIn.rand.nextFloat() < 0.05D) {
				if (worldIn.getClosestPlayerToEntity(event.getEntity(), 10) != null && !worldIn.isRemote && event.getEntity().getPosition().getY() > 60) {
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


}
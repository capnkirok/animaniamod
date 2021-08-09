package com.animania.common.events;

import com.animania.Animania;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Animania.MODID)
public class EntityEventHandler
{

	@SubscribeEvent
	public static void onEntityTakeDamage(LivingHurtEvent event)
	{
		float amount = event.getAmount();
		LivingEntity entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof AnimalEntity)
		{
			AnimalEntity animal = (AnimalEntity) entity;

			if (source == DamageSource.FALL)
				if (animal.isLeashed())
				{
					event.setAmount(amount * AnimaniaConfig.gameRules.fallDamageReduceMultiplier);
					animal.fallDistance = 0;
				}

		}

	}

	@SubscribeEvent
	public static void onEntityHit(LivingAttackEvent event)
	{
		float amount = event.getAmount();
		LivingEntity entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof LivingEntity && entity instanceof IAnimaniaAnimal)
		{
			LivingEntity animal = entity;

			if (animal.isRiding())
				event.setCanceled(true);
		}

		if (entity instanceof ISleeping)
		{
			ISleeping isleeping = (ISleeping) entity;
			if (isleeping.getSleeping())
			{
				if (source == DamageSource.STARVE)
				{
					event.setCanceled(true);
				}

				((ISleeping) entity).setSleeping(false);
			}
		}

		if (entity instanceof TameableEntity)
		{
			if (((TameableEntity) entity).isSit())
				((TameableEntity) entity).setSitting(false);
		}

	}

	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			ItemHandler.regItemEggColors(event.getWorld());
		}
	}

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();

		if (entity instanceof ISleeping && entity instanceof AnimalEntity)
		{
			ISleeping isleeping = (ISleeping) entity;
			if (isleeping.getSleeping() && ((AnimalEntity) entity).isLeashed())
				isleeping.setSleeping(false);
		}
	}

	@SubscribeEvent
	public static void removeSquidSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		World worldIn = (World) event.getWorld();
		Biome biome = event.getWorld().getBiome(pos);

		if (!AnimaniaConfig.gameRules.spawnFreshWaterSquids && event.getEntity().getClass().equals(SquidEntity.class) && !worldIn.isClientSide)
		{

			if (!AnimaniaHelper.hasBiomeType(biome, Type.OCEAN))
			{
				if (!event.getEntity().hasCustomName())
				{
					event.setResult(Result.DENY);
				}
			}

		}
	}

}

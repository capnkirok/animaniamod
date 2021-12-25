package com.animania.common.events;

import com.animania.Animania;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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

		if ((entity instanceof Animal animal) && (source == DamageSource.FALL && animal.isLeashed()))
		{
			event.setAmount(amount * AnimaniaConfig.gameRules.fallDamageReduceMultiplier);
			animal.fallDistance = 0;
		}

	}

	@SubscribeEvent
	public static void onEntityHit(LivingAttackEvent event)
	{
		LivingEntity entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof LivingEntity && entity instanceof IAnimaniaAnimal)
		{
			LivingEntity animal = entity;

			if (animal.isPassenger())
				event.setCanceled(true);
		}

		if ((entity instanceof ISleeping isleeping) && isleeping.getSleeping())
		{
			if (source == DamageSource.STARVE)
			{
				event.setCanceled(true);
			}

			((ISleeping) entity).setSleeping(false);
		}

		if ((entity instanceof TamableAnimal) && ((TamableAnimal) entity).isInSittingPose())
			((TamableAnimal) entity).setInSittingPose(false);

	}

	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		if (event.getEntity() instanceof Player)
		{
			ItemHandler.regItemEggColors(event.getLevel());
		}
	}

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		LivingEntity entity = event.getEntityLiving();

		if ((entity instanceof ISleeping isleeping && entity instanceof Animal) && (isleeping.getSleeping() && ((Animal) entity).isLeashed()))
			isleeping.setSleeping(false);
	}

	@SubscribeEvent
	public static void removeSquidSpawns(CheckSpawn event)
	{
		BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
		Level levelIn = (Level) event.getLevel();
		Biome biome = event.getLevel().getBiome(pos);

		if (!AnimaniaConfig.gameRules.spawnFreshWaterSquids && event.getEntity().getClass().equals(Squid.class) && !levelIn.isClientSide && !AnimaniaHelper.hasBiomeType(biome, Type.OCEAN))
		{
			if (!event.getEntity().hasCustomName())
			{
				event.setResult(Result.DENY);
			}
		}
	}

}

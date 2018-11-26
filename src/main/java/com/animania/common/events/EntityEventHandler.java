package com.animania.common.events;

import com.animania.common.api.interfaces.IAnimaniaAnimal;
import com.animania.common.api.interfaces.ISleeping;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler
{

	@SubscribeEvent
	public void onEntityTakeDamage(LivingHurtEvent event)
	{
		float amount = event.getAmount();
		EntityLivingBase entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof EntityAnimal)
		{
			EntityAnimal animal = (EntityAnimal) entity;

			if (source == DamageSource.FALL)
				if (animal.getLeashed())
				{
					event.setAmount(amount * AnimaniaConfig.gameRules.fallDamageReduceMultiplier);
					animal.fallDistance = 0;
				}

		}

	}

	@SubscribeEvent
	public void onEntityHit(LivingAttackEvent event)
	{
		float amount = event.getAmount();
		EntityLivingBase entity = event.getEntityLiving();
		DamageSource source = event.getSource();

		if (entity instanceof EntityLivingBase && entity instanceof IAnimaniaAnimal)
		{
			EntityLivingBase animal = (EntityLivingBase) entity;

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

		if (entity instanceof EntityTameable)
		{
			if (((EntityTameable) entity).isSitting())
				((EntityTameable) entity).setSitting(false);
		}

	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			ItemHandler.regItemEggColors(event.getWorld());
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if (entity instanceof ISleeping && entity instanceof EntityAnimal)
		{
			ISleeping isleeping = (ISleeping) entity;
			if (isleeping.getSleeping() && ((EntityAnimal) entity).getLeashed())
				isleeping.setSleeping(false);
		}
	}

}

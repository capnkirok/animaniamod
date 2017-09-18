package com.animania.common.events;

import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEventHandler
{

    @SubscribeEvent
    public void onEntityTakeDamage(LivingHurtEvent event) {
        float amount = event.getAmount();
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (entity instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) entity;

            if (source == DamageSource.FALL)
                if (animal.getLeashed()) {
                    event.setAmount(amount * AnimaniaConfig.gameRules.fallDamageReduceMultiplier);
                    animal.fallDistance = 0;
                }

        }

    }

    @SubscribeEvent
    public void onEntityHit(LivingAttackEvent event) {
        float amount = event.getAmount();
        EntityLivingBase entity = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (entity instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) entity;

            if (animal.isRiding())
                event.setCanceled(true);
        }

    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
    	if(event.getEntity() instanceof EntityPlayerSP)
    	{
            ItemHandler.regItemEggColors();
    	}
    }

}

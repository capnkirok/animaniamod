package com.animania.common.events;

import com.animania.common.capabilities.CapabilityPlayerProvider;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLoadHandler
{
	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)            

	{
		if (event.getGenericType() instanceof EntityPlayer) {
			event.addCapability(CapabilityRefs.toResource("AnimaniaPlayerCaps"), new CapabilityPlayerProvider());
		}

	}

	@SubscribeEvent
	public void onClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath()) {
			return;
		}

		final ICapabilityPlayer propsNew = CapabilityRefs.getPlayerCaps(event.getEntityPlayer());
		propsNew.read(event.getOriginal());
		
	}	

}
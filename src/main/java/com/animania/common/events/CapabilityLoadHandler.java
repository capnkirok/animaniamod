package com.animania.common.events;

import com.animania.Animania;
import com.animania.common.capabilities.CapabilityPlayerProvider;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.network.client.CapSyncPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLoadHandler
{
	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(CapabilityRefs.toResource("AnimaniaPlayerCaps"), new CapabilityPlayerProvider());
		}

	}

	@SubscribeEvent
	public void onClone(PlayerEvent.Clone event)
	{
		if (!event.isWasDeath())
		{
			return;
		}

		final ICapabilityPlayer propsNew = CapabilityRefs.getPlayerCaps(event.getEntityPlayer());
		propsNew.read(event.getOriginal());

	}

	@SubscribeEvent
	public void startTracking(StartTracking event)
	{
		Entity target = event.getTarget();
		EntityPlayer viewer = event.getEntityPlayer();
		if (target instanceof EntityPlayerMP)
		{
			ICapabilityPlayer caps = CapabilityRefs.getPlayerCaps((EntityPlayer) target);
			if (caps != null)
				Animania.network.sendTo(new CapSyncPacket(caps, target.getEntityId()), (EntityPlayerMP) viewer);
		}
	}

	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event)
	{
		Entity e = event.getEntity();
		if(e instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) e;
			ICapabilityPlayer caps = CapabilityRefs.getPlayerCaps(player);
			if (caps != null)
				Animania.network.sendTo(new CapSyncPacket(caps, player.getEntityId()), player);
		}
	}
}
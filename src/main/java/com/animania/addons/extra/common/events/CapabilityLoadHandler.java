package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityPlayerProvider;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.network.CapSyncPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLoadHandler
{
	@SubscribeEvent
	public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof PlayerEntity)
		{
			event.addCapability(CapabilityRefs.toResource("AnimaniaPlayerCaps"), new CapabilityPlayerProvider());
		}

	}

	@SubscribeEvent
	public static void onClone(PlayerEvent.Clone event)
	{
		if (!event.isWasDeath())
		{
			return;
		}

		final ICapabilityPlayer propsNew = CapabilityRefs.getPlayerCaps(event.getPlayer());
		propsNew.read(event.getOriginal());

	}

	@SubscribeEvent
	public static void startTracking(StartTracking event)
	{
		Entity target = event.getTarget();
		PlayerEntity viewer = event.getPlayer();
		if (target instanceof ServerPlayerEntity)
		{
			ICapabilityPlayer caps = CapabilityRefs.getPlayerCaps((PlayerEntity) target);
			if (caps != null)
				Animania.network.sendTo(new CapSyncPacket(caps, target.getEntityId()), (ServerPlayerEntity) viewer);
		}
	}

	@SubscribeEvent
	public static void entityJoinWorld(EntityJoinWorldEvent event)
	{
		Entity e = event.getEntity();
		if (e instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity player = (ServerPlayerEntity) e;
			ICapabilityPlayer caps = CapabilityRefs.getPlayerCaps(player);
			if (caps != null)
				Animania.network.sendTo(new CapSyncPacket(caps, player.getEntityId()), player);
		}
	}
}
package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityPlayerProvider;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.network.CapSyncPacket;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityLoadHandler
{
	@SubscribeEvent
	public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof Player)
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
		Player viewer = event.getPlayer();
		if (target instanceof ServerPlayer)
		{
			ICapabilityPlayer caps = CapabilityRefs.getPlayerCaps((Player) target);
			if (caps != null)
				Animania.network.sendTo(new CapSyncPacket(caps, target.getEntityId()), (ServerPlayerEntity) viewer);
		}
	}

	@SubscribeEvent
	public static void entityJoinLevel(EntityJoinLevelEvent event)
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
package com.animania.addons.extra.network;

import com.animania.addons.extra.common.handler.ExtraAddonCapHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CapSyncPacketHandler implements IMessageHandler<CapSyncPacket, IMessage>
{

	@Override
	public IMessage onMessage(CapSyncPacket message, MessageContext ctx)
	{
		IThreadListener mainThread = Minecraft.getMinecraft();

		mainThread.addScheduledTask(() -> {

			Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityID);

			if (entity != null)
			{
				ExtraAddonCapHandler.syncCap(entity, message.carry);
			}

		});

		return null;
	}

}

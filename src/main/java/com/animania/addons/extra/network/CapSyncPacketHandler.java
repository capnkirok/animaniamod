package com.animania.addons.extra.network;

import javax.xml.ws.handler.MessageContext;

import com.animania.addons.extra.common.handler.ExtraAddonCapHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class CapSyncPacketHandler implements IMessageHandler<CapSyncPacket, IMessage>
{

	@Override
	public IMessage onMessage(CapSyncPacket message, MessageContext ctx)
	{
		IThreadListener mainThread = Minecraft.getMinecraft();

		mainThread.addScheduledTask(() -> {

			Entity entity = Minecraft.getMinecraft().level.getEntityByID(message.entityID);

			if (entity != null)
			{
				ExtraAddonCapHandler.syncCap(entity, message.carry);
			}

		});

		return null;
	}

}

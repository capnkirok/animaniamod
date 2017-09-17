package com.animania.network.client;


import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CapSyncPacketHandler implements IMessageHandler<CapSyncPacket, IMessage> {


	@Override
	public IMessage onMessage(CapSyncPacket message, MessageContext ctx) {
		IThreadListener mainThread = Minecraft.getMinecraft();

		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {

				Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityID);

				if(entity != null)
				{
					
					AnimaniaHelper.syncCap(entity, message.carry);
					
				}

			}});

		return null;
	}

	

}

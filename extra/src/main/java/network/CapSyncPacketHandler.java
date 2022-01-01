package network;

import common.handler.ExtraAddonCapHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

import javax.xml.ws.handler.MessageContext;

public class CapSyncPacketHandler implements IMessageHandler<CapSyncPacket, IMessage>
{

	@Override
	public IMessage onMessage(CapSyncPacket message, MessageContext ctx)
	{
		IThreadListener mainThread = Minecraft.getInstance();

		mainThread.addScheduledTask(() -> {

			Entity entity = Minecraft.getInstance().level.getEntityByID(message.entityID);

			if (entity != null)
			{
				ExtraAddonCapHandler.syncCap(entity, message.carry);
			}

		});

		return null;
	}

}

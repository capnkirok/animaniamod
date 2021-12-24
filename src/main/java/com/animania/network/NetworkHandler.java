package com.animania.network;

import com.animania.Animania;
import com.animania.network.client.TileEntitySyncPacket;
import com.animania.network.client.TileEntitySyncPacketHandler;
import com.animania.network.common.PacketCloseManual;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkRegistry;

public class NetworkHandler
{

	public static int packetID = 0;

	public static void init()
	{

		Animania.network = NetworkRegistry.INSTANCE.newSimpleChannel("Animania");

		Animania.network.registerMessage(TileEntitySyncPacketHandler.class, TileEntitySyncPacket.class, packetID++, Dist.CLIENT);
		Animania.network.registerMessage(PacketCloseManual.class, PacketCloseManual.class, packetID++, Dist.DEDICATED_SERVER);
	}

}

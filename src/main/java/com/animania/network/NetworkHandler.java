package com.animania.network;

import com.animania.Animania;
import com.animania.network.client.BlockEntitySyncPacket;
import com.animania.network.client.BlockEntitySyncPacketHandler;
import com.animania.network.common.PacketCloseManual;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkRegistry;

public class NetworkHandler
{

	public static int packetID = 0;

	public static void init()
	{

		Animania.network = NetworkRegistry.newSimpleChannel("Animania");

		Animania.network.registerMessage(BlockEntitySyncPacketHandler.class, BlockEntitySyncPacket.class, packetID++, Dist.CLIENT);
		Animania.network.registerMessage(PacketCloseManual.class, PacketCloseManual.class, packetID++, Dist.DEDICATED_SERVER);
	}

}

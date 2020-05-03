package com.animania.network;

import com.animania.Animania;
import com.animania.network.client.TileEntitySyncPacket;
import com.animania.network.client.TileEntitySyncPacketHandler;
import com.animania.network.common.PacketCloseManual;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{

	public static int packetID = 0;

	public static void init()
	{

		Animania.network = NetworkRegistry.INSTANCE.newSimpleChannel("Animania");

		Animania.network.registerMessage(TileEntitySyncPacketHandler.class, TileEntitySyncPacket.class, packetID++, Side.CLIENT);
		Animania.network.registerMessage(PacketCloseManual.class, PacketCloseManual.class, packetID++, Side.SERVER);
	}

}

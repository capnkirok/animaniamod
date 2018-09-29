package com.animania.network;

import com.animania.Animania;
import com.animania.network.client.CapSyncPacket;
import com.animania.network.client.CapSyncPacketHandler;
import com.animania.network.client.TileEntitySyncPacket;
import com.animania.network.client.TileEntitySyncPacketHandler;
import com.animania.network.common.PacketCloseManual;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{

	public static void init()
	{
		
		Animania.network = NetworkRegistry.INSTANCE.newSimpleChannel("Animania");
		
		Animania.network.registerMessage(TileEntitySyncPacketHandler.class, TileEntitySyncPacket.class, 0, Side.CLIENT);
		Animania.network.registerMessage(CapSyncPacketHandler.class, CapSyncPacket.class, 1, Side.CLIENT);
		Animania.network.registerMessage(PacketCloseManual.class, PacketCloseManual.class, 2, Side.SERVER);

		
	}
	
	
}

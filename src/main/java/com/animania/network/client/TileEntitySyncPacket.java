package com.animania.network.client;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class TileEntitySyncPacket
{

	public CompoundTag data;

	public TileEntitySyncPacket()
	{
	}

	public TileEntitySyncPacket(FriendlyByteBuf buf)
	{
	}

	public void toBytes(FriendlyByteBuf buf)
	{
	}

	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection().getReceptionSide().isClient())
		{
			ctx.get().enqueueWork(() -> {

				Player player = Minecraft.getInstance().player;

				if (player != null)
					ScriptReader.reloadScripts();

				ctx.get().setPacketHandled(true);
			});
		}
	}

}
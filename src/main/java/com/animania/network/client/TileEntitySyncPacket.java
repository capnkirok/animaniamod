package com.animania.network.client;

import com.animania.Animania;
import java.util.function.Supplier;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
//import com.animania.common.scripting.ScriptReader;
import java.util.function.Supplier;

public class TileEntitySyncPacket {

	public CompoundNBT data;

	public TileEntitySyncPacket() {
	}

	public TileEntitySyncPacket(PacketBuffer buf) {
	}

	public void toBytes(PacketBuffer buf) {
	}

	public void handle(Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide().isClient()) {
			ctx.get().enqueueWork(() -> {

				PlayerEntity player = Minecraft.getInstance().player;

				if (player != null)
					ScriptReader.reloadScripts();

				ctx.get().setPacketHandled(true);
			});
		}
	}

}
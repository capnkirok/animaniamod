package com.animania.network.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.network.simple.IndexedMessageCodec;

public class BlockEntitySyncPacketHandler
{

	@Override
	public IndexedMessageCodec.MessageHandler<?> handle(BlockEntitySyncPacket message, MessageContext ctx)
	{
		IThreadListener mainThread = Minecraft.getInstance();

		mainThread.addScheduledTask((Runnable) () -> {

			CompoundTag data = message.data;
			BlockPos pos = new BlockPos(data.getInt("x"), data.getInt("y"), data.getInt("z"));
			CompoundTag tileData = data.getCompound("data");

			BlockEntity tile = Minecraft.getInstance().level.getBlockEntity(pos);

			if (tile != null)
			{
				tile.load(tileData);
			}

		});

		return null;
	}

}

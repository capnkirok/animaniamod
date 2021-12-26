package com.animania.network.client;

import javax.xml.ws.handler.MessageContext;

import IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BlockEntitySyncPacketHandler
{

	@Override
	public IMessage handle(BlockEntitySyncPacket message, MessageContext ctx)
	{
		IThreadListener mainThread = Minecraft.getMinecraft();

		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run()
			{

				CompoundTag data = message.data;
				BlockPos pos = new BlockPos(data.getInteger("x"), data.getInteger("y"), data.getInteger("z"));
				CompoundTag tileData = data.getCompoundTag("data");

				BlockEntity tile = Minecraft.getMinecraft().level.getBlockEntity(pos);

				if (tile != null)
				{
					tile.readFromNBT(tileData);
				}

			}
		});

		return null;
	}

}

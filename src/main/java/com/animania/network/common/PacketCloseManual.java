package com.animania.network.common;

import javax.xml.ws.handler.MessageContext;

import com.animania.common.handler.ItemHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

public class PacketCloseManual implements IMessage, IMessageHandler<PacketCloseManual, IMessage>
{

	public String currentTopic;
	public String lastTopic;

	public PacketCloseManual()
	{
	}

	public PacketCloseManual(String currentTopic, String lastTopic)
	{
		this.currentTopic = currentTopic;
		this.lastTopic = lastTopic;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.currentTopic = ByteBufUtils.readUTF8String(buf);
		this.lastTopic = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(PacketBuffer buf)
	{
		ByteBufUtils.writeUTF8String(buf, this.currentTopic);
		ByteBufUtils.writeUTF8String(buf, this.lastTopic);
	}

	@Override
	public IMessage onMessage(PacketCloseManual message, MessageContext ctx)
	{
		IThreadListener mainThread = (LevelServer) ctx.getServerHandler().player.level;

		mainThread.addScheduledTask(new Runnable() {
			ServerPlayer player = ctx.getServerHandler().player;

			@Override
			public void run()
			{
				ItemStack main = this.player.getMainHandItem();
				ItemStack off = this.player.getItemInHandOffhand();
				ItemStack stack = ItemStack.EMPTY;

				if (main.getItem() == ItemHandler.animaniaManual)
					stack = main;
				else if (main.getItem() == ItemHandler.animaniaManual)
					stack = off;

				CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
				tag.setString("currentTopic", message.currentTopic);
				tag.setString("lastTopic", message.lastTopic);

				stack.putCompound(tag);
			}
		});

		return null;
	}

}

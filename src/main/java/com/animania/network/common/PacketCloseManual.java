package com.animania.network.common;

import javax.xml.ws.handler.MessageContext;

import com.animania.common.handler.ItemHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;


public class  PacketCloseManual implements IMessage, IMessageHandler<PacketCloseManual, IMessage>
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
		currentTopic = ByteBufUtils.readUTF8String(buf);
		lastTopic = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(PacketBuffer buf)
	{
		ByteBufUtils.writeUTF8String(buf, currentTopic);
		ByteBufUtils.writeUTF8String(buf, lastTopic);
	}

	@Override
	public IMessage onMessage(PacketCloseManual message, MessageContext ctx)
	{
		IThreadListener mainThread = (LevelServer) ctx.getServerHandler().player.level;

		mainThread.addScheduledTask(new Runnable()
		{
			ServerPlayerEntity player = ctx.getServerHandler().player;

			@Override
			public void run()
			{
				ItemStack main = player.getMainHandItem();
				ItemStack off = player.getHeldItemOffhand();
				ItemStack stack = ItemStack.EMPTY;
				
				if (main.getItem() == ItemHandler.animaniaManual)
					stack = main;
				else if (main.getItem() == ItemHandler.animaniaManual)
					stack = off;
				
				CompoundTag tag = stack.hasTagCompound() ? stack.getTagCompound() : new CompoundTag();
				tag.setString("currentTopic", message.currentTopic);
				tag.setString("lastTopic", message.lastTopic);

				stack.putTagCompound(tag);
			}
		});

		return null;
	}

}


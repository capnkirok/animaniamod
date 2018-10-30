package com.animania.network.common;

import com.animania.common.handler.ItemHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
		currentTopic = ByteBufUtils.readUTF8String(buf);
		lastTopic = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, currentTopic);
		ByteBufUtils.writeUTF8String(buf, lastTopic);
	}

	@Override
	public IMessage onMessage(PacketCloseManual message, MessageContext ctx)
	{
		IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;

		mainThread.addScheduledTask(new Runnable()
		{
			EntityPlayerMP player = ctx.getServerHandler().player;

			@Override
			public void run()
			{
				ItemStack main = player.getHeldItemMainhand();
				ItemStack off = player.getHeldItemOffhand();
				ItemStack stack = ItemStack.EMPTY;
				
				if (main.getItem() == ItemHandler.animaniaManual)
					stack = main;
				else if (main.getItem() == ItemHandler.animaniaManual)
					stack = off;
				
				NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
				tag.setString("currentTopic", message.currentTopic);
				tag.setString("lastTopic", message.lastTopic);

				stack.setTagCompound(tag);
			}
		});

		return null;
	}

}

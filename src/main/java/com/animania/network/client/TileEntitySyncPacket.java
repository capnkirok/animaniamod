package com.animania.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TileEntitySyncPacket implements IMessage{
	

	public NBTTagCompound data;
	
	public TileEntitySyncPacket() {
	}
	
	
	public TileEntitySyncPacket(NBTTagCompound data)
	{
		this.data = data;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.data = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, data);
		
	}

}

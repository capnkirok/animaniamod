package com.animania.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TileEntitySyncPacket implements IMessage{
	

	public CompoundNBT data;
	
	public TileEntitySyncPacket() {
	}
	
	
	public TileEntitySyncPacket(CompoundNBT data)
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

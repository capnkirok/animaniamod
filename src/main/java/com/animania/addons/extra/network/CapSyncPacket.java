package com.animania.addons.extra.network;

import com.animania.addons.extra.common.capabilities.CapabilitiesPlayerStorage;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CapSyncPacket implements IMessage
{	
	public ICapabilityPlayer carry;
	public int entityID;
	
	public CapSyncPacket() {
	}
	
	
	public CapSyncPacket(ICapabilityPlayer carry, int entityID)
	{
		this.carry = carry;
		this.entityID = entityID;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.carry = ((CapabilitiesPlayerStorage) CapabilityRefs.CAPS.getStorage()).readNBT2(CapabilityRefs.CAPS, CapabilityRefs.CAPS.getDefaultInstance(), null, ByteBufUtils.readTag(buf));
		this.entityID = ByteBufUtils.readVarInt(buf, 4);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, (CompoundTag) CapabilityRefs.CAPS.getStorage().writeNBT(CapabilityRefs.CAPS, carry, null));
		ByteBufUtils.writeVarInt(buf, entityID, 4);
		
	}
}

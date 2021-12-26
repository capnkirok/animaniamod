package com.animania.common.tileentities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BlockEntityWaterBottle extends BlockEntity
{
	public int rotation;

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.rotation = compound.getInteger("BlockRotation");
	}

	/*
	 * @Override public void writeToNBT(CompoundTag compound) {
	 * super.writeToNBT(compound); compound.putInteger("BlockRotation",
	 * rotation); }
	 *
	 * @Override public Packet getDescriptionPacket() { CompoundTag CompoundTag
	 * = new CompoundTag(); this.writeToNBT(CompoundTag); return new
	 * S35PacketUpdateBlockEntity(this.x, this.y, this.z, -1, CompoundTag); }
	 *
	 * @Override public void onDataPacket(NetworkManager net,
	 * S35PacketUpdateBlockEntity pkt) { readFromNBT(pkt.getTag()); }
	 */
}

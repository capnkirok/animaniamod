package com.animania.common.tileentities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TileEntityWaterBottle extends BlockEntity
{
    public int rotation;

    @Override
    public void readFromNBT(CompoundTag compound) {
        super.readFromNBT(compound);
        this.rotation = compound.getInteger("BlockRotation");
    }

    /*
     * @Override public void writeToNBT(CompoundNBT compound) {
     * super.writeToNBT(compound); compound.putInteger("BlockRotation",
     * rotation); }
     *
     * @Override public Packet getDescriptionPacket() { CompoundNBT
     * CompoundNBT = new CompoundNBT(); this.writeToNBT(CompoundNBT);
     * return new S35PacketUpdateTileEntity(this.x, this.y,
     * this.z, -1, CompoundNBT); }
     *
     * @Override public void onDataPacket(NetworkManager net,
     * S35PacketUpdateTileEntity pkt) { readFromNBT(pkt.getTag()); }
     */
}

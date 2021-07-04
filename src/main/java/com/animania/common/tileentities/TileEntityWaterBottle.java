package com.animania.common.tileentities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWaterBottle extends TileEntity
{
    public int rotation;

    @Override
    public void readFromNBT(CompoundNBT compound) {
        super.readFromNBT(compound);
        this.rotation = compound.getInteger("BlockRotation");
    }

    /*
     * @Override public void writeToNBT(CompoundNBT compound) {
     * super.writeToNBT(compound); compound.setInteger("BlockRotation",
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

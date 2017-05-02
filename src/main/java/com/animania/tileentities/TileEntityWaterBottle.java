package com.animania.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWaterBottle extends TileEntity {
	public int rotation;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
    	super.readFromNBT(compound);
    	rotation = compound.getInteger("BlockRotation");
    }

    /*
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
    	super.writeToNBT(compound);
    	compound.setInteger("BlockRotation", rotation);
    }
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -1, nbttagcompound);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
    	readFromNBT(pkt.func_148857_g());
    }
    */
}

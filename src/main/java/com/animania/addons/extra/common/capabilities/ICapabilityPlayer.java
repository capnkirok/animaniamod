package com.animania.addons.extra.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface ICapabilityPlayer {

	public NBTTagCompound getAnimal();
	public void setAnimal(NBTTagCompound tag);
	
	public boolean isCarrying();
	public void setCarrying(boolean carrying);
	
	public String getType();
	public void setType(String type);
	
	void read(EntityPlayer player);

	void update();

	NBTTagCompound writeNBT();

	void readNBT(NBTTagCompound nbt);

}

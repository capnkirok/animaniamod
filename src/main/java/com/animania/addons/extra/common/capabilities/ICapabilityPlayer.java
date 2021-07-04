package com.animania.addons.extra.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompoundNBT;

import CompoundNBT;

public interface ICapabilityPlayer {

	public CompoundNBT getAnimal();
	public void setAnimal(CompoundNBT tag);
	
	public boolean isCarrying();
	public void setCarrying(boolean carrying);
	
	public String getType();
	public void setType(String type);
	
	void read(EntityPlayer player);

	void update();

	CompoundNBT writeNBT();

	void readNBT(CompoundNBT nbt);

}

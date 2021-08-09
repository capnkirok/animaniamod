package com.animania.addons.extra.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface ICapabilityPlayer {

	public CompoundNBT getAnimal();
	public void setAnimal(CompoundNBT tag);
	
	public boolean isCarrying();
	public void setCarrying(boolean carrying);
	
	public String getType();
	public void setType(String type);
	
	void read(PlayerEntity player);

	void update();

	CompoundNBT writeNBT();

	void readNBT(CompoundNBT nbt);

}

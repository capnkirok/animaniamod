package com.animania.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public interface ICapabilityPlayer {

	//Triggered Abilities
	NBTBase setMounted(boolean mounted);
	NBTBase setPetType(String petType);
	NBTBase setPetColor(int petColor);
	NBTBase setPetName(String petName);
	
	boolean getMounted();
	String getPetType();
	int getPetColor();
	String getPetName();
	
	
	
	
	void read(EntityPlayer player);

	void update();

	NBTTagCompound writeNBT();

	void readNBT(NBTTagCompound nbt);

}

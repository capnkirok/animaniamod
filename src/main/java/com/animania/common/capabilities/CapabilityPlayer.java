package com.animania.common.capabilities;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class CapabilityPlayer implements ICapabilityPlayer {

	private boolean mounted;
	private String petType;
	private int petColor;
	private String petName;
	
	
	public CapabilityPlayer() {
		mounted = false;
		petType = "";
		petColor = 0;
		petName = "";
	}

	@Override
	public void read(EntityPlayer player){
		ICapabilityPlayer cap = CapabilityRefs.getPlayerCaps(player);   
	}

	@Override
	public void update() {
		
	}

	//GETTERS
	@Override
	public boolean getMounted() {
		return mounted;
	}
	
	public String getPetType() {
		return petType;
	}
	
	public int getPetColor() {
		return petColor;
	}
	
	public String getPetName() {
		return petName;
	}

	//SETTERS
	@Override
	public NBTBase setMounted(boolean mounted) {
		this.mounted = mounted;
		return CapabilityRefs.toTagInt(this.mounted? 1 : 0);
	}
	
	public NBTBase setPetType(String petType) {
		this.petType = petType;
		return CapabilityRefs.toTagString(petType);
	}
	
	public NBTBase setPetColor(int petColor) {
		this.petColor = petColor;
		return CapabilityRefs.toTagInt(petColor);
	}
	
	public NBTBase setPetName(String petName) {
		this.petName = petName;
		return CapabilityRefs.toTagString(petName);
	}
	
	@Override
	public NBTTagCompound writeNBT() {
		return (NBTTagCompound) CapabilityPlayerHandler.writeNBT(CapabilityRefs.CAPS, this);
	}
	@Override
	public void readNBT(NBTTagCompound nbt) {
		CapabilityPlayerHandler.readNBT(CapabilityRefs.CAPS, this, nbt);
	}

	

	

}
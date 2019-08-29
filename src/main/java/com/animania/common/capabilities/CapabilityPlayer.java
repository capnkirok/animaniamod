package com.animania.common.capabilities;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CapabilityPlayer implements ICapabilityPlayer {

	private NBTTagCompound animal;
	private boolean carrying;
	private String type;
	
	public CapabilityPlayer() {
		this.animal = new NBTTagCompound();
		this.carrying = false;
		this.type = "";
	}

	@Override
	public void read(EntityPlayer player){
		ICapabilityPlayer cap = CapabilityRefs.getPlayerCaps(player);   
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public NBTTagCompound writeNBT() {
		return (NBTTagCompound) CapabilityPlayerHandler.writeNBT(CapabilityRefs.CAPS, this);
	}
	@Override
	public void readNBT(NBTTagCompound nbt) {
		CapabilityPlayerHandler.readNBT(CapabilityRefs.CAPS, this, nbt);
	}

	@Override
	public NBTTagCompound getAnimal()
	{
		return this.animal;
	}

	@Override
	public void setAnimal(NBTTagCompound tag)
	{
		this.animal = tag;
	}

	@Override
	public boolean isCarrying()
	{
		return this.carrying;
	}

	@Override
	public void setCarrying(boolean carrying)
	{
		this.carrying = carrying;
	}

	@Override
	public String getType()
	{
		return this.type;
	}

	@Override
	public void setType(String type)
	{
		this.type = type;
	}

	

	

}
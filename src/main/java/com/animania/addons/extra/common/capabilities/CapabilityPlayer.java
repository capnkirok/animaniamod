package com.animania.addons.extra.common.capabilities;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompoundNBT;

import CompoundNBT;

public class CapabilityPlayer implements ICapabilityPlayer {

	private CompoundNBT animal;
	private boolean carrying;
	private String type;
	
	public CapabilityPlayer() {
		this.animal = new CompoundNBT();
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
	public CompoundNBT writeNBT() {
		return (CompoundNBT) CapabilityPlayerHandler.writeNBT(CapabilityRefs.CAPS, this);
	}
	@Override
	public void readNBT(CompoundNBT nbt) {
		CapabilityPlayerHandler.readNBT(CapabilityRefs.CAPS, this, nbt);
	}

	@Override
	public CompoundNBT getAnimal()
	{
		return this.animal;
	}

	@Override
	public void setAnimal(CompoundNBT tag)
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
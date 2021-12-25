package com.animania.addons.extra.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class CapabilityPlayer implements ICapabilityPlayer
{

	private CompoundTag animal;
	private boolean carrying;
	private String type;

	public CapabilityPlayer()
	{
		this.animal = new CompoundTag();
		this.carrying = false;
		this.type = "";
	}

	@Override
	public void read(Player player)
	{
		ICapabilityPlayer cap = CapabilityRefs.getPlayerCaps(player);
	}

	@Override
	public void update()
	{

	}

	@Override
	public CompoundTag writeNBT()
	{
		return (CompoundTag) CapabilityPlayerHandler.writeNBT(CapabilityRefs.CAPS, this);
	}

	@Override
	public void readNBT(CompoundTag nbt)
	{
		CapabilityPlayerHandler.readNBT(CapabilityRefs.CAPS, this, nbt);
	}

	@Override
	public CompoundTag getAnimal()
	{
		return this.animal;
	}

	@Override
	public void setAnimal(CompoundTag tag)
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
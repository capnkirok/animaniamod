package com.animania.addons.extra.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface ICapabilityPlayer
{

	public CompoundTag getAnimal();

	public void setAnimal(CompoundTag tag);

	public boolean isCarrying();

	public void setCarrying(boolean carrying);

	public String getType();

	public void setType(String type);

	void read(Player player);

	void update();

	CompoundTag writeNBT();

	void readNBT(CompoundTag nbt);

}

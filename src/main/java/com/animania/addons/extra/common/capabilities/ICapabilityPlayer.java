package com.animania.addons.extra.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface ICapabilityPlayer
{

	CompoundTag getAnimal();

	void setAnimal(CompoundTag tag);

	boolean isCarrying();

	void setCarrying(boolean carrying);

	String getType();

	void setType(String type);

	void read(Player player);

	void update();

	CompoundTag writeNBT();

	void readNBT(CompoundTag nbt);

}

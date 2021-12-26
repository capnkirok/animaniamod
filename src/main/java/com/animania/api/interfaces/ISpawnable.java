package com.animania.api.interfaces;

import net.minecraft.world.item.Item;

public interface ISpawnable
{

	public Item getSpawnEgg();

	public int getPrimaryEggColor();

	public int getSecondaryEggColor();

	default boolean usesEggColor()
	{
		return true;
	}

}

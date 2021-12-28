package com.animania.api.interfaces;

import net.minecraft.world.item.Item;

public interface ISpawnable
{

	Item getSpawnEgg();

	int getPrimaryEggColor();

	int getSecondaryEggColor();

	default boolean usesEggColor()
	{
		return true;
	}

}

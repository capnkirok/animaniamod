package com.animania.common.entities.interfaces;

import net.minecraft.item.Item;

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

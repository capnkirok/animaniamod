package com.animania.api.interfaces;

public interface ISpawnable
{

	public RItem getSpawnEgg();

	public int getPrimaryEggColor();

	public int getSecondaryEggColor();

	default boolean usesEggColor()
	{
		return true;
	}

}

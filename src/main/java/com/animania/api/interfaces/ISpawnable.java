package com.animania.api.interfaces;

import com.animania.common.helper.RegistryHelper.RItem;

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

package com.animania.api.interfaces;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

public interface ISterilizable extends IAnimaniaAnimal
{
	EntityDataAccessor<Boolean> getSterilizedParam();

	default boolean getSterilized()
	{
		EntityDataAccessor<Boolean> param = this.getSterilizedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setSterilized(boolean sterilized)
	{
		EntityDataAccessor<Boolean> param = this.getSterilizedParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, sterilized);
	}

	void sterilize();

}

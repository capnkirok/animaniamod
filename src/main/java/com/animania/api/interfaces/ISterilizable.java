package com.animania.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;

public interface ISterilizable extends IAnimaniaAnimal
{
	public DataParameter<Boolean> getSterilizedParam();
	
	default boolean getSterilized()
	{
		DataParameter<Boolean> param = getSterilizedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}
	
	default void setSterilized(boolean sterilized)
	{
		DataParameter<Boolean> param = getSterilizedParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, sterilized);
	}
	
	public void sterilize();
	
}

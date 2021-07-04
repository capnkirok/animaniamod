package com.animania.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;

public interface IAgeable extends IAnimaniaAnimal
{
	public DataParameter<Integer> getAgeParam();
		
	default void setAge(int age)
	{
		DataParameter<Integer> param = getAgeParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, age);
	}
	
	default int getAge()
	{
		DataParameter<Integer> param = getAgeParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}
}

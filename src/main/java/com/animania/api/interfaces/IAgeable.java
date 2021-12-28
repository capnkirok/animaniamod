package com.animania.api.interfaces;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

public interface IAgeable extends IAnimaniaAnimal
{
	EntityDataAccessor<Integer> getAgeParam();

	default void setAge(int age)
	{
		EntityDataAccessor<Integer> param = this.getAgeParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, age);
	}

	default int getAge()
	{
		EntityDataAccessor<Integer> param = this.getAgeParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}
}

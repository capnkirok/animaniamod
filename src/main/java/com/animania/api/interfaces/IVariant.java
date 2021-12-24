package com.animania.api.interfaces;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

public interface IVariant extends IAnimaniaAnimal
{
	public EntityDataAccessor<Integer> getVariantParam();
	
	default int getVariant()
	{
		EntityDataAccessor<Integer> param = getVariantParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}

	default void setVariant(int i)
	{
		EntityDataAccessor<Integer> param = getVariantParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, i);
	}
	
	default int getVariantCount()
	{
		return 0;
	}
	
	default int getEyeColorForVariant(int variant)
	{
		return 0;
	}
	
}


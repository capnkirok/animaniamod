package com.animania.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;

public interface IVariant extends IAnimaniaAnimal
{
	public DataParameter<Integer> getVariantParam();
	
	default int getVariant()
	{
		DataParameter<Integer> param = getVariantParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}

	default void setVariant(int i)
	{
		DataParameter<Integer> param = getVariantParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, i);
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


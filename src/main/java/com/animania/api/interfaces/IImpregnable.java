package com.animania.api.interfaces;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;

public interface IImpregnable extends IAnimaniaAnimal
{
	public DataParameter<Integer> getGestationParam();

	public DataParameter<Boolean> getPregnantParam();

	public DataParameter<Boolean> getFertileParam();

	public DataParameter<Boolean> getHasKidsParam();
	

	default boolean getFertile()
	{
		DataParameter<Boolean> param = getFertileParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setFertile(boolean fertile)
	{
		DataParameter<Boolean> param = getFertileParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, fertile);
	}

	default boolean getPregnant()
	{
		DataParameter<Boolean> param = getPregnantParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	
	default void setPregnant(boolean pregnant)
	{
		DataParameter<Boolean> param = getPregnantParam();
		if (param != null)
		{
			if(pregnant)
				this.setGestation(AnimaniaConfig.careAndFeeding.gestationTimer + Animania.RANDOM.nextInt(200));
			((Entity) this).getDataManager().set(param, pregnant);

		}
	}
	
	default void setHasKids(boolean hasKids)
	{
		DataParameter<Boolean> param = getHasKidsParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, hasKids);
	}
	
	default boolean getHasKids()
	{
		DataParameter<Boolean> param = getHasKidsParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
		
	}
	
	default void setGestation(int gestation)
	{
		DataParameter<Integer> param = getGestationParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, gestation);
	}
	
	default int getGestation()
	{
		DataParameter<Integer> param = getGestationParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}

}

package com.animania.api.interfaces;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.network.syncher.EntityDataAccessor;

public interface IImpregnable extends IAnimaniaAnimal
{	
	public EntityDataAccessor<Integer> getGestationParam();

	public EntityDataAccessor<Boolean> getPregnantParam();

	public EntityDataAccessor<Boolean> getFertileParam();

	public EntityDataAccessor<Boolean> getHasKidsParam();
	

	default boolean getFertile()
	{
		EntityDataAccessor<Boolean> param = getFertileParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setFertile(boolean fertile)
	{
		EntityDataAccessor<Boolean> param = getFertileParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, fertile);
	}

	default boolean getPregnant()
	{
		EntityDataAccessor<Boolean> param = getPregnantParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	
	default void setPregnant(boolean pregnant)
	{
		EntityDataAccessor<Boolean> param = getPregnantParam();
		if (param != null)
		{
			if(pregnant)
				this.setGestation(AnimaniaConfig.careAndFeeding.gestationTimer + Animania.RANDOM.nextInt(200));
			((Entity) this).getEntityData().set(param, pregnant);

		}
	}
	
	default void setHasKids(boolean hasKids)
	{
		EntityDataAccessor<Boolean> param = getHasKidsParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, hasKids);
	}
	
	default boolean getHasKids()
	{
		EntityDataAccessor<Boolean> param = getHasKidsParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
		
	}
	
	default void setGestation(int gestation)
	{
		EntityDataAccessor<Integer> param = getGestationParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, gestation);
	}
	
	default int getGestation()
	{
		EntityDataAccessor<Integer> param = getGestationParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}
	
	public int getDryTimer();
	
	public void setDryTimer(int i);

}

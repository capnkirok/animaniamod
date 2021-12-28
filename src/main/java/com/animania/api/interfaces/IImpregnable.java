package com.animania.api.interfaces;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

public interface IImpregnable extends IAnimaniaAnimal
{
	EntityDataAccessor<Integer> getGestationParam();

	EntityDataAccessor<Boolean> getPregnantParam();

	EntityDataAccessor<Boolean> getFertileParam();

	EntityDataAccessor<Boolean> getHasKidsParam();

	default boolean getFertile()
	{
		EntityDataAccessor<Boolean> param = this.getFertileParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setFertile(boolean fertile)
	{
		EntityDataAccessor<Boolean> param = this.getFertileParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, fertile);
	}

	default boolean getPregnant()
	{
		EntityDataAccessor<Boolean> param = this.getPregnantParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setPregnant(boolean pregnant)
	{
		EntityDataAccessor<Boolean> param = this.getPregnantParam();
		if (param != null)
		{
			if (pregnant)
				this.setGestation(AnimaniaConfig.careAndFeeding.gestationTimer + Animania.RANDOM.nextInt(200));
			((Entity) this).getEntityData().set(param, pregnant);

		}
	}

	default void setHasKids(boolean hasKids)
	{
		EntityDataAccessor<Boolean> param = this.getHasKidsParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, hasKids);
	}

	default boolean getHasKids()
	{
		EntityDataAccessor<Boolean> param = this.getHasKidsParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;

	}

	default void setGestation(int gestation)
	{
		EntityDataAccessor<Integer> param = this.getGestationParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, gestation);
	}

	default int getGestation()
	{
		EntityDataAccessor<Integer> param = this.getGestationParam();
		if (param != null)
			return this.getIntFromDataManager(param);
		return 0;
	}

	int getDryTimer();

	void setDryTimer(int i);

}

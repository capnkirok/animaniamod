package com.animania.api.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

import java.util.Optional;

public interface ISleeping extends IAnimaniaAnimal
{
	EntityDataAccessor<Boolean> getSleepingParam();

	EntityDataAccessor<Float> getSleepTimerParam();

	default void setSleeping(boolean sleeping)
	{
		EntityDataAccessor<Boolean> param = this.getSleepingParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, sleeping);
	}

	default boolean getSleeping()
	{
		EntityDataAccessor<Boolean> param = this.getSleepingParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	void setSleepingPos(BlockPos pos);

	Optional<BlockPos> getSleepingPos();

	default Float getSleepTimer()
	{
		EntityDataAccessor<Float> param = this.getSleepTimerParam();
		if (param != null)
			return this.getFloatFromDataManager(param);
		return 0f;
	}

	default void setSleepTimer(Float timer)
	{
		EntityDataAccessor<Float> param = this.getSleepTimerParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, timer);
	}

}

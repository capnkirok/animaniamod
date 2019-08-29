package com.animania.api.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.math.BlockPos;

public interface ISleeping extends IAnimaniaAnimal
{
	public DataParameter<Boolean> getSleepingParam();
	public DataParameter<Float> getSleepTimerParam();
	
	default void setSleeping(boolean sleeping)
	{
		DataParameter<Boolean> param = getSleepingParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, sleeping);
	}
	
	default boolean getSleeping()
	{
		DataParameter<Boolean> param = getSleepingParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}
	
	public void setSleepingPos(BlockPos pos);
	
	public BlockPos getSleepingPos();
	
	default Float getSleepTimer()
	{
		DataParameter<Float> param = getSleepTimerParam();
		if (param != null)
			return this.getFloatFromDataManager(param);
		return 0f;
	}
	
	default void setSleepTimer(Float timer)
	{
		DataParameter<Float> param = getSleepTimerParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, timer);
	}

}

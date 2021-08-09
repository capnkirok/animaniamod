package com.animania.api.interfaces;

import java.util.UUID;

import com.google.common.base.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataParameter;

public interface IChild extends IAnimaniaAnimal
{
	public DataParameter<Optional<UUID>> getParentUniqueIdParam();
	public DataParameter<Float> getEntityAgeParam();

	default UUID getParentUniqueId()
	{
		DataParameter<Optional<UUID>> param = getParentUniqueIdParam();
		if (param != null)
			return this.getUUIDFromDataManager(param);
		return null;
	}
	
	default void setParentUniqueId(UUID id)
	{
		DataParameter<Optional<UUID>> param = getParentUniqueIdParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, Optional.fromNullable(id));
	}
	
	default float getEntityAge()
	{
		DataParameter<Float> param = getEntityAgeParam();
		if (param != null)
			return this.getFloatFromDataManager(param);
		return 0;
	}

	default void setEntityAge(float age)
	{
		DataParameter<Float> param = getEntityAgeParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, age);
	}
	
	public int getAgeTimer();
	
	public void setAgeTimer(int i);
	
	public float getSizeDividend();
}

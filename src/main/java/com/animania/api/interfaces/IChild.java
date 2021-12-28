package com.animania.api.interfaces;

import java.util.UUID;

import com.google.common.base.Optional;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

public interface IChild extends IAnimaniaAnimal
{
	EntityDataAccessor<Optional<UUID>> getParentUniqueIdParam();

	EntityDataAccessor<Float> getEntityAgeParam();

	default UUID getParentUniqueId()
	{
		EntityDataAccessor<Optional<UUID>> param = this.getParentUniqueIdParam();
		if (param != null)
			return this.getUUIDFromDataManager(param);
		return null;
	}

	default void setParentUniqueId(UUID id)
	{
		EntityDataAccessor<Optional<UUID>> param = this.getParentUniqueIdParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, Optional.fromNullable(id));
	}

	default float getEntityAge()
	{
		EntityDataAccessor<Float> param = this.getEntityAgeParam();
		if (param != null)
			return this.getFloatFromDataManager(param);
		return 0;
	}

	default void setEntityAge(float age)
	{
		EntityDataAccessor<Float> param = this.getEntityAgeParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, age);
	}

	int getAgeTimer();

	void setAgeTimer(int i);

	float getSizeDividend();
}

package com.animania.api.interfaces;

import com.google.common.base.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;

import java.util.UUID;

public interface IMateable extends IAnimaniaAnimal
{
	
	public DataParameter<Optional<UUID>> getMateUniqueIdParam();
	
	default UUID getMateUniqueId()
	{
		DataParameter<Optional<UUID>> param = getMateUniqueIdParam();
		if (param != null)
			return this.getUUIDFromDataManager(param);
		return null;
	}

	default void setMateUniqueId(UUID id)
	{
		DataParameter<Optional<UUID>> param = getMateUniqueIdParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, Optional.fromNullable(id));
	}

	
	default void setInLove(PlayerEntity player)
	{
		
	}
}

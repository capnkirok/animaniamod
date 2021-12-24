package com.animania.api.interfaces;

import java.util.UUID;

import com.google.common.base.Optional;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface IMateable extends IAnimaniaAnimal
{
	
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam();
	
	default UUID getMateUniqueId()
	{
		EntityDataAccessor<Optional<UUID>> param = getMateUniqueIdParam();
		if (param != null)
			return this.getUUIDFromDataManager(param);
		return null;
	}

	default void setMateUniqueId(UUID id)
	{
		EntityDataAccessor<Optional<UUID>> param = getMateUniqueIdParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, Optional.fromNullable(id));
	}

	
	default void setInLove(Player player)
	{
		
	}
}

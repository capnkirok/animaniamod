package com.animania.api.interfaces;

import java.util.UUID;

import com.google.common.base.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.item.ItemStack;

public interface IAnimaniaAnimal
{
	public AnimaniaType getAnimalType();

	// ==================================================
	// Data Manager Trapper (borrowed from Lycanites)
	// ==================================================

	default boolean getBoolFromDataManager(EntityDataAccessor<Boolean> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	default byte getByteFromDataManager(EntityDataAccessor<Byte> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	default int getIntFromDataManager(EntityDataAccessor<Integer> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	default float getFloatFromDataManager(EntityDataAccessor<Float> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	default String getStringFromDataManager(EntityDataAccessor<String> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	default UUID getUUIDFromDataManager(EntityDataAccessor<Optional<UUID>> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key).orNull();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	default ItemStack getItemStackFromDataManager(EntityDataAccessor<ItemStack> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key);
		}
		catch (Exception e)
		{
			return ItemStack.EMPTY;
		}
	}

	default BlockPos getBlockPosFromDataManager(EntityDataAccessor<Optional<BlockPos>> key)
	{
		try
		{
			Entity entity = (Entity) this;
			return entity.getEntityData().get(key).orNull();
		}
		catch (Exception e)
		{
			return null;
		}
	}

}

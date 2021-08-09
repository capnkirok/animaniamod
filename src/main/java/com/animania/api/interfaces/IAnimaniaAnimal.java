package com.animania.api.interfaces;

import com.google.common.base.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public interface IAnimaniaAnimal
{
	public AnimaniaType getAnimalType();
	
	
	// ==================================================
	// Data Manager Trapper (borrowed from Lycanites)
	// ==================================================

	
	default boolean getBoolFromDataManager(DataParameter<Boolean> key)
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
	
	default byte getByteFromDataManager(DataParameter<Byte> key)
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

	default int getIntFromDataManager(DataParameter<Integer> key)
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

	default float getFloatFromDataManager(DataParameter<Float> key)
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

	default String getStringFromDataManager(DataParameter<String> key)
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

	default UUID getUUIDFromDataManager(DataParameter<Optional<UUID>> key)
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

	default ItemStack getItemStackFromDataManager(DataParameter<ItemStack> key)
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

	default BlockPos getBlockPosFromDataManager(DataParameter<Optional<BlockPos>> key)
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

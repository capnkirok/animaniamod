package com.animania.common.api.interfaces;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;

public interface IMateable
{
	default boolean getPregnant()
	{
		return false;
	}
	
	default void setPregnant(boolean pregnant)
	{
	}
	
	default boolean getFertile()
	{
		return false;
	}
	
	default void setFertile(boolean fertile)
	{
	}

	
	public UUID getMateUniqueId();

	public void setMateUniqueId(UUID id);

	
	default void setInLove(EntityPlayer player)
	{
		
	}
}

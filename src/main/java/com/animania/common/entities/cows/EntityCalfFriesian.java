package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCalfFriesian extends EntityCalfBase
{

	public EntityCalfFriesian(World world)
	{
		super(world);
		this.cowType = CowType.FRIESIAN;
	}

}
package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCalfHolstein extends EntityCalfBase
{

	public EntityCalfHolstein(World world)
	{
		super(world);
		this.cowType = CowType.HOLSTEIN;
	}

}
package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfHolstein extends EntityCalfBase
{

	public EntityCalfHolstein(World world)
	{
		super(world);
		this.cowType = CowType.HOLSTEIN;
	}

}
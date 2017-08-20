package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfAngus extends EntityCalfBase
{

	public EntityCalfAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
	}

}
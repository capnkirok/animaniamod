package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCalfAngus extends EntityCalfBase
{

	public EntityCalfAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
	}

}
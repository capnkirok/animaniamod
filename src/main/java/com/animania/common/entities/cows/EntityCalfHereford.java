package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCalfHereford extends EntityCalfBase
{

	public EntityCalfHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
	}

}
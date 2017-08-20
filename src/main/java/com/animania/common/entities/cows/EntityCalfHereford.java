package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfHereford extends EntityCalfBase
{

	public EntityCalfHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
	}

}
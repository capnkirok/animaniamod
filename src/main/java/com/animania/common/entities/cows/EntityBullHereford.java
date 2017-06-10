package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityBullHereford extends EntityBullBase
{

	public EntityBullHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
	}

}
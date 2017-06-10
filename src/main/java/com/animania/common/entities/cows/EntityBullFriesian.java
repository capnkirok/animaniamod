package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityBullFriesian extends EntityBullBase
{

	public EntityBullFriesian(World world)
	{
		super(world);
		this.cowType = CowType.FRIESIAN;
	}

}
package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleCorgi extends EntityMaleDogBase
{

	public EntityMaleCorgi(World world)
	{
		super(world);
		this.type = DogType.CORGI;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -263173;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -2987202;
	}
}

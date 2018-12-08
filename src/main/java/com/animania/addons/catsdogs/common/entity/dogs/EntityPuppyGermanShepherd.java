package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyGermanShepherd extends EntityPuppyBase
{

	public EntityPuppyGermanShepherd(World world)
	{
		super(world);
		this.type = DogType.GERMAN_SHEPHERD;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -8300224;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -14478321;
	}
}

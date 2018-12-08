package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyPomeranian extends EntityPuppyBase
{

	public EntityPuppyPomeranian(World world)
	{
		super(world);
		this.type = DogType.POMERANIAN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -197380;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13884380;
	}
}

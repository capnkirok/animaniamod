package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityFemalePoodle extends EntityFemaleDogBase
{

	public EntityFemalePoodle(World world)
	{
		super(world);
		this.type = DogType.POODLE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -658707;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -5537189;
	}
}

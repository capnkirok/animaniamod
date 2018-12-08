package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityFemaleCollie extends EntityFemaleDogBase
{

	public EntityFemaleCollie(World world)
	{
		super(world);
		this.type = DogType.COLLIE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -12570587;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -197380;
	}
}

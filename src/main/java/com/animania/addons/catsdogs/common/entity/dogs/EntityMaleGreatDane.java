package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleGreatDane extends EntityMaleDogBase
{

	public EntityMaleGreatDane(World world)
	{
		super(world);
		this.type = DogType.GREAT_DANE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -8300224;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -14412785;
	}
}

package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyFox extends EntityPuppyBase
{

	public EntityPuppyFox(World world)
	{
		super(world);
		this.type = DogType.FOX;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -5415620;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13946603;
	}
}

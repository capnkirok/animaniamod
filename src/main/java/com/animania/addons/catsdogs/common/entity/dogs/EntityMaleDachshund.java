package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleDachshund extends EntityMaleDogBase
{

	public EntityMaleDachshund(World world)
	{
		super(world);
		this.type = DogType.DACHSHUND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -197380;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -15988473;
	}
}

package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfJersey extends EntityCalfBase
{

	public EntityCalfJersey(World world)
	{
		super(world);
		this.cowType = CowType.JERSEY;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12089918;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16775643;
	}

}
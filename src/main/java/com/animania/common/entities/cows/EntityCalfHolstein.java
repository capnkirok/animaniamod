package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfHolstein extends EntityCalfBase
{

	public EntityCalfHolstein(World world)
	{
		super(world);
		this.cowType = CowType.HOLSTEIN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2236962;
	}

}
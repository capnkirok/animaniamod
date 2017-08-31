package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfLonghorn extends EntityCalfBase
{

	public EntityCalfLonghorn(World world)
	{
		super(world);
		this.cowType = CowType.LONGHORN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16763795;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 11227168;
	}

}
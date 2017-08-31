package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfAngus extends EntityCalfBase
{

	public EntityCalfAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 3028024;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2304560;
	}


}
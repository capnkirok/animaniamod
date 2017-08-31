package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class EntityPigletLargeBlack extends EntityPigletBase
{

	public EntityPigletLargeBlack(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_BLACK;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8417906;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 5326149;
	}

}
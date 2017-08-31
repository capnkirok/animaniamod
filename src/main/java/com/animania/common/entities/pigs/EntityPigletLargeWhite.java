package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class EntityPigletLargeWhite extends EntityPigletBase
{

	public EntityPigletLargeWhite(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_WHITE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15061714;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13876669;
	}

}
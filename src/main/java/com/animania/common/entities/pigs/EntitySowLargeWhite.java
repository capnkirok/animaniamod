package com.animania.common.entities.pigs;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntitySowLargeWhite extends EntitySowBase
{

	public EntitySowLargeWhite(World world)
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
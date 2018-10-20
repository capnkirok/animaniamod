package com.animania.common.entities.pigs;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntitySowYorkshire extends EntitySowBase
{

	public EntitySowYorkshire(World world)
	{
		super(world);
		this.pigType = PigType.YORKSHIRE;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 15845576;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 15117998;
	}
}
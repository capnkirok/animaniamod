package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletYorkshire extends EntityPigletBase
{

	public EntityPigletYorkshire(World world)
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
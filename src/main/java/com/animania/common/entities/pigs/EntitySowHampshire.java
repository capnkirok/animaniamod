package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntitySowHampshire extends EntitySowBase
{

	public EntitySowHampshire(World world)
	{
		super(world);
		this.pigType = PigType.HAMPSHIRE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 5327691;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13684944;
	}


}
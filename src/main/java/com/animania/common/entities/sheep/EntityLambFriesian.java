package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambFriesian extends EntityLambBase
{

	public EntityLambFriesian(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.FRIESIAN;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 2039583;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 4013373;
	}
	
}

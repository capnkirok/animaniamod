package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambSuffolk extends EntityLambBase
{

	public EntityLambSuffolk(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.SUFFOLK;
	}
	

	@Override
	public int getPrimaryEggColor()
	{
		return 4336416;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 2757652;
	}

}

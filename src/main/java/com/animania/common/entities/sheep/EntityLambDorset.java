package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambDorset extends EntityLambBase
{

	public EntityLambDorset(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORSET;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 4863280;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 15790320;
	}
}

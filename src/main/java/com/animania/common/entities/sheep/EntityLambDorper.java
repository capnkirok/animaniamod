package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambDorper extends EntityLambBase
{

	public EntityLambDorper(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORPER;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 1776411;
	}
}

package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityEweJacob extends EntityEweBase
{

	public EntityEweJacob(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.JACOB;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15921647;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 2368548;
	}

}

package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambMerino extends EntityLambBase
{

	public EntityLambMerino(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.MERINO;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 15526109;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 11904114;
	}
}

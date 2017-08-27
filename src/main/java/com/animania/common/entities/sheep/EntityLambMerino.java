package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambMerino extends EntityLambBase
{

	public EntityLambMerino(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.MERINO;
	}

}

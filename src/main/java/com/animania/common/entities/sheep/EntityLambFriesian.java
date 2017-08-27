package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityLambFriesian extends EntityLambBase
{

	public EntityLambFriesian(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.FRIESIAN;
	}

}

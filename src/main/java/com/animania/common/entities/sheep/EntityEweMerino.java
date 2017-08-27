package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityEweMerino extends EntityEweBase
{

	public EntityEweMerino(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.MERINO;
	}

}

package com.animania.common.entities.rodents;

import net.minecraft.world.World;

public class EntityHedgehog extends EntityHedgehogBase
{

	public EntityHedgehog(World worldIn)
	{
		super(worldIn);
		this.type = HedgehogType.NORMAL;
	}
	
}
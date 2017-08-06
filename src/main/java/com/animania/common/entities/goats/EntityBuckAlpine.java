package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityBuckAlpine extends EntityBuckBase
{

	public EntityBuckAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
	}

}

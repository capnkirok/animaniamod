package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityBuckPygmy extends EntityBuckBase
{

	public EntityBuckPygmy(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.PYGMY;
	}

}

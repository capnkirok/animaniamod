package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityBuckFainting extends EntityBuckBase
{

	public EntityBuckFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
	}

}

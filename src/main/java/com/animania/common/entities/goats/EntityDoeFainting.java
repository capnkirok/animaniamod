package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityDoeFainting extends EntityDoeBase
{

	public EntityDoeFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
	}

}

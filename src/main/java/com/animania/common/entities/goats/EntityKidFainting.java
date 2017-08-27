package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidFainting extends EntityKidBase
{

	public EntityKidFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
	}

}

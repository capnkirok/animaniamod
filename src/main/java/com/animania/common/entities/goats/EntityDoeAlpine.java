package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityDoeAlpine extends EntityDoeBase
{

	public EntityDoeAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
	}

}

package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityBuckKinder extends EntityBuckBase
{

	public EntityBuckKinder(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KINDER;
	}

}

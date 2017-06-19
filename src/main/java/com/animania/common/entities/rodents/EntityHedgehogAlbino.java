package com.animania.common.entities.rodents;

import net.minecraft.world.World;

public class EntityHedgehogAlbino extends EntityHedgehogBase
{

	public EntityHedgehogAlbino(World worldIn)
	{
		super(worldIn);
		this.type = HedgehogType.ALBINO;
	}
	
}
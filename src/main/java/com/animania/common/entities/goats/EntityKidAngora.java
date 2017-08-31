package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidAngora extends EntityKidBase
{

	public EntityKidAngora(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ANGORA;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16776179;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13814191;
	}


}

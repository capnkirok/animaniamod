package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidAlpine extends EntityKidBase
{

	public EntityKidAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 14867928;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8281676;
	}

}

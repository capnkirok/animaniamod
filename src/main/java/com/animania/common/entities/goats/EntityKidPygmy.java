package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidPygmy extends EntityKidBase
{

	public EntityKidPygmy(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.PYGMY;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 9475221;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 4145731;
	}
}

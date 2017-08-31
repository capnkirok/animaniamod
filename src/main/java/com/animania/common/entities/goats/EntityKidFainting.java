package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidFainting extends EntityKidBase
{

	public EntityKidFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 1250067;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14803425;
	}

}

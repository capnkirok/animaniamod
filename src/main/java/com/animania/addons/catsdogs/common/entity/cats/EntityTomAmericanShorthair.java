package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityTomAmericanShorthair extends EntityTomBase
{
	public EntityTomAmericanShorthair(World worldIn)
	{
		super(worldIn);
		this.type = CatType.AMERICAN_SHORTHAIR;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0x717171;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0x000000;
	}
}

package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityTomTabby extends EntityTomBase
{
	public EntityTomTabby(World worldIn)
	{
		super(worldIn);
		this.type = CatType.TABBY;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0x41332B;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0x3E3028;
	}
}

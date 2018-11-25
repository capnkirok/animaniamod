package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityKittenNorwegian extends EntityKittenBase {
	public EntityKittenNorwegian(World worldIn)
	{
		super(worldIn);
		this.type = CatType.NORWEGIAN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 0x3B2D25;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 0x987862;
	}
}

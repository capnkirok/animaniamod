package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityQueenExotic extends EntityQueenBase {
	public EntityQueenExotic(World worldIn)
	{
		super(worldIn);
		this.type = CatType.EXOTIC;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 0xAE5B24;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 0xD79A72;
	}
}

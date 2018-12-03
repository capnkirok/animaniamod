package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityQueenShorthair extends EntityAnimaniaCat {
	public EntityQueenShorthair(World worldIn)
	{
		super(worldIn);
		this.type = CatType.SHORTHAIR;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 13948116;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8741209;
	}
}

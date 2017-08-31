package com.animania.common.entities.rodents;

import net.minecraft.world.World;

public class EntityFerretWhite extends EntityFerretBase
{

	public EntityFerretWhite(World worldIn)
	{
		super(worldIn);
		this.type = FerretType.WHITE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15395298;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16447993;
	}
	
}
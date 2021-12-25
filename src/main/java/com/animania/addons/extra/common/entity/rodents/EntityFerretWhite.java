package com.animania.addons.extra.common.entity.rodents;

public class EntityFerretWhite extends EntityFerretBase
{

	public EntityFerretWhite(Level levelIn)
	{
		super(levelIn);
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
package com.animania.addons.extra.common.entity.rodents;

public class EntityFerretGrey extends EntityFerretBase
{

	public EntityFerretGrey(Level levelIn)
	{
		super(levelIn);
		this.type = FerretType.GREY;
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
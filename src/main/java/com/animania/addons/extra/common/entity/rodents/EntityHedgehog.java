package com.animania.addons.extra.common.entity.rodents;

public class EntityHedgehog extends EntityHedgehogBase
{

	public EntityHedgehog(Level levelIn)
	{
		super(levelIn);
		this.type = HedgehogType.NORMAL;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 10451558;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 14337943;
	}

}
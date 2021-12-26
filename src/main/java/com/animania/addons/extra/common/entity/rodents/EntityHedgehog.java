package com.animania.addons.extra.common.entity.rodents;

import net.minecraft.world.level.Level;

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
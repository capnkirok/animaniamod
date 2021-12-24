package com.animania.addons.extra.common.entity.rodents;

import net.minecraft.world.level.Level;

public class EntityHedgehogAlbino extends EntityHedgehogBase
{

	public EntityHedgehogAlbino(Level worldIn)
	{
		super(worldIn);
		this.type = HedgehogType.ALBINO;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12369084;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16777215;
	}
	
}
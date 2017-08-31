package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidKinder extends EntityKidBase
{

	public EntityKidKinder(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KINDER;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 9263679;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13811120;
	}

}

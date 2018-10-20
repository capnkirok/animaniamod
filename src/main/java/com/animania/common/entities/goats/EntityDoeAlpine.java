package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeAlpine extends EntityDoeBase
{

	public EntityDoeAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
		this.setSize(1.6F, 1.3F);

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 14867928;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8281676;
	}

}

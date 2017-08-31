package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckPygmy extends EntityBuckBase
{

	public EntityBuckPygmy(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.PYGMY;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 9475221;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 4145731;
	}
}

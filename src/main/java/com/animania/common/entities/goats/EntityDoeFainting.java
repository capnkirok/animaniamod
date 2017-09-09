package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeFainting extends EntityDoeBase
{

	public EntityDoeFainting(World worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.0F);
		this.goatType = GoatType.FAINTING;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 1250067;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14803425;
	}

}

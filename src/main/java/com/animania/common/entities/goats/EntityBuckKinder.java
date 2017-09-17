package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckKinder extends EntityBuckBase
{

	public EntityBuckKinder(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KINDER;
		this.setSize(1.3F, 1.2F);
		this.width = 1.3F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
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

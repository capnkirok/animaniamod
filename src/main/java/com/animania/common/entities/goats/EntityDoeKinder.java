package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeKinder extends EntityDoeBase
{

	public EntityDoeKinder(World worldIn)
	{
		super(worldIn);
		this.setSize(1.4F, 1.2F);
		this.goatType = GoatType.KINDER;
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

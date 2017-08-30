package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeKinder extends EntityDoeBase
{

	public EntityDoeKinder(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KINDER;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}

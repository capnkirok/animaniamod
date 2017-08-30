package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckKinder extends EntityBuckBase
{

	public EntityBuckKinder(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KINDER;
		this.setSize(1.4F, 1.2F);
		this.width = 1.5F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}

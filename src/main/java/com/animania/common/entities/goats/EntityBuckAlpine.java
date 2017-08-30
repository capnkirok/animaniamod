package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckAlpine extends EntityBuckBase
{

	public EntityBuckAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
		this.setSize(1.7F, 1.4F);
		this.width = 2.0F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
		
	}

}

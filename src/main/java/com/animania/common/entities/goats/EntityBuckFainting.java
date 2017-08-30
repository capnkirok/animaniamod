package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckFainting extends EntityBuckBase
{

	public EntityBuckFainting(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.FAINTING;
		this.setSize(1.4F, 1.2F);
		this.width = 1.6F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}

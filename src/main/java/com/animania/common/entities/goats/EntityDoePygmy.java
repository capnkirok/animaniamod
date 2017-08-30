package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoePygmy extends EntityDoeBase
{

	public EntityDoePygmy(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.PYGMY;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}

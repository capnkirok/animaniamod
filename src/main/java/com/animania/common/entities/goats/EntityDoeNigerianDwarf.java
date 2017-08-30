package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeNigerianDwarf extends EntityDoeBase
{

	public EntityDoeNigerianDwarf(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.NIGERIAN_DWARF;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}

}

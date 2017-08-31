package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckAngora extends EntityBuckBase
{

	public EntityBuckAngora(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ANGORA;
		this.setSize(1.6F, 1.4F);
		this.width = 1.9F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16776179;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13814191;
	}

}

package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckKiko extends EntityBuckBase
{

	public EntityBuckKiko(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KIKO;
		this.setSize(1.3F, 1.2F);
		this.width = 1.5F;
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8802872;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3549475;
	}

}

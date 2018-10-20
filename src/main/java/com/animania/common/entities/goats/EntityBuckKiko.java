package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckKiko extends EntityBuckBase
{

	public EntityBuckKiko(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KIKO;
		this.setSize(1.2F, 1.0F);

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

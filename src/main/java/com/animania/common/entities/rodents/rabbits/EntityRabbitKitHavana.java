package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitHavana extends EntityRabbitKitBase
{

	public EntityRabbitKitHavana(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.HAVANA;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4079166;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}
	
}
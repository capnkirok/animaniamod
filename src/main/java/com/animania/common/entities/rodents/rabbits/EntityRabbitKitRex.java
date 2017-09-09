package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitRex extends EntityRabbitKitBase
{

	public EntityRabbitKitRex(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.REX;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 13419709;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 5389358;
	}
	
}
package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitCottontail extends EntityRabbitKitBase
{

	public EntityRabbitKitCottontail(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.COTTONTAIL;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 11310726;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 7559493;
	}
	
}
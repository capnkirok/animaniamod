package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitNewZealand extends EntityRabbitKitBase
{

	public EntityRabbitKitNewZealand(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.NEW_ZEALAND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16513529;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14211031;
	}
	
}
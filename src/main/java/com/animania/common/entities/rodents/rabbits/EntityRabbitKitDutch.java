package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitDutch extends EntityRabbitKitBase
{

	public EntityRabbitKitDutch(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.DUTCH;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16777215;
	}
	
}
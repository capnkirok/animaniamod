package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class EntityRabbitKitLop extends EntityRabbitKitBase
{

	public EntityRabbitKitLop(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.LOP;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16513763;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12883817;
	}
	
}
package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRabbitDoeRex extends EntityRabbitDoeBase
{

	public EntityRabbitDoeRex(World worldIn)
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
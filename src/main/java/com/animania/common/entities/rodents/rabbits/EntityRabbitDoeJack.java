package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitDoeJack extends EntityRabbitDoeBase
{

	public EntityRabbitDoeJack(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.JACK;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12692381;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 6640455;
	}
	
}
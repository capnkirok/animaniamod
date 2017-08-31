package com.animania.common.entities.rodents.rabbits;

import com.animania.common.entities.rodents.EntityRabbitBase;
import com.animania.common.entities.rodents.RabbitType;

import net.minecraft.world.World;

public class EntityRabbitJack extends EntityRabbitBase
{

	public EntityRabbitJack(World worldIn)
	{
		super(worldIn);
		this.type = RabbitType.LOP;
	}
	
}
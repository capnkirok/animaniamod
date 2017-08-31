package com.animania.common.entities.rodents.rabbits;

import com.animania.common.entities.rodents.EntityRabbitBase;
import com.animania.common.entities.rodents.RabbitType;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRabbitChinchilla extends EntityRabbitBase
{

	public EntityRabbitChinchilla(World worldIn)
	{
		super(worldIn);
		this.type = RabbitType.LOP;
	}
	
}
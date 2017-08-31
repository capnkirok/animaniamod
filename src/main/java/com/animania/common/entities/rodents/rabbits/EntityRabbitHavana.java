package com.animania.common.entities.rodents.rabbits;

import com.animania.common.entities.rodents.EntityRabbitBase;
import com.animania.common.entities.rodents.RabbitType;

import net.minecraft.world.World;

public class EntityRabbitHavana extends EntityRabbitBase
{

	public EntityRabbitHavana(World worldIn)
	{
		super(worldIn);
		this.type = RabbitType.LOP;
	}
	
}
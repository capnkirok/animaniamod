package com.animania.common.entities.rodents.rabbits;

import com.animania.common.entities.rodents.EntityRabbitBase;
import com.animania.common.entities.rodents.RabbitType;

import net.minecraft.world.World;

public class EntityRabbitLop extends EntityRabbitBase
{

	public EntityRabbitLop(World worldIn)
	{
		super(worldIn);
		this.type = RabbitType.LOP;
	}
	
}
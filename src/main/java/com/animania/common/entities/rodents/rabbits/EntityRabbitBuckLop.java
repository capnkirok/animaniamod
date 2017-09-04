package com.animania.common.entities.rodents.rabbits;

import com.animania.common.entities.rodents.RabbitType;

import net.minecraft.world.World;

public class EntityRabbitBuckLop extends EntityAnimaniaRabbit
{

	public EntityRabbitBuckLop(World worldIn)
	{
		super(worldIn);
		this.type = RabbitType.LOP;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15845576;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 9859698;
	}
}
package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRabbitBuckNewZealand extends EntityRabbitBuckBase
{

	public EntityRabbitBuckNewZealand(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.NEW_ZEALAND;
		this.dropRaw = ItemHandler.rawRabbit;
		this.dropCooked = ItemHandler.cookedRabbit;
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
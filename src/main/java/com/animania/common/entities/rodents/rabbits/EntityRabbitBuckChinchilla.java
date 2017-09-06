package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRabbitBuckChinchilla extends EntityRabbitBuckBase
{

	public EntityRabbitBuckChinchilla(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.CHINCHILLA;
		this.dropRaw = ItemHandler.rawRabbit;
		this.dropCooked = ItemHandler.cookedRabbit;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 13750737;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8289918;
	}
}
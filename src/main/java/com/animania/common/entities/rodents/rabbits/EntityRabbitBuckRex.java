package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitBuckRex extends EntityRabbitBuckBase
{

	public EntityRabbitBuckRex(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.REX;
		this.dropRaw = ItemHandler.rawRabbit;
		this.dropCooked = ItemHandler.cookedRabbit;
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
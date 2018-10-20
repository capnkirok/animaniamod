package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitBuckLop extends EntityRabbitBuckBase
{

	public EntityRabbitBuckLop(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.LOP;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16513763;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12883817;
	}
}
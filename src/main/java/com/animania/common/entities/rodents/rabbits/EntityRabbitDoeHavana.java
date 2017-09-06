package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitDoeHavana extends EntityRabbitDoeBase
{

	public EntityRabbitDoeHavana(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.HAVANA;
		this.dropRaw = Item.getItemFromBlock(Blocks.AIR);
		this.dropCooked = Item.getItemFromBlock(Blocks.AIR);
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4079166;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}
	
}
package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitDoeDutch extends EntityRabbitDoeBase
{

	public EntityRabbitDoeDutch(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.DUTCH;
		this.dropRaw = Item.getItemFromBlock(Blocks.AIR);
		this.dropCooked = Item.getItemFromBlock(Blocks.AIR);
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16777215;
	}
	
}
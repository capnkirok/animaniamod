package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitDoeLop extends EntityRabbitDoeBase
{

	public EntityRabbitDoeLop(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.LOP;
		this.dropRaw = Item.getItemFromBlock(Blocks.AIR);
		this.dropCooked = Item.getItemFromBlock(Blocks.AIR);
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
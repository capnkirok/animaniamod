package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitDoeDutch extends EntityRabbitDoeBase
{

	public EntityRabbitDoeDutch(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.DUTCH;
		this.dropRaw = Items.RABBIT;
		this.dropCooked = Items.COOKED_RABBIT;
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
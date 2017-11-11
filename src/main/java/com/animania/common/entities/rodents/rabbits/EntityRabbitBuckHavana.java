package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitBuckHavana extends EntityRabbitBuckBase
{

	public EntityRabbitBuckHavana(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.HAVANA;
		this.dropRaw = Items.RABBIT;
		this.dropCooked = Items.COOKED_RABBIT;
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
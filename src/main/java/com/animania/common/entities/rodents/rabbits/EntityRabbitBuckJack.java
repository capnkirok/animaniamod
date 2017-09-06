package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitBuckJack extends EntityRabbitBuckBase
{

	public EntityRabbitBuckJack(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.JACK;
		this.dropRaw = Items.RABBIT;
		this.dropCooked = Items.COOKED_RABBIT;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12692381;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 6640455;
	}
}
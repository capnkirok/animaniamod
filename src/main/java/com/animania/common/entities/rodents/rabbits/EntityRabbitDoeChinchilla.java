package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitDoeChinchilla extends EntityRabbitDoeBase
{

	public EntityRabbitDoeChinchilla(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.CHINCHILLA;

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
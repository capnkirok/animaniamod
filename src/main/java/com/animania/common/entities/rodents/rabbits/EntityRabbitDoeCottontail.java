package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRabbitDoeCottontail extends EntityRabbitDoeBase
{

	public EntityRabbitDoeCottontail(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.COTTONTAIL;
		this.dropRaw = Items.RABBIT;
		this.dropCooked = Items.COOKED_RABBIT;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 11310726;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 7559493;
	}
	
}
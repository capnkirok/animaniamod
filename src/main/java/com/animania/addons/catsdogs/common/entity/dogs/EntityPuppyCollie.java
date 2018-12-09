package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyCollie extends EntityPuppyBase
{

	public EntityPuppyCollie(World world)
	{
		super(world);
		this.type = DogType.COLLIE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -12570587;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -197380;
	}
	
	@Override
	public int getVariantCount()
	{
		return 2;
	}
	
	@Override
	public int getEyeColorForVariant(int variant)
	{
		switch(variant)
		{
		case 0: 
			return 0x433227;
		default:
			return 0x1B1B1B;
		}
	}
}

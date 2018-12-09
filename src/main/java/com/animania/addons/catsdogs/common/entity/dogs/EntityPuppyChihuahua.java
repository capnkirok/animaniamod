package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityPuppyChihuahua extends EntityPuppyBase
{

	public EntityPuppyChihuahua(World world)
	{
		super(world);
		this.type = DogType.CHIHUAHUA;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -593428;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -16382716;
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
			return 0x8E5C2B;
		default:
			return 0xE5DAD3;
		}
	}
}

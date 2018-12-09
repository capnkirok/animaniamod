package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityFemaleLabrador extends EntityFemaleDogBase
{

	public EntityFemaleLabrador(World world)
	{
		super(world);
		this.type = DogType.LABRADOR;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -4153993;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -12506848;
	}
	
	@Override
	public int getVariantCount()
	{
		return 3;
	}
	
	@Override
	public int getEyeColorForVariant(int variant)
	{
		switch(variant)
		{
		case 0: 
			return 0xAA7A4C;
		case 1:
			return 0x1B1B1B;
		default:
			return 0x39211A;
		}
	}
}

package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleWolf extends EntityMaleDogBase
{

	public EntityMaleWolf(World world)
	{
		super(world);
		this.type = DogType.WOLF;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -4409680;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13488852;
	}
	
	@Override
	public int getVariantCount()
	{
		return 8;
	}
	
	@Override
	public int getEyeColorForVariant(int variant)
	{
		switch(variant)
		{
		case 0: 
			return 0x524E48;
		case 1:
			return 0xD9D9D9;
		case 2:
			return 0x2A2725;
		case 3:
			return 0x2A2725;
		case 4: 
			return 0x71533F;
		case 5: 
			return 0x9D8C76;
		case 6: 
			return 0x9A9389;
		default:
			return 0xCECECD;
		}
	}
}

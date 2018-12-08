package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityFemaleFox extends EntityFemaleDogBase
{

	public EntityFemaleFox(World world)
	{
		super(world);
		this.type = DogType.FOX;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -5415620;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13946603;
	}
}

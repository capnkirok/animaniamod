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
}

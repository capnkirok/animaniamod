package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleLabrador extends EntityMaleDogBase
{

	public EntityMaleLabrador(World world)
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
}

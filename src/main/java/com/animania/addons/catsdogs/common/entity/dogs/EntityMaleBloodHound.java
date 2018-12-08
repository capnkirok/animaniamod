package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMaleBloodHound extends EntityMaleDogBase
{

	public EntityMaleBloodHound(World world)
	{
		super(world);
		this.type = DogType.BLOOD_HOUND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -5938636;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13689844;
	}
}

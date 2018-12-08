package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityFemaleHusky extends EntityFemaleDogBase
{

	public EntityFemaleHusky(World world)
	{
		super(world);
		this.type = DogType.HUSKY;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -14606304;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -1118482;
	}
}

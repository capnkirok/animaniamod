package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfMooshroom extends EntityCalfBase
{

	public EntityCalfMooshroom(World world)
	{
		super(world);
		this.cowType = CowType.MOOSHROOM;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12325394;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12627887;
	}

}
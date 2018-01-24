package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfHighland extends EntityCalfBase
{

	public EntityCalfHighland(World world)
	{
		super(world);
		this.cowType = CowType.HIGHLAND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8340777;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2760475;
	}

}
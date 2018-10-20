package com.animania.common.entities.cows;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBullHolstein extends EntityBullBase
{

	public EntityBullHolstein(World world)
	{
		super(world);
		this.cowType = CowType.HOLSTEIN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2236962;
	}

}
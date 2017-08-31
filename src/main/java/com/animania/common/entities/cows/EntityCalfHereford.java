package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class EntityCalfHereford extends EntityCalfBase
{

	public EntityCalfHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4461056;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		
		return 15987699;
	}

}
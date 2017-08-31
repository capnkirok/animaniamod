package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class EntityPigletHampshire extends EntityPigletBase
{

	public EntityPigletHampshire(World world)
	{
		super(world);
		this.pigType = PigType.HAMPSHIRE;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 5327691;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13684944;
	}


}
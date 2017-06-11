package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletOldSpot extends EntityPigletBase
{

	public EntityPigletOldSpot(World world)
	{
		super(world);
		this.pigType = PigType.OLD_SPOT;
	}

}
package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletHampshire extends EntityPigletBase
{

	public EntityPigletHampshire(World world)
	{
		super(world);
		this.pigType = PigType.HAMPSHIRE;
	}

}
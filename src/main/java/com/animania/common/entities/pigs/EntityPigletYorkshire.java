package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletYorkshire extends EntityPigletBase
{

	public EntityPigletYorkshire(World world)
	{
		super(world);
		this.pigType = PigType.YORKSHIRE;
	}

}
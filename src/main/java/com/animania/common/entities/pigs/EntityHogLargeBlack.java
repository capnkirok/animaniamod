package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogLargeBlack extends EntityHogBase
{

	public EntityHogLargeBlack(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_BLACK;
	}

}
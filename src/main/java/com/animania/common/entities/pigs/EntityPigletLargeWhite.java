package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletLargeWhite extends EntityPigletBase
{

	public EntityPigletLargeWhite(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_WHITE;
	}

}
package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityPigletDuroc extends EntityPigletBase
{

	public EntityPigletDuroc(World world)
	{
		super(world);
		this.pigType = PigType.DUROC;
	}

}
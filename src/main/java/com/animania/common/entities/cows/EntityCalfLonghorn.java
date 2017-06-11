package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCalfLonghorn extends EntityCalfBase
{

	public EntityCalfLonghorn(World world)
	{
		super(world);
		this.cowType = CowType.LONGHORN;
	}

}
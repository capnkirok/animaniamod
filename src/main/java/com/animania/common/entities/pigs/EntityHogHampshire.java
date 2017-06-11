package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogHampshire extends EntityHogBase
{

	public EntityHogHampshire(World world)
	{
		super(world);
		this.pigType = PigType.HAMPSHIRE;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}

}
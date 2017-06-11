package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogYorkshire extends EntityHogBase
{

	public EntityHogYorkshire(World world)
	{
		super(world);
		this.pigType = PigType.YORKSHIRE;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}

}
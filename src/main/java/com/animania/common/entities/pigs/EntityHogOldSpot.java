package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogOldSpot extends EntityHogBase
{

	public EntityHogOldSpot(World world)
	{
		super(world);
		this.pigType = PigType.OLD_SPOT;
		this.oldDropRaw = ItemHandler.rawOldSpotPork;
		this.oldDropCooked = ItemHandler.cookedOldSpotRoast;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}

}
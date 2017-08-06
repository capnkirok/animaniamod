package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogLargeBlack extends EntityHogBase
{

	public EntityHogLargeBlack(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_BLACK;
		this.oldDropRaw = ItemHandler.rawLargeBlackPork;
		this.oldDropCooked = ItemHandler.cookedLargeBlackRoast;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}

}
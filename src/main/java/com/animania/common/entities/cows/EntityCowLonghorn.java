package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowLonghorn extends EntityCowBase
{

	public EntityCowLonghorn(World world)
	{
		super(world);
		this.cowType = CowType.LONGHORN;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
		this.oldDropRaw = ItemHandler.rawLonghornBeef;
		this.oldDropCooked = ItemHandler.cookedLonghornRoast;
	}

}
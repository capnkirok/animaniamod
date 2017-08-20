package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowAngus extends EntityCowBase
{

	public EntityCowAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
		this.oldDropRaw = ItemHandler.rawAngusBeef;
		this.oldDropCooked = ItemHandler.cookedAngusRoast;
	}

}
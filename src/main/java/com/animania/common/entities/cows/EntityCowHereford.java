package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowHereford extends EntityCowBase
{

	public EntityCowHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
		this.oldDropRaw = ItemHandler.rawHerefordBeef;
		this.oldDropCooked = ItemHandler.cookedHerefordRoast;
	}

}
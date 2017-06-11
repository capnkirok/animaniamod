package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullHereford extends EntityBullBase
{

	public EntityBullHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
	}

}
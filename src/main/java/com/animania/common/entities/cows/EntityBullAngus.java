package com.animania.common.entities.cows;

import com.animania.common.entities.EntityGender;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullAngus extends EntityBullBase
{

	public EntityBullAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
		this.oldDropRaw = ItemHandler.rawAngusBeef;
		this.oldDropCooked = ItemHandler.cookedAngusRoast;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 3028024;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2304560;
	}

}
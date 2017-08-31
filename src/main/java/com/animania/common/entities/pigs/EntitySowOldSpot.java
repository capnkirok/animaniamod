package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntitySowOldSpot extends EntitySowBase
{

	public EntitySowOldSpot(World world)
	{
		super(world);
		this.pigType = PigType.OLD_SPOT;
		this.oldDropRaw = ItemHandler.rawOldSpotPork;
		this.oldDropCooked = ItemHandler.cookedOldSpotRoast;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15845576;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 9859698;
	}

}
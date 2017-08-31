package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogHampshire extends EntityHogBase
{

	public EntityHogHampshire(World world)
	{
		super(world);
		this.pigType = PigType.HAMPSHIRE;
		this.oldDropRaw = ItemHandler.rawHampshirePork;
		this.oldDropCooked = ItemHandler.cookedHampshireRoast;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 5327691;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13684944;
	}

}
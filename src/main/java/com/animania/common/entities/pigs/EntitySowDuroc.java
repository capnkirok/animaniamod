package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntitySowDuroc extends EntitySowBase
{

	public EntitySowDuroc(World world)
	{
		super(world);
		this.pigType = PigType.DUROC;
		this.oldDropRaw = ItemHandler.rawDurocPork;
		this.oldDropCooked = ItemHandler.cookedDurocRoast;
		this.dropRaw = ItemHandler.rawPrimePork;
		this.dropCooked = ItemHandler.cookedPrimePork;
	}

}
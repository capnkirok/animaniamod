package com.animania.common.entities.cows;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class EntityCowLonghorn extends EntityCowBase
{

	public EntityCowLonghorn(World world)
	{
		super(world);
		this.cowType = CowType.LONGHORN;
		this.dropRaw = ItemHandler.rawLonghornBeef;
		this.dropCooked = ItemHandler.cookedLonghornRoast;
	}

}
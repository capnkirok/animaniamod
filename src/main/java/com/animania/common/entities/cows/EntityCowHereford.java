package com.animania.common.entities.cows;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class EntityCowHereford extends EntityCowBase
{

	public EntityCowHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
		this.dropRaw = ItemHandler.rawHerefordBeef;
		this.dropCooked = ItemHandler.cookedHerefordRoast;
	}

}
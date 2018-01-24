package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowHighland extends EntityCowBase
{

	public EntityCowHighland(World world)
	{
		super(world);
		this.cowType = CowType.HIGHLAND;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8340777;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2760475;
	}

}
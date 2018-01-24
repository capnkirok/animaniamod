package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullJersey extends EntityBullBase
{

	public EntityBullJersey(World world)
	{
		super(world);
		this.cowType = CowType.JERSEY;
		this.dropRaw = ItemHandler.rawPrimeBeef;
		this.dropCooked = ItemHandler.cookedPrimeBeef;
		this.oldDropRaw = ItemHandler.rawPrimeBeef;
		this.oldDropCooked = ItemHandler.cookedPrimeBeef;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12089918;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16775643;
	}
	

}
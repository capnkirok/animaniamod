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
		this.oldDropRaw = ItemHandler.rawHerefordBeef;
		this.oldDropCooked = ItemHandler.cookedHerefordRoast;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4461056;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		
		return 15987699;
	}
	

}
package com.animania.common.entities.cows;

import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBullHolstein extends EntityBullBase
{

	public EntityBullHolstein(World world)
	{
		super(world);
		this.cowType = CowType.HOLSTEIN;
		this.dropRaw = Items.BEEF;
		this.dropCooked = Items.COOKED_BEEF;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2236962;
	}

}
package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullJersey extends EntityBullBase
{

	public EntityBullJersey(World world)
	{
		super(world);
		this.cowType = CowType.JERSEY;

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
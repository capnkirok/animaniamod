package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullHighland extends EntityBullBase
{

	public EntityBullHighland(World world)
	{
		super(world);
		this.cowType = CowType.HIGHLAND;

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
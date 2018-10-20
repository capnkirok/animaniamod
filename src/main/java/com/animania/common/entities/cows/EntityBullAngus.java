package com.animania.common.entities.cows;

import com.animania.Animania;
import com.animania.common.entities.EntityGender;
import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBullAngus extends EntityBullBase
{

	public EntityBullAngus(World world)
	{
		super(world);
		this.cowType = CowType.ANGUS;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 3028024;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 2304560;
	}


}
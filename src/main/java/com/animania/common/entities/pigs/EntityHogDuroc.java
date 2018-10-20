package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogDuroc extends EntityHogBase
{

	public EntityHogDuroc(World world)
	{
		super(world);
		this.pigType = PigType.DUROC;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 9399147;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 6896443;
	}

}
package com.animania.common.entities.pigs;

import net.minecraft.world.World;

public class EntityPigletDuroc extends EntityPigletBase
{

	public EntityPigletDuroc(World world)
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
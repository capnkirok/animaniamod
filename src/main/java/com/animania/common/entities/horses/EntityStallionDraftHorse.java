package com.animania.common.entities.horses;

import net.minecraft.world.World;

public class EntityStallionDraftHorse extends EntityStallionBase
{

	public EntityStallionDraftHorse(World world)
	{
		super(world);
		this.horseType = horseType.DRAFT;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8600606;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12829635;
	}

}

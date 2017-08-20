package com.animania.common.entities.horses;

import net.minecraft.world.World;

public class EntityMareDraftHorse extends EntityMareBase
{

	public EntityMareDraftHorse(World world)
	{
		super(world);
		this.horseType = HorseType.DRAFT;
	}

}

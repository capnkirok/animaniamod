package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityRamFriesian extends EntityRamBase
{

	public EntityRamFriesian(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.FRIESIAN;
	}

}

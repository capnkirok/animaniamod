package com.animania.common.entities.sheep;

import net.minecraft.world.World;

public class EntityRamMerino extends EntityRamBase
{

	public EntityRamMerino(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.MERINO;
	}

}

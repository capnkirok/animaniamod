package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidAlpine extends EntityKidBase
{

	public EntityKidAlpine(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ALPINE;
		this.setSize(1.0F, 1.0F); 
		this.width = 1.0F;
		this.height = 1.0F;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 14867928;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8281676;
	}

}

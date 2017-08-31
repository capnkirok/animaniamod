package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidKiko extends EntityKidBase
{

	public EntityKidKiko(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KIKO;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8802872;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3549475;
	}

}

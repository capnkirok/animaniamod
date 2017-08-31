package com.animania.common.entities.goats;

import net.minecraft.world.World;

public class EntityKidNigerianDwarf extends EntityKidBase
{

	public EntityKidNigerianDwarf(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.NIGERIAN_DWARF;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 2697513;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8343350;
	}

}

package com.animania.addons.extra.common.entity.rodents;

import net.minecraft.world.World;

public class EntityFerretGrey extends EntityFerretBase
{

	public EntityFerretGrey(World worldIn)
	{
		super(worldIn);
		this.type = FerretType.GREY;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 13948116;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8741209;
	}
	
}
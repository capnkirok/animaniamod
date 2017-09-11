package com.animania.common.entities.sheep;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityEweSuffolk extends EntityEweBase
{

	public EntityEweSuffolk(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.SUFFOLK;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 16119026;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 2302755;
	}
	
}

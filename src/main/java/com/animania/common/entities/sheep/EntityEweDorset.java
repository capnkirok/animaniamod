package com.animania.common.entities.sheep;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityEweDorset extends EntityEweBase
{

	public EntityEweDorset(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORSET;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4863280;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 15790320;
	}

}

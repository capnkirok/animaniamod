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

}

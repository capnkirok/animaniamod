package com.animania.common.entities.sheep;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityEweDorper extends EntityEweBase
{

	public EntityEweDorper(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORPER;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}

}

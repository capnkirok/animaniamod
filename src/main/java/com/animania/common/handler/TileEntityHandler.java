package com.animania.common.handler;

import com.animania.common.blockentities.BlockEntityInvisiblock;
import com.animania.common.blockentities.BlockEntityNest;
import com.animania.common.blockentities.BlockEntitySaltLick;
import com.animania.common.blockentities.BlockEntityTrough;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockEntityHandler
{

	public static void preInit()
	{
		GameRegistry.registerBlockEntity(BlockEntityTrough.class, "BlockEntityTrough");
		GameRegistry.registerBlockEntity(BlockEntityNest.class, "BlockEntityNest");
		GameRegistry.registerBlockEntity(BlockEntityInvisiblock.class, "BlockEntityInvisiblock");
		GameRegistry.registerBlockEntity(BlockEntitySaltLick.class, "BlockEntitySaltLick");
	}
}

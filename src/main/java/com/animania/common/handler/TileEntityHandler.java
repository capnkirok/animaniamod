package com.animania.common.handler;

import com.animania.common.tileentities.BlockEntityInvisiblock;
import com.animania.common.tileentities.BlockEntityNest;
import com.animania.common.tileentities.BlockEntitySaltLick;
import com.animania.common.tileentities.BlockEntityTrough;

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

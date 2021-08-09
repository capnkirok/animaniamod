package com.animania.common.handler;

import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.registries.GameRegistry;

public class TileEntityHandler
{

	public static void preInit()
	{
		GameRegistry.registerTileEntity(TileEntityTrough.class, "TileEntityTrough");
		GameRegistry.registerTileEntity(TileEntityNest.class, "TileEntityNest");
		GameRegistry.registerTileEntity(TileEntityInvisiblock.class, "TileEntityInvisiblock");
		GameRegistry.registerTileEntity(TileEntitySaltLick.class, "TileEntitySaltLick");
	}
}

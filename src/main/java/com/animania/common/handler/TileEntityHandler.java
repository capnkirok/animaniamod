package com.animania.common.handler;

import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void preInit() {
		GameRegistry.registerTileEntity(TileEntityTrough.class, "TileEntityTrough");
		GameRegistry.registerTileEntity(TileEntityNest.class, "TileEntityNest");
	}
}

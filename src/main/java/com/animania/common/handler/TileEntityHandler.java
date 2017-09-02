package com.animania.common.handler;

import com.animania.common.tileentities.TileEntityCheeseMold;
import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.common.tileentities.TileEntityInvisiblock;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{

	public static void preInit()
	{
		GameRegistry.registerTileEntity(TileEntityTrough.class, "TileEntityTrough");
		GameRegistry.registerTileEntity(TileEntityNest.class, "TileEntityNest");
		GameRegistry.registerTileEntity(TileEntityInvisiblock.class, "TileEntityInvisiblock");
		GameRegistry.registerTileEntity(TileEntityHamsterWheel.class, "TileEntityHamsterWheel");
		GameRegistry.registerTileEntity(TileEntityCheeseMold.class, "TileEntityCheeseMold");
		GameRegistry.registerTileEntity(TileEntitySaltLick.class, "TileEntitySaltLick");

	}
}

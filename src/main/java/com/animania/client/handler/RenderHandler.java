package com.animania.client.handler;

import com.animania.client.render.tileEntity.TileEntityNestRenderer;
import com.animania.client.render.tileEntity.TileEntitySaltLickRenderer;
import com.animania.client.render.tileEntity.TileEntityTroughRenderer;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntitySaltLick;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderHandler
{

	public static void preInit()
	{
		renderEntitiesFactory();
	}

	public static void init()
	{
		renderTileEntity();
	}

	@SideOnly(Dist.CLIENT)
	static void renderEntitiesFactory()
	{

	}

	@SideOnly(Dist.CLIENT)
	static void renderTileEntity()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNest.class, new TileEntityNestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrough.class, new TileEntityTroughRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySaltLick.class, new TileEntitySaltLickRenderer());

	}
}

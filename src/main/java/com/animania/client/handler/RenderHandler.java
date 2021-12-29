package com.animania.client.handler;

import com.animania.client.render.tileEntity.BlockEntityNestRenderer;
import com.animania.client.render.tileEntity.BlockEntitySaltLickRenderer;
import com.animania.client.render.tileEntity.BlockEntityTroughRenderer;
import com.animania.common.blockentities.BlockEntityNest;
import com.animania.common.blockentities.BlockEntitySaltLick;
import com.animania.common.blockentities.BlockEntityTrough;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
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
		renderBlockEntity();
	}

	@SideOnly(Dist.CLIENT)
	static void renderEntitiesFactory()
	{

	}

	@SideOnly(Dist.CLIENT)
	static void renderBlockEntity()
	{
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityNest.class, new BlockEntityNestRenderer());
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntityTrough.class, new BlockEntityTroughRenderer());
		ClientRegistry.bindBlockEntitySpecialRenderer(BlockEntitySaltLick.class, new BlockEntitySaltLickRenderer());

	}
}

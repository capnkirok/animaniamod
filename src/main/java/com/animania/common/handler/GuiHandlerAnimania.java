package com.animania.common.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandlerAnimania implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, PlayerEntity player, World world, int x, int y, int z)
	{
		return AddonHandler.openAddonGui(player, id, world, x, y, z, Dist.DEDICATED_SERVER);
		
	}

	@Override
	public Object getClientGuiElement(int id, PlayerEntity player, World world, int x, int y, int z)
	{
		return AddonHandler.openAddonGui(player, id, world, x, y, z, Dist.CLIENT);
	}
}

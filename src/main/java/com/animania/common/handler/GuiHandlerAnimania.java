package com.animania.common.handler;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerAnimania implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, Player player, Level level, int x, int y, int z)
	{
		return AddonHandler.openAddonGui(player, id, level, x, y, z, Dist.DEDICATED_SERVER);

	}

	@Override
	public Object getClientGuiElement(int id, Player player, Level level, int x, int y, int z)
	{
		return AddonHandler.openAddonGui(player, id, level, x, y, z, Dist.CLIENT);
	}
}

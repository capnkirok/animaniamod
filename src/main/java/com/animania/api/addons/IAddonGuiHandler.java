package com.animania.api.addons;

import javax.annotation.Nullable;

import net.minecraft.world.entity.player.Player;

public interface IAddonGuiHandler
{

	@Nullable
    Object getServerGuiElement(int ID, Player player, Level level, int x, int y, int z);

  
    @Nullable
    Object getClientGuiElement(int ID, Player player, Level level, int x, int y, int z);
    
    void setGuiIdOffset(int offset);
    
    int getGuiIdOffset();

	
}

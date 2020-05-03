package com.animania.api.addons;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IAddonGuiHandler
{

	@Nullable
    Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);

  
    @Nullable
    Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
    
    void setGuiIdOffset(int offset);
    
    int getGuiIdOffset();

	
}

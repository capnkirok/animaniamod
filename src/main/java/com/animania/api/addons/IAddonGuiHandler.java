package com.animania.api.addons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IAddonGuiHandler
{

	@Nullable
    Object getServerGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z);

  
    @Nullable
    Object getClientGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z);
    
    void setGuiIdOffset(int offset);
    
    int getGuiIdOffset();

	
}

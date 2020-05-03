package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderChild;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityPigletProvider extends WailaEntityAnimalProviderChild
{
    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
    
    	EntityAnimaniaPig tempEnt = (EntityAnimaniaPig) entity;
		boolean played = tempEnt.getPlayed();

        if (played)
            currenttip.add(I18n.translateToLocal("text.waila.played"));
        else
            currenttip.add(I18n.translateToLocal("text.waila.bored"));

        currenttip = super.getWailaBody(entity, currenttip, accessor, config);

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {

        tag.setBoolean("Played", ent.getEntityData().getBoolean("Played"));

        return super.getNBTData(player, ent, tag, world);
    }
}

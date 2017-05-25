package com.animania.compat.waila.provider;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityPigProvider extends WailaEntityAnimalProviderMateable
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        boolean played = accessor.getNBTData().getBoolean("Played");

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

package com.animania.compat.waila.provider;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityAnimalProviderBase implements IWailaEntityProvider
{

    @Override
    public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        NBTTagCompound comp = accessor.getNBTData();
        boolean fed = accessor.getNBTData().getBoolean("Fed");
        boolean watered = accessor.getNBTData().getBoolean("Watered");

        if (fed && watered)
            currenttip.add(I18n.translateToLocal("text.waila.fed"));

        if (fed && !watered)
            currenttip.add(I18n.translateToLocal("text.waila.thirsty"));

        if (!fed && watered)
            currenttip.add(I18n.translateToLocal("text.waila.hungry"));

        if (!fed && !watered)
            currenttip.add(I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
        NBTTagCompound comp = ent.getEntityData();

        tag.setBoolean("Fed", comp.getBoolean("Fed"));
        tag.setBoolean("Watered", comp.getBoolean("Watered"));

        return tag;
    }

}

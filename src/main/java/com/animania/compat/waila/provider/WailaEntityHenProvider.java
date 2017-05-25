package com.animania.compat.waila.provider;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityHenProvider extends WailaEntityAnimalProviderBase
{
    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);
        NBTTagCompound tag = accessor.getNBTData();
        int timer = tag.getInteger("EggLayTime");

        if (accessor.getPlayer().isSneaking())
            currenttip.add(I18n.translateToLocal("text.waila.egglay") + ": " + timer);

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
        int laytime = ent.getEntityData().getInteger("EggLayTime");
        tag.setInteger("EggLayTime", laytime);

        return super.getNBTData(player, ent, tag, world);
    }

}

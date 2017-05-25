package com.animania.compat.waila.provider;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityRodentProvider extends WailaEntityAnimalProviderBase
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);
        boolean tamed = accessor.getNBTData().getBoolean("IsTamed");
        boolean sitting = accessor.getNBTData().getBoolean("IsSitting");

        if (sitting)
            currenttip.add(I18n.translateToLocal("text.waila.sitting"));

        if (accessor.getPlayer().isSneaking())
            if (tamed) {
                EntityLivingBase owner = ((EntityTameable) accessor.getEntity()).getOwner();
                if (owner != null) {
                    String name = owner.getName();
                    if (!name.equals(""))
                        currenttip.add(I18n.translateToLocal("text.waila.tamed") + " (" + name + ")");
                    else
                        currenttip.add(I18n.translateToLocal("text.waila.tamed"));
                }
                else
                    currenttip.add(I18n.translateToLocal("text.waila.ownermissing"));

            }

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
        NBTTagCompound comp = ent.getEntityData();

        tag.setBoolean("IsSitting", comp.getBoolean("IsSitting"));
        tag.setBoolean("IsTamed", comp.getBoolean("IsTamed"));

        return super.getNBTData(player, ent, tag, world);
    }

}

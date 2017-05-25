package com.animania.compat.waila.provider;

import java.util.List;
import java.util.UUID;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityAnimalProviderChild extends WailaEntityAnimalProviderBase
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);

        if (accessor.getPlayer().isSneaking()) {
            String parent = accessor.getNBTData().getString("ParentUUID");
            World world = entity.world;

            if (!parent.equals("")) {
                for (Entity e : world.getLoadedEntityList()) {
                    UUID id = e.getPersistentID();
                    if (id.toString().equals(parent)) {
                        String name = e.getCustomNameTag();
                        if (!name.equals(""))
                            currenttip.add(I18n.translateToLocal("text.waila.parent") + " (" + name + ")");

                        return currenttip;
                    }
                }

                currenttip.add(I18n.translateToLocal("text.waila.parentmissing"));

            }

        }

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
        NBTTagCompound comp = ent.getEntityData();

        String parent = comp.getString("ParentUUID");
        if (!parent.equals(""))
            tag.setString("ParentUUID", parent);

        return super.getNBTData(player, ent, tag, world);
    }

}

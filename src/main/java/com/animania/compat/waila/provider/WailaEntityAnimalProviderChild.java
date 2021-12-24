package com.animania.compat.waila.provider;

import java.util.List;
import java.util.UUID;

import com.animania.common.helper.AnimaniaHelper;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class WailaAnimalEntityProviderChild extends WailaAnimalEntityProviderBase
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);

        if (accessor.getPlayer().isSneaking()) {
            String parent = accessor.getNBTData().getString("ParentUUID");
            World world = entity.level;

            if (!parent.equals("")) {
                for (Entity e : AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 20, world, entity)) {
                    UUID id = e.getUUID();
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
    public CompoundNBT getNBTData(ServerPlayerEntity player, Entity ent, CompoundNBT tag, World world) {
        CompoundNBT comp = ent.getEntityData();

        String parent = comp.getString("ParentUUID");
        if (!parent.equals(""))
            tag.setString("ParentUUID", parent);

        return super.getNBTData(player, ent, tag, world);
    }

}

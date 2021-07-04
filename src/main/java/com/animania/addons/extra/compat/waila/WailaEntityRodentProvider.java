package com.animania.addons.extra.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityRodentProvider extends WailaAnimalEntityProviderBase
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);
        boolean tamed = accessor.getNBTData().getBoolean("IsTamed");
        boolean sitting = accessor.getNBTData().getBoolean("IsSitting");
        boolean sleeping = accessor.getNBTData().getBoolean("Sleep");

        if (sitting)
            currenttip.add(I18n.translateToLocal("text.waila.sitting"));
        
        if (sleeping)
        	currenttip.add(I18n.translateToLocal("text.waila.sleeping"));
        
        if (accessor.getPlayer().isSneaking())
            if (tamed) {
                LivingEntity owner = ((TameableEntity) accessor.getEntity()).getOwner();
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
    public CompoundNBT getNBTData(EntityPlayerMP player, Entity ent, CompoundNBT tag, World world) {
        CompoundNBT comp = ent.getEntityData();

        tag.setBoolean("IsSitting", comp.getBoolean("IsSitting"));
        tag.setBoolean("IsTamed", comp.getBoolean("IsTamed"));
        tag.setBoolean("Sleep", comp.getBoolean("Sleep"));

        return super.getNBTData(player, ent, tag, world);
    }

}

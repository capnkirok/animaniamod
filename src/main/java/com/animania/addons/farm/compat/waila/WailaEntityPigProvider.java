package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class WailaPigEntityProvider extends WailaAnimalEntityProviderMateable
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
    public CompoundNBT getNBTData(ServerPlayerEntity player, Entity ent, CompoundNBT tag, World world) {

        tag.putBoolean("Played", ent.getEntityData().getBoolean("Played"));

        return super.getNBTData(player, ent, tag, world);
    }

}

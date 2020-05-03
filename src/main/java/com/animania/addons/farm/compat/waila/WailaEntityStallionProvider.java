package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaEntityAnimalProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;

public class WailaEntityStallionProvider extends WailaEntityAnimalProviderMateable
{

    @Override
    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        currenttip = super.getWailaBody(entity, currenttip, accessor, config);

        return currenttip;
    }

}

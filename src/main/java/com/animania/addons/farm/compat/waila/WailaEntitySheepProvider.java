package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;

public class WailaSheepEntityProvider extends WailaAnimalEntityProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		if (accessor.getPlayer().isSneaking() && currenttip.contains(I18n.translateToLocal("text.waila.fed")))
			currenttip.add(I18n.translateToLocal("text.waila.milkable"));
		return currenttip;
	}

}

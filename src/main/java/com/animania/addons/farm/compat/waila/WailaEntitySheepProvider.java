package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;

public class WailaSheepEntityProvider extends WailaAnimalEntityProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		if (accessor.getPlayer().isSneaking())
		{
			if (currenttip.contains(I18n.translateToLocal("text.waila.fed")))
				currenttip.add(I18n.translateToLocal("text.waila.milkable"));
			
			/*int timer = accessor.getNBTData().getInteger("gestation");
			
			if (timer > -1)
			{
				currenttip.add(I18n.translateToLocal("text.waila.pregnant1") + ", " + timer + " " + I18n.translateToLocal("text.waila.pregnant2"));
			} */
		}
		return currenttip;
	}


}

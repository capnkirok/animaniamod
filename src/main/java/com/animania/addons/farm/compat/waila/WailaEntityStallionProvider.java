package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;

public class WailaEntityStallionProvider extends WailaAnimalEntityProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return super.getWailaBody(entity, currenttip, accessor, config);
	}

}

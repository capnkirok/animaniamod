package com.animania.compat.waila.provider;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityCowProvider extends WailaEntityAnimalProviderMateable
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		
		if(currenttip.contains(I18n.translateToLocal("text.waila.happy")) && accessor.getPlayer().isSneaking())
			currenttip.add(I18n.translateToLocal("text.waila.milkable"));
		
		
		return currenttip;
	}
	
	
	
}

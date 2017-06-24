package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.entities.cows.EntityCowBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityCowProvider extends WailaEntityAnimalProviderMateable
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

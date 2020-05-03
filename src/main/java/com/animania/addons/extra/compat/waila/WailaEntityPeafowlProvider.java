package com.animania.addons.extra.compat.waila;

import java.util.List;

import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityPeafowlProvider extends WailaEntityAnimalProviderBase
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);

		EntityPeafowlBase ehb = (EntityPeafowlBase)entity;
		int timer = ehb.getLaidTimer();


		if (accessor.getPlayer().isSneaking())

			if (timer >= 0) { 
				currenttip.add(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			} else {
				currenttip.add(I18n.translateToLocal("text.waila.egglay2"));
			}
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world) {
		int laytime = ent.getEntityData().getInteger("EggLayTime");
		tag.setInteger("EggLayTime", laytime);

		return super.getNBTData(player, ent, tag, world);
	}

}

package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderChild;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;

public class WailaPigEntityletProvider extends WailaAnimalEntityProviderChild
{
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{

		EntityAnimaniaPig tempEnt = (EntityAnimaniaPig) entity;
		boolean played = tempEnt.getPlayed();

		if (played)
			currenttip.add(I18n.translateToLocal("text.waila.played"));
		else
			currenttip.add(I18n.translateToLocal("text.waila.bored"));

		return super.getWailaBody(entity, currenttip, accessor, config);
	}

	@Override
	public CompoundTag getNBTData(ServerPlayerEntity player, Entity ent, CompoundTag tag, Level level)
	{

		tag.putBoolean("Played", ent.getEntityData().getBoolean("Played"));

		return super.getNBTData(player, ent, tag, level);
	}
}

package com.animania.addons.extra.compat.waila;

import java.util.List;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class WailaEntityRodentProvider extends WailaAnimalEntityProviderBase
{

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		currenttip = super.getWailaBody(entity, currenttip, accessor, config);
		boolean tamed = accessor.getNBTData().getBoolean("IsTamed");
		boolean sitting = accessor.getNBTData().getBoolean("IsSitting");
		boolean sleeping = accessor.getNBTData().getBoolean("Sleep");

		if (sitting)
			currenttip.add(I18n.translateToLocal("text.waila.sitting"));

		if (sleeping)
			currenttip.add(I18n.translateToLocal("text.waila.sleeping"));

		if (accessor.getPlayer().isSneaking() && tamed)
		{
			LivingEntity owner = ((TameableEntity) accessor.getEntity()).getOwner();
			if (owner != null)
			{
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
	public CompoundTag getNBTData(ServerPlayer player, Entity ent, CompoundTag tag, Level level)
	{
		CompoundTag comp = ent.getEntityData();

		tag.putBoolean("IsSitting", comp.getBoolean("IsSitting"));
		tag.putBoolean("IsTamed", comp.getBoolean("IsTamed"));
		tag.putBoolean("Sleep", comp.getBoolean("Sleep"));

		return super.getNBTData(player, ent, tag, level);
	}

}

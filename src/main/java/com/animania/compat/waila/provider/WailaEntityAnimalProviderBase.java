package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.ISleeping;
import com.animania.api.interfaces.ISterilizable;
import com.animania.config.AnimaniaConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;

public class WailaAnimalEntityProviderBase implements IWailaEntityProvider
{

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{

		CompoundTag comp = accessor.getNBTData();
		boolean fed = accessor.getNBTData().getBoolean("Fed");
		boolean watered = accessor.getNBTData().getBoolean("Watered");
		boolean sleeping = accessor.getNBTData().getBoolean("Sleeping");

		if (entity instanceof IFoodEating)
		{
			IFoodEating tempEnt = (IFoodEating) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
		}
		if (entity instanceof ISleeping)
		{
			ISleeping tempEnt = (ISleeping) entity;
			sleeping = tempEnt.getSleeping();
		}

		if (fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.fed"));

		if (fed && !watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.thirsty"));

		if (!fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.hungry"));

		if (!fed && !watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));

		if (sleeping)
			currenttip.add(I18n.translateToLocal("text.waila.sleeping"));

		if (accessor.getPlayer().isSneaking())
			if (entity instanceof ISterilizable)
			{
				if (((ISterilizable) entity).getSterilized())
					currenttip.add(I18n.translateToLocal("text.waila.sterilized"));
			}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayerEntity player, Entity ent, CompoundTag tag, Level level)
	{
		CompoundTag comp = ent.getEntityData();

		tag.putBoolean("Fed", comp.getBoolean("Fed"));
		tag.putBoolean("Watered", comp.getBoolean("Watered"));

		return tag;
	}

}

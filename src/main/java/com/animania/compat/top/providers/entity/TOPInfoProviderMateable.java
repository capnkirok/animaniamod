package com.animania.compat.top.providers.entity;

import java.util.UUID;

import com.animania.api.interfaces.ISterilizable;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public interface TOPInfoProviderMateable extends TOPInfoProviderBase
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{

		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, level, entity, data);

		if ((player.isSneaking() && entity instanceof ISterilizable) && ((ISterilizable) entity).getSterilized())
			probeInfo.text(I18n.translateToLocal("text.waila.sterilized"));

		if (mode == ProbeMode.EXTENDED)
		{

			CompoundTag nbt = new CompoundTag();
			entity.writeToNBT(nbt);
			String mate = nbt.getString("MateUUID");

			if (mate != null && !mate.equals(""))
			{
				for (Entity e : AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 20, level, entity))
				{
					UUID id = e.getUUID();
					if (id.toString().equals(mate))
					{
						String name = e.getCustomNameTag();
						if (!name.equals(""))
						{
							probeInfo.entity(e).text(I18n.translateToLocal("text.waila.mated") + " (" + name + ")");
						}
						else
							probeInfo.entity(e).text(I18n.translateToLocal("text.waila.mated"));

						return;
					}
				}

				// probeInfo.text(I18n.translateToLocal("text.waila.matemissing"));
			}
		}

	}

}

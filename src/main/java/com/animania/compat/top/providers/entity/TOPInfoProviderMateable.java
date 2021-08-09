package com.animania.compat.top.providers.entity;

import java.util.UUID;

import com.animania.api.interfaces.ISterilizable;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public interface TOPInfoProviderMateable extends TOPInfoProviderBase
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
	{

		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, world, entity, data);

		if (player.isSneaking())
			if (entity instanceof ISterilizable)
			{
				if (((ISterilizable) entity).getSterilized())
					probeInfo.text(I18n.translateToLocal("text.waila.sterilized"));
			}

		if (mode == ProbeMode.EXTENDED)
		{

			CompoundNBT nbt = new CompoundNBT();
			entity.writeToNBT(nbt);
			String mate = nbt.getString("MateUUID");

			if (mate != null)
			{
				if (!mate.equals(""))
				{
					for (Entity e : AnimaniaHelper.getEntitiesInRange(LivingEntity.class, 20, world, entity))
					{
						UUID id = e.getPersistentID();
						if (id.toString().equals(mate))
						{
							String name = e.getCustomNameTag();
							if (!name.equals(""))
							{
								probeInfo.entity(e).text(I18n.translateToLocal("text.waila.mated") + " (" + name + ")");
							} else
								probeInfo.entity(e).text(I18n.translateToLocal("text.waila.mated"));

							return;
						}
					}

					// probeInfo.text(I18n.translateToLocal("text.waila.matemissing"));
				}
			}
		}

	}

}

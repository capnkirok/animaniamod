package com.animania.compat.top.providers.entity;

import java.util.UUID;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import com.animania.common.entities.ISterilizable;
import com.animania.common.helper.AnimaniaHelper;

public interface TOPInfoProviderMateable extends TOPInfoProviderBase
{

	@Override
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
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

			NBTTagCompound nbt = new NBTTagCompound();
			entity.writeToNBT(nbt);
			String mate = nbt.getString("MateUUID");

			if (mate != null)
			{
				if (!mate.equals(""))
				{
					for (Entity e : AnimaniaHelper.getEntitiesInRange(EntityLivingBase.class, 20, world, entity))
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

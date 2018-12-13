package com.animania.compat.top.providers.entity;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IGendered;
import com.animania.compat.top.providers.TOPInfoEntityProvider;
import com.animania.config.AnimaniaConfig;

public interface TOPInfoProviderBase extends TOPInfoEntityProvider
{

	@Override
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		NBTTagCompound tag = new NBTTagCompound();
		entity.writeToNBT(tag);

		if (player.isSneaking())
		{
			if (entity instanceof IGendered)
			{
				IGendered igendered = (IGendered) entity;
				if (igendered.getEntityGender() == EntityGender.MALE || igendered.getEntityGender() == EntityGender.FEMALE)
					probeInfo.text(igendered.getEntityGender() == EntityGender.MALE ? TextFormatting.AQUA + "\u2642" : TextFormatting.LIGHT_PURPLE + "\u2640");
			}
		}
		
		boolean fed = tag.getBoolean("Fed");
		boolean watered = tag.getBoolean("Watered");
		boolean sleeping = tag.getBoolean("Sleep");

		if (fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.fed"));

		if (fed && !watered)
			probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.thirsty"));

		if (!fed && watered)
			probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.hungry"));

		if (!fed && !watered)
			probeInfo.text(TextFormatting.RED + I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));
		
		if (sleeping)
			probeInfo.text(TextFormatting.GRAY + I18n.translateToLocal("text.waila.sleeping"));


		
	}

}

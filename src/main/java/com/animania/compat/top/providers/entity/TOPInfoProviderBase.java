package com.animania.compat.top.providers.entity;

import com.animania.compat.top.providers.TOPInfoEntityProvider;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public interface TOPInfoProviderBase extends TOPInfoEntityProvider
{

	@Override
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		NBTTagCompound tag = new NBTTagCompound();
		entity.writeToNBT(tag);

		boolean fed = tag.getBoolean("Fed");
		boolean watered = tag.getBoolean("Watered");
		
		 if (fed && watered)
	            probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.fed"));

	        if (fed && !watered)
	            probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.thirsty"));

	        if (!fed && watered)
	            probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.hungry"));

	        if (!fed && !watered)
	            probeInfo.text(TextFormatting.RED + I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));
	}

}

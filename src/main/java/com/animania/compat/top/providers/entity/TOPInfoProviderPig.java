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

public interface TOPInfoProviderPig extends TOPInfoProviderMateable
{

	@Override
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		NBTTagCompound tag = new NBTTagCompound();
		entity.writeToNBT(tag);
		
		boolean played = tag.getBoolean("Played");
		
		 if (played)
	           	probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.played"));
	        else
	        	probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.bored"));
		
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}

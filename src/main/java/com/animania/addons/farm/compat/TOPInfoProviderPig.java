package com.animania.addons.farm.compat;

import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface TOPInfoProviderPig extends TOPInfoProviderMateable
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		CompoundTag tag = new CompoundTag();
		entity.writeToNBT(tag);

		boolean played = tag.getBoolean("Played");

		if (played)
			probeInfo.text(ChatFormatting.GREEN + I18n.translateToLocal("text.waila.played"));
		else
			probeInfo.text(ChatFormatting.YELLOW + I18n.translateToLocal("text.waila.bored"));

		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

}

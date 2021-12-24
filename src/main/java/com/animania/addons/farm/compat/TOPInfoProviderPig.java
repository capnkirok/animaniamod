package com.animania.addons.farm.compat;

import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.text.TextFormatting;

public interface TOPInfoProviderPig extends TOPInfoProviderMateable
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, Level level, Entity entity, IProbeHitEntityData data)
	{
		CompoundTag tag = new CompoundTag();
		entity.writeToNBT(tag);

		boolean played = tag.getBoolean("Played");

		if (played)
			probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.played"));
		else
			probeInfo.text(TextFormatting.YELLOW + I18n.translateToLocal("text.waila.bored"));

		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

}

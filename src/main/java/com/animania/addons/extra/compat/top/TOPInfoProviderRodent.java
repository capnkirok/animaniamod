package com.animania.addons.extra.compat.top;

import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public interface TOPInfoProviderRodent extends TOPInfoProviderBase
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{

		CompoundTag tag = new CompoundTag();
		entity.writeToNBT(tag);

		boolean sitting = tag.getBoolean("IsSitting");

		if (sitting)
			probeInfo.text(ChatFormatting.GRAY + I18n.translateToLocal("text.waila.sitting"));

		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, level, entity, data);

	}

}

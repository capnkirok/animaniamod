package com.animania.compat.top.providers.entity;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IGendered;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.TOPInfoEntityProvider;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.text.ChatFormatting;

public interface TOPInfoProviderBase extends TOPInfoEntityProvider
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		CompoundTag tag = new CompoundTag();
		entity.writeToNBT(tag);

		if ((player.isSneaking() && entity instanceof IGendered igendered) && (igendered.getEntityGender() == EntityGender.MALE || igendered.getEntityGender() == EntityGender.FEMALE))
			probeInfo.text(igendered.getEntityGender() == EntityGender.MALE ? ChatFormatting.AQUA + "\u2642" : ChatFormatting.LIGHT_PURPLE + "\u2640");

		boolean fed = tag.getBoolean("Fed");
		boolean watered = tag.getBoolean("Watered");
		boolean sleeping = tag.getBoolean("Sleep");

		if (fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			probeInfo.text(ChatFormatting.GREEN + I18n.translateToLocal("text.waila.fed"));

		if (fed && !watered)
			probeInfo.text(ChatFormatting.YELLOW + I18n.translateToLocal("text.waila.thirsty"));

		if (!fed && watered)
			probeInfo.text(ChatFormatting.YELLOW + I18n.translateToLocal("text.waila.hungry"));

		if (!fed && !watered)
			probeInfo.text(ChatFormatting.RED + I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));

		if (sleeping)
			probeInfo.text(ChatFormatting.GRAY + I18n.translateToLocal("text.waila.sleeping"));

	}

}

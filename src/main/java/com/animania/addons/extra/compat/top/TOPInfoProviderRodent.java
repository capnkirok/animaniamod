package com.animania.addons.extra.compat.top;

import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public interface TOPInfoProviderRodent extends TOPInfoProviderBase
{

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	default void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
	{

		CompoundNBT tag = new CompoundNBT();
		entity.writeToNBT(tag);

		boolean sitting = tag.getBoolean("IsSitting");
		
		if (sitting)
			probeInfo.text(TextFormatting.GRAY + I18n.translateToLocal("text.waila.sitting"));
		
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, world, entity, data);

		
	}

}

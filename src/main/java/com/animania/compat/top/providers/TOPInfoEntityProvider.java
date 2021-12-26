package com.animania.compat.top.providers;

import com.animania.common.handler.CompatHandler;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface TOPInfoEntityProvider
{

	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data);

}

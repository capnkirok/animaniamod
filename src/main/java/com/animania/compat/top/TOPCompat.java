package com.animania.compat.top;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.compat.top.providers.TOPInfoEntityProvider;
import com.animania.compat.top.providers.TOPInfoProvider;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class TOPCompat implements Function<ITheOneProbe, Void>
{

	public static ITheOneProbe probe;

	@Nullable
	@Override
	public Void apply(ITheOneProbe input)
	{
		probe = input;
		probe.registerProvider(new IProbeInfoProvider()
		{
			@Override
			public String getID()
			{
				return Animania.MODID + ":blocks";
			}

			@Override
			public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, BlockState blockState, IProbeHitData data)
			{
				if (blockState.getBlock() instanceof TOPInfoProvider)
				{
					TOPInfoProvider provider = (TOPInfoProvider) blockState.getBlock();
					provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
				}

			}
			
			
		});
		
		probe.registerEntityProvider(new IProbeInfoEntityProvider()
				{

					@Override
					public String getID()
					{
						return Animania.MODID + ":entities";
					}

					@Override
					public void addProbeEntityInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
					{

						if (entity instanceof TOPInfoEntityProvider)
						{
							TOPInfoEntityProvider provider = (TOPInfoEntityProvider)entity;
							provider.addProbeInfo(mode, probeInfo, player, world, entity, data);
						}
						
					}
			
				});
		
		return null;
	}

}

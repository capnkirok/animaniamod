package com.animania.compat.top;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TOPCompat implements Function<ITheOneProbe, Void>
{

	public static ITheOneProbe probe;
	
    @Nullable
	@Override
	public Void apply(ITheOneProbe input)
	{
    	probe = input;
    	probe.registerProvider(new IProbeInfoProvider() {
            @Override
            public String getID() {
                return Animania.MODID + ":default";
            }

            @Override
            public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
                if (blockState.getBlock() instanceof TOPInfoProvider) {
                    TOPInfoProvider provider = (TOPInfoProvider) blockState.getBlock();
                    provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                }
                

            }
        });
		return null;
	}

}

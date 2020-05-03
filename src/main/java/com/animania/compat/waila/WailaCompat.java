package com.animania.compat.waila;

import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;
import com.animania.compat.waila.provider.WailaBlockInvisiblockProvider;
import com.animania.compat.waila.provider.WailaBlockNestProvider;
import com.animania.compat.waila.provider.WailaBlockSeedProvider;
import com.animania.compat.waila.provider.WailaBlockTroughProvider;

import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaCompat
{

	public static void registerWaila(IWailaRegistrar r)
	{
		// BLOCKS
		r.registerStackProvider(new WailaBlockSeedProvider(), BlockSeeds.class);
		r.registerBodyProvider(new WailaBlockTroughProvider(), BlockTrough.class);
		r.registerBodyProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerNBTProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerStackProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerBodyProvider(new WailaBlockNestProvider(), BlockNest.class);
	}

}

package com.animania.addons.extra.common.handler;

import java.util.List;
import java.util.Random;

import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;
import com.animania.addons.extra.common.entity.peafowl.EntityPeachickBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeacockBase;
import com.animania.addons.extra.common.entity.peafowl.PeacockType;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExtraAddonInjectionHandler
{
	public static String ID = "extra";

	public static void preInit()
	{
		AddonInjectionHandler.addInjection(ID, "nestHatchPeafowl", args -> {

			TileEntityNest te = (TileEntityNest) args[0];
			World worldIn = (World) args[1];
			BlockPos pos = (BlockPos) args[2];
			IBlockState state = (IBlockState) args[3];
			Random rand = (Random) args[4];

			if (te.getBirdType() instanceof PeacockType)
			{
				List<EntityPeacockBase> peacocks = AnimaniaHelper.getEntitiesInRange(EntityPeacockBase.class, 3, worldIn, pos);
				List<EntityAnimaniaPeacock> nearbyPeacocks = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPeacock.class, 15, worldIn, pos);
				if (nearbyPeacocks.size() < AnimaniaConfig.careAndFeeding.entityBreedingLimit)
				{
					PeacockType birdType = (PeacockType) te.getBirdType();
					for (EntityPeacockBase peacock : peacocks)
					{
						if (rand.nextInt(AnimaniaConfig.careAndFeeding.eggHatchChance) < 1)
						{
							PeacockType chickType = PeacockType.breed(peacock.type, birdType);
							EntityPeachickBase chick = chickType.getChild(worldIn);
							chick.setPosition(pos.getX() + .5, pos.getY() + .2, pos.getZ() + .5);
						AnimaniaHelper.spawnEntity(	worldIn, chick);
							chick.playSound(ExtraAddonSoundHandler.peacock1, 0.50F, 1.4F);
							te.removeItem();
							te.markDirty();
							break;
						}
					}
				}
			}

			return null;
		});
	}
}

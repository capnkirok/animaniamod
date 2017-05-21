package com.animania.compat.waila;

import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.compat.waila.provider.WailaBlockInvisiblockProvider;
import com.animania.compat.waila.provider.WailaBlockSeedProvider;
import com.animania.compat.waila.provider.WailaBlockTroughProvider;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderBase;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderChild;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderMateable;
import com.animania.compat.waila.provider.WailaEntityCowProvider;
import com.animania.compat.waila.provider.WailaEntityPigProvider;
import com.animania.compat.waila.provider.WailaEntityPigletProvider;
import com.animania.compat.waila.provider.WailaEntityRodentProvider;

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

		// COWS
		regEntityInfoBull(r, EntityBullAngus.class);
		regEntityInfoBull(r, EntityBullFriesian.class);
		regEntityInfoBull(r, EntityBullHereford.class);
		regEntityInfoBull(r, EntityBullHolstein.class);
		regEntityInfoBull(r, EntityBullLonghorn.class);
		regEntityInfoCow(r, EntityCowAngus.class);
		regEntityInfoCow(r, EntityCowFriesian.class);
		regEntityInfoCow(r, EntityCowHereford.class);
		regEntityInfoCow(r, EntityCowHolstein.class);
		regEntityInfoCow(r, EntityCowLonghorn.class);
		regEntityInfoCalf(r, EntityCalfAngus.class);
		regEntityInfoCalf(r, EntityCalfFriesian.class);
		regEntityInfoCalf(r, EntityCalfHereford.class);
		regEntityInfoCalf(r, EntityCalfHolstein.class);
		regEntityInfoCalf(r, EntityCalfLonghorn.class);

		// PIGS
		regEntityInfoPig(r, EntityHogDuroc.class);
		regEntityInfoPig(r, EntityHogHampshire.class);
		regEntityInfoPig(r, EntityHogLargeBlack.class);
		regEntityInfoPig(r, EntityHogLargeWhite.class);
		regEntityInfoPig(r, EntityHogOldSpot.class);
		regEntityInfoPig(r, EntityHogYorkshire.class);
		regEntityInfoPig(r, EntitySowDuroc.class);
		regEntityInfoPig(r, EntitySowHampshire.class);
		regEntityInfoPig(r, EntitySowLargeBlack.class);
		regEntityInfoPig(r, EntitySowLargeWhite.class);
		regEntityInfoPig(r, EntitySowOldSpot.class);
		regEntityInfoPig(r, EntitySowYorkshire.class);
		regEntityInfoPiglet(r, EntityPigletDuroc.class);
		regEntityInfoPiglet(r, EntityPigletHampshire.class);
		regEntityInfoPiglet(r, EntityPigletLargeBlack.class);
		regEntityInfoPiglet(r, EntityPigletLargeWhite.class);
		regEntityInfoPiglet(r, EntityPigletOldSpot.class);
		regEntityInfoPiglet(r, EntityPigletYorkshire.class);
		
		//CHICKENS
		regEntityInfoBase(r, EntityRoosterLeghorn.class);
		regEntityInfoBase(r, EntityRoosterOrpington.class);
		regEntityInfoBase(r, EntityRoosterPlymouthRock.class);
		regEntityInfoBase(r, EntityRoosterRhodeIslandRed.class);
		regEntityInfoBase(r, EntityRoosterWyandotte.class);
		regEntityInfoBase(r, EntityChickLeghorn.class);
		regEntityInfoBase(r, EntityChickOrpington.class);
		regEntityInfoBase(r, EntityChickPlymouthRock.class);
		regEntityInfoBase(r, EntityChickRhodeIslandRed.class);
		regEntityInfoBase(r, EntityChickWyandotte.class);
		regEntityInfoHen(r, EntityHenLeghorn.class);
		regEntityInfoHen(r, EntityHenOrpington.class);
		regEntityInfoHen(r, EntityHenPlymouthRock.class);
		regEntityInfoHen(r, EntityHenRhodeIslandRed.class);
		regEntityInfoHen(r, EntityHenWyandotte.class);
		
		//PEACOCKS
		regEntityInfoBase(r, EntityPeacockBlue.class);
		regEntityInfoBase(r, EntityPeacockWhite.class);
		regEntityInfoBase(r, EntityPeafowlBlue.class);
		regEntityInfoBase(r, EntityPeafowlWhite.class);
		regEntityInfoBase(r, EntityPeachickBlue.class);
		regEntityInfoBase(r, EntityPeachickWhite.class);
		
		//RODENTS
		regEntityInfoRodent(r, EntityFerretGrey.class);
		regEntityInfoRodent(r, EntityFerretWhite.class);
		regEntityInfoRodent(r, EntityHamster.class);
		regEntityInfoRodent(r, EntityHedgehog.class);
		regEntityInfoRodent(r, EntityHedgehogAlbino.class);

	}

	private static void regEntityInfoBull(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityAnimalProviderMateable(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderMateable(), clazz);
	}

	private static void regEntityInfoCow(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityCowProvider(), clazz);
		r.registerNBTProvider(new WailaEntityCowProvider(), clazz);
	}

	private static void regEntityInfoCalf(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityAnimalProviderChild(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderChild(), clazz);
	}

	private static void regEntityInfoPig(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityPigProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPigProvider(), clazz);
	}

	private static void regEntityInfoPiglet(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityPigletProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPigletProvider(), clazz);
	}
	
	private static void regEntityInfoBase(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityAnimalProviderBase(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderBase(), clazz);
	}
	
	private static void regEntityInfoHen(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityAnimalProviderBase(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderBase(), clazz);
		//r.registerBodyProvider(new WailaEntityHenProvider(), clazz);
		//r.registerNBTProvider(new WailaEntityHenProvider(), clazz);
	}
	
	private static void regEntityInfoRodent(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityRodentProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRodentProvider(), clazz);
	}
	
	
}

package com.animania.compat.waila;

import com.animania.common.blocks.BlockHamsterWheel;
import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.entities.chickens.EntityChickBase;
import com.animania.common.entities.chickens.EntityHenBase;
import com.animania.common.entities.chickens.EntityRoosterBase;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityCalfBase;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.compat.waila.provider.WailaBlockHamsterWheelProvider;
import com.animania.compat.waila.provider.WailaBlockInvisiblockProvider;
import com.animania.compat.waila.provider.WailaBlockSeedProvider;
import com.animania.compat.waila.provider.WailaBlockTroughProvider;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderBase;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderChild;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderMateable;
import com.animania.compat.waila.provider.WailaEntityCowProvider;
import com.animania.compat.waila.provider.WailaEntityHorseProvider;
import com.animania.compat.waila.provider.WailaEntityPigProvider;
import com.animania.compat.waila.provider.WailaEntityPigletProvider;
import com.animania.compat.waila.provider.WailaEntityRodentProvider;

import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaCompat
{

	public static void registerWaila(IWailaRegistrar r) {
		// BLOCKS
		r.registerStackProvider(new WailaBlockSeedProvider(), BlockSeeds.class);
		r.registerBodyProvider(new WailaBlockTroughProvider(), BlockTrough.class);
		r.registerBodyProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerNBTProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerStackProvider(new WailaBlockInvisiblockProvider(), BlockInvisiblock.class);
		r.registerBodyProvider(new WailaBlockHamsterWheelProvider(), BlockHamsterWheel.class);
		r.registerNBTProvider(new WailaBlockHamsterWheelProvider(), BlockHamsterWheel.class);
		
		// COWS
		regEntityInfoBull(r, EntityBullBase.class);
		
		regEntityInfoCow(r, EntityCowBase.class);
	
		regEntityInfoCalf(r, EntityCalfBase.class);

		// PIGS
		regEntityInfoPig(r, EntityHogBase.class);
		
		regEntityInfoPig(r, EntitySowBase.class);
		
		regEntityInfoPiglet(r, EntityPigletBase.class);
		

		// CHICKENS
		regEntityInfoBase(r, EntityRoosterBase.class);
		
		regEntityInfoBase(r, EntityChickBase.class);
		
		regEntityInfoHen(r, EntityHenBase.class);
		
		// PEACOCKS
		regEntityInfoBase(r, EntityAnimaniaPeacock.class);

		// RODENTS
		regEntityInfoRodent(r, EntityFerretBase.class);
		
		regEntityInfoRodent(r, EntityHamster.class);
		
		regEntityInfoRodent(r, EntityHedgehogBase.class);

		// HORSES
		regEntityInfoHorse(r, EntityAnimaniaHorse.class);

		
	}

	private static void regEntityInfoBull(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderMateable(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderMateable(), clazz);
	}

	private static void regEntityInfoCow(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityCowProvider(), clazz);
		r.registerNBTProvider(new WailaEntityCowProvider(), clazz);
	}

	private static void regEntityInfoCalf(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderChild(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderChild(), clazz);
	}

	private static void regEntityInfoPig(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityPigProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPigProvider(), clazz);
	}

	private static void regEntityInfoPiglet(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityPigletProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPigletProvider(), clazz);
	}

	private static void regEntityInfoBase(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderBase(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderBase(), clazz);
	}

	private static void regEntityInfoHen(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderBase(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderBase(), clazz);
		// r.registerBodyProvider(new WailaEntityHenProvider(), clazz);
		// r.registerNBTProvider(new WailaEntityHenProvider(), clazz);
	}
	
	private static void regEntityInfoRodent(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityRodentProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRodentProvider(), clazz);
	}

	private static void regEntityInfoHorse(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityHorseProvider(), clazz);
		r.registerNBTProvider(new WailaEntityHorseProvider(), clazz);
	}
	
}

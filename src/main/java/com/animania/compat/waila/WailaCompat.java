package com.animania.compat.waila;

import com.animania.common.blocks.BlockCheese;
import com.animania.common.blocks.BlockCheeseMold;
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
import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.goats.EntityDoeBase;
import com.animania.common.entities.goats.EntityKidBase;
import com.animania.common.entities.horses.EntityFoalBase;
import com.animania.common.entities.horses.EntityMareBase;
import com.animania.common.entities.horses.EntityStallionBase;
import com.animania.common.entities.peacocks.EntityPeachickBase;
import com.animania.common.entities.peacocks.EntityPeacockBase;
import com.animania.common.entities.peacocks.EntityPeafowlBase;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.sheep.EntityEweBase;
import com.animania.common.entities.sheep.EntityLambBase;
import com.animania.common.entities.sheep.EntityRamBase;
import com.animania.compat.waila.provider.WailaBlockCheeseMoldProvider;
import com.animania.compat.waila.provider.WailaBlockCheeseProvider;
import com.animania.compat.waila.provider.WailaBlockHamsterWheelProvider;
import com.animania.compat.waila.provider.WailaBlockInvisiblockProvider;
import com.animania.compat.waila.provider.WailaBlockSeedProvider;
import com.animania.compat.waila.provider.WailaBlockTroughProvider;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderBase;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderChild;
import com.animania.compat.waila.provider.WailaEntityAnimalProviderMateable;
import com.animania.compat.waila.provider.WailaEntityBuckProvider;
import com.animania.compat.waila.provider.WailaEntityCowProvider;
import com.animania.compat.waila.provider.WailaEntityDoeProvider;
import com.animania.compat.waila.provider.WailaEntityEweProvider;
import com.animania.compat.waila.provider.WailaEntityHenProvider;
import com.animania.compat.waila.provider.WailaEntityMareProvider;
import com.animania.compat.waila.provider.WailaEntityPeafowlProvider;
import com.animania.compat.waila.provider.WailaEntityPigProvider;
import com.animania.compat.waila.provider.WailaEntityPigletProvider;
import com.animania.compat.waila.provider.WailaEntityRamProvider;
import com.animania.compat.waila.provider.WailaEntityRodentProvider;
import com.animania.compat.waila.provider.WailaEntitySowProvider;

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
		r.registerStackProvider(new WailaBlockCheeseProvider(), BlockCheese.class);
		r.registerBodyProvider(new WailaBlockCheeseMoldProvider(), BlockCheeseMold.class);


		// COWS
		regEntityInfoBull(r, EntityBullBase.class);
		regEntityInfoCow(r, EntityCowBase.class);
		regEntityInfoCalf(r, EntityCalfBase.class);

		// PIGS
		regEntityInfoHog(r, EntityHogBase.class);
		regEntityInfoSow(r, EntitySowBase.class);
		regEntityInfoPiglet(r, EntityPigletBase.class);


		// CHICKENS
		regEntityInfoBase(r, EntityRoosterBase.class);
		regEntityInfoBase(r, EntityChickBase.class);
		regEntityInfoHen(r, EntityHenBase.class);

		// GOATS
		regEntityInfoBuck(r, EntityBuckBase.class);
		regEntityInfoDoe(r, EntityDoeBase.class);
		regEntityInfoKid(r, EntityKidBase.class);

		// SHEEP
		regEntityInfoRam(r, EntityRamBase.class);
		regEntityInfoEwe(r, EntityEweBase.class);
		regEntityInfoLamb(r, EntityLambBase.class);

		// PEACOCKS
		regEntityInfoBase(r, EntityPeacockBase.class);
		regEntityInfoPeafowl(r, EntityPeafowlBase.class);
		regEntityInfoBase(r, EntityPeachickBase.class);

		// RODENTS
		regEntityInfoRodent(r, EntityFerretBase.class);
		regEntityInfoRodent(r, EntityHamster.class);
		regEntityInfoRodent(r, EntityHedgehogBase.class);

		// HORSES
		regEntityInfoStallion(r, EntityStallionBase.class);
		regEntityInfoMare(r, EntityMareBase.class);
		regEntityInfoFoal(r, EntityFoalBase.class);


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

	private static void regEntityInfoHog(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityPigProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPigProvider(), clazz);
	}
	
	private static void regEntityInfoSow(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntitySowProvider(), clazz);
		r.registerNBTProvider(new WailaEntitySowProvider(), clazz);
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
		 r.registerBodyProvider(new WailaEntityHenProvider(), clazz);
		 r.registerNBTProvider(new WailaEntityHenProvider(), clazz);
	}
	
	private static void regEntityInfoPeafowl(IWailaRegistrar r, Class clazz) {
		 r.registerBodyProvider(new WailaEntityPeafowlProvider(), clazz);
		 r.registerNBTProvider(new WailaEntityPeafowlProvider(), clazz);
	}

	private static void regEntityInfoRodent(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityRodentProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRodentProvider(), clazz);
	}

	private static void regEntityInfoStallion(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderMateable(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderMateable(), clazz);
	}

	private static void regEntityInfoMare(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityMareProvider(), clazz);
		r.registerNBTProvider(new WailaEntityMareProvider(), clazz);
	}
	
	private static void regEntityInfoFoal(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderChild(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderChild(), clazz);
	}
	
	private static void regEntityInfoBuck(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityBuckProvider(), clazz);
		r.registerNBTProvider(new WailaEntityBuckProvider(), clazz);
	}

	private static void regEntityInfoDoe(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityDoeProvider(), clazz);
		r.registerNBTProvider(new WailaEntityDoeProvider(), clazz);
	}

	private static void regEntityInfoKid(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderChild(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderChild(), clazz);
	}

	private static void regEntityInfoRam(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityRamProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRamProvider(), clazz);
	}

	private static void regEntityInfoEwe(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityEweProvider(), clazz);
		r.registerNBTProvider(new WailaEntityEweProvider(), clazz);
	}

	private static void regEntityInfoLamb(IWailaRegistrar r, Class clazz) {
		r.registerBodyProvider(new WailaEntityAnimalProviderChild(), clazz);
		r.registerNBTProvider(new WailaEntityAnimalProviderChild(), clazz);
	}

	
}

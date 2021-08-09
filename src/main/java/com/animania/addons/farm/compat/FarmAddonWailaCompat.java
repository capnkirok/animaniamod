package com.animania.addons.farm.compat;

import com.animania.addons.farm.common.block.BlockCheese;
import com.animania.addons.farm.common.block.BlockCheeseMold;
import com.animania.addons.farm.common.block.BlockHive;
import com.animania.addons.farm.common.entity.chickens.EntityChickBase;
import com.animania.addons.farm.common.entity.chickens.EntityHenBase;
import com.animania.addons.farm.common.entity.chickens.EntityRoosterBase;
import com.animania.addons.farm.common.entity.cows.CowEntityBase;
import com.animania.addons.farm.common.entity.cows.EntityBullBase;
import com.animania.addons.farm.common.entity.cows.EntityCalfBase;
import com.animania.addons.farm.common.entity.goats.EntityBuckBase;
import com.animania.addons.farm.common.entity.goats.EntityDoeBase;
import com.animania.addons.farm.common.entity.goats.EntityKidBase;
import com.animania.addons.farm.common.entity.horses.EntityFoalBase;
import com.animania.addons.farm.common.entity.horses.EntityMareBase;
import com.animania.addons.farm.common.entity.horses.EntityStallionBase;
import com.animania.addons.farm.common.entity.pigs.EntityHogBase;
import com.animania.addons.farm.common.entity.pigs.EntitySowBase;
import com.animania.addons.farm.common.entity.pigs.PigEntityletBase;
import com.animania.addons.farm.common.entity.sheep.EntityEweBase;
import com.animania.addons.farm.common.entity.sheep.EntityLambBase;
import com.animania.addons.farm.common.entity.sheep.EntityRamBase;
import com.animania.addons.farm.compat.waila.WailaBlockCheeseMoldProvider;
import com.animania.addons.farm.compat.waila.WailaBlockCheeseProvider;
import com.animania.addons.farm.compat.waila.WailaBlockHiveProvider;
import com.animania.addons.farm.compat.waila.WailaCowEntityProvider;
import com.animania.addons.farm.compat.waila.WailaEntityBuckProvider;
import com.animania.addons.farm.compat.waila.WailaEntityDoeProvider;
import com.animania.addons.farm.compat.waila.WailaEntityEweProvider;
import com.animania.addons.farm.compat.waila.WailaEntityHenProvider;
import com.animania.addons.farm.compat.waila.WailaEntityMareProvider;
import com.animania.addons.farm.compat.waila.WailaEntityRamProvider;
import com.animania.addons.farm.compat.waila.WailaEntitySowProvider;
import com.animania.addons.farm.compat.waila.WailaPigEntityProvider;
import com.animania.addons.farm.compat.waila.WailaPigEntityletProvider;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderChild;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaRegistrar;

public class FarmAddonWailaCompat
{

	public static void registerWaila(IWailaRegistrar r)
	{
		r.registerStackProvider(new WailaBlockCheeseProvider(), BlockCheese.class);
		r.registerBodyProvider(new WailaBlockCheeseMoldProvider(), BlockCheeseMold.class);
		r.registerBodyProvider(new WailaBlockHiveProvider(), BlockHive.class);

		// COWS
		regEntityInfoBull(r, EntityBullBase.class);
		regEntityInfoCow(r, CowEntityBase.class);
		regEntityInfoCalf(r, EntityCalfBase.class);

		// PIGS
		regEntityInfoHog(r, EntityHogBase.class);
		regEntityInfoSow(r, EntitySowBase.class);
		regEntityInfoPiglet(r, PigEntityletBase.class);

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

		// HORSES
		regEntityInfoStallion(r, EntityStallionBase.class);
		regEntityInfoMare(r, EntityMareBase.class);
		regEntityInfoFoal(r, EntityFoalBase.class);

	}

	private static void regEntityInfoBull(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderMateable(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderMateable(), clazz);
	}

	private static void regEntityInfoCow(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaCowEntityProvider(), clazz);
		r.registerNBTProvider(new WailaCowEntityProvider(), clazz);
	}

	private static void regEntityInfoCalf(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderChild(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderChild(), clazz);
	}

	private static void regEntityInfoHog(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaPigEntityProvider(), clazz);
		r.registerNBTProvider(new WailaPigEntityProvider(), clazz);
	}

	private static void regEntityInfoSow(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntitySowProvider(), clazz);
		r.registerNBTProvider(new WailaEntitySowProvider(), clazz);
	}

	private static void regEntityInfoPiglet(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaPigEntityletProvider(), clazz);
		r.registerNBTProvider(new WailaPigEntityletProvider(), clazz);
	}

	private static void regEntityInfoHen(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityHenProvider(), clazz);
		r.registerNBTProvider(new WailaEntityHenProvider(), clazz);
	}

	private static void regEntityInfoStallion(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderMateable(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderMateable(), clazz);
	}

	private static void regEntityInfoMare(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityMareProvider(), clazz);
		r.registerNBTProvider(new WailaEntityMareProvider(), clazz);
	}

	private static void regEntityInfoFoal(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderChild(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderChild(), clazz);
	}

	private static void regEntityInfoBuck(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityBuckProvider(), clazz);
		r.registerNBTProvider(new WailaEntityBuckProvider(), clazz);
	}

	private static void regEntityInfoDoe(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityDoeProvider(), clazz);
		r.registerNBTProvider(new WailaEntityDoeProvider(), clazz);
	}

	private static void regEntityInfoKid(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderChild(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderChild(), clazz);
	}

	private static void regEntityInfoRam(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityRamProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRamProvider(), clazz);
	}

	private static void regEntityInfoEwe(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityEweProvider(), clazz);
		r.registerNBTProvider(new WailaEntityEweProvider(), clazz);
	}

	private static void regEntityInfoLamb(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderChild(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderChild(), clazz);
	}

	private static void regEntityInfoBase(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderBase(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderBase(), clazz);
	}

}

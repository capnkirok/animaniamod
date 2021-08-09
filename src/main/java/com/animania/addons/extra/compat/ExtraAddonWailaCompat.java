package com.animania.addons.extra.compat;

import com.animania.addons.extra.common.block.BlockHamsterWheel;
import com.animania.addons.extra.common.entity.peafowl.EntityPeachickBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeacockBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitEntityBuckBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitEntityDoeBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitEntityKitBase;
import com.animania.addons.extra.compat.waila.WailaBlockHamsterWheelProvider;
import com.animania.addons.extra.compat.waila.WailaEntityPeafowlProvider;
import com.animania.addons.extra.compat.waila.WailaEntityRodentProvider;
import com.animania.addons.extra.compat.waila.WailaRabbitEntityDoeProvider;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderChild;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;

import mcp.mobius.waila.api.IWailaRegistrar;

public class ExtraAddonWailaCompat
{
	public static void registerWaila(IWailaRegistrar r)
	{
		r.registerBodyProvider(new WailaBlockHamsterWheelProvider(), BlockHamsterWheel.class);
		r.registerNBTProvider(new WailaBlockHamsterWheelProvider(), BlockHamsterWheel.class);

		// PEACOCKS
		regEntityInfoBase(r, EntityPeacockBase.class);
		regEntityInfoPeafowl(r, EntityPeafowlBase.class);
		regEntityInfoBase(r, EntityPeachickBase.class);

		// RODENTS
		regEntityInfoRodent(r, EntityFerretBase.class);
		regEntityInfoRodent(r, EntityHamster.class);
		regEntityInfoRodent(r, EntityHedgehogBase.class);

		// RABBITS
		regEntityInfoRabbitBuck(r, RabbitEntityBuckBase.class);
		regEntityInfoRabbitDoe(r, RabbitEntityDoeBase.class);
		regEntityInfoRabbitKit(r, RabbitEntityKitBase.class);
	}

	private static void regEntityInfoBase(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderBase(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderBase(), clazz);
	}

	private static void regEntityInfoPeafowl(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityPeafowlProvider(), clazz);
		r.registerNBTProvider(new WailaEntityPeafowlProvider(), clazz);
	}

	private static void regEntityInfoRodent(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaEntityRodentProvider(), clazz);
		r.registerNBTProvider(new WailaEntityRodentProvider(), clazz);
	}

	private static void regEntityInfoRabbitBuck(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderMateable(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderMateable(), clazz);
	}

	private static void regEntityInfoRabbitDoe(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaRabbitEntityDoeProvider(), clazz);
		r.registerNBTProvider(new WailaRabbitEntityDoeProvider(), clazz);
	}

	private static void regEntityInfoRabbitKit(IWailaRegistrar r, Class clazz)
	{
		r.registerBodyProvider(new WailaAnimalEntityProviderChild(), clazz);
		r.registerNBTProvider(new WailaAnimalEntityProviderChild(), clazz);
	}

}

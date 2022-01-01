package compat;

import com.animania.compat.waila.provider.WailaAnimalEntityProviderBase;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderChild;
import com.animania.compat.waila.provider.WailaAnimalEntityProviderMateable;
import common.block.BlockHamsterWheel;
import common.entity.peafowl.EntityPeachickBase;
import common.entity.peafowl.EntityPeacockBase;
import common.entity.peafowl.EntityPeafowlBase;
import common.entity.rodents.EntityFerretBase;
import common.entity.rodents.EntityHamster;
import common.entity.rodents.EntityHedgehogBase;
import common.entity.rodents.rabbits.RabbitEntityBuckBase;
import common.entity.rodents.rabbits.RabbitEntityDoeBase;
import common.entity.rodents.rabbits.RabbitEntityKitBase;
import compat.waila.WailaBlockHamsterWheelProvider;
import compat.waila.WailaEntityPeafowlProvider;
import compat.waila.WailaEntityRodentProvider;
import compat.waila.WailaRabbitEntityDoeProvider;
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

package com.animania.addons.extra.common.handler;

import com.animania.addons.extra.common.capabilities.CapabilitiesPlayerStorage;
import com.animania.addons.extra.common.capabilities.CapabilityPlayer;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ExtraAddonCapHandler
{
	public static void syncCap(Entity entity, ICapabilityPlayer other)
	{
		ICapabilityPlayer cap = entity.getCapability(CapabilityRefs.CAPS, null);

		cap.setAnimal(other.getAnimal());
		cap.setCarrying(other.isCarrying());
		cap.setType(other.getType());
	}

	public static void preInit()
	{
		CapabilityManager.INSTANCE.register(ICapabilityPlayer.class, new CapabilitiesPlayerStorage(), CapabilityPlayer.class);
	}
}

package common.handler;

import common.capabilities.CapabilitiesPlayerStorage;
import common.capabilities.CapabilityPlayer;
import common.capabilities.CapabilityRefs;
import common.capabilities.ICapabilityPlayer;
import net.minecraft.world.entity.Entity;
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

package common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityPlayerHandler
{

	public static CompoundTag writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance)
	{
		CompoundTag tags = new CompoundTag();
		tags.put("animal", instance.getAnimal());
		tags.putBoolean("carrying", instance.isCarrying());
		tags.putString("type", instance.getType());
		return tags;
	}

	public static void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, CompoundTag nbt)
	{

		if (nbt.contains("animal"))
		{
			instance.setAnimal(nbt.getCompound("animal"));
		}

		if (nbt.contains("carrying"))
		{
			instance.setCarrying(nbt.getBoolean("carrying"));
		}

		if (nbt.contains("type"))
		{
			instance.setType(nbt.getString("type"));
		}
	}

	public static void onUpdate(Player player)
	{
		if (!(player instanceof ServerPlayer))
			return;
		final LazyOptional<ICapabilityPlayer> props = CapabilityRefs.getPlayerCaps(player);

		props.ifPresent(ICapabilityPlayer::update);

	}

	public static void processNBTData(Player player, CompoundTag nbt)
	{
		LazyOptional<ICapabilityPlayer> instance = CapabilityRefs.getPlayerCaps(player);
		if (instance == null)
			return;

		if (nbt.contains("animal"))
		{
			instance.ifPresent(caps -> caps.setAnimal(nbt.getCompound("animal")));
		}
		if (nbt.contains("carrying"))
		{
			instance.ifPresent(caps -> caps.setCarrying(nbt.getBoolean("carrying")));
		}

		if (nbt.contains("type"))
		{
			instance.ifPresent(caps -> caps.setType(nbt.getString("type")));
		}

	}
}
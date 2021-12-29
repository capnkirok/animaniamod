package com.animania.addons.extra.common.capabilities;

import NBTBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityPlayerHandler
{

	public static NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance)
	{
		CompoundTag tags = new CompoundTag();
		tags.put("animal", instance.getAnimal());
		tags.putBoolean("carrying", instance.isCarrying());
		tags.setString("type", instance.getType());
		return tags;
	}

	public static void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, CompoundTag nbt)
	{

		if (nbt.contains("animal"))
		{
			instance.setAnimal(nbt.getCompoundTag("animal"));
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
		final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		props.update();

	}

	public static void processNBTData(Player player, CompoundTag nbt)
	{
		ICapabilityPlayer instance = CapabilityRefs.getPlayerCaps(player);
		if (instance == null)
			return;

		if (nbt.contains("animal"))
		{
			instance.setAnimal(nbt.getCompoundTag("animal"));
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
}
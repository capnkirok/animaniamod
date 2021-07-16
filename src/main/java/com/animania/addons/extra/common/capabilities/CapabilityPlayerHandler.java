package com.animania.addons.extra.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;

import NBTBase;

public class CapabilityPlayerHandler {

	public static NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance) {
		CompoundNBT tags = new CompoundNBT();
		tags.put("animal", instance.getAnimal());
		tags.putBoolean("carrying", instance.isCarrying());
		tags.putString("type", instance.getType());
		return tags;
	}

	public static void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, CompoundNBT nbt) {

		if (nbt.hasKey("animal")) {
			instance.setAnimal(nbt.getCompoundTag("animal"));
		}
		
		if (nbt.hasKey("carrying")) {
			instance.setCarrying(nbt.getBoolean("carrying"));
		}
		
		if (nbt.hasKey("type")) {
			instance.setType(nbt.getString("type"));
		}
	}

	public static void onUpdate(EntityPlayer player) {
		if (!(player instanceof EntityPlayerMP))
			return;
		final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		props.update();

	}

	public static void processNBTData(EntityPlayer player, CompoundNBT nbt) {
		ICapabilityPlayer instance = CapabilityRefs.getPlayerCaps(player);
		if (instance == null)
			return;

		if (nbt.hasKey("animal")) {
			instance.setAnimal(nbt.getCompoundTag("animal"));
		}
		if (nbt.hasKey("carrying")) {
			instance.setCarrying(nbt.getBoolean("carrying"));
		}

		if (nbt.hasKey("type")) {
			instance.setType(nbt.getString("type"));
		}
		
	}
}
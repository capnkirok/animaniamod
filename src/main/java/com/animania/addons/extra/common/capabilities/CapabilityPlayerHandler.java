package com.animania.addons.extra.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityPlayerHandler {

	public static NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance) {
		CompoundNBT tags = new CompoundNBT();
		tags.putTag("animal", instance.getAnimal());
		tags.putBoolean("carrying", instance.isCarrying());
		tags.setString("type", instance.getType());
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

	public static void onUpdate(PlayerEntity player) {
		if (!(player instanceof ServerPlayerEntity))
			return;
		final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		props.update();

	}

	public static void processNBTData(PlayerEntity player, CompoundNBT nbt) {
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
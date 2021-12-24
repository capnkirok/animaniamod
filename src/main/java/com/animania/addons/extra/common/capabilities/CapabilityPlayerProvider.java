package com.animania.addons.extra.common.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPlayerProvider implements ICapabilitySerializable<CompoundTag> {
	public ICapabilityPlayer instance = null;

	public CapabilityPlayerProvider() {
		instance = new CapabilityPlayer();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing) {
		return capability == CapabilityRefs.CAPS;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing) {
		if (CapabilityRefs.CAPS != null && CapabilityRefs.CAPS == capability)
			return (T) instance;
		return null;
	}

	@Override
	public CompoundTag serializeNBT() {
		return (CompoundTag) CapabilityRefs.CAPS.getStorage().writeNBT(CapabilityRefs.CAPS, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		CapabilityRefs.CAPS.getStorage().readNBT(CapabilityRefs.CAPS, instance, null, nbt);
	}

}
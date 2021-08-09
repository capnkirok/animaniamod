package com.animania.addons.extra.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPlayerProvider implements ICapabilitySerializable<CompoundNBT> {
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
	public CompoundNBT serializeNBT() {
		return (CompoundNBT) CapabilityRefs.CAPS.getStorage().writeNBT(CapabilityRefs.CAPS, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		CapabilityRefs.CAPS.getStorage().readNBT(CapabilityRefs.CAPS, instance, null, nbt);
	}

}
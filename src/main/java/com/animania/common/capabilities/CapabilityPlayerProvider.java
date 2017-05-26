package com.animania.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityPlayerProvider implements ICapabilitySerializable<NBTTagCompound> {
	public ICapabilityPlayer instance = null;

	public CapabilityPlayerProvider() {
		instance = new CapabilityPlayer();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityRefs.CAPS;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (CapabilityRefs.CAPS != null && CapabilityRefs.CAPS == capability)
			return (T) instance;
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CapabilityRefs.CAPS.getStorage().writeNBT(CapabilityRefs.CAPS, instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CapabilityRefs.CAPS.getStorage().readNBT(CapabilityRefs.CAPS, instance, null, nbt);
	}

}
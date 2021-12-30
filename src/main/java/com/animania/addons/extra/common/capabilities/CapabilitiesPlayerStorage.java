package com.animania.addons.extra.common.capabilities;

import java.util.concurrent.Callable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilitiesPlayerStorage implements IStorage<ICapabilityPlayer>
{

	@Override
	public CompoundTag writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side)
	{
		return CapabilityPlayerHandler.writeNBT(capability, instance);
	}

	@Override
	public void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side, CompoundTag nbt)
	{
		CapabilityPlayerHandler.readNBT(capability, instance, nbt);
	}

	public ICapabilityPlayer readNBT2(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side, CompoundTag nbt)
	{
		CapabilityPlayerHandler.readNBT(capability, instance, nbt);

		return instance;
	}

	public class Factory implements Callable<ICapabilityPlayer>
	{
		@Override
		public ICapabilityPlayer call() throws Exception
		{
			return new CapabilityPlayer();
		}

	}
}
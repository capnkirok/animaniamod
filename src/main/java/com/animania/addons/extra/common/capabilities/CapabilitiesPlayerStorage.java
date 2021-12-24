package com.animania.addons.extra.common.capabilities;

import java.util.concurrent.Callable;

import NBTBase;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilitiesPlayerStorage implements IStorage<ICapabilityPlayer> {

	  @Override
	  public NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side) {
	    return CapabilityPlayerHandler.writeNBT(capability, instance);
	  }

	  @Override
	  public void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side, NBTBase nbt) {
	    CapabilityPlayerHandler.readNBT(capability, instance, (CompoundNBT) nbt);
	  }
	  
	  
	  public ICapabilityPlayer readNBT2(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, Direction side, NBTBase nbt) {
	    CapabilityPlayerHandler.readNBT(capability, instance, (CompoundNBT) nbt);
	    
	    return instance;
	  }
	  
	  public class Factory implements Callable<ICapabilityPlayer> {
	    @Override
	    public ICapabilityPlayer call() throws Exception {
	      return new CapabilityPlayer();
	    }
	    
	  }
	}
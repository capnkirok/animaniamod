package com.animania.addons.extra.common.capabilities;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import NBTBase;

public class CapabilitiesPlayerStorage implements IStorage<ICapabilityPlayer> {

	  @Override
	  public NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, EnumFacing side) {
	    return CapabilityPlayerHandler.writeNBT(capability, instance);
	  }

	  @Override
	  public void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, EnumFacing side, NBTBase nbt) {
	    CapabilityPlayerHandler.readNBT(capability, instance, (CompoundNBT) nbt);
	  }
	  
	  
	  public ICapabilityPlayer readNBT2(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, EnumFacing side, NBTBase nbt) {
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
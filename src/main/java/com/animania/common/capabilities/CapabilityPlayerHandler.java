package com.animania.common.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityPlayerHandler {

	public static NBTBase writeNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance) {
		NBTTagCompound tags = new NBTTagCompound();
		tags.setBoolean("mounted", instance.getMounted());
		tags.setString("petType", instance.getPetType());
		tags.setInteger("petColor", instance.getPetColor());
		tags.setString("petName", instance.getPetName());
		
		return tags;
	}

	public static void readNBT(Capability<ICapabilityPlayer> capability, ICapabilityPlayer instance, NBTTagCompound nbt) {

		if (nbt.hasKey("mounted")) {
			instance.setMounted(nbt.getBoolean("mounted"));
		}
		
		if (nbt.hasKey("petType")) {
			instance.setPetType(nbt.getString("petType"));
		}
		
		if (nbt.hasKey("petColor")) {
			instance.setPetColor(nbt.getInteger("petColor"));
		}
		
		if (nbt.hasKey("petName")) {
			instance.setPetName(nbt.getString("petName"));
		}
		
		
	}

	public static void onUpdate(EntityPlayer player) {
		if (!(player instanceof EntityPlayerMP))
			return;
		final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		props.update();

	}

	public static void processNBTData(EntityPlayer player, NBTTagCompound nbt) {
		ICapabilityPlayer instance = CapabilityRefs.getPlayerCaps(player);
		if (instance == null)
			return;

		if (nbt.hasKey("mounted")) {
			instance.setMounted(nbt.getBoolean("mounted"));
		}

		
	}
}
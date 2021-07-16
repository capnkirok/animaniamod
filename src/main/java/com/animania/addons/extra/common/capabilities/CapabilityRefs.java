package com.animania.addons.extra.common.capabilities;

import com.animania.Animania;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityRefs {


	@CapabilityInject(ICapabilityPlayer.class)
	public static final Capability<ICapabilityPlayer> CAPS = null;

	public static ICapabilityPlayer getPlayerCaps(PlayerEntity player) {
		return player.getCapability(CapabilityRefs.CAPS, null);
	}

	public static NBTTagString toTagString(String s){
		return new NBTTagString(s);
	}
	public static NBTTagInt toTagInt(int i){
		return new NBTTagInt(i);
	}
	public static NBTTagLong toTagLong(long i){
		return new NBTTagLong(i);
	}
	public static NBTTagFloat toTagFloat(float f){
		return new NBTTagFloat(f);
	}
	
	public static NBTTagList toTagList(NBTTagList l){
		return new NBTTagList();
	}
	
	public static ResourceLocation toResource(String path) {
		return new ResourceLocation(Animania.MODID, path);
	}
}
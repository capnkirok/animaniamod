package com.animania.addons.extra.common.capabilities;

import com.animania.Animania;

import NBTTagString;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityRefs
{

	@CapabilityInject(ICapabilityPlayer.class)
	public static final Capability<ICapabilityPlayer> CAPS = null;

	public static ICapabilityPlayer getPlayerCaps(Player player)
	{
		return player.getCapability(CapabilityRefs.CAPS, null);
	}

	public static NBTTagString toTagString(String s)
	{
		return new NBTTagString(s);
	}

	public static NBTTagInt toTagInt(int i)
	{
		return new NBTTagInt(i);
	}

	public static NBTTagLong toTagLong(long i)
	{
		return new NBTTagLong(i);
	}

	public static NBTTagFloat toTagFloat(float f)
	{
		return new NBTTagFloat(f);
	}

	public static ListTag toTagList(ListTag l)
	{
		return new ListTag();
	}

	public static ResourceLocation toResource(String path)
	{
		return new ResourceLocation(Animania.MODID, path);
	}
}
package com.animania.common.helper;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class StringParser
{

	@Nullable
	public static Block getBlock(String string)
	{
		CompoundTag tag = getTagCompound(string);
		if (tag != null)
			string = string.replace(tag.toString(), "");

		if (string.contains("#"))
			string = string.replace(string.substring(string.indexOf("#")), "");

		Block block = Block.getBlockFromName(string);
		if (block == null)
			new InvalidConfigException("Block Parsing Error. Invalid Name: " + string).printException();

		return block;
	}

	public static int getMeta(String string)
	{
		CompoundTag tag = getTagCompound(string);
		if (tag != null)
			string = string.replace(tag.toString(), "");

		if (string.contains("#"))
		{
			int meta = 0;
			try
			{
				meta = Integer.parseInt(string.substring(string.indexOf("#")).replace("#", ""));
			}
			catch (Exception e)
			{
				new InvalidConfigException("Meta Parsing Error at: " + string + " : " + e.getMessage()).printException();
			}

			return meta;
		}
		return 0;
	}

	@Nullable
	public static BlockState getBlockState(String string)
	{
		CompoundTag tag = getTagCompound(string);
		if (tag != null)
			string = string.replace(tag.toString(), "");

		int meta = getMeta(string);
		if (meta == 0)
		{
			Block block = getBlock(string);
			if (block != null)
				return block.defaultBlockState();
		}
		try
		{
			return getBlock(string).getStateFromMeta(meta);
		}
		catch (Exception e)
		{
			new InvalidConfigException("Blockstate parsing Exception at: " + string + " : " + e.getMessage()).printException();
			return null;
		}
	}

	@Nullable
	public static Item getItem(String string)
	{
		CompoundTag tag = getTagCompound(string);
		if (tag != null)
			string = string.replace(tag.toString(), "");

		if (string.contains("#"))
			string = string.replace(string.substring(string.indexOf("#")), "");

		return Item.getByNameOrId(string);
	}

	public static ItemStack getItemStack(String string)
	{
		Item item = getItem(string);

		if (item == null)
			return ItemStack.EMPTY;

		ItemStack stack = new ItemStack(item, 1, getMeta(string));
		CompoundTag tag = getTagCompound(string);
		if (tag != null)
			stack.putTagCompound(tag);

		return stack;
	}

	@Nullable
	public static CompoundTag getTagCompound(String string)
	{
		CompoundTag tag = null;
		if (string.contains("{"))
		{
			if (!string.contains("}"))
				new InvalidConfigException("Missing } at  : " + string).printException();

			String nbt = string.substring(string.indexOf("{"));
			string = string.replace(nbt, "");
			try
			{
				tag = JsonToNBT.getTagFromJson(nbt);
			}
			catch (NBTException e)
			{
				new InvalidConfigException("Error while parsing NBT: " + e.getMessage()).printException();
				return null;
			}

		}
		else if (string.contains("}"))
			new InvalidConfigException("Missing { at  : " + string).printException();

		return tag;
	}

}

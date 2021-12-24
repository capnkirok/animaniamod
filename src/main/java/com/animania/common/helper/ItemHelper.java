package com.animania.common.helper;

import java.util.Arrays;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ItemHelper
{

	public static void spawnItem(Level world, BlockPos pos, Item item, int count, int meta)
	{
		spawnItem(world, pos, new ItemStack(item, count, meta));
	}
	
	public static void spawnItem(Level world, BlockPos pos, Item item, int count)
	{
		spawnItem(world, pos, new ItemStack(item, count, 0));
	}
	
	public static void spawnItem(Level world, BlockPos pos, Item item)
	{
		spawnItem(world, pos, new ItemStack(item, 1, 0));
	}
	
	public static void spawnItem(Level world,BlockPos pos, Block block, int count, int meta)
	{
		spawnItem(world, pos, new ItemStack(block, count, meta));
	}
	
	public static void spawnItem(Level world,BlockPos pos, Block block, int count)
	{
		spawnItem(world, pos, new ItemStack(block, count, 0));
	}
	
	public static void spawnItem(Level world,BlockPos pos, Block block)
	{
		spawnItem(world, pos, new ItemStack(block, 1, 0));
	}

	public static void spawnItem(Level world, BlockPos pos, ItemStack itemStack)
	{
		EntityItem item = new EntityItem(world);
		item.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
		item.setItem(itemStack);
		AnimaniaHelper.spawnEntity(world, item);
	}
	
	public static int getSlotForItem(Item item, PlayerEntity player)
	{

		final List<NonNullList<ItemStack>> allInventories = Arrays.<NonNullList<ItemStack>>asList(new NonNullList[] { player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory});

		for(int k = 0; k < allInventories.size(); k++)
		{
			NonNullList<ItemStack> list = allInventories.get(k);
			for (int i = 0; i < list.size(); ++i)
			{
				if (!((ItemStack)list.get(i)).isEmpty() && ((ItemStack)list.get(i)).getItem() == item)
				{
					if(list.size() == 4)
						return 36 + i;
					if(list.size() == 1)
						return 40;
					return i;

				}
			}
		}

		return -1;
	}
	
	
}

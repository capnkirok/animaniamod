package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.TileEntityNest;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;

public class WailaBlockNestProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileEntityNest tile = (TileEntityNest) accessor.getTileEntity();
		CompoundTag tag = new CompoundTag();
		tile.writeToNBT(tag);
		String from = tag.getString("birdType");

		ItemStack stack = tile.itemHandler.getStackInSlot(0);

		if (!stack.isEmpty())
			currenttip.add(stack.getCount() + " " + stack.getDisplayName());

		if (!from.isEmpty())
			currenttip.add("From: " + from.substring(0, 1).toUpperCase() + from.substring(1).toLowerCase());

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayerEntity player, TileEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

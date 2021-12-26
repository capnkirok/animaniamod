package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.BlockEntityNest;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

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
		BlockEntityNest tile = (BlockEntityNest) accessor.getBlockEntity();
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
	public CompoundTag getNBTData(ServerPlayer player, BlockEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

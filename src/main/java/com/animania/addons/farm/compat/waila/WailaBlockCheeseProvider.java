package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;

public class WailaBlockCheeseProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		Block block = accessor.getBlock();
		if (block == FarmAddonBlockHandler.blockCheeseFriesian)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseFriesian);
		if (block == FarmAddonBlockHandler.blockCheeseHolstein)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseHolstein);
		if (block == FarmAddonBlockHandler.blockCheeseSheep)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseSheep);
		if (block == FarmAddonBlockHandler.blockCheeseGoat)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseGoat);
		return ItemStack.EMPTY;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayerEntity player, TileEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		return null;
	}

}

package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WailaBlockCheeseProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		Block block = accessor.getBlock();
		if(block == FarmAddonBlockHandler.blockCheeseFriesian)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseFriesian);
		if(block == FarmAddonBlockHandler.blockCheeseHolstein)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseHolstein);
		if(block == FarmAddonBlockHandler.blockCheeseSheep)
			return new ItemStack(FarmAddonBlockHandler.blockCheeseSheep);
		if(block == FarmAddonBlockHandler.blockCheeseGoat)
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
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
	{
		return null;
	}

}

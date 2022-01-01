package compat.waila;

import common.handler.FarmAddonBlockHandler;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

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
	public CompoundTag getNBTData(ServerPlayer player, BlockEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		return null;
	}

}

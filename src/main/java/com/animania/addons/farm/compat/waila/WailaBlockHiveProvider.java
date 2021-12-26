package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.tileentity.TileEntityHive;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockHiveProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
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
		TileEntityHive tile = (TileEntityHive) accessor.getTileEntity();

		FluidStack fluid = tile.fluidHandler.getFluid();

		if (fluid != null)
			currenttip.add(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayer player, TileEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		return null;
	}

}

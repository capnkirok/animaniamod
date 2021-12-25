package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.TileEntityTrough;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockTroughProvider implements IWailaDataProvider
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
		TileEntityTrough tile = (TileEntityTrough) accessor.getTileEntity();

		ItemStack stack = tile.itemHandler.getStackInSlot(0);
		FluidStack fluid = tile.fluidHandler.getFluid();

		if (!stack.isEmpty())
			currenttip.add(stack.getCount() + " " + stack.getDisplayName());

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
	public CompoundTag getNBTData(ServerPlayerEntity player, TileEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		return null;
	}

}

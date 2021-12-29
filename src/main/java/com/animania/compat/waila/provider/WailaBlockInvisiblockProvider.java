package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.handler.BlockHandler;
import com.animania.common.blockentities.BlockEntityInvisiblock;
import com.animania.common.blockentities.BlockEntityTrough;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockInvisiblockProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return new ItemStack(BlockHandler.blockTrough);
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{

		String stack = accessor.getNBTData().getString("stack");
		String fluid = accessor.getNBTData().getString("fluid");

		if (!stack.isEmpty())
			currenttip.add(stack);

		if (fluid != null)
			currenttip.add(fluid);

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public CompoundTag getNBTData(ServerPlayer player, BlockEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		BlockEntityInvisiblock tile = (BlockEntityInvisiblock) te;
		BlockEntityTrough trough = tile.getTrough();

		try
		{
			ItemStack stack = trough.itemHandler.getStackInSlot(0);
			FluidStack fluid = trough.fluidHandler.getFluid();

			if (!stack.isEmpty())
				tag.setString("stack", stack.getCount() + " " + stack.getDisplayName());

			if (fluid != null)
				tag.setString("fluid", fluid.getLocalizedName() + ", " + fluid.amount + "mB");

			return tag;
		}
		catch (Exception e)
		{
			return new CompoundTag();
		}
	}

}

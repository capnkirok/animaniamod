package com.animania.addons.extra.compat.waila;

import java.util.List;

import com.animania.addons.extra.common.tileentity.TileEntityHamsterWheel;
import com.animania.addons.extra.config.ExtraConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.item.ItemStack;

public class WailaBlockHamsterWheelProvider implements IWailaDataProvider
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

		String stack = accessor.getNBTData().getString("stack");
		int energy = accessor.getNBTData().getInteger("energy");

		currenttip.add(energy + "/" + ExtraConfig.settings.hamsterWheelCapacity + " RF");

		if (!stack.isEmpty())
			currenttip.add(stack);

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
		TileEntityHamsterWheel tile = (TileEntityHamsterWheel) te;

		ItemStack stack = tile.getItemHandler().getStackInSlot(0);
		int energy = tile.getEnergy();

		if (!stack.isEmpty())
			tag.setString("stack", stack.getCount() + " " + stack.getDisplayName());

		tag.putInteger("energy", energy);
		return tag;
	}

}

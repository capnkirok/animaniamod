package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.TileEntityNest;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WailaBlockNestProvider  implements IWailaDataProvider
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
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntityNest tile = (TileEntityNest) accessor.getTileEntity();
        CompoundNBT tag = new CompoundNBT();
        tile.writeToNBT(tag);
        String from = tag.getString("birdType");
        
        ItemStack stack = tile.itemHandler.getStackInSlot(0);

        if (!stack.isEmpty())
            currenttip.add(stack.getCount() + " " + stack.getDisplayName());

        if(!from.isEmpty())
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
	public CompoundNBT getNBTData(ServerPlayerEntity player, TileEntity te, CompoundNBT tag, World world, BlockPos pos)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

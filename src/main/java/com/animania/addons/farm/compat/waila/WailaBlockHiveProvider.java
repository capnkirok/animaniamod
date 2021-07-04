package com.animania.addons.farm.compat.waila;

import java.util.List;

import com.animania.addons.farm.common.tileentity.TileEntityHive;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockHiveProvider implements IWailaDataProvider
{

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    	return ItemStack.EMPTY;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntityHive tile = (TileEntityHive) accessor.getTileEntity();

        FluidStack fluid = tile.fluidHandler.getFluid();

        if (fluid != null)
            currenttip.add(fluid.getLocalizedName() + ", " + fluid.amount + "mB");

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public CompoundNBT getNBTData(EntityPlayerMP player, TileEntity te, CompoundNBT tag, World world, BlockPos pos) {
        return null;
    }

}

package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.tileentities.TileEntityHamsterWheel;
import com.animania.config.AnimaniaConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WailaBlockHamsterWheelProvider implements IWailaDataProvider
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

        String stack = accessor.getNBTData().getString("stack");
        int energy = accessor.getNBTData().getInteger("energy");

        currenttip.add(energy + "/" +  AnimaniaConfig.gameRules.hamsterWheelCapacity + " RF");
        
        if (!stack.isEmpty())
            currenttip.add(stack);

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        TileEntityHamsterWheel tile = (TileEntityHamsterWheel) te;

        ItemStack stack = tile.getItemHandler().getStackInSlot(0);
        int energy = tile.getEnergy();
        
        if (!stack.isEmpty())
            tag.setString("stack", stack.getCount() + " " + stack.getDisplayName());
        
        tag.setInteger("energy", energy);	
        return tag;
    }

}

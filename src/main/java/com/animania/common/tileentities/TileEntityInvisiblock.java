package com.animania.common.tileentities;

import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityInvisiblock extends TileEntity implements ITickable
{

    private TileEntityTrough trough;

    public TileEntityInvisiblock() {

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        if (AnimaniaConfig.gameRules.allowTroughAutomation) {
            if (this.world.isRemote) {
                if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                    return true;
                if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                    return true;
            }

            if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.trough != null)
                return true;
            if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.trough != null)
                return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (AnimaniaConfig.gameRules.allowTroughAutomation) {

            if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.trough.fluidHandler.getFluid() == null)
                return (T) this.trough.itemHandler;

            if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.trough.itemHandler.getStackInSlot(0).isEmpty())
                return (T) this.trough.fluidHandler;

        }

        return super.getCapability(capability, facing);

    }

    private TileEntityTrough getPlacedTrough() {
        BlockPos pos1 = new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ());
        BlockPos pos2 = new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ());
        BlockPos pos3 = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1);
        BlockPos pos4 = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1);

        Block block1 = this.world.getBlockState(pos1).getBlock();
        Block block2 = this.world.getBlockState(pos2).getBlock();
        Block block3 = this.world.getBlockState(pos3).getBlock();
        Block block4 = this.world.getBlockState(pos4).getBlock();

        if (block1 == BlockHandler.blockTrough) {
            TileEntityTrough te = (TileEntityTrough) this.world.getTileEntity(pos1);
            if (te != null && te.getTroughRotation() == 1 && !this.world.isRemote)
                return te;
        }

        if (block2 == BlockHandler.blockTrough) {
            TileEntityTrough te = (TileEntityTrough) this.world.getTileEntity(pos2);
            if (te != null && te.getTroughRotation() == 0 && !this.world.isRemote)
                return te;
        }

        if (block3 == BlockHandler.blockTrough) {
            TileEntityTrough te = (TileEntityTrough) this.world.getTileEntity(pos3);
            if (te != null && te.getTroughRotation() == 2 && !this.world.isRemote)
                return te;

        }

        if (block4 == BlockHandler.blockTrough) {
            TileEntityTrough te = (TileEntityTrough) this.world.getTileEntity(pos4);
            if (te != null && te.getTroughRotation() == 3 && !this.world.isRemote)
                return te;

        }
        return null;
    }

    @Override
    public void update() {

        if (this.trough == null)
            this.trough = this.getPlacedTrough();

    }

    public TileEntityTrough getTrough() {
        return this.trough;
    }

}

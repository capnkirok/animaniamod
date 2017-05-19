package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityInvisiblock extends TileEntity implements ITickable {
	
	private TileEntityTrough trough;

	public TileEntityInvisiblock() {
	
	
	}
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		if(AnimaniaConfig.gameRules.allowTroughAutomation)
		{
			if(this.world.isRemote)
			{
				if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
					return true;
				if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
					return true;
			}
				
			if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && trough != null)
				return true;
			if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && trough != null)
				return true;
		}

		return super.hasCapability(capability, facing);
	}


	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(AnimaniaConfig.gameRules.allowTroughAutomation)
		{

			if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && trough.fluidHandler.getFluid() == null)		
				return (T) trough.itemHandler;

			if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && trough.itemHandler.getStackInSlot(0).isEmpty())
				return (T) trough.fluidHandler;

		}

		return super.getCapability(capability, facing);

	}
	
	
	private TileEntityTrough getPlacedTrough()
	{
		BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

		Block block1 = world.getBlockState(pos1).getBlock();
		Block block2 = world.getBlockState(pos2).getBlock();
		Block block3 = world.getBlockState(pos3).getBlock();
		Block block4 = world.getBlockState(pos4).getBlock();

		if (block1 == BlockHandler.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) world.getTileEntity(pos1);
			if (te != null && te.getTroughRotation() == 1 && !world.isRemote) {
				
				return te;

			}
		}

		if (block2 == BlockHandler.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) world.getTileEntity(pos2);
			if (te != null && te.getTroughRotation() == 0 && !world.isRemote) {
				
				return te;
			}
		}

		if (block3 == BlockHandler.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) world.getTileEntity(pos3);
			if (te != null && te.getTroughRotation() == 2 && !world.isRemote) {
				
				return te;

			}

		}

		if (block4 == BlockHandler.blockTrough) {
			TileEntityTrough te = (TileEntityTrough) world.getTileEntity(pos4);
			if (te != null && te.getTroughRotation() == 3 && !world.isRemote) {

				return te;

			}

		}
		return null;
	}


	@Override
	public void update() {

		if(trough == null)
			this.trough = getPlacedTrough();

		
	}
	
	
	public TileEntityTrough getTrough()
	{
		return trough;
	}
	
	
	
}

package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.blocks.BlockCheeseMold;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.FluidHandlerCheeseMold;
import com.animania.common.tileentities.handler.ItemHandlerCheeseMold;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityCheeseMold extends TileEntity implements ITickable
{

	private FluidHandlerCheeseMold fluidHandler;
	private ItemHandlerCheeseMold itemHandler;
	private int progress;

	public TileEntityCheeseMold()
	{
		fluidHandler = new FluidHandlerCheeseMold();
		itemHandler = new ItemHandlerCheeseMold();
	}

	@Override
	public void update()
	{
		if (world.getBlockState(pos).getBlock() != Blocks.AIR)
		{

			if (this.fluidHandler.getFluid() != null && this.fluidHandler.getFluid().amount >= 1000 && progress < AnimaniaConfig.gameRules.cheeseMaturityTime && this.itemHandler.getStackInSlot(0).isEmpty())
			{
				progress++;
				markDirty();

			}

			if (progress >= AnimaniaConfig.gameRules.cheeseMaturityTime)
			{
				FluidStack fluid = fluidHandler.getFluid();
				if (fluid != null)
				{
					IBlockState state = world.getBlockState(pos);
					fluidHandler.drain(1000, true);
					if (fluid.getFluid() == BlockHandler.fluidMilkFriesian)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(ItemHandler.cheeseWheelFriesian));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));
					}
					else if (fluid.getFluid() == BlockHandler.fluidMilkHolstein)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(ItemHandler.cheeseWheelHolstein));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));
					}
					else if (fluid.getFluid() == BlockHandler.fluidMilkGoat)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(ItemHandler.cheeseWheelGoat));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));
					}
					else if (fluid.getFluid() == BlockHandler.fluidMilkSheep)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(ItemHandler.cheeseWheelSheep));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));
					}
					else if (fluid.getFluid() == FluidRegistry.WATER)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(ItemHandler.salt, 16));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));
					}

					progress = 0;
					markDirty();

				}

			}

			IBlockState state2 = world.getBlockState(pos);

			if(this.itemHandler.getStackInSlot(0).getItem() == ItemHandler.cheeseWheelFriesian && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));

			if(this.itemHandler.getStackInSlot(0).getItem() == ItemHandler.cheeseWheelHolstein && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));

			if(this.itemHandler.getStackInSlot(0).getItem() == ItemHandler.cheeseWheelGoat && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));
			
			if(this.itemHandler.getStackInSlot(0).getItem() == ItemHandler.cheeseWheelSheep && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));

			if(this.itemHandler.getStackInSlot(0).getItem() == ItemHandler.salt && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SALT)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));

			if(this.fluidHandler.getFluid() != null)
			{
				
				FluidStack fluid = fluidHandler.getFluid();
				if (fluid.getFluid() == BlockHandler.fluidMilkFriesian)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_MILK));
				}
				else if (fluid.getFluid() == BlockHandler.fluidMilkHolstein)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_MILK));
				}
				else if (fluid.getFluid() == BlockHandler.fluidMilkGoat)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_MILK));
				}
				else if (fluid.getFluid() == BlockHandler.fluidMilkSheep)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_MILK));
				}
				else if (fluid.getFluid() == FluidRegistry.WATER)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.WATER)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.WATER));
				}
			}


			if (itemHandler.getStackInSlot(0).isEmpty() && this.fluidHandler.getFluid() == null && world.getBlockState(pos) != world.getBlockState(pos).getBlock().getDefaultState())
				world.setBlockState(pos, world.getBlockState(pos).getBlock().getDefaultState());

		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.fluidHandler;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.itemHandler;

		return super.getCapability(capability, facing);

	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());

	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		return this.writeToNBT(new NBTTagCompound());
	}

	public FluidHandlerCheeseMold getFluidHandler()
	{
		return fluidHandler;
	}

	public ItemHandlerCheeseMold getItemHandler()
	{
		return itemHandler;
	}

	public int getProgress()
	{
		return progress;
	}

	public void setProgress(int progress)
	{
		this.progress = progress;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		NBTTagCompound fluid = new NBTTagCompound();
		NBTTagCompound items = new NBTTagCompound();
		fluid = this.fluidHandler.writeToNBT(fluid);
		items = this.itemHandler.serializeNBT();
		tag.setTag("fluid", fluid);
		tag.setTag("items", items);
		tag.setInteger("progress", progress);

		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.fluidHandler = new FluidHandlerCheeseMold();
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.itemHandler = new ItemHandlerCheeseMold();
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
		this.progress = compound.getInteger("progress");
	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		AnimaniaHelper.sendTileEntityUpdate(this);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
		return false;
	}

}

package com.animania.addons.farm.common.tileentity;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.block.BlockCheeseMold;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.tileentity.handler.FluidHandlerCheeseMold;
import com.animania.addons.farm.common.tileentity.handler.ItemHandlerCheeseMold;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
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
		if (world.getBlockState(pos).getBlock() == FarmAddonBlockHandler.blockCheeseMold)
		{

			if (this.fluidHandler.getFluid() != null && this.fluidHandler.getFluid().amount >= 1000 && progress < FarmConfig.settings.cheeseMaturityTime && this.itemHandler.getStackInSlot(0).isEmpty())
			{
				progress++;
				markDirty();

			}

			if (progress >= FarmConfig.settings.cheeseMaturityTime)
			{
				FluidStack fluid = fluidHandler.getFluid();
				if (fluid != null)
				{
					IBlockState state = world.getBlockState(pos);
					fluidHandler.drain(1000, true);
					if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelFriesian));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelHolstein));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelGoat));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelSheep));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelJersey));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));
					} else if (fluid.getFluid() == FluidRegistry.WATER)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.salt, FarmConfig.settings.saltCreationAmount));
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));
					}

					progress = 0;
					markDirty();

				}

			}

			IBlockState state2 = world.getBlockState(pos);

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelFriesian && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelHolstein && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelJersey && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelGoat && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelSheep && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_CHEESE)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.salt && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SALT)
				world.setBlockState(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));

			if (this.fluidHandler.getFluid() != null)
			{

				FluidStack fluid = fluidHandler.getFluid();
				if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
				{
					IBlockState state = world.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_MILK)
						world.setBlockState(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_MILK));
				} else if (fluid.getFluid() == FluidRegistry.WATER)
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
		CompoundNBT tagCompound = new CompoundNBT();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		return this.writeToNBT(new CompoundNBT());
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
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		CompoundNBT tag = super.writeToNBT(compound);
		CompoundNBT fluid = new CompoundNBT();
		CompoundNBT items = new CompoundNBT();
		fluid = this.fluidHandler.writeToNBT(fluid);
		items = this.itemHandler.serializeNBT();
		tag.putTag("fluid", fluid);
		tag.putTag("items", items);
		tag.putInteger("progress", progress);

		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
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
		return oldState.getBlock() != newSate.getBlock();
	}

}

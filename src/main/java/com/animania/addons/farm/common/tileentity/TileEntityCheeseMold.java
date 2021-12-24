package com.animania.addons.farm.common.tileentity;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.block.BlockCheeseMold;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.tileentity.handler.FluidHandlerCheeseMold;
import com.animania.addons.farm.common.tileentity.handler.ItemHandlerCheeseMold;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityCheeseMold extends BlockEntity implements Tickable
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
		if (level.getBlockState(pos).getBlock() == FarmAddonBlockHandler.blockCheeseMold)
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
					BlockState state = level.getBlockState(pos);
					fluidHandler.drain(1000, true);
					if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelFriesian));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelHolstein));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelGoat));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelSheep));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));
					} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelJersey));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));
					} else if (fluid.getFluid() == FluidRegistry.WATER)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.salt, FarmConfig.settings.saltCreationAmount));
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));
					}

					progress = 0;
					markDirty();

				}

			}

			BlockState state2 = level.getBlockState(pos);

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelFriesian && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_CHEESE)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelHolstein && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_CHEESE)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelJersey && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_CHEESE)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelGoat && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_CHEESE)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelSheep && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_CHEESE)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.salt && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SALT)
				level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));

			if (this.fluidHandler.getFluid() != null)
			{

				FluidStack fluid = fluidHandler.getFluid();
				if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_MILK)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_MILK)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_MILK)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_MILK)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_MILK));
				} else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_MILK)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_MILK));
				} else if (fluid.getFluid() == FluidRegistry.WATER)
				{
					BlockState state = level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.WATER)
						level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.WATER));
				}
			}

			if (itemHandler.getStackInSlot(0).isEmpty() && this.fluidHandler.getFluid() == null && level.getBlockState(pos) != level.getBlockState(pos).getBlock().defaultBlockState())
				level.setBlock(pos, level.getBlockState(pos).getBlock().defaultBlockState());

		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
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
		CompoundTag tagCompound = new CompoundTag();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.writeToNBT(new CompoundTag());
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
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		CompoundTag tag = super.writeToNBT(compound);
		CompoundTag fluid = new CompoundTag();
		CompoundTag items = new CompoundTag();
		fluid = this.fluidHandler.writeToNBT(fluid);
		items = this.itemHandler.serializeNBT();
		tag.putTag("fluid", fluid);
		tag.putTag("items", items);
		tag.putInteger("progress", progress);

		return tag;

	}

	@Override
	public void readFromNBT(CompoundTag compound)
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
	public boolean shouldRefresh(Level level, BlockPos pos, BlockState oldState, BlockState newSate)
	{
		return oldState.getBlock() != newSate.getBlock();
	}

}

package com.animania.addons.farm.common.BlockEntity;

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
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockEntityCheeseMold extends BlockEntity implements Tickable
{

	private FluidHandlerCheeseMold fluidHandler;
	private ItemHandlerCheeseMold itemHandler;
	private int progress;

	public BlockEntityCheeseMold()
	{
		this.fluidHandler = new FluidHandlerCheeseMold();
		this.itemHandler = new ItemHandlerCheeseMold();
	}

	@Override
	public void update()
	{
		if (this.level.getBlockState(pos).getBlock() == FarmAddonBlockHandler.blockCheeseMold)
		{

			if (this.fluidHandler.getFluid() != null && this.fluidHandler.getFluid().amount >= 1000 && this.progress < FarmConfig.settings.cheeseMaturityTime && this.itemHandler.getStackInSlot(0).isEmpty())
			{
				this.progress++;
				this.markDirty();

			}

			if (this.progress >= FarmConfig.settings.cheeseMaturityTime)
			{
				FluidStack fluid = this.fluidHandler.getFluid();
				if (fluid != null)
				{
					BlockState state = this.level.getBlockState(pos);
					this.fluidHandler.drain(1000, true);
					if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelFriesian));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));
					}
					else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelHolstein));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));
					}
					else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelGoat));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));
					}
					else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelSheep));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));
					}
					else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.cheeseWheelJersey));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));
					}
					else if (fluid.getFluid() == FluidRegistry.WATER)
					{
						this.itemHandler.setStackInSlot(0, new ItemStack(FarmAddonItemHandler.salt, FarmConfig.settings.saltCreationAmount));
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));
					}

					this.progress = 0;
					this.markDirty();

				}

			}

			BlockState state2 = this.level.getBlockState(pos);

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelFriesian && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_CHEESE)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelHolstein && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_CHEESE)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelJersey && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_CHEESE)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelGoat && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_CHEESE)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.cheeseWheelSheep && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_CHEESE)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_CHEESE));

			if (this.itemHandler.getStackInSlot(0).getItem() == FarmAddonItemHandler.salt && state2.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SALT)
				this.level.setBlock(pos, state2.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SALT));

			if (this.fluidHandler.getFluid() != null)
			{

				FluidStack fluid = this.fluidHandler.getFluid();
				if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkFriesian)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.FRIESIAN_MILK)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.FRIESIAN_MILK));
				}
				else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkHolstein)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.HOLSTEIN_MILK)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.HOLSTEIN_MILK));
				}
				else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkJersey)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.JERSEY_MILK)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.JERSEY_MILK));
				}
				else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkGoat)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.GOAT_MILK)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.GOAT_MILK));
				}
				else if (fluid.getFluid() == FarmAddonBlockHandler.fluidMilkSheep)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.SHEEP_MILK)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.SHEEP_MILK));
				}
				else if (fluid.getFluid() == FluidRegistry.WATER)
				{
					BlockState state = this.level.getBlockState(pos);
					if (state.getValue(BlockCheeseMold.VARIANT) != BlockCheeseMold.EnumType.WATER)
						this.level.setBlock(pos, state.withProperty(BlockCheeseMold.VARIANT, BlockCheeseMold.EnumType.WATER));
				}
			}

			if (this.itemHandler.getStackInSlot(0).isEmpty() && this.fluidHandler.getFluid() == null && this.level.getBlockState(pos) != this.level.getBlockState(pos).getBlock().defaultBlockState())
				this.level.setBlock(pos, this.level.getBlockState(pos).getBlock().defaultBlockState());

		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{

		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty() || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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
	public void onDataPacket(NetworkManager net, SPacketUpdateBlockEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());

	}

	@Override
	@Nullable
	public SPacketUpdateBlockEntity getUpdatePacket()
	{
		CompoundTag tagCompound = new CompoundTag();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateBlockEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public CompoundTag getUpdateTag()
	{
		return this.writeToNBT(new CompoundTag());
	}

	public FluidHandlerCheeseMold getFluidHandler()
	{
		return this.fluidHandler;
	}

	public ItemHandlerCheeseMold getItemHandler()
	{
		return this.itemHandler;
	}

	public int getProgress()
	{
		return this.progress;
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
		tag.putInteger("progress", this.progress);

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

		AnimaniaHelper.sendBlockEntityUpdate(this);
	}

	@Override
	public boolean shouldRefresh(Level level, BlockPos pos, BlockState oldState, BlockState newSate)
	{
		return oldState.getBlock() != newSate.getBlock();
	}

}

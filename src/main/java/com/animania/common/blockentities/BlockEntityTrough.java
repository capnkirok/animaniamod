package com.animania.common.blockentities;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodProviderBE;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.blockentities.handler.FluidHandlerTrough;
import com.animania.common.blockentities.handler.ItemHandlerTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockEntityTrough extends BlockEntity implements Tickable, IFoodProviderBE
{
	protected TroughContent troughType;
	private int troughRotation;

	public ItemHandlerTrough itemHandler;
	public FluidHandlerTrough fluidHandler;

	private int oldItemCount = 0;
	private int oldFluidCount = 0;

	public BlockEntityTrough()
	{

		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
	}

	@Override
	public void onDataPacket(Connection net, SPacketUpdateBlockEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		if (this.blockType != null && this.pos != null)
			this.level.notifyBlockUpdate(this.pos, this.blockType.defaultBlockState(), this.blockType.defaultBlockState(), 1);

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

	@Override
	public void update()
	{

		ItemStack stack = this.itemHandler.getStackInSlot(0);
		FluidStack fluid = this.fluidHandler.getFluid();

		if (!stack.isEmpty())
		{
			int count = stack.getCount();

			if (count != this.oldItemCount)
			{
				this.markDirty();
				this.oldItemCount = count;
			}

			if (count == 0 && fluid == null && this.troughType != TroughContent.EMPTY)
				this.setTroughType(TroughContent.EMPTY);
			else if (count >= 1 && this.troughType != TroughContent.FOOD)
				this.setTroughType(TroughContent.FOOD);

		}
		else if (fluid != null)
		{
			if (this.troughType != TroughContent.LIQUID)
				this.setTroughType(TroughContent.LIQUID);

			if (fluid.amount != this.oldFluidCount)
			{
				this.markDirty();
				this.oldFluidCount = fluid.amount;
			}

		}
		else if (this.troughType != TroughContent.EMPTY)
			this.setTroughType(TroughContent.EMPTY);

	}

	public void setTroughType(TroughContent type)
	{
		this.troughType = type;
		this.markDirty();
		this.level.notifyBlockUpdate(this.pos, this.level.getBlockState(this.pos), this.level.getBlockState(this.pos), 2);
	}

	public TroughContent getTroughContent()
	{
		return this.troughType;
	}

	public int getTroughRotation()
	{
		return this.troughRotation;
	}

	public void setTroughRotation(int rotation)
	{
		this.troughRotation = rotation;
	}

	@Override
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		CompoundTag tag = super.writeToNBT(compound);
		CompoundTag items = this.itemHandler.serializeNBT();
		CompoundTag fluid = new CompoundTag();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.put("items", items);
		tag.put("fluid", fluid);
		tag.setByte("Rot", (byte) (this.troughRotation & 255));

		return tag;

	}

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.troughRotation = compound.getByte("Rot");
		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{

		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.fluidHandler.getFluid() == null || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
				return true;
		}

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
	{
		if (AnimaniaConfig.gameRules.allowTroughAutomation)
		{

			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.fluidHandler.getFluid() == null)
				return (T) this.itemHandler;

			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
				return (T) this.fluidHandler;

		}

		return super.getCapability(capability, facing);

	}

	@Override
	public void markDirty()
	{
		super.markDirty();

		AnimaniaHelper.sendBlockEntityUpdate(this);
	}

	@Override
	public boolean canConsume(@Nullable Set<ItemStack> fooditems, @Nullable Fluid[] fluid)
	{
		if (fluid == null)
			return this.canConsume(null, fooditems);
		else
		{
			boolean canConsumeAny = false;
			for (Fluid f : fluid)
			{
				boolean consume = this.canConsume(new FluidStack(f, 0), fooditems);
				if (consume)
					canConsumeAny = true;
			}

			return canConsumeAny;
		}
	}

	@Override
	public boolean canConsume(@Nullable FluidStack fluid, @Nullable Set<ItemStack> fooditems)
	{
		if (fooditems != null && !this.itemHandler.getStackInSlot(0).isEmpty())
		{
			ItemStack stack = this.itemHandler.getStackInSlot(0);
			return AnimaniaHelper.containsItemStack(fooditems, stack);
		}

		if (fluid != null && this.fluidHandler.getFluid() != null && fluid.getFluid() == this.fluidHandler.getFluid().getFluid())
		{
			FluidStack fluidstack = this.fluidHandler.getFluid();
			return fluidstack.getFluid() == fluid.getFluid() && fluidstack.amount >= fluid.amount;
		}

		return false;
	}

	@Override
	public void consumeSolidOrLiquid(int liquidAmount, int itemAmount)
	{
		if (!this.itemHandler.getStackInSlot(0).isEmpty())
		{
			this.consumeSolid(itemAmount);
			return;
		}

		if (this.fluidHandler.getFluid() != null)
		{
			this.consumeLiquid(liquidAmount);
		}
	}

	@Override
	public void consumeSolid(int amount)
	{
		this.itemHandler.getStackInSlot(0).shrink(amount);
	}

	@Override
	public void consumeLiquid(int amount)
	{
		this.fluidHandler.drain(amount, true);
	}

	public static enum TroughContent
	{
		EMPTY, LIQUID, FOOD,
	}

}
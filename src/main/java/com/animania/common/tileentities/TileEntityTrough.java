package com.animania.common.tileentities;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.FluidHandlerTrough;
import com.animania.common.tileentities.handler.ItemHandlerTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityTrough extends TileEntity implements ITickable, IFoodProviderTE
{
	protected TroughContent troughType;
	private int troughRotation;

	public ItemHandlerTrough itemHandler;
	public FluidHandlerTrough fluidHandler;

	private int oldItemCount = 0;
	private int oldFluidCount = 0;

	public TileEntityTrough()
	{

		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		if (this.blockType != null && this.pos != null)
			this.level.notifyBlockUpdate(this.pos, this.blockType.defaultBlockState(), this.blockType.defaultBlockState(), 1);

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

	@Override
	public void update()
	{

		ItemStack stack = this.itemHandler.getStackInSlot(0);
		FluidStack fluid = this.fluidHandler.getFluid();

		if (!stack.isEmpty())
		{
			int count = stack.getCount();

			if (count != oldItemCount)
			{
				this.markDirty();
				oldItemCount = count;
			}

			if (count == 0 && fluid == null && this.troughType != TroughContent.EMPTY)
				this.setTroughType(TroughContent.EMPTY);
			else if (count >= 1 && this.troughType != TroughContent.FOOD)
				this.setTroughType(TroughContent.FOOD);

		} else if (fluid != null)
		{
			if (this.troughType != TroughContent.LIQUID)
				this.setTroughType(TroughContent.LIQUID);

			if (fluid.amount != oldFluidCount)
			{
				this.markDirty();
				oldFluidCount = fluid.amount;
			}

		} else if (this.troughType != TroughContent.EMPTY)
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
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		CompoundNBT tag = super.writeToNBT(compound);
		CompoundNBT items = this.itemHandler.serializeNBT();
		CompoundNBT fluid = new CompoundNBT();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.putTag("items", items);
		tag.putTag("fluid", fluid);
		tag.setByte("Rot", (byte) (this.troughRotation & 255));

		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
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
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && this.fluidHandler.getFluid() == null)
				return true;
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && this.itemHandler.getStackInSlot(0).isEmpty())
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

		AnimaniaHelper.sendTileEntityUpdate(this);
	}

	@Override
	public boolean canConsume(@Nullable Set<ItemStack> fooditems, @Nullable Fluid[] fluid)
	{
		if (fluid == null)
			return canConsume(null, fooditems);
		else
		{
			boolean canConsumeAny = false;
			for (Fluid f : fluid)
			{
				boolean consume = canConsume(new FluidStack(f, 0), fooditems);
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
			return;
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
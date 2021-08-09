package com.animania.addons.catsdogs.common.tileentity;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.addons.catsdogs.common.tileentity.handler.FluidHandlerPetBowl;
import com.animania.addons.catsdogs.common.tileentity.handler.ItemHandlerPetBowl;
import com.animania.api.interfaces.IFoodProviderTE;
import com.animania.common.helper.AnimaniaHelper;
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

public class TileEntityPetBowl extends TileEntity implements ITickable, IFoodProviderTE
{
	protected BowlContent type;

	public ItemHandlerPetBowl itemHandler;
	public FluidHandlerPetBowl fluidHandler;

	private int oldItemCount = 0;
	private int oldFluidCount = 0;

	public TileEntityPetBowl()
	{

		this.itemHandler = new ItemHandlerPetBowl();
		this.fluidHandler = new FluidHandlerPetBowl(1000);
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

			if (count == 0 && fluid == null && this.type != BowlContent.EMPTY)
				this.setTroughType(BowlContent.EMPTY);
			else if (count >= 1 && this.type != BowlContent.FOOD)
				this.setTroughType(BowlContent.FOOD);

		} else if (fluid != null)
		{
			if (this.type != BowlContent.LIQUID)
				this.setTroughType(BowlContent.LIQUID);

			if (fluid.amount != oldFluidCount)
			{
				this.markDirty();
				oldFluidCount = fluid.amount;
			}

		} else if (this.type != BowlContent.EMPTY)
			this.setTroughType(BowlContent.EMPTY);

	}

	public void setTroughType(BowlContent type)
	{
		this.type = type;
		this.markDirty();
		this.level.notifyBlockUpdate(this.pos, this.level.getBlockState(this.pos), this.level.getBlockState(this.pos), 2);
	}

	public BowlContent getBowlContent()
	{
		return this.type;
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

		return tag;

	}

	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.itemHandler = new ItemHandlerPetBowl();
		this.fluidHandler = new FluidHandlerPetBowl(1000);
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

			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				return (T) this.itemHandler;

			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
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

	public static enum BowlContent
	{
		EMPTY, LIQUID, FOOD,
	}

}
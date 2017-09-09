package com.animania.common.tileentities;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.FluidHandlerTrough;
import com.animania.common.tileentities.handler.ItemHandlerTrough;
import com.animania.config.AnimaniaConfig;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityTrough extends TileEntity implements ITickable
{
	private TroughContent troughType;
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
			this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 1);

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

	@Override
	public void update()
	{

		ItemStack stack = this.itemHandler.getStackInSlot(0);
		FluidStack fluid = this.fluidHandler.getFluid();

		if (!stack.isEmpty())
		{
			int count = stack.getCount();
			
			if(count != oldItemCount)
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
			if(this.troughType != TroughContent.LIQUID)
				this.setTroughType(TroughContent.LIQUID);
		
			if(fluid.amount != oldFluidCount)
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
		this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 2);
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagCompound tag = super.writeToNBT(compound);
		NBTTagCompound items = this.itemHandler.serializeNBT();
		NBTTagCompound fluid = new NBTTagCompound();
		fluid = this.fluidHandler.writeToNBT(fluid);
		tag.setTag("items", items);
		tag.setTag("fluid", fluid);
		tag.setByte("Rot", (byte) (this.troughRotation & 255));

		return tag;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.troughRotation = compound.getByte("Rot");
		this.itemHandler = new ItemHandlerTrough();
		this.fluidHandler = new FluidHandlerTrough(1000);
		this.fluidHandler.readFromNBT(compound.getCompoundTag("fluid"));
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
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
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
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
	
	public boolean canConsume(@Nullable Set<Item> fooditems, @Nullable Fluid fluid)
	{
		if(fooditems != null && !this.itemHandler.getStackInSlot(0).isEmpty())
		{
			ItemStack stack = this.itemHandler.getStackInSlot(0);
			return fooditems.contains(stack.getItem());
		}
		
		if(fluid != null && this.fluidHandler.getFluid() != null)
		{
			FluidStack fluidstack = this.fluidHandler.getFluid();
			return fluidstack.getFluid() == fluid;
		}
		
		return false;
	}
	
	public void consumeSolidOrLiquid(int liquidAmount, int itemAmount)
	{
		if(!this.itemHandler.getStackInSlot(0).isEmpty())
		{
			this.consumeSolid(itemAmount);
			return;
		}
		
		if(this.fluidHandler.getFluid() != null)
		{
			this.consumeLiquid(liquidAmount);
			return;
		}
	}
	
	public void consumeSolid(int amount)
	{
		this.itemHandler.getStackInSlot(0).shrink(amount);
	}
	
	public void consumeLiquid(int amount)
	{
		this.fluidHandler.drain(amount, true);
	}
	
	public static enum TroughContent
	{
		EMPTY,
		LIQUID,
		FOOD,
	}
	

}
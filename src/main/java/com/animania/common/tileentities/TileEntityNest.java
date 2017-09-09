package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.ItemHandlerNest;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityNest extends TileEntity implements ITickable
{
	
	private NestContent nestContent;
	public AnimaniaType birdType;
	public ItemHandlerNest itemHandler;
	private int oldItemCount = 0;

	public TileEntityNest()
	{
		this.itemHandler = new ItemHandlerNest();
	}

	public NestContent getNestContent()
	{
		return this.nestContent;
	}

	public AnimaniaType getBirdType()
	{
		return this.birdType;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagCompound items = this.itemHandler.serializeNBT();
		compound.setTag("items", items);
		if (birdType != null)
			compound.setString("birdType", birdType.toString());
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.itemHandler = new ItemHandlerNest();
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
		try
		{
			ChickenType ct = ChickenType.valueOf(compound.getString("birdType"));
			this.birdType = ct;
		}
		catch (Exception e)
		{
		}

		try
		{
			PeacockType pt = PeacockType.valueOf(compound.getString("birdType"));
			this.birdType = pt;
		}
		catch (Exception e)
		{
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
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

		if (!stack.isEmpty())
		{
			int count = stack.getCount();
			if (count != oldItemCount)
			{
				this.markDirty();
				oldItemCount = count;
			}

			if (stack.getItem() == Items.EGG && this.nestContent != NestContent.CHICKEN_WHITE)
				this.nestContent = NestContent.CHICKEN_WHITE;
			else if (stack.getItem() == ItemHandler.brownEgg && this.nestContent != NestContent.CHICKEN_BROWN)
				this.nestContent = NestContent.CHICKEN_BROWN;
			else if (stack.getItem() == ItemHandler.peacockEggBlue && this.nestContent != NestContent.PEACOCK_BLUE)
				this.nestContent = NestContent.PEACOCK_BLUE;
			else if (stack.getItem() == ItemHandler.peacockEggWhite && this.nestContent != NestContent.PEACOCK_WHITE)
				this.nestContent = NestContent.PEACOCK_WHITE;
		}
		else if (this.nestContent != NestContent.EMPTY)
		{
			this.nestContent = NestContent.EMPTY;
			this.birdType = null;
		}
	}

	public boolean insertItem(ItemStack stack)
	{
		ItemStack item = this.itemHandler.getStackInSlot(0);
		if (item.isEmpty() ? true : ItemStack.areItemsEqual(stack, item))
		{
			int count = item.getCount();
			if (stack.getCount() + count <= 3)
			{
				ItemStack newStack = stack.copy();
				newStack.setCount(stack.getCount() + count);
				itemHandler.setStackInSlot(0, newStack);
				return true;
			}
		}

		return false;
	}

	public void removeItem()
	{
		ItemStack item = this.itemHandler.getStackInSlot(0);
		ItemStack copy = item.copy();
		copy.shrink(1);
		this.itemHandler.setStackInSlot(0, copy);
	}


	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.itemHandler;

		return super.getCapability(capability, facing);
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		AnimaniaHelper.sendTileEntityUpdate(this);
	}


	public static enum NestContent
	{
		EMPTY,
		CHICKEN_WHITE,
		CHICKEN_BROWN,
		PEACOCK_WHITE,
		PEACOCK_BLUE;
	}

}
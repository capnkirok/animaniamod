package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AnimalTypeHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.tileentities.handler.ItemHandlerNest;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityNest extends TileEntity implements ITickable
{

	private NestContent nestContent;
	public AnimaniaType birdType;
	public ItemHandlerNest itemHandler;
	private int oldItemCount = 0;

	public TileEntityNest()
	{
		this.itemHandler = new ItemHandlerNest(this);
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
	public CompoundNBT writeToNBT(CompoundNBT compound)
	{
		super.writeToNBT(compound);
		CompoundNBT items = this.itemHandler.serializeNBT();
		compound.putTag("items", items);
		if (birdType != null)
			compound.setString("birdType", birdType.toString());
		return compound;
	}

	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.itemHandler = new ItemHandlerNest(this);
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));
		
		if(AddonHandler.isAddonLoaded("farm"))
		{
			AnimaniaType type = AnimalTypeHandler.getType(Animania.MODID + ":chicken", compound.getString("birdType"));
			if(type != null)
				this.birdType = type;
		}
		
		if(AddonHandler.isAddonLoaded("extra"))
		{
			AnimaniaType type = AnimalTypeHandler.getType(Animania.MODID + ":peacock", compound.getString("birdType"));
			if(type != null)
				this.birdType = type;
		}
		
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		this.level.notifyBlockUpdate(this.pos, this.getBlockType().getDefaultState(), this.getBlockType().getDefaultState(), 1);

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

		if (!stack.isEmpty())
		{
			int count = stack.getCount();
			if (count != oldItemCount)
			{
				this.markDirty();
				oldItemCount = count;
			}

			for (NestContent c : NestContent.values())
			{
				if (stack.getItem() == c.item && this.nestContent != c)
					this.nestContent = c;
			}
		} else if (this.nestContent != NestContent.EMPTY)
		{
			this.nestContent = NestContent.EMPTY;
			this.birdType = null;
			this.markDirty();
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
		EMPTY(Items.AIR), 
		CHICKEN_WHITE(Items.EGG), 
		CHICKEN_BROWN(Item.getByNameOrId(Animania.MODID + ":brown_egg")), 
		PEACOCK_WHITE(Item.getByNameOrId(Animania.MODID + ":peacock_egg_white")), 
		PEACOCK_BLUE(Item.getByNameOrId(Animania.MODID + ":peacock_egg_blue"));

		private Item item;

		private NestContent(Item item)
		{
			if (item == null)
				item = Items.AIR;
			this.item = item;
		}

		public Item getItem()
		{
			return item;
		}
	}

}
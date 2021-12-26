package com.animania.common.tileentities;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AnimalTypeHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.tileentities.handler.ItemHandlerNest;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class BlockEntityNest extends BlockEntity implements Tickable
{

	private NestContent nestContent;
	public AnimaniaType birdType;
	public ItemHandlerNest itemHandler;
	private int oldItemCount = 0;

	public BlockEntityNest()
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
	public CompoundTag writeToNBT(CompoundTag compound)
	{
		super.writeToNBT(compound);
		CompoundTag items = this.itemHandler.serializeNBT();
		compound.putTag("items", items);
		if (this.birdType != null)
			compound.setString("birdType", this.birdType.toString());
		return compound;
	}

	@Override
	public void readFromNBT(CompoundTag compound)
	{
		super.readFromNBT(compound);
		this.itemHandler = new ItemHandlerNest(this);
		this.itemHandler.deserializeNBT(compound.getCompoundTag("items"));

		if (AddonHandler.isAddonLoaded("farm"))
		{
			AnimaniaType type = AnimalTypeHandler.getType(Animania.MODID + ":chicken", compound.getString("birdType"));
			if (type != null)
				this.birdType = type;
		}

		if (AddonHandler.isAddonLoaded("extra"))
		{
			AnimaniaType type = AnimalTypeHandler.getType(Animania.MODID + ":peacock", compound.getString("birdType"));
			if (type != null)
				this.birdType = type;
		}

	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateBlockEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
		this.level.notifyBlockUpdate(this.pos, this.getBlockType().defaultBlockState(), this.getBlockType().defaultBlockState(), 1);

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

		if (!stack.isEmpty())
		{
			int count = stack.getCount();
			if (count != this.oldItemCount)
			{
				this.markDirty();
				this.oldItemCount = count;
			}

			for (NestContent c : NestContent.values())
			{
				if (stack.getItem() == c.item && this.nestContent != c)
					this.nestContent = c;
			}
		}
		else if (this.nestContent != NestContent.EMPTY)
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
				this.itemHandler.setStackInSlot(0, newStack);
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
	public boolean hasCapability(Capability<?> capability, Direction facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.itemHandler;

		return super.getCapability(capability, facing);
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		AnimaniaHelper.sendBlockEntityUpdate(this);
	}

	public static enum NestContent
	{
		EMPTY(Items.AIR), CHICKEN_WHITE(Items.EGG), CHICKEN_BROWN(RItem.getByNameOrId(Animania.MODID + ":brown_egg")), PEACOCK_WHITE(RItem.getByNameOrId(Animania.MODID + ":peacock_egg_white")), PEACOCK_BLUE(RItem.getByNameOrId(Animania.MODID + ":peacock_egg_blue"));

		private RItem item;

		private NestContent(RItem item)
		{
			if (item == null)
				item = Items.AIR;
			this.item = item;
		}

		public RItem getItem()
		{
			return this.item;
		}
	}

}
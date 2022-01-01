package common.blockentity.handler;

import common.handler.ExtraAddonItemHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerHamsterWheel extends ItemStackHandler
{

	public ItemHandlerHamsterWheel()
	{
		this.setSize(1);
	}

	@Override
	public int getSlotLimit(int slot)
	{
		return 16;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{

		if (!stack.isEmpty() && stack.getItem() == ExtraAddonItemHandler.hamsterFood)
			return super.insertItem(slot, stack, simulate);

		return stack;
	}

}

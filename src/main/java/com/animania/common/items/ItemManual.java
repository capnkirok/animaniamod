package com.animania.common.items;

import com.animania.Animania;
import com.animania.manual.resources.ManualResourceLoader;

import net.minecraft.client.entity.PlayerEntitySP;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.item.ItemStack;

public class ItemManual extends AnimaniaItem
{

	public ItemManual()
	{
		super("animania_manual");
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(Level level, PlayerEntity player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (level.isRemote && player instanceof PlayerEntitySP)
		{
			if (!ManualResourceLoader.errored)
			{
				Animania.proxy.openManualGui(stack);
			}
			else
				player.sendMessage(new TextComponentString(TextFormatting.RED + "Error while building the book! Please double-check your json files!"));
		}

		return new ActionResult(ActionResultType.SUCCESS, stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return slotChanged;
	}

}

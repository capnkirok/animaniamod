package com.animania.common.items;

import com.animania.Animania;
import com.animania.manual.resources.ManualResourceLoader;

import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemManual extends AnimaniaItem
{

	public ItemManual()
	{
		super("animania_manual", new Item.Properties().stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		if (level.isClientSide && player instanceof LocalPlayer)
		{
			if (!ManualResourceLoader.errored)
			{
				Animania.proxy.openManualGui(stack);
			}
			else
				player.displayClientMessage(new TextComponent(ChatFormatting.RED + "Error while building the book! Please double-check your json files!"), false);
		}

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return slotChanged;
	}

}

package com.animania.common.events;

import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemDolly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DollyEvents
{

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onScroll(MouseEvent event)
	{
		if (event.getDwheel() > 0 || event.getDwheel() < 0)
		{
			ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
			if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly)
			{
				if (ItemDolly.hasChest(stack))
					event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDrop(ItemTossEvent event)
	{
		ItemStack stack = event.getEntityItem().getEntityItem();
		EntityPlayer player = event.getPlayer();
		Item item = stack.getItem();
		if (!stack.isEmpty() && item == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			int slot = player.inventory.currentItem;
			player.inventory.setInventorySlotContents(slot, stack);
			event.setCanceled(true);

		}
	}

	@SubscribeEvent
	public void onOffHand(LivingEquipmentChangeEvent event)
	{
		EntityEquipmentSlot slot = event.getSlot();
		ItemStack from = event.getFrom();
		ItemStack to = event.getTo();
		EntityLivingBase living = event.getEntityLiving();

		if (slot == EntityEquipmentSlot.OFFHAND && !to.isEmpty() && to.getItem() == ItemHandler.dolly && ItemDolly.hasChest(to))
		{
			living.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, to);
			living.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, from);
		}
		if (slot == EntityEquipmentSlot.MAINHAND && !from.isEmpty() && from.getItem() == ItemHandler.dolly && ItemDolly.hasChest(from))
		{
			living.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, from);
			living.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, to);
		}
			
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event)
	{
		if (event.getGui() != null)
		{
			boolean inventory = event.getGui() instanceof GuiContainer;
			EntityPlayer player = Minecraft.getMinecraft().player;
			if (player != null)
			{
				ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
				if (inventory && !stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
				{
					event.setCanceled(true);
				}
			}
		}
	}

}

package com.animania.common.events;

import java.lang.reflect.Field;

import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemDolly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
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
					Minecraft.getMinecraft().currentScreen = null;
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void inputEvent(InputEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		Field field = KeyBinding.class.getDeclaredField("pressed");
		field.setAccessible(true);
		ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			if (settings.keyBindDrop.isPressed())
			{
				field.set(settings.keyBindDrop, false);
			}
			if (settings.keyBindSwapHands.isPressed())
			{
				field.set(settings.keyBindSwapHands, false);
			}
			for (KeyBinding keyBind : settings.keyBindsHotbar)
			{
				if (keyBind.isPressed())
				{
					field.set(keyBind, false);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBlockClick(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			event.setUseBlock(Result.DENY);
		}

	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onItemDropped(EntityJoinWorldEvent event)
	{
		Entity e = event.getEntity();
		World world = event.getWorld();
		if (e instanceof EntityItem)
		{
			EntityItem eitem = (EntityItem) e;
			ItemStack stack = eitem.getEntityItem();
			Item item = stack.getItem();
			if (item == ItemHandler.dolly && ItemDolly.hasChest(stack))
			{
				BlockPos pos = eitem.getPosition();
				BlockPos finalPos = pos;
				if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos) || !Blocks.CHEST.canPlaceBlockAt(world, pos))
				{
					for (EnumFacing facing : EnumFacing.VALUES)
					{
						BlockPos offsetPos = pos.offset(facing);
						if (world.getBlockState(offsetPos).getBlock().isReplaceable(world, offsetPos) && Blocks.CHEST.canPlaceBlockAt(world, offsetPos))
						{
							finalPos = offsetPos;
							break;
						}
					}
				}
				world.setBlockState(finalPos, Blocks.CHEST.getDefaultState());
				TileEntity tile = world.getTileEntity(finalPos);
				tile.readFromNBT(ItemDolly.getChest(stack));
				tile.setPos(finalPos);
				ItemDolly.clearChest(stack);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHand(RenderHandEvent event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		if(!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			BlockPos pos = player.getPosition();
			NBTTagCompound nbt = ItemDolly.getChest(stack);
			TileEntityChest chest = (TileEntityChest) TileEntityChest.create(world, nbt);
			TileEntitySpecialRenderer<TileEntityChest> tesr = TileEntityRendererDispatcher.instance.getSpecialRenderer(chest);
			GlStateManager.pushMatrix();
			tesr.renderTileEntityAt(chest, pos.getX(), pos.getY() + 0.5, pos.getZ(), 1.0f, 0);
			GlStateManager.popMatrix();
		}
	
	}
}

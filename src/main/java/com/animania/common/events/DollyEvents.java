package com.animania.common.events;

import java.lang.reflect.Field;

import com.animania.common.handler.ForbiddenTileHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemDolly;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
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
	private static final ResourceLocation CHEST_TEXTURE = new ResourceLocation("minecraft:textures/entity/chest/normal.png");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onScroll(MouseEvent event)
	{
		if (event.getDwheel() > 0 || event.getDwheel() < 0)
		{
			ItemStack stack = Minecraft.getMinecraft().player.getHeldItemMainhand();
			if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly)
			{
				if (ItemDolly.hasTileData(stack))
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
				if (inventory && !stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
				{
					// event.setCanceled(true);
					// Minecraft.getMinecraft().currentScreen = null;
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
		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
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
		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
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
			if (item == ItemHandler.dolly && ItemDolly.hasTileData(stack))
			{
				BlockPos pos = eitem.getPosition();
				BlockPos finalPos = pos;
				Block block = ItemDolly.getBlock(stack);
				if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos) || !block.canPlaceBlockAt(world, pos))
				{
					for (EnumFacing facing : EnumFacing.VALUES)
					{
						BlockPos offsetPos = pos.offset(facing);
						if (world.getBlockState(offsetPos).getBlock().isReplaceable(world, offsetPos) && block.canPlaceBlockAt(world, offsetPos))
						{
							finalPos = offsetPos;
							break;
						}
					}
				}
				world.setBlockState(finalPos, block.getDefaultState());
				TileEntity tile = world.getTileEntity(finalPos);
				tile.readFromNBT(ItemDolly.getTileData(stack));
				tile.setPos(finalPos);
				ItemDolly.clearTileData(stack);
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
		int pass = event.getRenderPass();
		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
		{
			Block block = ItemDolly.getBlock(stack);
			BlockPos pos = player.getPosition();
			stack = ItemDolly.getItemStack(stack);

			int light = world.getLight(player.getPosition());
			int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;

			GlStateManager.pushMatrix();
			GlStateManager.scale(2.5, 2.5, 2.5);
			// Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityItem,
			// pos.getX() + .1, pos.getY(), pos.getZ() + .1, player.rotationYaw,
			// 90, true);
			GlStateManager.translate(0, -0.5, -1);
			if (block == Blocks.CHEST || block == Blocks.ENDER_CHEST || block == Blocks.TRAPPED_CHEST)
			{
				GlStateManager.rotate(180, 0, 1f, 0);
				GlStateManager.rotate(-15, 1f, 0, 0);
			}
			else
				GlStateManager.rotate(15, 1f, 0, 0);

			RenderHelper.enableStandardItemLighting();

			if (perspective == 0)
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, player));
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();
			
			event.setCanceled(true);

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerRenderPost(RenderPlayerEvent.Post event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();

		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
		{
			Block block = ItemDolly.getBlock(stack);
			stack = ItemDolly.getItemStack(stack);

			EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0);
			entityItem.hoverStart = 0;

			entityItem.setEntityItemStack(stack);
			float rotation = -player.renderYawOffset;
			int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;

			GlStateManager.pushMatrix();
			GlStateManager.scale(2.2, 2.2, 2.2);

			if (block == Blocks.CHEST || block == Blocks.ENDER_CHEST || block == Blocks.TRAPPED_CHEST)
			{
				GlStateManager.rotate(rotation, 0, 1.0f, 0);
				GlStateManager.translate(0, 0.1, 0.2);
			}
			else
			{
				GlStateManager.rotate(rotation + 180, 0, 1.0f, 0);
				GlStateManager.translate(0, 0.1, -0.2);
			}

			if (player.isSneaking())
				GlStateManager.translate(0, -0.08, 0);

			Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityItem, event.getX(), event.getY(), event.getZ(), 0, 0, true);
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerRenderPre(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		RenderPlayer renderer = event.getRenderer();

		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasTileData(stack))
		{

		}
	}

	@SubscribeEvent
	public void onBlockRightClick(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack main = player.getHeldItemMainhand();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		Block block = world.getBlockState(pos).getBlock();
		IBlockState state = world.getBlockState(pos);

		if (main.isEmpty() && player.isSneaking() && !ForbiddenTileHandler.isForbidden(block))
		{
			ItemStack stack = new ItemStack(ItemHandler.dolly);

			TileEntity te = world.getTileEntity(pos);
			if (te != null && (block.getBlockHardness(state, world, pos) != -1 || player.isCreative()))
			{
				if (ItemDolly.storeTileData(te, state, stack))
				{
					world.removeTileEntity(pos);
					world.setBlockToAir(pos);
					player.setHeldItem(EnumHand.MAIN_HAND, stack);
				}

			}

		}
	}

}

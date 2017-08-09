package com.animania.common.events;

import java.lang.reflect.Field;

import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemDolly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
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

		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			BlockPos pos = player.getPosition();
			stack = new ItemStack(Blocks.CHEST, 1);

			EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0);
			entityItem.hoverStart = 0;

			entityItem.setEntityItemStack(stack);

			GlStateManager.pushMatrix();
			GlStateManager.scale(3, 3, 3);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(entityItem, pos.getX() + .1, pos.getY(), pos.getZ() + .1, player.rotationYaw, 90, true);
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerRenderPost(RenderPlayerEvent.Post event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();

		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			// TODO Temporary... pull stack from Dolly when we have it
			stack = new ItemStack(Blocks.CHEST, 1);

			EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0);
			entityItem.hoverStart = 0;

			entityItem.setEntityItemStack(stack);
			float rotation = -player.renderYawOffset;


			GlStateManager.pushMatrix();
			GlStateManager.scale(3, 3, 3);
			GlStateManager.rotate(rotation, 0, 1.0f, 0);
			GlStateManager.translate(0, 0, 0.15);
			
			if(player.isSneaking())
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

		if (!stack.isEmpty() && stack.getItem() == ItemHandler.dolly && ItemDolly.hasChest(stack))
		{
			ModelPlayer playerModel = renderer.getMainModel();
			
			playerModel.bipedLeftArm.rotateAngleZ = 0.1f;

		}
	}

}

package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.client.models.item.AnimatedEggModelWrapper;
import com.animania.client.render.item.RenderAnimatedEgg;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemEntityEggAnimated;
import com.animania.manual.resources.ManualResourceLoader;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CarryRenderer
{

	/*
	 * Renders the Entity in First Person
	 */
	@SideOnly(Dist.CLIENT)
	@SubscribeEvent
	public static void renderHand(RenderHandEvent event)
	{

		Level level = Minecraft.getInstance().level;
		Player player = Minecraft.getInstance().player;
		AbstractClientPlayer aplayer = (AbstractClientPlayer) player;
		ItemStack stack = player.getMainHandItem();
		int perspective = Minecraft.getInstance().gameSettings.thirdPersonView;
		float partialticks = event.getPartialTicks();
		ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		if (props != null && props.isCarrying())
		{
			BlockPos pos = player.getPosition();
			Entity entity = EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, props.getType()), level);
			entity.readFromNBT(props.getAnimal());
			if (entity != null)
			{
				double d0 = player.lastTickgetX() + (player.getX() - player.lastTickgetX()) * partialticks;
				double d1 = player.lastTickgetY() + (player.getY() - player.lastTickgetY()) * partialticks;
				double d2 = player.lastTickgetZ() + (player.getZ() - player.lastTickgetZ()) * partialticks;

				entity.setPosition(d0, d1, d2);
				entity.rotationYaw = 0.0f;
				entity.prevRotationYaw = 0.0f;
				entity.setRotationYawHead(0.0f);

				float height = entity.height;
				float width = entity.width;
				float multiplier = height * width;

				GlStateManager.pushMatrix();
				GlStateManager.scale(50, 50, 50);
				GlStateManager.rotate(180, 0, 1, 0);
				GlStateManager.translate(-0.2, -0.1, 0.13);
				GlStateManager.enableAlpha();
				GlStateManager.scale(0.3, 0.3, 0.3);
				GlStateManager.translate(0, 0.1, 0);

				if (perspective == 0)
				{
					RenderHelper.enableStandardItemLighting();
					Minecraft.getInstance().getRenderManager().setRenderShadow(false);
					Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0.0f, false);
					Minecraft.getInstance().getRenderManager().setRenderShadow(true);

				}

				GlStateManager.disableAlpha();

				GlStateManager.scale(1, 1, 1);
				GlStateManager.popMatrix();

				// RenderHelper.disableStandardItemLighting();
				// GlStateManager.disableRescaleNormal();
				// GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
				// GlStateManager.disableTexture2D();
				// GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);

			}
		}

	}

	/*
	 * Renders the Block in Third Person
	 */
	@SideOnly(Dist.CLIENT)
	@SubscribeEvent
	public static void onPlayerRenderPost(RenderPlayerEvent.Post event)
	{
		Level level = Minecraft.getInstance().level;
		Player player = event.getPlayer();
		ModelPlayer modelPlayer = event.getRenderer().getMainModel();
		LocalPlayer clientPlayer = Minecraft.getInstance().player;
		ItemStack stack = player.getMainHandItem();
		float partialticks = event.getPartialRenderTick();
		ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);

		if (props != null && props.isCarrying())
		{
			Entity entity = EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, props.getType()), level);
			entity.readFromNBT(props.getAnimal());
			float rotation = -(player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialticks);
			int perspective = Minecraft.getInstance().gameSettings.thirdPersonView;

			if (entity != null)
			{
				if (entity instanceof EntityHamster)
					((EntityHamster) entity).setSitting(true);
				double d0 = player.lastTickgetX() + (player.getX() - player.lastTickgetX()) * partialticks;
				double d1 = player.lastTickgetY() + (player.getY() - player.lastTickgetY()) * partialticks;
				double d2 = player.lastTickgetZ() + (player.getZ() - player.lastTickgetZ()) * partialticks;

				double c0 = clientPlayer.lastTickgetX() + (clientPlayer.getX() - clientPlayer.lastTickgetX()) * partialticks;
				double c1 = clientPlayer.lastTickgetY() + (clientPlayer.getY() - clientPlayer.lastTickgetY()) * partialticks;
				double c2 = clientPlayer.lastTickgetZ() + (clientPlayer.getZ() - clientPlayer.lastTickgetZ()) * partialticks;

				double xOffset = d0 - c0;
				double yOffset = d1 - c1;
				double zOffset = d2 - c2;

				float height = entity.height;
				float width = entity.width;
				float multiplier = height * width;

				entity.setPosition(c0, c1, c2);
				entity.rotationYaw = 0.0f;
				entity.prevRotationYaw = 0.0f;
				entity.setRotationYawHead(0.0f);

				GlStateManager.pushMatrix();
				GlStateManager.translate(xOffset, yOffset, zOffset);
				GlStateManager.scale(1, 1, 1);
				GlStateManager.rotate(rotation, 0, 1f, 0);
				GlStateManager.translate(-0.32, 1.37, 0);

				if (player.isSneaking())
					GlStateManager.translate(0, -0.3, 0);

				Minecraft.getInstance().getRenderManager().setRenderShadow(false);
				Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0.0f, false);
				Minecraft.getInstance().getRenderManager().setRenderShadow(true);

				GlStateManager.scale(1, 1, 1);
				GlStateManager.popMatrix();

			}
		}

	}

	@SubscribeEvent
	@SideOnly(Dist.CLIENT)
	public static void onModelBake(ModelBakeEvent event)
	{
		for (Item item : ItemHandler.entityEggList)
		{
			if (item instanceof ItemEntityEggAnimated)
			{
				IBakedModel model = event.getModelRegistry().getObject(new ModelResourceLocation("animania:fancy_egg", "inventory"));
				RenderAnimatedEgg.wrapperModel = new AnimatedEggModelWrapper(model);
				event.getModelRegistry().putObject(new ModelResourceLocation("animania:fancy_egg", "inventory"), RenderAnimatedEgg.wrapperModel);
			}
		}

		ManualResourceLoader.loadResources();
	}

	@SubscribeEvent
	@SideOnly(Dist.CLIENT)
	public static void onModelRegistryReady(ModelRegistryEvent event)
	{
		for (Item item : ItemHandler.entityEggList)
		{
			if (item instanceof ItemEntityEggAnimated)
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("animania:fancy_egg", "inventory"));
				item.setBlockEntityItemStackRenderer(new RenderAnimatedEgg());
			}
		}
	}
}
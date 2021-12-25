package com.animania.client.render.item;

import com.animania.api.interfaces.IChild;
import com.animania.client.models.item.AnimatedEggModelWrapper;
import com.animania.common.items.ItemEntityEggAnimated;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderAnimatedEgg extends TileEntityItemStackRenderer
{

	public static AnimatedEggModelWrapper wrapperModel;
	public static TransformType transformType;
	public static float renderTimer = 0;

	@Override
	public void renderByItem(ItemStack stack, float partialTicks)
	{
		PlayerEntity player = Minecraft.getMinecraft().player;
		Minecraft mc = Minecraft.getMinecraft();

		Entity entity = ItemEntityEggAnimated.getEntity(player.level, stack);

		if (entity != null)
		{
			entity.setPosition(-1, -1, -1);

			if (entity instanceof IChild)
			{
				((IChild) entity).setEntityAge(1.3f / ((IChild) entity).getSizeDividend());
			}

			float pbx = OpenGlHelper.lastBrightnessX;
			float pby = OpenGlHelper.lastBrightnessY;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);

			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();

			float height = entity.height;
			float width = entity.width;

			float size = width * height;

			GlStateManager.pushMatrix();

			switch (transformType)
			{
			case GROUND:
				GlStateManager.translate(0.5, 0, 0.5);
				if (height > width)
					GlStateManager.scale(1 / height, 1 / height, 1 / height);
				else if (width > height)
					GlStateManager.scale(1 / width, 1 / width, 1 / width);
				else
					GlStateManager.scale(1 / Math.sqrt(size), 1 / Math.sqrt(size), 1 / Math.sqrt(size));

				break;
			case FIXED:
				GlStateManager.rotate(90, 0.0f, 1.0f, 0f);
				GlStateManager.translate(-0.5, 0, 0.5);

				if (height > width)
					GlStateManager.scale(0.8 / height, 0.8 / height, 0.8 / height);
				else if (width > height)
					GlStateManager.scale(0.8 / width, 0.8 / width, 0.8 / width);
				else
					GlStateManager.scale(0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size));

				break;
			case GUI:
				GlStateManager.translate(0.5, 0.1, 0);
				if (height > width)
					GlStateManager.scale(0.8 / height, 0.8 / height, 0.8 / height);
				else if (width > height)
					GlStateManager.scale(0.8 / width, 0.8 / width, 0.8 / width);
				else
					GlStateManager.scale(0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size));

				if (AnimaniaConfig.gameRules.fancyEggsRotate)
					GlStateManager.rotate(360 * renderTimer, 0, 1f, 0);
				else
					GlStateManager.rotate(20, 0.5f, 1f, 0);

				break;
			case THIRD_PERSON_LEFT_HAND:
			case THIRD_PERSON_RIGHT_HAND:

				GlStateManager.rotate(180, 0, 1f, 0);
				GlStateManager.rotate(-90, 1f, 0, 0);
				GlStateManager.translate(-0.5, 0, 0.2);

				if (height > width)
					GlStateManager.scale(0.8 / height, 0.8 / height, 0.8 / height);
				else if (width > height)
					GlStateManager.scale(0.8 / width, 0.8 / width, 0.8 / width);
				else
					GlStateManager.scale(0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size));

				break;
			case FIRST_PERSON_RIGHT_HAND:
				GlStateManager.rotate(-90.0f, 0f, 1f, 0f);
				if (height > width)
					GlStateManager.scale(0.8 / height, 0.8 / height, 0.8 / height);
				else if (width > height)
					GlStateManager.scale(0.8 / width, 0.8 / width, 0.8 / width);
				else
					GlStateManager.scale(0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size));

				break;
			case FIRST_PERSON_LEFT_HAND:
				GlStateManager.rotate(90.0f, 0f, 1f, 0f);
				GlStateManager.translate(0, 0, 1);
				if (height > width)
					GlStateManager.scale(0.8 / height, 0.8 / height, 0.8 / height);
				else if (width > height)
					GlStateManager.scale(0.8 / width, 0.8 / width, 0.8 / width);
				else
					GlStateManager.scale(0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size), 0.8 / Math.sqrt(size));

			default:
				break;
			}

			this.renderEntityStatic(entity);

			// RenderHelper.enableStandardItemLighting();
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();

			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		}
	}

	@SideOnly(Dist.CLIENT)
	private void renderEntityStatic(Entity entity)
	{
		if (entity.ticksExisted == 0)
		{
			entity.lastTickgetX() = entity.getX();
			entity.lastTickgetY() = entity.getY();
			entity.lastTickgetZ() = entity.getZ();
		}

		float f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw);
		int i = this.getBrightnessForRender(entity, Minecraft.getMinecraft().player);

		if (entity.isBurning())
		{
			i = 15728880;
		}

		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (transformType == TransformType.GUI)
			this.setLightmapDisabled(true);
		else
			this.setLightmapDisabled(false);

		Minecraft.getMinecraft().getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, f, 0.0F, true);

	}

	@SideOnly(Dist.CLIENT)
	private int getBrightnessForRender(Entity entity, PlayerEntity player)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(player.getX()), 0, MathHelper.floor(player.getZ()));

		if (entity.level.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(player.getY() + entity.getEyeHeight()));
			return entity.level.getCombinedLight(blockpos$mutableblockpos, 0);
		}
		else
		{
			return 0;
		}
	}

	@SideOnly(Dist.CLIENT)
	private void setLightmapDisabled(boolean disabled)
	{
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);

		if (disabled)
		{
			GlStateManager.disableTexture2D();
		}
		else
		{
			GlStateManager.enableTexture2D();
		}

		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

}

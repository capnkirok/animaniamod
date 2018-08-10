package com.animania.client.render.item;

import java.util.List;

import javax.annotation.Nonnull;

import com.animania.client.models.item.AnimatedEggModelWrapper;
import com.animania.common.items.ItemEntityEggAnimated;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderAnimatedEgg extends TileEntityItemStackRenderer
{

	public static AnimatedEggModelWrapper wrapperModel;
	public static TransformType transformType;

	@Override
	public void renderByItem(ItemStack stack, float partialTicks)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		Minecraft mc = Minecraft.getMinecraft();

		Entity entity = ItemEntityEggAnimated.getEntity(player.world, stack);

		if (entity != null)
		{
			entity.setPosition(-1, -1, -1);

			float pbx = OpenGlHelper.lastBrightnessX;
			float pby = OpenGlHelper.lastBrightnessY;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);

			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();
			
			
			float height = entity.height;
			float width = entity.width;

			float size = width * height;
			float appliedScale = 1/ size;
			
			appliedScale = Math.min(appliedScale, 1.4f);
			appliedScale = Math.max(appliedScale, 0.65f);

			GlStateManager.scale(appliedScale, appliedScale, appliedScale);
			
			GlStateManager.translate(0.95 + -appliedScale * 0.42, -0.5F, 0.5F);

			GlStateManager.pushMatrix();

			switch (transformType)
			{
			case GROUND:
				break;
			case FIXED:
				GlStateManager.scale(0.6f, 0.6f, 0.6f);
				GlStateManager.rotate(90, 0.0f, 1.0f, 0f);
				GlStateManager.translate(0, 1, 0);
				break;
			case GUI:
				GlStateManager.scale((100 - size) * 0.006, (100 - size) * 0.006, (100 - size) * 0.006);
				GlStateManager.translate(0.0, height / 2 + -(height / 2) + 1.1, width - 0.1 < 0.7 ? width - 0.55 + (0.1 - (width - 0.0)) : width - 1.4);
				GlStateManager.rotate(30f, 0.5f, 1.0f, 0f);
				break;
			case THIRD_PERSON_LEFT_HAND:
			case THIRD_PERSON_RIGHT_HAND:
				GlStateManager.scale((100 - size) * 0.008, (100 - size) * 0.008, (100 - size) * 0.008);
				GlStateManager.translate(0.0, height / 2 + -(height / 2) + 0.8, width - 0.1 < 0.7 ? width - 0.55 + (0.1 - (width - 0.0)) : width - 1.4);
				GlStateManager.rotate(180, 0f, 1f, 0f);
				GlStateManager.rotate(-90, 1f, 0f, 0f);
				break;
			case FIRST_PERSON_RIGHT_HAND:
				GlStateManager.rotate(-90.0f, 0f, 1f, 0f);
				break;
			case FIRST_PERSON_LEFT_HAND:
				GlStateManager.rotate(90.0f, 0f, 1f, 0f);
			default:
				break;
			}

			renderEntityStatic(entity);

			RenderHelper.enableStandardItemLighting();
			GlStateManager.popMatrix();
			GlStateManager.popMatrix();

			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, pbx, pby);
		}
	}

	@SideOnly(Side.CLIENT)
	private void renderEntityStatic(Entity entity)
	{
		if (entity.ticksExisted == 0)
		{
			entity.lastTickPosX = entity.posX;
			entity.lastTickPosY = entity.posY;
			entity.lastTickPosZ = entity.posZ;
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
		this.setLightmapDisabled(true);

	}

	@SideOnly(Side.CLIENT)
	private int getBrightnessForRender(Entity entity, EntityPlayer player)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(player.posX), 0, MathHelper.floor(player.posZ));

		if (entity.world.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(player.posY + entity.getEyeHeight()));
			return entity.world.getCombinedLight(blockpos$mutableblockpos, 0);
		}
		else
		{
			return 0;
		}
	}

	@SideOnly(Side.CLIENT)
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

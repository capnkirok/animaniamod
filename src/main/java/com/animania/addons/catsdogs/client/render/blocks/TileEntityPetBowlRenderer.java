package com.animania.addons.catsdogs.client.render.blocks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.animania.addons.catsdogs.client.models.blocks.ModelPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl.BowlContent;
import com.animania.client.events.RenderEvents;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.vertex.BufferBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityPetBowlRenderer extends TileEntitySpecialRenderer<TileEntityPetBowl>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/pet_bowl.png");
	public static TileEntityPetBowlRenderer instance;
	private final ModelPetBowl pet_bowl = new ModelPetBowl();

	private static Map<TileEntityPetBowl, Color> cachedColors = new HashMap<TileEntityPetBowl, Color>();

	@Override
	public void render(TileEntityPetBowl te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(0.5, -1.5, -0.5);
		// GlStateManager.enableAlpha();
		// GlStateManager.enableBlend();
		GlStateManager.enableCull();

		this.bindTexture(TEXTURE);
		pet_bowl.render((Entity) null, partialTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);

		if (te.getBowlContent() == BowlContent.FOOD)
		{
			GlStateManager.pushMatrix();
			GlStateManager.enableCull();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1f, 1f, 1f, 1f);
			ItemStack item = te.itemHandler.getStackInSlot(0);

			if (RenderEvents.ticks % 20 == 0)
			{
				Color foodColor = getAverageColor(item);
				cachedColors.put(te, foodColor);
			}

			Color foodColor = cachedColors.get(te);
			if (foodColor == null)
			{
				foodColor = getAverageColor(item);
				cachedColors.put(te, foodColor);
			}

			float[] rgb = new float[3];
			foodColor.getRGBColorComponents(rgb);

			pet_bowl.setColor(rgb[0], rgb[1], rgb[2]);
			GlStateManager.scale(1.2, 1.2, 1.2);
			GlStateManager.translate(0, -0.12 - (item.getCount() - 1) * 0.04, 0);
			pet_bowl.renderFood(0.0625F);

			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.disableAlpha();

			GlStateManager.popMatrix();
		}

		GlStateManager.pushMatrix();
		if (te.getBowlContent() == BowlContent.LIQUID)
		{
			FluidStack fluid = te.fluidHandler.getFluid();
			if (fluid != null)
			{
				TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());
				ResourceLocation loc = new ResourceLocation(fluid.getFluid().getStill().getResourceDomain() + ":textures/" + fluid.getFluid().getStill().getResourcePath() + ".png");
				double multi = ((double) fluid.amount / (double) 1000);
				GlStateManager.pushMatrix();
				GlStateManager.translate(0.4, 1.405, -0.3);
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();

				GlStateManager.translate(0, -0.155 * multi, 0);

				GlStateManager.rotate(90, 1f, 0f, 0f);
				drawOctagon(-0.65, 0.05, sprite, 0.21);

				GlStateManager.popMatrix();
			}
		}
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityPetBowlRenderer.instance = this;
	}

	private static void drawOctagon(double x, double y, TextureAtlasSprite textureSprite, double len)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		double u = textureSprite.getMaxU() - textureSprite.getMinU();
		double v = textureSprite.getMaxV() - textureSprite.getMinV();

		double u1 = textureSprite.getMaxU();
		double v1 = textureSprite.getMaxV();

		double uLen = u * len;
		double vLen = v * len;

		double a = Math.sqrt((len * len) / 2);
		double b = Math.sqrt((uLen * uLen) / 2);
		double c = Math.sqrt((vLen * vLen) / 2);

		bufferbuilder.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION_TEX_NORMAL);
		bufferbuilder.pos(x + a, y, 0).tex(u1 - b, v1).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x + a + len, y, 0).tex(u1 - b - uLen, v1).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x + 2 * a + len, y + a, 0).tex(u1 - 2 * b - uLen, v1 - c).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x + 2 * a + len, y + a + len, 0).tex(u1 - 2 * b - uLen, v1 - c - vLen).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x + a + len, y + 2 * a + len, 0).tex(u1 - b - uLen, v1 - 2 * c - vLen).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x + a, y + 2 * a + len, 0).tex(u1 - b, v1 - 2 * c - vLen).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x, y + a + len, 0).tex(u1, v1 - c - vLen).normal(0, 0, 1).endVertex();
		bufferbuilder.pos(x, y + a, 0).tex(u1, v1 - c).normal(0, 0, 1).endVertex();
		Tessellator.getInstance().draw();

	}

	public Color getAverageColor(ItemStack item)
	{
		InputStream is;
		BufferedImage image;
		try
		{
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(item, null, null).getParticleTexture();
			ResourceLocation loc = new ResourceLocation(sprite.getIconName().replace(":", ":textures/") + ".png");

			is = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
			image = ImageIO.read(is);
		} catch (IOException e)
		{
			e.printStackTrace();
			return new Color(0, 0, 0);
		}

		int x1 = image.getWidth();
		int y1 = image.getHeight();

		double sumr = 0, sumg = 0, sumb = 0;
		for (int x = 0; x < x1; x++)
		{
			for (int y = 0; y < y1; y++)
			{
				Color pixel = new Color(image.getRGB(x, y), true);
				double alpha = pixel.getAlpha() / 255.0;
				sumr += pixel.getRed() * alpha;
				sumg += pixel.getGreen() * alpha;
				sumb += pixel.getBlue() * alpha;

			}
		}
		int num = image.getWidth() * image.getHeight();
		return new Color((int) sumr / num, (int) sumg / num, (int) sumb / num).brighter().brighter().brighter();
	}
}

package com.animania.client.render.tileEntity;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.animania.client.models.ModelTrough;
import com.animania.common.tileentities.TileEntityTrough;
import com.animania.common.tileentities.TileEntityTrough.TroughContent;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTroughRenderer extends TileEntitySpecialRenderer<TileEntityTrough>
{
	private static final ResourceLocation TROUGH_EMPTY_TEXTURE = new ResourceLocation("animania:textures/entity/tileentities/block_trough.png");
	private static final ResourceLocation WHEAT = new ResourceLocation("animania:textures/entity/tileentities/wheat.png");
	public static TileEntityTroughRenderer instance;
	private final ModelTrough trough = new ModelTrough();

	@Override
	public void render(TileEntityTrough te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		EnumFacing enumfacing = EnumFacing.getFront(te.getBlockMetadata() & 7);
		this.renderTrough(te, (float) x, (float) y, (float) z, enumfacing, destroyStage, partialTicks);
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityTroughRenderer.instance = this;
	}

	public void renderTrough(TileEntityTrough te, float x, float y, float z, EnumFacing facing, int destroyStage, float animateTicks)
	{
		ModelBase modelbase = this.trough;

		if (destroyStage >= 0)
		{
			this.bindTexture(TileEntitySpecialRenderer.DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		}
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		Float rot = 0.0F;

		if (facing == EnumFacing.UP)
			GlStateManager.translate(x, y, z);
		else
			switch (facing)
			{

			case NORTH:
				GlStateManager.translate(x + 1.5, y + 1.5F, z + .5);
				break;
			case SOUTH:
				GlStateManager.translate(x - .5, y + 1.5F, z + .5);
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				break;
			case WEST:
				GlStateManager.translate(x + .5, y + 1.5F, z - 0.5);
				GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
				break;
			case EAST:
			default:
				GlStateManager.translate(x + .5, y + 1.5F, z + 1.5);
				GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);

			}

		float f = 0.0625F;
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();
		GlStateManager.enableCull();

		this.bindTexture(TileEntityTroughRenderer.TROUGH_EMPTY_TEXTURE);
		modelbase.render((Entity) null, animateTicks, 0.0F, 0.0F, 0, 0.0F, 0.0625F);

		GlStateManager.pushMatrix();
		if (te.getTroughContent() == TroughContent.LIQUID)
		{
			FluidStack fluid = te.fluidHandler.getFluid();
			if (fluid != null)
			{
				TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());
				ResourceLocation loc = new ResourceLocation(fluid.getFluid().getStill().getResourceDomain() + ":textures/" + fluid.getFluid().getStill().getResourcePath() + ".png");
				double multi = 0.34 * (1 - ((double) fluid.amount / (double) 1000));
				GlStateManager.translate(0.0, multi, 0.0);
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();
				this.bindTexture(loc);
				trough.renderFluid(0.0625f, sprite.getIconWidth(), sprite.getIconHeight() * sprite.getFrameCount());
			}

		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		if (te.getTroughContent() == TroughContent.FOOD)
		{
			ItemStack stack = te.itemHandler.getStackInSlot(0);
			if (!stack.isEmpty())
			{
				this.bindTexture(TROUGH_EMPTY_TEXTURE);
				if (stack.getItem() == Items.WHEAT)
					trough.renderFeed(stack.getCount(), new Color(160, 124, 89));
				else
					trough.renderFeed(stack.getCount(), getAverageColor(stack));

				TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, null).getParticleTexture();
				if (stack.getItem() == Items.WHEAT)
					this.bindTexture(WHEAT);
				else
					this.bindTexture(new ResourceLocation(sprite.getIconName().replace(":", ":textures/") + ".png"));

				trough.renderFood(0.0625F, stack.getCount());
			}
		}
		GlStateManager.popMatrix();

		GlStateManager.popMatrix();

		if (destroyStage >= 0)
		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	/**
	 * Code by SuperKael on minecraftforge.net, updated by Tschipp
	 * 
	 * @param item
	 * @return
	 */
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
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new Color(0, 0, 0);
		}
		int[] texture = new int[image.getWidth() * image.getHeight() * 4];
		texture = image.getRaster().getPixels(image.getRaster().getMinX(), image.getRaster().getMinY(), image.getRaster().getWidth(), image.getRaster().getHeight(), texture);
		int r = 0, g = 0, b = 0;
		int rloops = 0, gloops = 0, bloops = 0;
		for (int i = 0; i < texture.length; i++)
		{
			try
			{
				if (((float) i / 4) * 4 == i && texture[i + 3] >= 255)
				{
					r += texture[i];
					rloops++;
				}
				if (((float) (i - 1) / 4) * 4 == i - 1 && texture[i + 2] >= 255)
				{
					g += texture[i];
					gloops++;
				}
				if (((float) (i - 2) / 4) * 4 == i - 2 && texture[i + 1] >= 255)
				{
					b += texture[i];
					bloops++;
				}
			}
			catch (Exception e)
			{
			}
		}
		try
		{
			r /= rloops;
			g /= gloops;
			b /= bloops;
		}
		catch (Exception e)
		{
		}
		return new Color(r, g, b);
	}
}
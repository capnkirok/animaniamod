package com.animania.client.render.pigs;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.client.models.ModelSow;
import com.animania.common.entities.pigs.EntitySowLargeWhite;

@SideOnly(Side.CLIENT)
public class LayerMudSowLargeWhite implements LayerRenderer<EntitySowLargeWhite>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/pigs/pig_muddy.png");
	private final RenderSowLargeWhite pigRenderer;
	private final ModelSow pigModel = new ModelSow(0.5F);

	public LayerMudSowLargeWhite(RenderSowLargeWhite pigRendererIn)
	{
		this.pigRenderer = pigRendererIn;
	}

	public void doRenderLayer(EntitySowLargeWhite entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		if (entitylivingbaseIn.getMuddy()) {

			float splashTimer = entitylivingbaseIn.getSplashTimer();

			if (splashTimer <= 0) {
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				this.pigRenderer.bindTexture(TEXTURE);
				GlStateManager.enableBlend();
				this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				GlStateManager.depthMask(true);
				GlStateManager.disableBlend();
			}
		} else if (entitylivingbaseIn.getMudTimer() > 0 ) {

			float mudTimer = entitylivingbaseIn.getMudTimer();
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			this.pigRenderer.bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
			this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.disableBlend();

		}
	}

	public boolean shouldCombineTextures()
	{
		return true;
	}
}
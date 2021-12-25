package com.animania.addons.farm.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.pig.ModelSow;
import com.animania.addons.farm.client.render.pigs.RenderPigletOldSpot;
import com.animania.addons.farm.common.entity.pigs.PigOldSpot.PigEntityletOldSpot;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class LayerMudPigletOldSpot implements LayerRenderer<PigEntityletOldSpot>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/pigs/piglet_muddy.png");
	private final RenderPigletOldSpot pigRenderer;
	private final ModelSow pigModel = new ModelSow(0.5F);

	public LayerMudPigletOldSpot(RenderPigletOldSpot pigRendererIn)
	{
		this.pigRenderer = pigRendererIn;
	}

	@Override
	public void doRenderLayer(PigEntityletOldSpot LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		if (LivingEntityIn.getMuddy())
		{

			float splashTimer = LivingEntityIn.getSplashTimer();

			if (splashTimer <= 0)
			{
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				this.pigRenderer.bindTexture(LayerMudPigletOldSpot.TEXTURE);
				GlStateManager.enableBlend();
				this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				GlStateManager.depthMask(true);
				GlStateManager.disableBlend();
			}
		}
		else if (LivingEntityIn.getMudTimer() > 0)
		{

			float mudTimer = LivingEntityIn.getMudTimer();
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			this.pigRenderer.bindTexture(LayerMudPigletOldSpot.TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
			this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.disableBlend();

		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}
}
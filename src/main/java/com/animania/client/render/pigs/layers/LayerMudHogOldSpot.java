package com.animania.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHog;
import com.animania.client.render.pigs.RenderHogOldSpot;
import com.animania.common.entities.pigs.PigOldSpot.EntityHogOldSpot;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerMudHogOldSpot implements LayerRenderer<EntityHogOldSpot>
{
    private static final ResourceLocation TEXTURE  = new ResourceLocation("animania:textures/entity/pigs/pig_muddy.png");
    private final RenderHogOldSpot        pigRenderer;
    private final ModelHog                pigModel = new ModelHog(0.5F);

    public LayerMudHogOldSpot(RenderHogOldSpot pigRendererIn) {
        this.pigRenderer = pigRendererIn;
    }

    @Override
    public void doRenderLayer(EntityHogOldSpot entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {

        if (entitylivingbaseIn.getMuddy()) {

            float splashTimer = entitylivingbaseIn.getSplashTimer();

            if (splashTimer <= 0) {
                GL11.glScalef(1.01F, 1.01F, 1.01F);
                this.pigRenderer.bindTexture(LayerMudHogOldSpot.TEXTURE);
                GlStateManager.enableBlend();
                this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();
            }
        }
        else if (entitylivingbaseIn.getMudTimer() > 0) {

            float mudTimer = entitylivingbaseIn.getMudTimer();
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.pigRenderer.bindTexture(LayerMudHogOldSpot.TEXTURE);
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
            this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
package com.animania.addons.farm.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.pig.ModelSow;
import com.animania.addons.farm.client.render.pigs.RenderSowDuroc;
import com.animania.addons.farm.common.entity.pigs.PigDuroc.EntitySowDuroc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class LayerMudSowDuroc implements LayerRenderer<EntitySowDuroc>
{
    private static final ResourceLocation TEXTURE  = new ResourceLocation("animania:textures/entity/pigs/pig_muddy.png");
    private final RenderSowDuroc          pigRenderer;
    private final ModelSow                pigModel = new ModelSow(0.5F);

    public LayerMudSowDuroc(RenderSowDuroc pigRendererIn) {
        this.pigRenderer = pigRendererIn;
    }

    @Override
    public void doRenderLayer(EntitySowDuroc LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {

        if (LivingEntityIn.getMuddy()) {

            float splashTimer = LivingEntityIn.getSplashTimer();

            if (splashTimer <= 0) {
                GL11.glScalef(1.01F, 1.01F, 1.01F);
                this.pigRenderer.bindTexture(LayerMudSowDuroc.TEXTURE);
                GlStateManager.enableBlend();
                this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();
            }
        }
        else if (LivingEntityIn.getMudTimer() > 0) {

            float mudTimer = LivingEntityIn.getMudTimer();
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.pigRenderer.bindTexture(LayerMudSowDuroc.TEXTURE);
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
            this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
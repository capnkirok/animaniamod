package com.animania.addons.farm.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.pig.ModelPiglet;
import com.animania.addons.farm.client.render.pigs.RenderPigletYorkshire;
import com.animania.addons.farm.common.entity.pigs.PigYorkshire.PigEntityletYorkshire;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class LayerMudPigletYorkshire implements LayerRenderer<PigEntityletYorkshire>
{
    private static final ResourceLocation TEXTURE  = new ResourceLocation("animania:textures/entity/pigs/piglet_muddy.png");
    private final RenderPigletYorkshire   pigRenderer;
    private final ModelPiglet             pigModel = new ModelPiglet(0.5F);

    public LayerMudPigletYorkshire(RenderPigletYorkshire pigRendererIn) {
        this.pigRenderer = pigRendererIn;
    }

    @Override
    public void doRenderLayer(PigEntityletYorkshire LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {

        if (LivingEntityIn.getMuddy()) {

            float splashTimer = LivingEntityIn.getSplashTimer();

            if (splashTimer <= 0) {
                GL11.glScalef(1.01F, 1.01F, 1.01F);
                this.pigRenderer.bindTexture(LayerMudPigletYorkshire.TEXTURE);
                GlStateManager.enableBlend();
                this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();
            }
        }
        else if (LivingEntityIn.getMudTimer() > 0) {

            float mudTimer = LivingEntityIn.getMudTimer();
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.pigRenderer.bindTexture(LayerMudPigletYorkshire.TEXTURE);
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
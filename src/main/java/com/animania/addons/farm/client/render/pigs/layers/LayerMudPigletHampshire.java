package com.animania.addons.farm.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.pig.ModelPigletHampshire;
import com.animania.addons.farm.client.render.pigs.RenderPigletHampshire;
import com.animania.addons.farm.common.entity.pigs.PigHampshire.PigEntityletHampshire;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class LayerMudPigletHampshire implements LayerRenderer<PigEntityletHampshire>
{
    private static final ResourceLocation TEXTURE  = new ResourceLocation("animania:textures/entity/pigs/piglet_muddy.png");
    private final RenderPigletHampshire   pigRenderer;
    private final ModelPigletHampshire    pigModel = new ModelPigletHampshire(0.5F);

    public LayerMudPigletHampshire(RenderPigletHampshire renderPigletHampshire) {
        this.pigRenderer = renderPigletHampshire;
    }

    @Override
    public void doRenderLayer(PigEntityletHampshire LivingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {

        if (LivingEntityIn.getMuddy()) {

            float splashTimer = LivingEntityIn.getSplashTimer();

            if (splashTimer <= 0) {
                GL11.glScalef(1.01F, 1.01F, 1.01F);
                this.pigRenderer.bindTexture(LayerMudPigletHampshire.TEXTURE);
                GlStateManager.enableBlend();
                this.pigRenderer.getMainModel().render(LivingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();
            }
        }
        else if (LivingEntityIn.getMudTimer() > 0) {

            float mudTimer = LivingEntityIn.getMudTimer();
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.pigRenderer.bindTexture(LayerMudPigletHampshire.TEXTURE);
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
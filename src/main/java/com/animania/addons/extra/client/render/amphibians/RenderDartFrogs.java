package com.animania.addons.extra.client.render.amphibians;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.amphibians.ModelFrog;
import com.animania.addons.extra.common.entity.amphibians.EntityDartFrogs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderDartFrogs<T extends EntityDartFrogs> extends RenderLiving<T>
{// RenderPlayer
    public static final Factory             FACTORY        = new Factory();

    // Need to move in main class
    private static final String             modid          = "animania", frogsBaseDir = "textures/entity/amphibians/dartfrogs/";
    private static final ResourceLocation[] FROGS_TEXTURES = new ResourceLocation[] {
            new ResourceLocation(RenderDartFrogs.modid, RenderDartFrogs.frogsBaseDir + "red_dart_frog.png"),
            new ResourceLocation(RenderDartFrogs.modid, RenderDartFrogs.frogsBaseDir + "blue_dart_frog.png"),
            new ResourceLocation(RenderDartFrogs.modid, RenderDartFrogs.frogsBaseDir + "yellow_dart_frog.png") };

    public RenderDartFrogs(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelFrog(), 0.05F);
    }

    /**
     * Allows the render to do state modifications necessary before the model is
     * rendered.
     */
    @Override
    protected void preRenderCallback(T entityIn, float partialTickTime) {

        GlStateManager.scale(0.2D, 0.2D, 0.2D);
        float f1 = 1.2F;
        float f2 = (entityIn.prevSquishFactor + (entityIn.squishFactor - entityIn.prevSquishFactor) * partialTickTime) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);

    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        switch (entity.getFrogsType()) {
            case 0:
            default:
                return RenderDartFrogs.FROGS_TEXTURES[0];
            case 1:
                return RenderDartFrogs.FROGS_TEXTURES[1];
            case 2:
                return RenderDartFrogs.FROGS_TEXTURES[2];

        }
    }

    public static class Factory<T extends EntityDartFrogs> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderDartFrogs(manager);
        }
    }
}

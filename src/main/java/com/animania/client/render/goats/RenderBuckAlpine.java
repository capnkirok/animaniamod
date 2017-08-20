package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBullAngus;
import com.animania.client.models.goats.ModelBuckAlpine;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.goats.EntityBuckAlpine;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuckAlpine<T extends EntityBuckAlpine> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/buck_alpine.png");
    private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/buck_alpine_blink.png");
    Random                                rand             = new Random();

    public RenderBuckAlpine(RenderManager rm) {
        super(rm, new ModelBuckAlpine(), 0.5F);
    }

    protected ResourceLocation getGoatTextures(T par1EntityCow) {
        return RenderBuckAlpine.goatTextures;
    }

    protected ResourceLocation getGoatTexturesBlink(T par1EntityCow) {
        return RenderBuckAlpine.goatTexturesBlink;
    }

    protected void preRenderScale(EntityBuckAlpine entity, float f) {
        GL11.glScalef(0.67F, 0.67F, 0.67F);
        GL11.glTranslatef(0f, 0f, -0.5f);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }
    

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        int blinkTimer = entity.blinkTimer;

        if (blinkTimer < 7 && blinkTimer >= 0)
            return this.getGoatTexturesBlink(entity);
        else
            return this.getGoatTextures(entity);
    }

    static class Factory<T extends EntityBuckAlpine> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderBuckAlpine(manager);
        }

    }
}

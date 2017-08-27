package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBullAngus;
import com.animania.client.models.goats.ModelBuckNigerianDwarf;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.goats.EntityBuckNigerianDwarf;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuckNigerianDwarf<T extends EntityBuckNigerianDwarf> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/buck_nigerian.png");
    private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/buck_nigerian_blink.png");
    Random                                rand             = new Random();

    public RenderBuckNigerianDwarf(RenderManager rm) {
        super(rm, new ModelBuckNigerianDwarf(), 0.2F);
    }

    protected ResourceLocation getGoatTextures(T par1EntityCow) {
        return RenderBuckNigerianDwarf.goatTextures;
    }

    protected ResourceLocation getGoatTexturesBlink(T par1EntityCow) {
        return RenderBuckNigerianDwarf.goatTexturesBlink;
    }

    protected void preRenderScale(EntityBuckNigerianDwarf entity, float f) {
        GL11.glScalef(0.45F, 0.45F, 0.45F);
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

    static class Factory<T extends EntityBuckNigerianDwarf> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderBuckNigerianDwarf(manager);
        }

    }
}

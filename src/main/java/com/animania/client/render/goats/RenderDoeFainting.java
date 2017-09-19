package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.goats.ModelDoeFainting;
import com.animania.common.entities.goats.EntityDoeFainting;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoeFainting<T extends EntityDoeFainting> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/doe_fainting.png");
    private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/doe_fainting_blink.png");
    Random                                rand             = new Random();

    public RenderDoeFainting(RenderManager rm) {
        super(rm, new ModelDoeFainting(), 0.3F);
    }

    protected ResourceLocation getGoatTextures(T par1Entity) {
        return RenderDoeFainting.goatTextures;
    }

    protected ResourceLocation getGoatTexturesBlink(T par1Entity) {
        return RenderDoeFainting.goatTexturesBlink;
    }

    protected void preRenderScale(EntityDoeFainting entity, float f) {
        GL11.glScalef(0.4F, 0.4F, 0.4F);
        if (entity.getSpooked() && entity.getSpookedTimer() < 0.94F && entity.getSpookedTimer() > 0.02F)  {
			GlStateManager.translate(0.0F, entity.height - 1.5F, 0.0F);
			GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
			this.renderManager.setRenderShadow(false);
		} else {
			GL11.glTranslatef(0f, 0f, -0.5f);
			this.renderManager.setRenderShadow(true);
		}
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

    static class Factory<T extends EntityDoeFainting> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderDoeFainting(manager);
        }

    }
}

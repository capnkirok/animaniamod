package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.goats.ModelKidKinder;
import com.animania.common.entities.goats.EntityKidKinder;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKidKinder<T extends EntityKidKinder> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/kid_kinder.png");
    private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/kid_kinder_blink.png");
    Random                                rand             = new Random();

    public RenderKidKinder(RenderManager rm) {
        super(rm, new ModelKidKinder(), 0.5F);
    }

    protected ResourceLocation getGoatTextures(T par1Entity) {
        return RenderKidKinder.goatTextures;
    }

    protected ResourceLocation getGoatTexturesBlink(T par1Entity) {
        return RenderKidKinder.goatTexturesBlink;
    }

    protected void preRenderScale(EntityKidKinder entity, float f) {
    	float age = entity.getEntityAge();
		GL11.glScalef(0.27F + age, 0.27F + age, 0.27F + age); 
        GL11.glTranslatef(0f, 0f, -0.5f);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }
    

    /**
     * Returns the location of an entity's texture. kidsn't seem to be called
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

    static class Factory<T extends EntityKidKinder> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderKidKinder(manager);
        }

    }
}

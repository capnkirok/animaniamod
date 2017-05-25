package com.animania.client.render.peacocks;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPeachick;
import com.animania.common.entities.peacocks.EntityPeachickBlue;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPeachickBlue<T extends EntityPeachickBlue> extends RenderLiving<T>
{
    public static final Factory FACTORY = new Factory();

    public RenderPeachickBlue(RenderManager rm) {
        super(rm, new ModelPeachick(), 0.15F);
    }

    @Override
    protected float handleRotationFloat(T livingBase, float partialTicks) {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    protected void preRenderScale(T entity, float f) {
        float age = entity.getEntityAge();
        GL11.glScalef(0.3F + age, 0.3F + age, 0.3F + age);

    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        int blinkTimer = entity.blinkTimer;

        if (blinkTimer < 5 && blinkTimer >= 0)
            return entity.getResourceLocationBlink();
        else
            return entity.getResourceLocation();
    }

    static class Factory<T extends EntityPeachickBlue> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderPeachickBlue(manager);
        }
    }

}
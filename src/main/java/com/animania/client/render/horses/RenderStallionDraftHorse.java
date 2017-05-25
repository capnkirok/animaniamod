package com.animania.client.render.horses;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelStallionDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStallionDraftHorse<T extends EntityStallionDraftHorse> extends RenderLiving<T>
{
    public static final Factory           FACTORY       = new Factory();
    private static final ResourceLocation horseTextures = new ResourceLocation("animania:textures/entity/horses/draft_horse_black.png");

    public RenderStallionDraftHorse(RenderManager rm) {
        super(rm, new ModelStallionDraftHorse(), 0.5F);
    }

    protected void preRenderScale(T entity, float f) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }

    protected ResourceLocation getHorseTextures(T par1EntityCow) {
        return RenderStallionDraftHorse.horseTextures;
    }

    @Override
    protected ResourceLocation getEntityTexture(T par1Entity) {
        return this.getHorseTextures(par1Entity);
    }

    public static class Factory<T extends EntityStallionDraftHorse> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderStallionDraftHorse(manager);
        }
    }
}

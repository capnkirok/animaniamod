package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.goats.ModelDoeAngora;
import com.animania.common.entities.goats.EntityDoeAngora;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoeAngora<T extends EntityDoeAngora> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation goatTextures      = new ResourceLocation("animania:textures/entity/goats/Doe_angora.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/Doe_angora_blink.png");
	private static final ResourceLocation goatTexturesSheared      = new ResourceLocation("animania:textures/entity/goats/Doe_angora_sheared.png");
	private static final ResourceLocation goatTexturesShearedBlink = new ResourceLocation("animania:textures/entity/goats/Doe_angora_sheared_blink.png");
    Random                                rand             = new Random();

    public RenderDoeAngora(RenderManager rm) {
        super(rm, new ModelDoeAngora(), 0.5F);
    }

    protected ResourceLocation getGoatTextures(T entity) {
		if (entity.getSheared()) {
			return RenderDoeAngora.goatTexturesSheared;
		} else {
			return RenderDoeAngora.goatTextures;
		}
	}

	protected ResourceLocation getGoatTexturesBlink(T entity) {
		if (entity.getSheared()) {
			return RenderDoeAngora.goatTexturesShearedBlink;
		} else {
			return RenderDoeAngora.goatTexturesBlink;
		}
	}

    protected void preRenderScale(EntityDoeAngora entity, float f) {
        GL11.glScalef(0.58F, 0.58F, 0.58F);
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

    static class Factory<T extends EntityDoeAngora> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderDoeAngora(manager);
        }

    }
}

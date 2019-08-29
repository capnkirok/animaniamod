package com.animania.client.render.goats;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.goats.ModelBuckAlpine;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.goats.GoatAlpine.EntityBuckAlpine;

import net.minecraft.client.renderer.GlStateManager;
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
    private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/goats_blink.png");

    public RenderBuckAlpine(RenderManager rm) {
        super(rm, new ModelBuckAlpine(), 0.5F);
        this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0x83786D));
    }

    protected ResourceLocation getGoatTextures(T par1EntityGoat) {
        return RenderBuckAlpine.goatTextures;
    }

    protected ResourceLocation getGoatTexturesBlink(T par1EntityGoat) {
        return RenderBuckAlpine.goatTexturesBlink;
    }

    protected void preRenderScale(EntityBuckAlpine entity, float f) {
        GL11.glScalef(0.67F, 0.67F, 0.67F);
        GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaGoat entityGoat = (EntityAnimaniaGoat) entity;
		if (entityGoat.getSleeping()) {
			this.shadowSize = 0;
			float sleepTimer = entityGoat.getSleepTimer();
			if (sleepTimer > - 0.55F) {
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else {
			this.shadowSize = 0.5F;
			entityGoat.setSleeping(false);
			entityGoat.setSleepTimer(0F);
		}
	}

		@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getGoatTextures(entity);
	}

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }
 
    static class Factory<T extends EntityBuckAlpine> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderBuckAlpine(manager);
        }

    }
}

package com.animania.addons.catsdogs.client.render.cats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.catsdogs.client.models.cats.ModelRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityAnimaniaCat;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomRagdoll;
import com.animania.client.render.layer.LayerBlinking;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTomRagdoll<T extends EntityTomRagdoll> extends RenderLiving<T>
{
    public static final Factory           FACTORY          = new Factory();
    private static final ResourceLocation catTextures      = new ResourceLocation("animania:textures/entity/cats/ragdoll.png");
    private static final ResourceLocation catTexturesBlink = new ResourceLocation("animania:textures/entity/cats/ragdoll.png");

    public RenderTomRagdoll(RenderManager rm) {
        super(rm, new ModelRagdoll(), 0.5F);
        this.addLayer(new LayerBlinking(this, catTexturesBlink, 0x83786D));
    }

    protected ResourceLocation getCatTextures(T par1Entitycat) {
        return RenderTomRagdoll.catTextures;
    }

    protected ResourceLocation getcatTexturesBlink(T par1Entitycat) {
        return RenderTomRagdoll.catTexturesBlink;
    }

    protected void preRenderScale(EntityTomRagdoll entity, float f) {
        GL11.glScalef(0.67F, 0.67F, 0.67F);
        GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaCat entityCat = (EntityAnimaniaCat) entity;
		if (entityCat.getSleeping()) {
			this.shadowSize = 0;
			float sleepTimer = entityCat.getSleepTimer();
			if (sleepTimer > - 0.55F) {
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else {
			this.shadowSize = 0.5F;
			entityCat.setSleeping(false);
			entityCat.setSleepTimer(0F);
		}
	}

		@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCatTextures(entity);
	}

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        this.preRenderScale(entityliving, f);
    }
 
    static class Factory<T extends EntityTomRagdoll> implements IRenderFactory<T>
    {
        @Override
        public Render<? super T> createRenderFor(RenderManager manager) {
            return new RenderTomRagdoll(manager);
        }

    }
}

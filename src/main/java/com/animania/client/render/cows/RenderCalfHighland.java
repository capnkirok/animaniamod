package com.animania.client.render.cows;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelCalfLonghorn;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityCalfHighland;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCalfHighland<T extends EntityCalfHighland> extends RenderLiving<T>
{
	public static final Factory           FACTORY          = new Factory();
	private static final ResourceLocation cowTextures      = new ResourceLocation("animania:textures/entity/cows/calf_highland.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/calf_blink.png");

	public RenderCalfHighland(RenderManager rm) {
		super(rm, new ModelCalfLonghorn(), 0.5F);
		addLayer(new LayerBlinking(this, cowTexturesBlink, 0x5B2F1B));
	}

	protected ResourceLocation getCowTextures(T par1EntityCow) {
		return RenderCalfHighland.cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(T par1EntityCow) {
		return RenderCalfHighland.cowTexturesBlink;
	}

	@Override
	protected void preRenderCallback(T entityliving, float f) {
		this.preRenderScale(entityliving, f);
	}

	protected void preRenderScale(T entity, float f) {
		float age = entity.getEntityAge();
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age);

		EntityAnimaniaCow entityCow = (EntityAnimaniaCow) entity;
		if (entityCow.getSleeping()) {
			float sleepTimer = entityCow.getSleepTimer();
			if (sleepTimer > - 0.55F) {
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.95F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		} else {
			entityCow.setSleeping(false);
			entityCow.setSleepTimer(0F);
		}
	}


	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);
	}


	static class Factory<T extends EntityCalfHighland> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager) {
			return new RenderCalfHighland(manager);
		}

	}
}
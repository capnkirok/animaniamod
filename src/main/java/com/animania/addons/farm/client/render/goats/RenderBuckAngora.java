package com.animania.addons.farm.client.render.goats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.goats.ModelBuckAngora;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderBuckAngora<T extends EntityBuckAngora> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation goatTextures = new ResourceLocation("animania:textures/entity/goats/buck_angora.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");
	private static final ResourceLocation goatTexturesSheared = new ResourceLocation("animania:textures/entity/goats/buck_angora_sheared.png");
	private static final ResourceLocation goatTexturesShearedBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");

	public RenderBuckAngora(RenderManager rm)
	{
		super(rm, new ModelBuckAngora(), 0.5F);
		this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0xCAC4B7));
	}

	protected ResourceLocation getGoatTextures(T entity)
	{
		if (entity.getSheared())
		{
			return RenderBuckAngora.goatTexturesSheared;
		}
		else
		{
			return RenderBuckAngora.goatTextures;
		}
	}

	protected ResourceLocation getGoatTexturesBlink(T entity)
	{
		if (entity.getSheared())
		{
			return RenderBuckAngora.goatTexturesShearedBlink;
		}
		else
		{
			return RenderBuckAngora.goatTexturesBlink;
		}
	}

	protected void preRenderScale(EntityBuckAngora entity, float f)
	{
		GL11.glScalef(0.64F, 0.64F, 0.64F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaGoat entityGoat = (EntityAnimaniaGoat) entity;
		if (entityGoat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityGoat.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
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
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	static class Factory<T extends EntityBuckAngora> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBuckAngora(manager);
		}

	}
}

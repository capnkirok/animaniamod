package com.animania.addons.farm.client.render.goats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.goats.ModelDoeAngora;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
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
public class RenderDoeAngora<T extends EntityDoeAngora> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation goatTextures = new ResourceLocation("animania:textures/entity/goats/buck_angora.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");
	private static final ResourceLocation goatTexturesSheared = new ResourceLocation("animania:textures/entity/goats/buck_angora_sheared.png");
	private static final ResourceLocation goatTexturesShearedBlink = new ResourceLocation("animania:textures/entity/goats/goats_blink.png");

	public RenderDoeAngora(RenderManager rm)
	{
		super(rm, new ModelDoeAngora(), 0.5F);
		this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0xCAC4B7));
	}

	protected ResourceLocation getGoatTextures(T entity)
	{
		if (entity.getSheared())
		{
			return RenderDoeAngora.goatTexturesSheared;
		}
		else
		{
			return RenderDoeAngora.goatTextures;
		}
	}

	protected ResourceLocation getGoatTexturesBlink(T entity)
	{
		if (entity.getSheared())
		{
			return RenderDoeAngora.goatTexturesShearedBlink;
		}
		else
		{
			return RenderDoeAngora.goatTexturesBlink;
		}
	}

	protected void preRenderScale(EntityDoeAngora entity, float f)
	{
		GL11.glScalef(0.58F, 0.58F, 0.58F);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaGoat entityGoat = entity;
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

	static class Factory<T extends EntityDoeAngora> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderDoeAngora(manager);
		}

	}
}

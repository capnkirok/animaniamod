package com.animania.client.render.goats;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.goats.ModelBuckAngora;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.goats.EntityBuckAngora;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBuckAngora<T extends EntityBuckAngora> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation goatTextures = new ResourceLocation("animania:textures/entity/goats/buck_angora.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");
	private static final ResourceLocation goatTexturesSheared = new ResourceLocation("animania:textures/entity/goats/buck_angora_sheared.png");
	private static final ResourceLocation goatTexturesShearedBlink = new ResourceLocation("animania:textures/entity/goats/angora_blink.png");
	Random rand = new Random();

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
		boolean isSleeping = false;
		EntityAnimaniaGoat entityGoat = (EntityAnimaniaGoat) entity;
		if (entityGoat.getSleeping())
		{
			isSleeping = true;
		}

		if (isSleeping)
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
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
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

package com.animania.addons.catsdogs.client.render.cats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.catsdogs.client.models.cats.ModelCatRagdoll;
import com.animania.addons.catsdogs.common.entity.cats.EntityAnimaniaCat;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCatGeneric<T extends EntityAnimaniaCat> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	private final ResourceLocation blink;
	private final int eyeColor;
	private final float scale;

	public RenderCatGeneric(RenderManager rm, ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeColor, float scale)
	{
		super(rm, model, 0.5F);
		this.texture = texture;
		this.blink = blink;
		this.eyeColor = eyeColor;
		this.scale = scale;

		this.addLayer(new LayerBlinking(this, blink, eyeColor));
	}

	protected void preRenderScale(EntityAnimaniaCat entity, float f)
	{
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(0f, 0f, -0.5f);
		EntityAnimaniaCat entityCat = (EntityAnimaniaCat) entity;
		if (entityCat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityCat.getSleepTimer();
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
			entityCat.setSleeping(false);
			entityCat.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return texture;
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
	}

	public static class Factory<T extends EntityAnimaniaCat> implements IRenderFactory<T>
	{
		ResourceLocation tex;
		ResourceLocation blink;
		int eye;
		float scale;
		ModelBase model;
		
		public Factory(ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeCol, float scale)
		{
			this.tex = texture;
			this.blink = blink;
			this.eye = eyeCol;
			this.scale = scale;
			this.model = model;
		}

		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderCatGeneric(manager, model, tex, blink, eye, scale);
		}

	}
}

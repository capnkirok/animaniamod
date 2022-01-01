package com.animania.catsdogs.client.render.cats;

import com.animania.api.interfaces.IChild;
import com.animania.catsdogs.common.entity.felids.EntityAnimaniaCat;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderCatGeneric<T extends EntityAnimaniaCat> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	private final ResourceLocation blink;
	private final int eyeColor;
	private final float scale;

	public RenderCatGeneric(RenderManager rm, ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeColor, float scale)
	{
		super(rm, model, 0.2F);
		this.texture = texture;
		this.blink = blink;
		this.eyeColor = eyeColor;
		this.scale = scale;

		this.addLayer(new LayerBlinking(this, blink, eyeColor));
	}

	protected void preRenderScale(EntityAnimaniaCat entity, float f)
	{
		if (entity instanceof IChild child)
		{
			float age = child.getEntityAge();
			double dividend = 0.85 / (0.8 * this.scale);
			GlStateManager.scale(this.scale + age / dividend, this.scale + age / dividend, this.scale + age / dividend);
			this.shadowSize = (this.scale + age) / 2;
		}
		else
		{
			GlStateManager.scale(this.scale, this.scale, this.scale);
			this.shadowSize = this.scale / 2;
		}

		EntityAnimaniaCat entityCat = entity;
		if (entityCat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityCat.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 2F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translate(0, 0.6, 0);

			if (entity instanceof IChild)
				GlStateManager.translate(0, 0.4, 0);

		}
		else
		{
			entityCat.setSleeping(false);
			entityCat.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.texture;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
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
			return new RenderCatGeneric(manager, this.model, this.tex, this.blink, this.eye, this.scale);
		}

	}
}

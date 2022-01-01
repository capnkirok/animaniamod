package com.animania.catsdogs.client.render.dogs;

import com.animania.api.interfaces.IChild;
import com.animania.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Function;

@SideOnly(Dist.CLIENT)
public class RenderDogGeneric<T extends EntityAnimaniaDog> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	private final ResourceLocation blink;
	private final int eyeColor;
	private final float scale;
	private final LayerBlinking blinking;
	private final double x, y, z;

	private final Function<EntityAnimaniaDog, ResourceLocation> textureOverrideFunction;

	public RenderDogGeneric(RenderManager rm, ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeColor, float scale, double x, double y, double z, Function<EntityAnimaniaDog, ResourceLocation> textureOverride)
	{
		super(rm, model, 0.5F);
		this.texture = texture;
		this.blink = blink;
		this.eyeColor = eyeColor;
		this.scale = scale;
		this.x = x;
		this.y = y;
		this.z = z;
		this.textureOverrideFunction = textureOverride;

		this.addLayer(this.blinking = new LayerBlinking(this, blink, eyeColor, true));
	}

	protected void preRenderScale(EntityAnimaniaDog entity, float f)
	{
		GlStateManager.translate(this.x, this.y, this.z);

		if (entity instanceof IChild)
		{
			double dividend = 0.85 / (0.8 * this.scale);

			IChild child = (IChild) entity;
			float age = child.getEntityAge();
			GlStateManager.scale(this.scale + age / dividend, this.scale + age / dividend, this.scale + age / dividend);
		}
		else
			GlStateManager.scale(this.scale, this.scale, this.scale);

		if (entity.getSleeping())
		{
			GlStateManager.pushMatrix();
			this.shadowSize = 0;
			float sleepTimer = entity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			// GlStateManager.translate(0, entity.height - 2.65F - sleepTimer,
			// 0);
			GlStateManager.popMatrix();
			GlStateManager.translate(0, -0.1, 0);
			// GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			// this.shadowSize = 0.5F;
			// entity.setSleeping(false);
			// entity.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (this.textureOverrideFunction != null)
		{
			ResourceLocation loc = this.textureOverrideFunction.apply(entity);
			if (loc != null)
				return loc;
		}

		if (entity.getVariantCount() > 0)
		{
			String tex = this.texture.toString().replace(".png", "");

			if (entity.getPosition().equals(new BlockPos(-1, -1, -1)))
				return new ResourceLocation(tex + 0 + ".png");

			tex += entity.getVariant() + ".png";
			return new ResourceLocation(tex);
		}

		return this.texture;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		if (LivingEntity.getVariantCount() > 0)
		{
			int col = LivingEntity.getEyeColorForVariant(LivingEntity.getVariant());
			this.blinking.setColors(col, col);
		}

		this.preRenderScale(LivingEntity, f);
	}

	public static class Factory<T extends EntityAnimaniaDog> implements IRenderFactory<T>
	{
		ResourceLocation tex;
		ResourceLocation blink;
		int eye;
		float scale;
		ModelBase model;
		double x, y, z;
		Function<EntityAnimaniaDog, ResourceLocation> overrideFunc;

		public Factory(ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeCol, float scale, double x, double y, double z, Function<EntityAnimaniaDog, ResourceLocation> overrideFunc)
		{
			this.tex = texture;
			this.blink = blink;
			this.eye = eyeCol;
			this.scale = scale;
			this.model = model;
			this.x = x;
			this.y = y;
			this.z = z;
			this.overrideFunc = overrideFunc;
		}

		public Factory(ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeCol, float scale)
		{
			this(model, texture, blink, eyeCol, scale, 0, 0, 0, null);
		}

		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderDogGeneric(manager, this.model, this.tex, this.blink, this.eye, this.scale, this.x, this.y, this.z, this.overrideFunc);
		}

	}
}

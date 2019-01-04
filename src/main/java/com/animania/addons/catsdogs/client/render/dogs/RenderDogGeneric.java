package com.animania.addons.catsdogs.client.render.dogs;

import com.animania.addons.catsdogs.common.entity.dogs.EntityAnimaniaDog;
import com.animania.api.interfaces.IChild;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDogGeneric<T extends EntityAnimaniaDog> extends RenderLiving<T>
{
	private final ResourceLocation texture;
	private final ResourceLocation blink;
	private final int eyeColor;
	private final float scale;
	private final LayerBlinking blinking;

	public RenderDogGeneric(RenderManager rm, ModelBase model, ResourceLocation texture, ResourceLocation blink, int eyeColor, float scale)
	{
		super(rm, model, 0.5F);
		this.texture = texture;
		this.blink = blink;
		this.eyeColor = eyeColor;
		this.scale = scale;

		this.addLayer(blinking = new LayerBlinking(this, blink, eyeColor, true));
	}

	protected void preRenderScale(EntityAnimaniaDog entity, float f)
	{
		if (entity instanceof IChild)
		{
			float age = ((IChild) entity).getEntityAge();
			GlStateManager.scale(scale + age, scale + age, scale + age);
		}
		else
			GlStateManager.scale(scale, scale, scale);

//		GlStateManager.translate(0f, 0f, -0.5f);
		EntityAnimaniaDog entityCat = (EntityAnimaniaDog) entity;
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
		if(entity.getVariantCount() > 0)
		{
			
			
			String tex = texture.toString().replace(".png", "");
			
			if(entity.getPosition().equals(new BlockPos(-1, -1, -1)))
				return new ResourceLocation(tex + 0 + ".png");
			
			tex += entity.getVariant() + ".png";
			return new ResourceLocation(tex);
		}
		
		return texture;
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{	
		if(entityliving.getVariantCount() > 0)
		{
			int col = entityliving.getEyeColorForVariant(entityliving.getVariant());
			blinking.setColors(col, col);
		}
		
		this.preRenderScale(entityliving, f);
	}

	public static class Factory<T extends EntityAnimaniaDog> implements IRenderFactory<T>
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
			return new RenderDogGeneric(manager, model, tex, blink, eye, scale);
		}

	}
}

package com.animania.addons.catsdogs.client.render.dogs;

import com.animania.addons.catsdogs.client.models.dogs.ModelFox;
import com.animania.addons.catsdogs.common.entity.canids.DogFox.EntityFemaleFox;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.interfaces.IChild;
import com.animania.client.render.layer.LayerBlinking;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderFox<T extends EntityAnimaniaDog> extends RenderLiving<T>
{
	private final ResourceLocation texture = new ResourceLocation("animania:textures/entity/dogs/fox.png");
	private final ResourceLocation texture_razz = new ResourceLocation("animania:textures/entity/dogs/razz_fox.png");
	private final ResourceLocation blink = new ResourceLocation("animania:textures/entity/dogs/blink_fox.png");
	private final LayerBlinking blinking;

	public RenderFox(RenderManager rm)
	{
		super(rm, new ModelFox(), 0.5F);

		this.addLayer(blinking = new LayerBlinking(this, blink, -5415620, true));
	}

	protected void preRenderScale(EntityAnimaniaDog entity, float f)
	{
		if (entity instanceof EntityFemaleFox)
			GlStateManager.scale(0.9, 0.9, 0.9);

		double dividend = 0.85 / (0.9 - 0.5);

		if (entity instanceof IChild)
		{
			float age = ((IChild) entity).getEntityAge();
			GlStateManager.scale(0.5 + age / dividend, 0.5 + age / dividend, 0.5 + age / dividend);
		} else
			GlStateManager.scale(1, 1, 1);

		EntityAnimaniaDog entityCat = entity;
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
			GlStateManager.translate(0, -0.3, 0);
		} else
		{
			this.shadowSize = 0.5F;
			entityCat.setSleeping(false);
			entityCat.setSleepTimer(0F);
		}
		GlStateManager.translate(0, 0.1, 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getName().equalsIgnoreCase("razz"))
		{
			return texture_razz;
		}

		return texture;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		if (LivingEntity.getName().equalsIgnoreCase("razz"))
		{
			blinking.setColors(0xA81348, 0xA81348);
		}

		this.preRenderScale(LivingEntity, f);
	}

	public static class Factory<T extends EntityAnimaniaDog> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderFox(manager);
		}

	}
}

package com.animania.addons.farm.client.render.goats;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.goats.ModelBuckFainting;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderBuckFainting<T extends EntityBuckFainting> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation goatTextures = new ResourceLocation("animania:textures/entity/goats/buck_fainting.png");
	private static final ResourceLocation goatTexturesBlink = new ResourceLocation("animania:textures/entity/goats/goats_blink.png");

	public RenderBuckFainting(RenderManager rm)
	{
		super(rm, new ModelBuckFainting(), 0.35F);
		this.addLayer(new LayerBlinking(this, goatTexturesBlink, 0x6B6968));
	}

	protected ResourceLocation getGoatTextures(T par1EntityCow)
	{
		return RenderBuckFainting.goatTextures;
	}

	protected ResourceLocation getGoatTexturesBlink(T par1EntityCow)
	{
		return RenderBuckFainting.goatTexturesBlink;
	}

	protected void preRenderScale(EntityBuckFainting entity, float f)
	{
		GL11.glScalef(0.42F, 0.42F, 0.42F);

		EntityAnimaniaGoat entityGoat = (EntityAnimaniaGoat) entity;
		if (!entity.getSleeping() && entity.getSpooked() && entity.getSpookedTimer() < 0.94F && entity.getSpookedTimer() > 0.06F)
		{
			GlStateManager.translate(0.0F, entity.height - 1.5F, 0.0F);
			GlStateManager.rotate(86.0F, 0.0F, 0.0F, 1.0F);
			this.shadowSize = 0F;
		}
		else
		{
			GL11.glTranslatef(0f, 0f, -0.5f);
			this.shadowSize = 0.35F;
		}

		if (entityGoat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityGoat.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.10F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.35F;
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

	static class Factory<T extends EntityBuckFainting> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBuckFainting(manager);
		}

	}
}

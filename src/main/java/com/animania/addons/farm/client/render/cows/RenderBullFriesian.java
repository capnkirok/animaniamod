package com.animania.addons.farm.client.render.cows;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.cow.ModelBull;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
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
public class RenderBullFriesian<T extends EntityBullFriesian> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_friesian.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_blink.png");
	private static final ResourceLocation purpTextures = new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");
	private static final ResourceLocation purpTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");

	LayerBlinking blinkingLayer;
	
	public RenderBullFriesian(RenderManager rm)
	{
		super(rm, new ModelBull(), 0.5F);
		addLayer(blinkingLayer = new LayerBlinking(this, cowTexturesBlink, 0x463930, 0xDEDEDE));
	}

	protected void preRenderScale(EntityBullFriesian entity, float f)
	{
		GL11.glScalef(1.3F, 1.3F, 1.3F);
		EntityAnimaniaCow CowEntity = (EntityAnimaniaCow) entity;
		if (CowEntity.getSleeping())
		{
			float sleepTimer = CowEntity.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.85F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			CowEntity.setSleeping(false);
			CowEntity.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	protected ResourceLocation getCowTextures(T par1CowEntity)
	{

		if (par1CowEntity.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			this.blinkingLayer.setColors(0x4F0AA3, 0x4F0AA3);
			return RenderBullFriesian.purpTextures;
		}
		else
		{
			this.blinkingLayer.setColors(0x463930, 0xDEDEDE);
			return RenderBullFriesian.cowTextures;
		}
	}

	protected ResourceLocation getCowTexturesBlink(T par1CowEntity)
	{
		if (par1CowEntity.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			return RenderBullFriesian.purpTexturesBlink;
		}
		else
		{
			return RenderBullFriesian.cowTexturesBlink;
		}
	}

	static class Factory<T extends EntityBullFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBullFriesian(manager);
		}

	}
}

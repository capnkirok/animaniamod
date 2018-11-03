package com.animania.client.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBull;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityBullHolstein;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBullHolstein<T extends EntityBullHolstein> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_holstein.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_blink.png");
	private static final ResourceLocation purpTextures = new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");
	private static final ResourceLocation purpTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_purplicious.png");
	Random rand = new Random();

	LayerBlinking blinkingLayer;
	
	public RenderBullHolstein(RenderManager rm)
	{
		super(rm, new ModelBull(), 0.5F);
		addLayer(blinkingLayer = new LayerBlinking(this, cowTexturesBlink, 0x1C242B, 0xDEDEDE));

	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.3F, 1.3F, 1.3F);
		EntityAnimaniaCow entityCow = (EntityAnimaniaCow) entity;
		if (entityCow.getSleeping())
		{
			float sleepTimer = entityCow.getSleepTimer();
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
			entityCow.setSleeping(false);
			entityCow.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
	}

	protected ResourceLocation getCowTextures(T par1EntityCow)
	{

		if (par1EntityCow.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			this.blinkingLayer.setColors(0x4F0AA3, 0x4F0AA3);
			return RenderBullHolstein.purpTextures;
		}
		else
		{
			this.blinkingLayer.setColors(0x1C242B, 0xDEDEDE);
			return RenderBullHolstein.cowTextures;
		}
	}

	protected ResourceLocation getCowTexturesBlink(T par1EntityCow)
	{
		if (par1EntityCow.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			return RenderBullHolstein.purpTexturesBlink;
		}
		else
		{
			return RenderBullHolstein.cowTexturesBlink;
		}
	}

	static class Factory<T extends EntityBullHolstein> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBullHolstein(manager);
		}
	}
}

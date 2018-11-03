package com.animania.client.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelBull;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.client.render.layer.LayerBullMooshroomMushroom;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityBullMooshroom;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBullMooshroom<T extends EntityBullMooshroom> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/bull_mooshroom.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/bull_blink.png");
	Random rand = new Random();

	public RenderBullMooshroom(RenderManager rm)
	{
		super(rm, new ModelBull(), 0.5F);
		this.addLayer(new LayerBullMooshroomMushroom(this));
		addLayer(new LayerBlinking(this, cowTexturesBlink, 0xAB0F0F));
	}

	protected void preRenderScale(EntityBullMooshroom entity, float f)
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
		return RenderBullMooshroom.cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(T par1EntityCow)
	{
		return RenderBullMooshroom.cowTexturesBlink;
	}

	static class Factory<T extends EntityBullMooshroom> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderBullMooshroom(manager);
		}

	}
}

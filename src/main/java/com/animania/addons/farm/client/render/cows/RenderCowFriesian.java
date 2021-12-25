package com.animania.addons.farm.client.render.cows;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.cow.ModelCow;
import com.animania.addons.farm.common.entity.cows.CowFriesian.CowEntityFriesian;
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
public class RenderCowFriesian<T extends CowEntityFriesian> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_friesian.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_blink.png");
	private static final ResourceLocation purpTextures = new ResourceLocation("animania:textures/entity/cows/cow_purplicious.png");
	private static final ResourceLocation purpTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_purplicious.png");

	LayerBlinking blinkingLayer;

	public RenderCowFriesian(RenderManager rm)
	{
		super(rm, new ModelCow(), 0.5F);
		addLayer(this.blinkingLayer = new LayerBlinking(this, cowTexturesBlink, 0x463930, 0xDEDEDE));
	}

	protected ResourceLocation getCowTextures(T par1CowEntity)
	{

		if (par1CowEntity.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			this.blinkingLayer.setColors(0x4F0AA3, 0x4F0AA3);
			return RenderCowFriesian.purpTextures;
		}
		else
		{
			this.blinkingLayer.setColors(0x463930, 0xDEDEDE);
			return RenderCowFriesian.cowTextures;
		}
	}

	protected ResourceLocation getCowTexturesBlink(T par1CowEntity)
	{
		if (par1CowEntity.getCustomNameTag().equalsIgnoreCase("purp"))
		{
			return RenderCowFriesian.purpTexturesBlink;
		}
		else
		{
			return RenderCowFriesian.cowTexturesBlink;
		}
	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.24F, 1.24F, 1.24F);

		EntityAnimaniaCow CowEntity = entity;
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

	static class Factory<T extends CowEntityFriesian> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderCowFriesian(manager);
		}
	}
}
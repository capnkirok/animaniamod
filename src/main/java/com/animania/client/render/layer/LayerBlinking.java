package com.animania.client.render.layer;

import java.awt.Color;

import com.animania.api.interfaces.IBlinking;
import com.animania.api.interfaces.ISleeping;
import com.animania.client.models.IColoredModel;
import com.animania.common.handler.AddonInjectionHandler;
import com.mojang.blaze3d.platform.GlStateManager;

import RenderLiving;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class LayerBlinking<E extends LivingEntity> implements RenderLayer
{

	private RenderLiving render;
	private ResourceLocation texture_base;
	private int colLeft;
	private int colRight;
	private ResourceLocation left;
	private ResourceLocation right;

	public LayerBlinking(RenderLiving render, ResourceLocation blinkingTexture, int colLeft, int colRight)
	{
		this.render = render;
		this.texture_base = blinkingTexture;
		this.colLeft = colLeft;
		this.colRight = colRight;

		this.left = new ResourceLocation(texture_base.toString().replace(".png", "") + "_left.png");
		this.right = new ResourceLocation(texture_base.toString().replace(".png", "") + "_right.png");
	}

	public LayerBlinking(RenderLiving render, ResourceLocation blinkingTexture, int col)
	{
		this(render, blinkingTexture, col, col);
	}

	public LayerBlinking(RenderLiving render, ResourceLocation blinkingTexture, int col, boolean oneTexture)
	{
		this.render = render;
		this.texture_base = blinkingTexture;
		this.colLeft = col;
		this.colRight = col;

		this.left = texture_base;
		this.right = texture_base;
	}

	public void setColors(int left, int right)
	{
		this.colLeft = left;
		this.colRight = right;
	}

	@Override
	public void doRenderLayer(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		boolean drawBlink = false;

		if (entity instanceof IBlinking)
		{
			IBlinking iblinking = (IBlinking) entity;
			if (iblinking.getBlinkTimer() < 7 && iblinking.getBlinkTimer() >= 0)
				drawBlink = true;
		}

		if (entity instanceof ISleeping)
		{
			long currentTime = entity.level.getLevelTime() % 23999;
			ISleeping isleeping = (ISleeping) entity;
			boolean isSleeping = isleeping.getSleeping();
			float sleepTimer = isleeping.getSleepTimer();

			if (sleepTimer == -100 || sleepTimer == 0.0 ? isSleeping && currentTime < 23250 : isSleeping && sleepTimer <= -0.55F && currentTime < 23250)
				drawBlink = true;
		}

		if (drawBlink)
		{

			float[] rgb = new float[3];

			Color c = new Color(colLeft);
			rgb = c.getRGBColorComponents(rgb);

			// Manual position and scale overrides, because nothing is perfect
			AddonInjectionHandler.runInjection("farm", "blink1", Void.class, this.render, entity);

			this.render.bindTexture(left);
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableCull();
			GlStateManager.color(rgb[0], rgb[1], rgb[2], 1.0F);

			if (this.render.getMainModel() instanceof IColoredModel)
				((IColoredModel) this.render.getMainModel()).setColor(-1f, -1f, -1f);

			this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.color(1f, 1f, 1f);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();

			AddonInjectionHandler.runInjection("farm", "blink2", Void.class, this.render, entity);

			c = new Color(colRight);
			rgb = c.getRGBColorComponents(new float[3]);
			this.render.bindTexture(right);
			GlStateManager.color(rgb[0], rgb[1], rgb[2], 1.0F);

			if (this.render.getMainModel() instanceof IColoredModel)
				((IColoredModel) this.render.getMainModel()).setColor(-1f, -1f, -1f);

			this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.color(1f, 1f, 1f);
			GlStateManager.disableCull();
			GlStateManager.popMatrix();
		}

	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}

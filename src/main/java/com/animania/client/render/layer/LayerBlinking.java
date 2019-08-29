package com.animania.client.render.layer;

import java.awt.Color;

import com.animania.api.interfaces.IBlinking;
import com.animania.api.interfaces.ISleeping;
import com.animania.client.models.IColoredModel;
import com.animania.client.models.ModelDraftHorseFoal;
import com.animania.client.models.ModelDraftHorseMare;
import com.animania.client.models.ModelDraftHorseStallion;
import com.animania.client.models.ModelPiglet;
import com.animania.client.models.ModelPigletHampshire;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.SheepJacob.EntityEweJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityLambJacob;
import com.animania.common.entities.sheep.SheepJacob.EntityRamJacob;
import com.animania.common.entities.sheep.SheepMerino.EntityRamMerino;
import com.animania.common.entities.sheep.SheepSuffolk.EntityRamSuffolk;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerBlinking<E extends EntityLivingBase> implements LayerRenderer
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
	public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
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
			long currentTime = entity.world.getWorldTime() % 23999;
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

			if (this.render.getMainModel() instanceof ModelPiglet || this.render.getMainModel() instanceof ModelPigletHampshire)
			{
				GlStateManager.translate(0.0, -0.038, 0.1499);
			}
			else
				GlStateManager.scale(1.0002, 1.0002, 1.0002);

			GlStateManager.pushMatrix();

			if (this.render.getMainModel() instanceof ModelDraftHorseStallion)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityAnimaniaSheep)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityRamSuffolk || entity instanceof EntityRamMerino || entity instanceof EntityRamJacob || entity instanceof EntityEweJacob || entity instanceof EntityLambJacob)
			{
				GlStateManager.translate(0.0, 0, -0.0001);
			}

			this.render.bindTexture(left);
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(rgb[0], rgb[1], rgb[2], 1.0F);

			if (this.render.getMainModel() instanceof IColoredModel)
				((IColoredModel) this.render.getMainModel()).setColor(-1f, -1f, -1f);

			this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.color(1f, 1f, 1f);
			GlStateManager.popMatrix();

			GlStateManager.pushMatrix();

			if (this.render.getMainModel() instanceof ModelDraftHorseMare || this.render.getMainModel() instanceof ModelDraftHorseFoal)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			if (entity instanceof EntityRamSuffolk || entity instanceof EntityRamMerino || entity instanceof EntityRamJacob || entity instanceof EntityEweJacob || entity instanceof EntityLambJacob)
			{
				GlStateManager.translate(0.0, 0, 0.0001);
			}

			c = new Color(colRight);
			rgb = c.getRGBColorComponents(new float[3]);
			this.render.bindTexture(right);
			GlStateManager.color(rgb[0], rgb[1], rgb[2], 1.0F);

			if (this.render.getMainModel() instanceof IColoredModel)
				((IColoredModel) this.render.getMainModel()).setColor(-1f, -1f, -1f);

			this.render.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.color(1f, 1f, 1f);
			GlStateManager.popMatrix();
		}

	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}

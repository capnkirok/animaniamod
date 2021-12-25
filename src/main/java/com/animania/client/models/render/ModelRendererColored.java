package com.animania.client.models.render;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.model.ModelBase;

public class ModelRendererColored extends ModelRendererAnimania
{

	private float r, g, b;

	public ModelRendererColored(ModelBase model, int texOffX, int texOffY)
	{
		super(model, texOffX, texOffY);
		this.r = 1f;
		this.g = 1f;
		this.b = 1f;
	}

	public void setColor(float r, float g, float b)
	{
		this.r = r;
		this.b = b;
		this.g = g;
	}

	@Override
	public void render(float scale)
	{
		GlStateManager.pushMatrix();
		if (this.r != -1 && this.g != -1 && this.b != -1)
			GlStateManager.color(this.r, this.g, this.b);
		super.render(scale);
		if (this.r != -1 && this.g != -1 && this.b != -1)
			GlStateManager.color(1, 1, 1);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderWithRotation(float scale)
	{
		GlStateManager.pushMatrix();
		if (this.r != -1 && this.g != -1 && this.b != -1)
			GlStateManager.color(this.r, this.g, this.b);
		super.renderWithRotation(scale);
		if (this.r != -1 && this.g != -1 && this.b != -1)
			GlStateManager.color(1, 1, 1);
		GlStateManager.popMatrix();
	}

}

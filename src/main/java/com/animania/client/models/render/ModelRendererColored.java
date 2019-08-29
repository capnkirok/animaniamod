package com.animania.client.models.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;

public class ModelRendererColored extends ModelRendererAnimania
{

	private float r, g, b;

	public ModelRendererColored(ModelBase model, int texOffX, int texOffY)
	{
		super(model, texOffX, texOffY);
		r = 1f;
		g = 1f;
		b = 1f;
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
		if (r != -1 && g != -1 && b != -1)
			GlStateManager.color(r, g, b);
		super.render(scale);
		if (r != -1 && g != -1 && b != -1)
			GlStateManager.color(1, 1, 1);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderWithRotation(float scale)
	{
		GlStateManager.pushMatrix();
		if (r != -1 && g != -1 && b != -1)
			GlStateManager.color(r, g, b);
		super.renderWithRotation(scale);
		if (r != -1 && g != -1 && b != -1)
			GlStateManager.color(1, 1, 1);
		GlStateManager.popMatrix();	}

}

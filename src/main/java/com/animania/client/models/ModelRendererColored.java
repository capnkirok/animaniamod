package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ModelRendererColored extends ModelRenderer
{

	private float r,g,b;
	
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
		GlStateManager.color(r, g, b);
		super.render(scale);
		GlStateManager.color(1f, 1f, 1f);
		GlStateManager.popMatrix();
	}

}

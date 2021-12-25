package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelWaterBottle extends ModelBase
{
	// fields
	ModelRenderer Shape1;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape2;

	public ModelWaterBottle()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.Shape1 = new ModelRenderer(this, 22, 0);
		this.Shape1.addBox(0F, -2.5F, -0.5F, 1, 5, 1);
		this.Shape1.setRotationPoint(-0.5F, 20F, 4F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, -0.7853982F, 0F, 0F);
		this.Shape3 = new ModelRenderer(this, 0, 16);
		this.Shape3.addBox(0F, 0F, 0F, 3, 2, 3);
		this.Shape3.setRotationPoint(-1.5F, 17F, 4F);
		this.Shape3.setTextureSize(64, 32);
		this.Shape3.mirror = true;
		this.setRotation(this.Shape3, 0F, 0F, 0F);
		this.Shape4 = new ModelRenderer(this, 33, 0);
		this.Shape4.addBox(0F, 0F, 0F, 4, 9, 4);
		this.Shape4.setRotationPoint(-2F, 7.5F, 3.5F);
		this.Shape4.setTextureSize(64, 32);
		this.Shape4.mirror = true;
		this.setRotation(this.Shape4, 0F, 0F, 0F);
		this.Shape2 = new ModelRenderer(this, 0, 0);
		this.Shape2.addBox(0F, 0F, 0F, 5, 10, 5);
		this.Shape2.setRotationPoint(-2.5F, 7F, 3F);
		this.Shape2.setTextureSize(64, 32);
		this.Shape2.mirror = true;
		this.setRotation(this.Shape2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5);
		this.Shape1.render(f5);
		this.Shape3.render(f5);
		this.Shape4.render(f5);
		this.Shape2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
	}

}

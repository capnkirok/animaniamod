package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToad extends ModelBase {
	ModelRenderer MainBody;
	ModelRenderer Chubs;
	ModelRenderer Head;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer EyeL;
	ModelRenderer EyeR;
	ModelRenderer Neck;
	ModelRenderer HindLegR;
	ModelRenderer HindFootR;
	ModelRenderer HindLegL;
	ModelRenderer HindFootL;
	ModelRenderer FrontLegLTop;
	ModelRenderer FrontLegLMiddle;
	ModelRenderer FrontFootL;
	ModelRenderer FrontLegRTop;
	ModelRenderer FrontLegRMiddle;
	ModelRenderer FrontFootR;

	public ModelToad() {
		this(0.0f);
	}

	public ModelToad(float par1) {
		MainBody = new ModelRenderer(this, 0, 0);
		MainBody.setTextureSize(32, 32);
		MainBody.addBox(-2F, 0F, -7F, 4, 3, 7);
		MainBody.setRotationPoint(-2.25725E-07F, 19F, -2.581994F);
		Chubs = new ModelRenderer(this, 7, 22);
		Chubs.setTextureSize(32, 32);
		Chubs.addBox(-3F, 0F, -5.5F, 6, 3, 6);
		Chubs.setRotationPoint(-1.577356E-07F, 19.80322F, -1.804284F);
		Head = new ModelRenderer(this, 0, 10);
		Head.setTextureSize(32, 32);
		Head.addBox(-2F, -2F, -3F, 4, 2, 3);
		Head.setRotationPoint(-0.01000023F, 19F, -2.581994F);
		Snout1 = new ModelRenderer(this, 0, 15);
		Snout1.setTextureSize(32, 32);
		Snout1.addBox(-2F, 0F, 0F, 4, 1, 2);
		Snout1.setRotationPoint(-4.854712E-07F, 18.5422F, -5.546859F);
		Snout2 = new ModelRenderer(this, 0, 21);
		Snout2.setTextureSize(32, 32);
		Snout2.addBox(-2F, 0F, -2F, 4, 1, 2);
		Snout2.setRotationPoint(0.00999938F, 19.77704F, -7.120125F);
		EyeL = new ModelRenderer(this, 1, 2);
		EyeL.setTextureSize(32, 32);
		EyeL.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EyeL.setRotationPoint(1.3F, 18.67613F, -6.039873F);
		EyeR = new ModelRenderer(this, 1, 2);
		EyeR.setTextureSize(32, 32);
		EyeR.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EyeR.setRotationPoint(-1.300001F, 18.67613F, -6.039873F);
		Neck = new ModelRenderer(this, 0, 18);
		Neck.setTextureSize(32, 32);
		Neck.addBox(-2F, -1F, 0F, 4, 1, 2);
		Neck.setRotationPoint(-3.141467E-07F, 21.83037F, -3.576481F);
		HindLegR = new ModelRenderer(this, 20, 0);
		HindLegR.setTextureSize(32, 32);
		HindLegR.addBox(0F, -1F, 0F, 2, 2, 4);
		HindLegR.setRotationPoint(-2.5F, 23F, 2F);
		HindFootR = new ModelRenderer(this, 21, 6);
		HindFootR.setTextureSize(32, 32);
		HindFootR.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		HindFootR.setRotationPoint(-3.90315F, 23.4039F, -0.1988889F);
		HindLegL = new ModelRenderer(this, 20, 0);
		HindLegL.setTextureSize(32, 32);
		HindLegL.addBox(-2F, -1F, 0F, 2, 2, 4);
		HindLegL.setRotationPoint(2.5F, 23F, 2F);
		HindFootL = new ModelRenderer(this, 21, 6);
		HindFootL.setTextureSize(32, 32);
		HindFootL.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		HindFootL.setRotationPoint(3.90315F, 23.4039F, -0.1988887F);
		FrontLegLTop = new ModelRenderer(this, 24, 11);
		FrontLegLTop.setTextureSize(32, 32);
		FrontLegLTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		FrontLegLTop.setRotationPoint(2.5F, 19.97F, -4.5F);
		FrontLegLMiddle = new ModelRenderer(this, 24, 14);
		FrontLegLMiddle.setTextureSize(32, 32);
		FrontLegLMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		FrontLegLMiddle.setRotationPoint(2.49F, 21.42505F, -3.039574F);
		FrontFootL = new ModelRenderer(this, 23, 18);
		FrontFootL.setTextureSize(32, 32);
		FrontFootL.addBox(-0.5F, -1F, 0F, 1, 1, 2);
		FrontFootL.setRotationPoint(2.49F, 24.02687F, -4.533063F);
		FrontLegRTop = new ModelRenderer(this, 24, 11);
		FrontLegRTop.setTextureSize(32, 32);
		FrontLegRTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		FrontLegRTop.setRotationPoint(-2.5F, 19.97F, -4.5F);
		FrontLegRMiddle = new ModelRenderer(this, 24, 14);
		FrontLegRMiddle.setTextureSize(32, 32);
		FrontLegRMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		FrontLegRMiddle.setRotationPoint(-2.51F, 21.42505F, -3.039574F);
		FrontFootR = new ModelRenderer(this, 23, 18);
		FrontFootR.setTextureSize(32, 32);
		FrontFootR.addBox(-0.5F, -1F, 0F, 1, 1, 2);
		FrontFootR.setRotationPoint(-2.51F, 24.02687F, -4.533063F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		MainBody.rotateAngleX = 0.3378883F;
		MainBody.rotateAngleY = -3.141593F;
		MainBody.rotateAngleZ = -9.023148E-16F;
		MainBody.renderWithRotation(par7);

		Chubs.rotateAngleX = 0.3378883F;
		Chubs.rotateAngleY = -3.141593F;
		Chubs.rotateAngleZ = -9.023148E-16F;
		Chubs.renderWithRotation(par7);

		Head.rotateAngleX = -0.1531985F;
		Head.rotateAngleY = 8.742279E-08F;
		Head.rotateAngleZ = -3.141593F;
		Head.renderWithRotation(par7);

		Snout1.rotateAngleX = -0.6654596F;
		Snout1.rotateAngleY = -3.141593F;
		Snout1.rotateAngleZ = -4.153653E-15F;
		Snout1.renderWithRotation(par7);

		Snout2.rotateAngleX = -0.03649529F;
		Snout2.rotateAngleY = -3.141593F;
		Snout2.rotateAngleZ = -3.182659E-15F;
		Snout2.renderWithRotation(par7);

		EyeL.rotateAngleX = -0.1938854F;
		EyeL.rotateAngleY = 2.785568F;
		EyeL.rotateAngleZ = 0.07152723F;
		EyeL.renderWithRotation(par7);

		EyeR.rotateAngleX = -0.1938854F;
		EyeR.rotateAngleY = -2.785568F;
		EyeR.rotateAngleZ = -0.07152724F;
		EyeR.renderWithRotation(par7);

		Neck.rotateAngleX = 0.6386186F;
		Neck.rotateAngleY = -3.141593F;
		Neck.rotateAngleZ = -4.058466E-15F;
		Neck.renderWithRotation(par7);

		HindLegR.rotateAngleX = 0.3026401F;
		HindLegR.rotateAngleY = -2.96706F;
		HindLegR.rotateAngleZ = -1.009837E-08F;
		HindLegR.renderWithRotation(par7);

		HindFootR.rotateAngleX = 3.076197E-05F;
		HindFootR.rotateAngleY = -2.96706F;
		HindFootR.rotateAngleZ = -9.639443E-09F;
		HindFootR.renderWithRotation(par7);

		HindLegL.rotateAngleX = 0.3026401F;
		HindLegL.rotateAngleY = 2.96706F;
		HindLegL.rotateAngleZ = 1.009837E-08F;
		HindLegL.renderWithRotation(par7);

		HindFootL.rotateAngleX = 3.076197E-05F;
		HindFootL.rotateAngleY = 2.96706F;
		HindFootL.rotateAngleZ = 9.639443E-09F;
		HindFootL.renderWithRotation(par7);

		FrontLegLTop.rotateAngleX = -0.5422654F;
		FrontLegLTop.rotateAngleY = -3.141593F;
		FrontLegLTop.rotateAngleZ = -6.446634E-16F;
		FrontLegLTop.renderWithRotation(par7);

		FrontLegLMiddle.rotateAngleX = 0.521095F;
		FrontLegLMiddle.rotateAngleY = -3.141593F;
		FrontLegLMiddle.rotateAngleZ = -2.761143E-15F;
		FrontLegLMiddle.renderWithRotation(par7);

		FrontFootL.rotateAngleX = -2.610812E-07F;
		FrontFootL.rotateAngleY = -3.141593F;
		FrontFootL.rotateAngleZ = -2.39467E-15F;
		FrontFootL.renderWithRotation(par7);

		FrontLegRTop.rotateAngleX = -0.5422654F;
		FrontLegRTop.rotateAngleY = -3.141593F;
		FrontLegRTop.rotateAngleZ = -6.446634E-16F;
		FrontLegRTop.renderWithRotation(par7);

		FrontLegRMiddle.rotateAngleX = 0.521095F;
		FrontLegRMiddle.rotateAngleY = -3.141593F;
		FrontLegRMiddle.rotateAngleZ = -2.761143E-15F;
		FrontLegRMiddle.renderWithRotation(par7);

		FrontFootR.rotateAngleX = -2.610812E-07F;
		FrontFootR.rotateAngleY = -3.141593F;
		FrontFootR.rotateAngleZ = -2.39467E-15F;
		FrontFootR.renderWithRotation(par7);

	}

}

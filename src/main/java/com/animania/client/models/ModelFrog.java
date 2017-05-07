package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFrog extends ModelBase {
	ModelRenderer MainBody;
	ModelRenderer HindLegR;
	ModelRenderer HindFootR;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer EyeL;
	ModelRenderer EyeR;
	ModelRenderer FrontLegRTop;
	ModelRenderer FrontLegRMiddle;
	ModelRenderer FrontFootR;
	ModelRenderer FrontLegLTop;
	ModelRenderer FrontLegLMiddle;
	ModelRenderer FrontFootL;
	ModelRenderer HindLegL;
	ModelRenderer HindFootL;

	public ModelFrog() {
		this(0.0f);
	}

	public ModelFrog(float par1) {
		this.textureHeight = 32;
		this.textureWidth = 32;
		MainBody = new ModelRenderer(this, 0, 0);
		MainBody.setTextureSize(32, 32);
		MainBody.addBox(-2F, 0F, -5F, 4, 3, 5);
		MainBody.setRotationPoint(-2.25725E-07F, 19F, -2.581994F);
		HindLegR = new ModelRenderer(this, 20, 0);
		HindLegR.setTextureSize(32, 32);
		HindLegR.addBox(-1F, -1F, -2F, 2, 2, 4);
		HindLegR.setRotationPoint(-2.5F, 22.47402F, 5.702166E-07F);
		HindFootR = new ModelRenderer(this, 21, 6);
		HindFootR.setTextureSize(32, 32);
		HindFootR.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		HindFootR.setRotationPoint(-2.5F, 23.47402F, -0.4999994F);
		Neck = new ModelRenderer(this, 2, 19);
		Neck.setTextureSize(32, 32);
		Neck.addBox(-2F, -1F, 0F, 4, 1, 2);
		Neck.setRotationPoint(-3.615531E-07F, 21.56633F, -4.135685F);
		Head = new ModelRenderer(this, 1, 8);
		Head.setTextureSize(32, 32);
		Head.addBox(-2F, -2F, 0F, 4, 2, 3);
		Head.setRotationPoint(-0.01000047F, 17.94402F, -5.39F);
		Snout1 = new ModelRenderer(this, 2, 13);
		Snout1.setTextureSize(32, 32);
		Snout1.addBox(-2F, 0F, 0F, 4, 1, 2);
		Snout1.setRotationPoint(-4.719492E-07F, 17.94402F, -5.39F);
		Snout2 = new ModelRenderer(this, 2, 16);
		Snout2.setTextureSize(32, 32);
		Snout2.addBox(-2F, 0F, -2F, 4, 1, 2);
		Snout2.setRotationPoint(0.009999371F, 18.83004F, -7.183031F);
		EyeL = new ModelRenderer(this, 0, 8);
		EyeL.setTextureSize(32, 32);
		EyeL.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EyeL.setRotationPoint(1.299999F, 17.97402F, -5.900001F);
		EyeR = new ModelRenderer(this, 0, 8);
		EyeR.setTextureSize(32, 32);
		EyeR.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EyeR.setRotationPoint(-1.3F, 17.97402F, -5.9F);
		FrontLegRTop = new ModelRenderer(this, 24, 11);
		FrontLegRTop.setTextureSize(32, 32);
		FrontLegRTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		FrontLegRTop.setRotationPoint(-2.5F, 19.97402F, -4.5F);
		FrontLegRMiddle = new ModelRenderer(this, 24, 14);
		FrontLegRMiddle.setTextureSize(32, 32);
		FrontLegRMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		FrontLegRMiddle.setRotationPoint(-2.49F, 21.42906F, -3.039574F);
		FrontFootR = new ModelRenderer(this, 23, 18);
		FrontFootR.setTextureSize(32, 32);
		FrontFootR.addBox(-0.5F, -1F, 0F, 1, 1, 2);
		FrontFootR.setRotationPoint(-2.49F, 24.03089F, -4.533064F);
		FrontLegLTop = new ModelRenderer(this, 24, 11);
		FrontLegLTop.setTextureSize(32, 32);
		FrontLegLTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
		FrontLegLTop.setRotationPoint(2.5F, 19.97402F, -4.5F);
		FrontLegLMiddle = new ModelRenderer(this, 24, 14);
		FrontLegLMiddle.setTextureSize(32, 32);
		FrontLegLMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		FrontLegLMiddle.setRotationPoint(2.49F, 21.42906F, -3.039574F);
		FrontFootL = new ModelRenderer(this, 23, 18);
		FrontFootL.setTextureSize(32, 32);
		FrontFootL.addBox(-0.5F, -1F, 0F, 1, 1, 2);
		FrontFootL.setRotationPoint(2.49F, 24.03089F, -4.533064F);
		HindLegL = new ModelRenderer(this, 20, 0);
		HindLegL.setTextureSize(32, 32);
		HindLegL.addBox(-1F, -1F, -2F, 2, 2, 4);
		HindLegL.setRotationPoint(2.5F, 22.47402F, 1.253854E-07F);
		HindFootL = new ModelRenderer(this, 21, 6);
		HindFootL.setTextureSize(32, 32);
		HindFootL.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		HindFootL.setRotationPoint(2.5F, 23.47402F, -0.4999997F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		MainBody.rotateAngleX = 0.5443909F;
		MainBody.rotateAngleY = -3.141593F;
		MainBody.rotateAngleZ = -3.529419E-16F;
		MainBody.renderWithRotation(scale);

		HindLegR.rotateAngleX = 0.3026092F;
		HindLegR.rotateAngleY = -3.141593F;
		HindLegR.rotateAngleZ = 8.267461E-18F;
		HindLegR.renderWithRotation(scale);

		HindFootR.rotateAngleX = -1.947392E-07F;
		HindFootR.rotateAngleY = -3.141593F;
		HindFootR.rotateAngleZ = 7.89182E-18F;
		HindFootR.renderWithRotation(scale);

		Neck.rotateAngleX = 0.8451216F;
		Neck.rotateAngleY = -3.141593F;
		Neck.rotateAngleZ = 8.215925E-16F;
		Neck.renderWithRotation(scale);

		Head.rotateAngleX = -0.3597011F;
		Head.rotateAngleY = 8.74228E-08F;
		Head.rotateAngleZ = -3.141593F;
		Head.renderWithRotation(scale);

		Snout1.rotateAngleX = -0.458957F;
		Snout1.rotateAngleY = -3.141593F;
		Snout1.rotateAngleZ = 2.203421E-16F;
		Snout1.renderWithRotation(scale);

		Snout2.rotateAngleX = 0.1700074F;
		Snout2.rotateAngleY = -3.141593F;
		Snout2.rotateAngleZ = 1.505328E-15F;
		Snout2.renderWithRotation(scale);

		EyeL.rotateAngleX = -1.248631E-07F;
		EyeL.rotateAngleY = 2.792527F;
		EyeL.rotateAngleZ = 4.380097E-08F;
		EyeL.renderWithRotation(scale);

		EyeR.rotateAngleX = -1.248631E-07F;
		EyeR.rotateAngleY = -2.792527F;
		EyeR.rotateAngleZ = -4.380097E-08F;
		EyeR.renderWithRotation(scale);

		FrontLegRTop.rotateAngleX = -0.5422653F;
		FrontLegRTop.rotateAngleY = -3.141593F;
		FrontLegRTop.rotateAngleZ = -2.302643E-15F;
		FrontLegRTop.renderWithRotation(scale);

		FrontLegRMiddle.rotateAngleX = 0.5210952F;
		FrontLegRMiddle.rotateAngleY = -3.141593F;
		FrontLegRMiddle.rotateAngleZ = -2.330141E-15F;
		FrontLegRMiddle.renderWithRotation(scale);

		FrontFootR.rotateAngleX = -1.766048E-07F;
		FrontFootR.rotateAngleY = -3.141593F;
		FrontFootR.rotateAngleZ = -2.020871E-15F;
		FrontFootR.renderWithRotation(scale);

		FrontLegLTop.rotateAngleX = -0.5422653F;
		FrontLegLTop.rotateAngleY = -3.141593F;
		FrontLegLTop.rotateAngleZ = -2.302643E-15F;
		FrontLegLTop.renderWithRotation(scale);

		FrontLegLMiddle.rotateAngleX = 0.5210952F;
		FrontLegLMiddle.rotateAngleY = -3.141593F;
		FrontLegLMiddle.rotateAngleZ = -2.330141E-15F;
		FrontLegLMiddle.renderWithRotation(scale);

		FrontFootL.rotateAngleX = -1.766048E-07F;
		FrontFootL.rotateAngleY = -3.141593F;
		FrontFootL.rotateAngleZ = -2.020871E-15F;
		FrontFootL.renderWithRotation(scale);

		HindLegL.rotateAngleX = 0.3026092F;
		HindLegL.rotateAngleY = -3.141593F;
		HindLegL.rotateAngleZ = 8.267461E-18F;
		HindLegL.renderWithRotation(scale);

		HindFootL.rotateAngleX = -1.947392E-07F;
		HindFootL.rotateAngleY = -3.141593F;
		HindFootL.rotateAngleZ = 7.89182E-18F;
		HindFootL.renderWithRotation(scale);

	}

}

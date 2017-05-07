package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrough extends ModelBase
{
	ModelRenderer Block1;
	ModelRenderer Base2;
	ModelRenderer Base1;
	ModelRenderer Block2;
	ModelRenderer Block3;
	ModelRenderer Block4;
	ModelRenderer Block5;
	ModelRenderer Feed;
	ModelRenderer FeedA;
	ModelRenderer FeedB;
	ModelRenderer FeedC;
	ModelRenderer FeedD;
	ModelRenderer FeedE;
	ModelRenderer FeedF;
	ModelRenderer FeedG;
	ModelRenderer FeedH;
	
	ModelRenderer Feed1;
	ModelRenderer FeedA1;
	ModelRenderer FeedB1;
	ModelRenderer FeedC1;
	ModelRenderer FeedD1;
	ModelRenderer FeedE1;
	ModelRenderer FeedF1;
	ModelRenderer FeedG1;
	ModelRenderer FeedH1;
	
	ModelRenderer Feed2;
	ModelRenderer FeedA2;
	ModelRenderer FeedB2;
	ModelRenderer FeedC2;
	ModelRenderer FeedD2;
	ModelRenderer FeedE2;
	ModelRenderer FeedF2;
	ModelRenderer FeedG2;
	ModelRenderer FeedH2;
	
	ModelRenderer Slop1;
	ModelRenderer Slop2;
	ModelRenderer Slop3;
	ModelRenderer Water1;
	ModelRenderer Water2;
	ModelRenderer Water3;

	public ModelTrough()
	{
		this( 0.0f );
	}

	public ModelTrough(float par1)
	{
		Block1 = new ModelRenderer( this, 2, 2 );
		Block1.setTextureSize( 128, 64 );
		Block1.addBox( -1F, -5F, -6F, 2, 10, 12);
		Block1.setRotationPoint( -7F, 17F, 0F );
		Base2 = new ModelRenderer( this, 4, 4 );
		Base2.setTextureSize( 128, 64 );
		Base2.addBox( -1F, -1F, -5F, 2, 2, 10);
		Base2.setRotationPoint( 22F, 23F, 0F );
		Base1 = new ModelRenderer( this, 4, 4 );
		Base1.setTextureSize( 128, 64 );
		Base1.addBox( -1F, -1F, -5F, 2, 2, 10);
		Base1.setRotationPoint( -6F, 23F, 0F );
		Block2 = new ModelRenderer( this, 2, 2 );
		Block2.setTextureSize( 128, 64 );
		Block2.addBox( -1F, -5F, -6F, 2, 10, 12);
		Block2.setRotationPoint( 23F, 17F, 0F );
		Block3 = new ModelRenderer( this, 1, 26 );
		Block3.setTextureSize( 128, 64 );
		Block3.addBox( -14F, -4F, -1F, 28, 8, 2);
		Block3.setRotationPoint( 8F, 18F, -5F );
		Block4 = new ModelRenderer( this, 1, 26 );
		Block4.setTextureSize( 128, 64 );
		Block4.addBox( -14F, -4F, -1F, 28, 8, 2);
		Block4.setRotationPoint( 8F, 18F, 5F );
		Block5 = new ModelRenderer( this, 3, 42 );
		Block5.setTextureSize( 128, 64 );
		Block5.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Block5.setRotationPoint( 8F, 21.5F, 0F );

		Feed = new ModelRenderer( this, 56, 1 );
		Feed.setTextureSize( 128, 64 );
		Feed.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Feed.setRotationPoint( 8F, 16F, 0F );
		FeedA = new ModelRenderer( this, 72, 36 );
		FeedA.setTextureSize( 128, 64 );
		FeedA.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedA.setRotationPoint( -1F, 15F, 2.5F );
		FeedB = new ModelRenderer( this, 72, 36 );
		FeedB.setTextureSize( 128, 64 );
		FeedB.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedB.setRotationPoint( 8F, 15F, 1.000001F );
		FeedC = new ModelRenderer( this, 72, 36 );
		FeedC.setTextureSize( 128, 64 );
		FeedC.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedC.setRotationPoint( 17F, 15F, 2.500001F );
		FeedD = new ModelRenderer( this, 72, 36 );
		FeedD.setTextureSize( 128, 64 );
		FeedD.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedD.setRotationPoint( 14F, 15F, -1.999999F );
		FeedE = new ModelRenderer( this, 72, 36 );
		FeedE.setTextureSize( 128, 64 );
		FeedE.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedE.setRotationPoint( 2F, 15F, -0.9999991F );
		FeedF = new ModelRenderer( this, 72, 36 );
		FeedF.setTextureSize( 128, 64 );
		FeedF.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedF.setRotationPoint( 9.000001F, 14F, -1.999999F );
		FeedG = new ModelRenderer( this, 72, 36 );
		FeedG.setTextureSize( 128, 64 );
		FeedG.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedG.setRotationPoint( 1.56835E-07F, 14F, -0.9999991F );
		FeedH = new ModelRenderer( this, 72, 36 );
		FeedH.setTextureSize( 128, 64 );
		FeedH.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedH.setRotationPoint( 17F, 14F, -0.9999986F );
		
		
		Feed1 = new ModelRenderer( this, 56, 1 );
		Feed1.setTextureSize( 128, 64 );
		Feed1.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Feed1.setRotationPoint( 8F, 18F, 0F );
		FeedA1 = new ModelRenderer( this, 72, 36 );
		FeedA1.setTextureSize( 128, 64 );
		FeedA1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedA1.setRotationPoint( -1F, 17F, 2.5F );
		FeedB1 = new ModelRenderer( this, 72, 36 );
		FeedB1.setTextureSize( 128, 64 );
		FeedB1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedB1.setRotationPoint( 8F, 17F, 1.000001F );
		FeedC1 = new ModelRenderer( this, 72, 36 );
		FeedC1.setTextureSize( 128, 64 );
		FeedC1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedC1.setRotationPoint( 17F, 17F, 2.500001F );
		FeedD1 = new ModelRenderer( this, 72, 36 );
		FeedD1.setTextureSize( 128, 64 );
		FeedD1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedD1.setRotationPoint( 14F, 17F, -1.999999F );
		FeedE1 = new ModelRenderer( this, 72, 36 );
		FeedE1.setTextureSize( 128, 64 );
		FeedE1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedE1.setRotationPoint( 2F, 17F, -0.9999991F );
		FeedF1 = new ModelRenderer( this, 72, 36 );
		FeedF1.setTextureSize( 128, 64 );
		FeedF1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedF1.setRotationPoint( 9.000001F, 16F, -1.999999F );
		FeedG1 = new ModelRenderer( this, 72, 36 );
		FeedG1.setTextureSize( 128, 64 );
		FeedG1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedG1.setRotationPoint( 1.56835E-07F, 16F, -0.9999991F );
		FeedH1 = new ModelRenderer( this, 72, 36 );
		FeedH1.setTextureSize( 128, 64 );
		FeedH1.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedH1.setRotationPoint( 17F, 16F, -0.9999986F );
		
		Feed2 = new ModelRenderer( this, 56, 1 );
		Feed2.setTextureSize( 128, 64 );
		Feed2.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Feed2.setRotationPoint( 8F, 20F, 0F );
		FeedA2 = new ModelRenderer( this, 72, 36 );
		FeedA2.setTextureSize( 128, 64 );
		FeedA2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedA2.setRotationPoint( -1F, 19F, 2.5F );
		FeedB2 = new ModelRenderer( this, 72, 36 );
		FeedB2.setTextureSize( 128, 64 );
		FeedB2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedB2.setRotationPoint( 8F, 19F, 1.000001F );
		FeedC2 = new ModelRenderer( this, 72, 36 );
		FeedC2.setTextureSize( 128, 64 );
		FeedC2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedC2.setRotationPoint( 17F, 19F, 2.500001F );
		FeedD2 = new ModelRenderer( this, 72, 36 );
		FeedD2.setTextureSize( 128, 64 );
		FeedD2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedD2.setRotationPoint( 14F, 19F, -1.999999F );
		FeedE2 = new ModelRenderer( this, 72, 36 );
		FeedE2.setTextureSize( 128, 64 );
		FeedE2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedE2.setRotationPoint( 2F, 19F, -0.9999991F );
		FeedF2 = new ModelRenderer( this, 72, 36 );
		FeedF2.setTextureSize( 128, 64 );
		FeedF2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedF2.setRotationPoint( 9.000001F, 18F, -1.999999F );
		FeedG2 = new ModelRenderer( this, 72, 36 );
		FeedG2.setTextureSize( 128, 64 );
		FeedG2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedG2.setRotationPoint( 1.56835E-07F, 18F, -0.9999991F );
		FeedH2 = new ModelRenderer( this, 72, 36 );
		FeedH2.setTextureSize( 128, 64 );
		FeedH2.addBox( -4.5F, 0F, -5F, 9, 0, 10);
		FeedH2.setRotationPoint( 17F, 18F, -0.9999986F );
		
	
		Slop1 = new ModelRenderer( this, 56, 12 );
		Slop1.setTextureSize( 128, 64 );
		Slop1.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Slop1.setRotationPoint( 8F, 16F, 0F );
		
		Slop2 = new ModelRenderer( this, 56, 12 );
		Slop2.setTextureSize( 128, 64 );
		Slop2.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Slop2.setRotationPoint( 8F, 18F, 0F );
		
		Slop3 = new ModelRenderer( this, 56, 12 );
		Slop3.setTextureSize( 128, 64 );
		Slop3.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Slop3.setRotationPoint( 8F, 20F, 0F );

		Water1 = new ModelRenderer( this, 56, 54 );
		Water1.setTextureSize( 128, 64 );
		Water1.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Water1.setRotationPoint( 8F, 16F, 0F );

		Water2 = new ModelRenderer( this, 56, 54 );
		Water2.setTextureSize( 128, 64 );
		Water2.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Water2.setRotationPoint( 8F, 18F, 0F );

		Water3 = new ModelRenderer( this, 56, 54 );
		Water3.setTextureSize( 128, 64 );
		Water3.addBox( -14F, -0.5F, -4F, 28, 1, 8);
		Water3.setRotationPoint( 8F, 20F, 0F );

	}

	@Override
	public void render(Entity entityIn, float troughType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		Block1.render(scale);
		Block2.render(scale);
		Block3.render(scale);
		Block4.render(scale);
		Block5.render(scale);
		Base1.render(scale);
		Base2.render(scale);

		if (troughType == 1.0F) {
			Water1.render(scale);
		} else if (troughType == 2.0F) {
			Water2.render(scale);
		} else if (troughType == 3.0F) {
			Water3.render(scale);
		} else if (troughType == 4.0F) {
			Feed2.render(scale);
			FeedA2.render(scale);
			FeedB2.render(scale);
			FeedC2.render(scale);
			FeedD2.render(scale);
			FeedE2.render(scale);
			FeedF2.render(scale);
			FeedG2.render(scale);
			FeedH2.render(scale);
		} else if (troughType == 5.0F) {
			Feed1.render(scale);
			FeedA1.render(scale);
			FeedB1.render(scale);
			FeedC1.render(scale);
			FeedD1.render(scale);
			FeedE1.render(scale);
			FeedF1.render(scale);
			FeedG1.render(scale);
			FeedH1.render(scale);
		} else if (troughType == 6.0F) {
			Feed.render(scale);
			FeedA.render(scale);
			FeedB.render(scale);
			FeedC.render(scale);
			FeedD.render(scale);
			FeedE.render(scale);
			FeedF.render(scale);
			FeedG.render(scale);
			FeedH.render(scale);
		} else if (troughType == 7.0F) {
			Slop1.render(scale);
		} else if (troughType == 8.0F) {
			Slop2.render(scale);
		} else if (troughType == 9.0F) {
			Slop3.render(scale);
		} 

		this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		FeedA.rotateAngleX = 0.5566564F;
		FeedA.rotateAngleY = 0.6934777F;
		FeedA.rotateAngleZ = 0.418777F;

		FeedB.rotateAngleX = 0.3321232F;
		FeedB.rotateAngleY = 1.126472F;
		FeedB.rotateAngleZ = 0.4568707F;

		FeedC.rotateAngleX = 0.5561382F;
		FeedC.rotateAngleY = 0.6948478F;
		FeedC.rotateAngleZ = 0.4195006F;

		FeedD.rotateAngleX = 0.329293F;
		FeedD.rotateAngleY = -2.02634F;
		FeedD.rotateAngleZ = 0.4998787F;

		FeedE.rotateAngleX = 0.329293F;
		FeedE.rotateAngleY = -2.02634F;
		FeedE.rotateAngleZ = 0.3414953F;

		FeedF.rotateAngleX = 0.223693F;
		FeedF.rotateAngleY = -1.865655F;
		FeedF.rotateAngleZ = 0.009323301F;

		FeedG.rotateAngleX = 0.2029555F;
		FeedG.rotateAngleY = -1.465355F;
		FeedG.rotateAngleZ = 0.09517268F;

		FeedH.rotateAngleX = 0.286131F;
		FeedH.rotateAngleY = 2.831252F;
		FeedH.rotateAngleZ = 0.4113167F;
		
		FeedA1.rotateAngleX = 0.5566564F;
		FeedA1.rotateAngleY = 0.6934777F;
		FeedA1.rotateAngleZ = 0.418777F;

		FeedB1.rotateAngleX = 0.3321232F;
		FeedB1.rotateAngleY = 1.126472F;
		FeedB1.rotateAngleZ = 0.4568707F;

		FeedC1.rotateAngleX = 0.5561382F;
		FeedC1.rotateAngleY = 0.6948478F;
		FeedC1.rotateAngleZ = 0.4195006F;

		FeedD1.rotateAngleX = 0.329293F;
		FeedD1.rotateAngleY = -2.02634F;
		FeedD1.rotateAngleZ = 0.4998787F;

		FeedE1.rotateAngleX = 0.329293F;
		FeedE1.rotateAngleY = -2.02634F;
		FeedE1.rotateAngleZ = 0.3414953F;

		FeedF1.rotateAngleX = 0.223693F;
		FeedF1.rotateAngleY = -1.865655F;
		FeedF1.rotateAngleZ = 0.009323301F;

		FeedG1.rotateAngleX = 0.2029555F;
		FeedG1.rotateAngleY = -1.465355F;
		FeedG1.rotateAngleZ = 0.09517268F;

		FeedH1.rotateAngleX = 0.286131F;
		FeedH1.rotateAngleY = 2.831252F;
		FeedH1.rotateAngleZ = 0.4113167F;
		
		FeedA2.rotateAngleX = 0.5566564F;
		FeedA2.rotateAngleY = 0.6934777F;
		FeedA2.rotateAngleZ = 0.418777F;

		FeedB2.rotateAngleX = 0.3321232F;
		FeedB2.rotateAngleY = 1.126472F;
		FeedB2.rotateAngleZ = 0.4568707F;

		FeedC2.rotateAngleX = 0.5561382F;
		FeedC2.rotateAngleY = 0.6948478F;
		FeedC2.rotateAngleZ = 0.4195006F;

		FeedD2.rotateAngleX = 0.329293F;
		FeedD2.rotateAngleY = -2.02634F;
		FeedD2.rotateAngleZ = 0.4998787F;

		FeedE2.rotateAngleX = 0.329293F;
		FeedE2.rotateAngleY = -2.02634F;
		FeedE2.rotateAngleZ = 0.3414953F;

		FeedF2.rotateAngleX = 0.223693F;
		FeedF2.rotateAngleY = -1.865655F;
		FeedF2.rotateAngleZ = 0.009323301F;

		FeedG2.rotateAngleX = 0.2029555F;
		FeedG2.rotateAngleY = -1.465355F;
		FeedG2.rotateAngleZ = 0.09517268F;

		FeedH2.rotateAngleX = 0.286131F;
		FeedH2.rotateAngleY = 2.831252F;
		FeedH2.rotateAngleZ = 0.4113167F;

	}



}

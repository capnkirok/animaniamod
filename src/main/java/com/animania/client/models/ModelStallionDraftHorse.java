package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelStallionDraftHorse extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer Head = new ModelRenderer(this, 0, 0);
	ModelRenderer Body;
	ModelRenderer tailA;
	ModelRenderer tailB;
	ModelRenderer tailC;
	ModelRenderer tailD;
	ModelRenderer BackLeftMuscle;
	ModelRenderer BackLeftA;
	ModelRenderer BackLeftB;
	ModelRenderer BackLeftHoof;
	ModelRenderer BackLeftHoof2;
	ModelRenderer BackLeftFluff;
	ModelRenderer BackLeftFluffb;
	ModelRenderer BackRightMuscle;
	ModelRenderer BackRightA;
	ModelRenderer BackRightB;
	ModelRenderer BackRightHoof;
	ModelRenderer BackRightHoof2;
	ModelRenderer BackRightFluff;
	ModelRenderer BackRightFluffb;
	ModelRenderer FrontLeftMuscle;
	ModelRenderer FrontLeftA;
	ModelRenderer FrontLeftB;
	ModelRenderer FrontLeftHoof;
	ModelRenderer FrontLeftHoof2;
	ModelRenderer FrontLeftFluff;
	ModelRenderer FrontLeftFluffb;
	ModelRenderer FrontRightMuscle;
	ModelRenderer FrontRightA;
	ModelRenderer FrontRightB;
	ModelRenderer FrontRightHoof;
	ModelRenderer FrontRightHoof2;
	ModelRenderer FrontRightFluff;
	ModelRenderer FrontRightFluffb;
	ModelRenderer neck_0047;

	ModelRenderer Nose;
	ModelRenderer Mouth;
	ModelRenderer Cheek;
	ModelRenderer Cheek2;
	ModelRenderer EarR;
	ModelRenderer EarL;
	ModelRenderer Fleco;
	ModelRenderer Fleco2;
	ModelRenderer Fleco3;
	ModelRenderer Fleco4;
	ModelRenderer Mane;
	ModelRenderer Block1;
	ModelRenderer Block2;


	//protected float field_78145_g = 8.0F;
	//protected float field_78151_h = 4.0F;

	public ModelStallionDraftHorse()
	{
		Body = new ModelRenderer( this, 0, 0 );
		Body.setTextureSize( 128, 128 );
		Body.addBox( -7F, -7F, -16F, 14, 14, 32);
		Body.setRotationPoint( 0F, -3F, 3F );
		
		tailA = new ModelRenderer( this, 108, 108 );
		tailA.setTextureSize( 128, 128 );
		tailA.addBox( -1F, -1F, 0F, 2, 2, 3);
		tailA.setRotationPoint( 0F, -10F, 19F );
		
		tailB = new ModelRenderer( this, 102, 115 );
		tailB.setTextureSize( 128, 128 );
		tailB.addBox( -1.5F, -2F, 3F, 3, 4, 7);
		tailB.setRotationPoint( 0F, -10F + 10F, 19F - 19F );
		
		tailC = new ModelRenderer( this, 88, 111 );
		tailC.setTextureSize( 128, 128 );
		tailC.addBox( -1.5F, -4.5F, 9F, 3, 4, 7);
		tailC.setRotationPoint( 0F, -10F + 10F, 19F  - 19F);
		
		tailD = new ModelRenderer( this, 54, 56 );
		tailD.setTextureSize( 128, 128 );
		tailD.addBox( -2F, -2.5F, 0.5F, 4, 5, 11);
		tailD.setRotationPoint( 0F, 4.186213F + 10F, 24.47736F - 19F);
		
		BackLeftMuscle = new ModelRenderer( this, 60, 2 );
		BackLeftMuscle.setTextureSize( 128, 128 );
		BackLeftMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		BackLeftMuscle.setRotationPoint( 5F, -1F, 14F );
		BackLeftA = new ModelRenderer( this, 1, 67 );
		BackLeftA.setTextureSize( 128, 128 );
		BackLeftA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		BackLeftA.setRotationPoint( 5.5F, 3.5F, 15F );
		BackLeftB = new ModelRenderer( this, 77, 72 );
		BackLeftB.setTextureSize( 128, 128 );
		BackLeftB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		BackLeftB.setRotationPoint( 5.5F, 12.84808F, 16.73648F );
		BackLeftHoof = new ModelRenderer( this, 75, 84 );
		BackLeftHoof.setTextureSize( 128, 128 );
		BackLeftHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		BackLeftHoof.setRotationPoint( 5.5F, 13.98481F, 15.67365F );
		BackLeftHoof2 = new ModelRenderer( this, 97, 92 );
		BackLeftHoof2.setTextureSize( 128, 128 );
		BackLeftHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		BackLeftHoof2.setRotationPoint( 5.5F, 15.98481F, 15.67365F );
		BackLeftFluff = new ModelRenderer( this, 74, 49 );
		BackLeftFluff.setTextureSize( 128, 128 );
		BackLeftFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackLeftFluff.setRotationPoint( 6F, 15.16499F, 18.3588F );
		BackLeftFluffb = new ModelRenderer( this, 74, 49 );
		BackLeftFluffb.setTextureSize( 128, 128 );
		BackLeftFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackLeftFluffb.setRotationPoint( 5.000259F, 15.18772F, 18.3578F );
		BackRightMuscle = new ModelRenderer( this, 60, 2 );
		BackRightMuscle.setTextureSize( 128, 128 );
		BackRightMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		BackRightMuscle.setRotationPoint( -4F, -1F, 14F );
		BackRightA = new ModelRenderer( this, 23, 67 );
		BackRightA.setTextureSize( 128, 128 );
		BackRightA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		BackRightA.setRotationPoint( -4.5F, 3.5F, 15F );
		BackRightB = new ModelRenderer( this, 93, 72 );
		BackRightB.setTextureSize( 128, 128 );
		BackRightB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		BackRightB.setRotationPoint( -4.499999F, 13F, 16.56283F );
		BackRightHoof = new ModelRenderer( this, 75, 84 );
		BackRightHoof.setTextureSize( 128, 128 );
		BackRightHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		BackRightHoof.setRotationPoint( -4.499999F, 14.13673F, 15.5F );
		BackRightHoof2 = new ModelRenderer( this, 97, 92 );
		BackRightHoof2.setTextureSize( 128, 128 );
		BackRightHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		BackRightHoof2.setRotationPoint( -4.499999F, 16.13673F, 15.5F );
		BackRightFluff = new ModelRenderer( this, 74, 49 );
		BackRightFluff.setTextureSize( 128, 128 );
		BackRightFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackRightFluff.setRotationPoint( -3.999999F, 15.16499F, 18.3588F );
		BackRightFluffb = new ModelRenderer( this, 74, 49 );
		BackRightFluffb.setTextureSize( 128, 128 );
		BackRightFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackRightFluffb.setRotationPoint( -4.99974F, 15.18772F, 18.3578F );
		FrontLeftMuscle = new ModelRenderer( this, 60, 2 );
		FrontLeftMuscle.setTextureSize( 128, 128 );
		FrontLeftMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		FrontLeftMuscle.setRotationPoint( 5F, -1F, -8F );
		FrontLeftA = new ModelRenderer( this, 1, 67 );
		FrontLeftA.setTextureSize( 128, 128 );
		FrontLeftA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		FrontLeftA.setRotationPoint( 5.5F, 3F, -9F );
		FrontLeftB = new ModelRenderer( this, 77, 72 );
		FrontLeftB.setTextureSize( 128, 128 );
		FrontLeftB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		FrontLeftB.setRotationPoint( 5.5F, 12.84808F, -9F );
		FrontLeftHoof = new ModelRenderer( this, 75, 84 );
		FrontLeftHoof.setTextureSize( 128, 128 );
		FrontLeftHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		FrontLeftHoof.setRotationPoint( 5.5F, 13.98481F, -9F );
		FrontLeftHoof2 = new ModelRenderer( this, 73, 92 );
		FrontLeftHoof2.setTextureSize( 128, 128 );
		FrontLeftHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		FrontLeftHoof2.setRotationPoint( 5.5F, 15.98481F, -9F );
		FrontLeftFluff = new ModelRenderer( this, 74, 49 );
		FrontLeftFluff.setTextureSize( 128, 128 );
		FrontLeftFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontLeftFluff.setRotationPoint( 6F, 15.16499F, -6F );
		FrontLeftFluffb = new ModelRenderer( this, 74, 49 );
		FrontLeftFluffb.setTextureSize( 128, 128 );
		FrontLeftFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontLeftFluffb.setRotationPoint( 5.015076F, 15.18012F, -6.172327F );
		FrontRightMuscle = new ModelRenderer( this, 60, 2 );
		FrontRightMuscle.setTextureSize( 128, 128 );
		FrontRightMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		FrontRightMuscle.setRotationPoint( -4F, -1F, -8F );
		FrontRightA = new ModelRenderer( this, 23, 67 );
		FrontRightA.setTextureSize( 128, 128 );
		FrontRightA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		FrontRightA.setRotationPoint( -4.5F, 3F, -9F );
		FrontRightB = new ModelRenderer( this, 93, 72 );
		FrontRightB.setTextureSize( 128, 128 );
		FrontRightB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		FrontRightB.setRotationPoint( -4.5F, 12.84808F, -9F );
		FrontRightHoof = new ModelRenderer( this, 75, 84 );
		FrontRightHoof.setTextureSize( 128, 128 );
		FrontRightHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		FrontRightHoof.setRotationPoint( -4.5F, 13.98481F, -9F );
		FrontRightHoof2 = new ModelRenderer( this, 73, 92 );
		FrontRightHoof2.setTextureSize( 128, 128 );
		FrontRightHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		FrontRightHoof2.setRotationPoint( -4.5F, 15.98481F, -9F );
		FrontRightFluff = new ModelRenderer( this, 74, 49 );
		FrontRightFluff.setTextureSize( 128, 128 );
		FrontRightFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontRightFluff.setRotationPoint( -3.999999F, 15.16499F, -6F );
		FrontRightFluffb = new ModelRenderer( this, 74, 49 );
		FrontRightFluffb.setTextureSize( 128, 128 );
		FrontRightFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontRightFluffb.setRotationPoint( -4.999883F, 15.18012F, -5.998679F );
		neck_0047 = new ModelRenderer( this, 87, 46 );
		neck_0047.setTextureSize( 128, 128 );
		neck_0047.addBox( -4.05F, -14F, -5F, 8, 14, 10);
		neck_0047.setRotationPoint( 0F, -7F, -9F );
		
		Head = new ModelRenderer( this, 0, 46 );
		Head.setTextureSize( 128, 128 );
		Head.addBox( -4F, -4F, -6F, 8, 8, 12);
		Head.setRotationPoint( 4.842877E-08F, -18.87436F, -16.43301F );
		
		Nose = new ModelRenderer( this, 28, 46 );
		Nose.setTextureSize( 128, 128 );
		Nose.addBox( -3.5F, -2.5F, -6F, 7, 5, 6);
		Nose.setRotationPoint( 4.842877E-08F, -17.4234F +18.87436F, -21.94615F );
		
		Mouth = new ModelRenderer( this, 40, 57 );
		Mouth.setTextureSize( 128, 128 );
		Mouth.addBox( -3F, -0.5F, -6F, 6, 3, 6);
		Mouth.setRotationPoint( 4.842877E-08F - 4.842877E-08F, -16.15272F +18.87436F, -20.32277F );
		
		Cheek = new ModelRenderer( this, 94, 29 );
		Cheek.setTextureSize( 128, 128 );
		Cheek.addBox( -1F, -3.5F, -4.5F, 2, 7, 9);
		Cheek.setRotationPoint( 4F - 4.842877E-08F, -15.30978F +18.87436F, -16.10705F );
		
		Cheek2 = new ModelRenderer( this, 94, 29 );
		Cheek2.setTextureSize( 128, 128 );
		Cheek2.addBox( -1F, -3.5F, -4.5F, 2, 7, 9);
		Cheek2.setRotationPoint( -4F - 4.842877E-08F, -15.30978F +18.87436F, -16.10705F );
		
		EarR = new ModelRenderer( this, 1, 25 );
		EarR.setTextureSize( 128, 128 );
		EarR.addBox( -1.5F, -4F, -0.5F, 3, 4, 1);
		EarR.setRotationPoint( -2.5F - 4.842877E-08F, -23.40545F +18.87436F, -15.58494F );
		
		EarL = new ModelRenderer( this, 10, 25 );
		EarL.setTextureSize( 128, 128 );
		EarL.addBox( -1.5F, -4F, -0.5F, 3, 4, 1);
		EarL.setRotationPoint( 2.5F - 4.842877E-08F, -23.40545F +18.87436F, -15.58494F );
		
		Fleco = new ModelRenderer( this, 88, 113 );
		Fleco.setTextureSize( 128, 128 );
		Fleco.addBox( -0.5F, -0.5F, -4F, 1, 1, 8);
		Fleco.setRotationPoint( 0.5000001F - 4.842877E-08F, -24.77147F +18.87436F, -15.21891F );
		
		Fleco2 = new ModelRenderer( this, 104, 116 );
		Fleco2.setTextureSize( 128, 128 );
		Fleco2.addBox( -0.5F, -0.5F, -3.5F, 1, 1, 7);
		Fleco2.setRotationPoint( -0.4999999F - 4.842877E-08F, -25.02147F +18.87436F, -14.7859F );
		
		Fleco3 = new ModelRenderer( this, 103, 118 );
		Fleco3.setTextureSize( 128, 128 );
		Fleco3.addBox( -0.5F, -0.5F, -3.5F, 1, 1, 7);
		Fleco3.setRotationPoint( 1.5F - 4.842877E-08F, -25.02147F +18.87436F, -14.7859F );
		
		Fleco4 = new ModelRenderer( this, 92, 114 );
		Fleco4.setTextureSize( 128, 128 );
		Fleco4.addBox( -0.5F, -0.5F, -3F, 1, 1, 6);
		Fleco4.setRotationPoint( -1.5F - 4.842877E-08F, -25.27147F +18.87436F, -14.35288F );
		
		Mane = new ModelRenderer( this, 65, 101 );
		Mane.setTextureSize( 128, 128 );
		Mane.addBox( -1.5F, -10F, -2.5F, 3, 20, 5);
		Mane.setRotationPoint( 4.842877E-08F - 4.842877E-08F, -16.9282F +18.87436F, -7.803848F );
		
		Block1 = new ModelRenderer( this, 46, 0 );
		Block1.setTextureSize( 128, 128 );
		Block1.addBox( -2F, -2F, -1.5F, 4, 4, 3);
		Block1.setRotationPoint( 0F, 6F, 16F );
		
		Block2 = new ModelRenderer( this, 46, 13 );
		Block2.setTextureSize( 128, 128 );
		Block2.addBox( -1F, -2F, -3F, 2, 4, 6);
		Block2.setRotationPoint( 0F, 4.5F, 12F );

		this.Head.addChild(this.Nose);
		this.Head.addChild(this.Mouth);
		this.Head.addChild(this.Cheek);
		this.Head.addChild(this.Cheek2);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarR);
		this.Head.addChild(this.Fleco);
		this.Head.addChild(this.Fleco2);
		this.Head.addChild(this.Fleco3);
		this.Head.addChild(this.Fleco4);

		this.BackLeftMuscle.addChild(this.BackLeftA);
		this.BackLeftMuscle.addChild(this.BackLeftB);
		this.BackLeftMuscle.addChild(this.BackLeftHoof);
		this.BackLeftMuscle.addChild(this.BackLeftHoof2);
		this.BackLeftMuscle.addChild(this.BackLeftFluff);
		this.BackLeftMuscle.addChild(this.BackLeftFluffb);

		this.BackRightMuscle.addChild(this.BackRightA);
		this.BackRightMuscle.addChild(this.BackRightB);
		this.BackRightMuscle.addChild(this.BackRightHoof);
		this.BackRightMuscle.addChild(this.BackRightHoof2);
		this.BackRightMuscle.addChild(this.BackRightFluff);
		this.BackRightMuscle.addChild(this.BackRightFluffb);

		this.FrontLeftMuscle.addChild(this.FrontLeftA);
		this.FrontLeftMuscle.addChild(this.FrontLeftB);
		this.FrontLeftMuscle.addChild(this.FrontLeftHoof);
		this.FrontLeftMuscle.addChild(this.FrontLeftHoof2);
		this.FrontLeftMuscle.addChild(this.FrontLeftFluff);
		this.FrontLeftMuscle.addChild(this.FrontLeftFluffb);

		this.FrontRightMuscle.addChild(this.FrontRightA);
		this.FrontRightMuscle.addChild(this.FrontRightB);
		this.FrontRightMuscle.addChild(this.FrontRightHoof);
		this.FrontRightMuscle.addChild(this.FrontRightHoof2);
		this.FrontRightMuscle.addChild(this.FrontRightFluff);
		this.FrontRightMuscle.addChild(this.FrontRightFluffb);

		this.tailA.addChild(this.tailB);
		this.tailA.addChild(this.tailC);
		this.tailA.addChild(this.tailD);

	}


	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{


		tailA.rotateAngleX = -1.105676F;
		tailB.rotateAngleX = -1.105676F;
		tailC.rotateAngleX = -1.367476F;
		tailD.rotateAngleX = -1.507424F;
		BackLeftA.rotateAngleX = 0.1745329F;
		BackLeftB.rotateAngleX = -0.1745329F;
		BackLeftHoof.rotateAngleX = 4.00787E-08F;
		BackLeftHoof2.rotateAngleX = 4.007869E-08F;
		BackLeftFluff.rotateAngleX = 0.08790723F;
		BackLeftFluff.rotateAngleY = 0.08461326F;
		BackLeftFluff.rotateAngleZ = -0.01519369F;
		BackLeftFluffb.rotateAngleX = 0.08393065F;
		BackLeftFluffb.rotateAngleY = -0.09052216F;
		BackLeftFluffb.rotateAngleZ = -0.0302609F;
		BackRightA.rotateAngleX = 0.1745329F;
		BackRightB.rotateAngleX = -0.1745329F;
		BackRightHoof.rotateAngleX = 4.00787E-08F;
		BackRightHoof2.rotateAngleX = 4.007869E-08F;
		BackRightFluff.rotateAngleX = 0.08790723F;
		BackRightFluff.rotateAngleY = 0.08461326F;
		BackRightFluff.rotateAngleZ = -0.01519369F;
		BackRightFluffb.rotateAngleX = 0.08393065F;
		BackRightFluffb.rotateAngleY = -0.09052216F;
		BackRightFluffb.rotateAngleZ = -0.0302609F;
		FrontLeftFluff.rotateAngleX = 0.1745329F;
		FrontLeftFluff.rotateAngleY = -0.08726647F;
		FrontLeftFluff.rotateAngleZ = 1.599395E-10F;
		FrontLeftFluffb.rotateAngleX = 0.1718547F;
		FrontLeftFluffb.rotateAngleY = -0.2644362F;
		FrontLeftFluffb.rotateAngleZ = -0.03060928F;
		FrontRightFluff.rotateAngleX = 0.1745329F;
		FrontRightFluff.rotateAngleY = 0.08726647F;
		FrontRightFluff.rotateAngleZ = -1.599395E-10F;
		FrontRightFluffb.rotateAngleX = 0.1718547F;
		FrontRightFluffb.rotateAngleY = -0.08990332F;
		FrontRightFluffb.rotateAngleZ = -0.03060928F;
		neck_0047.rotateAngleX = 0.5235988F;
		Head.rotateAngleX = 0.5235988F;
		Nose.rotateAngleX = 0.6616941F;
		Mouth.rotateAngleX = 0.836227F;
		Cheek2.rotateAngleX = 1.489178E-08F;
		EarR.rotateAngleX = 0.5040353F;
		EarR.rotateAngleY = -0.3000592F;
		EarR.rotateAngleZ = -0.4101312F;
		EarL.rotateAngleX = 0.5040353F;
		EarL.rotateAngleY = 0.3000592F;
		EarL.rotateAngleZ = 0.4101312F;
		Fleco.rotateAngleX = 0.5235988F;
		Fleco2.rotateAngleX = 0.5235988F;
		Fleco3.rotateAngleX = 0.5235988F;
		Fleco4.rotateAngleX = 0.5235988F;
		Mane.rotateAngleX = 0.5235988F;
		Block2.rotateAngleX = 0.2617994F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);



		tailA.render(scale);

		BackLeftMuscle.render(scale);
		BackLeftA.render(scale);
		BackLeftB.render(scale);
		BackLeftHoof.render(scale);
		BackLeftHoof2.render(scale);
		BackLeftFluff.render(scale);
		BackLeftFluffb.render(scale);

		BackRightMuscle.render(scale);
		BackRightA.render(scale);
		BackRightB.render(scale);
		BackRightHoof.render(scale);
		BackRightHoof2.render(scale);
		BackRightFluff.render(scale);
		BackRightFluffb.render(scale);

		FrontLeftMuscle.render(scale);
		FrontLeftA.render(scale);
		FrontLeftB.render(scale);
		FrontLeftHoof.render(scale);
		FrontLeftHoof2.render(scale);
		FrontLeftFluff.render(scale);
		FrontLeftFluffb.render(scale);

		FrontRightMuscle.render(scale);
		FrontRightA.render(scale);
		FrontRightB.render(scale);
		FrontRightHoof.render(scale);
		FrontRightHoof2.render(scale);
		FrontRightFluff.render(scale);
		FrontRightFluffb.render(scale);

		neck_0047.render(scale);
		Head.render(scale);

		Block1.render(scale);

		Block2.render(scale);



	}


	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		/*
		if (entitylivingbaseIn instanceof EntityStallionDraftHorse) {
			this.Head.rotationPointY = 6.0F + ((EntityStallionDraftHorse)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityStallionDraftHorse)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} 
		 */
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity scale)
	{
		float f6 = (180F / (float)Math.PI);

		this.Body.rotateAngleX = ((float)Math.PI / 2F);


		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;

		this.tailA.rotateAngleX = ((float)Math.PI / 2F);
		this.tailA.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.FrontLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.FrontRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}

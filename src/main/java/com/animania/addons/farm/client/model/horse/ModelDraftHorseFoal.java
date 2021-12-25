package com.animania.addons.farm.client.model.horse;

import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityFoalDraftHorse;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelDraftHorseFoal extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer TailNode;
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

	ModelRenderer Neck;
	ModelRenderer Head;
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

	public ModelDraftHorseFoal()
	{
		this.Body = new ModelRenderer(this, 0, 0);
		this.Body.setTextureSize(256, 128);
		this.Body.addBox(-7F, -7F, -16F, 14, 14, 32);
		this.Body.setRotationPoint(0F, -3F, 3F);

		this.TailNode = new ModelRenderer(this, 108, 108);
		this.TailNode.setTextureSize(256, 128);
		this.TailNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.TailNode.setRotationPoint(0F, -10F, 19F);

		this.tailA = new ModelRenderer(this, 108, 108);
		this.tailA.setTextureSize(256, 128);
		this.tailA.addBox(-1F, -1F, 0F, 2, 2, 3);
		this.tailA.setRotationPoint(0F, 0F, 0F);

		this.tailB = new ModelRenderer(this, 102, 115);
		this.tailB.setTextureSize(256, 128);
		this.tailB.addBox(-1.5F, -2F, 3F, 3, 4, 7);
		this.tailB.setRotationPoint(0F, -10F + 10F, 19F - 19F);

		this.tailC = new ModelRenderer(this, 88, 111);
		this.tailC.setTextureSize(256, 128);
		this.tailC.addBox(-1.5F, -4.5F, 9F, 3, 4, 7);
		this.tailC.setRotationPoint(0F, -10F + 10F, 19F - 19F);

		this.tailD = new ModelRenderer(this, 54, 56);
		this.tailD.setTextureSize(256, 128);
		this.tailD.addBox(-2F, -2.5F, 0.5F, 4, 5, 11);
		this.tailD.setRotationPoint(0F, 4.186213F + 10F, 24.47736F - 19F);

		this.BackLeftMuscle = new ModelRenderer(this, 60, 2);
		this.BackLeftMuscle.setTextureSize(256, 128);
		this.BackLeftMuscle.addBox(-4F, -6F, -6F, 7, 12, 12);
		this.BackLeftMuscle.setRotationPoint(5F, -1F, 14F);

		this.BackLeftA = new ModelRenderer(this, 1, 67);
		this.BackLeftA.setTextureSize(256, 128);
		this.BackLeftA.addBox(-3F, 0.5F, -3F, 5, 9, 6);
		this.BackLeftA.setRotationPoint(5.5F - 5F, 3.5F + 1F, 15F - 14F);

		this.BackLeftB = new ModelRenderer(this, 77, 72);
		this.BackLeftB.setTextureSize(256, 128);
		this.BackLeftB.addBox(-2.5F, -1F, -2F, 4, 8, 4);
		this.BackLeftB.setRotationPoint(5.5F - 5F, 12.84808F + 1F, 16.73648F - 14F);

		this.BackLeftHoof = new ModelRenderer(this, 75, 84);
		this.BackLeftHoof.setTextureSize(256, 128);
		this.BackLeftHoof.addBox(-3F, 5.1F, -2.5F, 5, 3, 5);
		this.BackLeftHoof.setRotationPoint(5.5F - 5F, 13.98481F + 1F, 15.67365F - 14F);

		this.BackLeftHoof2 = new ModelRenderer(this, 97, 92);
		this.BackLeftHoof2.setTextureSize(256, 128);
		this.BackLeftHoof2.addBox(-3.5F, 5.1F, -3F, 6, 3, 6);
		this.BackLeftHoof2.setRotationPoint(5.5F - 5F, 15.98481F + 1F, 15.67365F - 14F);

		this.BackLeftFluff = new ModelRenderer(this, 74, 49);
		this.BackLeftFluff.setTextureSize(256, 128);
		this.BackLeftFluff.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.BackLeftFluff.setRotationPoint(6 - 5F, 15.16499F + 1F, 18.3588F - 14F);

		this.BackLeftFluffb = new ModelRenderer(this, 74, 49);
		this.BackLeftFluffb.setTextureSize(256, 128);
		this.BackLeftFluffb.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.BackLeftFluffb.setRotationPoint(5.000259F - 5F, 15.18772F + 1F, 18.3578F - 14F);

		this.BackRightMuscle = new ModelRenderer(this, 60, 2);
		this.BackRightMuscle.setTextureSize(256, 128);
		this.BackRightMuscle.addBox(-4F, -6F, -6F, 7, 12, 12);
		this.BackRightMuscle.setRotationPoint(-4F, -1F, 14F);

		this.BackRightA = new ModelRenderer(this, 23, 67);
		this.BackRightA.setTextureSize(256, 128);
		this.BackRightA.addBox(-3F, 0.5F, -3F, 5, 9, 6);
		this.BackRightA.setRotationPoint(-4.5F + 4F, 3.5F + 1F, 15F - 14F);

		this.BackRightB = new ModelRenderer(this, 93, 72);
		this.BackRightB.setTextureSize(256, 128);
		this.BackRightB.addBox(-2.5F, -1F, -2F, 4, 8, 4);
		this.BackRightB.setRotationPoint(-4.499999F + 4F, 13F + 1, 16.56283F - 14F);

		this.BackRightHoof = new ModelRenderer(this, 75, 84);
		this.BackRightHoof.setTextureSize(256, 128);
		this.BackRightHoof.addBox(-3F, 5.1F, -2.5F, 5, 3, 5);
		this.BackRightHoof.setRotationPoint(-4.499999F + 4F, 14.13673F + 1F, 15.5F - 14F);

		this.BackRightHoof2 = new ModelRenderer(this, 97, 92);
		this.BackRightHoof2.setTextureSize(256, 128);
		this.BackRightHoof2.addBox(-3.5F, 5.1F, -3F, 6, 3, 6);
		this.BackRightHoof2.setRotationPoint(-4.499999F + 4F, 16.13673F + 1F, 15.5F - 14F);

		this.BackRightFluff = new ModelRenderer(this, 74, 49);
		this.BackRightFluff.setTextureSize(256, 128);
		this.BackRightFluff.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.BackRightFluff.setRotationPoint(-3.999999F + 4F, 15.16499F + 1F, 18.3588F - 14F);

		this.BackRightFluffb = new ModelRenderer(this, 74, 49);
		this.BackRightFluffb.setTextureSize(256, 128);
		this.BackRightFluffb.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.BackRightFluffb.setRotationPoint(-4.99974F + 4F, 15.18772F + 1F, 18.3578F - 14F);

		this.FrontLeftMuscle = new ModelRenderer(this, 60, 2);
		this.FrontLeftMuscle.setTextureSize(256, 128);
		this.FrontLeftMuscle.addBox(-4F, -6F, -6F, 7, 12, 12);
		this.FrontLeftMuscle.setRotationPoint(5F, -1F, -8F);

		this.FrontLeftA = new ModelRenderer(this, 1, 67);
		this.FrontLeftA.setTextureSize(256, 128);
		this.FrontLeftA.addBox(-3F, 0.5F, -3F, 5, 9, 6);
		this.FrontLeftA.setRotationPoint(5.5F - 5F, 3F + 1F, -9F + 8F);

		this.FrontLeftB = new ModelRenderer(this, 77, 72);
		this.FrontLeftB.setTextureSize(256, 128);
		this.FrontLeftB.addBox(-2.5F, -1F, -2F, 4, 8, 4);
		this.FrontLeftB.setRotationPoint(5.5F - 5F, 12.84808F + 1F, -9F + 8F);

		this.FrontLeftHoof = new ModelRenderer(this, 75, 84);
		this.FrontLeftHoof.setTextureSize(256, 128);
		this.FrontLeftHoof.addBox(-3F, 5.1F, -2.5F, 5, 3, 5);
		this.FrontLeftHoof.setRotationPoint(5.5F - 5F, 13.98481F + 1F, -9F + 8F);

		this.FrontLeftHoof2 = new ModelRenderer(this, 73, 92);
		this.FrontLeftHoof2.setTextureSize(256, 128);
		this.FrontLeftHoof2.addBox(-3.5F, 5.1F, -3F, 6, 3, 6);
		this.FrontLeftHoof2.setRotationPoint(5.5F - 5F, 15.98481F + 1F, -9F + 8F);

		this.FrontLeftFluff = new ModelRenderer(this, 74, 49);
		this.FrontLeftFluff.setTextureSize(256, 128);
		this.FrontLeftFluff.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.FrontLeftFluff.setRotationPoint(6F - 5F, 15.16499F + 1F, -6F + 8F);

		this.FrontLeftFluffb = new ModelRenderer(this, 74, 49);
		this.FrontLeftFluffb.setTextureSize(256, 128);
		this.FrontLeftFluffb.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.FrontLeftFluffb.setRotationPoint(5.015076F - 5F, 15.18012F + 1F, -6.172327F + 8F);

		this.FrontRightMuscle = new ModelRenderer(this, 60, 2);
		this.FrontRightMuscle.setTextureSize(256, 128);
		this.FrontRightMuscle.addBox(-4F, -6F, -6F, 7, 12, 12);
		this.FrontRightMuscle.setRotationPoint(-4F, -1F, -8F);

		this.FrontRightA = new ModelRenderer(this, 23, 67);
		this.FrontRightA.setTextureSize(256, 128);
		this.FrontRightA.addBox(-3F, 0.5F, -3F, 5, 9, 6);
		this.FrontRightA.setRotationPoint(-4.5F + 4F, 3F + 1F, -9F + 8F);

		this.FrontRightB = new ModelRenderer(this, 93, 72);
		this.FrontRightB.setTextureSize(256, 128);
		this.FrontRightB.addBox(-2.5F, -1F, -2F, 4, 8, 4);
		this.FrontRightB.setRotationPoint(-4.5F + 4F, 12.84808F + 1F, -9F + 8F);

		this.FrontRightHoof = new ModelRenderer(this, 75, 84);
		this.FrontRightHoof.setTextureSize(256, 128);
		this.FrontRightHoof.addBox(-3F, 5.1F, -2.5F, 5, 3, 5);
		this.FrontRightHoof.setRotationPoint(-4.5F + 4F, 13.98481F + 1F, -9F + 8F);

		this.FrontRightHoof2 = new ModelRenderer(this, 73, 92);
		this.FrontRightHoof2.setTextureSize(256, 128);
		this.FrontRightHoof2.addBox(-3.5F, 5.1F, -3F, 6, 3, 6);
		this.FrontRightHoof2.setRotationPoint(-4.5F + 4F, 15.98481F + 1F, -9F + 8F);

		this.FrontRightFluff = new ModelRenderer(this, 74, 49);
		this.FrontRightFluff.setTextureSize(256, 128);
		this.FrontRightFluff.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.FrontRightFluff.setRotationPoint(-3.999999F + 4F, 15.16499F + 1F, -6F + 8F);

		this.FrontRightFluffb = new ModelRenderer(this, 74, 49);
		this.FrontRightFluffb.setTextureSize(256, 128);
		this.FrontRightFluffb.addBox(-1F, -1.5F, -2F, 1, 9, 4);
		this.FrontRightFluffb.setRotationPoint(-4.999883F + 4F, 15.18012F + 1F, -5.998679F + 8F);

		this.HeadNode = new ModelRenderer(this, 87, 46);
		this.HeadNode.setTextureSize(256, 128);
		this.HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.HeadNode.setRotationPoint(0F, -7F, -9F);

		this.Neck = new ModelRenderer(this, 87, 46);
		this.Neck.setTextureSize(256, 128);
		this.Neck.addBox(-4.05F, -14F, -5F, 8, 14, 10);
		this.Neck.setRotationPoint(0F, 0F, 0F);

		this.Head = new ModelRenderer(this, 0, 46);
		this.Head.setTextureSize(256, 128);
		this.Head.addBox(-4F, -4F, -6F, 8, 8, 12);
		this.Head.setRotationPoint(4.842877E-08F, -18.87436F + 7F, -16.43301F + 9F);

		this.Nose = new ModelRenderer(this, 28, 46);
		this.Nose.setTextureSize(256, 128);
		this.Nose.addBox(-3.5F, -2.5F, -6F, 7, 5, 6);
		this.Nose.setRotationPoint(4.842877E-08F, -17.4234F + 7F, -21.94615F + 9F);

		this.Mouth = new ModelRenderer(this, 40, 57);
		this.Mouth.setTextureSize(256, 128);
		this.Mouth.addBox(-3F, -0.5F, -6F, 6, 3, 6);
		this.Mouth.setRotationPoint(4.842877E-08F, -16.15272F + 7F, -20.32277F + 9F);

		this.Cheek = new ModelRenderer(this, 94, 29);
		this.Cheek.setTextureSize(256, 128);
		this.Cheek.addBox(-1F, -3.5F, -4.5F, 2, 7, 9);
		this.Cheek.setRotationPoint(4F, -15.30978F + 7F, -16.10705F + 9F);

		this.Cheek2 = new ModelRenderer(this, 94, 29);
		this.Cheek2.setTextureSize(256, 128);
		this.Cheek2.addBox(-1F, -3.5F, -4.5F, 2, 7, 9);
		this.Cheek2.setRotationPoint(-4F, -15.30978F + 7F, -16.10705F + 9F);

		this.EarR = new ModelRenderer(this, 1, 25);
		this.EarR.setTextureSize(256, 128);
		this.EarR.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		this.EarR.setRotationPoint(-2.5F, -23.40545F + 7F, -15.58494F + 9F);

		this.EarL = new ModelRenderer(this, 10, 25);
		this.EarL.setTextureSize(256, 128);
		this.EarL.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		this.EarL.setRotationPoint(2.5F, -23.40545F + 7F, -15.58494F + 9F);

		this.Fleco = new ModelRenderer(this, 88, 113);
		this.Fleco.setTextureSize(256, 128);
		this.Fleco.addBox(-0.5F, -0.5F, -4F, 1, 1, 8);
		this.Fleco.setRotationPoint(0.5000001F, -24.77147F + 7F, -15.21891F + 9F);

		this.Fleco2 = new ModelRenderer(this, 104, 116);
		this.Fleco2.setTextureSize(256, 128);
		this.Fleco2.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 7);
		this.Fleco2.setRotationPoint(-0.4999999F, -25.02147F + 7F, -14.7859F + 9F);

		this.Fleco3 = new ModelRenderer(this, 103, 118);
		this.Fleco3.setTextureSize(256, 128);
		this.Fleco3.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 7);
		this.Fleco3.setRotationPoint(1.5F, -25.02147F + 7F, -14.7859F + 9F);

		this.Fleco4 = new ModelRenderer(this, 92, 114);
		this.Fleco4.setTextureSize(256, 128);
		this.Fleco4.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
		this.Fleco4.setRotationPoint(-1.5F, -25.27147F + 7F, -14.35288F + 9F);

		this.Mane = new ModelRenderer(this, 65, 101);
		this.Mane.setTextureSize(256, 128);
		this.Mane.addBox(-1.5F, -10F, -2.5F, 3, 20, 5);
		this.Mane.setRotationPoint(4.842877E-08F, -16.9282F + 7F, -7.803848F + 9F);

		this.HeadNode.addChild(this.Neck);
		this.HeadNode.addChild(this.Head);
		this.HeadNode.addChild(this.Nose);
		this.HeadNode.addChild(this.Mouth);
		// this.HeadNode.addChild(this.Cheek);
		// this.HeadNode.addChild(this.Cheek2);
		this.HeadNode.addChild(this.EarL);
		this.HeadNode.addChild(this.EarR);
		this.HeadNode.addChild(this.Fleco);
		this.HeadNode.addChild(this.Fleco2);
		this.HeadNode.addChild(this.Fleco3);
		this.HeadNode.addChild(this.Fleco4);
		this.HeadNode.addChild(this.Mane);

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

		this.TailNode.addChild(this.tailA);
		this.TailNode.addChild(this.tailB);
		this.TailNode.addChild(this.tailC);
		this.TailNode.addChild(this.tailD);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.tailA.rotateAngleX = -1.105676F;
		this.tailB.rotateAngleX = -1.105676F;
		this.tailC.rotateAngleX = -1.367476F;
		this.tailD.rotateAngleX = -1.507424F;
		this.BackLeftA.rotateAngleX = 0.1745329F;
		this.BackLeftB.rotateAngleX = -0.1745329F;
		this.BackLeftHoof.rotateAngleX = 4.00787E-08F;
		this.BackLeftHoof2.rotateAngleX = 4.007869E-08F;
		this.BackLeftFluff.rotateAngleX = 0.08790723F;
		this.BackLeftFluff.rotateAngleY = 0.08461326F;
		this.BackLeftFluff.rotateAngleZ = -0.01519369F;
		this.BackLeftFluffb.rotateAngleX = 0.08393065F;
		this.BackLeftFluffb.rotateAngleY = -0.09052216F;
		this.BackLeftFluffb.rotateAngleZ = -0.0302609F;
		this.BackRightA.rotateAngleX = 0.1745329F;
		this.BackRightB.rotateAngleX = -0.1745329F;
		this.BackRightHoof.rotateAngleX = 4.00787E-08F;
		this.BackRightHoof2.rotateAngleX = 4.007869E-08F;
		this.BackRightFluff.rotateAngleX = 0.08790723F;
		this.BackRightFluff.rotateAngleY = 0.08461326F;
		this.BackRightFluff.rotateAngleZ = -0.01519369F;
		this.BackRightFluffb.rotateAngleX = 0.08393065F;
		this.BackRightFluffb.rotateAngleY = -0.09052216F;
		this.BackRightFluffb.rotateAngleZ = -0.0302609F;
		this.FrontLeftFluff.rotateAngleX = 0.1745329F;
		this.FrontLeftFluff.rotateAngleY = -0.08726647F;
		this.FrontLeftFluff.rotateAngleZ = 1.599395E-10F;
		this.FrontLeftFluffb.rotateAngleX = 0.1718547F;
		this.FrontLeftFluffb.rotateAngleY = -0.2644362F;
		this.FrontLeftFluffb.rotateAngleZ = -0.03060928F;
		this.FrontRightFluff.rotateAngleX = 0.1745329F;
		this.FrontRightFluff.rotateAngleY = 0.08726647F;
		this.FrontRightFluff.rotateAngleZ = -1.599395E-10F;
		this.FrontRightFluffb.rotateAngleX = 0.1718547F;
		this.FrontRightFluffb.rotateAngleY = -0.08990332F;
		this.FrontRightFluffb.rotateAngleZ = -0.03060928F;

		this.Neck.rotateAngleX = 0.5235988F;
		this.Head.rotateAngleX = 0.5235988F;
		this.Nose.rotateAngleX = 0.6616941F;
		this.Mouth.rotateAngleX = 0.836227F;
		this.Cheek2.rotateAngleX = 1.489178E-08F;
		this.EarR.rotateAngleX = 0.5040353F;
		this.EarR.rotateAngleY = -0.3000592F;
		this.EarR.rotateAngleZ = -0.4101312F;
		this.EarL.rotateAngleX = 0.5040353F;
		this.EarL.rotateAngleY = 0.3000592F;
		this.EarL.rotateAngleZ = 0.4101312F;
		this.Fleco.rotateAngleX = 0.5235988F;
		this.Fleco2.rotateAngleX = 0.5235988F;
		this.Fleco3.rotateAngleX = 0.5235988F;
		this.Fleco4.rotateAngleX = 0.5235988F;
		this.Mane.rotateAngleX = 0.5235988F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		EntityAnimaniaHorse ech = (EntityAnimaniaHorse) entityIn;
		if (ech.getSleeping())
		{
			float sleepTimer = ech.getSleepTimer();
			this.FrontLeftMuscle.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeftMuscle.render(scale * .95F);
			this.FrontRightMuscle.rotateAngleX = sleepTimer * -1.8F;
			this.FrontRightMuscle.render(scale * .97F);
			this.BackLeftMuscle.rotateAngleX = sleepTimer * 1.7F;
			this.BackLeftMuscle.render(scale * .97F);
			this.BackRightMuscle.rotateAngleX = sleepTimer * 1.75F;
			this.BackRightMuscle.render(scale * .95F);
			this.HeadNode.rotateAngleY = sleepTimer * +2.8F;

			if (sleepTimer > -.28)
			{
				this.Body.rotateAngleX = -(sleepTimer / 3);
			}
			else
			{
				this.Body.rotateAngleX = sleepTimer / 3;
			}

		}
		else
		{

			this.BackLeftMuscle.rotateAngleZ = 0;
			this.BackLeftMuscle.render(scale);
			this.BackRightMuscle.rotateAngleZ = 0;
			this.BackRightMuscle.render(scale);
			this.FrontLeftMuscle.rotateAngleZ = 0;
			this.FrontLeftMuscle.render(scale);
			this.FrontRightMuscle.rotateAngleZ = 0;
			this.FrontRightMuscle.render(scale);
			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0;

		}

		this.Body.render(scale);

		this.TailNode.render(scale);

		this.BackLeftMuscle.render(scale);
		this.BackRightMuscle.render(scale);
		this.FrontLeftMuscle.render(scale);
		this.FrontRightMuscle.render(scale);

		this.HeadNode.render(scale);

	}

	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		Entity horse = (Entity) LivingEntityIn;

		if (!horse.isBeingRidden())
		{
			this.HeadNode.rotationPointY = -7.0F + ((EntityFoalDraftHorse) LivingEntityIn).getHeadAnchorPointY(partialTickTime) * 10.0F;
			this.headRotationAngleX = ((EntityFoalDraftHorse) LivingEntityIn).getHeadAngleX(partialTickTime);
		}
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity scale)
	{

		this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleX = this.headRotationAngleX;

		EntityFoalDraftHorse HorseEntity = (EntityFoalDraftHorse) scale;
		if (HorseEntity.eatTimer > 4 && HorseEntity.eatTimer < 160)
		{
			this.HeadNode.rotateAngleX = .687F + this.headRotationAngleX;
		}
		else
		{
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}

		// this.tailA.rotateAngleX = 1.5F;
		// this.tailA.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) *
		// MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
		this.BackRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
		this.FrontLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.0F * par2;
		this.FrontRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
	}
}

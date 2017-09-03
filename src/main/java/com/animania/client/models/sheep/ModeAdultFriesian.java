package com.animania.client.models.sheep;

import com.animania.common.entities.sheep.EntityEweFriesian;
import com.animania.common.entities.sheep.EntityRamFriesian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModeAdultFriesian extends ModelBase
{	
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer RightFrontLeg;
	ModelRenderer LeftFrontLegWool;
	ModelRenderer LeftFrontLeg;
	ModelRenderer RightFrontLegWool;
	ModelRenderer Hips;
	ModelRenderer LeftBackLeg;
	ModelRenderer RightBackLegWool;
	ModelRenderer LeftBackLeg1;
	ModelRenderer LeftBackLegWool;
	ModelRenderer Tail;
	ModelRenderer WoolHips;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer UpperJaw;
	ModelRenderer Nose;
	ModelRenderer UpperJawDetail1;
	ModelRenderer UpperJawDetail2;
	ModelRenderer LowerJaw;
	ModelRenderer LeftEar1;
	ModelRenderer LeftEar2;
	ModelRenderer RightEar1;
	ModelRenderer RightEar2;
	ModelRenderer RightHorn1;
	ModelRenderer RightHorn2;
	ModelRenderer RightHorn3;
	ModelRenderer LeftHorn1;
	ModelRenderer LeftHorn2;
	ModelRenderer LeftHorn3;
	ModelRenderer WoolNeck1;
	ModelRenderer WoolNeck2;
	ModelRenderer WoolBody1;
	ModelRenderer WoolBody2;

	public ModeAdultFriesian()
	{
		this(0.0f);
	}

	public ModeAdultFriesian(float par1)
	{

		Body = new ModelRenderer( this, 0, 36 );
		Body.setTextureSize( 128, 128 );
		Body.addBox( -3.5F, 0F, 0F, 7, 10, 17);
		Body.setRotationPoint( 0F, 1.5F, -12F );
		RightFrontLeg = new ModelRenderer( this, 48, 0 );
		RightFrontLeg.setTextureSize( 128, 128 );
		RightFrontLeg.addBox( -3F, 0F, 0F, 3, 16, 3);
		RightFrontLeg.setRotationPoint( -2.3F, 8.048207F, -10.16398F );
		LeftFrontLegWool = new ModelRenderer( this, 0, 99 );
		LeftFrontLegWool.setTextureSize( 128, 128 );
		LeftFrontLegWool.addBox( -2.5F, 0F, -2F, 4, 9, 4);
		LeftFrontLegWool.setRotationPoint( -3.3F, 7.548208F, -8.663978F );
		LeftFrontLeg = new ModelRenderer( this, 48, 0 );
		LeftFrontLeg.setTextureSize( 128, 128 );
		LeftFrontLeg.addBox( 0F, 0F, 0F, 3, 16, 3);
		LeftFrontLeg.setRotationPoint( 2.3F, 8.048207F, -10.16398F );
		RightFrontLegWool = new ModelRenderer( this, 0, 99 );
		RightFrontLegWool.setTextureSize( 128, 128 );
		RightFrontLegWool.addBox( -2F, 0F, -2F, 4, 9, 4);
		RightFrontLegWool.setRotationPoint( 3.8F, 7.548208F, -8.663978F );
		Hips = new ModelRenderer( this, 49, 41 );
		Hips.setTextureSize( 128, 128 );
		Hips.addBox( -4.5F, 0F, 0F, 9, 10, 11);
		Hips.setRotationPoint( 0F, 1.49707F, 3.004812F );
		LeftBackLeg = new ModelRenderer( this, 31, 0 );
		LeftBackLeg.setTextureSize( 128, 128 );
		LeftBackLeg.addBox( 0F, 0F, 0.5F, 3, 19, 4);
		LeftBackLeg.setRotationPoint( 3.5F, 4.94706F, 5.92462F );
		RightBackLegWool = new ModelRenderer( this, 40, 94 );
		RightBackLegWool.setTextureSize( 128, 128 );
		RightBackLegWool.addBox( -2F, 0F, -3F, 4, 12, 6);
		RightBackLegWool.setRotationPoint( 5F, 3.94706F, 8.42462F );
		LeftBackLeg1 = new ModelRenderer( this, 31, 0 );
		LeftBackLeg1.setTextureSize( 128, 128 );
		LeftBackLeg1.addBox( -3F, 0F, 0.5F, 3, 19, 4);
		LeftBackLeg1.setRotationPoint( -3.5F, 4.94706F, 5.92462F );
		LeftBackLegWool = new ModelRenderer( this, 40, 94 );
		LeftBackLegWool.setTextureSize( 128, 128 );
		LeftBackLegWool.addBox( -2F, 0F, -3F, 4, 12, 6);
		LeftBackLegWool.setRotationPoint( -5F, 3.94706F, 8.42462F );
		Tail = new ModelRenderer( this, 0, 0 );
		Tail.setTextureSize( 128, 128 );
		Tail.addBox( -1.5F, 0F, -2F, 3, 5, 3);
		Tail.setRotationPoint( 0F, 2.032116F, 13.67688F );
		WoolHips = new ModelRenderer( this, 0, 70 );
		WoolHips.setTextureSize( 128, 128 );
		WoolHips.addBox( -5F, 0F, -0.5F, 10, 12, 12);
		WoolHips.setRotationPoint( 0F, 0.4975643F, 2.973402F );
		WoolBody1 = new ModelRenderer( this, 0, 95 );
		WoolBody1.setTextureSize( 128, 128 );
		WoolBody1.addBox( -4F, 0F, 0F, 8, 12, 18);
		WoolBody1.setRotationPoint( 0F, 0.4751854F, -12.97455F );
		WoolBody2 = new ModelRenderer( this, 53, 104 );
		WoolBody2.setTextureSize( 128, 128 );
		WoolBody2.addBox( -5F, 0F, 0F, 10, 12, 9);
		WoolBody2.setRotationPoint( 0F, 0.0004730225F, -11.96231F );

		HeadNode = new ModelRenderer(this, 0, 19);
		HeadNode.setTextureSize(128, 128);
		HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		HeadNode.setRotationPoint(0F, 3.843416F, -9.107978F);

		Neck = new ModelRenderer( this, 0, 19 );
		Neck.setTextureSize( 128, 128 );
		Neck.addBox( -2F, -1F, -10F, 4, 6, 10);
		Neck.setRotationPoint( 0F, 0F, 0F );

		Head = new ModelRenderer( this, 32, 25 );
		Head.setTextureSize( 128, 128 );
		Head.addBox( -3F, -2F, -4F, 6, 7, 5);
		Head.setRotationPoint( 0F, -0.9239368F - 3.843416F, -17.88137F + 9.107978F);
		UpperJaw = new ModelRenderer( this, 32, 39 );
		UpperJaw.setTextureSize( 128, 128 );
		UpperJaw.addBox( -2F, 0F, -5F, 4, 3, 5);
		UpperJaw.setRotationPoint( 0F, 1.478338F - 3.843416F, -21.23235F  + 9.107978F);
		Nose = new ModelRenderer( this, 0, 36 );
		Nose.setTextureSize( 128, 128 );
		Nose.addBox( 0F, 0F, 0F, 2, 2, 2);
		Nose.setRotationPoint( -1F, 3.952723F - 3.843416F, -26.14839F + 9.107978F);
		UpperJawDetail1 = new ModelRenderer( this, 0, 10 );
		UpperJawDetail1.setTextureSize( 128, 128 );
		UpperJawDetail1.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		UpperJawDetail1.setRotationPoint( 0F, -1.125036F - 3.843416F, -22.2631F + 9.107978F);
		UpperJawDetail2 = new ModelRenderer( this, 14, 0 );
		UpperJawDetail2.setTextureSize( 128, 128 );
		UpperJawDetail2.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		UpperJawDetail2.setRotationPoint( 0F, -0.1574173F - 3.843416F, -22.00577F + 9.107978F);
		LowerJaw = new ModelRenderer( this, 12, 8 );
		LowerJaw.setTextureSize( 128, 128 );
		LowerJaw.addBox( -1.5F, 0F, -5F, 3, 1, 5);
		LowerJaw.setRotationPoint( 0F, 3.953815F - 3.843416F, -19.60692F + 9.107978F);
		LeftEar1 = new ModelRenderer( this, 20, 15 );
		LeftEar1.setTextureSize( 128, 128 );
		LeftEar1.addBox( 0F, -1F, -1F, 4, 2, 1);
		LeftEar1.setRotationPoint( 2.5F, -1.109892F - 3.843416F, -17.95499F + 9.107978F);
		LeftEar2 = new ModelRenderer( this, 0, 26 );
		LeftEar2.setTextureSize( 128, 128 );
		LeftEar2.addBox( 0F, -0.5F, -1F, 1, 1, 1);
		LeftEar2.setRotationPoint( 6.166575F, -1.498089F - 3.843416F, -18.87452F + 9.107978F);
		RightEar1 = new ModelRenderer( this, 20, 15 );
		RightEar1.setTextureSize( 128, 128 );
		RightEar1.addBox( -4F, -1F, -1F, 4, 2, 1);
		RightEar1.setRotationPoint( -2.5F, -1.109892F - 3.843416F, -17.95499F + 9.107978F);
		RightEar2 = new ModelRenderer( this, 0, 26 );
		RightEar2.setTextureSize( 128, 128 );
		RightEar2.addBox( -1F, -0.5F, -1F, 1, 1, 1);
		RightEar2.setRotationPoint( -6.203258F, -1.282747F - 3.843416F, -18.78926F + 9.107978F);
		RightHorn1 = new ModelRenderer( this, 80, 15 );
		RightHorn1.setTextureSize( 128, 128 );
		RightHorn1.addBox( -5F, -1F, -1F, 5, 2, 2);
		RightHorn1.setRotationPoint( -2.1F, -1.299633F - 3.843416F, -19.10564F + 9.107978F);

		RightHorn2 = new ModelRenderer( this, 80, 15 );
		RightHorn2.setTextureSize( 128, 128 );
		RightHorn2.addBox( -6F, -1F, -1F, 6, 2, 2);
		RightHorn2.setRotationPoint( -5.204046F, -3.656723F - 3.843416F, -16.48655F + 9.107978F);

		RightHorn3 = new ModelRenderer( this, 80, 15 );
		RightHorn3.setTextureSize( 128, 128 );
		RightHorn3.addBox( -6F, -1F, -1F, 6, 2, 2);
		RightHorn3.setRotationPoint( -6.294238F, 4.2681656F - 3.843416F, -20.6277F + 9.107978F);

		LeftHorn1 = new ModelRenderer( this, 80, 15 );
		LeftHorn1.setTextureSize( 128, 128 );
		LeftHorn1.addBox( 0F, -1F, -1F, 5, 2, 2);
		LeftHorn1.setRotationPoint( 2.1F, -1.299633F - 3.843416F, -19.10564F + 9.107978F);

		LeftHorn2 = new ModelRenderer( this, 80, 15 );
		LeftHorn2.setTextureSize( 128, 128 );
		LeftHorn2.addBox( 0F, -1F, -1F, 6, 2, 2);
		LeftHorn2.setRotationPoint( 5.204046F, -3.656723F - 3.843416F, -16.48655F + 9.107978F);

		LeftHorn3 = new ModelRenderer( this, 80, 15 );
		LeftHorn3.setTextureSize( 128, 128 );
		LeftHorn3.addBox( 0F, -1F, -1F, 6, 2, 2);
		LeftHorn3.setRotationPoint( 6.294238F, 4.2681656F - 3.843416F, -20.6277F + 9.107978F);

		WoolNeck1 = new ModelRenderer( this, 45, 76 );
		WoolNeck1.setTextureSize( 128, 128 );
		WoolNeck1.addBox( -3F, 0F, -8F, 6, 8, 9);
		WoolNeck1.setRotationPoint( 0F, 2.225382F - 3.843416F, -7.932407F + 9.107978F);
		WoolNeck2 = new ModelRenderer( this, 72, 91 );
		WoolNeck2.setTextureSize( 128, 128 );
		WoolNeck2.addBox( -3.5F, -0.5F, -4F, 7, 8, 4);
		WoolNeck2.setRotationPoint( 0F, -2.040525F - 3.843416F, -14.22542F + 9.107978F);

		this.HeadNode.addChild(Head);
		this.HeadNode.addChild(Neck);
		this.HeadNode.addChild(UpperJaw);
		this.HeadNode.addChild(Nose);
		this.HeadNode.addChild(UpperJawDetail1);
		this.HeadNode.addChild(UpperJawDetail2);
		this.HeadNode.addChild(LowerJaw);
		this.HeadNode.addChild(LeftEar1);
		this.HeadNode.addChild(LeftEar2);
		this.HeadNode.addChild(RightEar1);
		this.HeadNode.addChild(RightEar2);
		//this.HeadNode.addChild(RightHorn1);
		//this.HeadNode.addChild(RightHorn2);
		//this.HeadNode.addChild(RightHorn3);
		//this.HeadNode.addChild(LeftHorn1);
		//this.HeadNode.addChild(LeftHorn2);
		//this.HeadNode.addChild(LeftHorn3);
		this.HeadNode.addChild(WoolNeck1);
		this.HeadNode.addChild(WoolNeck2);


	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		Body.rotateAngleX = -0.02513274F;
		Hips.rotateAngleX = 0.03141592F;
		LeftBackLeg.rotateAngleX = -3.724831E-09F;
		RightBackLegWool.rotateAngleX = -1.862498E-09F;
		LeftBackLeg1.rotateAngleX = -3.724831E-09F;
		LeftBackLegWool.rotateAngleX = -1.862498E-09F;
		Tail.rotateAngleX = 0.3455752F;
		WoolHips.rotateAngleX = 0.03141592F;
		Neck.rotateAngleX = -0.6283185F;
		Head.rotateAngleX = 0.3769911F;
		UpperJaw.rotateAngleX = 0.5026549F;
		Nose.rotateAngleX = 0.6283185F;
		UpperJawDetail1.rotateAngleX = 1.137256F;
		UpperJawDetail2.rotateAngleX = 0.8796461F;
		LowerJaw.rotateAngleX = 0.4398229F;
		LeftEar1.rotateAngleX = 0.3427996F;
		LeftEar1.rotateAngleY = 0.2090596F;
		LeftEar1.rotateAngleZ = -0.1086819F;
		LeftEar2.rotateAngleX = 0.3568386F;
		LeftEar2.rotateAngleY = 0.4091918F;
		LeftEar2.rotateAngleZ = -0.0398866F;
		RightEar1.rotateAngleX = 0.3307607F;
		RightEar1.rotateAngleY = -0.2059437F;
		RightEar1.rotateAngleZ = 0.04811366F;
		RightEar2.rotateAngleX = 0.3336921F;
		RightEar2.rotateAngleY = -0.4053539F;
		RightEar2.rotateAngleZ = -0.01711698F;
		RightHorn1.rotateAngleX = 0.09060038F;
		RightHorn1.rotateAngleY = 0.5898282F;
		RightHorn1.rotateAngleZ = 0.5822915F;

		RightHorn2.rotateAngleX = 0.1612098F;
		RightHorn2.rotateAngleY = 0.2702589F;
		RightHorn2.rotateAngleZ = -1.1250489F;

		RightHorn3.rotateAngleX = -0.1058653F;
		RightHorn3.rotateAngleY = 2.14474F;
		RightHorn3.rotateAngleZ = -1.849401F;

		LeftHorn1.rotateAngleX = 0.09060038F;
		LeftHorn1.rotateAngleY = -0.5898282F;
		LeftHorn1.rotateAngleZ = -0.5822915F;

		LeftHorn2.rotateAngleX = 0.1612098F;
		LeftHorn2.rotateAngleY = -0.2702589F;
		LeftHorn2.rotateAngleZ = 1.1250489F;

		LeftHorn3.rotateAngleX = -0.1058653F;
		LeftHorn3.rotateAngleY = -2.14474F;
		LeftHorn3.rotateAngleZ = 1.849401F;

		WoolNeck1.rotateAngleX = -0.6220354F;
		WoolNeck2.rotateAngleX = -0.1822123F;
		WoolBody1.rotateAngleX = -0.02513274F;
		WoolBody2.rotateAngleX = -0.02513274F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		Body.render(scale);
		HeadNode.render(scale);
		RightFrontLeg.render(scale);
		LeftFrontLeg.render(scale);
		Hips.render(scale);
		LeftBackLeg.render(scale);
		
		LeftBackLeg1.render(scale);
		Tail.render(scale);
	
		LeftFrontLegWool.render(scale);
		RightFrontLegWool.render(scale);
		RightBackLegWool.render(scale);
		LeftBackLegWool.render(scale);
		WoolHips.render(scale);
		WoolBody1.render(scale);
		WoolBody2.render(scale);
		

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entity, p_78086_2_, p_78086_3_, partialTickTime);

		if (entity instanceof EntityRamFriesian ) {
			this.HeadNode.rotationPointY = + 7.75F + ((EntityRamFriesian)entity).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityRamFriesian)entity).getHeadRotationAngleX(partialTickTime);
		} else {
			this.HeadNode.rotationPointY = + 7.75F + ((EntityEweFriesian)entity).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityEweFriesian)entity).getHeadRotationAngleX(partialTickTime);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = 180F / (float) Math.PI;



		if (entity instanceof EntityRamFriesian) {
			/*
			EntityRamFriesian eb = (EntityRamFriesian) entity;
			if (eb.getFighting() && eb.getRivalUniqueId() != null) {
				this.HeadNode.rotateAngleX = 0.687F;
			}
			else {
				this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
				this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
				this.HeadNode.rotateAngleX = this.headRotationAngleX;
			}

		}
		else {
			 */
			this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}

		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.LeftBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.8882F + (float) Math.PI) * 1.2F * par2;
		this.RightBackLegWool.rotateAngleX = MathHelper.cos(par1 * 0.8882F + (float) Math.PI) * 1.2F * par2;

		this.LeftBackLegWool.rotateAngleX = MathHelper.cos(par1 * 0.8882F) * 1.2F * par2;
		this.LeftBackLeg1.rotateAngleX = MathHelper.cos(par1 * 0.8882F) * 1.2F * par2;

		this.RightFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.8882F) * 1.2F * par2;
		this.RightFrontLegWool.rotateAngleX = MathHelper.cos(par1 * 0.8882F + (float) Math.PI) * 1.2F * par2;
		
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.8882F + (float) Math.PI) * 1.2F * par2;
		this.LeftFrontLegWool.rotateAngleX = MathHelper.cos(par1 * 0.8882F) * 1.2F * par2;

	}

}

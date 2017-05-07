package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelHen extends ModelBase
{
	private float lastLimb;
	ModelRenderer RootNode;
	ModelRenderer Body1;
	ModelRenderer Wing1;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Wing2;
	ModelRenderer leg2Pivot;
	ModelRenderer leg2Top;
	ModelRenderer leg2;
	ModelRenderer Foot2;
	ModelRenderer Foot2b;
	ModelRenderer leg1Pivot;
	ModelRenderer leg1Top;
	ModelRenderer leg1;
	ModelRenderer Foot1;
	ModelRenderer Foot1b;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer Head;
	ModelRenderer Crest;
	ModelRenderer CrestBottom;
	ModelRenderer BeakBottom;
	ModelRenderer BeakTop;


	public ModelHen()
	{
		this( 0.0f );
	}

	public ModelHen(float par1)
	{
		RootNode = new ModelRenderer( this, 16, 16 );
		RootNode.setTextureSize( 64, 32 );
		RootNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		RootNode.setRotationPoint( 0F, 15F, 0F );
		Body1 = new ModelRenderer( this, 0, 7 );
		Body1.setTextureSize( 64, 32 );
		Body1.addBox( -3F, -3F, -3F, 6, 6, 6);
		Body1.setRotationPoint( 0F, 17.99997F, 2F );
		Wing1 = new ModelRenderer( this, 30, 6 );
		Wing1.setTextureSize( 64, 32 );
		Wing1.addBox( -0.5F, -1.5F, 0F, 1, 3, 4);
		Wing1.setRotationPoint( -3.5F, 16.8998F, -1.360003F );

		Tail1 = new ModelRenderer( this, 36, 20 );
		Tail1.setTextureSize( 64, 32 );
		Tail1.addBox( -2.5F, -2F, -2F, 5, 4, 4);
		Tail1.setRotationPoint( 0F, 18.02851F, 5.041247F );

		Tail2 = new ModelRenderer( this, 38, 14 );
		Tail2.setTextureSize( 64, 32 );
		Tail2.addBox( -2F, -1F, -1.5F, 4, 2, 3);
		//Tail2.setRotationPoint( 0F, 16.76367F, 7.324377F );
		Tail2.setRotationPoint( 0F, -1.264F, 2.28313F );

		Wing2 = new ModelRenderer( this, 41, 6 );
		Wing2.setTextureSize( 64, 32 );
		Wing2.addBox( -0.5F, -1.5F, 0F, 1, 3, 4);
		Wing2.setRotationPoint( 3.5F, 16.8998F, -1.360003F );

		leg2Pivot = new ModelRenderer( this, 41, 2 );
		leg2Pivot.setTextureSize( 64, 32 );
		leg2Pivot.addBox( 0F, 0F, 0F, 0, 0, 0);
		leg2Pivot.setRotationPoint( 1.5F, 20F, 1.75F );

		leg2Top = new ModelRenderer( this, 39, 0 );
		leg2Top.setTextureSize( 64, 32 );
		leg2Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		//leg2Top.setRotationPoint( 1.5F, 21.29997F, 1.75F );
		leg2Top.setRotationPoint(0F, 1.29997F, 0F );

		leg2 = new ModelRenderer( this, 35, 0 );
		leg2.setTextureSize( 64, 32 );
		leg2.addBox( -0.5F, -2F, -0.5F, 1, 2, 1);
		//leg2.setRotationPoint( 1.5F, 24.09997F, 0.9999998F );
		leg2.setRotationPoint(0F, 4.09997F, -.7499F );

		Foot2 = new ModelRenderer( this, 25, 0 );
		Foot2.setTextureSize( 64, 32 );
		Foot2.addBox( -0.2F, -0.2F, -1.5F, 1, 1, 3);
		//Foot2.setRotationPoint( 0.5F, 23.99997F, -3.858046E-07F );
		Foot2.setRotationPoint(-1F, 3.09997F, -3.858046E-07F-1.75F );

		Foot2b = new ModelRenderer( this, 25, 0 );
		Foot2b.setTextureSize( 64, 32 );
		Foot2b.addBox(-0.2F, -0.2F, -1.5F, 1, 1, 3);
		//Foot2b.setRotationPoint( 2.5F, 23.99997F, -1.555099E-07F );
		Foot2b.setRotationPoint(1.0F, 3.09997F, -1.555099E-07F-1.75F);

		leg1Pivot = new ModelRenderer( this, 41, 2 );
		leg1Pivot.setTextureSize( 64, 32 );
		leg1Pivot.addBox( 0F, 0F, 0F, 0, 0, 0);
		leg1Pivot.setRotationPoint( -1.5F, 20F, 1.75F );

		leg1Top = new ModelRenderer( this, 39, 0 );
		leg1Top.setTextureSize( 64, 32 );
		leg1Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		//leg1Top.setRotationPoint( -1.5F, 21.29997F, 1.75F );
		leg1Top.setRotationPoint(0F, 1.29997F, 0F );

		leg1 = new ModelRenderer( this, 35, 0 );
		leg1.setTextureSize( 64, 32 );
		leg1.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
		//leg1.setRotationPoint( -1.5F, 24.09997F, 0.9999998F );
		leg1.setRotationPoint(0F, 4.09997F, -.7499F );

		Foot1 = new ModelRenderer( this, 25, 0 );
		Foot1.setTextureSize( 64, 32 );
		Foot1.addBox(-0.2F, -0.2F, -1.5F, 1, 1, 3);
		//Foot1.setRotationPoint(-2.5F, 23.99997F, -1.555099E-07F );
		Foot1.setRotationPoint(-1F, 3.09997F, -1.555099E-07F-1.75F );

		Foot1b = new ModelRenderer( this, 25, 0 );
		Foot1b.setTextureSize( 64, 32 );
		Foot1b.addBox( -0.2F, -0.2F, -1.5F, 1, 1, 3);
		//Foot1b.setRotationPoint(-0.5000002F, 23.99997F, 7.478472E-08F );
		Foot1b.setRotationPoint(1.0F, 3.09997F, 7.478472E-08F-1.75F );

		Neck = new ModelRenderer( this, 3, 19 );
		Neck.setTextureSize( 64, 32 );
		Neck.addBox( -2.5F, -2.5F, -2F, 5, 5, 4);
		Neck.setRotationPoint( 0F, 16.8998F, -1F );
		Neck2 = new ModelRenderer( this, 21, 21 );
		Neck2.setTextureSize( 64, 32 );
		Neck2.addBox( -2F, -2F, -1.5F, 4, 4, 3);
		//Neck2.setRotationPoint(0F, 16.10069F, -2.556894F );
		Neck2.setRotationPoint( 0F, -0.7998F, -1.556894F );

		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 64, 32 );
		Head.addBox( -1.5F, -2F, -1.5F, 3, 4, 3);
		//Head.setRotationPoint( 0F, 14.49997F, -3.889997F );
		Head.setRotationPoint( 0F, -3.890F, -2.889997F );

		Crest = new ModelRenderer( this, 20, 4 );
		Crest.setTextureSize( 64, 32 );
		Crest.addBox( 0F, -1.5F, -1.5F, 0, 3, 3);
		//Crest.setRotationPoint( 0F, 12.99997F, -5.139997F );
		Crest.setRotationPoint( 0F, -5.299F, -4.139997F );

		CrestBottom = new ModelRenderer( this, 22, 6 );
		CrestBottom.setTextureSize( 64, 32 );
		CrestBottom.addBox( 0F, -1F, -0.5F, 0, 2, 1);
		//CrestBottom.setRotationPoint( 0F, 15.74997F, -5.639997F );
		CrestBottom.setRotationPoint( 0F, -2.148F, -4.739997F );

		BeakBottom = new ModelRenderer( this, 19, 0 );
		BeakBottom.setTextureSize( 64, 32 );
		BeakBottom.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		//BeakBottom.setRotationPoint( 0F, 14.74997F, -5.889997F );
		BeakBottom.setRotationPoint( 0F, -3.1499F, -4.889997F );

		BeakTop = new ModelRenderer( this, 19, 0 );
		BeakTop.setTextureSize( 64, 32 );
		BeakTop.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		//BeakTop.setRotationPoint( 0F, 14.44997F, -5.789997F );
		BeakTop.setRotationPoint( 0F, -3.4499F, -4.789997F );

		this.Neck.addChild(Neck2);
		this.Neck.addChild(Head);
		this.Neck.addChild(this.BeakTop);
		this.Neck.addChild(this.BeakBottom);
		this.Neck.addChild(this.Crest);
		this.Neck.addChild(this.CrestBottom);

		this.leg1Pivot.addChild(this.leg1Top);
		this.leg1Pivot.addChild(this.leg1);
		this.leg1Pivot.addChild(this.Foot1);
		this.leg1Pivot.addChild(this.Foot1b);

		this.leg2Pivot.addChild(this.leg2Top);
		this.leg2Pivot.addChild(this.leg2);
		this.leg2Pivot.addChild(this.Foot2);
		this.leg2Pivot.addChild(this.Foot2b);

		this.Tail1.addChild(this.Tail2);

	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body1.render(scale);
		this.leg1Pivot.render(scale);
		this.leg2Pivot.render(scale);
		this.Wing1.render(scale);
		this.Wing2.render(scale);
		this.Tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}


	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, limbSwingAmount, ageInTicks, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{


		this.Body1.rotateAngleX = -0.1745329F;
		this.Crest.rotateAngleX = 0.3490659F;
		this.Tail1.rotateAngleX = 0.2144478F;
		this.Tail2.rotateAngleX = 0.5295422F;
		this.leg2Top.rotateAngleX = 0.2617994F;
		this.leg2.rotateAngleX = -0.2617995F;
		this.Foot2.rotateAngleX = -1.555994E-08F;
		this.Foot2b.rotateAngleX = -4.214685E-08F;
		this.leg1Top.rotateAngleX = 0.2617994F;
		this.leg1.rotateAngleX = -0.2617995F;
		this.Foot1.rotateAngleX = -1.555994E-08F;
		this.Foot1b.rotateAngleX = -4.214685E-08F;
		this.Neck.rotateAngleX = -0.4742105F;
		this.Neck2.rotateAngleX = -0.7360098F;
		this.Head.rotateAngleX = 0.05872217F;
		this.Crest.rotateAngleX = 0.3490659F;
		this.CrestBottom.rotateAngleX = 5.213158E-08F;
		this.BeakBottom.rotateAngleX = 0.05872219F;
		this.BeakTop.rotateAngleX = 0.3169494F;

		this.Neck.rotateAngleX = Math.abs((headPitch / (180F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float)Math.PI));

		this.Body1.rotateAngleX = ((float)Math.PI / 2F);

		this.leg1Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2Pivot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.Wing1.rotateAngleZ = ageInTicks;
		this.Wing2.rotateAngleZ = -ageInTicks;
	}

}

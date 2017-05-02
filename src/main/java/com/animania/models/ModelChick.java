package com.animania.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelChick extends ModelBase
{
	private float lastLimb;
	ModelRenderer RootNode;
	ModelRenderer Body;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer wing3;
	ModelRenderer wing4;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer BeakTop;
	ModelRenderer leg2Top;
	ModelRenderer leg2;
	ModelRenderer foot2;
	ModelRenderer leg1Top;
	ModelRenderer leg1;
	ModelRenderer foot1;


	public ModelChick()
	{
		this( 0.0f );
	}

	public ModelChick( float par1 )
	{
		RootNode = new ModelRenderer( this, 16, 16 );
		RootNode.setTextureSize( 16, 32 );
		RootNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		RootNode.setRotationPoint( 0F, 24F, 0F );
		
		Body = new ModelRenderer( this, 0, 10 );
		Body.setTextureSize( 16, 32 );
		Body.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		Body.setRotationPoint( 0F, 20.5F, 0F );
		
		tail1 = new ModelRenderer( this, 2, 16 );
		tail1.setTextureSize( 16, 32 );
		tail1.addBox( -1F, -1F, -1F, 2, 2, 2);
		tail1.setRotationPoint( 0F, 20.39043F, 1.525777F );
		
		tail2 = new ModelRenderer( this, 3, 20 );
		tail2.setTextureSize( 16, 32 );
		tail2.addBox( -1F, -0.5F, -0.5F, 2, 1, 1);
		//tail2.setRotationPoint( 0F, 19.6524F, 2.637223F );
		tail2.setRotationPoint( 0F, -073803F, 1.1111F );
		
		wing1 = new ModelRenderer( this, 2, 23 );
		wing1.setTextureSize( 16, 32 );
		wing1.addBox( -0.5F, -0.5F, 0F, 1, 1, 2);
		wing1.setRotationPoint( 1.5F, 20.17379F, -1.547121F );
		
		wing2 = new ModelRenderer( this, 2, 26 );
		wing2.setTextureSize( 16, 32 );
		wing2.addBox( -0.5F, -0.5F, 0F, 1, 1, 2);
		wing2.setRotationPoint( 1.4F, 20.66941F, -1.480338F );
		
		wing3 = new ModelRenderer( this, 2, 23 );
		wing3.setTextureSize( 16, 32 );
		wing3.addBox( -0.5F, -0.5F, 0F, 1, 1, 2);
		wing3.setRotationPoint( -1.5F, 20.17379F, -1.547121F );
		
		wing4 = new ModelRenderer( this, 2, 26 );
		wing4.setTextureSize( 16, 32 );
		wing4.addBox( -0.5F, -0.5F, 0F, 1, 1, 2);
		wing4.setRotationPoint( -1.4F, 20.66941F, -1.480338F );
		
		Neck = new ModelRenderer( this, 0, 5 );
		Neck.setTextureSize( 16, 32 );
		Neck.addBox( -1F, -1F, -1.5F, 2, 2, 3);
		//Neck.setRotationPoint( 0F, 20.1F, -0.7F );
		Neck.setRotationPoint( 0F, 20.1F, -0.7F );
		
		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 16, 32 );
		Head.addBox( -1F, -1F, -1F, 2, 2, 2);
		//Head.setRotationPoint( 0F, 18.98694F, -2.118133F );
		Head.setRotationPoint( 0F, -1.11309F, -1.418133F );
		
		BeakTop = new ModelRenderer( this, 9, 1 );
		BeakTop.setTextureSize( 16, 32 );
		BeakTop.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		//BeakTop.setRotationPoint( 0F, 19.06768F, -3.020065F );
		BeakTop.setRotationPoint( 0F, -1.03F, -2.32F );
		
		leg2Top = new ModelRenderer( this, 11, 8 );
		leg2Top.setTextureSize( 16, 32 );
		leg2Top.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		leg2Top.setRotationPoint( 0.75F, 22.1F, -2.89661E-07F );
		
		leg2 = new ModelRenderer( this, 9, 2 );
		leg2.setTextureSize( 16, 32 );
		leg2.addBox( -0.5F, -2F, -0.5F, 1, 2, 1);
		//leg2.setRotationPoint( 0.75F, 23.93978F, -0.1833103F );
		leg2.setRotationPoint( 0.0F, 1.83978F, -0.1833103F+2.89661E-07F );
		
		foot2 = new ModelRenderer( this, 9, 2 );
		foot2.setTextureSize( 16, 32 );
		foot2.addBox( -0.5F, -1F, -0.5F, 1, 1, 1);
		//foot2.setRotationPoint( 0.75F, 23.6983F, -0.1186056F );
		foot2.setRotationPoint( 0.0F, 1.5983F, -0.1186056F+2.89661E-07F );
		
		leg1Top = new ModelRenderer( this, 11, 8 );
		leg1Top.setTextureSize( 16, 32 );
		leg1Top.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		leg1Top.setRotationPoint( -0.75F, 22.1F, -2.89661E-07F );
		
		leg1 = new ModelRenderer( this, 9, 2 );
		leg1.setTextureSize( 16, 32 );
		leg1.addBox( -0.5F, -2F, -0.5F, 1, 2, 1);
		//leg1.setRotationPoint( -0.75F, 23.93978F, -0.1833104F );
		leg1.setRotationPoint(0F, 1.83978F, -0.1833104F+2.89661E-07F );
		
		foot1 = new ModelRenderer( this, 9, 2 );
		foot1.setTextureSize( 16, 32 );
		foot1.addBox( -0.5F, -1F, -0.5F, 1, 1, 1);
		//foot1.setRotationPoint( -0.75F, 23.6983F, -0.1186057F );
		foot1.setRotationPoint(0F, 1.5983F, -0.1186057F+2.89661E-07F );

		this.Neck.addChild(Head);
		this.Neck.addChild(this.BeakTop);

		this.leg1Top.addChild(this.leg1);
		this.leg1Top.addChild(this.foot1);

		this.leg2Top.addChild(this.leg2);
		this.leg2Top.addChild(this.foot2);

		this.tail1.addChild(this.tail2);
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body.render(scale);
		this.leg1Top.render(scale);
		this.leg2Top.render(scale);
		this.wing1.render(scale);
		this.wing2.render(scale);
		this.wing3.render(scale);
		this.wing4.render(scale);
		this.tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, limbSwingAmount, ageInTicks, partialTickTime);
		Random rand = new Random();
		if (limbSwingAmount > lastLimb) {
			this.Neck.rotateAngleX = Math.abs(((30F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		}  else {
			//do nothing
		}

	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		Body.rotateAngleX = -0.1257044F;
		tail1.rotateAngleX = 0.3593722F;
		tail2.rotateAngleX = 0.6340498F;
		wing1.rotateAngleX = 0.1139416F;
		wing2.rotateAngleX = 0.1139416F;
		wing3.rotateAngleX = 0.1139416F;
		wing4.rotateAngleX = 0.1139416F;
		Neck.rotateAngleX = -0.7853984F;
		Head.rotateAngleX = -0.0213736F;
		BeakTop.rotateAngleX = 0.7268012F;
		leg2Top.rotateAngleX = 0.1745329F;
		leg2.rotateAngleX = -0.2617995F;
		foot2.rotateAngleX = 1.570796F;
		leg1Top.rotateAngleX = 0.1745329F;
		leg1.rotateAngleX = -0.2617994F;
		foot1.rotateAngleX = 1.570796F;


		this.Neck.rotateAngleX = Math.abs((headPitch / (180F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float)Math.PI));
		this.Body.rotateAngleX = ((float)Math.PI / 2F);
		this.leg1Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.wing1.rotateAngleZ = ageInTicks;
		this.wing2.rotateAngleZ = -ageInTicks;
		this.wing3.rotateAngleZ = ageInTicks;
		this.wing4.rotateAngleZ = -ageInTicks;
	}

}

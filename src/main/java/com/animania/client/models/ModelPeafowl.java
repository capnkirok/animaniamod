package com.animania.client.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelPeafowl extends ModelBase
{
	private float lastLimb;
	ModelRenderer RootNode;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer Neck3;
	ModelRenderer Head;
	ModelRenderer Crest;
	ModelRenderer BeakBottom;
	ModelRenderer BeakTop;
	ModelRenderer Body1;
	ModelRenderer leg1Top;
	ModelRenderer leg1top2;
	ModelRenderer leg1bottom;
	ModelRenderer Foot1;
	ModelRenderer Foot1b;
	ModelRenderer leg2Top;
	ModelRenderer leg2top2;
	ModelRenderer leg2bottom;
	ModelRenderer Foot2;
	ModelRenderer Foot2b;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer Wing1;
	ModelRenderer Wing1a;
	ModelRenderer Wing1b;
	ModelRenderer Wing2;
	ModelRenderer Wing2a;
	ModelRenderer Wing2b;


	public ModelPeafowl()
	{
		this( 0.0f );
	}

	public ModelPeafowl(float par1)
	{
		RootNode = new ModelRenderer( this, 16, 16 );
        RootNode.setTextureSize( 64, 64 );
        RootNode.addBox( 0F, 0F, 0F, 0, 0, 0);
        RootNode.setRotationPoint( 0F, 11.5F, 0F );
       
        Neck = new ModelRenderer( this, 3, 19 );
        Neck.setTextureSize( 64, 64 );
        Neck.addBox( -2.5F, -2.5F, -2F, 5, 5, 4);
        Neck.setRotationPoint( 0F, 13.3998F, -1F );
        
        Neck2 = new ModelRenderer( this, 21, 21 );
        Neck2.setTextureSize( 64, 64 );
        Neck2.addBox( -2F, -2F, -1.5F, 4, 4, 3);
        Neck2.setRotationPoint( 0F, 11.90154F - 13.3998F, -3.01686F + 1F);
        
        Neck3 = new ModelRenderer( this, 5, 30 );
        Neck3.setTextureSize( 64, 64 );
        Neck3.addBox( -1.5F, -1.5F, -5F, 3, 3, 4);
        Neck3.setRotationPoint( 0F, 12.19275F - 13.3998F, -3.423303F + 1F);
        
        Head = new ModelRenderer( this, 0, 0 );
        Head.setTextureSize( 64, 64 );
        Head.addBox( -1.5F, -2.5F, -2F, 3, 3, 4);
        Head.setRotationPoint( 0F, 6.809536F - 13.3998F, -4.552853F  + 1F);
        
        Crest = new ModelRenderer( this, 33, 43 );
        Crest.setTextureSize( 64, 64 );
        Crest.addBox( 0F, -2.5F, -3.5F, 0, 5, 7);
        Crest.setRotationPoint( 0F, 2.514879F - 13.3998F, -4.127494F  + 1F);
        
        BeakBottom = new ModelRenderer( this, 19, 0 );
        BeakBottom.setTextureSize( 64, 64 );
        BeakBottom.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
        BeakBottom.setRotationPoint( 0F, 6.171864F - 13.3998F, -7.610052F  + 1F);
        
        BeakTop = new ModelRenderer( this, 19, 0 );
        BeakTop.setTextureSize( 64, 64 );
        BeakTop.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
        BeakTop.setRotationPoint( 0F, 5.870186F - 13.3998F, -7.515235F + 1F);
       
        Body1 = new ModelRenderer( this, 0, 7 );
        Body1.setTextureSize( 64, 64 );
        Body1.addBox( -3F, -3F, -3F, 6, 6, 6);
        Body1.setRotationPoint( 0F, 14.5F, 2F );
        
        leg1Top = new ModelRenderer( this, 39, 0 );
        leg1Top.setTextureSize( 64, 64 );
        leg1Top.addBox( -1F, -1F, -1F, 2, 2, 2);
        leg1Top.setRotationPoint( -1.5F, 17.79997F, 1.75F );
        
        leg1top2 = new ModelRenderer( this, 35, 0 );
        leg1top2.setTextureSize( 64, 64 );
        leg1top2.addBox( -0.5F, -2.5F, -0.5F, 1, 3, 1);
        leg1top2.setRotationPoint( -1.5F + 1.5F, 20.75261F - 17.79997F, 2.602251F - 1.75F );
        
        leg1bottom = new ModelRenderer( this, 35, 0 );
        leg1bottom.setTextureSize( 64, 64 );
        leg1bottom.addBox( -0.5F, -2.5F, -0.5F, 1, 3, 1);
        leg1bottom.setRotationPoint( -1.5F + 1.5F, 23.5F - 17.79997F, 2.25F - 1.75F );
       
        Foot1 = new ModelRenderer( this, 25, 0 );
        Foot1.setTextureSize( 64, 64 );
        Foot1.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot1.setRotationPoint( -2.25F + 1.5F, 23.95373F - 17.79997F, 1.195186F- 1.75F );
       
        Foot1b = new ModelRenderer( this, 25, 0 );
        Foot1b.setTextureSize( 64, 64 );
        Foot1b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot1b.setRotationPoint( -0.75F + 1.5F, 23.95373F - 17.79997F, 1.195186F - 1.75F );
       
        leg2Top = new ModelRenderer( this, 39, 0 );
        leg2Top.setTextureSize( 64, 64 );
        leg2Top.addBox( -1F, -1F, -1F, 2, 2, 2);
        leg2Top.setRotationPoint( 1.5F, 17.79997F, 1.75F );
       
        leg2top2 = new ModelRenderer( this, 35, 0 );
        leg2top2.setTextureSize( 64, 64 );
        leg2top2.addBox( -0.5F, -3F, -0.5F, 1, 3, 1);
        leg2top2.setRotationPoint( 1.5F - 1.5F, 21.23324F - 17.79997F, 2.74007F - 1.75F );
       
        leg2bottom = new ModelRenderer( this, 35, 0 );
        leg2bottom.setTextureSize( 64, 64 );
        leg2bottom.addBox( -0.5F, -2.5F, -0.5F, 1, 3, 1);
        leg2bottom.setRotationPoint( 1.5F - 1.5F, 23.5F - 17.79997F, 2.25F  - 1.75F);
       
        Foot2 = new ModelRenderer( this, 25, 0 );
        Foot2.setTextureSize( 64, 64 );
        Foot2.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot2.setRotationPoint( 0.75F - 1.5F, 23.99486F - 17.79997F, 1.198088F  - 1.75F);
        
        Foot2b = new ModelRenderer( this, 25, 0 );
        Foot2b.setTextureSize( 64, 64 );
        Foot2b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot2b.setRotationPoint( 2.25F - 1.5F, 23.99486F - 17.79997F, 1.198088F - 1.75F );
       
        Tail1 = new ModelRenderer( this, 36, 20 );
        Tail1.setTextureSize( 64, 64 );
        Tail1.addBox( -2.5F, -2F, -2F, 5, 4, 4);
        Tail1.setRotationPoint( 0F, 15.10777F, 5.446827F );
        
        Tail2 = new ModelRenderer( this, 25, 14 );
        Tail2.setTextureSize( 64, 64 );
        Tail2.addBox( -2F, -1F, -1.5F, 4, 2, 3);
        Tail2.setRotationPoint( 0F, 15.25801F - 15.10777F , 8.135215F - 5.446827F );
       
        Wing1 = new ModelRenderer( this, 2, 40 );
        Wing1.setTextureSize( 64, 64 );
        Wing1.addBox( -0.5F, -1.5F, -1.5F, 1, 3, 7);
        Wing1.setRotationPoint( -3.5F, 13.6603F - 0.8F, 0.1172086F );
       
        Wing1a = new ModelRenderer( this, 3, 40 );
        Wing1a.setTextureSize( 64, 64 );
        Wing1a.addBox( -0.5F, -1F, 1F, 1, 2, 2);
        Wing1a.setRotationPoint( -3.5F + 3.5F, 14.96746F- 13.6603F - 1.3F, 4.423172F - 0.1172086F );
       
        Wing1b = new ModelRenderer( this, 3, 40 );
        Wing1b.setTextureSize( 64, 64 );
        Wing1b.addBox( -0.5F, -0.5F, -1F, 1, 1, 6);
        Wing1b.setRotationPoint( -3.5F + 3.5F, 15.57406F - 13.6603F, -0.4637532F - 0.1172086F);
       
        Wing2 = new ModelRenderer( this, 2, 40 );
        Wing2.setTextureSize( 64, 64 );
        Wing2.addBox( -0.5F, -1.5F, -1.5F, 1, 3, 7);
        Wing2.setRotationPoint( 3.5F, 13.6603F - 0.8F, 0.1172086F );
       
        Wing2a = new ModelRenderer( this, 3, 40 );
        Wing2a.setTextureSize( 64, 64 );
        Wing2a.addBox( -0.5F, -1F, 1F, 1, 2, 2);
        Wing2a.setRotationPoint( 3.5F - 3.5F, 14.96746F - 13.6603F - 1.3F, 4.423172F - 0.1172086F );
       
        Wing2b = new ModelRenderer( this, 3, 40 );
        Wing2b.setTextureSize( 64, 64 );
        Wing2b.addBox( -0.5F, -0.5F, -1F, 1, 1, 6);
        Wing2b.setRotationPoint( 3.5F - 3.5F, 15.57406F - 13.6603F, -0.4637532F - 0.1172086F );
		
		this.Neck.addChild(Neck2);
		this.Neck.addChild(Neck3);
		this.Neck.addChild(Head);
		this.Neck.addChild(this.BeakTop);
		this.Neck.addChild(this.BeakBottom);
		this.Neck.addChild(this.Crest);

		this.leg1Top.addChild(this.leg1top2);
		this.leg1Top.addChild(this.leg1bottom);
		this.leg1Top.addChild(this.Foot1);
		this.leg1Top.addChild(this.Foot1b);

		this.leg2Top.addChild(this.leg2top2);
		this.leg2Top.addChild(this.leg2bottom);
		this.leg2Top.addChild(this.Foot2);
		this.leg2Top.addChild(this.Foot2b);
		
		this.Wing1.addChild(this.Wing1a);
		this.Wing1.addChild(this.Wing1b);
		this.Wing2.addChild(this.Wing2a);
		this.Wing2.addChild(this.Wing2b);

		this.Tail1.addChild(this.Tail2);


	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body1.render(scale);
		this.leg1Top.render(scale);
		this.leg2Top.render(scale);
		this.Wing1.render(scale);
		this.Wing2.render(scale);
		this.Tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}


	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, limbSwingAmount, ageInTicks, partialTickTime);
		Random rand = new Random();
		if (limbSwingAmount > lastLimb) {
			//this.Neck.rotateAngleX = Math.abs(((30F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		}  else {
			//do nothing
		}

	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{



		Neck.rotateAngleX = -0.539251F;
		Neck2.rotateAngleX = -0.9490896F;
		Neck3.rotateAngleX = -1.376694F;
		Head.rotateAngleX = 0.07594994F;
		Crest.rotateAngleX = -0.1465151F;
		BeakBottom.rotateAngleX = 0.07594992F;
		BeakTop.rotateAngleX = 0.3341772F;
		Body1.rotateAngleX = -0.1745329F;
		leg1Top.rotateAngleX = 0.2792527F;
		leg1top2.rotateAngleX = 0.2792527F;
		leg1bottom.rotateAngleX = -0.1745331F;
		Foot1.rotateAngleX = -1.588343E-07F;
		leg2Top.rotateAngleX = 0.2792527F;
		leg2top2.rotateAngleX = 0.2792527F;
		leg2bottom.rotateAngleX = -0.1745331F;
		Foot2.rotateAngleX = -1.588343E-07F;
		Tail1.rotateAngleX = -0.4363323F;
		Tail2.rotateAngleX = -0.121238F;
		
		Wing1.rotateAngleX = -0.2947292F;
		//Wing1a.rotateAngleX = -0.2947292F;
		//Wing1b.rotateAngleX = -0.2947292F;
		Wing2.rotateAngleX = -0.2947292F;
		//Wing2a.rotateAngleX = -0.2947292F;
		//Wing2b.rotateAngleX = -0.2947292F;

		this.Neck.rotateAngleX = Math.abs((headPitch / (180F / (float)Math.PI)) * 1.4F * limbSwingAmount);
		this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float)Math.PI));

		this.Body1.rotateAngleX = ((float)Math.PI / 2F);
		//this.leg1Top.rotateAngleX = ((float)Math.PI / 2F);
		//this.leg2Top.rotateAngleX = ((float)Math.PI / 2F);
		this.leg1Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.Wing1.rotateAngleZ = ageInTicks;
		this.Wing2.rotateAngleZ = -ageInTicks;
	}

}

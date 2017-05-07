package com.animania.client.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelPeacock extends ModelBase
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
	ModelRenderer FanNodeA;
	ModelRenderer FeatherA;
	ModelRenderer FeatherA1;
	ModelRenderer FeatherA1b;
	ModelRenderer FeatherA2;
	ModelRenderer FeatherA2b;
	ModelRenderer FeatherA3;
	ModelRenderer FeatherA3b;
	ModelRenderer FeatherA4;
	ModelRenderer FeatherA4b;
	ModelRenderer FeatherA5;
	ModelRenderer FeatherA5b;
	ModelRenderer FeatherA6;
	ModelRenderer FeatherA6b;
	ModelRenderer FeatherA7;
	ModelRenderer FeatherA7b;
	ModelRenderer FeatherA8;
	ModelRenderer FeatherA8b;
	ModelRenderer FanNodeB;
	ModelRenderer FeatherB;
	ModelRenderer FeatherB1;
	ModelRenderer FeatherB1b;
	ModelRenderer FeatherB2;
	ModelRenderer FeatherB2b;
	ModelRenderer FeatherB3;
	ModelRenderer FeatherB3b;
	ModelRenderer FeatherB4;
	ModelRenderer FeatherB4b;
	ModelRenderer FeatherB5;
	ModelRenderer FeatherB5b;
	ModelRenderer FeatherB6;
	ModelRenderer FeatherB6b;
	ModelRenderer FeatherB7;
	ModelRenderer FeatherB7b;
	ModelRenderer FeatherB8;
	ModelRenderer FeatherB8b;
	ModelRenderer FanNodeC;
	ModelRenderer FeatherB11;
	ModelRenderer FeatherB1b1;
	ModelRenderer FeatherB21;
	ModelRenderer FeatherB2b1;
	ModelRenderer FeatherB31;
	ModelRenderer FeatherB3b1;
	ModelRenderer FeatherB41;
	ModelRenderer FeatherB4b1;
	ModelRenderer FanNodeD;
	ModelRenderer FeatherD;
	ModelRenderer FeatherD1;
	ModelRenderer FeatherD1b;
	ModelRenderer FeatherD2;
	ModelRenderer FeatherD2b;
	ModelRenderer Wing1;
	ModelRenderer Wing1a;
	ModelRenderer Wing1b;
	ModelRenderer Wing2;
	ModelRenderer Wing2a;
	ModelRenderer Wing2b;



	public ModelPeacock()
	{
		this( 0.0f );
	}

	public ModelPeacock(float par1)
	{
		RootNode = new ModelRenderer( this, 16, 16 );
		RootNode.setTextureSize( 128, 64 );
		RootNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		RootNode.setRotationPoint( 0F, 10.5F, 0F );
		
		Neck = new ModelRenderer( this, 3, 19 );
		Neck.setTextureSize( 128, 64 );
		Neck.addBox( -2.5F, -2.5F, -2F, 5, 5, 4);
		Neck.setRotationPoint( 0F, 12.3998F, -1F );
		
		Neck2 = new ModelRenderer( this, 21, 21 );
		Neck2.setTextureSize( 128, 64 );
		Neck2.addBox( -2F, -2F, -1.5F, 4, 4, 3);
		Neck2.setRotationPoint( 0F, 10.90154F - 12.3998F, -3.01686F + 1F);
		
		Neck3 = new ModelRenderer( this, 3, 28 );
		Neck3.setTextureSize( 128, 64 );
		Neck3.addBox( -1.51F, -1.5F, -6F, 3, 3, 6);
		Neck3.setRotationPoint( 0F, 10.5F - 12.3998F, -3.75F + 1F);
		
		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 128, 64 );
		Head.addBox( -1.5F, -2.5F, -2F, 3, 3, 4);
		Head.setRotationPoint( 0F, 4.5F - 12.3998F, -5F + 1F);
		
		Crest = new ModelRenderer( this, 33, 43 );
        Crest.setTextureSize( 128, 64 );
        Crest.addBox( 0F, -2.5F, -3.5F, 0, 5, 7);
        Crest.setRotationPoint( 0F, -0.04393768F  - 12.3998F, -4.59361F + 1F );
		
		BeakBottom = new ModelRenderer( this, 19, 0 );
		BeakBottom.setTextureSize( 128, 64 );
		BeakBottom.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		BeakBottom.setRotationPoint( 0F, 3.862326F - 12.3998F, -8.057199F + 1F);
		
		BeakTop = new ModelRenderer( this, 19, 0 );
		BeakTop.setTextureSize( 128, 64 );
		BeakTop.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		BeakTop.setRotationPoint( 0F, 3.560648F - 12.3998F, -7.962383F + 1F);
		
		Body1 = new ModelRenderer( this, 0, 7 );
		Body1.setTextureSize( 128, 64 );
		Body1.addBox( -3F, -3F, -3F, 6, 6, 6);
		Body1.setRotationPoint( 0F, 13.5F, 2F );
		
		leg1Top = new ModelRenderer( this, 39, 0 );
		leg1Top.setTextureSize( 128, 64 );
		leg1Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		leg1Top.setRotationPoint( -1.5F, 16.79997F, 1.75F );
		
		leg1top2 = new ModelRenderer( this, 35, 0 );
		leg1top2.setTextureSize( 128, 64 );
		leg1top2.addBox( -0.5F, -2.5F, -0.5F, 1, 3, 1);
		leg1top2.setRotationPoint( -1.5F + 1.5F, 19.75261F - 16.79997F, 2.602251F - 1.75F);
		
		leg1bottom = new ModelRenderer( this, 35, 0 );
		leg1bottom.setTextureSize( 128, 64 );
		leg1bottom.addBox( -0.5F, -3F, -0.5F, 1, 4, 1);
		leg1bottom.setRotationPoint( -1.5F + 1.5F, 22.95F - 16.79997F, 2.2F - 1.75F);
		
		Foot1 = new ModelRenderer( this, 25, 0 );
		Foot1.setTextureSize( 128, 64 );
		Foot1.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		Foot1.setRotationPoint( -2.25F + 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F);
		
		Foot1b = new ModelRenderer( this, 25, 0 );
        Foot1b.setTextureSize( 128, 64 );
        Foot1b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot1b.setRotationPoint( -0.75F + 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F );
		
		leg2Top = new ModelRenderer( this, 39, 0 );
		leg2Top.setTextureSize( 128, 64 );
		leg2Top.addBox( -1F, -1F, -1F, 2, 2, 2);
		leg2Top.setRotationPoint( 1.5F, 16.79997F, 1.75F );
		
		leg2top2 = new ModelRenderer( this, 35, 0 );
		leg2top2.setTextureSize( 128, 64 );
		leg2top2.addBox( -0.5F, -3F, -0.5F, 1, 3, 1);
		leg2top2.setRotationPoint( 1.5F - 1.5F, 20.23324F - 16.79997F, 2.74007F - 1.75F);
		
		leg2bottom = new ModelRenderer( this, 35, 0 );
		leg2bottom.setTextureSize( 128, 64 );
		leg2bottom.addBox( -0.5F, -3F, -0.5F, 1, 4, 1);
		leg2bottom.setRotationPoint( 1.5F -1.5F, 22.95F - 16.79997F, 2.2F - 1.75F);
		
		Foot2 = new ModelRenderer( this, 25, 0 );
		Foot2.setTextureSize( 128, 64 );
		Foot2.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		Foot2.setRotationPoint( 0.75F - 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F );
		
		Foot2b = new ModelRenderer( this, 25, 0 );
        Foot2b.setTextureSize( 128, 64 );
        Foot2b.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
        Foot2b.setRotationPoint( 2.25F - 1.5F, 23.95172F - 16.79997F, 1.200874F - 1.75F );
		
		Tail1 = new ModelRenderer( this, 36, 20 );
		Tail1.setTextureSize( 128, 64 );
		Tail1.addBox( -2.5F, -2F, -2F, 5, 4, 4);
		Tail1.setRotationPoint( 0F, 14.10777F, 5.446827F );
		
		Tail2 = new ModelRenderer( this, 25, 14 );
		Tail2.setTextureSize( 128, 64 );
		Tail2.addBox( -2F, -1F, -1.5F, 4, 2, 3);
		Tail2.setRotationPoint( 0F, 14.25801F - 14.10777F, 8.135215F - 5.446827F);
		
		FanNodeA = new ModelRenderer( this, 6, 13 );
		FanNodeA.setTextureSize( 128, 64 );
		FanNodeA.addBox( 0F, 0F, 0F, 0, 0, 0);
		FanNodeA.setRotationPoint( 0F, 9+10.89287F, 10+4.49056F );
		
		FeatherA = new ModelRenderer( this, 63, 0 );
		FeatherA.setTextureSize( 128, 64 );
		FeatherA.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA.setRotationPoint( 0F, 10.89287F - 10.89287F, 4.49056F - 4.49056F );
		
		FeatherA1 = new ModelRenderer( this, 63, 0 );
		FeatherA1.setTextureSize( 128, 64 );
		FeatherA1.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA1.setRotationPoint( 4F, 10.89287F - 10.89287F, 4.49056F - 4.49056F );
		
		FeatherA1b = new ModelRenderer( this, 63, 0 );
		FeatherA1b.setTextureSize( 128, 64 );
		FeatherA1b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA1b.setRotationPoint( -4F, 10.89287F - 10.89287F, 4.49056F - 4.49056F );
		
		FeatherA2 = new ModelRenderer( this, 63, 0 );
		FeatherA2.setTextureSize( 128, 64 );
		FeatherA2.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA2.setRotationPoint( 8F, 10.89287F - 8.89287F, 4.49056F - 4.49056F );
		
		FeatherA2b = new ModelRenderer( this, 63, 0 );
		FeatherA2b.setTextureSize( 128, 64 );
		FeatherA2b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA2b.setRotationPoint( -8F, 10.89287F - 8.89287F, 4.49056F - 4.49056F );
		
		FeatherA3 = new ModelRenderer( this, 63, 0 );
		FeatherA3.setTextureSize( 128, 64 );
		FeatherA3.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA3.setRotationPoint( 12F, 10.89287F - 6.89287F, 4.49056F - 4.49056F );
		
		FeatherA3b = new ModelRenderer( this, 63, 0 );
		FeatherA3b.setTextureSize( 128, 64 );
		FeatherA3b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA3b.setRotationPoint( -12F, 10.89287F - 6.89287F, 4.49056F - 4.49056F );
		
		FeatherA4 = new ModelRenderer( this, 63, 0 );
		FeatherA4.setTextureSize( 128, 64 );
		FeatherA4.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA4.setRotationPoint( 16F, 10.89287F - 4.89287F, 4.49056F - 4.49056F );
		
		FeatherA4b = new ModelRenderer( this, 63, 0 );
		FeatherA4b.setTextureSize( 128, 64 );
		FeatherA4b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA4b.setRotationPoint( -16F, 10.89287F - 4.89287F, 4.49056F - 4.49056F );
		
		FeatherA5 = new ModelRenderer( this, 63, 0 );
		FeatherA5.setTextureSize( 128, 64 );
		FeatherA5.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA5.setRotationPoint( 18F, 10.89287F - 2.89287F, 4.49056F - 4.49056F );
		
		FeatherA5b = new ModelRenderer( this, 63, 0 );
		FeatherA5b.setTextureSize( 128, 64 );
		FeatherA5b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		FeatherA5b.setRotationPoint( -18F, 10.89287F - 2.89287F, 4.49056F - 4.49056F );
		
		FeatherA6 = new ModelRenderer( this, 63, 0 );
		FeatherA6.setTextureSize( 128, 64 );
		FeatherA6.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA6.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA6.setRotationPoint( 20F, 10.89288F - 0.39287F, 4.49056F - 4.49056F );
		
		FeatherA6b = new ModelRenderer( this, 63, 0 );
		FeatherA6b.setTextureSize( 128, 64 );
		FeatherA6b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA6b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA6b.setRotationPoint( -20F, 10.89288F - 0.39287F, 4.49056F - 4.49056F );
		
		FeatherA7 = new ModelRenderer( this, 63, 0 );
		FeatherA7.setTextureSize( 128, 64 );
		FeatherA7.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA7.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA7.setRotationPoint( 22F, 10.89288F + 3.89287F, 4.49056F - 4.49056F );
		
		FeatherA7b = new ModelRenderer( this, 63, 0 );
		FeatherA7b.setTextureSize( 128, 64 );
		FeatherA7b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA7b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA7b.setRotationPoint( -22F, 10.89288F + 3.89287F, 4.49056F - 4.49056F );
		
		FeatherA8 = new ModelRenderer( this, 63, 0 );
		FeatherA8.setTextureSize( 128, 64 );
		FeatherA8.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA8.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA8.setRotationPoint( 22F, 10.89288F + 6.89287F, 4.49056F - 4.49056F );
		
		FeatherA8b = new ModelRenderer( this, 63, 0 );
		FeatherA8b.setTextureSize( 128, 64 );
		FeatherA8b.addBox( -6.5F, -42.5F, 0F, 13, 64, 0);
		//FeatherA8b.setRotationPoint( -2.473759E-06F, 10.89288F - 10.89287F, 4.49056F - 4.49056F );
		FeatherA8b.setRotationPoint( -22F, 10.89288F + 6.89287F, 4.49056F - 4.49056F );
		
		FanNodeB = new ModelRenderer( this, 6, 13 );
		FanNodeB.setTextureSize( 128, 64 );
		FanNodeB.addBox( 0F, 0F, 0F, 0, 0, 0);
		FanNodeB.setRotationPoint( 0F, 15 + 8.74961F, 6+4.853049F );
		
		FeatherB = new ModelRenderer( this, 63, 0 );
		FeatherB.setTextureSize( 128, 64 );
		FeatherB.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB.setRotationPoint( 0F, 11.17086F - 8.74961F, 3.293798F - 3.853049F );
		
		FeatherB1 = new ModelRenderer( this, 63, 0 );
		FeatherB1.setTextureSize( 128, 64 );
		FeatherB1.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB1.setRotationPoint( 4F, 11.16443F - 8.74961F, 3.206001F - 3.853049F );
		
		FeatherB1b = new ModelRenderer( this, 63, 0 );
		FeatherB1b.setTextureSize( 128, 64 );
		FeatherB1b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB1b.setRotationPoint( -4F, 11.16443F - 8.74961F, 3.206001F - 3.853049F );
		
		FeatherB2 = new ModelRenderer( this, 63, 0 );
		FeatherB2.setTextureSize( 128, 64 );
		FeatherB2.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB2.setRotationPoint( 8F, 11.16443F - 6.74961F, 3.206001F - 3.853049F );
		
		FeatherB2b = new ModelRenderer( this, 63, 0 );
		FeatherB2b.setTextureSize( 128, 64 );
		FeatherB2b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB2b.setRotationPoint( -8F, 11.16443F - 6.74961F, 3.206001F - 3.853049F );
		
		FeatherB3 = new ModelRenderer( this, 63, 0 );
		FeatherB3.setTextureSize( 128, 64 );
		FeatherB3.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB3.setRotationPoint( 12F, 11.16443F - 4.74961F, 3.206001F - 3.853049F );
		
		FeatherB3b = new ModelRenderer( this, 63, 0 );
		FeatherB3b.setTextureSize( 128, 64 );
		FeatherB3b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB3b.setRotationPoint( -12F, 11.16443F - 4.74961F, 3.206001F - 3.853049F );
		
		FeatherB4 = new ModelRenderer( this, 63, 0 );
		FeatherB4.setTextureSize( 128, 64 );
		FeatherB4.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB4.setRotationPoint( 16F, 11.16443F - 2.74961F, 3.206001F - 3.853049F );
		
		FeatherB4b = new ModelRenderer( this, 63, 0 );
		FeatherB4b.setTextureSize( 128, 64 );
		FeatherB4b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB4b.setRotationPoint( -16F, 11.16443F - 2.74961F, 3.206001F - 3.853049F );
		
		FeatherB5 = new ModelRenderer( this, 63, 0 );
		FeatherB5.setTextureSize( 128, 64 );
		FeatherB5.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB5.setRotationPoint( 18F, 11.16443F + 1.24961F, 3.206001F - 3.853049F );
		
		FeatherB5b = new ModelRenderer( this, 63, 0 );
		FeatherB5b.setTextureSize( 128, 64 );
		FeatherB5b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB5b.setRotationPoint( -18F, 11.16443F + 1.24961F, 3.206001F - 3.853049F );
		
		FeatherB6 = new ModelRenderer( this, 63, 0 );
		FeatherB6.setTextureSize( 128, 64 );
		FeatherB6.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB6.setRotationPoint( 20F, 11.16443F + 4.24961F, 3.206F - 3.853049F );
		
		FeatherB6b = new ModelRenderer( this, 63, 0 );
		FeatherB6b.setTextureSize( 128, 64 );
		FeatherB6b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB6b.setRotationPoint( -20F, 11.16443F + 4.24961F, 3.206F - 3.853049F );
		
		FeatherB7 = new ModelRenderer( this, 63, 0 );
		FeatherB7.setTextureSize( 128, 64 );
		FeatherB7.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB7.setRotationPoint( 22F, 11.16443F + 7.24961F, 3.206F - 3.853049F );
		
		FeatherB7b = new ModelRenderer( this, 63, 0 );
		FeatherB7b.setTextureSize( 128, 64 );
		FeatherB7b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB7b.setRotationPoint( -22F, 11.16443F + 7.24961F, 3.206F - 3.853049F );
		
		FeatherB8 = new ModelRenderer( this, 63, 0 );
		FeatherB8.setTextureSize( 128, 64 );
		FeatherB8.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB8.setRotationPoint( 22F, 11.16443F + 10.24961F, 3.206F - 3.853049F );
		
		FeatherB8b = new ModelRenderer( this, 63, 0 );
		FeatherB8b.setTextureSize( 128, 64 );
		FeatherB8b.addBox( -6.5F, -35F, 0F, 13, 52, 0);
		FeatherB8b.setRotationPoint( -22F, 11.16443F +10.24961F, 3.206F - 3.853049F );
		
		//FANNODEC
		FanNodeC = new ModelRenderer( this, 6, 13 );
		FanNodeC.setTextureSize( 128, 64 );
		FanNodeC.addBox( 0F, 3F, 0F, 0, 0, 0);
		FanNodeC.setRotationPoint( 0.13F, 16+10.575961F, 2+1.868241F );
		
		FeatherB11 = new ModelRenderer( this, 63, 0 );
		FeatherB11.setTextureSize( 128, 64 );
		FeatherB11.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB11.setRotationPoint( 0.003027502F, 11.51945F - 8.575961F, 2.069598F - 2.868241F );
		
		FeatherB1b1 = new ModelRenderer( this, 63, 0 );
		FeatherB1b1.setTextureSize( 128, 64 );
		FeatherB1b1.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB1b1.setRotationPoint( 0.003027502F, 11.51945F - 8.575961F, 2.069598F - 2.868241F );
		
		FeatherB21 = new ModelRenderer( this, 63, 0 );
		FeatherB21.setTextureSize( 128, 64 );
		FeatherB21.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB21.setRotationPoint( 4.003027502F, 11.51945F - 6.575961F, 2.069598F - 2.868241F );
		
		FeatherB2b1 = new ModelRenderer( this, 63, 0 );
		FeatherB2b1.setTextureSize( 128, 64 );
		FeatherB2b1.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB2b1.setRotationPoint( -4.003027502F, 11.51945F - 6.575961F, 2.069598F - 2.868241F );
		
		FeatherB31 = new ModelRenderer( this, 63, 0 );
		FeatherB31.setTextureSize( 128, 64 );
		FeatherB31.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB31.setRotationPoint( 8.003027502F, 11.51945F - 4.575961F, 2.069598F - 2.868241F );
		
		FeatherB3b1 = new ModelRenderer( this, 63, 0 );
		FeatherB3b1.setTextureSize( 128, 64 );
		FeatherB3b1.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB3b1.setRotationPoint( -8.003027502F, 11.51945F - 4.575961F, 2.069598F - 2.868241F );
		
		FeatherB41 = new ModelRenderer( this, 63, 0 );
		FeatherB41.setTextureSize( 128, 64 );
		FeatherB41.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB41.setRotationPoint( 12.003025029F, 11.51945F - 2.575961F, 2.069597F - 2.868241F );
		
		FeatherB4b1 = new ModelRenderer( this, 63, 0 );
		FeatherB4b1.setTextureSize( 128, 64 );
		FeatherB4b1.addBox( -6.5F, -27F, 0F, 13, 40, 0);
		FeatherB4b1.setRotationPoint( -12.003025029F, 11.51945F - 2.575961F, 2.069597F - 2.868241F );
		
		FanNodeD = new ModelRenderer( this, 31, 31 );
		FanNodeD.setTextureSize( 128, 64 );
		FanNodeD.addBox( 0F, -4.5F, 0F, 0, 9, 0);
		FanNodeD.setRotationPoint( 0F, 8 + 16.473945F, 4 + .202189F );
		
		FeatherD = new ModelRenderer( this, 31, 31 );
		FeatherD.setTextureSize( 128, 64 );
		FeatherD.addBox( -6.5F, -8.5F, 0F, 13, 13, 0);
		FeatherD.setRotationPoint( 0F, 9.473946F - 9.473945F, 2.202188F - 2.202189F );
		
		FeatherD1 = new ModelRenderer( this, 31, 31 );
		FeatherD1.setTextureSize( 128, 64 );
		FeatherD1.addBox( -6.5F, -8.5F, 0F, 13, 13, 0);
		FeatherD1.setRotationPoint( 0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F );
		
		FeatherD1b = new ModelRenderer( this, 31, 31 );
		FeatherD1b.setTextureSize( 128, 64 );
		FeatherD1b.addBox( -6.5F, -8.5F, 0F, 13, 13, 0);
		FeatherD1b.setRotationPoint( 0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F );
		
		FeatherD2 = new ModelRenderer( this, 31, 31 );
		FeatherD2.setTextureSize( 128, 64 );
		FeatherD2.addBox( -6.5F, -8.5F, 0F, 13, 13, 0);
		FeatherD2.setRotationPoint( 0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F );
		
		FeatherD2b = new ModelRenderer( this, 31, 31 );
		FeatherD2b.setTextureSize( 128, 64 );
		FeatherD2b.addBox( -6.5F, -8.5F, 0F, 13, 13, 0);
		FeatherD2b.setRotationPoint( 0F, 9.473945F - 9.473945F, 2.202189F - 2.202189F );
		
		Wing1 = new ModelRenderer( this, 92, 2 );
		Wing1.setTextureSize( 128, 64 );
		Wing1.addBox( -0.5F, -1.5F, -1.5F, 1, 3, 7);
		Wing1.setRotationPoint( -3.5F, 12.6603F, 0.1172081F );
		
		Wing1a = new ModelRenderer( this, 96, 2 );
		Wing1a.setTextureSize( 128, 64 );
		Wing1a.addBox( -0.5F, -1F, 1F, 1, 2, 2);
		Wing1a.setRotationPoint( -3.5F + 3.51F, 13.96746F - 13.6603F, 4.423172F - 0.1172081F );
		
		Wing1b = new ModelRenderer( this, 93, 2 );
		Wing1b.setTextureSize( 128, 64 );
		Wing1b.addBox( -0.5F, -0.5F, -1F, 1, 1, 6);
		Wing1b.setRotationPoint( -3.5F + 3.52F, 14.57406F - 12.6603F, -0.4637537F - 0.1172081F );
		
		Wing2 = new ModelRenderer( this, 92, 2 );
		Wing2.setTextureSize( 128, 64 );
		Wing2.addBox( -0.5F, -1.5F, -1.5F, 1, 3, 7);
		Wing2.setRotationPoint( 3.5F, 12.6603F, 0.1172086F );
		
		Wing2a = new ModelRenderer( this, 96, 2 );
		Wing2a.setTextureSize( 128, 64 );
		Wing2a.addBox( -0.5F, -1F, 1F, 1, 2, 2);
		Wing2a.setRotationPoint( 3.5F - 3.51F, 13.96746F - 13.6603F, 4.423172F - 0.1172086F );
		
		Wing2b = new ModelRenderer( this, 93, 2 );
		Wing2b.setTextureSize( 128, 64 );
		Wing2b.addBox( -0.5F, -0.5F, -1F, 1, 1, 6);
		Wing2b.setRotationPoint( 3.5F - 3.52F, 14.57406F - 12.6603F, -0.4637532F - 0.1172086F);

		this.Neck.addChild(this.Neck2);
		this.Neck.addChild(this.Neck3);
		this.Neck.addChild(this.Head);
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
		
		this.FanNodeA.addChild(this.FeatherA);
		this.FanNodeA.addChild(this.FeatherA1);
		this.FanNodeA.addChild(this.FeatherA1b);
		this.FanNodeA.addChild(this.FeatherA2);
		this.FanNodeA.addChild(this.FeatherA2b);
		this.FanNodeA.addChild(this.FeatherA3);
		this.FanNodeA.addChild(this.FeatherA3b);
		this.FanNodeA.addChild(this.FeatherA4);
		this.FanNodeA.addChild(this.FeatherA4b);
		this.FanNodeA.addChild(this.FeatherA5);
		this.FanNodeA.addChild(this.FeatherA5b);
		this.FanNodeA.addChild(this.FeatherA6);
		this.FanNodeA.addChild(this.FeatherA6b);
		this.FanNodeA.addChild(this.FeatherA7);
		this.FanNodeA.addChild(this.FeatherA7b);
		this.FanNodeA.addChild(this.FeatherA8);
		this.FanNodeA.addChild(this.FeatherA8b);
		
		this.FanNodeB.addChild(this.FeatherB);
		this.FanNodeB.addChild(this.FeatherB1);
		this.FanNodeB.addChild(this.FeatherB1b);
		this.FanNodeB.addChild(this.FeatherB2);
		this.FanNodeB.addChild(this.FeatherB2b);
		this.FanNodeB.addChild(this.FeatherB3);
		this.FanNodeB.addChild(this.FeatherB3b);
		this.FanNodeB.addChild(this.FeatherB4);
		this.FanNodeB.addChild(this.FeatherB4b);
		this.FanNodeB.addChild(this.FeatherB5);
		this.FanNodeB.addChild(this.FeatherB5b);
		this.FanNodeB.addChild(this.FeatherB6);
		this.FanNodeB.addChild(this.FeatherB6b);
		this.FanNodeB.addChild(this.FeatherB7);
		this.FanNodeB.addChild(this.FeatherB7b);
		this.FanNodeB.addChild(this.FeatherB8);
		this.FanNodeB.addChild(this.FeatherB8b);
		
		this.FanNodeC.addChild(this.FeatherB11);
		this.FanNodeC.addChild(this.FeatherB1b1);
		this.FanNodeC.addChild(this.FeatherB21);
		this.FanNodeC.addChild(this.FeatherB2b1);
		this.FanNodeC.addChild(this.FeatherB31);
		this.FanNodeC.addChild(this.FeatherB3b1);
		this.FanNodeC.addChild(this.FeatherB41);
		this.FanNodeC.addChild(this.FeatherB4b1);
		
		this.FanNodeD.addChild(this.FeatherD);
		this.FanNodeD.addChild(this.FeatherD1b);
		this.FanNodeD.addChild(this.FeatherD2);
		this.FanNodeD.addChild(this.FeatherD2b);
		
		this.Tail1.addChild(this.Tail2);


	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Neck.render(scale);
		this.Body1.render(scale);
		this.leg1Top.render(scale);
		this.leg2Top.render(scale);
		this.Wing1.render(scale);
		this.Wing2.render(scale);
		this.Tail1.render(scale);
		this.FanNodeA.render(scale/3);
		this.FanNodeB.render(scale/3);
		this.FanNodeC.render(scale/3);
		this.FanNodeD.render(scale/3);

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

	@Override
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
		Foot1.rotateAngleX = -5.789743E-08F;
		leg2Top.rotateAngleX = 0.2792527F;
		leg2top2.rotateAngleX = 0.2792527F;
		leg2bottom.rotateAngleX = -0.1745331F;
		Foot2.rotateAngleX = -5.789743E-08F;
		Tail1.rotateAngleX = -0.4363323F;
		Tail2.rotateAngleX = -0.121238F;
		
		FanNodeA.rotateAngleX = -0.2617994F;
		FeatherA.rotateAngleX = -0.2617994F;
		FeatherA1.rotateAngleX = -0.2792528F;
		FeatherA1.rotateAngleY = -5.792813E-09F;
		FeatherA1.rotateAngleZ = 0.2268928F;
		FeatherA1b.rotateAngleX = -0.2792527F;
		FeatherA1b.rotateAngleY = 3.986776E-09F;
		FeatherA1b.rotateAngleZ = -0.2268928F;
		FeatherA2.rotateAngleX = -0.296706F;
		FeatherA2.rotateAngleY = -6.077558E-09F;
		FeatherA2.rotateAngleZ = 0.4537856F;
		FeatherA2b.rotateAngleX = -0.296706F;
		FeatherA2b.rotateAngleY = 1.589279E-09F;
		FeatherA2b.rotateAngleZ = -0.4537856F;
		FeatherA3.rotateAngleX = -0.3141594F;
		FeatherA3.rotateAngleY = -3.567901E-08F;
		FeatherA3.rotateAngleZ = 0.6806786F;
		FeatherA3b.rotateAngleX = -0.3141594F;
		FeatherA3b.rotateAngleY = 1.490632E-08F;
		FeatherA3b.rotateAngleZ = -0.6806785F;
		FeatherA4.rotateAngleX = -0.3316126F;
		FeatherA4.rotateAngleY = -1.525341E-08F;
		FeatherA4.rotateAngleZ = 0.9075713F;
		FeatherA4b.rotateAngleX = -0.3316126F;
		FeatherA4b.rotateAngleY = 1.525341E-08F;
		FeatherA4b.rotateAngleZ = -0.9075713F;
		FeatherA5.rotateAngleX = -0.3490659F;
		FeatherA5.rotateAngleY = -4.286015E-08F;
		FeatherA5.rotateAngleZ = 1.134464F;
		FeatherA5b.rotateAngleX = -0.349066F;
		FeatherA5b.rotateAngleY = 2.922648E-08F;
		FeatherA5b.rotateAngleZ = -1.134464F;
		FeatherA6.rotateAngleX = -0.3563616F;
		FeatherA6.rotateAngleY = -0.01687157F;
		FeatherA6.rotateAngleZ = 1.365743F;
		FeatherA6b.rotateAngleX = -0.3563616F;
		FeatherA6b.rotateAngleY = -0.01687157F;
		FeatherA6b.rotateAngleZ = -1.356971F;
		FeatherA7.rotateAngleX = -0.3635876F;
		FeatherA7.rotateAngleY = -0.03381116F;
		FeatherA7.rotateAngleZ = 1.597045F;
		FeatherA7b.rotateAngleX = -0.3635877F;
		FeatherA7b.rotateAngleY = -0.0338112F;
		FeatherA7b.rotateAngleZ = -1.579455F;
		FeatherA8.rotateAngleX = -0.3707415F;
		FeatherA8.rotateAngleY = -0.05100469F;
		FeatherA8.rotateAngleZ = 1.828417F;
		FeatherA8b.rotateAngleX = -0.3707415F;
		FeatherA8b.rotateAngleY = -0.05100469F;
		FeatherA8b.rotateAngleZ = -1.801868F;
		FanNodeB.rotateAngleX = -0.2617994F;
		FeatherB.rotateAngleX = -0.2617994F;
		FeatherB1.rotateAngleX = -0.2792527F;
		FeatherB1.rotateAngleY = -5.112545E-10F;
		FeatherB1.rotateAngleZ = 0.2268928F;
		FeatherB1b.rotateAngleX = -0.2792527F;
		FeatherB1b.rotateAngleY = -3.301778E-09F;
		FeatherB1b.rotateAngleZ = -0.2268928F;
		FeatherB2.rotateAngleX = -0.296706F;
		FeatherB2.rotateAngleY = -3.057893E-09F;
		FeatherB2.rotateAngleZ = 0.4537857F;
		FeatherB2b.rotateAngleX = -0.296706F;
		FeatherB2b.rotateAngleY = 1.056584E-08F;
		FeatherB2b.rotateAngleZ = -0.4537857F;
		FeatherB3.rotateAngleX = -0.3141594F;
		FeatherB3.rotateAngleY = -2.389782E-08F;
		FeatherB3.rotateAngleZ = 0.6806788F;
		FeatherB3b.rotateAngleX = -0.3141594F;
		FeatherB3b.rotateAngleY = 9.560558E-09F;
		FeatherB3b.rotateAngleZ = -0.6806785F;
		FeatherB4.rotateAngleX = -0.3316126F;
		FeatherB4.rotateAngleY = -1.282882E-09F;
		FeatherB4.rotateAngleZ = 0.9075713F;
		FeatherB4b.rotateAngleX = -0.3316126F;
		FeatherB4b.rotateAngleY = 1.282882E-09F;
		FeatherB4b.rotateAngleZ = -0.9075713F;
		FeatherB5.rotateAngleX = -0.3490659F;
		FeatherB5.rotateAngleY = -5.144992E-09F;
		FeatherB5.rotateAngleZ = 1.134465F;
		FeatherB5b.rotateAngleX = -0.3490659F;
		FeatherB5b.rotateAngleY = 2.968926E-08F;
		FeatherB5b.rotateAngleZ = -1.134464F;
		FeatherB6.rotateAngleX = -0.3563616F;
		FeatherB6.rotateAngleY = -0.01687154F;
		FeatherB6.rotateAngleZ = 1.365743F;
		FeatherB6b.rotateAngleX = -0.3563617F;
		FeatherB6b.rotateAngleY = -0.01687153F;
		FeatherB6b.rotateAngleZ = -1.356971F;
		FeatherB7.rotateAngleX = -0.3635876F;
		FeatherB7.rotateAngleY = -0.03381118F;
		FeatherB7.rotateAngleZ = 1.597045F;
		FeatherB7b.rotateAngleX = -0.3635877F;
		FeatherB7b.rotateAngleY = -0.03381117F;
		FeatherB7b.rotateAngleZ = -1.579455F;
		FeatherB8.rotateAngleX = -0.3707415F;
		FeatherB8.rotateAngleY = -0.05100475F;
		FeatherB8.rotateAngleZ = 1.828418F;
		FeatherB8b.rotateAngleX = -0.3707414F;
		FeatherB8b.rotateAngleY = -0.05100471F;
		FeatherB8b.rotateAngleZ = -1.801867F;
		FanNodeC.rotateAngleX = -0.2617994F;
		FanNodeC.rotateAngleY = 3.139456E-09F;
		FanNodeC.rotateAngleZ = -7.534808E-08F;
		FeatherB11.rotateAngleX = -0.2792527F;
		FeatherB11.rotateAngleY = -6.7773E-09F;
		FeatherB11.rotateAngleZ = -0.2268928F;
		FeatherB1b1.rotateAngleX = -0.2792527F;
		FeatherB1b1.rotateAngleY = 1.632293E-09F;
		FeatherB1b1.rotateAngleZ = 0.2268928F;
		FeatherB21.rotateAngleX = -0.3141594F;
		FeatherB21.rotateAngleY = -3.313954E-08F;
		FeatherB21.rotateAngleZ = 0.6806789F;
		FeatherB2b1.rotateAngleX = -0.3141593F;
		FeatherB2b1.rotateAngleY = 1.610589E-08F;
		FeatherB2b1.rotateAngleZ = -0.6806785F;
		FeatherB31.rotateAngleX = -0.3490659F;
		FeatherB31.rotateAngleY = 5.208576E-09F;
		FeatherB31.rotateAngleZ = 1.134464F;
		FeatherB3b1.rotateAngleX = -0.3490659F;
		FeatherB3b1.rotateAngleY = 1.29077E-08F;
		FeatherB3b1.rotateAngleZ = -1.134464F;
		FeatherB41.rotateAngleX = -0.3635876F;
		FeatherB41.rotateAngleY = -0.03381122F;
		FeatherB41.rotateAngleZ = 1.597045F;
		FeatherB4b1.rotateAngleX = -0.3635877F;
		FeatherB4b1.rotateAngleY = -0.0338112F;
		FeatherB4b1.rotateAngleZ = -1.579455F;
		FanNodeD.rotateAngleX = -0.2617995F;
		FeatherD.rotateAngleX = -0.2617994F;
		FeatherD1.rotateAngleX = -0.296706F;
		FeatherD1.rotateAngleY = 3.409307E-09F;
		FeatherD1.rotateAngleZ = 0.7853982F;
		FeatherD1b.rotateAngleX = -0.296706F;
		FeatherD1b.rotateAngleY = -5.56468E-10F;
		FeatherD1b.rotateAngleZ = -0.7853984F;
		FeatherD2.rotateAngleX = -0.3316126F;
		FeatherD2.rotateAngleZ = 1.570797F;
		FeatherD2b.rotateAngleX = -0.3316126F;
		FeatherD2b.rotateAngleZ = -1.570797F;
		
		
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

package com.animania.client.models;

import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHedgehog extends ModelBase
{
	private float headRotationAngleX;
	ModelRenderer BodyNode;
	ModelRenderer LegFrontLeft;
	ModelRenderer LegFrontLeftFoot;
	ModelRenderer Block3;
	ModelRenderer Block3b;
	ModelRenderer Block3c;
	ModelRenderer Block3d;
	ModelRenderer Block2;
	ModelRenderer Block2b;
	ModelRenderer Block2c;
	ModelRenderer Block2d;
	ModelRenderer Block2e;
	ModelRenderer Block1;
	ModelRenderer Block1b;
	ModelRenderer Block1c;
	ModelRenderer Block4;
	ModelRenderer Block4b;
	ModelRenderer Block4c;
	ModelRenderer Block5;
	ModelRenderer Block5b;
	ModelRenderer Block5c;
	ModelRenderer Block6;
	ModelRenderer Block6b;
	ModelRenderer Block7;
	ModelRenderer Block8;
	ModelRenderer Head;
	ModelRenderer Snout;
	ModelRenderer nose;
	ModelRenderer cheek1;
	ModelRenderer cheek2;
	ModelRenderer EarR;
	ModelRenderer EarL;
	ModelRenderer EarR1;
	ModelRenderer LegBackLeft;
	ModelRenderer LegFrontLeftFoot1;
	ModelRenderer LegBackRight;
	ModelRenderer LegFrontLeftFoot2;
	ModelRenderer LegFrontRight;
	ModelRenderer LegFrontLeftFoot3;
	ModelRenderer Spike1;
	ModelRenderer Spike6;
	ModelRenderer Spike2;
	ModelRenderer Spike3;
	ModelRenderer Spike4;
	ModelRenderer Spike5;
	ModelRenderer Spike7;
	ModelRenderer Spike8;
	ModelRenderer Spike9;
	ModelRenderer Spike10;
	ModelRenderer Spike11;
	ModelRenderer Spike12;
	ModelRenderer Spike13;
	ModelRenderer Spike14;
	ModelRenderer Spike15;
	ModelRenderer Spike16;
	ModelRenderer Spike22;
	ModelRenderer Spike23;
	ModelRenderer Spike24;
	ModelRenderer Spike25;
	ModelRenderer Spike26;
	ModelRenderer Spike27;
	ModelRenderer Spike28;
	ModelRenderer Spike29;
	ModelRenderer Spike30;
	ModelRenderer Spike31;
	ModelRenderer Spike32;
	ModelRenderer Spike33;
	ModelRenderer Spike34;
	ModelRenderer Spike35;
	ModelRenderer Spike36;
	ModelRenderer Spike37;
	ModelRenderer Spike38;
	ModelRenderer Spike39;
	ModelRenderer Spike40;
	ModelRenderer Spike17;
	ModelRenderer Spike18;
	ModelRenderer Spike19;
	ModelRenderer Spike20;
	ModelRenderer Spike21;
	ModelRenderer Spike41;
	ModelRenderer Spike42;
	ModelRenderer Spike43;
	ModelRenderer Spike44;
	ModelRenderer Spike45;
	ModelRenderer Spike46;
	ModelRenderer Spike47;
	ModelRenderer Spike48;
	ModelRenderer Spike49;
	ModelRenderer Spike50;


	public ModelHedgehog()
	{
		this( 0.0f );
	}

	public ModelHedgehog( float par1 )
	{
		BodyNode = new ModelRenderer( this, 16, 16 );
		BodyNode.setTextureSize( 64, 64 );
		BodyNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		BodyNode.setRotationPoint( 0F, 19.5F, 0F );
		
		LegFrontLeft = new ModelRenderer( this, 46, 1 );
		LegFrontLeft.setTextureSize( 64, 64 );
		LegFrontLeft.addBox( -0.5F, -0.5F, -1F, 1, 2, 2);
		LegFrontLeft.setRotationPoint( 3F, 21.5F, -2F );
		
		LegFrontLeftFoot = new ModelRenderer( this, 5, 4 );
        LegFrontLeftFoot.setTextureSize( 64, 64 );
        LegFrontLeftFoot.addBox( -0.5F, 0F, -1F, 1, 1, 2);
        LegFrontLeftFoot.setRotationPoint( 3F, 23F, -2.5F );
		
		Block3 = new ModelRenderer( this, 0, 25 );
		Block3.setTextureSize( 64, 64 );
		Block3.addBox( -3.5F, -0.5F, -5.5F, 7, 1, 11);
		Block3.setRotationPoint( -1.311342E-07F, 20.5F, -1.5F );
		Block3b = new ModelRenderer( this, 49, 24 );
		Block3b.setTextureSize( 64, 64 );
		Block3b.addBox( -2.5F, -0.5F, -0.5F, 5, 1, 1);
		Block3b.setRotationPoint( 3.934025E-07F, 20.5F, 4.5F );
		Block3c = new ModelRenderer( this, 44, 19 );
		Block3c.setTextureSize( 64, 64 );
		Block3c.addBox( -0.5F, -0.5F, -3F, 1, 1, 6);
		Block3c.setRotationPoint( 4F, 20.5F, -3.496911E-07F );
		Block3d = new ModelRenderer( this, 44, 19 );
		Block3d.setTextureSize( 64, 64 );
		Block3d.addBox( -0.5F, -0.5F, -3F, 1, 1, 6);
		Block3d.setRotationPoint( -4F, 20.5F, 3.496911E-07F );
		Block2 = new ModelRenderer( this, 0, 13 );
		Block2.setTextureSize( 64, 64 );
		Block2.addBox( -3.5F, -0.5F, -5.5F, 7, 1, 11);
		Block2.setRotationPoint( -4.371139E-08F, 21.5F, -0.5F );
		Block2b = new ModelRenderer( this, 45, 7 );
		Block2b.setTextureSize( 64, 64 );
		Block2b.addBox( -2.5F, -0.5F, -0.5F, 5, 1, 1);
		Block2b.setRotationPoint( -5.68248E-07F, 21.5F, -6.5F );
		Block2c = new ModelRenderer( this, 45, 7 );
		Block2c.setTextureSize( 64, 64 );
		Block2c.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block2c.setRotationPoint( -6.556708E-07F, 21.5F, -7.5F );
		Block2d = new ModelRenderer( this, 42, 24 );
		Block2d.setTextureSize( 64, 64 );
		Block2d.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		Block2d.setRotationPoint( 4F, 21.5F, 2F );
		Block2e = new ModelRenderer( this, 42, 24 );
		Block2e.setTextureSize( 64, 64 );
		Block2e.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		Block2e.setRotationPoint( -4F, 21.5F, 2F );
		Block1 = new ModelRenderer( this, 38, 0 );
		Block1.setTextureSize( 64, 64 );
		Block1.addBox( -2.5F, -0.5F, -4F, 5, 1, 8);
		Block1.setRotationPoint( 0F, 22.50005F, 0F );
		Block1b = new ModelRenderer( this, 45, 7 );
		Block1b.setTextureSize( 64, 64 );
		Block1b.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block1b.setRotationPoint( -3.934025E-07F, 22.50005F, -4.5F );
		Block1c = new ModelRenderer( this, 45, 20 );
		Block1c.setTextureSize( 64, 64 );
		Block1c.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block1c.setRotationPoint( 3.934025E-07F, 22.50005F, 4.5F );
		Block4 = new ModelRenderer( this, 29, 21 );
		Block4.setTextureSize( 64, 64 );
		Block4.addBox( -4.5F, -1.5F, -3.5F, 9, 3, 7);
		Block4.setRotationPoint( -4.371139E-08F, 18.5F, -0.5F );
		Block4b = new ModelRenderer( this, 33, 25 );
		Block4b.setTextureSize( 64, 64 );
		Block4b.addBox( -2.5F, -0.5F, -1.5F, 5, 1, 3);
		Block4b.setRotationPoint( -4.808253E-07F, 17.5F, -5.5F );
		Block4c = new ModelRenderer( this, 35, 27 );
		Block4c.setTextureSize( 64, 64 );
		Block4c.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block4c.setRotationPoint( -6.556708E-07F, 17.5F, -7.5F );
		Block5 = new ModelRenderer( this, 30, 21 );
		Block5.setTextureSize( 64, 64 );
		Block5.addBox( -3.5F, -0.5F, -4F, 7, 1, 8);
		Block5.setRotationPoint( -8.742278E-08F, 16.5F, -1F );
		Block5b = new ModelRenderer( this, 35, 27 );
		Block5b.setTextureSize( 64, 64 );
		Block5b.addBox( -2.5F, -0.5F, -0.5F, 5, 1, 1);
		Block5b.setRotationPoint( -4.808253E-07F, 16.5F, -5.5F );
		Block5c = new ModelRenderer( this, 35, 27 );
		Block5c.setTextureSize( 64, 64 );
		Block5c.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block5c.setRotationPoint( -5.68248E-07F, 16.5F, -6.5F );
		Block6 = new ModelRenderer( this, 30, 21 );
		Block6.setTextureSize( 64, 64 );
		Block6.addBox( -2.5F, -0.5F, -4F, 5, 1, 8);
		Block6.setRotationPoint( -8.742278E-08F, 15.5F, -1F );
		Block6b = new ModelRenderer( this, 35, 27 );
		Block6b.setTextureSize( 64, 64 );
		Block6b.addBox( -1.5F, -0.5F, -0.5F, 3, 1, 1);
		Block6b.setRotationPoint( -4.808253E-07F, 15.5F, -5.5F );
		Block7 = new ModelRenderer( this, 37, 31 );
		Block7.setTextureSize( 64, 64 );
		Block7.addBox( -3.5F, -2F, -0.5F, 7, 4, 1);
		Block7.setRotationPoint( 3.059797E-07F, 18F, 3.5F );
		Block8 = new ModelRenderer( this, 43, 21 );
		Block8.setTextureSize( 64, 64 );
		Block8.addBox( -2.5F, -1.5F, -0.5F, 5, 3, 1);
		Block8.setRotationPoint( 3.934025E-07F, 18.5F, 4.5F );

		Head = new ModelRenderer( this, 20, 0 );
		Head.setTextureSize( 64, 64 );
		Head.addBox( -2.5F, -1.5F, -2F, 5, 3, 4);
		Head.setRotationPoint( -5.245366E-07F, 19.5F, -6F );

		Snout = new ModelRenderer( this, 1, 1 );
		Snout.setTextureSize( 64, 64 );
		Snout.addBox( -1.5F, -1F, -0.5F, 3, 2, 1);
		Snout.setRotationPoint( -7.430936E-07F + 5.245366E-07F, 20F - 19.5F, -8.5F + 6F);

		nose = new ModelRenderer( this, 0, 4 );
		nose.setTextureSize( 64, 64 );
		nose.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		nose.setRotationPoint( -8.305163E-07F + 5.245366E-07F, 20F - 19.5F, -9.5F + 6F);

		cheek1 = new ModelRenderer( this, 21, 1 );
		cheek1.setTextureSize( 64, 64 );
		cheek1.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		cheek1.setRotationPoint( 3F + 5.245366E-07F, 19.5F - 19.5F, -5.5F + 6F);

		cheek2 = new ModelRenderer( this, 21, 1 );
		cheek2.setTextureSize( 64, 64 );
		cheek2.addBox( -0.5F, -0.5F, -1.5F, 1, 1, 3);
		cheek2.setRotationPoint( -3F + 5.245366E-07F, 19.5F - 19.5F, -5.5F + 6F);

		EarR = new ModelRenderer( this, 1, 9 );
		EarR.setTextureSize( 64, 64 );
		EarR.addBox( -2F, -1F, -0.5F, 2, 2, 1);
		EarR.setRotationPoint( 2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F  + 6F);

		EarL = new ModelRenderer( this, 1, 9 );
		EarL.setTextureSize( 64, 64 );
		EarL.addBox( -2F, -1F, -0.5F, 2, 2, 1);
		EarL.setRotationPoint( 2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F + 6F);

		EarR1 = new ModelRenderer( this, 1, 12 );
		EarR1.setTextureSize( 64, 64 );
		EarR1.addBox( 0F, -1F, -0.5F, 2, 2, 1);
		EarR1.setRotationPoint( -2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F + 6F);

		LegBackLeft = new ModelRenderer( this, 46, 1 );
        LegBackLeft.setTextureSize( 64, 64 );
        LegBackLeft.addBox( -0.5F, -0.5F, -1F, 1, 2, 2);
        LegBackLeft.setRotationPoint( 3F, 21.5F, 2F );
        
        LegFrontLeftFoot1 = new ModelRenderer( this, 5, 4 );
        LegFrontLeftFoot1.setTextureSize( 64, 64 );
        LegFrontLeftFoot1.addBox( -0.5F, 0F, -1F, 1, 1, 2);
        LegFrontLeftFoot1.setRotationPoint( 3F, 23F, 1.5F );
        
        LegBackRight = new ModelRenderer( this, 46, 1 );
        LegBackRight.setTextureSize( 64, 64 );
        LegBackRight.addBox( -0.5F, -0.5F, -1F, 1, 2, 2);
        LegBackRight.setRotationPoint( -3F, 21.5F, 2F );
        
        LegFrontLeftFoot2 = new ModelRenderer( this, 5, 4 );
        LegFrontLeftFoot2.setTextureSize( 64, 64 );
        LegFrontLeftFoot2.addBox( -0.5F, 0F, -1F, 1, 1, 2);
        LegFrontLeftFoot2.setRotationPoint( -3F, 23F, 1.5F );
        
        LegFrontRight = new ModelRenderer( this, 46, 1 );
        LegFrontRight.setTextureSize( 64, 64 );
        LegFrontRight.addBox( -0.5F, -0.5F, -1F, 1, 2, 2);
        LegFrontRight.setRotationPoint( -3F, 21.5F, -2F );
        
        LegFrontLeftFoot3 = new ModelRenderer( this, 5, 4 );
        LegFrontLeftFoot3.setTextureSize( 64, 64 );
        LegFrontLeftFoot3.addBox( -0.5F, 0F, -1F, 1, 1, 2);
        LegFrontLeftFoot3.setRotationPoint( -3F, 23F, -2.5F );
        
		Spike1 = new ModelRenderer( this, 15, 0 );
		Spike1.setTextureSize( 64, 64 );
		Spike1.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike1.setRotationPoint( 4.808253E-07F, 22.50005F, 5.5F );
		Spike6 = new ModelRenderer( this, 15, 0 );
		Spike6.setTextureSize( 64, 64 );
		Spike6.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike6.setRotationPoint( 1F, 20.50005F, 5.5F );
		Spike2 = new ModelRenderer( this, 15, 0 );
		Spike2.setTextureSize( 64, 64 );
		Spike2.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike2.setRotationPoint( 2F, 21.50005F, 5.5F );
		Spike3 = new ModelRenderer( this, 15, 0 );
		Spike3.setTextureSize( 64, 64 );
		Spike3.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike3.setRotationPoint( -2F, 21.50005F, 5.5F );
		Spike4 = new ModelRenderer( this, 15, 0 );
		Spike4.setTextureSize( 64, 64 );
		Spike4.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike4.setRotationPoint( -5F, 21.50005F, 2.5F );
		Spike5 = new ModelRenderer( this, 15, 0 );
		Spike5.setTextureSize( 64, 64 );
		Spike5.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike5.setRotationPoint( 5F, 21.50005F, 2.5F );
		Spike7 = new ModelRenderer( this, 15, 0 );
		Spike7.setTextureSize( 64, 64 );
		Spike7.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike7.setRotationPoint( -0.9999995F, 20.50005F, 5.5F );
		Spike8 = new ModelRenderer( this, 15, 0 );
		Spike8.setTextureSize( 64, 64 );
		Spike8.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike8.setRotationPoint( 4F, 20.50005F, 3.5F );
		Spike9 = new ModelRenderer( this, 15, 0 );
		Spike9.setTextureSize( 64, 64 );
		Spike9.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike9.setRotationPoint( -4F, 20.50005F, 3.5F );
		Spike10 = new ModelRenderer( this, 15, 0 );
		Spike10.setTextureSize( 64, 64 );
		Spike10.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike10.setRotationPoint( -5F, 20.50005F, -0.4999996F );
		Spike11 = new ModelRenderer( this, 15, 0 );
		Spike11.setTextureSize( 64, 64 );
		Spike11.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike11.setRotationPoint( 5F, 20.50005F, -0.5000004F );
		Spike12 = new ModelRenderer( this, 15, 0 );
		Spike12.setTextureSize( 64, 64 );
		Spike12.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike12.setRotationPoint( 5F, 19.50005F, -2.5F );
		Spike13 = new ModelRenderer( this, 15, 0 );
		Spike13.setTextureSize( 64, 64 );
		Spike13.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike13.setRotationPoint( 5F, 19.50005F, 1.5F );
		Spike14 = new ModelRenderer( this, 15, 0 );
		Spike14.setTextureSize( 64, 64 );
		Spike14.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike14.setRotationPoint( 5F, 17.50005F, 1.5F );
		Spike15 = new ModelRenderer( this, 15, 0 );
		Spike15.setTextureSize( 64, 64 );
		Spike15.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike15.setRotationPoint( 5F, 17.50005F, -2.5F );
		Spike16 = new ModelRenderer( this, 15, 0 );
		Spike16.setTextureSize( 64, 64 );
		Spike16.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike16.setRotationPoint( 5F, 18.50005F, -0.5000005F );
		Spike22 = new ModelRenderer( this, 15, 0 );
		Spike22.setTextureSize( 64, 64 );
		Spike22.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike22.setRotationPoint( 4F, 16.50005F, -0.5000004F );
		Spike23 = new ModelRenderer( this, 15, 0 );
		Spike23.setTextureSize( 64, 64 );
		Spike23.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike23.setRotationPoint( -4F, 16.50005F, -0.4999997F );
		Spike24 = new ModelRenderer( this, 15, 0 );
		Spike24.setTextureSize( 64, 64 );
		Spike24.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike24.setRotationPoint( -4.371139E-07F, 16.50005F, -7.5F );
		Spike25 = new ModelRenderer( this, 15, 0 );
		Spike25.setTextureSize( 64, 64 );
		Spike25.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike25.setRotationPoint( 0.9999996F, 15.50005F, -6.5F );
		Spike26 = new ModelRenderer( this, 15, 0 );
		Spike26.setTextureSize( 64, 64 );
		Spike26.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike26.setRotationPoint( -1F, 15.50005F, -6.5F );
		Spike27 = new ModelRenderer( this, 15, 0 );
		Spike27.setTextureSize( 64, 64 );
		Spike27.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike27.setRotationPoint( 3F, 15.50005F, -2.5F );
		Spike28 = new ModelRenderer( this, 15, 0 );
		Spike28.setTextureSize( 64, 64 );
		Spike28.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike28.setRotationPoint( 3F, 15.50005F, 1.5F );
		Spike29 = new ModelRenderer( this, 15, 0 );
		Spike29.setTextureSize( 64, 64 );
		Spike29.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike29.setRotationPoint( -3F, 15.50005F, 1.5F );
		Spike30 = new ModelRenderer( this, 15, 0 );
		Spike30.setTextureSize( 64, 64 );
		Spike30.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike30.setRotationPoint( -3F, 15.50005F, -2.5F );
		Spike31 = new ModelRenderer( this, 15, 0 );
		Spike31.setTextureSize( 64, 64 );
		Spike31.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike31.setRotationPoint( 1F, 14.50005F, 1.5F );
		Spike32 = new ModelRenderer( this, 15, 0 );
		Spike32.setTextureSize( 64, 64 );
		Spike32.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike32.setRotationPoint( -0.9999996F, 14.50005F, 1.5F );
		Spike33 = new ModelRenderer( this, 15, 0 );
		Spike33.setTextureSize( 64, 64 );
		Spike33.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike33.setRotationPoint( -2F, 14.50005F, -0.4999999F );
		Spike34 = new ModelRenderer( this, 15, 0 );
		Spike34.setTextureSize( 64, 64 );
		Spike34.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike34.setRotationPoint( 1.748456E-07F, 14.50005F, -0.5000001F );
		Spike35 = new ModelRenderer( this, 15, 0 );
		Spike35.setTextureSize( 64, 64 );
		Spike35.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike35.setRotationPoint( 2F, 14.50005F, -0.5000002F );
		Spike36 = new ModelRenderer( this, 15, 0 );
		Spike36.setTextureSize( 64, 64 );
		Spike36.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike36.setRotationPoint( 1F, 14.50005F, -2.5F );
		Spike37 = new ModelRenderer( this, 15, 0 );
		Spike37.setTextureSize( 64, 64 );
		Spike37.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike37.setRotationPoint( -1F, 14.50005F, -2.5F );
		Spike38 = new ModelRenderer( this, 15, 0 );
		Spike38.setTextureSize( 64, 64 );
		Spike38.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike38.setRotationPoint( -1.748456E-07F, 14.50005F, -4.5F );
		Spike39 = new ModelRenderer( this, 15, 0 );
		Spike39.setTextureSize( 64, 64 );
		Spike39.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike39.setRotationPoint( 2F, 14.50005F, -4.5F );
		Spike40 = new ModelRenderer( this, 15, 0 );
		Spike40.setTextureSize( 64, 64 );
		Spike40.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike40.setRotationPoint( -2F, 14.50005F, -4.5F );
		Spike17 = new ModelRenderer( this, 15, 0 );
		Spike17.setTextureSize( 64, 64 );
		Spike17.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike17.setRotationPoint( -5F, 19.50005F, -2.5F );
		Spike18 = new ModelRenderer( this, 15, 0 );
		Spike18.setTextureSize( 64, 64 );
		Spike18.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike18.setRotationPoint( -5F, 19.50005F, 1.5F );
		Spike19 = new ModelRenderer( this, 15, 0 );
		Spike19.setTextureSize( 64, 64 );
		Spike19.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike19.setRotationPoint( -5F, 17.50005F, 1.5F );
		Spike20 = new ModelRenderer( this, 15, 0 );
		Spike20.setTextureSize( 64, 64 );
		Spike20.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike20.setRotationPoint( -5F, 17.50005F, -2.5F );
		Spike21 = new ModelRenderer( this, 15, 0 );
		Spike21.setTextureSize( 64, 64 );
		Spike21.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike21.setRotationPoint( -5F, 18.50005F, -0.4999995F );
		Spike41 = new ModelRenderer( this, 15, 0 );
		Spike41.setTextureSize( 64, 64 );
		Spike41.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike41.setRotationPoint( 4F, 18.50005F, 3.5F );
		Spike42 = new ModelRenderer( this, 15, 0 );
		Spike42.setTextureSize( 64, 64 );
		Spike42.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike42.setRotationPoint( -4F, 18.50005F, 3.5F );
		Spike43 = new ModelRenderer( this, 15, 0 );
		Spike43.setTextureSize( 64, 64 );
		Spike43.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike43.setRotationPoint( 2F, 18.50005F, 5.5F );
		Spike44 = new ModelRenderer( this, 15, 0 );
		Spike44.setTextureSize( 64, 64 );
		Spike44.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike44.setRotationPoint( 4.808253E-07F, 18.50005F, 5.5F );
		Spike45 = new ModelRenderer( this, 15, 0 );
		Spike45.setTextureSize( 64, 64 );
		Spike45.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike45.setRotationPoint( -2F, 18.50005F, 5.5F );
		Spike46 = new ModelRenderer( this, 15, 0 );
		Spike46.setTextureSize( 64, 64 );
		Spike46.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike46.setRotationPoint( 3F, 19.50005F, 4.5F );
		Spike47 = new ModelRenderer( this, 15, 0 );
		Spike47.setTextureSize( 64, 64 );
		Spike47.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike47.setRotationPoint( -3F, 19.50005F, 4.5F );
		Spike48 = new ModelRenderer( this, 15, 0 );
		Spike48.setTextureSize( 64, 64 );
		Spike48.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike48.setRotationPoint( 1F, 16.50005F, 4.5F );
		Spike49 = new ModelRenderer( this, 15, 0 );
		Spike49.setTextureSize( 64, 64 );
		Spike49.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike49.setRotationPoint( -0.9999996F, 16.50005F, 4.5F );
		Spike50 = new ModelRenderer( this, 15, 0 );
		Spike50.setTextureSize( 64, 64 );
		Spike50.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Spike50.setRotationPoint( 3.059797E-07F, 15.50005F, 3.5F );


		this.Head.addChild(this.Snout);
		this.Head.addChild(this.nose);
		this.Head.addChild(this.cheek1);
		this.Head.addChild(this.cheek2);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarR);
		this.Head.addChild(this.EarR1);





	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{



		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		this.Head.render(scale);
		this.BodyNode.render(scale);

		this.Block3.render(scale);
		this.Block3b.render(scale);
		this.Block3c.render(scale);
		this.Block3d.render(scale);
		this.Block2.render(scale);
		this.Block2b.render(scale);
		this.Block2c.render(scale);
		this.Block2d.render(scale);
		this.Block2e.render(scale);
		this.Block1.render(scale);
		this.Block1b.render(scale);
		this.Block1c.render(scale);
		this.Block4.render(scale);
		this.Block4b.render(scale);
		this.Block4c.render(scale);
		this.Block5.render(scale);
		this.Block5b.render(scale);
		this.Block5c.render(scale);
		this.Block6.render(scale);
		this.Block6b.render(scale);
		this.Block7.render(scale);
		this.Block8.render(scale);

		this.LegBackLeft.render(scale);
		this.LegBackRight.render(scale);
		this.LegFrontLeft.render(scale);
		this.LegFrontRight.render(scale);

		this.LegFrontLeftFoot.render(scale);
		this.LegFrontLeftFoot1.render(scale);
		this.LegFrontLeftFoot2.render(scale);
		this.LegFrontLeftFoot3.render(scale);

		this.Spike1.render(scale);
		this.Spike2.render(scale);
		this.Spike3.render(scale);
		this.Spike4.render(scale);
		this.Spike5.render(scale);
		this.Spike6.render(scale);
		this.Spike7.render(scale);
		this.Spike8.render(scale);
		this.Spike9.render(scale);
		this.Spike10.render(scale);
		this.Spike11.render(scale);
		this.Spike12.render(scale);
		this.Spike13.render(scale);
		this.Spike14.render(scale);
		this.Spike15.render(scale);
		this.Spike16.render(scale);
		this.Spike17.render(scale);
		this.Spike18.render(scale);
		this.Spike19.render(scale);
		this.Spike20.render(scale);
		this.Spike21.render(scale);
		this.Spike22.render(scale);
		this.Spike23.render(scale);
		this.Spike24.render(scale);
		this.Spike25.render(scale);
		this.Spike26.render(scale);
		this.Spike27.render(scale);
		this.Spike28.render(scale);
		this.Spike29.render(scale);
		this.Spike30.render(scale);
		this.Spike31.render(scale);
		this.Spike32.render(scale);
		this.Spike33.render(scale);
		this.Spike34.render(scale);
		this.Spike35.render(scale);
		this.Spike36.render(scale);
		this.Spike37.render(scale);
		this.Spike38.render(scale);
		this.Spike39.render(scale);
		this.Spike40.render(scale);
		this.Spike41.render(scale);
		this.Spike42.render(scale);
		this.Spike43.render(scale);
		this.Spike44.render(scale);
		this.Spike45.render(scale);
		this.Spike46.render(scale);
		this.Spike47.render(scale);
		this.Spike48.render(scale);
		this.Spike49.render(scale);
		this.Spike50.render(scale);


	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityHedgehog) {
			this.Head.rotationPointY = 19.5F + ((EntityHedgehog)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 0F; //number should match model Y point
			this.headRotationAngleX = ((EntityHedgehog)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityHedgehogAlbino) {
			this.Head.rotationPointY = 19.5F + ((EntityHedgehogAlbino)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 0F; //number should match model Y point
			this.headRotationAngleX = ((EntityHedgehogAlbino)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);

		}

	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{

		BodyNode.rotateAngleY = -3.141593F;
		Block3.rotateAngleY = -3.141593F;
		Block3b.rotateAngleY = -3.141593F;
		Block3c.rotateAngleY = -3.141593F;
		Block3d.rotateAngleY = -3.141593F;
		Block2.rotateAngleY = -3.141593F;
		Block2b.rotateAngleY = -3.141593F;
		Block2c.rotateAngleY = -3.141593F;
		Block2d.rotateAngleY = -3.141593F;
		Block2e.rotateAngleY = -3.141593F;
		Block1.rotateAngleY = -3.141593F;
		Block1b.rotateAngleY = -3.141593F;
		Block1c.rotateAngleY = -3.141593F;
		Block4.rotateAngleY = -3.141593F;
		Block4b.rotateAngleY = -3.141593F;
		Block4c.rotateAngleY = -3.141593F;
		Block5.rotateAngleY = -3.141593F;
		Block5b.rotateAngleY = -3.141593F;
		Block5c.rotateAngleY = -3.141593F;
		Block6.rotateAngleY = -3.141593F;
		Block6b.rotateAngleY = -3.141593F;
		Block7.rotateAngleY = -3.141593F;
		Block8.rotateAngleY = -3.141593F;
		Head.rotateAngleY = -3.141593F;
		Snout.rotateAngleY = -3.141593F;
		nose.rotateAngleY = -3.141593F;
		cheek1.rotateAngleY = -3.141593F;
		cheek2.rotateAngleY = -3.141593F;

		EarR.rotateAngleX = -1.26388E-09F;
		EarR.rotateAngleY = -2.96706F;
		EarR.rotateAngleZ = 0.3141593F;

		EarL.rotateAngleX = -1.26388E-09F;
		EarL.rotateAngleY = -2.96706F;
		EarL.rotateAngleZ = 0.3141593F;

		EarR1.rotateAngleX = 9.443689E-10F;
		EarR1.rotateAngleY = 2.96706F;
		EarR1.rotateAngleZ = -0.3141593F;

		LegFrontLeft.rotateAngleY = -3.141593F;
		LegFrontLeftFoot.rotateAngleY = 2.932153F;
		LegBackLeft.rotateAngleY = -3.141593F;
		LegFrontLeftFoot1.rotateAngleY = 2.932153F;
		LegBackRight.rotateAngleY = -3.141593F;
		LegFrontLeftFoot2.rotateAngleY = -2.932153F;
		LegFrontRight.rotateAngleY = -3.141593F;
		LegFrontLeftFoot3.rotateAngleY = -2.932153F;

		Spike1.rotateAngleY = -3.141593F;
		Spike6.rotateAngleY = -3.141593F;
		Spike2.rotateAngleY = -3.141593F;
		Spike3.rotateAngleY = -3.141593F;
		Spike4.rotateAngleY = -3.141593F;
		Spike5.rotateAngleY = -3.141593F;
		Spike7.rotateAngleY = -3.141593F;
		Spike8.rotateAngleY = -3.141593F;
		Spike9.rotateAngleY = -3.141593F;
		Spike10.rotateAngleY = -3.141593F;
		Spike11.rotateAngleY = -3.141593F;
		Spike12.rotateAngleY = -3.141593F;
		Spike13.rotateAngleY = -3.141593F;
		Spike14.rotateAngleY = -3.141593F;
		Spike15.rotateAngleY = -3.141593F;
		Spike16.rotateAngleY = -3.141593F;
		Spike22.rotateAngleY = -3.141593F;
		Spike23.rotateAngleY = -3.141593F;
		Spike24.rotateAngleY = -3.141593F;
		Spike25.rotateAngleY = -3.141593F;
		Spike26.rotateAngleY = -3.141593F;
		Spike27.rotateAngleY = -3.141593F;
		Spike28.rotateAngleY = -3.141593F;
		Spike29.rotateAngleY = -3.141593F;
		Spike30.rotateAngleY = -3.141593F;
		Spike31.rotateAngleY = -3.141593F;
		Spike32.rotateAngleY = -3.141593F;
		Spike33.rotateAngleY = -3.141593F;
		Spike34.rotateAngleY = -3.141593F;
		Spike35.rotateAngleY = -3.141593F;
		Spike36.rotateAngleY = -3.141593F;
		Spike37.rotateAngleY = -3.141593F;
		Spike38.rotateAngleY = -3.141593F;
		Spike39.rotateAngleY = -3.141593F;
		Spike40.rotateAngleY = -3.141593F;
		Spike17.rotateAngleY = -3.141593F;
		Spike18.rotateAngleY = -3.141593F;
		Spike19.rotateAngleY = -3.141593F;
		Spike20.rotateAngleY = -3.141593F;
		Spike21.rotateAngleY = -3.141593F;
		Spike41.rotateAngleY = -3.141593F;
		Spike42.rotateAngleY = -3.141593F;
		Spike43.rotateAngleY = -3.141593F;
		Spike44.rotateAngleY = -3.141593F;
		Spike45.rotateAngleY = -3.141593F;
		Spike46.rotateAngleY = -3.141593F;
		Spike47.rotateAngleY = -3.141593F;
		Spike48.rotateAngleY = -3.141593F;
		Spike49.rotateAngleY = -3.141593F;
		Spike50.rotateAngleY = -3.141593F;


		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;

		this.BodyNode.rotateAngleX = ((float)Math.PI/2);

		this.LegFrontLeft.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LegFrontLeftFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

		this.LegBackLeft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.LegFrontLeftFoot1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

		this.LegBackRight.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.LegFrontLeftFoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

		this.LegFrontRight.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LegFrontLeftFoot3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}




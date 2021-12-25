package com.animania.addons.extra.client.model.rodents;

import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
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
		this(0.0f);
	}

	public ModelHedgehog(float par1)
	{
		this.BodyNode = new ModelRenderer(this, 16, 16);
		this.BodyNode.setTextureSize(64, 64);
		this.BodyNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.BodyNode.setRotationPoint(0F, 19.5F, 0F);

		this.LegFrontLeft = new ModelRenderer(this, 46, 1);
		this.LegFrontLeft.setTextureSize(64, 64);
		this.LegFrontLeft.addBox(-0.5F, -0.5F, -1F, 1, 2, 2);
		this.LegFrontLeft.setRotationPoint(3F, 21.5F, -2F);

		this.LegFrontLeftFoot = new ModelRenderer(this, 5, 4);
		this.LegFrontLeftFoot.setTextureSize(64, 64);
		this.LegFrontLeftFoot.addBox(-0.5F, 0F, -1F, 1, 1, 2);
		this.LegFrontLeftFoot.setRotationPoint(3F, 23F, -2.5F);

		this.Block3 = new ModelRenderer(this, 0, 25);
		this.Block3.setTextureSize(64, 64);
		this.Block3.addBox(-3.5F, -0.5F, -5.5F, 7, 1, 11);
		this.Block3.setRotationPoint(-1.311342E-07F, 20.5F, -1.5F);
		this.Block3b = new ModelRenderer(this, 49, 24);
		this.Block3b.setTextureSize(64, 64);
		this.Block3b.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
		this.Block3b.setRotationPoint(3.934025E-07F, 20.5F, 4.5F);
		this.Block3c = new ModelRenderer(this, 44, 19);
		this.Block3c.setTextureSize(64, 64);
		this.Block3c.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
		this.Block3c.setRotationPoint(4F, 20.5F, -3.496911E-07F);
		this.Block3d = new ModelRenderer(this, 44, 19);
		this.Block3d.setTextureSize(64, 64);
		this.Block3d.addBox(-0.5F, -0.5F, -3F, 1, 1, 6);
		this.Block3d.setRotationPoint(-4F, 20.5F, 3.496911E-07F);
		this.Block2 = new ModelRenderer(this, 0, 13);
		this.Block2.setTextureSize(64, 64);
		this.Block2.addBox(-3.5F, -0.5F, -5.5F, 7, 1, 11);
		this.Block2.setRotationPoint(-4.371139E-08F, 21.5F, -0.5F);
		this.Block2b = new ModelRenderer(this, 45, 7);
		this.Block2b.setTextureSize(64, 64);
		this.Block2b.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
		this.Block2b.setRotationPoint(-5.68248E-07F, 21.5F, -6.5F);
		this.Block2c = new ModelRenderer(this, 45, 7);
		this.Block2c.setTextureSize(64, 64);
		this.Block2c.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block2c.setRotationPoint(-6.556708E-07F, 21.5F, -7.5F);
		this.Block2d = new ModelRenderer(this, 42, 24);
		this.Block2d.setTextureSize(64, 64);
		this.Block2d.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		this.Block2d.setRotationPoint(4F, 21.5F, 2F);
		this.Block2e = new ModelRenderer(this, 42, 24);
		this.Block2e.setTextureSize(64, 64);
		this.Block2e.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
		this.Block2e.setRotationPoint(-4F, 21.5F, 2F);
		this.Block1 = new ModelRenderer(this, 38, 0);
		this.Block1.setTextureSize(64, 64);
		this.Block1.addBox(-2.5F, -0.5F, -4F, 5, 1, 8);
		this.Block1.setRotationPoint(0F, 22.50005F, 0F);
		this.Block1b = new ModelRenderer(this, 45, 7);
		this.Block1b.setTextureSize(64, 64);
		this.Block1b.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block1b.setRotationPoint(-3.934025E-07F, 22.50005F, -4.5F);
		this.Block1c = new ModelRenderer(this, 45, 20);
		this.Block1c.setTextureSize(64, 64);
		this.Block1c.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block1c.setRotationPoint(3.934025E-07F, 22.50005F, 4.5F);
		this.Block4 = new ModelRenderer(this, 29, 21);
		this.Block4.setTextureSize(64, 64);
		this.Block4.addBox(-4.5F, -1.5F, -3.5F, 9, 3, 7);
		this.Block4.setRotationPoint(-4.371139E-08F, 18.5F, -0.5F);
		this.Block4b = new ModelRenderer(this, 33, 25);
		this.Block4b.setTextureSize(64, 64);
		this.Block4b.addBox(-2.5F, -0.5F, -1.5F, 5, 1, 3);
		this.Block4b.setRotationPoint(-4.808253E-07F, 17.5F, -5.5F);
		this.Block4c = new ModelRenderer(this, 35, 27);
		this.Block4c.setTextureSize(64, 64);
		this.Block4c.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block4c.setRotationPoint(-6.556708E-07F, 17.5F, -7.5F);
		this.Block5 = new ModelRenderer(this, 30, 21);
		this.Block5.setTextureSize(64, 64);
		this.Block5.addBox(-3.5F, -0.5F, -4F, 7, 1, 8);
		this.Block5.setRotationPoint(-8.742278E-08F, 16.5F, -1F);
		this.Block5b = new ModelRenderer(this, 35, 27);
		this.Block5b.setTextureSize(64, 64);
		this.Block5b.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
		this.Block5b.setRotationPoint(-4.808253E-07F, 16.5F, -5.5F);
		this.Block5c = new ModelRenderer(this, 35, 27);
		this.Block5c.setTextureSize(64, 64);
		this.Block5c.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block5c.setRotationPoint(-5.68248E-07F, 16.5F, -6.5F);
		this.Block6 = new ModelRenderer(this, 30, 21);
		this.Block6.setTextureSize(64, 64);
		this.Block6.addBox(-2.5F, -0.5F, -4F, 5, 1, 8);
		this.Block6.setRotationPoint(-8.742278E-08F, 15.5F, -1F);
		this.Block6b = new ModelRenderer(this, 35, 27);
		this.Block6b.setTextureSize(64, 64);
		this.Block6b.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
		this.Block6b.setRotationPoint(-4.808253E-07F, 15.5F, -5.5F);
		this.Block7 = new ModelRenderer(this, 37, 31);
		this.Block7.setTextureSize(64, 64);
		this.Block7.addBox(-3.5F, -2F, -0.5F, 7, 4, 1);
		this.Block7.setRotationPoint(3.059797E-07F, 18F, 3.5F);
		this.Block8 = new ModelRenderer(this, 43, 21);
		this.Block8.setTextureSize(64, 64);
		this.Block8.addBox(-2.5F, -1.5F, -0.5F, 5, 3, 1);
		this.Block8.setRotationPoint(3.934025E-07F, 18.5F, 4.5F);

		this.Head = new ModelRenderer(this, 20, 0);
		this.Head.setTextureSize(64, 64);
		this.Head.addBox(-2.5F, -1.5F, -2F, 5, 3, 4);
		this.Head.setRotationPoint(-5.245366E-07F, 19.5F, -6F);

		this.Snout = new ModelRenderer(this, 1, 1);
		this.Snout.setTextureSize(64, 64);
		this.Snout.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		this.Snout.setRotationPoint(-7.430936E-07F + 5.245366E-07F, 20F - 19.5F, -8.5F + 6F);

		this.nose = new ModelRenderer(this, 0, 4);
		this.nose.setTextureSize(64, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(-8.305163E-07F + 5.245366E-07F, 20F - 19.5F, -9.5F + 6F);

		this.cheek1 = new ModelRenderer(this, 21, 1);
		this.cheek1.setTextureSize(64, 64);
		this.cheek1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.cheek1.setRotationPoint(3F + 5.245366E-07F, 19.5F - 19.5F, -5.5F + 6F);

		this.cheek2 = new ModelRenderer(this, 21, 1);
		this.cheek2.setTextureSize(64, 64);
		this.cheek2.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
		this.cheek2.setRotationPoint(-3F + 5.245366E-07F, 19.5F - 19.5F, -5.5F + 6F);

		this.EarR = new ModelRenderer(this, 1, 9);
		this.EarR.setTextureSize(64, 64);
		this.EarR.addBox(-2F, -1F, -0.5F, 2, 2, 1);
		this.EarR.setRotationPoint(2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F + 6F);

		this.EarL = new ModelRenderer(this, 1, 9);
		this.EarL.setTextureSize(64, 64);
		this.EarL.addBox(-2F, -1F, -0.5F, 2, 2, 1);
		this.EarL.setRotationPoint(2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F + 6F);

		this.EarR1 = new ModelRenderer(this, 1, 12);
		this.EarR1.setTextureSize(64, 64);
		this.EarR1.addBox(0F, -1F, -0.5F, 2, 2, 1);
		this.EarR1.setRotationPoint(-2.5F + 5.245366E-07F, 18.5F - 19.5F, -5F + 6F);

		this.LegBackLeft = new ModelRenderer(this, 46, 1);
		this.LegBackLeft.setTextureSize(64, 64);
		this.LegBackLeft.addBox(-0.5F, -0.5F, -1F, 1, 2, 2);
		this.LegBackLeft.setRotationPoint(3F, 21.5F, 2F);

		this.LegFrontLeftFoot1 = new ModelRenderer(this, 5, 4);
		this.LegFrontLeftFoot1.setTextureSize(64, 64);
		this.LegFrontLeftFoot1.addBox(-0.5F, 0F, -1F, 1, 1, 2);
		this.LegFrontLeftFoot1.setRotationPoint(3F, 23F, 1.5F);

		this.LegBackRight = new ModelRenderer(this, 46, 1);
		this.LegBackRight.setTextureSize(64, 64);
		this.LegBackRight.addBox(-0.5F, -0.5F, -1F, 1, 2, 2);
		this.LegBackRight.setRotationPoint(-3F, 21.5F, 2F);

		this.LegFrontLeftFoot2 = new ModelRenderer(this, 5, 4);
		this.LegFrontLeftFoot2.setTextureSize(64, 64);
		this.LegFrontLeftFoot2.addBox(-0.5F, 0F, -1F, 1, 1, 2);
		this.LegFrontLeftFoot2.setRotationPoint(-3F, 23F, 1.5F);

		this.LegFrontRight = new ModelRenderer(this, 46, 1);
		this.LegFrontRight.setTextureSize(64, 64);
		this.LegFrontRight.addBox(-0.5F, -0.5F, -1F, 1, 2, 2);
		this.LegFrontRight.setRotationPoint(-3F, 21.5F, -2F);

		this.LegFrontLeftFoot3 = new ModelRenderer(this, 5, 4);
		this.LegFrontLeftFoot3.setTextureSize(64, 64);
		this.LegFrontLeftFoot3.addBox(-0.5F, 0F, -1F, 1, 1, 2);
		this.LegFrontLeftFoot3.setRotationPoint(-3F, 23F, -2.5F);

		this.Spike1 = new ModelRenderer(this, 15, 0);
		this.Spike1.setTextureSize(64, 64);
		this.Spike1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike1.setRotationPoint(4.808253E-07F, 22.50005F, 5.5F);
		this.Spike6 = new ModelRenderer(this, 15, 0);
		this.Spike6.setTextureSize(64, 64);
		this.Spike6.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike6.setRotationPoint(1F, 20.50005F, 5.5F);
		this.Spike2 = new ModelRenderer(this, 15, 0);
		this.Spike2.setTextureSize(64, 64);
		this.Spike2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike2.setRotationPoint(2F, 21.50005F, 5.5F);
		this.Spike3 = new ModelRenderer(this, 15, 0);
		this.Spike3.setTextureSize(64, 64);
		this.Spike3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike3.setRotationPoint(-2F, 21.50005F, 5.5F);
		this.Spike4 = new ModelRenderer(this, 15, 0);
		this.Spike4.setTextureSize(64, 64);
		this.Spike4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike4.setRotationPoint(-5F, 21.50005F, 2.5F);
		this.Spike5 = new ModelRenderer(this, 15, 0);
		this.Spike5.setTextureSize(64, 64);
		this.Spike5.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike5.setRotationPoint(5F, 21.50005F, 2.5F);
		this.Spike7 = new ModelRenderer(this, 15, 0);
		this.Spike7.setTextureSize(64, 64);
		this.Spike7.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike7.setRotationPoint(-0.9999995F, 20.50005F, 5.5F);
		this.Spike8 = new ModelRenderer(this, 15, 0);
		this.Spike8.setTextureSize(64, 64);
		this.Spike8.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike8.setRotationPoint(4F, 20.50005F, 3.5F);
		this.Spike9 = new ModelRenderer(this, 15, 0);
		this.Spike9.setTextureSize(64, 64);
		this.Spike9.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike9.setRotationPoint(-4F, 20.50005F, 3.5F);
		this.Spike10 = new ModelRenderer(this, 15, 0);
		this.Spike10.setTextureSize(64, 64);
		this.Spike10.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike10.setRotationPoint(-5F, 20.50005F, -0.4999996F);
		this.Spike11 = new ModelRenderer(this, 15, 0);
		this.Spike11.setTextureSize(64, 64);
		this.Spike11.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike11.setRotationPoint(5F, 20.50005F, -0.5000004F);
		this.Spike12 = new ModelRenderer(this, 15, 0);
		this.Spike12.setTextureSize(64, 64);
		this.Spike12.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike12.setRotationPoint(5F, 19.50005F, -2.5F);
		this.Spike13 = new ModelRenderer(this, 15, 0);
		this.Spike13.setTextureSize(64, 64);
		this.Spike13.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike13.setRotationPoint(5F, 19.50005F, 1.5F);
		this.Spike14 = new ModelRenderer(this, 15, 0);
		this.Spike14.setTextureSize(64, 64);
		this.Spike14.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike14.setRotationPoint(5F, 17.50005F, 1.5F);
		this.Spike15 = new ModelRenderer(this, 15, 0);
		this.Spike15.setTextureSize(64, 64);
		this.Spike15.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike15.setRotationPoint(5F, 17.50005F, -2.5F);
		this.Spike16 = new ModelRenderer(this, 15, 0);
		this.Spike16.setTextureSize(64, 64);
		this.Spike16.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike16.setRotationPoint(5F, 18.50005F, -0.5000005F);
		this.Spike22 = new ModelRenderer(this, 15, 0);
		this.Spike22.setTextureSize(64, 64);
		this.Spike22.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike22.setRotationPoint(4F, 16.50005F, -0.5000004F);
		this.Spike23 = new ModelRenderer(this, 15, 0);
		this.Spike23.setTextureSize(64, 64);
		this.Spike23.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike23.setRotationPoint(-4F, 16.50005F, -0.4999997F);
		this.Spike24 = new ModelRenderer(this, 15, 0);
		this.Spike24.setTextureSize(64, 64);
		this.Spike24.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike24.setRotationPoint(-4.371139E-07F, 16.50005F, -7.5F);
		this.Spike25 = new ModelRenderer(this, 15, 0);
		this.Spike25.setTextureSize(64, 64);
		this.Spike25.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike25.setRotationPoint(0.9999996F, 15.50005F, -6.5F);
		this.Spike26 = new ModelRenderer(this, 15, 0);
		this.Spike26.setTextureSize(64, 64);
		this.Spike26.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike26.setRotationPoint(-1F, 15.50005F, -6.5F);
		this.Spike27 = new ModelRenderer(this, 15, 0);
		this.Spike27.setTextureSize(64, 64);
		this.Spike27.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike27.setRotationPoint(3F, 15.50005F, -2.5F);
		this.Spike28 = new ModelRenderer(this, 15, 0);
		this.Spike28.setTextureSize(64, 64);
		this.Spike28.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike28.setRotationPoint(3F, 15.50005F, 1.5F);
		this.Spike29 = new ModelRenderer(this, 15, 0);
		this.Spike29.setTextureSize(64, 64);
		this.Spike29.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike29.setRotationPoint(-3F, 15.50005F, 1.5F);
		this.Spike30 = new ModelRenderer(this, 15, 0);
		this.Spike30.setTextureSize(64, 64);
		this.Spike30.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike30.setRotationPoint(-3F, 15.50005F, -2.5F);
		this.Spike31 = new ModelRenderer(this, 15, 0);
		this.Spike31.setTextureSize(64, 64);
		this.Spike31.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike31.setRotationPoint(1F, 14.50005F, 1.5F);
		this.Spike32 = new ModelRenderer(this, 15, 0);
		this.Spike32.setTextureSize(64, 64);
		this.Spike32.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike32.setRotationPoint(-0.9999996F, 14.50005F, 1.5F);
		this.Spike33 = new ModelRenderer(this, 15, 0);
		this.Spike33.setTextureSize(64, 64);
		this.Spike33.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike33.setRotationPoint(-2F, 14.50005F, -0.4999999F);
		this.Spike34 = new ModelRenderer(this, 15, 0);
		this.Spike34.setTextureSize(64, 64);
		this.Spike34.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike34.setRotationPoint(1.748456E-07F, 14.50005F, -0.5000001F);
		this.Spike35 = new ModelRenderer(this, 15, 0);
		this.Spike35.setTextureSize(64, 64);
		this.Spike35.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike35.setRotationPoint(2F, 14.50005F, -0.5000002F);
		this.Spike36 = new ModelRenderer(this, 15, 0);
		this.Spike36.setTextureSize(64, 64);
		this.Spike36.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike36.setRotationPoint(1F, 14.50005F, -2.5F);
		this.Spike37 = new ModelRenderer(this, 15, 0);
		this.Spike37.setTextureSize(64, 64);
		this.Spike37.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike37.setRotationPoint(-1F, 14.50005F, -2.5F);
		this.Spike38 = new ModelRenderer(this, 15, 0);
		this.Spike38.setTextureSize(64, 64);
		this.Spike38.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike38.setRotationPoint(-1.748456E-07F, 14.50005F, -4.5F);
		this.Spike39 = new ModelRenderer(this, 15, 0);
		this.Spike39.setTextureSize(64, 64);
		this.Spike39.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike39.setRotationPoint(2F, 14.50005F, -4.5F);
		this.Spike40 = new ModelRenderer(this, 15, 0);
		this.Spike40.setTextureSize(64, 64);
		this.Spike40.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike40.setRotationPoint(-2F, 14.50005F, -4.5F);
		this.Spike17 = new ModelRenderer(this, 15, 0);
		this.Spike17.setTextureSize(64, 64);
		this.Spike17.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike17.setRotationPoint(-5F, 19.50005F, -2.5F);
		this.Spike18 = new ModelRenderer(this, 15, 0);
		this.Spike18.setTextureSize(64, 64);
		this.Spike18.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike18.setRotationPoint(-5F, 19.50005F, 1.5F);
		this.Spike19 = new ModelRenderer(this, 15, 0);
		this.Spike19.setTextureSize(64, 64);
		this.Spike19.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike19.setRotationPoint(-5F, 17.50005F, 1.5F);
		this.Spike20 = new ModelRenderer(this, 15, 0);
		this.Spike20.setTextureSize(64, 64);
		this.Spike20.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike20.setRotationPoint(-5F, 17.50005F, -2.5F);
		this.Spike21 = new ModelRenderer(this, 15, 0);
		this.Spike21.setTextureSize(64, 64);
		this.Spike21.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike21.setRotationPoint(-5F, 18.50005F, -0.4999995F);
		this.Spike41 = new ModelRenderer(this, 15, 0);
		this.Spike41.setTextureSize(64, 64);
		this.Spike41.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike41.setRotationPoint(4F, 18.50005F, 3.5F);
		this.Spike42 = new ModelRenderer(this, 15, 0);
		this.Spike42.setTextureSize(64, 64);
		this.Spike42.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike42.setRotationPoint(-4F, 18.50005F, 3.5F);
		this.Spike43 = new ModelRenderer(this, 15, 0);
		this.Spike43.setTextureSize(64, 64);
		this.Spike43.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike43.setRotationPoint(2F, 18.50005F, 5.5F);
		this.Spike44 = new ModelRenderer(this, 15, 0);
		this.Spike44.setTextureSize(64, 64);
		this.Spike44.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike44.setRotationPoint(4.808253E-07F, 18.50005F, 5.5F);
		this.Spike45 = new ModelRenderer(this, 15, 0);
		this.Spike45.setTextureSize(64, 64);
		this.Spike45.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike45.setRotationPoint(-2F, 18.50005F, 5.5F);
		this.Spike46 = new ModelRenderer(this, 15, 0);
		this.Spike46.setTextureSize(64, 64);
		this.Spike46.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike46.setRotationPoint(3F, 19.50005F, 4.5F);
		this.Spike47 = new ModelRenderer(this, 15, 0);
		this.Spike47.setTextureSize(64, 64);
		this.Spike47.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike47.setRotationPoint(-3F, 19.50005F, 4.5F);
		this.Spike48 = new ModelRenderer(this, 15, 0);
		this.Spike48.setTextureSize(64, 64);
		this.Spike48.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike48.setRotationPoint(1F, 16.50005F, 4.5F);
		this.Spike49 = new ModelRenderer(this, 15, 0);
		this.Spike49.setTextureSize(64, 64);
		this.Spike49.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike49.setRotationPoint(-0.9999996F, 16.50005F, 4.5F);
		this.Spike50 = new ModelRenderer(this, 15, 0);
		this.Spike50.setTextureSize(64, 64);
		this.Spike50.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Spike50.setRotationPoint(3.059797E-07F, 15.50005F, 3.5F);

		this.Head.addChild(this.Snout);
		this.Head.addChild(this.nose);
		this.Head.addChild(this.cheek1);
		this.Head.addChild(this.cheek2);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarR);
		this.Head.addChild(this.EarR1);

	}

	@Override
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
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (LivingEntityIn instanceof EntityHedgehogBase entityHedgehog)
		{
			this.Head.rotationPointY = 19.5F + entityHedgehog.getHeadAnchorPointY(partialTickTime) * 0F; // number
			this.headRotationAngleX = entityHedgehog.getHeadAngleX(partialTickTime);

		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{

		this.BodyNode.rotateAngleY = -3.141593F;
		this.Block3.rotateAngleY = -3.141593F;
		this.Block3b.rotateAngleY = -3.141593F;
		this.Block3c.rotateAngleY = -3.141593F;
		this.Block3d.rotateAngleY = -3.141593F;
		this.Block2.rotateAngleY = -3.141593F;
		this.Block2b.rotateAngleY = -3.141593F;
		this.Block2c.rotateAngleY = -3.141593F;
		this.Block2d.rotateAngleY = -3.141593F;
		this.Block2e.rotateAngleY = -3.141593F;
		this.Block1.rotateAngleY = -3.141593F;
		this.Block1b.rotateAngleY = -3.141593F;
		this.Block1c.rotateAngleY = -3.141593F;
		this.Block4.rotateAngleY = -3.141593F;
		this.Block4b.rotateAngleY = -3.141593F;
		this.Block4c.rotateAngleY = -3.141593F;
		this.Block5.rotateAngleY = -3.141593F;
		this.Block5b.rotateAngleY = -3.141593F;
		this.Block5c.rotateAngleY = -3.141593F;
		this.Block6.rotateAngleY = -3.141593F;
		this.Block6b.rotateAngleY = -3.141593F;
		this.Block7.rotateAngleY = -3.141593F;
		this.Block8.rotateAngleY = -3.141593F;
		this.Head.rotateAngleY = -3.141593F;
		this.Snout.rotateAngleY = -3.141593F;
		this.nose.rotateAngleY = -3.141593F;
		this.cheek1.rotateAngleY = -3.141593F;
		this.cheek2.rotateAngleY = -3.141593F;

		this.EarR.rotateAngleX = -1.26388E-09F;
		this.EarR.rotateAngleY = -2.96706F;
		this.EarR.rotateAngleZ = 0.3141593F;

		this.EarL.rotateAngleX = -1.26388E-09F;
		this.EarL.rotateAngleY = -2.96706F;
		this.EarL.rotateAngleZ = 0.3141593F;

		this.EarR1.rotateAngleX = 9.443689E-10F;
		this.EarR1.rotateAngleY = 2.96706F;
		this.EarR1.rotateAngleZ = -0.3141593F;

		this.LegFrontLeft.rotateAngleY = -3.141593F;
		this.LegFrontLeftFoot.rotateAngleY = 2.932153F;
		this.LegBackLeft.rotateAngleY = -3.141593F;
		this.LegFrontLeftFoot1.rotateAngleY = 2.932153F;
		this.LegBackRight.rotateAngleY = -3.141593F;
		this.LegFrontLeftFoot2.rotateAngleY = -2.932153F;
		this.LegFrontRight.rotateAngleY = -3.141593F;
		this.LegFrontLeftFoot3.rotateAngleY = -2.932153F;

		this.Spike1.rotateAngleY = -3.141593F;
		this.Spike6.rotateAngleY = -3.141593F;
		this.Spike2.rotateAngleY = -3.141593F;
		this.Spike3.rotateAngleY = -3.141593F;
		this.Spike4.rotateAngleY = -3.141593F;
		this.Spike5.rotateAngleY = -3.141593F;
		this.Spike7.rotateAngleY = -3.141593F;
		this.Spike8.rotateAngleY = -3.141593F;
		this.Spike9.rotateAngleY = -3.141593F;
		this.Spike10.rotateAngleY = -3.141593F;
		this.Spike11.rotateAngleY = -3.141593F;
		this.Spike12.rotateAngleY = -3.141593F;
		this.Spike13.rotateAngleY = -3.141593F;
		this.Spike14.rotateAngleY = -3.141593F;
		this.Spike15.rotateAngleY = -3.141593F;
		this.Spike16.rotateAngleY = -3.141593F;
		this.Spike22.rotateAngleY = -3.141593F;
		this.Spike23.rotateAngleY = -3.141593F;
		this.Spike24.rotateAngleY = -3.141593F;
		this.Spike25.rotateAngleY = -3.141593F;
		this.Spike26.rotateAngleY = -3.141593F;
		this.Spike27.rotateAngleY = -3.141593F;
		this.Spike28.rotateAngleY = -3.141593F;
		this.Spike29.rotateAngleY = -3.141593F;
		this.Spike30.rotateAngleY = -3.141593F;
		this.Spike31.rotateAngleY = -3.141593F;
		this.Spike32.rotateAngleY = -3.141593F;
		this.Spike33.rotateAngleY = -3.141593F;
		this.Spike34.rotateAngleY = -3.141593F;
		this.Spike35.rotateAngleY = -3.141593F;
		this.Spike36.rotateAngleY = -3.141593F;
		this.Spike37.rotateAngleY = -3.141593F;
		this.Spike38.rotateAngleY = -3.141593F;
		this.Spike39.rotateAngleY = -3.141593F;
		this.Spike40.rotateAngleY = -3.141593F;
		this.Spike17.rotateAngleY = -3.141593F;
		this.Spike18.rotateAngleY = -3.141593F;
		this.Spike19.rotateAngleY = -3.141593F;
		this.Spike20.rotateAngleY = -3.141593F;
		this.Spike21.rotateAngleY = -3.141593F;
		this.Spike41.rotateAngleY = -3.141593F;
		this.Spike42.rotateAngleY = -3.141593F;
		this.Spike43.rotateAngleY = -3.141593F;
		this.Spike44.rotateAngleY = -3.141593F;
		this.Spike45.rotateAngleY = -3.141593F;
		this.Spike46.rotateAngleY = -3.141593F;
		this.Spike47.rotateAngleY = -3.141593F;
		this.Spike48.rotateAngleY = -3.141593F;
		this.Spike49.rotateAngleY = -3.141593F;
		this.Spike50.rotateAngleY = -3.141593F;

		this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;

		this.BodyNode.rotateAngleX = (float) Math.PI / 2;

		this.LegFrontLeft.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LegFrontLeftFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

		this.LegBackLeft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.LegFrontLeftFoot1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;

		this.LegBackRight.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.LegFrontLeftFoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;

		this.LegFrontRight.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LegFrontLeftFoot3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}

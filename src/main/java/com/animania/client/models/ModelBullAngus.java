package com.animania.client.models;

import org.lwjgl.opengl.GL11;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelBullAngus extends ModelBase {
	private float headRotationAngleX;
	public ModelRenderer Head = new ModelRenderer(this, 0, 0);
	ModelRenderer Body;
	ModelRenderer BodyHump;
	ModelRenderer BodyHump2;
	ModelRenderer sac;
	ModelRenderer penis;
	ModelRenderer TailTop;
	ModelRenderer Tail;
	ModelRenderer TailHair1;
	ModelRenderer TailHair2;
	ModelRenderer HeadTop;
	ModelRenderer Snout;
	ModelRenderer EarL;
	ModelRenderer EarLa;
	ModelRenderer EarR;
	ModelRenderer EarRa;
	ModelRenderer Ring;
	ModelRenderer Ring2;
	// ModelRenderer Horn1;
	// ModelRenderer Horn1A;
	// ModelRenderer Horn1B;
	// ModelRenderer Horn1C;
	// ModelRenderer Horn2;
	// ModelRenderer Horn1A1;
	// ModelRenderer Horn1B1;
	// ModelRenderer Horn1C1;
	ModelRenderer Leg0;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;

	protected float field_78145_g = 8.0F;
	protected float field_78151_h = 4.0F;

	public ModelBullAngus() {
		Body = new ModelRenderer(this, 18, 4);
		Body.setTextureSize(128, 64);
		Body.addBox(-6F, -5F, -5F, 12, 10, 10);
		Body.setRotationPoint(0F, 8F, 4F);
		BodyHump = new ModelRenderer(this, 62, 9);
		BodyHump.setTextureSize(128, 64);
		BodyHump.addBox(-7F, -5.02F, -6.5F, 14, 10, 13);
		BodyHump.setRotationPoint(0F, 8.01F, -5F);
		BodyHump2 = new ModelRenderer(this, 71, 34);
		BodyHump2.setTextureSize(128, 64);
		BodyHump2.addBox(-5.5F, -3.02F, -5F, 11, 6, 10);
		BodyHump2.setRotationPoint(0F, 7.200001F, 1F);

		sac = new ModelRenderer(this, 64, 5);
		sac.setTextureSize(128, 64);
		sac.addBox(-1.5F, -1.5F, -1F, 3, 3, 2);
		sac.setRotationPoint(0F, 14F, 6.983971F);
		penis = new ModelRenderer(this, 52, 0);
		penis.setTextureSize(128, 64);
		penis.addBox(-1F, -2.5F, -2F, 2, 5, 4);
		penis.setRotationPoint(-3.059797E-07F, 12.5F, 4.983971F);

		TailTop = new ModelRenderer(this, 32, 49);
		TailTop.setTextureSize(128, 64);
		TailTop.addBox(-1F, -0.5F, 0F, 2, 1, 1);
		TailTop.setRotationPoint(0F, 4.1F, 8.999999F);

		Tail = new ModelRenderer(this, 27, 51);
		Tail.setTextureSize(128, 64);
		Tail.addBox(-0.5F, -0.5F, -5.7F, 1, 1, 6);
		Tail.setRotationPoint(0F, 4F, 9F);

		TailHair1 = new ModelRenderer(this, 23, 52);
		TailHair1.setTextureSize(128, 64);
		TailHair1.addBox(-1F, 0F, -4F, 2, 0, 3);
		// TailHair1.setRotationPoint(0F, 8.189776F, 10.64188F);
		TailHair1.setRotationPoint(0F, -.1F, -4.64188F);

		TailHair2 = new ModelRenderer(this, 23, 52);
		TailHair2.setTextureSize(128, 64);
		TailHair2.addBox(-1F, 0F, -4F, 2, 0, 3);
		TailHair2.setRotationPoint(2.010928E-07F, -.1F, -4.64188F);
		// TailHair2.setRotationPoint(2.010928E-07F, 8.189775F, 10.64188F);

		Leg0 = new ModelRenderer(this, 0, 32);
		Leg0.setTextureSize(128, 64);
		Leg0.addBox(-2F, 0F, -2F, 4, 12, 4);
		Leg0.setRotationPoint(-4F, 13F, -7F);
		Leg1 = new ModelRenderer(this, 24, 32);
		Leg1.setTextureSize(128, 64);
		Leg1.addBox(-2F, 0F, -2F, 4, 12, 4);
		Leg1.setRotationPoint(4F, 13F, -7F);
		Leg2 = new ModelRenderer(this, 40, 32);
		Leg2.setTextureSize(128, 64);
		Leg2.addBox(-2F, 0F, -2F, 4, 12, 4);
		Leg2.setRotationPoint(4F, 13F, 7F);
		Leg3 = new ModelRenderer(this, 0, 16);
		Leg3.setTextureSize(128, 64);
		Leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
		Leg3.setRotationPoint(-4F, 13F, 7F);

		Head = new ModelRenderer(this, 0, 0);
		Head.setTextureSize(128, 64);
		Head.addBox(-4F, -4F, -3F, 8, 8, 6);
		Head.setRotationPoint(0F, 5F, -13F); // was -14.5F

		Snout = new ModelRenderer(this, 49, 50);
		Snout.setTextureSize(128, 64);
		Snout.addBox(-2F, -2F, -1.5F, 4, 4, 3);
		Snout.setRotationPoint(0F, 3F, -2.5F); // Snout.setRotationPoint( 0F,
												// 8F, -15.5F );

		EarL = new ModelRenderer(this, 39, 53);
		EarL.setTextureSize(128, 64);
		EarL.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		EarL.setRotationPoint(-5.5F, -1.5F, 1F);

		EarLa = new ModelRenderer(this, 45, 50);
		EarLa.setTextureSize(128, 64);
		EarLa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EarLa.setRotationPoint(-4.5F, -.04F, 0.69718F); // EarLa.setRotationPoint(
														// -4.5F, 4.969114F,
														// -13.69718F );

		EarR = new ModelRenderer(this, 39, 53);
		EarR.setTextureSize(128, 64);
		EarR.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		EarR.setRotationPoint(5.5F, -1.5F, 1F); // EarR.setRotationPoint( 5.5F,
												// 3.5F, -14F );

		EarRa = new ModelRenderer(this, 41, 50);
		EarRa.setTextureSize(128, 64);
		EarRa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EarRa.setRotationPoint(4.5F, -.04F, 0.69718F); // EarRa.setRotationPoint(
														// 4.5F, 4.969114F,
														// -13.69718F );

		/*
		 * Ring = new ModelRenderer(this, 65, 53); Ring.setTextureSize(128, 64);
		 * Ring.addBox(-2F, -2F, -1F, 4, 4, 0); Ring.setRotationPoint(0.0F, 3F,
		 * -4.2F); // Ring.setRotationPoint( 0F, 8F, -17F );
		 * 
		 * Ring2 = new ModelRenderer(this, 65, 53); Ring2.setTextureSize(128,
		 * 64); Ring2.addBox(-2F, -2F, -0.2F, 4, 4, 0);
		 * Ring2.setRotationPoint(0.0F, 3F, -4.2F); // Ring.setRotationPoint(
		 * 0F, 8F, -17F );
		 */

		HeadTop = new ModelRenderer(this, 3, 3); // ALL NEW
		HeadTop.setTextureSize(128, 64);
		HeadTop.addBox(-3F, -1.5F, -1.5F, 6, 3, 3);
		HeadTop.setRotationPoint(0F, -4F, 0F); // was -13

		/*
		 * Horn1 = new ModelRenderer( this, 22, 0 ); Horn1.setTextureSize( 128,
		 * 64 ); Horn1.addBox( -2.5F, -1F, -1F, 5, 2, 2);
		 * Horn1.setRotationPoint( 4.5F, -4F, -0F ); //Left Horn
		 * 
		 * Horn1A = new ModelRenderer( this, 22, 0 ); Horn1A.setTextureSize(
		 * 128, 64 ); Horn1A.addBox( -0.5F, -1F, -1F, 3, 2, 2);
		 * Horn1A.setRotationPoint( 6.4F, -4F, -0F ); //Left Horn
		 * 
		 * Horn1B = new ModelRenderer( this, 46, 0 ); Horn1B.setTextureSize(
		 * 128, 64 ); Horn1B.addBox( -0.5F, -1F, -1F, 1, 2, 2);
		 * Horn1B.setRotationPoint( 7.536851F, -3.71F, -2.0F ); //Left Horn
		 * 
		 * Horn1C = new ModelRenderer( this, 52, 1 ); Horn1C.setTextureSize(
		 * 128, 64 ); Horn1C.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		 * Horn1C.setRotationPoint( 7.121497F, -5.6769695F, -3.15219F ); //Left
		 * Horn
		 * 
		 * Horn2 = new ModelRenderer( this, 22, 0 ); Horn2.setTextureSize( 128,
		 * 64 ); Horn2.addBox( -2.5F, -1F, -1F, 5, 2, 2);
		 * Horn2.setRotationPoint( -4.5F, -4F, 0F ); //Right Horn
		 * 
		 * Horn1A1 = new ModelRenderer( this, 36, 0 ); Horn1A1.setTextureSize(
		 * 128, 64 ); Horn1A1.addBox( -0.5F, -1F, -1F, 3, 2, 2);
		 * Horn1A1.setRotationPoint( -8.4F, -4F, 0F ); //Right Horn was -6.4
		 * 
		 * Horn1B1 = new ModelRenderer( this, 46, 0 ); Horn1B1.setTextureSize(
		 * 128, 64 ); Horn1B1.addBox( -0.5F, -1F, -1F, 1, 2, 2);
		 * Horn1B1.setRotationPoint( -7.406949F, -4.71F, -2.0F ); //Right Horn
		 * -2.24409F
		 * 
		 * Horn1C1 = new ModelRenderer( this, 52, 1 ); Horn1C1.setTextureSize(
		 * 128, 64 ); Horn1C1.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		 * Horn1C1.setRotationPoint( -7.079102F, -5.7030411F, -3.22298F );
		 * //Right Horn
		 */

		this.Head.addChild(this.Snout);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarLa);
		this.Head.addChild(this.EarR);
		this.Head.addChild(this.EarRa);
		// this.Head.addChild(this.Ring);
		this.Head.addChild(this.HeadTop);

		/*
		 * this.Head.addChild(this.Horn1); this.Head.addChild(this.Horn1A);
		 * this.Head.addChild(this.Horn1B); this.Head.addChild(this.Horn1C);
		 * this.Head.addChild(this.Horn2); this.Head.addChild(this.Horn1A1);
		 * this.Head.addChild(this.Horn1B1); this.Head.addChild(this.Horn1C1);
		 */

		this.Tail.addChild(this.TailHair1);
		this.Tail.addChild(this.TailHair2);

	}

	@Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_,
			float p_78088_6_, float p_78088_7_) {

		// Body
		Body.rotateAngleX = 1.570796F;
		BodyHump.rotateAngleX = 1.570796F;
		BodyHump2.rotateAngleX = 1.308997F;
		sac.rotateAngleX = -3.821371E-15F;
		sac.rotateAngleY = -3.141593F;
		sac.rotateAngleZ = -3.141593F;
		penis.rotateAngleX = 1.457546F;
		penis.rotateAngleY = 3.141593F;
		penis.rotateAngleZ = 3.141593F;

		EarL.rotateAngleX = 0.2032792F;
		EarLa.rotateAngleX = 0.2032792F;
		EarR.rotateAngleX = 0.2032792F;
		EarRa.rotateAngleX = 0.2032792F;

		// Tail
		Tail.rotateAngleX = -2F;
		Tail.rotateAngleY = -3.141593F;
		Tail.rotateAngleZ = -3.141593F;
		TailHair1.rotateAngleZ = -2.280276F;
		TailHair2.rotateAngleZ = 2.432113F;

		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

		if (this.isChild) {
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, this.field_78145_g * p_78088_7_, this.field_78151_h * p_78088_7_);
			this.Head.render(p_78088_7_);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
			this.Body.render(p_78088_7_);
			this.Leg0.render(p_78088_7_);
			this.Leg1.render(p_78088_7_);
			this.Leg2.render(p_78088_7_);
			this.Leg3.render(p_78088_7_);
			GL11.glPopMatrix();
			// TODO add other child stuff
		} else {
			this.Head.render(p_78088_7_);
			this.Body.render(p_78088_7_);
			this.BodyHump.render(p_78088_7_);
			this.BodyHump2.render(p_78088_7_);
			if (AnimaniaConfig.gameRules.showParts) {
				this.sac.render(p_78088_7_);
				this.penis.render(p_78088_7_);
			}
			this.Leg0.render(p_78088_7_);
			this.Leg1.render(p_78088_7_);
			this.Leg2.render(p_78088_7_);
			this.Leg3.render(p_78088_7_);

			this.TailTop.render(p_78088_7_);
			this.Tail.render(p_78088_7_);

		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_,
			float partialTickTime) {
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityBullHolstein) {
			this.Head.rotationPointY = 6.0F
					+ ((EntityBullHolstein) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityBullHolstein) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityBullAngus) {
			this.Head.rotationPointY = 6.0F
					+ ((EntityBullAngus) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityBullAngus) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityBullFriesian) {
			this.Head.rotationPointY = 6.0F
					+ ((EntityBullFriesian) entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityBullFriesian) entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7) {
		float f6 = (180F / (float) Math.PI);

		this.Body.rotateAngleX = ((float) Math.PI / 2F);

		if (par7 instanceof EntityBullAngus) {

			EntityBullAngus eb = (EntityBullAngus) par7;
			if (eb.getFighting()) {
				this.Head.rotationPointY = 14.0F + ((EntityBullAngus) par7).getHeadRotationPointY(1) * 9.0F;
				this.headRotationAngleX = ((EntityBullAngus) par7).getHeadRotationAngleX(-3);
			} else {
				this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
				this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
				this.Head.rotateAngleX = this.headRotationAngleX;
			}

		} else {
			this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = this.headRotationAngleX;
		}

		this.TailTop.rotateAngleX = ((float) Math.PI / 2F);
		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F)
				* MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.Leg0.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}

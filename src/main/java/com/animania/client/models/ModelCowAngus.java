package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHolstein;


@SideOnly(Side.CLIENT)
public class ModelCowAngus extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer head = new ModelRenderer(this, 0, 0);
	ModelRenderer body;
	ModelRenderer udder;
	ModelRenderer udderA;
	ModelRenderer udderC;
	ModelRenderer udderB;
	ModelRenderer udderD;
	//ModelRenderer Horn1;
	//ModelRenderer Horn2;
	ModelRenderer Snout;
	ModelRenderer EarL;
	ModelRenderer EarLa;
	ModelRenderer EarR;
	ModelRenderer EarRa;
	ModelRenderer TailTop;
	ModelRenderer Tail;
	ModelRenderer TailHair1;
	ModelRenderer TailHair2;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	protected float field_78145_g = 8.0F;
	protected float field_78151_h = 4.0F;

	public ModelCowAngus()
	{
		head.setTextureSize(64, 64);
		head.addBox(-4F, -4F, -3F, 8, 8, 6);
		head.setRotationPoint(0F, 5F, -12F);

		body = new ModelRenderer(this, 18, 4);
		body.setTextureSize(64, 64);
		body.addBox(-6F, -9F, -5F, 12, 18, 10);
		body.setRotationPoint(0F, 8F, 0F);

		udder = new ModelRenderer(this, 52, 0);
		udder.setTextureSize(64, 64);
		udder.addBox(-2F, -3F, -1F, 4, 6, 2);
		udder.setRotationPoint(0F, 14F, 5.5F);
		udderA = new ModelRenderer(this, 54, 9);
		udderA.setTextureSize(64, 64);
		udderA.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		udderA.setRotationPoint(-1.5F, 15F, 4F);
		udderC = new ModelRenderer(this, 54, 9);
		udderC.setTextureSize(64, 64);
		udderC.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		udderC.setRotationPoint(1.5F, 15F, 7F);
		udderB = new ModelRenderer(this, 54, 9);
		udderB.setTextureSize(64, 64);
		udderB.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		udderB.setRotationPoint(-1.5F, 15F, 7F);
		udderD = new ModelRenderer(this, 54, 9);
		udderD.setTextureSize(64, 64);
		udderD.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		udderD.setRotationPoint(1.5F, 15F, 4F);

		leg1 = new ModelRenderer(this, 0, 32);
		leg1.setTextureSize(64, 64);
		leg1.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg1.setRotationPoint(-4F, 13F, -7F);
		leg2 = new ModelRenderer(this, 24, 32);
		leg2.setTextureSize(64, 64);
		leg2.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg2.setRotationPoint(4F, 13F, -7F);
		leg3 = new ModelRenderer(this, 40, 32);
		leg3.setTextureSize(64, 64);
		leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg3.setRotationPoint(4F, 13F, 7F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.setTextureSize(64, 64);
		leg4.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg4.setRotationPoint(-4F, 13F, 7F);

		/*
		Horn1 = new ModelRenderer(this, 22, 0);
		Horn1.setTextureSize(64, 64);
		Horn1.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
		Horn1.setRotationPoint(4.5F, -3.5F, -0.5F);
		Horn2 = new ModelRenderer(this, 22, 0);
		Horn2.setTextureSize(64, 64);
		Horn2.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
		Horn2.setRotationPoint(-4.5F, -3.5F, -0.5F);
		*/
		
		Snout = new ModelRenderer(this, 49, 50);
		Snout.setTextureSize(64, 64);
		Snout.addBox(-2F, -2F, -1.5F, 4, 4, 3);
		Snout.setRotationPoint(0F, 3F, -2.5F);
		EarL = new ModelRenderer(this, 39, 53);
		EarL.setTextureSize(64, 64);
		EarL.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		EarL.setRotationPoint(-5.5F, -1.5F, 1F);
		EarLa = new ModelRenderer(this, 45, 50);
		EarLa.setTextureSize(64, 64);
		EarLa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		EarLa.setRotationPoint(-4.5F, -.04F, 0.69718F);

		EarR = new ModelRenderer(this, 39, 53);
		EarR.setTextureSize(64, 64);
		EarR.addBox(-1.5F, -1F, -0.5F, 3, 2, 1);
		//EarR.setRotationPoint(5.5F, 3.5F, -13F);
		//head.setRotationPoint(0F, 5F, -12F);
		EarR.setRotationPoint(5.5F, -1.5F, 1F);
		EarRa = new ModelRenderer(this, 41, 50);
		EarRa.setTextureSize(64, 64);
		EarRa.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		//head.setRotationPoint(0F, 5F, -12F);
		//EarRa.setRotationPoint(4.5F, 4.969114F, -12.69718F);
		EarRa.setRotationPoint(4.5F, -.04F, 0.69718F);

		TailTop = new ModelRenderer(this, 32, 49);
		TailTop.setTextureSize(64, 64);
		TailTop.addBox(-1F, -0.5F, 0F, 2, 1, 1);
		TailTop.setRotationPoint(0F, 4.0F, 9F);
		//TailTop.setRotationPoint(0F, 0F, 0F);
		Tail = new ModelRenderer(this, 27, 51);
		Tail.setTextureSize(64, 64);
		Tail.addBox(-0.5F, -1.0F, -5.7F, 1, 1, 6);
		Tail.setRotationPoint(0F, 4F, 9F);

		TailHair1 = new ModelRenderer(this, 23, 52);
		TailHair1.setTextureSize(64, 64);
		TailHair1.addBox(-1F, 0.0F, -4F, 2, 0, 3);
		TailHair1.setRotationPoint(0F, -.5F, -4.64188F);
		//Tail.setRotationPoint(0F, 4F, 9F);
		//TailHair1.setRotationPoint(0F, 8.189776F, 10.64188F);

		TailHair2 = new ModelRenderer(this, 23, 52);
		TailHair2.setTextureSize(64, 64);
		TailHair2.addBox(-1F, 0.0F, -4F, 2, 0, 3);
		TailHair2.setRotationPoint(2.010928E-07F, -.5F, -4.64188F);
		//TailHair2.setRotationPoint(2.010928E-07F, 8.189775F, 10.64188F);
		//TailHair2.setRotationPoint(2.010928E-07F, 4.189775F, 1.64188F);

		//this.head.addChild(this.Horn1);
		//this.head.addChild(this.Horn2);
		this.head.addChild(this.Snout);
		this.head.addChild(this.EarL);
		this.head.addChild(this.EarLa);
		this.head.addChild(this.EarR);
		this.head.addChild(this.EarRa);

		//this.Tail.addChild(this.TailTop);
		this.Tail.addChild(this.TailHair1);
		this.Tail.addChild(this.TailHair2);

	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
	{

		Tail.rotateAngleX =  -2F;
		Tail.rotateAngleY = -3.141593F;
		Tail.rotateAngleZ = -3.141593F;
		TailHair1.rotateAngleZ = -2.280276F;
		TailHair2.rotateAngleZ = 2.432113F;

		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

		if (this.isChild)
		{
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, this.field_78145_g * p_78088_7_, this.field_78151_h * p_78088_7_);
			this.head.render(p_78088_7_);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
			this.body.render(p_78088_7_);
			this.leg1.render(p_78088_7_);
			this.leg2.render(p_78088_7_);
			this.leg3.render(p_78088_7_);
			this.leg4.render(p_78088_7_);
			GL11.glPopMatrix();
			//TODO add other child stuff
		}
		else
		{
			this.body.render(p_78088_7_);
			this.head.render(p_78088_7_);
			this.leg1.render(p_78088_7_);
			this.leg2.render(p_78088_7_);
			this.leg3.render(p_78088_7_);
			this.leg4.render(p_78088_7_);
			this.udder.render(p_78088_7_);
			this.udderA.render(p_78088_7_);
			this.udderC.render(p_78088_7_);
			this.udderD.render(p_78088_7_);
			this.udderB.render(p_78088_7_);

			this.TailTop.render(p_78088_7_);
			this.Tail.render(p_78088_7_);

		}
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityCowHolstein) {
			this.head.rotationPointY = 6.0F + ((EntityCowHolstein)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityCowHolstein)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityCowAngus) {
			this.head.rotationPointY = 6.0F + ((EntityCowAngus)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityCowAngus)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityCowFriesian) {
			this.head.rotationPointY = 6.0F + ((EntityCowFriesian)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
			this.headRotationAngleX = ((EntityCowFriesian)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} 

	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = this.headRotationAngleX;
		this.body.rotateAngleX = ((float)Math.PI / 2F);

		this.udder.rotateAngleX = ((float)Math.PI / 2F);
		this.udderA.rotateAngleX = ((float)Math.PI / 2F);
		this.udderC.rotateAngleX = ((float)Math.PI / 2F);
		this.udderD.rotateAngleX = ((float)Math.PI / 2F);
		this.udderB.rotateAngleX = ((float)Math.PI / 2F);

		this.TailTop.rotateAngleX = ((float)Math.PI / 2F);
		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}






package com.animania.addons.catsdogs.client.models.cats;

import com.animania.addons.catsdogs.common.entity.cats.EntityAnimaniaCat;
import com.animania.addons.catsdogs.common.entity.cats.EntityTomRagdoll;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelRagdoll extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer Neck1 = new ModelRenderer(this, 0, 0);

	ModelRenderer body;
	ModelRenderer lower_body;
	ModelRenderer tail;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer back_leg_r1;
	ModelRenderer back_leg_r2;
	ModelRenderer back_leg_l1;
	ModelRenderer back_leg_l2;
	ModelRenderer neck1;
	ModelRenderer head_base;
	ModelRenderer head_front;
	ModelRenderer head_slope;
	ModelRenderer nose;
	ModelRenderer jaw;
	ModelRenderer cheek_l;
	ModelRenderer cheek_l2;
	ModelRenderer cheek_r;
	ModelRenderer cheek_r2;
	ModelRenderer ear_r;
	ModelRenderer ear_r2;
	ModelRenderer ear_r3;
	ModelRenderer ear_l;
	ModelRenderer ear_l2;
	ModelRenderer ear_l3;
	ModelRenderer mane;
	ModelRenderer leg_r1;
	ModelRenderer leg_r2;
	ModelRenderer leg_l1;
	ModelRenderer leg_l2;

	public ModelRagdoll()
	{
		this.body = new ModelRenderer(this, 38, 42);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.5F, -4.0F, -5.5F, 9, 10, 11);
		this.body.setRotationPoint(0.0F, -11.0F, -4.0F);
		this.lower_body = new ModelRenderer(this, 80, 41);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-4.0F, -4.0F, -0.5F, 8, 9, 13);
		this.lower_body.setRotationPoint(0.0F, -0.5F, 10.0F);
		this.tail = new ModelRenderer(this, 44, 16);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -2.0F, 0.5F, 3, 4, 9);
		this.tail.setRotationPoint(0.0F, -3.0F, 10.0F);
		this.tail2 = new ModelRenderer(this, 71, 27);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.5F, -2.0F, 0.5F, 3, 4, 5);
		this.tail2.setRotationPoint(0.0F, -0.0F, 6.0F);
		this.tail3 = new ModelRenderer(this, 62, 6);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-2.0F, -2.5F, -1.0F, 4, 5, 6);
		this.tail3.setRotationPoint(0.0F, -0.0F, 5.0F);
		this.tail4 = new ModelRenderer(this, 57, 5);
		this.tail4.setTextureSize(128, 64);
		this.tail4.addBox(-1.5F, -1.0F, -0.0F, 3, 4, 2);
		this.tail4.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.back_leg_r1 = new ModelRenderer(this, 19, 51);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, 0.5F, -2.0F, 2, 8, 5);
		this.back_leg_r1.setRotationPoint(-4.0F, 0.0F, 3.5F);
		this.back_leg_r2 = new ModelRenderer(this, 18, 35);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.01F, -2.5F, -0.8F, 2, 12, 3);
		this.back_leg_r2.setRotationPoint(0.01F, 6.0F, 1.7F);
		this.back_leg_l1 = new ModelRenderer(this, 19, 51);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, 0.5F, -2.0F, 2, 8, 5);
		this.back_leg_l1.setRotationPoint(4.0F, 0.0F, 3.5F);
		this.back_leg_l2 = new ModelRenderer(this, 18, 35);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-0.99F, -2.5F, -0.8F, 2, 12, 3);
		this.back_leg_l2.setRotationPoint(-0.01F, 6.0F, 1.7F);
		this.neck1 = new ModelRenderer(this, 11, 16);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -2.0F, -7.0F, 5, 6, 10);
		this.neck1.setRotationPoint(0.0F, -2.0F, -7.0F);
		this.head_base = new ModelRenderer(this, 102, 4);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.0F, -4.0F, -5.5F, 6, 6, 5);
		this.head_base.setRotationPoint(0.0F, -2.0F, -4.7F);
		this.head_front = new ModelRenderer(this, 0, 12);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-2.0F, -2.0F, -5.4F, 4, 2, 5);
		this.head_front.setRotationPoint(0.0F, 0.0F, -1.4F);
		this.head_slope = new ModelRenderer(this, 37, 11);
		this.head_slope.setTextureSize(128, 64);
		this.head_slope.addBox(-1.0F, -2.0F, -5.4F, 2, 2, 5);
		this.head_slope.setRotationPoint(0.0F, -2.0F, 0.10000000000000009F);
		this.nose = new ModelRenderer(this, 10, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, 0.09999999999999998F, -1.3F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -0.09999999999999998F, -2.3F);
		this.jaw = new ModelRenderer(this, 0, 20);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.5F, -0.7F, -3.0F, 3, 1, 4);
		this.jaw.setRotationPoint(0.0F, 2.1999999999999997F, -1.5F);
		this.cheek_l = new ModelRenderer(this, 32, 41);
		this.cheek_l.setTextureSize(128, 64);
		this.cheek_l.addBox(-3.5F, -3.1F, -2.5F, 3, 5, 3);
		this.cheek_l.setRotationPoint(3.4F, 0.4F, 1.0F);
		this.cheek_l2 = new ModelRenderer(this, 32, 36);
		this.cheek_l2.setTextureSize(128, 64);
		this.cheek_l2.addBox(-3.5F, -2.1F, -2.0F, 3, 3, 2);
		this.cheek_l2.setRotationPoint(1.0F, 0.0F, 0.5F);
		this.cheek_r = new ModelRenderer(this, 32, 41);
		this.cheek_r.setTextureSize(128, 64);
		this.cheek_r.addBox(0.5F, -3.1F, -2.5F, 3, 5, 3);
		this.cheek_r.setRotationPoint(-3.4F, 0.4F, 1.0F);
		this.cheek_r2 = new ModelRenderer(this, 32, 36);
		this.cheek_r2.setTextureSize(128, 64);
		this.cheek_r2.addBox(0.5F, -2.1F, -2.0F, 3, 3, 2);
		this.cheek_r2.setRotationPoint(-1.0F, 0.0F, 0.5F);
		this.ear_r = new ModelRenderer(this, 3, 3);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.0F, -1.1F, -2.5F, 3, 1, 4);
		this.ear_r.setRotationPoint(-3.0F, -3.1F, 0.20009999999999994F);
		this.ear_r2 = new ModelRenderer(this, 0, 0);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -1.1F, -1.0F, 2, 1, 1);
		this.ear_r2.setRotationPoint(0.0F, 0.0F, -2.2F);
		this.ear_r3 = new ModelRenderer(this, 1, 0);
		this.ear_r3.setTextureSize(128, 64);
		this.ear_r3.addBox(-0.5F, -1.1F, -1.0F, 1, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, 0.0F, -0.8F);
		this.ear_l = new ModelRenderer(this, 3, 3);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-2.0F, -1.1F, -2.5F, 3, 1, 4);
		this.ear_l.setRotationPoint(3.0F, -3.1F, 0.20009999999999994F);
		this.ear_l2 = new ModelRenderer(this, 0, 0);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -1.1F, -1.0F, 2, 1, 1);
		this.ear_l2.setRotationPoint(0.0F, 0.0F, -2.2F);
		this.ear_l3 = new ModelRenderer(this, 1, 0);
		this.ear_l3.setTextureSize(128, 64);
		this.ear_l3.addBox(-0.5F, -1.1F, -1.0F, 1, 1, 1);
		this.ear_l3.setRotationPoint(0.0F, 0.0F, -0.8F);
		this.mane = new ModelRenderer(this, 96, 22);
		this.mane.setTextureSize(128, 64);
		this.mane.addBox(-5.0F, -2.2F, -5.0F, 10, 8, 6);
		this.mane.setRotationPoint(0.0F, -0.7F, 1.0F);
		this.leg_r1 = new ModelRenderer(this, 2, 37);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -0.5F, -2.0F, 2, 9, 4);
		this.leg_r1.setRotationPoint(-4.2F, -1.0F, -2.0F);
		this.leg_r2 = new ModelRenderer(this, 1, 52);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.12F, -0.5F, -2.1F, 2, 9, 3);
		this.leg_r2.setRotationPoint(0.12F, 8.0F, -0.39999999999999997F);
		this.leg_l1 = new ModelRenderer(this, 2, 37);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -0.5F, -2.0F, 2, 9, 4);
		this.leg_l1.setRotationPoint(4.2F, -1.0F, -2.0F);
		this.leg_l2 = new ModelRenderer(this, 1, 52);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-0.88F, -0.5F, -2.1F, 2, 9, 3);
		this.leg_l2.setRotationPoint(-0.12F, 8.0F, -0.39999999999999997F);
		this.tail3.addChild(this.tail4);
		this.tail2.addChild(this.tail3);
		this.tail.addChild(this.tail2);
		this.lower_body.addChild(this.tail);
		this.back_leg_r1.addChild(this.back_leg_r2);
		this.lower_body.addChild(this.back_leg_r1);
		this.back_leg_l1.addChild(this.back_leg_l2);
		this.lower_body.addChild(this.back_leg_l1);
		this.body.addChild(this.lower_body);
		this.head_slope.addChild(this.nose);
		this.head_front.addChild(this.head_slope);
		this.head_base.addChild(this.head_front);
		this.head_base.addChild(this.jaw);
		this.cheek_l.addChild(this.cheek_l2);
		this.head_base.addChild(this.cheek_l);
		this.cheek_r.addChild(this.cheek_r2);
		this.head_base.addChild(this.cheek_r);
		this.ear_r2.addChild(this.ear_r3);
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.ear_l2.addChild(this.ear_l3);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.neck1.addChild(this.head_base);
		this.neck1.addChild(this.mane);
		this.body.addChild(this.neck1);
		this.leg_r1.addChild(this.leg_r2);
		this.body.addChild(this.leg_r1);
		this.leg_l1.addChild(this.leg_l2);
		this.body.addChild(this.leg_l1);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.body.rotateAngleX = 0.03490658503988659F;
		this.body.rotateAngleY = -0.0F;
		this.body.rotateAngleZ = -0.0F;
		this.lower_body.rotateAngleX = -0.04414810342919657F;
		this.lower_body.rotateAngleY = -0.0F;
		this.lower_body.rotateAngleZ = -0.0F;
		this.tail.rotateAngleX = -0.9733229999474338F;
		this.tail.rotateAngleY = -0.0F;
		this.tail.rotateAngleZ = -0.0F;
		this.tail2.rotateAngleX = 0.40470694695244513F;
		this.tail2.rotateAngleY = -0.0F;
		this.tail2.rotateAngleZ = -0.0F;
		this.tail3.rotateAngleX = 0.43627646246251855F;
		this.tail3.rotateAngleY = -0.0F;
		this.tail3.rotateAngleZ = -0.0F;
		this.tail4.rotateAngleX = 0.0F;
		this.tail4.rotateAngleY = -0.0F;
		this.tail4.rotateAngleZ = -0.0F;
		this.back_leg_r1.rotateAngleX = -1.7453292519943296E-6F;
		this.back_leg_r1.rotateAngleY = -0.0F;
		this.back_leg_r1.rotateAngleZ = -0.0F;
		this.back_leg_r2.rotateAngleX = 0.0F;
		this.back_leg_r2.rotateAngleY = -0.0F;
		this.back_leg_r2.rotateAngleZ = -0.0F;
		this.back_leg_l1.rotateAngleX = -1.7453292519943296E-6F;
		this.back_leg_l1.rotateAngleY = -0.0F;
		this.back_leg_l1.rotateAngleZ = -0.0F;
		this.back_leg_l2.rotateAngleX = 0.0F;
		this.back_leg_l2.rotateAngleY = -0.0F;
		this.back_leg_l2.rotateAngleZ = -0.0F;
		this.neck1.rotateAngleX = -0.8681459685637515F;
		this.neck1.rotateAngleY = -0.0F;
		this.neck1.rotateAngleZ = -0.0F;
		this.head_base.rotateAngleX = 1.0383626945230024F;
		this.head_base.rotateAngleY = -0.0F;
		this.head_base.rotateAngleZ = -0.0F;
		this.head_front.rotateAngleX = 0.15707788735023767F;
		this.head_front.rotateAngleY = -0.0F;
		this.head_front.rotateAngleZ = -0.0F;
		this.head_slope.rotateAngleX = 0.35123180400059084F;
		this.head_slope.rotateAngleY = -0.0F;
		this.head_slope.rotateAngleZ = -0.0F;
		this.nose.rotateAngleX = -0.17677218262974168F;
		this.nose.rotateAngleY = -0.0F;
		this.nose.rotateAngleZ = -0.0F;
		this.jaw.rotateAngleX = -0.009829694347232064F;
		this.jaw.rotateAngleY = -0.0F;
		this.jaw.rotateAngleZ = -0.0F;
		this.cheek_l.rotateAngleX = 0.0464484473833251F;
		this.cheek_l.rotateAngleY = 0.17291151432433022F;
		this.cheek_l.rotateAngleZ = -0.08372868020592397F;
		this.cheek_l2.rotateAngleX = 0.0F;
		this.cheek_l2.rotateAngleY = 0.24005433064855206F;
		this.cheek_l2.rotateAngleZ = 1.7453292519943296E-6F;
		this.cheek_r.rotateAngleX = 0.0464484473833251F;
		this.cheek_r.rotateAngleY = -0.17290976899507823F;
		this.cheek_r.rotateAngleZ = 0.08373042553517596F;
		this.cheek_r2.rotateAngleX = 0.0F;
		this.cheek_r2.rotateAngleY = -0.24005258531930007F;
		this.cheek_r2.rotateAngleZ = -0.0F;
		this.ear_r.rotateAngleX = -1.0327043370880369F;
		this.ear_r.rotateAngleY = 1.4596223441013618F;
		this.ear_r.rotateAngleZ = -1.433051451569F;
		this.ear_r2.rotateAngleX = 0.15050148672872304F;
		this.ear_r2.rotateAngleY = -0.0F;
		this.ear_r2.rotateAngleZ = -0.0F;
		this.ear_r3.rotateAngleX = 0.1898272454346593F;
		this.ear_r3.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_r3.rotateAngleZ = -0.0F;
		this.ear_l.rotateAngleX = -1.0327043370880369F;
		this.ear_l.rotateAngleY = -1.4596205987721098F;
		this.ear_l.rotateAngleZ = 1.433053196898252F;
		this.ear_l2.rotateAngleX = 0.15050148672872304F;
		this.ear_l2.rotateAngleY = -0.0F;
		this.ear_l2.rotateAngleZ = -0.0F;
		this.ear_l3.rotateAngleX = 0.1898272454346593F;
		this.ear_l3.rotateAngleY = -0.0F;
		this.ear_l3.rotateAngleZ = 1.7453292519943296E-6F;
		this.mane.rotateAngleX = 0.16029278383241125F;
		this.mane.rotateAngleY = -0.0F;
		this.mane.rotateAngleZ = -0.0F;
		this.leg_r1.rotateAngleX = -0.03490658503988659F;
		this.leg_r1.rotateAngleY = -0.0F;
		this.leg_r1.rotateAngleZ = -0.0F;
		this.leg_r2.rotateAngleX = 0.0F;
		this.leg_r2.rotateAngleY = -0.0F;
		this.leg_r2.rotateAngleZ = -0.0F;
		this.leg_l1.rotateAngleX = -0.03490658503988659F;
		this.leg_l1.rotateAngleY = -0.0F;
		this.leg_l1.rotateAngleZ = -0.0F;
		this.leg_l2.rotateAngleX = 0.0F;
		this.leg_l2.rotateAngleY = -0.0F;
		this.leg_l2.rotateAngleZ = -0.0F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entity, p_78086_2_, p_78086_3_, partialTickTime);

		/*
		 * if (entity instanceof EntityTomRagdoll) { EntityTomRagdoll eb =
		 * (EntityTomRagdoll) entity;
		 * 
		 * this.Neck1.rotationPointY = + 7.75F +
		 * eb.getHeadRotationPointY(partialTickTime) * 6.0F;
		 * this.headRotationAngleX = eb.getHeadRotationAngleX(partialTickTime);
		 * }
		 */

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{

		boolean isSleeping = false;

		EntityAnimaniaCat ech = (EntityAnimaniaCat) entity;
		if (ech.getSleeping())
		{
			isSleeping = true;
		}

		if (!isSleeping)
		{
			this.tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		this.back_leg_l1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.back_leg_r1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.leg_l1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.leg_r1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

	}

}

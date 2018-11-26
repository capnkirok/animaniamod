package com.animania.addons.catsdogs.client.models.cats;

import com.animania.addons.catsdogs.common.entity.cats.EntityAnimaniaCat;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelCatRagdoll extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania tail3;
	ModelRendererAnimania tail4;
	ModelRendererAnimania back_leg_r1;
	ModelRendererAnimania back_leg_r2;
	ModelRendererAnimania back_leg_l1;
	ModelRendererAnimania back_leg_l2;
	ModelRendererAnimania neck1;
	ModelRendererAnimania head_base;
	ModelRendererAnimania head_front;
	ModelRendererAnimania head_slope;
	ModelRendererAnimania nose;
	ModelRendererAnimania jaw;
	ModelRendererAnimania cheek_l;
	ModelRendererAnimania cheek_l2;
	ModelRendererAnimania cheek_r;
	ModelRendererAnimania cheek_r2;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;
	ModelRendererAnimania mane;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l2;

	public ModelCatRagdoll()
	{
		this.body = new ModelRendererAnimania(this, 38, 42);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.5F, -5.0F, -5.5F, 9, 10, 11);
		this.body.setRotationPoint(0.0F, 12.0F, -4.0F);
		this.body.setOffset(0.0F, 1.0F, -0.0F);
		this.lower_body = new ModelRendererAnimania(this, 80, 41);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-4.0F, -4.5F, -6.5F, 8, 9, 13);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 4.0F);
		this.lower_body.setOffset(0.0F, 0.5F, 6.0F);
		this.tail = new ModelRendererAnimania(this, 44, 16);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -2.0F, -4.5F, 3, 4, 9);
		this.tail.setRotationPoint(0.0F, -3.0F, 5.0F);
		this.tail.setOffset(0.0F, -0.0F, 5.0F);
		this.tail2 = new ModelRendererAnimania(this, 71, 27);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.5F, -2.0F, -2.5F, 3, 4, 5);
		this.tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail2.setOffset(0.0F, -0.0F, 3.0F);
		this.tail3 = new ModelRendererAnimania(this, 62, 6);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-2.0F, -2.5F, -3.0F, 4, 5, 6);
		this.tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail3.setOffset(0.0F, -0.0F, 2.0F);
		this.tail4 = new ModelRendererAnimania(this, 57, 5);
		this.tail4.setTextureSize(128, 64);
		this.tail4.addBox(-1.5F, -2.0F, -1.0F, 3, 4, 2);
		this.tail4.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.tail4.setOffset(0.0F, 1.0F, 1.0F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 19, 51);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_r1.setRotationPoint(-4.0F, -4.5F, 3.0F);
		this.back_leg_r1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 18, 35);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -6.0F, -1.5F, 2, 12, 3);
		this.back_leg_r2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_r2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 19, 51);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_l1.setRotationPoint(4.0F, -4.5F, 3.0F);
		this.back_leg_l1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 18, 35);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -6.0F, -1.5F, 2, 12, 3);
		this.back_leg_l2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_l2.setOffset(-0.01F, 3.5F, 0.7F);
		this.neck1 = new ModelRendererAnimania(this, 11, 16);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -3.0F, -5.0F, 5, 6, 10);
		this.neck1.setRotationPoint(0.0F, -3.0F, -5.0F);
		this.neck1.setOffset(0.0F, 1.0F, -2.0F);
		this.head_base = new ModelRendererAnimania(this, 102, 4);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.0F, -3.0F, -2.5F, 6, 6, 5);
		this.head_base.setRotationPoint(0.0F, -1.0F, -1.7F);
		this.head_base.setOffset(0.0F, -1.0F, -3.0F);
		this.head_front = new ModelRendererAnimania(this, 0, 12);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-2.0F, -1.0F, -2.5F, 4, 2, 5);
		this.head_front.setRotationPoint(0.0F, 1.0F, 1.5F);
		this.head_front.setOffset(0.0F, -1.0F, -2.9F);
		this.head_slope = new ModelRendererAnimania(this, 37, 11);
		this.head_slope.setTextureSize(128, 64);
		this.head_slope.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5);
		this.head_slope.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.head_slope.setOffset(0.0F, -1.0F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 10, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -0.7F, -1.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.jaw = new ModelRendererAnimania(this, 0, 20);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.jaw.setRotationPoint(0.0F, 2.4F, -0.5F);
		this.jaw.setOffset(0.0F, -0.2F, -1.0F);
		this.cheek_l = new ModelRendererAnimania(this, 32, 41);
		this.cheek_l.setTextureSize(128, 64);
		this.cheek_l.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3);
		this.cheek_l.setRotationPoint(1.4F, 1.0F, 2.0F);
		this.cheek_l.setOffset(2.0F, -0.6F, -1.0F);
		this.cheek_l2 = new ModelRendererAnimania(this, 32, 36);
		this.cheek_l2.setTextureSize(128, 64);
		this.cheek_l2.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.cheek_l2.setRotationPoint(-1.0F, 0.6F, 1.5F);
		this.cheek_l2.setOffset(2.0F, -0.6F, -1.0F);
		this.cheek_r = new ModelRendererAnimania(this, 32, 41);
		this.cheek_r.setTextureSize(128, 64);
		this.cheek_r.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3);
		this.cheek_r.setRotationPoint(-1.4F, 1.0F, 2.0F);
		this.cheek_r.setOffset(-2.0F, -0.6F, -1.0F);
		this.cheek_r2 = new ModelRendererAnimania(this, 32, 36);
		this.cheek_r2.setTextureSize(128, 64);
		this.cheek_r2.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.cheek_r2.setRotationPoint(1.0F, 0.6F, 1.5F);
		this.cheek_r2.setOffset(-2.0F, -0.6F, -1.0F);
		this.ear_r = new ModelRendererAnimania(this, 3, 3);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_r.setRotationPoint(-2.5F, -2.5F, 0.7001F);
		this.ear_r.setOffset(-0.5F, -0.6F, -0.5F);
		this.ear_r2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -1.7F);
		this.ear_r2.setOffset(0.0F, -0.6F, -0.5F);
		this.ear_r3 = new ModelRendererAnimania(this, 1, 0);
		this.ear_r3.setTextureSize(128, 64);
		this.ear_r3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, 0.6F, -0.3F);
		this.ear_r3.setOffset(0.0F, -0.6F, -0.5F);
		this.ear_l = new ModelRendererAnimania(this, 3, 3);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_l.setRotationPoint(2.5F, -2.5F, 0.7001F);
		this.ear_l.setOffset(0.5F, -0.6F, -0.5F);
		this.ear_l2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -1.7F);
		this.ear_l2.setOffset(0.0F, -0.6F, -0.5F);
		this.ear_l3 = new ModelRendererAnimania(this, 1, 0);
		this.ear_l3.setTextureSize(128, 64);
		this.ear_l3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_l3.setRotationPoint(0.0F, 0.6F, -0.3F);
		this.ear_l3.setOffset(0.0F, -0.6F, -0.5F);
		this.mane = new ModelRendererAnimania(this, 96, 22);
		this.mane.setTextureSize(128, 64);
		this.mane.addBox(-5.0F, -4.0F, -3.0F, 10, 8, 6);
		this.mane.setRotationPoint(0.0F, -2.5F, 3.0F);
		this.mane.setOffset(0.0F, 1.8F, -2.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 2, 37);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_r1.setRotationPoint(-4.2F, -5.0F, -2.0F);
		this.leg_r1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 1, 52);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_r2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_r2.setOffset(0.12F, 4.0F, -0.6F);
		this.leg_l1 = new ModelRendererAnimania(this, 2, 37);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_l1.setRotationPoint(4.2F, -5.0F, -2.0F);
		this.leg_l1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 1, 52);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_l2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_l2.setOffset(-0.12F, 4.0F, -0.6F);
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
		this.lower_body.rotateAngleX = -0.04414810342919657F;
		this.tail.rotateAngleX = -0.9733229999474338F;
		this.tail2.rotateAngleX = 0.40470694695244513F;
		this.tail3.rotateAngleX = 0.43627646246251855F;
		this.back_leg_r1.rotateAngleX = -1.7453292519943296E-6F;
		this.back_leg_l1.rotateAngleX = -1.7453292519943296E-6F;
		this.neck1.rotateAngleX = -0.8681459685637515F;
		this.head_base.rotateAngleX = 1.0383626945230024F;
		this.head_front.rotateAngleX = 0.15707963267948966F;
		this.head_slope.rotateAngleX = 0.35123180400059084F;
		this.nose.rotateAngleX = -0.17677218262974168F;
		this.jaw.rotateAngleX = -0.009829694347232064F;
		this.cheek_l.rotateAngleX = 0.0464484473833251F;
		this.cheek_l.rotateAngleY = 0.17291151432433022F;
		this.cheek_l.rotateAngleZ = -0.08372868020592397F;
		this.cheek_l2.rotateAngleY = 0.24005433064855206F;
		this.cheek_l2.rotateAngleZ = 1.7453292519943296E-6F;
		this.cheek_r.rotateAngleX = 0.0464484473833251F;
		this.cheek_r.rotateAngleY = -0.17290976899507823F;
		this.cheek_r.rotateAngleZ = 0.08373042553517596F;
		this.cheek_r2.rotateAngleY = -0.24005258531930007F;
		this.ear_r.rotateAngleX = -1.0327043370880369F;
		this.ear_r.rotateAngleY = 1.4596223441013618F;
		this.ear_r.rotateAngleZ = -1.433051451569F;
		this.ear_r2.rotateAngleX = 0.15050148672872304F;
		this.ear_r3.rotateAngleX = 0.1898272454346593F;
		this.ear_r3.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_l.rotateAngleX = -1.0327043370880369F;
		this.ear_l.rotateAngleY = -1.4596205987721098F;
		this.ear_l.rotateAngleZ = 1.433053196898252F;
		this.ear_l2.rotateAngleX = 0.15050148672872304F;
		this.ear_l3.rotateAngleX = 0.1898272454346593F;
		this.ear_l3.rotateAngleZ = 1.7453292519943296E-6F;
		this.mane.rotateAngleX = 0.16029278383241125F;
		this.leg_r1.rotateAngleX = -0.03490658503988659F;
		this.leg_l1.rotateAngleX = -0.03490658503988659F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime)
	{

		EntityAnimaniaCat cat = (EntityAnimaniaCat) entity;

		boolean sitting = cat.isSitting();
		if (!sitting)
		{
			this.body.rotateAngleX = 0F;
			this.lower_body.rotateAngleX = -0.04414810342919657F;
			this.tail.rotateAngleX = -1.0040809373553299F;
			this.back_leg_r1.rotateAngleX = 0.05145579700729682F;
			this.back_leg_l1.rotateAngleX = 0.03700098014227979F;
			this.leg_r1.rotateAngleX = 0F;
			this.leg_l1.rotateAngleX = 0F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		limbSwingAmount *= 0.6;
		
		this.neck1.rotateAngleX = headPitch * 0.001453292F - 0.7f;
		this.neck1.rotateAngleY = netHeadYaw * 0.017453292F;

		EntityAnimaniaCat cat = (EntityAnimaniaCat) entity;

		if (!cat.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		this.back_leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.back_leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		boolean sitting = cat.isSitting();
		if (sitting)
		{
			this.body.rotateAngleX = -0.12601277199399058F;
			this.lower_body.rotateAngleX = -0.6898780387650506F;
			this.tail.rotateAngleX = 0.5878635439859821F;
			this.back_leg_r1.rotateAngleX = -0.41790512675602626F;
			this.back_leg_l1.rotateAngleX = -0.4277452930787703F;
			this.leg_r1.rotateAngleX = -0.050714032075199235F;
			this.leg_l1.rotateAngleX = -0.04352676621548658F;
		}

	}
}

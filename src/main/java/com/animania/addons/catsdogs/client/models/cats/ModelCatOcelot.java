package com.animania.addons.catsdogs.client.models.cats;

import com.animania.addons.catsdogs.common.entity.felids.EntityAnimaniaCat;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelCatOcelot extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania tail3;
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
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania leg_l2;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l21;

	public ModelCatOcelot()
	{
		this.body = new ModelRendererAnimania(this, 38, 44);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-3.0F, -4.5F, -5.5F, 6, 9, 11);
		this.body.setRotationPoint(0.0F, 11.0F, -4.0F);
		this.body.setOffset(0.0F, 1.0F, -0.0F);
		this.lower_body = new ModelRendererAnimania(this, 76, 43);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.5F, -4.0F, -6.5F, 7, 8, 13);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 5.0F);
		this.lower_body.setOffset(0.0F, 0.5F, 6.0F);
		this.tail = new ModelRendererAnimania(this, 43, 15);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.0F, -1.5F, -5.0F, 2, 3, 10);
		this.tail.setRotationPoint(0.0F, -3.0F, 5.0F);
		this.tail.setOffset(0.0F, -0.0F, 4.0F);
		this.tail2 = new ModelRendererAnimania(this, 70, 26);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6);
		this.tail2.setRotationPoint(0.0F, 0.0F, 3.5F);
		this.tail2.setOffset(0.0F, -0.0F, 3.0F);
		this.tail3 = new ModelRendererAnimania(this, 60, 4);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-1.0F, -1.5F, -4.0F, 2, 3, 8);
		this.tail3.setRotationPoint(0.0F, 0.0F, 2.0001F);
		this.tail3.setOffset(0.0F, -0.0F, 4.0F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 19, 51);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_r1.setRotationPoint(-4.0F, -4.0F, 3.0F);
		this.back_leg_r1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 18, 35);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -6.0F, -1.5F, 2, 12, 3);
		this.back_leg_r2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_r2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 19, 51);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_l1.setRotationPoint(4.0F, -4.0F, 3.0001F);
		this.back_leg_l1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 18, 35);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -6.0F, -1.5F, 2, 12, 3);
		this.back_leg_l2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_l2.setOffset(-0.01F, 3.5F, 0.7F);
		this.neck1 = new ModelRendererAnimania(this, 12, 19);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -2.5F, -4.5F, 5, 5, 9);
		this.neck1.setRotationPoint(0.0F, -2.0F, -4.0F);
		this.neck1.setOffset(0.0F, 1.0F, -4.0F);
		this.head_base = new ModelRendererAnimania(this, 89, 14);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.0F, -2.5F, -2.5F, 6, 5, 5);
		this.head_base.setRotationPoint(0.0F, -1.5F, -2.0F);
		this.head_base.setOffset(0.0F, 0.5F, -3.0F);
		this.head_front = new ModelRendererAnimania(this, 0, 12);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-2.0F, -1.0F, -2.5F, 4, 2, 5);
		this.head_front.setRotationPoint(0.0F, 2.2F, -0.2F);
		this.head_front.setOffset(0.0F, -1.0F, -2.9F);
		this.head_slope = new ModelRendererAnimania(this, 32, 12);
		this.head_slope.setTextureSize(128, 64);
		this.head_slope.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5);
		this.head_slope.setRotationPoint(0.0F, -1.4F, 4.5F);
		this.head_slope.setOffset(0.0F, -1.0F, -4.0F);
		this.nose = new ModelRendererAnimania(this, 10, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -0.657F, -1.6634F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.jaw = new ModelRendererAnimania(this, 1, 21);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.jaw.setRotationPoint(0.0F, 2.0F, -2.2F);
		this.jaw.setOffset(0.0F, -0.2F, -1.0F);
		this.ear_r = new ModelRendererAnimania(this, 4, 4);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_r.setRotationPoint(2.5F, -2.0F, 0.7F);
		this.ear_r.setOffset(0.5F, -0.6F, -0.5F);
		this.ear_r2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -1.8F);
		this.ear_r2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_l = new ModelRendererAnimania(this, 4, 4);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_l.setRotationPoint(-2.5F, -2.0F, 0.7001F);
		this.ear_l.setOffset(-0.5F, -0.6F, -0.5F);
		this.ear_l2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -1.8F);
		this.ear_l2.setOffset(0.0F, -0.6F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 2, 37);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_l2.setRotationPoint(-3.0F, -4.5F, -3.0F);
		this.leg_l2.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 1, 52);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_r2.setRotationPoint(0.0F, 5.0F, 0.1F);
		this.leg_r2.setOffset(0.12F, 4.0F, -0.6F);
		this.leg_l1 = new ModelRendererAnimania(this, 2, 37);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_l1.setRotationPoint(3.0F, -4.5F, -3.0F);
		this.leg_l1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_l21 = new ModelRendererAnimania(this, 1, 52);
		this.leg_l21.setTextureSize(128, 64);
		this.leg_l21.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_l21.setRotationPoint(0.0F, 5.0F, 0.1F);
		this.leg_l21.setOffset(-0.12F, 4.0F, -0.6F);
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
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.neck1.addChild(this.head_base);
		this.body.addChild(this.neck1);
		this.leg_l2.addChild(this.leg_r2);
		this.body.addChild(this.leg_l2);
		this.leg_l1.addChild(this.leg_l21);
		this.body.addChild(this.leg_l1);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.body.rotateAngleX = 0.04466646621703888F;
		this.lower_body.rotateAngleX = -0.060589104982983144F;
		this.tail.rotateAngleX = 5.32134133772252F;
		this.tail2.rotateAngleX = 0.29802020576578775F;
		this.tail3.rotateAngleX = 0.4171459085314087F;
		this.back_leg_r1.rotateAngleX = -1.7453292519943296E-6F;
		this.neck1.rotateAngleX = -0.6042469496744529F;
		this.head_base.rotateAngleX = 1.0428656439931479F;
		this.head_front.rotateAngleX = -0.15707963267948966F;
		this.head_slope.rotateAngleX = 0.2669882516653286F;
		this.nose.rotateAngleX = -0.39269559104022017F;
		this.jaw.rotateAngleX = -0.16585514815851715F;
		this.ear_r.rotateAngleX = -0.9144879508627048F;
		this.ear_r.rotateAngleY = -2.263808976896529F;
		this.ear_r.rotateAngleZ = 1.7145172093796217F;
		this.ear_r2.rotateAngleX = 0.3490658503988659F;
		this.ear_l.rotateAngleX = -0.9144879508627048F;
		this.ear_l.rotateAngleY = 2.263810722225781F;
		this.ear_l.rotateAngleZ = -1.7145154640503697F;
		this.ear_l2.rotateAngleX = 0.3490658503988659F;
		this.leg_l2.rotateAngleX = -0.03490658503988659F;
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
			this.leg_l2.rotateAngleX = 0F;
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
		this.leg_l2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		boolean sitting = cat.isSitting();
		if (sitting)
		{
			this.body.rotateAngleX = -0.12601277199399058F;
			this.lower_body.rotateAngleX = -0.6898780387650506F;
			this.tail.rotateAngleX = 0.5878635439859821F;
			this.back_leg_r1.rotateAngleX = -0.41790512675602626F;
			this.back_leg_l1.rotateAngleX = -0.4277452930787703F;
			this.leg_l2.rotateAngleX = -0.050714032075199235F;
			this.leg_l1.rotateAngleX = -0.04352676621548658F;
		}

	}
}

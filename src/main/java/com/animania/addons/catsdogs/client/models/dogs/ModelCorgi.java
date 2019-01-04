package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.common.entity.dogs.EntityAnimaniaDog;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelCorgi extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania toes_r2;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania toes_r;
	ModelRendererAnimania leg_r21;
	ModelRendererAnimania toes_r21;
	ModelRendererAnimania leg_r11;
	ModelRendererAnimania toes_r1;
	ModelRendererAnimania head_base;
	ModelRendererAnimania head_front;
	ModelRendererAnimania nose;
	ModelRendererAnimania jaw;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania tail3;
	ModelRendererAnimania neck;

	public ModelCorgi()
	{
		this.body = new ModelRendererAnimania(this, 37, 44);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
		this.body.setRotationPoint(0.0F, 18.0F, -10.0F);
		this.body.setOffset(0.0F, 0.8F, 5.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 1, 55);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
		this.leg_r1.setRotationPoint(2.0F, 1.45F, 1.0F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.toes_r = new ModelRendererAnimania(this, 12, 54);
		this.toes_r.setTextureSize(128, 64);
		this.toes_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r.setRotationPoint(0.0F, 1.0F, -1.5F);
		this.toes_r.setOffset(0.0F, -0.0F, -0.0F);
		this.leg_r11 = new ModelRendererAnimania(this, 1, 55);
		this.leg_r11.setTextureSize(128, 64);
		this.leg_r11.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
		this.leg_r11.setRotationPoint(-2.0F, 1.45F, 1.0F);
		this.leg_r11.setOffset(0.0F, 3.0F, -0.0F);
		this.toes_r1 = new ModelRendererAnimania(this, 12, 54);
		this.toes_r1.setTextureSize(128, 64);
		this.toes_r1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r1.setRotationPoint(0.0F, 1.0F, -1.5F);
		this.toes_r1.setOffset(0.0F, -0.0F, -0.0F);
		this.head_base = new ModelRendererAnimania(this, 102, 28);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.5F, -3.0F, -2.5F, 7, 6, 5);
		this.head_base.setRotationPoint(0.0F, -4.8F, -3.0F);
		this.head_base.setOffset(0.0F, 0.49F, -1.8F);
		this.head_front = new ModelRendererAnimania(this, 104, 3);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-1.5F, -1.5F, -3.5F, 3, 3, 7);
		this.head_front.setRotationPoint(0.0F, 2.0F, 1.0F);
		this.head_front.setOffset(0.0F, -0.4F, -3.0F);
		this.nose = new ModelRendererAnimania(this, 98, 17);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -2.0F, -2.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.jaw = new ModelRendererAnimania(this, 85, 3);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.0F, -1.0F, -3.5F, 2, 2, 7);
		this.jaw.setRotationPoint(0.0F, -2.0F, 3.158F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.ear_r = new ModelRendererAnimania(this, 76, 2);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-2.0F, -0.5F, -1.5F, 4, 1, 3);
		this.ear_r.setRotationPoint(-2.0F, -2.0F, 0.7001F);
		this.ear_r.setOffset(-0.5F, -0.6F, -1.7F);
		this.ear_r2 = new ModelRendererAnimania(this, 61, 2);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.5F, -0.5F, -1.0F, 3, 1, 2);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -1.0F);
		this.ear_r2.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_r3 = new ModelRendererAnimania(this, 64, 0);
		this.ear_r3.setTextureSize(128, 64);
		this.ear_r3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, 0.6F, -0.9999F);
		this.ear_r3.setOffset(0.0F, -0.6F, -2.5F);
		this.ear_l = new ModelRendererAnimania(this, 76, 2);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-2.0F, -0.5F, -1.5F, 4, 1, 3);
		this.ear_l.setRotationPoint(2.0F, -2.0F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -1.7F);
		this.ear_l2 = new ModelRendererAnimania(this, 61, 2);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.5F, -0.5F, -1.0F, 3, 1, 2);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -1.0F);
		this.ear_l2.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_l3 = new ModelRendererAnimania(this, 64, 0);
		this.ear_l3.setTextureSize(128, 64);
		this.ear_l3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_l3.setRotationPoint(-1.0E-4F, 0.6F, -0.9999F);
		this.ear_l3.setOffset(0.0F, -0.6F, -2.5F);
		this.lower_body = new ModelRendererAnimania(this, 29, 25);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.0F, -3.0F, -6.5F, 6, 6, 13);
		this.lower_body.setRotationPoint(0.0F, -0.8851F, -1.0F);
		this.lower_body.setOffset(0.0F, 0.8F, 5.0F);
		this.tail = new ModelRendererAnimania(this, 71, 27);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5);
		this.tail.setRotationPoint(0.0F, -1.0F, 7.0F);
		this.tail.setOffset(0.0F, 1.0F, -1.0F);
		this.tail2 = new ModelRendererAnimania(this, 70, 26);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6);
		this.tail2.setRotationPoint(0.0F, -0.7853F, 2.5086F);
		this.tail2.setOffset(0.0F, 1.0F, 2.5F);
		this.tail3 = new ModelRendererAnimania(this, 70, 26);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-0.0F, -1.0F, -3.0F, 0, 2, 6);
		this.tail3.setRotationPoint(0.0F, -0.7853F, 2.5086F);
		this.tail3.setOffset(0.0F, 1.0F, 4.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 1, 55);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
		this.leg_r2.setRotationPoint(-2.0F, 1.5352F, 5.0F);
		this.leg_r2.setOffset(0.0F, 3.0F, -0.0F);
		this.toes_r2 = new ModelRendererAnimania(this, 12, 54);
		this.toes_r2.setTextureSize(128, 64);
		this.toes_r2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r2.setRotationPoint(0.0F, 1.0F, -1.5F);
		this.toes_r2.setOffset(0.0F, -0.0F, -0.0F);
		this.leg_r21 = new ModelRendererAnimania(this, 1, 55);
		this.leg_r21.setTextureSize(128, 64);
		this.leg_r21.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
		this.leg_r21.setRotationPoint(2.0F, 1.5352F, 5.0F);
		this.leg_r21.setOffset(0.0F, 3.0F, -0.0F);
		this.toes_r21 = new ModelRendererAnimania(this, 12, 54);
		this.toes_r21.setTextureSize(128, 64);
		this.toes_r21.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r21.setRotationPoint(0.0F, 1.0F, -1.5F);
		this.toes_r21.setOffset(0.0F, -0.0F, -0.0F);
		this.neck = new ModelRendererAnimania(this, 38, 16);
		this.neck.setTextureSize(128, 64);
		this.neck.addBox(-3.0F, -1.5F, -2.5F, 6, 3, 5);
		this.neck.setRotationPoint(0.0F, -4.8F, -6.0F);
		this.neck.setOffset(0.0F, 0.8F, 5.0F);
		this.leg_r1.addChild(this.toes_r);
		this.body.addChild(this.leg_r1);
		this.leg_r11.addChild(this.toes_r1);
		this.body.addChild(this.leg_r11);
		this.head_front.addChild(this.nose);
		this.head_front.addChild(this.jaw);
		this.head_base.addChild(this.head_front);
		this.ear_r.addChild(this.ear_r2);
		this.ear_r.addChild(this.ear_r3);
		this.head_base.addChild(this.ear_r);
		this.ear_l.addChild(this.ear_l2);
		this.ear_l.addChild(this.ear_l3);
		this.head_base.addChild(this.ear_l);
		this.body.addChild(this.head_base);
		this.tail.addChild(this.tail2);
		this.tail.addChild(this.tail3);
		this.lower_body.addChild(this.tail);
		this.leg_r2.addChild(this.toes_r2);
		this.lower_body.addChild(this.leg_r2);
		this.leg_r21.addChild(this.toes_r21);
		this.lower_body.addChild(this.leg_r21);
		this.body.addChild(this.lower_body);
		this.body.addChild(this.neck);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.head_base.rotateAngleX = -0.06602929626144947F;
		this.head_front.rotateAngleX = 0.04036772026937685F;
		this.jaw.rotateAngleX = 0.26870740097854295F;
		this.ear_r.rotateAngleX = -1.3181616628979693F;
		this.ear_r.rotateAngleY = 1.9450786942265765F;
		this.ear_r.rotateAngleZ = -1.6712679505152022F;
		this.ear_r2.rotateAngleX = 0.06981317007977318F;
		this.ear_r3.rotateAngleX = 0.06981317007977318F;
		this.ear_r3.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_l.rotateAngleX = -1.3181634082272213F;
		this.ear_l.rotateAngleY = -1.9450786942265765F;
		this.ear_l.rotateAngleZ = 1.6712696958444542F;
		this.ear_l2.rotateAngleX = 0.06981317007977318F;
		this.ear_l3.rotateAngleX = 0.06981317007977318F;
		this.ear_l3.rotateAngleZ = 1.7453292519943296E-6F;
		this.tail.rotateAngleX = -0.4323512169747833F;
		this.tail2.rotateAngleX = -0.2780745830739965F;
		this.tail3.rotateAngleX = -0.29183650422597185F;
		this.neck.rotateAngleX = -0.7303504787895471F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
		
	
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime)
	{

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		boolean sitting = dog.isSitting();
		if (!sitting)
		{
			this.body.rotateAngleX = 0;
			this.leg_r1.rotateAngleX = 0;
			this.leg_r11.rotateAngleX = 0;
			this.lower_body.rotateAngleX = 0F;
			this.tail.rotateAngleX = -0.4323512169747833F;
			this.tail2.rotateAngleX = -0.2780745830739965F;
			this.tail3.rotateAngleX = -0.29183650422597185F;
			this.leg_r2.rotateAngleX = 0;
			this.leg_r21.rotateAngleX = 0;
			

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		limbSwingAmount *= 0.6;

		this.head_base.rotateAngleX = headPitch * 0.001453292F;
		this.head_base.rotateAngleY = netHeadYaw * 0.017453292F;

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg_r11.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_r21.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg_r2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.rotateAngleX = -0.07355690132530102F;
			this.leg_r1.rotateAngleX = -0.39865763576503277F;
			this.leg_r11.rotateAngleX = -0.40206975445268167F;
			this.lower_body.rotateAngleX = -0.06664190682889948F;
			this.tail.rotateAngleX = -0.0519846317706511F;
			this.tail2.rotateAngleX = 0.3797661919414462F;
			this.tail3.rotateAngleX = 0.3947725328500934F;
			this.leg_r2.rotateAngleX = -0.6804916580478251F;
			this.leg_r21.rotateAngleX = -0.7049157956002338F;
		}

	}
}

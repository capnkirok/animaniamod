package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.client.models.dogs.poses.PoseChihuahuaSleeping;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelChihuahua extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania tail;
	ModelRendererAnimania neck;
	ModelRendererAnimania pug_head;
	ModelRendererAnimania snout;
	ModelRendererAnimania nose;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;
	ModelRendererAnimania front_right;
	ModelRendererAnimania front_left;
	ModelRendererAnimania back_left;
	ModelRendererAnimania back_right;

	public ModelChihuahua()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(64, 64);
		this.body.addBox(-3.0F, -3.5F, -6.5F, 6, 7, 13);
		this.body.setRotationPoint(0.0F, 13.5F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 10, 43);
		this.tail.setTextureSize(64, 64);
		this.tail.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.tail.setRotationPoint(0.0F, -6.0F, 7.0F);
		this.tail.setOffset(0.0F, -0.0F, 2.0F);
		this.neck = new ModelRendererAnimania(this, 28, 28);
		this.neck.setTextureSize(64, 64);
		this.neck.addBox(-2.5F, -3.0F, -3.5F, 5, 6, 7);
		this.neck.setRotationPoint(0.0F, -1.0F, -5.0F);
		this.neck.setOffset(0.0F, -0.0F, -1.0F);
		this.pug_head = new ModelRendererAnimania(this, 27, 12);
		this.pug_head.setTextureSize(64, 64);
		this.pug_head.addBox(-4.0F, -4.0F, -3.5F, 8, 8, 7);
		this.pug_head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.pug_head.setOffset(0.0F, -1.0F, -0.0F);
		this.snout = new ModelRendererAnimania(this, 47, 27);
		this.snout.setTextureSize(64, 64);
		this.snout.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4);
		this.snout.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.snout.setOffset(0.0F, 1.5F, -2.0F);
		this.nose = new ModelRendererAnimania(this, 40, 3);
		this.nose.setTextureSize(64, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.5F, -4.0F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.ear_l = new ModelRendererAnimania(this, 16, 8);
		this.ear_l.setTextureSize(64, 64);
		this.ear_l.addBox(-2.5F, -2.0F, -0.5F, 5, 4, 1);
		this.ear_l.setRotationPoint(-3.0F, -3.4909F, 0.5292F);
		this.ear_l.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_l2 = new ModelRendererAnimania(this, 18, 5);
		this.ear_l2.setTextureSize(64, 64);
		this.ear_l2.addBox(-1.5F, -1.0F, -0.5F, 3, 2, 1);
		this.ear_l2.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.ear_l2.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_l3 = new ModelRendererAnimania(this, 19, 3);
		this.ear_l3.setTextureSize(64, 64);
		this.ear_l3.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_l3.setRotationPoint(0.0F, -0.5F, -0.0F);
		this.ear_l3.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r = new ModelRendererAnimania(this, 16, 8);
		this.ear_r.setTextureSize(64, 64);
		this.ear_r.addBox(-2.5F, -2.0F, -0.5F, 5, 4, 1);
		this.ear_r.setRotationPoint(3.0F, -3.4909F, 0.5292F);
		this.ear_r.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r2 = new ModelRendererAnimania(this, 18, 5);
		this.ear_r2.setTextureSize(64, 64);
		this.ear_r2.addBox(-1.5F, -1.0F, -0.5F, 3, 2, 1);
		this.ear_r2.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.ear_r2.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r3 = new ModelRendererAnimania(this, 19, 3);
		this.ear_r3.setTextureSize(64, 64);
		this.ear_r3.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, -0.5F, -0.0F);
		this.ear_r3.setOffset(0.0F, -1.0F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 4, 19);
		this.front_right.setTextureSize(64, 64);
		this.front_right.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.front_right.setRotationPoint(-1.5F, 3.5F, -4.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 4, 19);
		this.front_left.setTextureSize(64, 64);
		this.front_left.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.front_left.setRotationPoint(1.5F, 3.5F, -4.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.back_left = new ModelRendererAnimania(this, 4, 19);
		this.back_left.setTextureSize(64, 64);
		this.back_left.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.back_left.setRotationPoint(1.5F, 3.5F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.back_right = new ModelRendererAnimania(this, 4, 19);
		this.back_right.setTextureSize(64, 64);
		this.back_right.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.back_right.setRotationPoint(-1.5F, 3.5F, 4.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.body.addChild(this.tail);
		this.pug_head.addChild(this.snout);
		this.pug_head.addChild(this.nose);
		this.ear_l2.addChild(this.ear_l3);
		this.ear_l.addChild(this.ear_l2);
		this.pug_head.addChild(this.ear_l);
		this.ear_r2.addChild(this.ear_r3);
		this.ear_r.addChild(this.ear_r2);
		this.pug_head.addChild(this.ear_r);
		this.neck.addChild(this.pug_head);
		this.body.addChild(this.neck);
		this.body.addChild(this.front_right);
		this.body.addChild(this.front_left);
		this.body.addChild(this.back_left);
		this.body.addChild(this.back_right);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.0F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.tail.rotateAngleX = -0.6430019857149869F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.neck.rotateAngleX = -0.9424777960769379F;
		this.neck.rotateAngleY = 0.0F;
		this.neck.rotateAngleZ = 0.0F;
		this.pug_head.rotateAngleX = 0.9867323645905062F;
		this.pug_head.rotateAngleY = 0.0F;
		this.pug_head.rotateAngleZ = 0.0F;
		this.snout.rotateAngleX = 0.0F;
		this.snout.rotateAngleY = 0.0F;
		this.snout.rotateAngleZ = 0.0F;
		this.nose.rotateAngleX = -1.7453292519943296E-6F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.ear_l.rotateAngleX = -0.04002563573598596F;
		this.ear_l.rotateAngleY = 0.0F;
		this.ear_l.rotateAngleZ = -0.43320991896676453F;
		this.ear_l2.rotateAngleX = 0.0F;
		this.ear_l2.rotateAngleY = 0.0F;
		this.ear_l2.rotateAngleZ = 0.0F;
		this.ear_l3.rotateAngleX = 0.0F;
		this.ear_l3.rotateAngleY = 0.0F;
		this.ear_l3.rotateAngleZ = 0.0F;
		this.ear_r.rotateAngleX = -0.04002563573598596F;
		this.ear_r.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_r.rotateAngleZ = 0.43321166429601654F;
		this.ear_r2.rotateAngleX = 0.0F;
		this.ear_r2.rotateAngleY = 0.0F;
		this.ear_r2.rotateAngleZ = 0.0F;
		this.ear_r3.rotateAngleX = 0.0F;
		this.ear_r3.rotateAngleY = 0.0F;
		this.ear_r3.rotateAngleZ = 0.0F;
		this.front_right.rotateAngleX = 0.0F;
		this.front_right.rotateAngleY = 0.0F;
		this.front_right.rotateAngleZ = 0.0F;
		this.front_left.rotateAngleX = 0.0F;
		this.front_left.rotateAngleY = 0.0F;
		this.front_left.rotateAngleZ = 0.0F;
		this.back_left.rotateAngleX = 0.0F;
		this.back_left.rotateAngleY = 0.0F;
		this.back_left.rotateAngleZ = 0.0F;
		this.back_right.rotateAngleX = 0.0F;
		this.back_right.rotateAngleY = 0.0F;
		this.back_right.rotateAngleZ = 0.0F;
	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
	}

	@Override
	public void setLivingAnimations(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTickTime)
	{

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		boolean sitting = dog.isSitting();
		if (!sitting)
		{
			this.body.setRotationPoint(0.0F, 13.5F, 10.0F);

			this.body.rotateAngleX = 0f;
			this.neck.rotateAngleX = -0.9467084741837722F;
			this.pug_head.rotateAngleX = 0.9867323645905062F;
			this.front_left.rotateAngleX = 0f;
			this.back_left.rotateAngleX = 0f;
			this.back_right.rotateAngleX = 0f;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PoseChihuahuaSleeping.INSTANCE));

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping() && !dog.isSitting())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck.rotateAngleX = headPitch * 0.001453292F - 0.9f;
			this.neck.rotateAngleY = netHeadYaw * 0.017453292F;
		}
		if (dog.getSleeping() && !dog.isSitting())
		{
			sleepingPose.transitionToPose(10, ageInTicks);
		}

		limbSwingAmount *= 0.6;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
			this.back_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.back_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.front_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.front_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;

		} else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.setRotationPoint(0.0F, 18.0F, 10.0F);

			this.body.rotateAngleX = -0.5040528333052143F;
			this.neck.rotateAngleX = -0.9206419818052369F;
			this.pug_head.rotateAngleX = 1.2854227767890596F;
			this.front_left.rotateAngleX = -0.04153534553896105F;
			this.back_left.rotateAngleX = 5.323938387649487F;
			this.back_right.rotateAngleX = -0.9943507267754614F;
		}

	}
}

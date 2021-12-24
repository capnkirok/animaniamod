package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.client.models.dogs.poses.PoseStandardDogSleeping;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelFox extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l2;
	ModelRendererAnimania toe_l;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania tail3;
	ModelRendererAnimania back_leg_r1;
	ModelRendererAnimania back_leg_r2;
	ModelRendererAnimania back_toe_r;
	ModelRendererAnimania back_leg_l1;
	ModelRendererAnimania back_leg_l2;
	ModelRendererAnimania back_toe_l;
	ModelRendererAnimania neck1;
	ModelRendererAnimania head_base;
	ModelRendererAnimania head_front;
	ModelRendererAnimania nose;
	ModelRendererAnimania upper_jaw_detail;
	ModelRendererAnimania jaw;
	ModelRendererAnimania chops_r;
	ModelRendererAnimania chops_l;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania toe_r;

	public ModelFox()
	{
		this.body = new ModelRendererAnimania(this, 34, 45);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.0F, -4.0F, -4.5F, 8, 8, 9);
		this.body.setRotationPoint(0.0F, 11.5F, -4.0F);
		this.body.setOffset(0.0F, 0.6F, -1.5F);
		this.leg_l1 = new ModelRendererAnimania(this, 0, 54);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -3.0F, -1.5F, 2, 6, 3);
		this.leg_l1.setRotationPoint(3.3F, 0.4F, -2.0F);
		this.leg_l1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 11, 54);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.leg_l2.setRotationPoint(0.0F, 2.4F, 1.0F);
		this.leg_l2.setOffset(-0.01F, 3.0F, -0.61F);
		this.toe_l = new ModelRendererAnimania(this, 11, 60);
		this.toe_l.setTextureSize(128, 64);
		this.toe_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe_l.setRotationPoint(0.0F, -3.0F, 0.61F);
		this.toe_l.setOffset(0.0F, 5.5F, -1.5F);
		this.lower_body = new ModelRendererAnimania(this, 30, 25);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.0F, -3.5F, -6.0F, 6, 7, 12);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 3.5F);
		this.lower_body.setOffset(0.0F, 0.8F, 5.9F);
		this.tail = new ModelRendererAnimania(this, 50, 12);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -2.0F, -3.5F, 3, 4, 7);
		this.tail.setRotationPoint(0.0F, -2.4F, 5.3F);
		this.tail.setOffset(0.0F, 1.0F, 3.5F);
		this.tail2 = new ModelRendererAnimania(this, 66, 22);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-2.0F, -2.5F, -5.0F, 4, 5, 10);
		this.tail2.setRotationPoint(0.0F, -1.2F, 2.8F);
		this.tail2.setOffset(0.0F, 1.5F, 4.6F);
		this.tail3 = new ModelRendererAnimania(this, 64, 8);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-1.5F, -2.0F, -2.0F, 3, 4, 4);
		this.tail3.setRotationPoint(0.0F, -0.5F, 3.4F);
		this.tail3.setOffset(0.0F, 0.6F, 3.0F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 20, 52);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.back_leg_r1.setRotationPoint(2.5F, -1.3F, 2.4F);
		this.back_leg_r1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 0, 43);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -4.0F, -1.0F, 2, 8, 2);
		this.back_leg_r2.setRotationPoint(0.0F, 2.0F, 0.5F);
		this.back_leg_r2.setOffset(-0.01F, 3.5F, 0.7F);
		this.back_toe_r = new ModelRendererAnimania(this, 1, 51);
		this.back_toe_r.setTextureSize(128, 64);
		this.back_toe_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_r.setRotationPoint(0.0F, -3.5F, -0.7F);
		this.back_toe_r.setOffset(0.01F, 7.0F, -0.4F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 20, 52);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.back_leg_l1.setRotationPoint(-2.5F, -1.3F, 2.4F);
		this.back_leg_l1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 0, 43);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -4.0F, -1.0F, 2, 8, 2);
		this.back_leg_l2.setRotationPoint(0.0F, 2.0F, 0.5F);
		this.back_leg_l2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_toe_l = new ModelRendererAnimania(this, 1, 51);
		this.back_toe_l.setTextureSize(128, 64);
		this.back_toe_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_l.setRotationPoint(0.0F, 2.0F, 0.5001F);
		this.back_toe_l.setOffset(0.01F, 7.0F, -0.4F);
		this.neck1 = new ModelRendererAnimania(this, 0, 27);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -3.0F, -4.5F, 5, 6, 9);
		this.neck1.setRotationPoint(0.0F, -1.0F, -3.0F);
		this.neck1.setOffset(0.0F, 1.2F, -3.0F);
		this.head_base = new ModelRendererAnimania(this, 0, 16);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.5F, -3.0F, -2.5F, 7, 6, 5);
		this.head_base.setRotationPoint(0.0F, -0.8F, -3.0F);
		this.head_base.setOffset(0.0F, 0.49F, -1.8F);
		this.head_front = new ModelRendererAnimania(this, 0, 8);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-1.5F, -1.0F, -2.5F, 3, 2, 5);
		this.head_front.setRotationPoint(0.0F, 1.5F, -1.0F);
		this.head_front.setOffset(0.0F, -0.4F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 10, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.0F, -1.4F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.upper_jaw_detail = new ModelRendererAnimania(this, 14, 0);
		this.upper_jaw_detail.setTextureSize(128, 64);
		this.upper_jaw_detail.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
		this.upper_jaw_detail.setRotationPoint(0.0F, -3.0969F, 2.6519F);
		this.upper_jaw_detail.setOffset(0.0F, 1.0F, -3.4F);
		this.jaw = new ModelRendererAnimania(this, 29, 11);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 6);
		this.jaw.setRotationPoint(0.0F, 2.91F, -0.5F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.chops_r = new ModelRendererAnimania(this, 38, 0);
		this.chops_r.setTextureSize(128, 64);
		this.chops_r.addBox(-2.0F, -1.0F, -2.5F, 4, 2, 5);
		this.chops_r.setRotationPoint(-2.0F, 1.6101F, -1.5F);
		this.chops_r.setOffset(-2.0F, 0.5F, -1.1F);
		this.chops_l = new ModelRendererAnimania(this, 50, 0);
		this.chops_l.setTextureSize(128, 64);
		this.chops_l.addBox(-2.0F, -1.0F, -2.5F, 4, 2, 5);
		this.chops_l.setRotationPoint(2.0F, 1.6101F, -1.2F);
		this.chops_l.setOffset(2.0F, 0.5F, -1.1F);
		this.ear_l = new ModelRendererAnimania(this, 4, 4);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_l.setRotationPoint(1.8F, -2.5F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -1.7F);
		this.ear_l2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -2.0F);
		this.ear_l2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_r = new ModelRendererAnimania(this, 4, 4);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_r.setRotationPoint(-1.8F, -2.5F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -1.7F);
		this.ear_r2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -2.0F);
		this.ear_r2.setOffset(0.0F, -0.6F, -0.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 0, 54);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -3.0F, -1.5F, 2, 6, 3);
		this.leg_r1.setRotationPoint(-3.3F, 0.4F, -2.0F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 11, 54);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.leg_r2.setRotationPoint(0.0F, 2.4F, 1.0F);
		this.leg_r2.setOffset(0.12F, 3.0F, -0.61F);
		this.toe_r = new ModelRendererAnimania(this, 11, 60);
		this.toe_r.setTextureSize(128, 64);
		this.toe_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe_r.setRotationPoint(0.0F, 2.4F, 1.0F);
		this.toe_r.setOffset(0.12F, 5.5F, -1.5F);
		this.leg_l2.addChild(this.toe_l);
		this.leg_l1.addChild(this.leg_l2);
		this.body.addChild(this.leg_l1);
		this.tail2.addChild(this.tail3);
		this.tail.addChild(this.tail2);
		this.lower_body.addChild(this.tail);
		this.back_leg_r2.addChild(this.back_toe_r);
		this.back_leg_r1.addChild(this.back_leg_r2);
		this.lower_body.addChild(this.back_leg_r1);
		this.back_leg_l1.addChild(this.back_leg_l2);
		this.back_leg_l1.addChild(this.back_toe_l);
		this.lower_body.addChild(this.back_leg_l1);
		this.body.addChild(this.lower_body);
		this.head_front.addChild(this.nose);
		this.head_front.addChild(this.upper_jaw_detail);
		this.head_base.addChild(this.head_front);
		this.head_base.addChild(this.jaw);
		this.head_base.addChild(this.chops_r);
		this.head_base.addChild(this.chops_l);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.neck1.addChild(this.head_base);
		this.body.addChild(this.neck1);
		this.leg_r1.addChild(this.leg_r2);
		this.leg_r1.addChild(this.toe_r);
		this.body.addChild(this.leg_r1);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.03490658503988659F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.leg_l1.rotateAngleX = 0.0F;
		this.leg_l1.rotateAngleY = 0.0F;
		this.leg_l1.rotateAngleZ = 0.0F;
		this.leg_l2.rotateAngleX = 0.0F;
		this.leg_l2.rotateAngleY = 0.0F;
		this.leg_l2.rotateAngleZ = 0.0F;
		this.toe_l.rotateAngleX = 0.0F;
		this.toe_l.rotateAngleY = 0.0F;
		this.toe_l.rotateAngleZ = 0.0F;
		this.lower_body.rotateAngleX = -0.045553093477052F;
		this.lower_body.rotateAngleY = 0.0F;
		this.lower_body.rotateAngleZ = 0.0F;
		this.tail.rotateAngleX = -0.8651597102135892F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.tail2.rotateAngleX = 0.22758918913080856F;
		this.tail2.rotateAngleY = 0.0F;
		this.tail2.rotateAngleZ = 0.0F;
		this.tail3.rotateAngleX = 0.3186971214141646F;
		this.tail3.rotateAngleY = 0.0F;
		this.tail3.rotateAngleZ = 0.0F;
		this.back_leg_r1.rotateAngleX = 0.0F;
		this.back_leg_r1.rotateAngleY = 0.0F;
		this.back_leg_r1.rotateAngleZ = 0.0F;
		this.back_leg_r2.rotateAngleX = 0.0F;
		this.back_leg_r2.rotateAngleY = 0.0F;
		this.back_leg_r2.rotateAngleZ = 0.0F;
		this.back_toe_r.rotateAngleX = 0.0F;
		this.back_toe_r.rotateAngleY = 0.0F;
		this.back_toe_r.rotateAngleZ = 0.0F;
		this.back_leg_l1.rotateAngleX = 0.0F;
		this.back_leg_l1.rotateAngleY = 0.0F;
		this.back_leg_l1.rotateAngleZ = 0.0F;
		this.back_leg_l2.rotateAngleX = 0.0F;
		this.back_leg_l2.rotateAngleY = 0.0F;
		this.back_leg_l2.rotateAngleZ = 0.0F;
		this.back_toe_l.rotateAngleX = -1.7453292519943296E-6F;
		this.back_toe_l.rotateAngleY = 0.0F;
		this.back_toe_l.rotateAngleZ = 0.0F;
		this.neck1.rotateAngleX = -0.6694891024432529F;
		this.neck1.rotateAngleY = 0.0F;
		this.neck1.rotateAngleZ = 0.0F;
		this.head_base.rotateAngleX = 0.9358699795288874F;
		this.head_base.rotateAngleY = 0.0F;
		this.head_base.rotateAngleZ = 0.0F;
		this.head_front.rotateAngleX = -0.12792390752492439F;
		this.head_front.rotateAngleY = 0.0F;
		this.head_front.rotateAngleZ = 0.0F;
		this.nose.rotateAngleX = -0.091106186954104F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.upper_jaw_detail.rotateAngleX = 0.4820878646688657F;
		this.upper_jaw_detail.rotateAngleY = 0.0F;
		this.upper_jaw_detail.rotateAngleZ = 0.0F;
		this.jaw.rotateAngleX = -0.20524722937602918F;
		this.jaw.rotateAngleY = 0.0F;
		this.jaw.rotateAngleZ = 0.0F;
		this.chops_r.rotateAngleX = -1.3417148811536328F;
		this.chops_r.rotateAngleY = -2.9124867733390016F;
		this.chops_r.rotateAngleZ = -1.9441431977475077F;
		this.chops_l.rotateAngleX = -1.3417148811536328F;
		this.chops_l.rotateAngleY = 2.9124867733390016F;
		this.chops_l.rotateAngleZ = 1.9441431977475077F;
		this.ear_l.rotateAngleX = -1.1021789132929232F;
		this.ear_l.rotateAngleY = -1.6230567205873627F;
		this.ear_l.rotateAngleZ = 1.520239374352377F;
		this.ear_l2.rotateAngleX = 0.20943951023931956F;
		this.ear_l2.rotateAngleY = 0.0F;
		this.ear_l2.rotateAngleZ = 0.0F;
		this.ear_r.rotateAngleX = -1.1021789132929232F;
		this.ear_r.rotateAngleY = 1.6230584659166147F;
		this.ear_r.rotateAngleZ = -1.520239374352377F;
		this.ear_r2.rotateAngleX = 0.20943951023931956F;
		this.ear_r2.rotateAngleY = 0.0F;
		this.ear_r2.rotateAngleZ = 0.0F;
		this.leg_r1.rotateAngleX = 0.0F;
		this.leg_r1.rotateAngleY = 0.0F;
		this.leg_r1.rotateAngleZ = 0.0F;
		this.leg_r2.rotateAngleX = 0.0F;
		this.leg_r2.rotateAngleY = 0.0F;
		this.leg_r2.rotateAngleZ = 0.0F;
		this.toe_r.rotateAngleX = 0.0F;
		this.toe_r.rotateAngleY = 0.0F;
		this.toe_r.rotateAngleZ = 0.0F;
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
			this.body.setRotationPoint(0.0F, 10.0F, -5.0F);

			this.body.rotateAngleX = -0.06981317007977318F;
			this.leg_l1.rotateAngleX = 0.06981317007977318F;
			this.lower_body.rotateAngleX = 0;
			this.tail.rotateAngleX = -0.8730119465183116F;
			this.tail2.rotateAngleX = -0.40761990147402366F;
			this.back_leg_l1.rotateAngleX = 0.06981317007977318F;
			this.back_leg_r1.rotateAngleX = 0.06981317007977318F;
			this.head_base.rotateAngleX = 1.3328817698092894F;
			this.leg_r1.rotateAngleX = 0.06981317007977318F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PoseStandardDogSleeping.INSTANCE));

		if (!dog.getSleeping())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck1.rotateAngleX = headPitch * 0.001453292F - 0.7f;
			this.neck1.rotateAngleY = netHeadYaw * 0.017453292F;
		}
		if (dog.getSleeping())
		{
			sleepingPose.transitionToPose(10, ageInTicks);
		}

		limbSwingAmount *= 0.6;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

			this.back_leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.back_leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		} else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.setRotationPoint(0.0F, 12.0F, -5.0F);

			this.body.rotateAngleX = -0.10049954898833749F;
			this.leg_l1.rotateAngleX = -0.4374388517443468F;
			this.lower_body.rotateAngleX = -0.68513423385813F;
			this.tail.rotateAngleX = 0.7665852593902014F;
			this.tail2.rotateAngleX = 0.458307753610443F;
			this.back_leg_l1.rotateAngleX = -0.43039993887105366F;
			this.back_leg_r1.rotateAngleX = -0.4596010425861708F;
			this.head_base.rotateAngleX = 1.1523920358728F;
			this.leg_r1.rotateAngleX = -0.4434654736514832F;
		}

	}
}

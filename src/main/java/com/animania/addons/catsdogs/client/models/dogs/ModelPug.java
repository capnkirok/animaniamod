package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.client.models.dogs.poses.PosePugSleeping;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelPug extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania tail;
	ModelRendererAnimania neck_base;
	ModelRendererAnimania neck;
	ModelRendererAnimania pug_head;
	ModelRendererAnimania ear1;
	ModelRendererAnimania ear2;
	ModelRendererAnimania nose;
	ModelRendererAnimania back_right;
	ModelRendererAnimania back_left;
	ModelRendererAnimania front_right;
	ModelRendererAnimania front_left;

	public ModelPug()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(64, 64);
		this.body.addBox(-4.5F, -4.5F, -6.5F, 9, 9, 13);
		this.body.setRotationPoint(0.0F, 13.5F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 6, 39);
		this.tail.setTextureSize(64, 64);
		this.tail.addBox(-1.0F, -3.0F, -3.0F, 2, 6, 6);
		this.tail.setRotationPoint(0.0F, -5.0F, 4.0F);
		this.tail.setOffset(0.0F, -0.0F, 2.0F);
		this.neck_base = new ModelRendererAnimania(this, 16, 16);
		this.neck_base.setTextureSize(64, 64);
		this.neck_base.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.neck_base.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.neck_base.setOffset(0.0F, -0.0F, -0.0F);
		this.neck = new ModelRendererAnimania(this, 27, 27);
		this.neck.setTextureSize(64, 64);
		this.neck.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 8);
		this.neck.setRotationPoint(0.7821F, -4.9384F, -3.5F);
		this.neck.setOffset(0.0F, 3.0F, 4.0F);
		this.pug_head = new ModelRendererAnimania(this, 28, 13);
		this.pug_head.setTextureSize(64, 64);
		this.pug_head.addBox(-4.0F, -4.0F, -3.0F, 8, 8, 6);
		this.pug_head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.pug_head.setOffset(0.0F, -0.0F, -0.0F);
		this.ear1 = new ModelRendererAnimania(this, 23, 9);
		this.ear1.setTextureSize(64, 64);
		this.ear1.addBox(-0.5F, -2.0F, -2.0F, 1, 4, 4);
		this.ear1.setRotationPoint(-4.0F, -4.0F, -0.0F);
		this.ear1.setOffset(0.5F, 2.0F, -0.0F);
		this.ear2 = new ModelRendererAnimania(this, 24, 1);
		this.ear2.setTextureSize(64, 64);
		this.ear2.addBox(-0.5F, -2.0F, -2.0F, 1, 4, 4);
		this.ear2.setRotationPoint(4.0F, -4.0F, 1.0E-4F);
		this.ear2.setOffset(0.5F, 2.0F, -0.0F);
		this.nose = new ModelRendererAnimania(this, 37, 9);
		this.nose.setTextureSize(64, 64);
		this.nose.addBox(-2.0F, -1.5F, -0.5F, 4, 3, 1);
		this.nose.setRotationPoint(0.0F, 0.0F, -1.5F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.back_right = new ModelRendererAnimania(this, 3, 27);
		this.back_right.setTextureSize(64, 64);
		this.back_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.back_right.setRotationPoint(-2.0F, 5.0F, 4.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.back_left = new ModelRendererAnimania(this, 3, 0);
		this.back_left.setTextureSize(64, 64);
		this.back_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.back_left.setRotationPoint(2.5F, 5.0F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 3, 18);
		this.front_right.setTextureSize(64, 64);
		this.front_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_right.setRotationPoint(-2.5F, 5.0F, -4.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 3, 9);
		this.front_left.setTextureSize(64, 64);
		this.front_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_left.setRotationPoint(2.5F, 5.0F, -4.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.body.addChild(this.tail);
		this.pug_head.addChild(this.ear1);
		this.pug_head.addChild(this.ear2);
		this.pug_head.addChild(this.nose);
		this.neck.addChild(this.pug_head);
		this.neck_base.addChild(this.neck);
		this.body.addChild(this.neck_base);
		this.body.addChild(this.back_right);
		this.body.addChild(this.back_left);
		this.body.addChild(this.front_right);
		this.body.addChild(this.front_left);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.0F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.tail.rotateAngleX = 0.0F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.neck_base.rotateAngleX = 0.0F;
		this.neck_base.rotateAngleY = 0.0F;
		this.neck_base.rotateAngleZ = -0.15707788735023767F;
		this.neck.rotateAngleX = -0.6022712369611952F;
		this.neck.rotateAngleY = -0.10910053154216555F;
		this.neck.rotateAngleZ = 0.19099487070424345F;
		this.pug_head.rotateAngleX = 0.6108652381980153F;
		this.pug_head.rotateAngleY = 0.0F;
		this.pug_head.rotateAngleZ = 0.0F;
		this.ear1.rotateAngleX = 0.2617993877991494F;
		this.ear1.rotateAngleY = 0.0F;
		this.ear1.rotateAngleZ = -0.17453292519943295F;
		this.ear2.rotateAngleX = -0.17453292519943295F;
		this.ear2.rotateAngleY = 0.0F;
		this.ear2.rotateAngleZ = -0.15707788735023767F;
		this.nose.rotateAngleX = 0.0F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.back_right.rotateAngleX = 0.0F;
		this.back_right.rotateAngleY = 0.0F;
		this.back_right.rotateAngleZ = 0.0F;
		this.back_left.rotateAngleX = 0.0F;
		this.back_left.rotateAngleY = 0.0F;
		this.back_left.rotateAngleZ = 0.0F;
		this.front_right.rotateAngleX = 0.0F;
		this.front_right.rotateAngleY = 0.0F;
		this.front_right.rotateAngleZ = 0.0F;
		this.front_left.rotateAngleX = 0.0F;
		this.front_left.rotateAngleY = 0.0F;
		this.front_left.rotateAngleZ = 0.0F;
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

			this.body.rotateAngleX = 0F;
			this.pug_head.rotateAngleX = 0.6108652381980153F;
			this.back_right.rotateAngleX = 0F;
			this.back_left.rotateAngleX = 0F;
			this.front_right.rotateAngleX = 0F;
			this.front_left.rotateAngleX = 0F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PosePugSleeping.INSTANCE));

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping() && !dog.isSitting())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck.rotateAngleX = headPitch * 0.001453292F - 0.7f;
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
		} else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		this.back_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.back_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.front_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.front_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;

		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.setRotationPoint(0.0F, 17.0F, 10.0F);

			this.body.rotateAngleX = -0.3811921259403255F;
			this.pug_head.rotateAngleX = 0.8168455058598821F;
			this.back_right.rotateAngleX = -1.2237498223405883F;
			this.back_left.rotateAngleX = -1.2199013713399407F;
			this.front_right.rotateAngleX = -0.587254424077036F;
			this.front_left.rotateAngleX = -0.5707523359994298F;
		}

	}
}

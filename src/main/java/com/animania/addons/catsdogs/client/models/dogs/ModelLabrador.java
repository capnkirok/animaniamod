package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.client.models.dogs.poses.PoseLabradorSleeping;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelLabrador extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l2;
	ModelRendererAnimania toes_l;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania tail3;
	ModelRendererAnimania back_leg_l1;
	ModelRendererAnimania back_leg_l2;
	ModelRendererAnimania back_toe_l;
	ModelRendererAnimania back_leg_r1;
	ModelRendererAnimania back_leg_r2;
	ModelRendererAnimania back_toe_r;
	ModelRendererAnimania neck1;
	ModelRendererAnimania head_base;
	ModelRendererAnimania head_front;
	ModelRendererAnimania nose;
	ModelRendererAnimania upper_jaw_detail;
	ModelRendererAnimania jaw;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania toes_r;

	public ModelLabrador()
	{
		this.body = new ModelRendererAnimania(this, 71, 43);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.0F, -5.0F, -5.5F, 8, 10, 11);
		this.body.setRotationPoint(0.0F, 21.0F, -5.0F);
		this.body.setOffset(0.0F, 0.6F, -1.5F);
		this.leg_l1 = new ModelRendererAnimania(this, 0, 54);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -3.5F, -1.5F, 2, 7, 3);
		this.leg_l1.setRotationPoint(3.3F, 0.0F, -2.0F);
		this.leg_l1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 11, 53);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
		this.leg_l2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.leg_l2.setOffset(-0.01F, 3.5F, -0.6F);
		this.toes_l = new ModelRendererAnimania(this, 12, 61);
		this.toes_l.setTextureSize(128, 64);
		this.toes_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_l.setRotationPoint(0.0F, -3.5F, 0.6F);
		this.toes_l.setOffset(0.0F, 7.5F, -1.5F);
		this.lower_body = new ModelRendererAnimania(this, 29, 24);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.0F, -4.5F, -6.5F, 6, 9, 13);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 6.0F);
		this.lower_body.setOffset(0.0F, 1.0F, 5.0F);
		this.tail = new ModelRendererAnimania(this, 52, 14);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -1.0F, -2.5F, 3, 2, 5);
		this.tail.setRotationPoint(0.0F, -4.6F, 7.0F);
		this.tail.setOffset(0.0F, 2.0F, 2.0F);
		this.tail2 = new ModelRendererAnimania(this, 70, 26);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.5F, -1.5F, -3.0F, 3, 3, 6);
		this.tail2.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.tail2.setOffset(0.0F, 1.5F, 2.5F);
		this.tail3 = new ModelRendererAnimania(this, 73, 19);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3);
		this.tail3.setRotationPoint(0.0F, -2.0F, 2.0F);
		this.tail3.setOffset(0.0F, 1.5F, 2.5F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 20, 52);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -4.0F, -2.0F, 2, 8, 4);
		this.back_leg_l1.setRotationPoint(2.5F, -2.5F, 3.0F);
		this.back_leg_l1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 10, 39);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -5.5F, -1.0F, 2, 11, 2);
		this.back_leg_l2.setRotationPoint(0.0F, 2.5F, 0.5F);
		this.back_leg_l2.setOffset(-0.01F, 3.5F, 0.7F);
		this.back_toe_l = new ModelRendererAnimania(this, 14, 50);
		this.back_toe_l.setTextureSize(128, 64);
		this.back_toe_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_l.setRotationPoint(0.0099F, -3.5F, -0.6999F);
		this.back_toe_l.setOffset(-0.01F, 8.5F, -0.4F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 20, 52);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -4.0F, -2.0F, 2, 8, 4);
		this.back_leg_r1.setRotationPoint(-2.5F, -2.5F, 3.0F);
		this.back_leg_r1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 10, 39);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -5.5F, -1.0F, 2, 11, 2);
		this.back_leg_r2.setRotationPoint(0.0F, 2.5F, 0.5F);
		this.back_leg_r2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_toe_r = new ModelRendererAnimania(this, 14, 50);
		this.back_toe_r.setTextureSize(128, 64);
		this.back_toe_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_r.setRotationPoint(-0.01F, -3.5F, -0.6999F);
		this.back_toe_r.setOffset(0.01F, 8.5F, -0.4F);
		this.neck1 = new ModelRendererAnimania(this, 5, 18);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -3.5F, -5.0F, 5, 7, 10);
		this.neck1.setRotationPoint(0.0F, -2.0F, -4.0F);
		this.neck1.setOffset(0.0F, 2.0F, -1.0F);
		this.head_base = new ModelRendererAnimania(this, 101, 27);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.5F, -3.0F, -3.0F, 7, 6, 6);
		this.head_base.setRotationPoint(0.0F, -0.3F, -5.0F);
		this.head_base.setOffset(0.0F, 0.49F, -1.0F);
		this.head_front = new ModelRendererAnimania(this, 106, 3);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-2.0F, -1.5F, -3.5F, 4, 3, 7);
		this.head_front.setRotationPoint(0.0F, 1.0F, -1.0F);
		this.head_front.setOffset(0.0F, -0.4F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 98, 17);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.6F, -2.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.upper_jaw_detail = new ModelRendererAnimania(this, 107, 17);
		this.upper_jaw_detail.setTextureSize(128, 64);
		this.upper_jaw_detail.addBox(-1.5F, -1.0F, -2.5F, 3, 2, 5);
		this.upper_jaw_detail.setRotationPoint(0.0F, -2.9638F, 4.0F);
		this.upper_jaw_detail.setOffset(0.0F, 1.0F, -5.0F);
		this.jaw = new ModelRendererAnimania(this, 86, 4);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.5F, -0.5F, -3.0F, 3, 1, 6);
		this.jaw.setRotationPoint(0.0F, 2.0F, -1.0F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.ear_l = new ModelRendererAnimania(this, 75, 1);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_l.setRotationPoint(3.0F, -2.0F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -1.7F);
		this.ear_l2 = new ModelRendererAnimania(this, 61, 2);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -1.0F);
		this.ear_l2.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_r = new ModelRendererAnimania(this, 75, 1);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_r.setRotationPoint(-3.0F, -2.0F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -1.7F);
		this.ear_r2 = new ModelRendererAnimania(this, 61, 2);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -1.0F);
		this.ear_r2.setOffset(0.0F, -0.6F, -1.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 0, 54);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -3.5F, -1.5F, 2, 7, 3);
		this.leg_r1.setRotationPoint(-3.3F, 0.0F, -2.0F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 11, 53);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
		this.leg_r2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.leg_r2.setOffset(0.01F, 3.5F, -0.6F);
		this.toes_r = new ModelRendererAnimania(this, 12, 61);
		this.toes_r.setTextureSize(128, 64);
		this.toes_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r.setRotationPoint(0.0F, -3.5F, 0.6F);
		this.toes_r.setOffset(0.0F, 7.5F, -1.5F);
		this.leg_l2.addChild(this.toes_l);
		this.leg_l1.addChild(this.leg_l2);
		this.body.addChild(this.leg_l1);
		this.tail2.addChild(this.tail3);
		this.tail.addChild(this.tail2);
		this.lower_body.addChild(this.tail);
		this.back_leg_l2.addChild(this.back_toe_l);
		this.back_leg_l1.addChild(this.back_leg_l2);
		this.lower_body.addChild(this.back_leg_l1);
		this.back_leg_r2.addChild(this.back_toe_r);
		this.back_leg_r1.addChild(this.back_leg_r2);
		this.lower_body.addChild(this.back_leg_r1);
		this.body.addChild(this.lower_body);
		this.head_front.addChild(this.nose);
		this.head_front.addChild(this.upper_jaw_detail);
		this.head_base.addChild(this.head_front);
		this.head_base.addChild(this.jaw);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.neck1.addChild(this.head_base);
		this.body.addChild(this.neck1);
		this.leg_r2.addChild(this.toes_r);
		this.leg_r1.addChild(this.leg_r2);
		this.body.addChild(this.leg_r1);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = -0.03490658503988659F;
		this.body.rotateAngleY = -0.0F;
		this.body.rotateAngleZ = -0.0F;
		this.leg_l1.rotateAngleX = 0.03490658503988659F;
		this.leg_l1.rotateAngleY = -0.0F;
		this.leg_l1.rotateAngleZ = -0.0F;
		this.leg_l2.rotateAngleX = 0.0F;
		this.leg_l2.rotateAngleY = -0.0F;
		this.leg_l2.rotateAngleZ = -0.0F;
		this.toes_l.rotateAngleX = 0.0F;
		this.toes_l.rotateAngleY = -0.0F;
		this.toes_l.rotateAngleZ = -0.0F;
		this.lower_body.rotateAngleX = 0.0F;
		this.lower_body.rotateAngleY = -0.0F;
		this.lower_body.rotateAngleZ = -0.0F;
		this.tail.rotateAngleX = -0.8730119465183116F;
		this.tail.rotateAngleY = -0.0F;
		this.tail.rotateAngleZ = -0.0F;
		this.tail2.rotateAngleX = -0.40761990147402366F;
		this.tail2.rotateAngleY = -0.0F;
		this.tail2.rotateAngleZ = -0.0F;
		this.tail3.rotateAngleX = 0.0F;
		this.tail3.rotateAngleY = -0.0F;
		this.tail3.rotateAngleZ = -0.0F;
		this.back_leg_l1.rotateAngleX = 0.03490658503988659F;
		this.back_leg_l1.rotateAngleY = -0.0F;
		this.back_leg_l1.rotateAngleZ = -0.0F;
		this.back_leg_l2.rotateAngleX = 0.0F;
		this.back_leg_l2.rotateAngleY = -0.0F;
		this.back_leg_l2.rotateAngleZ = -0.0F;
		this.back_toe_l.rotateAngleX = 0.0F;
		this.back_toe_l.rotateAngleY = -0.0F;
		this.back_toe_l.rotateAngleZ = -0.0F;
		this.back_leg_r1.rotateAngleX = 0.03490658503988659F;
		this.back_leg_r1.rotateAngleY = -0.0F;
		this.back_leg_r1.rotateAngleZ = -0.0F;
		this.back_leg_r2.rotateAngleX = 0.0F;
		this.back_leg_r2.rotateAngleY = -0.0F;
		this.back_leg_r2.rotateAngleZ = -0.0F;
		this.back_toe_r.rotateAngleX = 0.0F;
		this.back_toe_r.rotateAngleY = -0.0F;
		this.back_toe_r.rotateAngleZ = -0.0F;
		this.neck1.rotateAngleX = -0.8754030475935439F;
		this.neck1.rotateAngleY = -0.0F;
		this.neck1.rotateAngleZ = -0.0F;
		this.head_base.rotateAngleX = 1.3546861681544549F;
		this.head_base.rotateAngleY = -0.0F;
		this.head_base.rotateAngleZ = -0.0F;
		this.head_front.rotateAngleX = -0.12063017658084008F;
		this.head_front.rotateAngleY = -0.0F;
		this.head_front.rotateAngleZ = -0.0F;
		this.nose.rotateAngleX = -0.091106186954104F;
		this.nose.rotateAngleY = -0.0F;
		this.nose.rotateAngleZ = -0.0F;
		this.upper_jaw_detail.rotateAngleX = 0.2039696484F;
		this.upper_jaw_detail.rotateAngleY = -0.0F;
		this.upper_jaw_detail.rotateAngleZ = -0.0F;
		this.jaw.rotateAngleX = -0.08244411787645614F;
		this.jaw.rotateAngleY = -0.0F;
		this.jaw.rotateAngleZ = -0.0F;
		this.ear_l.rotateAngleX = 1.2217304763960306F;
		this.ear_l.rotateAngleY = -1.1608184855014287F;
		this.ear_l.rotateAngleZ = 0.6063273821428301F;
		this.ear_l2.rotateAngleX = 0.3490658503988659F;
		this.ear_l2.rotateAngleY = -0.0F;
		this.ear_l2.rotateAngleZ = -0.0F;
		this.ear_r.rotateAngleX = 1.2217304763960306F;
		this.ear_r.rotateAngleY = 1.4166854991730493F;
		this.ear_r.rotateAngleZ = -0.3787364476827695F;
		this.ear_r2.rotateAngleX = 0.3490658503988659F;
		this.ear_r2.rotateAngleY = -0.0F;
		this.ear_r2.rotateAngleZ = -0.0F;
		this.leg_r1.rotateAngleX = 0.03490658503988659F;
		this.leg_r1.rotateAngleY = -0.0F;
		this.leg_r1.rotateAngleZ = -0.0F;
		this.leg_r2.rotateAngleX = 0.0F;
		this.leg_r2.rotateAngleY = -0.0F;
		this.leg_r2.rotateAngleZ = -0.0F;
		this.toes_r.rotateAngleX = 0.0F;
		this.toes_r.rotateAngleY = -0.0F;
		this.toes_r.rotateAngleZ = -0.0F;
	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
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
		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PoseLabradorSleeping.INSTANCE));

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck1.rotateAngleX = headPitch * 0.001453292F - 1f;
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

		if (sitting && !dog.getSleeping())
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

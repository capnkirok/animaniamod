package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.common.entity.dogs.EntityAnimaniaDog;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelPomeranian extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania back_right;
	ModelRendererAnimania tail;
	ModelRendererAnimania back_left;
	ModelRendererAnimania upperbody;
	ModelRendererAnimania front_right;
	ModelRendererAnimania front_left;
	ModelRendererAnimania neck_base;
	ModelRendererAnimania neck;
	ModelRendererAnimania pug_head;
	ModelRendererAnimania snout;
	ModelRendererAnimania nose;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l1;

	public ModelPomeranian()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(64, 128);
		this.body.addBox(-4.5F, -3.5F, -6.5F, 9, 7, 13);
		this.body.setRotationPoint(0.0F, 13.5F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.back_right = new ModelRendererAnimania(this, 3, 27);
		this.back_right.setTextureSize(64, 128);
		this.back_right.addBox(-1.5F, -4.0F, -1.5F, 3, 8, 3);
		this.back_right.setRotationPoint(-2.0F, 4.0F, 4.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 7, 40);
		this.tail.setTextureSize(64, 128);
		this.tail.addBox(-2.0F, -2.0F, -2.5F, 4, 4, 5);
		this.tail.setRotationPoint(0.0F, -5.0F, 4.0F);
		this.tail.setOffset(0.0F, -0.0F, 2.0F);
		this.back_left = new ModelRendererAnimania(this, 3, 27);
		this.back_left.setTextureSize(64, 128);
		this.back_left.addBox(-1.5F, -4.0F, -1.5F, 3, 8, 3);
		this.back_left.setRotationPoint(2.0F, 4.0F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.upperbody = new ModelRendererAnimania(this, 25, 62);
		this.upperbody.setTextureSize(64, 128);
		this.upperbody.addBox(-5.0F, -4.5F, -3.5F, 10, 9, 7);
		this.upperbody.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.upperbody.setOffset(0.0F, -0.0F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 3, 18);
		this.front_right.setTextureSize(64, 128);
		this.front_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_right.setRotationPoint(-2.5F, 5.0F, -0.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 3, 9);
		this.front_left.setTextureSize(64, 128);
		this.front_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_left.setRotationPoint(2.5F, 5.0F, -0.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.neck_base = new ModelRendererAnimania(this, 16, 16);
		this.neck_base.setTextureSize(64, 128);
		this.neck_base.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.neck_base.setRotationPoint(0.0F, -6.0F, -3.5F);
		this.neck_base.setOffset(0.0F, -0.0F, -0.0F);
		this.neck = new ModelRendererAnimania(this, 29, 27);
		this.neck.setTextureSize(64, 128);
		this.neck.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 8);
		this.neck.setRotationPoint(0.0F, 0.0F, -0.0F);
		this.neck.setOffset(0.0F, 3.0F, 4.0F);
		this.pug_head = new ModelRendererAnimania(this, 25, 8);
		this.pug_head.setTextureSize(64, 128);
		this.pug_head.addBox(-5.0F, -5.0F, -4.0F, 10, 10, 8);
		this.pug_head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.pug_head.setOffset(0.0F, -2.0F, -1.0F);
		this.snout = new ModelRendererAnimania(this, 36, 2);
		this.snout.setTextureSize(64, 128);
		this.snout.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2);
		this.snout.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.snout.setOffset(0.0F, 1.5F, -2.0F);
		this.nose = new ModelRendererAnimania(this, 39, 0);
		this.nose.setTextureSize(64, 128);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.ear_l = new ModelRendererAnimania(this, 52, 0);
		this.ear_l.setTextureSize(64, 128);
		this.ear_l.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.ear_l.setRotationPoint(0.0F, 2.0001F, 1.0F);
		this.ear_l.setOffset(-2.0F, -8.0F, -1.0F);
		this.ear_l1 = new ModelRendererAnimania(this, 52, 0);
		this.ear_l1.setTextureSize(64, 128);
		this.ear_l1.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.ear_l1.setRotationPoint(0.0F, 2.0001F, 1.0F);
		this.ear_l1.setOffset(2.0F, -8.0F, -1.0F);
		this.body.addChild(this.back_right);
		this.body.addChild(this.tail);
		this.body.addChild(this.back_left);
		this.upperbody.addChild(this.front_right);
		this.upperbody.addChild(this.front_left);
		this.pug_head.addChild(this.snout);
		this.pug_head.addChild(this.nose);
		this.pug_head.addChild(this.ear_l);
		this.pug_head.addChild(this.ear_l1);
		this.neck.addChild(this.pug_head);
		this.neck_base.addChild(this.neck);
		this.upperbody.addChild(this.neck_base);
		this.body.addChild(this.upperbody);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.neck.rotateAngleX = -0.6108652381980153F;
		this.pug_head.rotateAngleX = 0.6108652381980153F;
		this.ear_l.rotateAngleZ = -0.23574336739612609F;
		this.ear_l1.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_l1.rotateAngleZ = 0.23574336739612609F;
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
			this.body.setRotationPoint(0.0F, 13.5F, 10.0F);

			this.body.rotateAngleX = 0F;
			this.back_right.rotateAngleX = 0F;
			this.back_left.rotateAngleX = 0F;
			this.upperbody.rotateAngleX = 0F;
			this.front_right.rotateAngleX = 0F;
			this.front_left.rotateAngleX = 0F;
			this.pug_head.rotateAngleX = 0.6108652381980153F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		limbSwingAmount *= 0.6;

		this.neck_base.rotateAngleX = headPitch * 0.001453292F;
		this.neck_base.rotateAngleY = netHeadYaw * 0.017453292F;

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
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
			this.body.setRotationPoint(0.0F, 18.0F, 10.0F);

			this.body.rotateAngleX = -0.4521991012284628F;
			this.back_right.rotateAngleX = -1.096649710222605F;
			this.back_left.rotateAngleX = -1.0346835404597985F;
			this.upperbody.rotateAngleX = 0.09062622140980556F;
			this.front_right.rotateAngleX = -0.5693700352318501F;
			this.front_left.rotateAngleX = -0.560472346705183F;
			this.pug_head.rotateAngleX = 0.8466574748131973F;
		}

	}
}

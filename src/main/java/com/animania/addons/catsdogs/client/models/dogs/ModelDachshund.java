package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelDachshund extends ModelBase
{
	ModelRendererAnimania wolf_head;
	ModelRendererAnimania body;
	ModelRendererAnimania leg1;
	ModelRendererAnimania leg2;
	ModelRendererAnimania leg3;
	ModelRendererAnimania leg4;
	ModelRendererAnimania tail;
	ModelRendererAnimania ear1;
	ModelRendererAnimania ear2;
	ModelRendererAnimania nose;
	ModelRendererAnimania toe1;
	ModelRendererAnimania toe2;
	ModelRendererAnimania toe21;
	ModelRendererAnimania toe211;
	ModelRendererAnimania head;

	public ModelDachshund()
	{
		this.body = new ModelRendererAnimania(this, 25, 0);
		this.body.setTextureSize(64, 32);
		this.body.addBox(-2.0F, -6.0F, -2.0F, 4, 12, 4);
		this.body.setRotationPoint(0.0F, 19.0F, -3.0F);
		this.body.setOffset(-2.0F, 4.0F, -1.0F);
		this.leg1 = new ModelRendererAnimania(this, 0, 18);
		this.leg1.setTextureSize(64, 32);
		this.leg1.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg1.setRotationPoint(-1.5F, 5.0F, 1.0E-4F);
		this.leg1.setOffset(0.0F, 2.0F, -0.0F);
		this.toe2 = new ModelRendererAnimania(this, 0, 22);
		this.toe2.setTextureSize(64, 32);
		this.toe2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe2.setRotationPoint(-1.0E-4F, -2.0F, 1.0E-4F);
		this.toe2.setOffset(0.0F, 3.5F, -1.0F);
		this.leg2 = new ModelRendererAnimania(this, 0, 18);
		this.leg2.setTextureSize(64, 32);
		this.leg2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg2.setRotationPoint(1.5F, 5.0F, 1.0E-4F);
		this.leg2.setOffset(0.0F, 2.0F, -0.0F);
		this.toe21 = new ModelRendererAnimania(this, 0, 22);
		this.toe21.setTextureSize(64, 32);
		this.toe21.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe21.setRotationPoint(0.0F, -2.0F, 1.0E-4F);
		this.toe21.setOffset(0.0F, 3.5F, -1.0F);
		this.leg3 = new ModelRendererAnimania(this, 0, 18);
		this.leg3.setTextureSize(64, 32);
		this.leg3.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg3.setRotationPoint(-1.5F, -5.0F, -0.0F);
		this.leg3.setOffset(0.0F, 2.0F, -0.0F);
		this.toe1 = new ModelRendererAnimania(this, 0, 22);
		this.toe1.setTextureSize(64, 32);
		this.toe1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe1.setRotationPoint(-1.0E-4F, -2.0F, -0.0F);
		this.toe1.setOffset(0.0F, 3.5F, -1.0F);
		this.leg4 = new ModelRendererAnimania(this, 0, 18);
		this.leg4.setTextureSize(64, 32);
		this.leg4.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg4.setRotationPoint(1.5F, -5.0F, -0.0F);
		this.leg4.setOffset(0.0F, 2.0F, -0.0F);
		this.toe211 = new ModelRendererAnimania(this, 0, 22);
		this.toe211.setTextureSize(64, 32);
		this.toe211.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe211.setRotationPoint(0.0F, -2.0F, 1.0E-4F);
		this.toe211.setOffset(0.0F, 3.5F, -1.0F);
		this.head = new ModelRendererAnimania(this, 16, 16);
		this.head.setTextureSize(64, 32);
		this.head.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.head.setRotationPoint(0.0F, -5.0F, 2.0F);
		this.head.setOffset(0.0F, -0.0F, -0.0F);
		this.wolf_head = new ModelRendererAnimania(this, 0, 0);
		this.wolf_head.setTextureSize(64, 32);
		this.wolf_head.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
		this.wolf_head.setRotationPoint(1.0F, -1.9999F, -0.5F);
		this.wolf_head.setOffset(-1.0F, -1.0F, -0.0F);
		this.ear1 = new ModelRendererAnimania(this, 16, 14);
		this.ear1.setTextureSize(64, 32);
		this.ear1.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear1.setRotationPoint(-2.0F, -2.0F, -0.0F);
		this.ear1.setOffset(-0.5F, 1.5F, -0.0F);
		this.ear2 = new ModelRendererAnimania(this, 16, 14);
		this.ear2.setTextureSize(64, 32);
		this.ear2.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear2.setRotationPoint(2.0F, -2.0F, -0.0F);
		this.ear2.setOffset(0.5F, 1.5F, -0.0F);
		this.nose = new ModelRendererAnimania(this, 0, 10);
		this.nose.setTextureSize(64, 32);
		this.nose.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3);
		this.nose.setRotationPoint(1.0F, 0.0F, 1.0F);
		this.nose.setOffset(-1.0F, 1.0F, -3.5F);
		this.tail = new ModelRendererAnimania(this, 9, 18);
		this.tail.setTextureSize(64, 32);
		this.tail.addBox(-0.5F, -2.5F, -0.5F, 1, 5, 1);
		this.tail.setRotationPoint(-0.5F, 5.0F, 2.0001F);
		this.tail.setOffset(0.5F, 2.5F, -0.5F);
		this.leg1.addChild(this.toe2);
		this.body.addChild(this.leg1);
		this.leg2.addChild(this.toe21);
		this.body.addChild(this.leg2);
		this.leg3.addChild(this.toe1);
		this.body.addChild(this.leg3);
		this.leg4.addChild(this.toe211);
		this.body.addChild(this.leg4);
		this.wolf_head.addChild(this.ear1);
		this.wolf_head.addChild(this.ear2);
		this.wolf_head.addChild(this.nose);
		this.head.addChild(this.wolf_head);
		this.body.addChild(this.head);
		this.body.addChild(this.tail);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.body.rotateAngleX = 1.5707963267948966F;
		this.leg1.rotateAngleX = -1.5707963267948966F;
		this.leg2.rotateAngleX = -1.5707963267948966F;
		this.leg3.rotateAngleX = -1.5707963267948966F;
		this.leg4.rotateAngleX = -1.5707963267948966F;
		this.toe211.rotateAngleX = -1.7453292519943296E-6F;
		this.wolf_head.rotateAngleX = -1.5707963267948966F;
		this.ear1.rotateAngleZ = 0.17453292519943295F;
		this.ear2.rotateAngleZ = -0.17453292519943295F;
		this.tail.rotateAngleX = 1.0092383852949731F;
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
			this.body.rotateAngleX = 1.5707963267948966F;
			this.leg1.rotateAngleX = -1.5707963267948966F;
			this.leg2.rotateAngleX = -1.5707963267948966F;
			this.leg3.rotateAngleX = -1.5707963267948966F;
			this.leg4.rotateAngleX = -1.5707963267948966F;
			this.toe211.rotateAngleX = 0F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		limbSwingAmount *= 0.6;

		this.head.rotateAngleX = headPitch * 0.001453292F ;
		this.head.rotateAngleZ = netHeadYaw * 0.017453292F;

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount -1.5707963267948966f;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount -1.5707963267948966f;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount -1.5707963267948966f;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount -1.5707963267948966f;

		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.rotateAngleX = 1.3789724246959558F;
			this.leg1.rotateAngleX = -0.5787459439735637F;
			this.leg1.rotateAngleY = -3.141592653589793F;
			this.leg1.rotateAngleZ = -3.141592653589793F;
			this.leg2.rotateAngleX = -0.6416929887759912F;
			this.leg2.rotateAngleY = -3.141592653589793F;
			this.leg2.rotateAngleZ = -3.141592653589793F;
			this.leg3.rotateAngleX = -1.301659574820363F;
			this.leg3.rotateAngleY = -3.141592653589793F;
			this.leg3.rotateAngleZ = -3.141592653589793F;
			this.leg4.rotateAngleX = -1.2743696066361798F;
			this.leg4.rotateAngleY = -3.141592653589793F;
			this.leg4.rotateAngleZ = -3.141592653589793F;
			this.toe211.rotateAngleX = -1.7453292519943296E-6F;
		}

	}
}

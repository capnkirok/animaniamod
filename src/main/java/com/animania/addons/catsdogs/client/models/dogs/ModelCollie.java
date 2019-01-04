package com.animania.addons.catsdogs.client.models.dogs;

import net.minecraft.client.model.ModelBase;

import com.animania.addons.catsdogs.common.entity.dogs.EntityAnimaniaDog;
import com.animania.client.models.render.ModelRendererAnimania;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelCollie extends ModelBase
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
	ModelRendererAnimania ear_l1;
	ModelRendererAnimania ear_l21;
	ModelRendererAnimania cheek;
	ModelRendererAnimania cheek1;
	ModelRendererAnimania cheek11;
	ModelRendererAnimania cheek111;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania toes_r;

	public ModelCollie()
	{
		this.body = new ModelRendererAnimania(this, 71, 43);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.0F, -5.0F, -5.5F, 8, 10, 11);
		this.body.setRotationPoint(0.0F, 10.0F, -5.0F);
		this.body.setOffset(0.0F, 0.6F, -1.5F);
		this.leg_l1 = new ModelRendererAnimania(this, 37, 53);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.leg_l1.setRotationPoint(3.3F, 0.0F, -2.0F);
		this.leg_l1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 12, 52);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_l2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.leg_l2.setOffset(-0.01F, 3.5F, -0.6F);
		this.toes_l = new ModelRendererAnimania(this, 12, 61);
		this.toes_l.setTextureSize(128, 64);
		this.toes_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_l.setRotationPoint(0.0F, -3.5F, -0.0F);
		this.toes_l.setOffset(0.0F, 7.5F, -1.5F);
		this.lower_body = new ModelRendererAnimania(this, 29, 24);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.0F, -4.5F, -6.5F, 6, 9, 13);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 6.0F);
		this.lower_body.setOffset(0.0F, 0.8F, 5.0F);
		this.tail = new ModelRendererAnimania(this, 51, 13);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.5F, -2.0F, -3.0F, 3, 4, 6);
		this.tail.setRotationPoint(0.0F, -3.8F, 6.0F);
		this.tail.setOffset(0.0F, 2.0F, 2.0F);
		this.tail2 = new ModelRendererAnimania(this, 68, 24);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 8);
		this.tail2.setRotationPoint(0.0F, -1.0F, 4.0F);
		this.tail2.setOffset(0.0F, 1.0F, 2.5F);
		this.tail3 = new ModelRendererAnimania(this, 66, 10);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail3.setOffset(0.0F, -0.0F, 2.0F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 21, 51);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_l1.setRotationPoint(2.5F, -2.5F, 3.0F);
		this.back_leg_l1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 0, 38);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -5.5F, -1.5F, 2, 11, 3);
		this.back_leg_l2.setRotationPoint(0.0F, 2.5F, 0.5F);
		this.back_leg_l2.setOffset(-0.01F, 3.5F, 0.7F);
		this.back_toe_l = new ModelRendererAnimania(this, 5, 49);
		this.back_toe_l.setTextureSize(128, 64);
		this.back_toe_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_l.setRotationPoint(0.0099F, -3.5F, -1.0F);
		this.back_toe_l.setOffset(-0.01F, 8.5F, -0.4F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 21, 51);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -4.0F, -2.5F, 2, 8, 5);
		this.back_leg_r1.setRotationPoint(-2.5F, -2.5F, 3.0F);
		this.back_leg_r1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 0, 38);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -5.5F, -1.5F, 2, 11, 3);
		this.back_leg_r2.setRotationPoint(0.0F, 2.5F, 0.5F);
		this.back_leg_r2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_toe_r = new ModelRendererAnimania(this, 5, 49);
		this.back_toe_r.setTextureSize(128, 64);
		this.back_toe_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_r.setRotationPoint(0.0F, -3.5F, -1.0F);
		this.back_toe_r.setOffset(-0.01F, 8.5F, -0.4F);
		this.neck1 = new ModelRendererAnimania(this, 5, 18);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -4.0F, -5.0F, 5, 8, 10);
		this.neck1.setRotationPoint(0.0F, -2.0F, -3.0F);
		this.neck1.setOffset(0.0F, 2.0F, -2.0F);
		this.head_base = new ModelRendererAnimania(this, 101, 27);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.5F, -3.0F, -3.0F, 7, 6, 6);
		this.head_base.setRotationPoint(0.0F, -1.0F, -5.0F);
		this.head_base.setOffset(0.0F, 0.49F, -1.0F);
		this.head_front = new ModelRendererAnimania(this, 106, 3);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-1.5F, -1.0F, -3.5F, 3, 2, 7);
		this.head_front.setRotationPoint(0.0F, 1.2F, -1.0F);
		this.head_front.setOffset(0.0F, -0.4F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 98, 17);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.6F, -2.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.upper_jaw_detail = new ModelRendererAnimania(this, 107, 17);
		this.upper_jaw_detail.setTextureSize(128, 64);
		this.upper_jaw_detail.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5);
		this.upper_jaw_detail.setRotationPoint(0.0F, -2.9638F, 3.5615F);
		this.upper_jaw_detail.setOffset(0.0F, 1.0F, -5.0F);
		this.jaw = new ModelRendererAnimania(this, 86, 4);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 6);
		this.jaw.setRotationPoint(0.0F, 2.5F, -1.0F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.ear_l = new ModelRendererAnimania(this, 76, 2);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_l.setRotationPoint(2.0F, -1.7F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -1.7F);
		this.ear_l2 = new ModelRendererAnimania(this, 61, 2);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_l2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_l2.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_l1 = new ModelRendererAnimania(this, 76, 2);
		this.ear_l1.setTextureSize(128, 64);
		this.ear_l1.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_l1.setRotationPoint(-2.0F, -1.7F, 0.7F);
		this.ear_l1.setOffset(-0.5F, -0.6F, -1.7F);
		this.ear_l21 = new ModelRendererAnimania(this, 61, 2);
		this.ear_l21.setTextureSize(128, 64);
		this.ear_l21.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_l21.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_l21.setOffset(0.0F, -0.6F, -1.0F);
		this.cheek = new ModelRendererAnimania(this, 105, 43);
		this.cheek.setTextureSize(128, 64);
		this.cheek.addBox(-1.5F, -2.5F, -1.0F, 3, 5, 2);
		this.cheek.setRotationPoint(-1.0F, 0.0F, 2.0F);
		this.cheek.setOffset(-2.0F, 0.49F, -1.0F);
		this.cheek1 = new ModelRendererAnimania(this, 105, 43);
		this.cheek1.setTextureSize(128, 64);
		this.cheek1.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2);
		this.cheek1.setRotationPoint(0.0F, -0.49F, 1.0F);
		this.cheek1.setOffset(-2.0F, 0.49F, -1.0F);
		this.cheek11 = new ModelRendererAnimania(this, 105, 43);
		this.cheek11.setTextureSize(128, 64);
		this.cheek11.addBox(-1.5F, -2.5F, -1.0F, 3, 5, 2);
		this.cheek11.setRotationPoint(1.0F, 0.0F, 2.0F);
		this.cheek11.setOffset(2.0F, 0.49F, -1.0F);
		this.cheek111 = new ModelRendererAnimania(this, 105, 43);
		this.cheek111.setTextureSize(128, 64);
		this.cheek111.addBox(-0.5F, -2.0F, -1.0F, 1, 4, 2);
		this.cheek111.setRotationPoint(0.0F, -0.49F, 1.0F);
		this.cheek111.setOffset(2.0F, 0.49F, -1.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 37, 53);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.leg_r1.setRotationPoint(-3.3F, 0.0F, -2.0F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 11, 52);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_r2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.leg_r2.setOffset(0.12F, 3.5F, -0.6F);
		this.toes_r = new ModelRendererAnimania(this, 12, 61);
		this.toes_r.setTextureSize(128, 64);
		this.toes_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r.setRotationPoint(-0.12F, -3.5F, -0.0F);
		this.toes_r.setOffset(0.12F, 7.5F, -1.5F);
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
		this.ear_l1.addChild(this.ear_l21);
		this.head_base.addChild(this.ear_l1);
		this.cheek.addChild(this.cheek1);
		this.head_base.addChild(this.cheek);
		this.cheek11.addChild(this.cheek111);
		this.head_base.addChild(this.cheek11);
		this.neck1.addChild(this.head_base);
		this.body.addChild(this.neck1);
		this.leg_r2.addChild(this.toes_r);
		this.leg_r1.addChild(this.leg_r2);
		this.body.addChild(this.leg_r1);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.body.rotateAngleX = -0.06981317007977318F;
		this.leg_l1.rotateAngleX = 0.06981317007977318F;
		this.tail.rotateAngleX = -0.6834220658619237F;
		this.tail2.rotateAngleX = -0.40761990147402366F;
		this.tail3.rotateAngleX = 0.08067609934418589F;
		this.back_leg_l1.rotateAngleX = 0.06981317007977318F;
		this.back_leg_r1.rotateAngleX = 0.06981317007977318F;
		this.neck1.rotateAngleX = -0.8180236031049783F;
		this.head_base.rotateAngleX = 1.1907142302588394F;
		this.head_front.rotateAngleX = -0.17280330391070658F;
		this.nose.rotateAngleX = -0.091106186954104F;
		this.upper_jaw_detail.rotateAngleX = -6.079215658816017F;
		this.jaw.rotateAngleX = -0.2525770680316114F;
		this.ear_l.rotateAngleX = -1.293639786907449F;
		this.ear_l.rotateAngleY = -2.588148747782391F;
		this.ear_l.rotateAngleZ = 2.3736477827122884F;
		this.ear_l2.rotateAngleX = 0.8103877876275032F;
		this.ear_l1.rotateAngleX = -1.0111390488503949F;
		this.ear_l1.rotateAngleY = 2.130956259563973F;
		this.ear_l1.rotateAngleZ = -1.9506096426261468F;
		this.ear_l21.rotateAngleX = 1.054184104192331F;
		this.cheek.rotateAngleX = -0.36139136557644985F;
		this.cheek.rotateAngleY = -0.2775701829201702F;
		this.cheek11.rotateAngleX = -0.36139136557644985F;
		this.cheek11.rotateAngleY = 0.2775701829201702F;
		this.cheek11.rotateAngleZ = 1.7453292519943296E-6F;
		this.cheek111.rotateAngleY = 1.7453292519943296E-6F;
		this.cheek111.rotateAngleZ = 1.7453292519943296E-6F;
		this.leg_r1.rotateAngleX = 0.06981317007977318F;
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
		limbSwingAmount *= 0.6;
		
		this.neck1.rotateAngleX = headPitch * 0.001453292F - 0.7f;
		this.neck1.rotateAngleY = netHeadYaw * 0.017453292F;

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		this.back_leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.back_leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
		this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;

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

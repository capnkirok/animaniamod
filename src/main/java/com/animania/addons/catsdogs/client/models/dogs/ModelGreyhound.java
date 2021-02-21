package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.addons.catsdogs.client.models.dogs.poses.PoseGreyhoundSleeping;
import com.animania.addons.catsdogs.common.entity.canids.EntityAnimaniaDog;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelGreyhound extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l2;
	ModelRendererAnimania toes_l;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania toes_r;
	ModelRendererAnimania lower_body;
	ModelRendererAnimania back_leg_l1;
	ModelRendererAnimania back_leg_l2;
	ModelRendererAnimania back_leg_l3;
	ModelRendererAnimania back_toe_l;
	ModelRendererAnimania back_leg_r1;
	ModelRendererAnimania back_leg_r2;
	ModelRendererAnimania back_leg_r3;
	ModelRendererAnimania back_toe_r;
	ModelRendererAnimania tail;
	ModelRendererAnimania tail2;
	ModelRendererAnimania neck;
	ModelRendererAnimania neck1;
	ModelRendererAnimania neck2;
	ModelRendererAnimania head_base;
	ModelRendererAnimania head_front;
	ModelRendererAnimania nose;
	ModelRendererAnimania upper_jaw_detail;
	ModelRendererAnimania jaw;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;

	public ModelGreyhound()
	{
		this.body = new ModelRendererAnimania(this, 70, 40);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.0F, -6.0F, -6.0F, 8, 12, 12);
		this.body.setRotationPoint(0.0F, 3.0F, -5.0F);
		this.body.setOffset(0.0F, 0.6F, -1.5F);
		this.leg_l1 = new ModelRendererAnimania(this, 0, 53);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.leg_l1.setRotationPoint(-3.5F, 5.4F, -3.5F);
		this.leg_l1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 8, 41);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
		this.leg_l2.setRotationPoint(0.0F, 2.5F, -0.0F);
		this.leg_l2.setOffset(0.12F, 3.5F, -0.6F);
		this.toes_l = new ModelRendererAnimania(this, 19, 53);
		this.toes_l.setTextureSize(128, 64);
		this.toes_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_l.setRotationPoint(-0.12F, -3.5F, 0.6001F);
		this.toes_l.setOffset(0.12F, 7.5F, -1.5F);
		this.leg_r1 = new ModelRendererAnimania(this, 0, 53);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -3.5F, -2.0F, 2, 7, 4);
		this.leg_r1.setRotationPoint(3.5F, 5.4F, -3.5F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 8, 41);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
		this.leg_r2.setRotationPoint(0.0F, 2.5F, -0.0F);
		this.leg_r2.setOffset(-0.12F, 3.5F, -0.6F);
		this.toes_r = new ModelRendererAnimania(this, 19, 53);
		this.toes_r.setTextureSize(128, 64);
		this.toes_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toes_r.setRotationPoint(0.1199F, -3.5F, 0.6001F);
		this.toes_r.setOffset(-0.12F, 7.5F, -1.5F);
		this.lower_body = new ModelRendererAnimania(this, 29, 24);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.0F, -4.0F, -6.5F, 6, 8, 13);
		this.lower_body.setRotationPoint(0.0F, -2.6F, 6.5F);
		this.lower_body.setOffset(0.0F, 0.8F, 5.0F);
		this.back_leg_l1 = new ModelRendererAnimania(this, 19, 49);
		this.back_leg_l1.setTextureSize(128, 64);
		this.back_leg_l1.addBox(-1.0F, -4.5F, -3.0F, 2, 9, 6);
		this.back_leg_l1.setRotationPoint(-3.0F, 2.9614F, 2.8805F);
		this.back_leg_l1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_l2 = new ModelRendererAnimania(this, 17, 39);
		this.back_leg_l2.setTextureSize(128, 64);
		this.back_leg_l2.addBox(-1.0F, -3.0F, -2.0F, 2, 6, 4);
		this.back_leg_l2.setRotationPoint(0.0F, -2.9542F, -3.1279F);
		this.back_leg_l2.setOffset(0.0F, 8.0F, -4.0F);
		this.back_leg_l3 = new ModelRendererAnimania(this, 0, 39);
		this.back_leg_l3.setTextureSize(128, 64);
		this.back_leg_l3.addBox(-1.0F, -5.5F, -1.0F, 2, 11, 2);
		this.back_leg_l3.setRotationPoint(0.0F, 1.5593F, -0.6497F);
		this.back_leg_l3.setOffset(0.01F, 3.5F, 1.0F);
		this.back_toe_l = new ModelRendererAnimania(this, 1, 37);
		this.back_toe_l.setTextureSize(128, 64);
		this.back_toe_l.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_l.setRotationPoint(-0.01F, -3.5F, -0.6999F);
		this.back_toe_l.setOffset(0.01F, 8.5F, -0.4F);
		this.back_leg_r1 = new ModelRendererAnimania(this, 19, 49);
		this.back_leg_r1.setTextureSize(128, 64);
		this.back_leg_r1.addBox(-1.0F, -4.5F, -3.0F, 2, 9, 6);
		this.back_leg_r1.setRotationPoint(3.0F, 2.9614F, 2.8805F);
		this.back_leg_r1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_r2 = new ModelRendererAnimania(this, 17, 39);
		this.back_leg_r2.setTextureSize(128, 64);
		this.back_leg_r2.addBox(-1.0F, -3.0F, -2.0F, 2, 6, 4);
		this.back_leg_r2.setRotationPoint(0.0F, -2.9542F, -3.1279F);
		this.back_leg_r2.setOffset(0.0F, 8.0F, -4.0F);
		this.back_leg_r3 = new ModelRendererAnimania(this, 0, 39);
		this.back_leg_r3.setTextureSize(128, 64);
		this.back_leg_r3.addBox(-1.0F, -5.5F, -1.0F, 2, 11, 2);
		this.back_leg_r3.setRotationPoint(0.0F, 1.5593F, -0.6497F);
		this.back_leg_r3.setOffset(-0.01F, 3.5F, 1.0F);
		this.back_toe_r = new ModelRendererAnimania(this, 1, 37);
		this.back_toe_r.setTextureSize(128, 64);
		this.back_toe_r.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.back_toe_r.setRotationPoint(0.0099F, -3.5F, -0.6999F);
		this.back_toe_r.setOffset(-0.01F, 8.5F, -0.4F);
		this.tail = new ModelRendererAnimania(this, 37, 48);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.0F, -1.0F, -6.5F, 2, 2, 13);
		this.tail.setRotationPoint(0.0F, -2.4369F, 6.0599F);
		this.tail.setOffset(0.0F, 0.8F, 5.0F);
		this.tail2 = new ModelRendererAnimania(this, 41, 52);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.0F, -1.0F, -4.5F, 2, 2, 9);
		this.tail2.setRotationPoint(0.0F, 1.9883F, -1.5034F);
		this.tail2.setOffset(0.0F, 0.8F, 12.0F);
		this.neck = new ModelRendererAnimania(this, 16, 16);
		this.neck.setTextureSize(128, 64);
		this.neck.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.neck.setRotationPoint(0.0F, -5.1F, -5.38F);
		this.neck.setOffset(0.0F, -0.0F, -0.0F);
		this.neck1 = new ModelRendererAnimania(this, 0, 1);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -2.5F, -7.0F, 5, 5, 14);
		this.neck1.setRotationPoint(0.0F, 0.0F, 1.0E-4F);
		this.neck1.setOffset(0.0F, 2.0F, -1.0F);
		this.neck2 = new ModelRendererAnimania(this, 0, 20);
		this.neck2.setTextureSize(128, 64);
		this.neck2.addBox(-2.5F, -1.0F, -4.5F, 5, 2, 9);
		this.neck2.setRotationPoint(0.0F, -4.5F, -0.04F);
		this.neck2.setOffset(0.0F, 1.0F, -1.0F);
		this.head_base = new ModelRendererAnimania(this, 101, 27);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
		this.head_base.setRotationPoint(0.0F, -0.8529F, -6.7255F);
		this.head_base.setOffset(0.0F, -1.0F, -1.0F);
		this.head_front = new ModelRendererAnimania(this, 106, 3);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-1.5F, -1.0F, -3.5F, 3, 2, 7);
		this.head_front.setRotationPoint(0.0F, 2.0F, -0.5F);
		this.head_front.setOffset(0.0F, -0.4F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 97, 16);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2);
		this.nose.setRotationPoint(0.0F, -1.0F, -2.5F);
		this.nose.setOffset(0.0F, -0.0F, -0.8F);
		this.upper_jaw_detail = new ModelRendererAnimania(this, 107, 17);
		this.upper_jaw_detail.setTextureSize(128, 64);
		this.upper_jaw_detail.addBox(-1.5F, -1.0F, -2.5F, 3, 2, 5);
		this.upper_jaw_detail.setRotationPoint(0.0F, -3.0F, 3.5615F);
		this.upper_jaw_detail.setOffset(0.0F, 1.0F, -5.0F);
		this.jaw = new ModelRendererAnimania(this, 86, 4);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 6);
		this.jaw.setRotationPoint(0.0F, 2.5F, -0.5F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.ear_r = new ModelRendererAnimania(this, 68, 9);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_r.setRotationPoint(-2.0F, -1.7F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -2.0F);
		this.ear_r2 = new ModelRendererAnimania(this, 70, 6);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_r2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_r2.setOffset(0.0F, -0.0F, -1.0F);
		this.ear_r3 = new ModelRendererAnimania(this, 72, 4);
		this.ear_r3.setTextureSize(128, 64);
		this.ear_r3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, 0.6922F, 0.0043F);
		this.ear_r3.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_l = new ModelRendererAnimania(this, 68, 9);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.ear_l.setRotationPoint(2.0F, -1.7F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -2.0F);
		this.ear_l2 = new ModelRendererAnimania(this, 70, 6);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2);
		this.ear_l2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_l2.setOffset(0.0F, -0.0F, -1.0F);
		this.ear_l3 = new ModelRendererAnimania(this, 72, 4);
		this.ear_l3.setTextureSize(128, 64);
		this.ear_l3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_l3.setRotationPoint(-1.0E-4F, 0.6922F, 0.0043F);
		this.ear_l3.setOffset(0.0F, -0.6F, -1.0F);
		this.leg_l2.addChild(this.toes_l);
		this.leg_l1.addChild(this.leg_l2);
		this.body.addChild(this.leg_l1);
		this.leg_r2.addChild(this.toes_r);
		this.leg_r1.addChild(this.leg_r2);
		this.body.addChild(this.leg_r1);
		this.back_leg_l3.addChild(this.back_toe_l);
		this.back_leg_l2.addChild(this.back_leg_l3);
		this.back_leg_l1.addChild(this.back_leg_l2);
		this.lower_body.addChild(this.back_leg_l1);
		this.back_leg_r3.addChild(this.back_toe_r);
		this.back_leg_r2.addChild(this.back_leg_r3);
		this.back_leg_r1.addChild(this.back_leg_r2);
		this.lower_body.addChild(this.back_leg_r1);
		this.tail.addChild(this.tail2);
		this.lower_body.addChild(this.tail);
		this.body.addChild(this.lower_body);
		this.neck1.addChild(this.neck2);
		this.head_front.addChild(this.nose);
		this.head_front.addChild(this.upper_jaw_detail);
		this.head_base.addChild(this.head_front);
		this.head_base.addChild(this.jaw);
		this.ear_r2.addChild(this.ear_r3);
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.ear_l2.addChild(this.ear_l3);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.neck1.addChild(this.head_base);
		this.neck.addChild(this.neck1);
		this.body.addChild(this.neck);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.0F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.body.setRotationPoint(0.0F, 3.0F, -5.0F);
		this.body.setOffset(0.0F, 0.6F, -1.5F);
		this.leg_l1.rotateAngleX = 0.0F;
		this.leg_l1.rotateAngleY = 0.0F;
		this.leg_l1.rotateAngleZ = 0.0F;
		this.leg_l1.setRotationPoint(-3.5F, 5.4F, -3.5F);
		this.leg_l1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_l2.rotateAngleX = 0.0F;
		this.leg_l2.rotateAngleY = 0.0F;
		this.leg_l2.rotateAngleZ = 0.0F;
		this.leg_l2.setRotationPoint(0.0F, 2.5F, -0.0F);
		this.leg_l2.setOffset(0.12F, 3.5F, -0.6F);
		this.toes_l.rotateAngleX = 0.0F;
		this.toes_l.rotateAngleY = 0.0F;
		this.toes_l.rotateAngleZ = 0.0F;
		this.toes_l.setRotationPoint(-0.12F, -3.5F, 0.6001F);
		this.toes_l.setOffset(0.12F, 7.5F, -1.5F);
		this.leg_r1.rotateAngleX = 0.0F;
		this.leg_r1.rotateAngleY = 0.0F;
		this.leg_r1.rotateAngleZ = 0.0F;
		this.leg_r1.setRotationPoint(3.5F, 5.4F, -3.5F);
		this.leg_r1.setOffset(0.0F, 3.0F, -0.0F);
		this.leg_r2.rotateAngleX = 0.0F;
		this.leg_r2.rotateAngleY = 0.0F;
		this.leg_r2.rotateAngleZ = 0.0F;
		this.leg_r2.setRotationPoint(0.0F, 2.5F, -0.0F);
		this.leg_r2.setOffset(-0.12F, 3.5F, -0.6F);
		this.toes_r.rotateAngleX = 0.0F;
		this.toes_r.rotateAngleY = 0.0F;
		this.toes_r.rotateAngleZ = 0.0F;
		this.toes_r.setRotationPoint(0.1199F, -3.5F, 0.6001F);
		this.toes_r.setOffset(-0.12F, 7.5F, -1.5F);
		this.lower_body.rotateAngleX = 0.03291167370485707F;
		this.lower_body.rotateAngleY = 0.0F;
		this.lower_body.rotateAngleZ = 0.0F;
		this.lower_body.setRotationPoint(0.0F, -2.6F, 6.5F);
		this.lower_body.setOffset(0.0F, 0.8F, 5.0F);
		this.back_leg_l1.rotateAngleX = 0.3737500420098217F;
		this.back_leg_l1.rotateAngleY = 0.0F;
		this.back_leg_l1.rotateAngleZ = 0.0F;
		this.back_leg_l1.setRotationPoint(-3.0F, 2.9614F, 2.8805F);
		this.back_leg_l1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_l2.rotateAngleX = 0.8980190240408864F;
		this.back_leg_l2.rotateAngleY = 0.0F;
		this.back_leg_l2.rotateAngleZ = 0.0F;
		this.back_leg_l2.setRotationPoint(0.0F, -2.9542F, -3.1279F);
		this.back_leg_l2.setOffset(0.0F, 8.0F, -4.0F);
		this.back_leg_l3.rotateAngleX = -1.3046842304140691F;
		this.back_leg_l3.rotateAngleY = 0.0F;
		this.back_leg_l3.rotateAngleZ = 0.0F;
		this.back_leg_l3.setRotationPoint(0.0F, 1.5593F, -0.6497F);
		this.back_leg_l3.setOffset(0.01F, 3.5F, 1.0F);
		this.back_toe_l.rotateAngleX = 0.0F;
		this.back_toe_l.rotateAngleY = 0.0F;
		this.back_toe_l.rotateAngleZ = 0.0F;
		this.back_toe_l.setRotationPoint(-0.01F, -3.5F, -0.6999F);
		this.back_toe_l.setOffset(0.01F, 8.5F, -0.4F);
		this.back_leg_r1.rotateAngleX = 0.3737517873390737F;
		this.back_leg_r1.rotateAngleY = 0.0F;
		this.back_leg_r1.rotateAngleZ = 0.0F;
		this.back_leg_r1.setRotationPoint(3.0F, 2.9614F, 2.8805F);
		this.back_leg_r1.setOffset(0.0F, 4.0F, 0.5F);
		this.back_leg_r2.rotateAngleX = 0.8980190240408864F;
		this.back_leg_r2.rotateAngleY = 0.0F;
		this.back_leg_r2.rotateAngleZ = 0.0F;
		this.back_leg_r2.setRotationPoint(0.0F, -2.9542F, -3.1279F);
		this.back_leg_r2.setOffset(0.0F, 8.0F, -4.0F);
		this.back_leg_r3.rotateAngleX = -1.3046842304140691F;
		this.back_leg_r3.rotateAngleY = 0.0F;
		this.back_leg_r3.rotateAngleZ = 0.0F;
		this.back_leg_r3.setRotationPoint(0.0F, 1.5593F, -0.6497F);
		this.back_leg_r3.setOffset(-0.01F, 3.5F, 1.0F);
		this.back_toe_r.rotateAngleX = 0.0F;
		this.back_toe_r.rotateAngleY = 0.0F;
		this.back_toe_r.rotateAngleZ = 0.0F;
		this.back_toe_r.setRotationPoint(0.0099F, -3.5F, -0.6999F);
		this.back_toe_r.setOffset(-0.01F, 8.5F, -0.4F);
		this.tail.rotateAngleX = -0.4668965188595071F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.tail.setRotationPoint(0.0F, -2.4369F, 6.0599F);
		this.tail.setOffset(0.0F, 0.8F, 5.0F);
		this.tail2.rotateAngleX = 0.35078849037058435F;
		this.tail2.rotateAngleY = 0.0F;
		this.tail2.rotateAngleZ = 0.0F;
		this.tail2.setRotationPoint(0.0F, 1.9883F, -1.5034F);
		this.tail2.setOffset(0.0F, 0.8F, 12.0F);
		this.neck.rotateAngleX = 0.0F;
		this.neck.rotateAngleY = 0.0F;
		this.neck.rotateAngleZ = 0.0F;
		this.neck.setRotationPoint(0.0F, -5.1F, -5.38F);
		this.neck.setOffset(0.0F, -0.0F, -0.0F);
		this.neck1.rotateAngleX = -1.0039133857471385F;
		this.neck1.rotateAngleY = 0.0F;
		this.neck1.rotateAngleZ = 0.0F;
		this.neck1.setRotationPoint(0.0F, 0.0F, 1.0E-4F);
		this.neck1.setOffset(0.0F, 2.0F, -1.0F);
		this.neck2.rotateAngleX = 0.38586262701866236F;
		this.neck2.rotateAngleY = 0.0F;
		this.neck2.rotateAngleZ = 0.0F;
		this.neck2.setRotationPoint(0.0F, -4.5F, -0.04F);
		this.neck2.setOffset(0.0F, 1.0F, -1.0F);
		this.head_base.rotateAngleX = 1.023461073369475F;
		this.head_base.rotateAngleY = 0.0F;
		this.head_base.rotateAngleZ = 0.0F;
		this.head_base.setRotationPoint(0.0F, -0.8529F, -6.7255F);
		this.head_base.setOffset(0.0F, -1.0F, -1.0F);
		this.head_front.rotateAngleX = -0.17280330391070658F;
		this.head_front.rotateAngleY = 0.0F;
		this.head_front.rotateAngleZ = 0.0F;
		this.head_front.setRotationPoint(0.0F, 2.0F, -0.5F);
		this.head_front.setOffset(0.0F, -0.4F, -2.9F);
		this.nose.rotateAngleX = 0.0F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.nose.setRotationPoint(0.0F, -1.0F, -2.5F);
		this.nose.setOffset(0.0F, -0.0F, -0.8F);
		this.upper_jaw_detail.rotateAngleX = 0.21191962310640397F;
		this.upper_jaw_detail.rotateAngleY = 0.0F;
		this.upper_jaw_detail.rotateAngleZ = 0.0F;
		this.upper_jaw_detail.setRotationPoint(0.0F, -3.0F, 3.5615F);
		this.upper_jaw_detail.setOffset(0.0F, 1.0F, -5.0F);
		this.jaw.rotateAngleX = -0.2525770680316114F;
		this.jaw.rotateAngleY = 0.0F;
		this.jaw.rotateAngleZ = 0.0F;
		this.jaw.setRotationPoint(0.0F, 2.5F, -0.5F);
		this.jaw.setOffset(0.0F, -0.2F, -2.9F);
		this.ear_r.rotateAngleX = -0.3815621357417483F;
		this.ear_r.rotateAngleY = 2.6514989636420294F;
		this.ear_r.rotateAngleZ = -1.5488994259993758F;
		this.ear_r.setRotationPoint(-2.0F, -1.7F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -2.0F);
		this.ear_r2.rotateAngleX = 0.9210416622039436F;
		this.ear_r2.rotateAngleY = 0.0F;
		this.ear_r2.rotateAngleZ = 0.0F;
		this.ear_r2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_r2.setOffset(0.0F, -0.0F, -1.0F);
		this.ear_r3.rotateAngleX = 0.1620555663769255F;
		this.ear_r3.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_r3.rotateAngleZ = 0.0F;
		this.ear_r3.setRotationPoint(0.0F, 0.6922F, 0.0043F);
		this.ear_r3.setOffset(0.0F, -0.6F, -1.0F);
		this.ear_l.rotateAngleX = -0.3815621357417483F;
		this.ear_l.rotateAngleY = -2.6514989636420294F;
		this.ear_l.rotateAngleZ = 1.5489011713286278F;
		this.ear_l.setRotationPoint(2.0F, -1.7F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -2.0F);
		this.ear_l2.rotateAngleX = 0.9210416622039436F;
		this.ear_l2.rotateAngleY = 0.0F;
		this.ear_l2.rotateAngleZ = 0.0F;
		this.ear_l2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.ear_l2.setOffset(0.0F, -0.0F, -1.0F);
		this.ear_l3.rotateAngleX = 0.1620555663769255F;
		this.ear_l3.rotateAngleY = 0.0F;
		this.ear_l3.rotateAngleZ = 1.7453292519943296E-6F;
		this.ear_l3.setRotationPoint(-1.0E-4F, 0.6922F, 0.0043F);
		this.ear_l3.setOffset(0.0F, -0.6F, -1.0F);
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
			this.body.setRotationPoint(0.0F, 3.0F, -5.0F);
			this.tail.setRotationPoint(0.0F, -4.6F, 17.5F);

			this.body.rotateAngleX = 0F;
			this.leg_l1.rotateAngleX = 0f;
			this.leg_r1.rotateAngleX = 0F;
			this.lower_body.rotateAngleX = 0.03291167370485707F;
			this.back_leg_l1.rotateAngleX = 0.4066634610439308F;
			this.back_leg_r1.rotateAngleX = 0.4066634610439308F;
			this.tail.rotateAngleX = -0.433983099825398F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PoseGreyhoundSleeping.INSTANCE));

		EntityAnimaniaDog dog = (EntityAnimaniaDog) entity;

		if (!dog.getSleeping() && !dog.isSitting())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck1.rotateAngleX = headPitch * 0.001453292F - 0.7f;
			this.neck1.rotateAngleY = netHeadYaw * 0.017453292F;
		}
		if (dog.getSleeping() && !dog.isSitting())
		{
			sleepingPose.transitionToPose(10, ageInTicks);
		}

		limbSwingAmount *= 0.6;

		if (!dog.getSleeping())
		{
			this.leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.back_leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.back_leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount + 0.06981317007977318F;
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		} else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		boolean sitting = dog.isSitting();
		if (sitting)
		{
			this.body.setRotationPoint(0.0F, 13.0F, -5.0F);
			this.tail.setRotationPoint(0.0F, -2.2F, 17.5F);

			this.body.rotateAngleX = 0.0498343861321941F;
			this.leg_l1.rotateAngleX = -1.2861313804653693F;
			this.leg_r1.rotateAngleX = -1.3041327063704389F;
			this.lower_body.rotateAngleX = -0.2823750743509106F;
			this.back_leg_l1.rotateAngleX = -0.9602522291792482F;
			this.back_leg_r1.rotateAngleX = -0.9624426173905011F;
			this.tail.rotateAngleX = -0.26909660940173774F;
		}

	}
}

package com.animania.addons.catsdogs.client.models.cats;

import com.animania.addons.catsdogs.client.models.cats.poses.PoseCatSleeping;
import com.animania.addons.catsdogs.common.entity.felids.EntityAnimaniaCat;
import com.animania.api.data.Pose;
import com.animania.api.rendering.ModelPose;
import com.animania.client.handler.AnimationHandler;
import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelCatTabby extends ModelBase
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
	ModelRendererAnimania cheek_r;
	ModelRendererAnimania cheek_r2;
	ModelRendererAnimania cheek_l;
	ModelRendererAnimania cheek_l2;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;
	ModelRendererAnimania leg_r1;
	ModelRendererAnimania leg_r2;
	ModelRendererAnimania leg_l1;
	ModelRendererAnimania leg_l2;

	public ModelCatTabby()
	{
		this.body = new ModelRendererAnimania(this, 38, 44);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-3.0F, -4.5F, -5.5F, 6, 9, 11);
		this.body.setRotationPoint(0.0F, 11.0F, -4.0F);
		this.body.setOffset(0.0F, 1.0F, -0.0F);
		this.lower_body = new ModelRendererAnimania(this, 77, 42);
		this.lower_body.setTextureSize(128, 64);
		this.lower_body.addBox(-3.5F, -4.5F, -6.0F, 7, 9, 12);
		this.lower_body.setRotationPoint(0.0F, -1.0F, 4.0F);
		this.lower_body.setOffset(0.0F, 1.0F, 6.0F);
		this.tail = new ModelRendererAnimania(this, 44, 16);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.0F, -1.5F, -4.5F, 2, 3, 9);
		this.tail.setRotationPoint(0.0F, -3.0F, 6.0F);
		this.tail.setOffset(0.0F, 1.0F, 3.0F);
		this.tail2 = new ModelRendererAnimania(this, 71, 27);
		this.tail2.setTextureSize(128, 64);
		this.tail2.addBox(-1.0F, -1.5F, -2.5F, 2, 3, 5);
		this.tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail2.setOffset(0.0F, -0.0F, 3.0F);
		this.tail3 = new ModelRendererAnimania(this, 61, 5);
		this.tail3.setTextureSize(128, 64);
		this.tail3.addBox(-1.0F, -1.5F, -3.5F, 2, 3, 7);
		this.tail3.setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail3.setOffset(0.0F, -0.0F, 3.0F);
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
		this.neck1 = new ModelRendererAnimania(this, 13, 20);
		this.neck1.setTextureSize(128, 64);
		this.neck1.addBox(-2.5F, -3.0F, -4.0F, 5, 6, 8);
		this.neck1.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.neck1.setOffset(0.0F, -0.0F, -5.0F);
		this.head_base = new ModelRendererAnimania(this, 89, 14);
		this.head_base.setTextureSize(128, 64);
		this.head_base.addBox(-3.0F, -3.0F, -2.5F, 6, 6, 5);
		this.head_base.setRotationPoint(0.0F, -2.0F, -2.0F);
		this.head_base.setOffset(0.0F, 1.0F, -3.0F);
		this.head_front = new ModelRendererAnimania(this, 0, 12);
		this.head_front.setTextureSize(128, 64);
		this.head_front.addBox(-2.0F, -1.0F, -2.5F, 4, 2, 5);
		this.head_front.setRotationPoint(0.0F, 1.5F, 1.0F);
		this.head_front.setOffset(0.0F, -1.0F, -2.9F);
		this.head_slope = new ModelRendererAnimania(this, 37, 11);
		this.head_slope.setTextureSize(128, 64);
		this.head_slope.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5);
		this.head_slope.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.head_slope.setOffset(0.0F, -1.0F, -2.9F);
		this.nose = new ModelRendererAnimania(this, 10, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -0.7F, -1.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.jaw = new ModelRendererAnimania(this, 1, 21);
		this.jaw.setTextureSize(128, 64);
		this.jaw.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.jaw.setRotationPoint(0.0F, 2.4F, -1.0F);
		this.jaw.setOffset(0.0F, -0.2F, -1.0F);
		this.cheek_r = new ModelRendererAnimania(this, 32, 41);
		this.cheek_r.setTextureSize(128, 64);
		this.cheek_r.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3);
		this.cheek_r.setRotationPoint(-1.0F, 0.5F, 2.0F);
		this.cheek_r.setOffset(-2.0F, -0.6F, -1.0F);
		this.cheek_r2 = new ModelRendererAnimania(this, 32, 36);
		this.cheek_r2.setTextureSize(128, 64);
		this.cheek_r2.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.cheek_r2.setRotationPoint(1.0F, 0.6F, 1.5F);
		this.cheek_r2.setOffset(-2.0F, -0.6F, -1.0F);
		this.cheek_l = new ModelRendererAnimania(this, 32, 41);
		this.cheek_l.setTextureSize(128, 64);
		this.cheek_l.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3);
		this.cheek_l.setRotationPoint(1.0F, 0.5F, 2.0F);
		this.cheek_l.setOffset(2.0F, -0.6F, -1.0F);
		this.cheek_l2 = new ModelRendererAnimania(this, 32, 36);
		this.cheek_l2.setTextureSize(128, 64);
		this.cheek_l2.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.cheek_l2.setRotationPoint(-1.0F, 0.6F, 1.5F);
		this.cheek_l2.setOffset(2.0F, -0.6F, -1.0F);
		this.ear_r = new ModelRendererAnimania(this, 3, 3);
		this.ear_r.setTextureSize(128, 64);
		this.ear_r.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_r.setRotationPoint(-2.5F, -2.7F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -0.5F);
		this.ear_r2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_r2.setTextureSize(128, 64);
		this.ear_r2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -2.2F);
		this.ear_r2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_r3 = new ModelRendererAnimania(this, -1, -1);
		this.ear_r3.setTextureSize(128, 64);
		this.ear_r3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, 0.6F, -0.5F);
		this.ear_r3.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_l = new ModelRendererAnimania(this, 3, 3);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 4);
		this.ear_l.setRotationPoint(2.5F, -2.7F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -0.5F);
		this.ear_l2 = new ModelRendererAnimania(this, 0, 0);
		this.ear_l2.setTextureSize(128, 64);
		this.ear_l2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -2.2F);
		this.ear_l2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_l3 = new ModelRendererAnimania(this, -1, -1);
		this.ear_l3.setTextureSize(128, 64);
		this.ear_l3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.ear_l3.setRotationPoint(0.0F, 0.6F, -0.5F);
		this.ear_l3.setOffset(0.0F, -0.6F, -0.0F);
		this.leg_r1 = new ModelRendererAnimania(this, 2, 37);
		this.leg_r1.setTextureSize(128, 64);
		this.leg_r1.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_r1.setRotationPoint(-3.0F, -4.5F, -3.0F);
		this.leg_r1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_r2 = new ModelRendererAnimania(this, 1, 52);
		this.leg_r2.setTextureSize(128, 64);
		this.leg_r2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_r2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_r2.setOffset(0.12F, 4.0F, -0.6F);
		this.leg_l1 = new ModelRendererAnimania(this, 2, 37);
		this.leg_l1.setTextureSize(128, 64);
		this.leg_l1.addBox(-1.0F, -4.5F, -2.0F, 2, 9, 4);
		this.leg_l1.setRotationPoint(3.0F, -4.5F, -3.0F);
		this.leg_l1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_l2 = new ModelRendererAnimania(this, 1, 52);
		this.leg_l2.setTextureSize(128, 64);
		this.leg_l2.addBox(-1.0F, -4.5F, -1.5F, 2, 9, 3);
		this.leg_l2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_l2.setOffset(-0.12F, 4.0F, -0.6F);
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
		this.cheek_r.addChild(this.cheek_r2);
		this.head_base.addChild(this.cheek_r);
		this.cheek_l.addChild(this.cheek_l2);
		this.head_base.addChild(this.cheek_l);
		this.ear_r2.addChild(this.ear_r3);
		this.ear_r.addChild(this.ear_r2);
		this.head_base.addChild(this.ear_r);
		this.ear_l2.addChild(this.ear_l3);
		this.ear_l.addChild(this.ear_l2);
		this.head_base.addChild(this.ear_l);
		this.neck1.addChild(this.head_base);
		this.body.addChild(this.neck1);
		this.leg_r1.addChild(this.leg_r2);
		this.body.addChild(this.leg_r1);
		this.leg_l1.addChild(this.leg_l2);
		this.body.addChild(this.leg_l1);
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.03490658503988659F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.body.setRotationPoint(0.0F, 11.0F, -4.0F);
		this.body.setOffset(0.0F, 1.0F, -0.0F);
		this.lower_body.rotateAngleX = -0.04414810342919657F;
		this.lower_body.rotateAngleY = 0.0F;
		this.lower_body.rotateAngleZ = 0.0F;
		this.lower_body.setRotationPoint(0.0F, -1.0F, 4.0F);
		this.lower_body.setOffset(0.0F, 1.0F, 6.0F);
		this.tail.rotateAngleX = -0.8751709188030287F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.tail.setRotationPoint(0.0F, -3.0F, 6.0F);
		this.tail.setOffset(0.0F, 1.0F, 3.0F);
		this.tail2.rotateAngleX = 0.21703867380250286F;
		this.tail2.rotateAngleY = 0.0F;
		this.tail2.rotateAngleZ = 0.0F;
		this.tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail2.setOffset(0.0F, -0.0F, 3.0F);
		this.tail3.rotateAngleX = 0.2716954046579573F;
		this.tail3.rotateAngleY = 0.0F;
		this.tail3.rotateAngleZ = 0.0F;
		this.tail3.setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail3.setOffset(0.0F, -0.0F, 3.0F);
		this.back_leg_r1.rotateAngleX = -1.7453292519943296E-6F;
		this.back_leg_r1.rotateAngleY = 0.0F;
		this.back_leg_r1.rotateAngleZ = 0.0F;
		this.back_leg_r1.setRotationPoint(-4.0F, -4.0F, 3.0F);
		this.back_leg_r1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_r2.rotateAngleX = 0.0F;
		this.back_leg_r2.rotateAngleY = 0.0F;
		this.back_leg_r2.rotateAngleZ = 0.0F;
		this.back_leg_r2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_r2.setOffset(0.01F, 3.5F, 0.7F);
		this.back_leg_l1.rotateAngleX = -1.7453292519943296E-6F;
		this.back_leg_l1.rotateAngleY = 0.0F;
		this.back_leg_l1.rotateAngleZ = 0.0F;
		this.back_leg_l1.setRotationPoint(4.0F, -4.0F, 3.0001F);
		this.back_leg_l1.setOffset(0.0F, 4.5F, 0.5F);
		this.back_leg_l2.rotateAngleX = 0.0F;
		this.back_leg_l2.rotateAngleY = 0.0F;
		this.back_leg_l2.rotateAngleZ = 0.0F;
		this.back_leg_l2.setRotationPoint(0.0F, 2.5F, 1.0F);
		this.back_leg_l2.setOffset(-0.01F, 3.5F, 0.7F);
		this.neck1.rotateAngleX = -0.566649066927991F;
		this.neck1.rotateAngleY = 0.0F;
		this.neck1.rotateAngleZ = 0.0F;
		this.neck1.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.neck1.setOffset(0.0F, -0.0F, -5.0F);
		this.head_base.rotateAngleX = 0.77376030794515F;
		this.head_base.rotateAngleY = 0.0F;
		this.head_base.rotateAngleZ = 0.0F;
		this.head_base.setRotationPoint(0.0F, -2.0F, -2.0F);
		this.head_base.setOffset(0.0F, 1.0F, -3.0F);
		this.head_front.rotateAngleX = 0.08333772645347724F;
		this.head_front.rotateAngleY = 0.0F;
		this.head_front.rotateAngleZ = 0.0F;
		this.head_front.setRotationPoint(0.0F, 1.5F, 1.0F);
		this.head_front.setOffset(0.0F, -1.0F, -2.9F);
		this.head_slope.rotateAngleX = 0.35123180400059084F;
		this.head_slope.rotateAngleY = 0.0F;
		this.head_slope.rotateAngleZ = 0.0F;
		this.head_slope.setRotationPoint(0.0F, -1.0F, 3.0F);
		this.head_slope.setOffset(0.0F, -1.0F, -2.9F);
		this.nose.rotateAngleX = -0.17677218262974168F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.nose.setRotationPoint(0.0F, -0.7F, -1.5F);
		this.nose.setOffset(0.0F, 0.6F, -0.8F);
		this.jaw.rotateAngleX = -0.009829694347232064F;
		this.jaw.rotateAngleY = 0.0F;
		this.jaw.rotateAngleZ = 0.0F;
		this.jaw.setRotationPoint(0.0F, 2.4F, -1.0F);
		this.jaw.setOffset(0.0F, -0.2F, -1.0F);
		this.cheek_r.rotateAngleX = 0.0F;
		this.cheek_r.rotateAngleY = -0.16720254234105678F;
		this.cheek_r.rotateAngleZ = -0.12217304763960307F;
		this.cheek_r.setRotationPoint(-1.0F, 0.5F, 2.0F);
		this.cheek_r.setOffset(-2.0F, -0.6F, -1.0F);
		this.cheek_r2.rotateAngleX = 0.0F;
		this.cheek_r2.rotateAngleY = -0.24005258531930007F;
		this.cheek_r2.rotateAngleZ = 0.0F;
		this.cheek_r2.setRotationPoint(1.0F, 0.6F, 1.5F);
		this.cheek_r2.setOffset(-2.0F, -0.6F, -1.0F);
		this.cheek_l.rotateAngleX = 0.0F;
		this.cheek_l.rotateAngleY = 0.16720254234105678F;
		this.cheek_l.rotateAngleZ = 0.12217304763960307F;
		this.cheek_l.setRotationPoint(1.0F, 0.5F, 2.0F);
		this.cheek_l.setOffset(2.0F, -0.6F, -1.0F);
		this.cheek_l2.rotateAngleX = 0.0F;
		this.cheek_l2.rotateAngleY = 0.24005433064855206F;
		this.cheek_l2.rotateAngleZ = 1.7453292519943296E-6F;
		this.cheek_l2.setRotationPoint(-1.0F, 0.6F, 1.5F);
		this.cheek_l2.setOffset(2.0F, -0.6F, -1.0F);
		this.ear_r.rotateAngleX = -1.0327043370880369F;
		this.ear_r.rotateAngleY = 1.4596223441013618F;
		this.ear_r.rotateAngleZ = -1.1730881501429486F;
		this.ear_r.setRotationPoint(-2.5F, -2.7F, 0.7F);
		this.ear_r.setOffset(-0.5F, -0.6F, -0.5F);
		this.ear_r2.rotateAngleX = 0.13962634015954636F;
		this.ear_r2.rotateAngleY = 0.0F;
		this.ear_r2.rotateAngleZ = 0.0F;
		this.ear_r2.setRotationPoint(0.0F, 0.6F, -2.2F);
		this.ear_r2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_r3.rotateAngleX = 0.20053833105414848F;
		this.ear_r3.rotateAngleY = 0.0F;
		this.ear_r3.rotateAngleZ = 0.0F;
		this.ear_r3.setRotationPoint(0.0F, 0.6F, -0.5F);
		this.ear_r3.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_l.rotateAngleX = -1.0327043370880369F;
		this.ear_l.rotateAngleY = -1.4596205987721098F;
		this.ear_l.rotateAngleZ = 1.1730898954722007F;
		this.ear_l.setRotationPoint(2.5F, -2.7F, 0.7F);
		this.ear_l.setOffset(0.5F, -0.6F, -0.5F);
		this.ear_l2.rotateAngleX = 0.13962634015954636F;
		this.ear_l2.rotateAngleY = 0.0F;
		this.ear_l2.rotateAngleZ = 0.0F;
		this.ear_l2.setRotationPoint(0.0F, 0.6F, -2.2F);
		this.ear_l2.setOffset(0.0F, -0.6F, -0.0F);
		this.ear_l3.rotateAngleX = 0.20053833105414848F;
		this.ear_l3.rotateAngleY = 0.0F;
		this.ear_l3.rotateAngleZ = 0.0F;
		this.ear_l3.setRotationPoint(0.0F, 0.6F, -0.5F);
		this.ear_l3.setOffset(0.0F, -0.6F, -0.0F);
		this.leg_r1.rotateAngleX = -0.03490658503988659F;
		this.leg_r1.rotateAngleY = 0.0F;
		this.leg_r1.rotateAngleZ = 0.0F;
		this.leg_r1.setRotationPoint(-3.0F, -4.5F, -3.0F);
		this.leg_r1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_r2.rotateAngleX = 0.0F;
		this.leg_r2.rotateAngleY = 0.0F;
		this.leg_r2.rotateAngleZ = 0.0F;
		this.leg_r2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_r2.setOffset(0.12F, 4.0F, -0.6F);
		this.leg_l1.rotateAngleX = -0.03490658503988659F;
		this.leg_l1.rotateAngleY = 0.0F;
		this.leg_l1.rotateAngleZ = 0.0F;
		this.leg_l1.setRotationPoint(3.0F, -4.5F, -3.0F);
		this.leg_l1.setOffset(0.0F, 4.0F, -0.0F);
		this.leg_l2.rotateAngleX = 0.0F;
		this.leg_l2.rotateAngleY = 0.0F;
		this.leg_l2.rotateAngleZ = 0.0F;
		this.leg_l2.setRotationPoint(0.0F, 4.0F, 0.2F);
		this.leg_l2.setOffset(-0.12F, 4.0F, -0.6F);
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

		EntityAnimaniaCat cat = (EntityAnimaniaCat) entity;

		boolean sitting = cat.isSitting();
		if (!sitting)
		{
			this.body.rotateAngleX = 0F;
			this.lower_body.rotateAngleX = -0.04414810342919657F;
			this.tail.rotateAngleX = -1.0040809373553299F;
			this.back_leg_r1.rotateAngleX = 0.05145579700729682F;
			this.back_leg_l1.rotateAngleX = 0.03700098014227979F;
			this.leg_r1.rotateAngleX = 0F;
			this.leg_l1.rotateAngleX = 0F;

		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		setupAngles();

		ModelPose sleepingPose = AnimationHandler.getOrCreatePose(entity, Pose.SLEEPING, () -> new ModelPose(this, PoseCatSleeping.INSTANCE));

		EntityAnimaniaCat cat = (EntityAnimaniaCat) entity;

		if (!cat.getSleeping() && !cat.isSitting())
		{
			sleepingPose.transitionToNormal(ageInTicks <= 10 ? 0 : 10, ageInTicks);
			this.neck1.rotateAngleX = headPitch * 0.001453292F - 0.7f;
			this.neck1.rotateAngleY = netHeadYaw * 0.017453292F;
		}
		if (cat.getSleeping() && !cat.isSitting())
		{
			sleepingPose.transitionToPose(10, ageInTicks);
		}

		limbSwingAmount *= 0.6;

		if (!cat.getSleeping())
		{
			this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 3.141593F * 0.05F) * MathHelper.sin(ageInTicks * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
			this.back_leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.back_leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.leg_l1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.leg_r1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		} else
		{
			this.tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		boolean sitting = cat.isSitting();
		if (sitting)
		{
			this.body.rotateAngleX = -0.12601277199399058F;
			this.lower_body.rotateAngleX = -0.6898780387650506F;
			this.tail.rotateAngleX = 0.5878635439859821F;
			this.back_leg_r1.rotateAngleX = -0.41790512675602626F;
			this.back_leg_l1.rotateAngleX = -0.4277452930787703F;
			this.leg_r1.rotateAngleX = -0.050714032075199235F;
			this.leg_l1.rotateAngleX = -0.04352676621548658F;
		}

	}
}

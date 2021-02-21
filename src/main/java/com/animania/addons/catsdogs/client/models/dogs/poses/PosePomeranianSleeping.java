package com.animania.addons.catsdogs.client.models.dogs.poses;

import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;

public class PosePomeranianSleeping extends ModelBase
{
	public static final PosePomeranianSleeping INSTANCE = new PosePomeranianSleeping();
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

	public PosePomeranianSleeping()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.5F, -3.5F, -6.5F, 9, 7, 13);
		this.body.setRotationPoint(0.0F, 20.0F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.back_right = new ModelRendererAnimania(this, 3, 27);
		this.back_right.setTextureSize(128, 64);
		this.back_right.addBox(-1.5F, -4.0F, -1.5F, 3, 8, 3);
		this.back_right.setRotationPoint(-2.0F, 4.0F, 4.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 7, 40);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-2.0F, -2.0F, -2.5F, 4, 4, 5);
		this.tail.setRotationPoint(0.0F, -3.0F, 5.0F);
		this.tail.setOffset(0.0F, -2.0F, 2.0F);
		this.back_left = new ModelRendererAnimania(this, 3, 27);
		this.back_left.setTextureSize(128, 64);
		this.back_left.addBox(-1.5F, -4.0F, -1.5F, 3, 8, 3);
		this.back_left.setRotationPoint(2.0F, 4.0F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.upperbody = new ModelRendererAnimania(this, 25, 62);
		this.upperbody.setTextureSize(128, 64);
		this.upperbody.addBox(-5.0F, -4.5F, -3.5F, 10, 9, 7);
		this.upperbody.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.upperbody.setOffset(0.0F, -0.0F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 3, 18);
		this.front_right.setTextureSize(128, 64);
		this.front_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_right.setRotationPoint(-2.5F, 5.0F, -0.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 3, 9);
		this.front_left.setTextureSize(128, 64);
		this.front_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_left.setRotationPoint(2.5F, 5.0F, -0.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.neck_base = new ModelRendererAnimania(this, 16, 16);
		this.neck_base.setTextureSize(128, 64);
		this.neck_base.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.neck_base.setRotationPoint(0.0F, 1.0F, -0.0F);
		this.neck_base.setOffset(0.0F, -6.0F, -3.0F);
		this.neck = new ModelRendererAnimania(this, 29, 27);
		this.neck.setTextureSize(128, 64);
		this.neck.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 8);
		this.neck.setRotationPoint(0.0F, 6.0F, 3.0F);
		this.neck.setOffset(0.0F, -0.0F, -4.0F);
		this.pug_head = new ModelRendererAnimania(this, 25, 8);
		this.pug_head.setTextureSize(128, 64);
		this.pug_head.addBox(-5.0F, -5.0F, -4.0F, 10, 10, 8);
		this.pug_head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.pug_head.setOffset(0.0F, -2.0F, -1.0F);
		this.snout = new ModelRendererAnimania(this, 36, 2);
		this.snout.setTextureSize(128, 64);
		this.snout.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2);
		this.snout.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.snout.setOffset(0.0F, 1.5F, -2.0F);
		this.nose = new ModelRendererAnimania(this, 39, 0);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.ear_l = new ModelRendererAnimania(this, 52, 0);
		this.ear_l.setTextureSize(128, 64);
		this.ear_l.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.ear_l.setRotationPoint(0.0F, 2.0001F, 1.0F);
		this.ear_l.setOffset(-2.0F, -8.0F, -1.0F);
		this.ear_l1 = new ModelRendererAnimania(this, 52, 0);
		this.ear_l1.setTextureSize(128, 64);
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
		setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = -0.017203710436908107F;
		this.body.rotateAngleY = 0.32456666368862147F;
		this.body.rotateAngleZ = 0.008983209660014814F;
		this.back_right.rotateAngleX = 0.01995783999655516F;
		this.back_right.rotateAngleY = -0.5505641125416113F;
		this.back_right.rotateAngleZ = 1.603297848125535F;
		this.tail.rotateAngleX = -0.9991468915599417F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.back_left.rotateAngleX = -0.004707152992628707F;
		this.back_left.rotateAngleY = 0.35579060400680007F;
		this.back_left.rotateAngleZ = -1.5581322177424257F;
		this.upperbody.rotateAngleX = 0.26333004155314843F;
		this.upperbody.rotateAngleY = -0.2438940550029396F;
		this.upperbody.rotateAngleZ = -0.05014854539755307F;
		this.front_right.rotateAngleX = -1.3828906888666832F;
		this.front_right.rotateAngleY = 1.7535654607344908F;
		this.front_right.rotateAngleZ = -1.4452355950771725F;
		this.front_left.rotateAngleX = -0.9995552986049085F;
		this.front_left.rotateAngleY = -2.5899586542167095F;
		this.front_left.rotateAngleZ = 1.558376563837705F;
		this.neck_base.rotateAngleX = 0.4133934506396209F;
		this.neck_base.rotateAngleY = -0.24517861733240745F;
		this.neck_base.rotateAngleZ = 0.008066911802717791F;
		this.neck.rotateAngleX = -0.5546935615518298F;
		this.neck.rotateAngleY = 0.0F;
		this.neck.rotateAngleZ = 0.0F;
		this.pug_head.rotateAngleX = 0.15711104860602554F;
		this.pug_head.rotateAngleY = -0.3220132469929538F;
		this.pug_head.rotateAngleZ = 0.008529424054496288F;
		this.snout.rotateAngleX = 0.0F;
		this.snout.rotateAngleY = 0.0F;
		this.snout.rotateAngleZ = 0.0F;
		this.nose.rotateAngleX = 0.0F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.ear_l.rotateAngleX = 0.0F;
		this.ear_l.rotateAngleY = 0.0F;
		this.ear_l.rotateAngleZ = -0.23574336739612609F;
		this.ear_l1.rotateAngleX = 0.0F;
		this.ear_l1.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_l1.rotateAngleZ = 0.23574336739612609F;
	}

}

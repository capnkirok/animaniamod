package com.animania.addons.catsdogs.client.models.dogs.poses;

import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;

public class PosePugSleeping extends ModelBase
{
	public static final PosePugSleeping INSTANCE = new PosePugSleeping();
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

	public PosePugSleeping()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-4.5F, -4.5F, -6.5F, 9, 9, 13);
		this.body.setRotationPoint(0.0F, 18.0F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 6, 39);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-1.0F, -3.0F, -3.0F, 2, 6, 6);
		this.tail.setRotationPoint(0.0F, -5.0F, 4.0F);
		this.tail.setOffset(0.0F, -0.0F, 2.0F);
		this.neck_base = new ModelRendererAnimania(this, 16, 16);
		this.neck_base.setTextureSize(128, 64);
		this.neck_base.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.neck_base.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.neck_base.setOffset(0.0F, -0.0F, -0.0F);
		this.neck = new ModelRendererAnimania(this, 27, 27);
		this.neck.setTextureSize(128, 64);
		this.neck.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 8);
		this.neck.setRotationPoint(0.7821F, -2.0F, -7.0F);
		this.neck.setOffset(0.0F, 3.0F, 4.0F);
		this.pug_head = new ModelRendererAnimania(this, 28, 13);
		this.pug_head.setTextureSize(128, 64);
		this.pug_head.addBox(-4.0F, -4.0F, -3.0F, 8, 8, 6);
		this.pug_head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.pug_head.setOffset(0.0F, -0.0F, -0.0F);
		this.ear1 = new ModelRendererAnimania(this, 23, 9);
		this.ear1.setTextureSize(128, 64);
		this.ear1.addBox(-0.5F, -2.0F, -2.0F, 1, 4, 4);
		this.ear1.setRotationPoint(-4.0F, -4.0F, -0.0F);
		this.ear1.setOffset(0.5F, 2.0F, -0.0F);
		this.ear2 = new ModelRendererAnimania(this, 24, 1);
		this.ear2.setTextureSize(128, 64);
		this.ear2.addBox(-0.5F, -2.0F, -2.0F, 1, 4, 4);
		this.ear2.setRotationPoint(4.0F, -4.0F, 1.0E-4F);
		this.ear2.setOffset(0.5F, 2.0F, -0.0F);
		this.nose = new ModelRendererAnimania(this, 37, 9);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-2.0F, -1.5F, -0.5F, 4, 3, 1);
		this.nose.setRotationPoint(0.0F, 0.0F, -1.5F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.back_right = new ModelRendererAnimania(this, 3, 27);
		this.back_right.setTextureSize(128, 64);
		this.back_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.back_right.setRotationPoint(-2.0F, 5.0F, 4.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.back_left = new ModelRendererAnimania(this, 3, 0);
		this.back_left.setTextureSize(128, 64);
		this.back_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.back_left.setRotationPoint(2.5F, 5.0F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 3, 18);
		this.front_right.setTextureSize(128, 64);
		this.front_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_right.setRotationPoint(-2.5F, 5.0F, -4.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 3, 9);
		this.front_left.setTextureSize(128, 64);
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
		this.setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.0F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.tail.rotateAngleX = -0.8361645553502074F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
		this.neck_base.rotateAngleX = 0.0F;
		this.neck_base.rotateAngleY = 0.0F;
		this.neck_base.rotateAngleZ = -0.15707788735023767F;
		this.neck.rotateAngleX = 0.02995159529347469F;
		this.neck.rotateAngleY = 0.004747295565424576F;
		this.neck.rotateAngleZ = 0.15715119117882143F;
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
		this.back_right.rotateAngleX = 0.08340928495280901F;
		this.back_right.rotateAngleY = -0.4044154769673621F;
		this.back_right.rotateAngleZ = 1.5276709863073688F;
		this.back_left.rotateAngleX = -0.022273891913951634F;
		this.back_left.rotateAngleY = 0.422294629824792F;
		this.back_left.rotateAngleZ = -1.5212726092695574F;
		this.front_right.rotateAngleX = 0.0429682608548484F;
		this.front_right.rotateAngleY = -0.612518064999654F;
		this.front_right.rotateAngleZ = 1.4249827944370304F;
		this.front_left.rotateAngleX = -0.02410648762854568F;
		this.front_left.rotateAngleY = 0.3846740577980542F;
		this.front_left.rotateAngleZ = -1.511325977862442F;
	}

}

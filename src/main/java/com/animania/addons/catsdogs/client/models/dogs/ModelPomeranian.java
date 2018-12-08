package com.animania.addons.catsdogs.client.models.dogs;

import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelPomeranian extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania back_right;
	ModelRendererAnimania tail;
	ModelRendererAnimania neck;
	ModelRendererAnimania pug_head;
	ModelRendererAnimania snout;
	ModelRendererAnimania nose;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l1;
	ModelRendererAnimania back_left;
	ModelRendererAnimania upperbody;
	ModelRendererAnimania front_right;
	ModelRendererAnimania front_left;

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
		this.neck = new ModelRendererAnimania(this, 29, 27);
		this.neck.setTextureSize(64, 128);
		this.neck.addBox(-3.5F, -3.5F, -4.0F, 7, 7, 8);
		this.neck.setRotationPoint(0.0F, -6.0F, -7.5F);
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
		this.ear_l.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.ear_l.setOffset(-2.0F, -8.0F, -1.0F);
		this.ear_l1 = new ModelRendererAnimania(this, 52, 0);
		this.ear_l1.setTextureSize(64, 128);
		this.ear_l1.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 2);
		this.ear_l1.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.ear_l1.setOffset(2.0F, -8.0F, -1.0F);
		this.back_left = new ModelRendererAnimania(this, 3, 27);
		this.back_left.setTextureSize(64, 128);
		this.back_left.addBox(-1.5F, -4.0F, -1.5F, 3, 8, 3);
		this.back_left.setRotationPoint(2.0F, 4.0F, 4.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.upperbody = new ModelRendererAnimania(this, 25, 62);
		this.upperbody.setTextureSize(64, 128);
		this.upperbody.addBox(-5.0F, -4.5F, -3.5F, 10, 9, 7);
		this.upperbody.setRotationPoint(0.0F, 13.5F, 6.0F);
		this.upperbody.setOffset(0.0F, -0.0F, -0.0F);
		this.front_right = new ModelRendererAnimania(this, 3, 18);
		this.front_right.setTextureSize(64, 128);
		this.front_right.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_right.setRotationPoint(-2.5F, 18.5F, 6.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 3, 9);
		this.front_left.setTextureSize(64, 128);
		this.front_left.addBox(-1.5F, -3.0F, -1.5F, 3, 6, 3);
		this.front_left.setRotationPoint(2.5F, 18.5F, 6.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.body.addChild(this.back_right);
		this.body.addChild(this.tail);
		this.pug_head.addChild(this.snout);
		this.pug_head.addChild(this.nose);
		this.neck.addChild(this.pug_head);
		this.neck.addChild(this.ear_l);
		this.neck.addChild(this.ear_l1);
		this.body.addChild(this.neck);
		this.body.addChild(this.back_left);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.neck.rotateAngleX = -0.6108652381980153F;
		this.pug_head.rotateAngleX = 0.6108652381980153F;
		this.ear_l.rotateAngleX = 0.6108652381980153F;
		this.ear_l.rotateAngleY = 1.7453292519943296E-6F;
		this.ear_l.rotateAngleZ = -0.23574336739612609F;
		this.ear_l1.rotateAngleX = 0.6108652381980153F;
		this.ear_l1.rotateAngleZ = 0.23574336739612609F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
		this.upperbody.render(scale);
		this.front_right.render(scale);
		this.front_left.render(scale);
	}
}

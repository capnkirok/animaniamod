package com.animania.addons.catsdogs.client.models.dogs;

import net.minecraft.client.model.ModelBase;
import com.animania.client.models.render.ModelRendererAnimania;
import net.minecraft.entity.Entity;

public class ModelChihuahua extends ModelBase
{
	ModelRendererAnimania body;
	ModelRendererAnimania tail;
	ModelRendererAnimania neck;
	ModelRendererAnimania head;
	ModelRendererAnimania snout;
	ModelRendererAnimania nose;
	ModelRendererAnimania front_right;
	ModelRendererAnimania front_left;
	ModelRendererAnimania back_left;
	ModelRendererAnimania back_right;
	ModelRendererAnimania ear_l;
	ModelRendererAnimania ear_l2;
	ModelRendererAnimania ear_l3;
	ModelRendererAnimania ear_r;
	ModelRendererAnimania ear_r2;
	ModelRendererAnimania ear_r3;

	public ModelChihuahua()
	{
		this.body = new ModelRendererAnimania(this, 20, 42);
		this.body.setTextureSize(64, 64);
		this.body.addBox(-3.0F, -3.5F, -6.5F, 6, 7, 13);
		this.body.setRotationPoint(0.0F, 13.5F, 10.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 10, 43);
		this.tail.setTextureSize(64, 64);
		this.tail.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.tail.setRotationPoint(0.0F, -6.0F, 7.0F);
		this.tail.setOffset(0.0F, -0.0F, 2.0F);
		this.neck = new ModelRendererAnimania(this, 28, 28);
		this.neck.setTextureSize(64, 64);
		this.neck.addBox(-2.5F, -3.0F, -3.5F, 5, 6, 7);
		this.neck.setRotationPoint(0.0F, -6.0F, -5.0F);
		this.neck.setOffset(0.0F, 3.0F, 4.0F);
		this.head = new ModelRendererAnimania(this, 27, 12);
		this.head.setTextureSize(64, 64);
		this.head.addBox(-4.0F, -4.0F, -3.5F, 8, 8, 7);
		this.head.setRotationPoint(0.0F, 0.8914F, -4.3271F);
		this.head.setOffset(0.0F, -1.0F, -0.0F);
		this.snout = new ModelRendererAnimania(this, 47, 27);
		this.snout.setTextureSize(64, 64);
		this.snout.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4);
		this.snout.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.snout.setOffset(0.0F, 1.5F, -2.0F);
		this.nose = new ModelRendererAnimania(this, 40, 3);
		this.nose.setTextureSize(64, 64);
		this.nose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.nose.setRotationPoint(0.0F, -1.5F, -4.0F);
		this.nose.setOffset(0.0F, 1.5F, -2.0F);
		this.front_right = new ModelRendererAnimania(this, 4, 19);
		this.front_right.setTextureSize(64, 64);
		this.front_right.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.front_right.setRotationPoint(-1.5F, 17.0F, 6.0F);
		this.front_right.setOffset(0.0F, 2.5F, -0.0F);
		this.front_left = new ModelRendererAnimania(this, 4, 19);
		this.front_left.setTextureSize(64, 64);
		this.front_left.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.front_left.setRotationPoint(1.5F, 17.0F, 6.0F);
		this.front_left.setOffset(0.0F, 2.5F, -0.0F);
		this.back_left = new ModelRendererAnimania(this, 4, 19);
		this.back_left.setTextureSize(64, 64);
		this.back_left.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.back_left.setRotationPoint(1.5F, 17.0F, 14.0F);
		this.back_left.setOffset(0.0F, 2.5F, -0.0F);
		this.back_right = new ModelRendererAnimania(this, 4, 19);
		this.back_right.setTextureSize(64, 64);
		this.back_right.addBox(-1.0F, -3.0F, -1.0F, 2, 6, 2);
		this.back_right.setRotationPoint(-1.5F, 17.0F, 14.0F);
		this.back_right.setOffset(0.0F, 2.5F, -0.0F);
		this.ear_l = new ModelRendererAnimania(this, 16, 8);
		this.ear_l.setTextureSize(64, 64);
		this.ear_l.addBox(-2.5F, -2.0F, -0.5F, 5, 4, 1);
		this.ear_l.setRotationPoint(-3.0F, 5.0F, 2.0F);
		this.ear_l.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_l2 = new ModelRendererAnimania(this, 18, 5);
		this.ear_l2.setTextureSize(64, 64);
		this.ear_l2.addBox(-1.5F, -1.0F, -0.5F, 3, 2, 1);
		this.ear_l2.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.ear_l2.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_l3 = new ModelRendererAnimania(this, 19, 3);
		this.ear_l3.setTextureSize(64, 64);
		this.ear_l3.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_l3.setRotationPoint(0.0F, -0.5F, -0.0F);
		this.ear_l3.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r = new ModelRendererAnimania(this, 16, 8);
		this.ear_r.setTextureSize(64, 64);
		this.ear_r.addBox(-2.5F, -2.0F, -0.5F, 5, 4, 1);
		this.ear_r.setRotationPoint(3.0F, 5.0F, 2.0F);
		this.ear_r.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r2 = new ModelRendererAnimania(this, 18, 5);
		this.ear_r2.setTextureSize(64, 64);
		this.ear_r2.addBox(-1.5F, -1.0F, -0.5F, 3, 2, 1);
		this.ear_r2.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.ear_r2.setOffset(0.0F, -1.0F, -0.0F);
		this.ear_r3 = new ModelRendererAnimania(this, 19, 3);
		this.ear_r3.setTextureSize(64, 64);
		this.ear_r3.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.ear_r3.setRotationPoint(0.0F, -0.5F, -0.0F);
		this.ear_r3.setOffset(0.0F, -1.0F, -0.0F);
		this.body.addChild(this.tail);
		this.head.addChild(this.snout);
		this.head.addChild(this.nose);
		this.neck.addChild(this.head);
		this.body.addChild(this.neck);
		this.ear_l2.addChild(this.ear_l3);
		this.ear_l.addChild(this.ear_l2);
		this.ear_r2.addChild(this.ear_r3);
		this.ear_r.addChild(this.ear_r2);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.tail.rotateAngleX = -0.6430019857149869F;
		this.neck.rotateAngleX = -0.9467084741837722F;
		this.head.rotateAngleX = 0.9867323645905062F;
		this.nose.rotateAngleX = -1.7453292519943296E-6F;
		this.ear_l.rotateAngleZ = -0.43321166429601654F;
		this.ear_r.rotateAngleZ = 0.43321166429601654F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.body.render(scale);
		this.front_right.render(scale);
		this.front_left.render(scale);
		this.back_left.render(scale);
		this.back_right.render(scale);
		this.ear_l.render(scale);
		this.ear_r.render(scale);
	}
}

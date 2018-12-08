package com.animania.addons.catsdogs.client.models.dogs;

import net.minecraft.client.model.ModelBase;
import com.animania.client.models.render.ModelRendererAnimania;
import net.minecraft.entity.Entity;

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

	public ModelDachshund()
	{
		this.wolf_head = new ModelRendererAnimania(this, 0, 0);
		this.wolf_head.setTextureSize(64, 32);
		this.wolf_head.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
		this.wolf_head.setRotationPoint(-1.0F, 18.5F, -6.0F);
		this.wolf_head.setOffset(-1.0F, -1.0F, -0.0F);
		this.body = new ModelRendererAnimania(this, 25, 0);
		this.body.setTextureSize(64, 32);
		this.body.addBox(-2.0F, -6.0F, -2.0F, 4, 12, 4);
		this.body.setRotationPoint(0.0F, 19.0F, -3.0F);
		this.body.setOffset(-2.0F, 4.0F, -1.0F);
		this.leg1 = new ModelRendererAnimania(this, 0, 18);
		this.leg1.setTextureSize(64, 32);
		this.leg1.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg1.setRotationPoint(-3.5F, 20.0F, 6.0F);
		this.leg1.setOffset(0.0F, 2.0F, -0.0F);
		this.leg2 = new ModelRendererAnimania(this, 0, 18);
		this.leg2.setTextureSize(64, 32);
		this.leg2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg2.setRotationPoint(-0.5F, 20.0F, 6.0F);
		this.leg2.setOffset(0.0F, 2.0F, -0.0F);
		this.leg3 = new ModelRendererAnimania(this, 0, 18);
		this.leg3.setTextureSize(64, 32);
		this.leg3.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg3.setRotationPoint(-3.5F, 20.0F, -4.0F);
		this.leg3.setOffset(0.0F, 2.0F, -0.0F);
		this.leg4 = new ModelRendererAnimania(this, 0, 18);
		this.leg4.setTextureSize(64, 32);
		this.leg4.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg4.setRotationPoint(-0.5F, 20.0F, -4.0F);
		this.leg4.setOffset(0.0F, 2.0F, -0.0F);
		this.tail = new ModelRendererAnimania(this, 9, 18);
		this.tail.setTextureSize(64, 32);
		this.tail.addBox(-0.5F, -2.5F, -0.5F, 1, 5, 1);
		this.tail.setRotationPoint(-2.5F, 18.0F, 6.0F);
		this.tail.setOffset(0.5F, 2.5F, -0.5F);
		this.ear1 = new ModelRendererAnimania(this, 16, 14);
		this.ear1.setTextureSize(64, 32);
		this.ear1.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear1.setRotationPoint(-4.0F, 15.5F, -6.0F);
		this.ear1.setOffset(-0.5F, 1.5F, -0.0F);
		this.ear2 = new ModelRendererAnimania(this, 16, 14);
		this.ear2.setTextureSize(64, 32);
		this.ear2.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear2.setRotationPoint(0.0F, 15.5F, -6.0F);
		this.ear2.setOffset(0.5F, 1.5F, -0.0F);
		this.nose = new ModelRendererAnimania(this, 0, 10);
		this.nose.setTextureSize(64, 32);
		this.nose.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3);
		this.nose.setRotationPoint(-1.0F, 17.5F, -5.0F);
		this.nose.setOffset(-1.0F, 1.0F, -3.5F);
		this.toe1 = new ModelRendererAnimania(this, 0, 22);
		this.toe1.setTextureSize(64, 32);
		this.toe1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe1.setRotationPoint(-3.5F, 20.0F, -4.0F);
		this.toe1.setOffset(0.0F, 3.5F, -1.0F);
		this.toe2 = new ModelRendererAnimania(this, 0, 22);
		this.toe2.setTextureSize(64, 32);
		this.toe2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe2.setRotationPoint(-0.5F, 20.0F, -4.0F);
		this.toe2.setOffset(0.0F, 3.5F, -1.0F);
		this.toe21 = new ModelRendererAnimania(this, 0, 22);
		this.toe21.setTextureSize(64, 32);
		this.toe21.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe21.setRotationPoint(-0.5F, 20.0F, 6.0F);
		this.toe21.setOffset(0.0F, 3.5F, -1.0F);
		this.toe211 = new ModelRendererAnimania(this, 0, 22);
		this.toe211.setTextureSize(64, 32);
		this.toe211.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe211.setRotationPoint(-3.5F, 20.0F, 6.0F);
		this.toe211.setOffset(0.0F, 3.5F, -1.0F);

	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale)
	{
		this.body.rotateAngleX = 1.5707963267948966F;
		this.tail.rotateAngleX = 0.5615561961706715F;
		this.tail.rotateAngleY = -3.141592653589793F;
		this.tail.rotateAngleZ = -3.141592653589793F;
		this.ear1.rotateAngleZ = 0.17453292519943295F;
		this.ear2.rotateAngleZ = -0.17453292519943295F;
		this.setRotationAngles(f1, f2, f3, f4, f5, scale, entity);
		this.wolf_head.render(scale);
		this.body.render(scale);
		this.leg1.render(scale);
		this.leg2.render(scale);
		this.leg3.render(scale);
		this.leg4.render(scale);
		this.tail.render(scale);
		this.ear1.render(scale);
		this.ear2.render(scale);
		this.nose.render(scale);
		this.toe1.render(scale);
		this.toe2.render(scale);
		this.toe21.render(scale);
		this.toe211.render(scale);
	}
}

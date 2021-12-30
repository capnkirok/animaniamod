package com.animania.addons.extra.client.model.blockentity;

public class ModelHamsterWheel extends ModelBase
{
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer axel1;
	ModelRenderer Base3;
	ModelRenderer Base4;
	ModelRenderer axel12;
	ModelRenderer Base5;
	ModelRenderer Wheel1;
	ModelRenderer Wheel2;
	ModelRenderer Wheel3;
	ModelRenderer Wheel4;
	ModelRenderer Wheel5;
	ModelRenderer Wheel6;
	ModelRenderer Wheel7;
	ModelRenderer Wheel8;
	ModelRenderer Stick;
	ModelRenderer Stick2;
	ModelRenderer axel1b;

	public ModelHamsterWheel()
	{
		this(0.0f);
	}

	public ModelHamsterWheel(float par1)
	{
		this.Base1 = new ModelRenderer(this, 12, 1);
		this.Base1.setTextureSize(64, 32);
		this.Base1.addBox(-1F, 0F, -0.5F, 1, 13, 1);
		this.Base1.setRotationPoint(6F, 24F, 6F);

		this.Base2 = new ModelRenderer(this, 12, 1);
		this.Base2.setTextureSize(64, 32);
		this.Base2.addBox(-0.5F, -0.5F, -0.5F, 1, 13, 1);
		this.Base2.setRotationPoint(-2.99795E-07F, 13F, 6F);

		this.axel1 = new ModelRenderer(this, 12, 1);
		this.axel1.setTextureSize(64, 32);
		this.axel1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.axel1.setRotationPoint(-2.99795E-07F, 13F, 5F);

		this.Base3 = new ModelRenderer(this, 12, 1);
		this.Base3.setTextureSize(64, 32);
		this.Base3.addBox(-1F, 0F, -0.5F, 1, 13, 1);
		this.Base3.setRotationPoint(5.999999F, 24F, -6F);

		this.Base4 = new ModelRenderer(this, 12, 1);
		this.Base4.setTextureSize(64, 32);
		this.Base4.addBox(-0.5F, -0.5F, -0.5F, 1, 13, 1);
		this.Base4.setRotationPoint(-3.023637E-06F, 13F, -6F);

		this.axel12 = new ModelRenderer(this, 12, 1);
		this.axel12.setTextureSize(64, 32);
		this.axel12.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.axel12.setRotationPoint(-4.87138E-06F, 13F, -5F);

		this.Base5 = new ModelRenderer(this, 4, 13);
		this.Base5.setTextureSize(64, 32);
		this.Base5.addBox(-0.5F, -0.5F, -6F, 1, 1, 12);
		this.Base5.setRotationPoint(-6.5F, 23.5F, 0F);

		this.Wheel1 = new ModelRenderer(this, 19, 2);
		this.Wheel1.setTextureSize(64, 32);
		this.Wheel1.addBox(-3F, -0.5F, -5F, 6, 1, 10);
		this.Wheel1.setRotationPoint(-3.234292E-06F, 6.500002F, 4.768372E-07F);

		this.Wheel2 = new ModelRenderer(this, 19, 2);
		this.Wheel2.setTextureSize(64, 32);
		this.Wheel2.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel2.setRotationPoint(2.999997F, 6F, 4.768372E-07F);

		this.Wheel3 = new ModelRenderer(this, 19, 2);
		this.Wheel3.setTextureSize(64, 32);
		this.Wheel3.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel3.setRotationPoint(7.242638F, 10.24264F, 4.768372E-07F);

		this.Wheel4 = new ModelRenderer(this, 19, 2);
		this.Wheel4.setTextureSize(64, 32);
		this.Wheel4.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel4.setRotationPoint(7.242639F, 16.24264F, 4.768372E-07F);

		this.Wheel5 = new ModelRenderer(this, 19, 2);
		this.Wheel5.setTextureSize(64, 32);
		this.Wheel5.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel5.setRotationPoint(2.999999F, 20.48528F, 4.768372E-07F);

		this.Wheel6 = new ModelRenderer(this, 19, 2);
		this.Wheel6.setTextureSize(64, 32);
		this.Wheel6.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel6.setRotationPoint(-3.000001F, 20.48528F, 1.218644E-06F);

		this.Wheel7 = new ModelRenderer(this, 19, 2);
		this.Wheel7.setTextureSize(64, 32);
		this.Wheel7.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel7.setRotationPoint(-7.242642F, 16.24264F, 2.267717E-06F);

		this.Wheel8 = new ModelRenderer(this, 19, 2);
		this.Wheel8.setTextureSize(64, 32);
		this.Wheel8.addBox(0F, 0F, -5F, 6, 1, 10);
		this.Wheel8.setRotationPoint(-7.242642F, 10.24264F, 3.009524E-06F);

		this.Stick = new ModelRenderer(this, 13, 2);
		this.Stick.setTextureSize(64, 32);
		this.Stick.addBox(-0.5F, -6.5F, 0F, 1, 13, 0);
		this.Stick.setRotationPoint(-1.748819E-06F, 13F, 4.75F);

		this.Stick2 = new ModelRenderer(this, 13, 2);
		this.Stick2.setTextureSize(64, 32);
		this.Stick2.addBox(-0.5F, -6.5F, 0F, 1, 13, 0);
		this.Stick2.setRotationPoint(-2.454983E-06F, 13F, -4.75F);

		this.axel1b = new ModelRenderer(this, 0, 0);
		this.axel1b.setTextureSize(64, 32);
		this.axel1b.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		this.axel1b.setRotationPoint(-2.99795E-07F, 13F, 7F);

	}

	@Override
	public void render(Entity entityIn, float nestType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Base1.render(scale);
		this.Base2.render(scale);
		this.axel1.render(scale);
		this.Base3.render(scale);
		this.Base4.render(scale);
		this.axel12.render(scale);
		this.Wheel1.render(scale);
		this.Wheel2.render(scale);
		this.Wheel3.render(scale);
		this.Wheel4.render(scale);
		this.Wheel5.render(scale);
		this.Wheel6.render(scale);
		this.Wheel7.render(scale);
		this.Wheel8.render(scale);
		this.Stick.render(scale);
		this.Stick2.render(scale);
		this.axel1b.render(scale);

		this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		this.Base1.rotateAngleZ = 1.570796F;
		this.Base2.rotateAngleZ = -0.5235988F;
		this.axel1.rotateAngleZ = -0.5235988F;
		this.Base3.rotateAngleZ = 1.570796F;
		this.Base4.rotateAngleZ = -0.523599F;
		this.axel12.rotateAngleZ = -0.523599F;
		this.Wheel1.rotateAngleZ = -1.685874E-07F;
		this.Wheel2.rotateAngleZ = 0.7853982F;
		this.Wheel3.rotateAngleZ = 1.570797F;
		this.Wheel4.rotateAngleZ = 2.356194F;
		this.Wheel5.rotateAngleX = -1.236345E-07F;
		this.Wheel5.rotateAngleY = 1.236345E-07F;
		this.Wheel5.rotateAngleZ = -3.141593F;
		this.Wheel6.rotateAngleX = -1.236345E-07F;
		this.Wheel6.rotateAngleY = 1.236345E-07F;
		this.Wheel6.rotateAngleZ = -2.356194F;
		this.Wheel7.rotateAngleX = -1.236345E-07F;
		this.Wheel7.rotateAngleY = 1.236344E-07F;
		this.Wheel7.rotateAngleZ = -1.570796F;
		this.Wheel8.rotateAngleX = -1.236345E-07F;
		this.Wheel8.rotateAngleY = 1.236344E-07F;
		this.Wheel8.rotateAngleZ = -0.7853976F;
		this.Stick.rotateAngleZ = -1.685874E-07F;
		this.Stick2.rotateAngleZ = -1.685874E-07F;
		this.axel1b.rotateAngleZ = -0.5235988F;

	}

}

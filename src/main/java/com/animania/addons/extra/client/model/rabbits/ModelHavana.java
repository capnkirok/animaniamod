package com.animania.addons.extra.client.model.rabbits;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelHavana extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer Neck1 = new ModelRenderer(this, 0, 0);
	private float jumpRotation;

	ModelRenderer LowerBody;
	ModelRenderer Tail;
	ModelRenderer BackLegR1;
	ModelRenderer BackLegR2;
	ModelRenderer BackLegL1;
	ModelRenderer BackLegL2;
	ModelRenderer LegL1;
	ModelRenderer LegR1;

	ModelRenderer HeadBase;
	ModelRenderer HeadFront;
	ModelRenderer WhiskerR1;
	ModelRenderer WhiskerR2;
	ModelRenderer WhiskerL1;
	ModelRenderer WhiskerL2;
	ModelRenderer Nose;
	ModelRenderer EarR;
	ModelRenderer EarL;

	public ModelHavana()
	{
		this(0.0f);
	}

	public ModelHavana(float par1)
	{
		this.LowerBody = new ModelRenderer(this, 24, 25);
		this.LowerBody.setTextureSize(128, 128);
		this.LowerBody.addBox(-3.5F, -2.9F, -3.2F, 7, 7, 13);
		this.LowerBody.setRotationPoint(0F, 16.1F, -1.5F + 7F);
		this.Tail = new ModelRenderer(this, 50, 18);
		this.Tail.setTextureSize(128, 128);
		this.Tail.addBox(-1.5F, -2F, -1.7F, 3, 3, 4);
		this.Tail.setRotationPoint(0F, 19.29693F, 7.870147F + 7F);
		this.BackLegR1 = new ModelRenderer(this, 20, 52);
		this.BackLegR1.setTextureSize(128, 128);
		this.BackLegR1.addBox(-1F, 0F, -2.5F, 2, 6, 5);
		this.BackLegR1.setRotationPoint(3.8F, 16.39402F, 5.77417F + 7F);
		this.BackLegR2 = new ModelRenderer(this, 0, 43);
		this.BackLegR2.setTextureSize(128, 128);
		this.BackLegR2.addBox(-1.02F, -1.4F, -0.7F, 2, 8, 2);
		this.BackLegR2.setRotationPoint(3.8F, 22.27699F, 5.412659F + 7F);
		this.BackLegL1 = new ModelRenderer(this, 20, 52);
		this.BackLegL1.setTextureSize(128, 128);
		this.BackLegL1.addBox(-1F, 0F, -2.5F, 2, 6, 5);
		this.BackLegL1.setRotationPoint(-3.8F, 16.39402F, 5.77417F + 7F);
		this.BackLegL2 = new ModelRenderer(this, 0, 43);
		this.BackLegL2.setTextureSize(128, 128);
		this.BackLegL2.addBox(-0.98F, -1.4F, -0.7F, 2, 8, 2);
		this.BackLegL2.setRotationPoint(-3.8F, 22.27699F, 5.412659F + 7F);
		this.LegL1 = new ModelRenderer(this, 0, 54);
		this.LegL1.setTextureSize(128, 128);
		this.LegL1.addBox(-1F, 0F, -1.5F, 2, 7, 2);
		this.LegL1.setRotationPoint(2.7F, 17.46663F, -3.741055F + 7F);
		this.LegR1 = new ModelRenderer(this, 0, 54);
		this.LegR1.setTextureSize(128, 128);
		this.LegR1.addBox(-1F, 0F, -1.5F, 2, 7, 2);
		this.LegR1.setRotationPoint(-2.7F, 17.46663F, -3.741055F + 7F);

		this.Neck1 = new ModelRenderer(this, 0, 27);
		this.Neck1.setTextureSize(128, 128);
		this.Neck1.addBox(-2F, -2.2F, -4.8F, 4, 5, 6);
		this.Neck1.setRotationPoint(0F, 15.88453F, -3.429656F + 7F);

		this.HeadBase = new ModelRenderer(this, 0, 16);
		this.HeadBase.setTextureSize(128, 128);
		this.HeadBase.addBox(-2.5F, -2.51F, -3.5F, 5, 4, 5);
		this.HeadBase.setRotationPoint(0F, 12.30659F - 15.88453F, -5.945282F + 3.429656F);

		this.HeadFront = new ModelRenderer(this, 0, 9);
		this.HeadFront.setTextureSize(128, 128);
		this.HeadFront.addBox(-1.5F, -1.2F, -2.6F, 3, 3, 3);
		this.HeadFront.setRotationPoint(0F, 12.70783F - 15.88453F, -8.681887F + 3.429656F);

		this.WhiskerR1 = new ModelRenderer(this, 0, 2);
		this.WhiskerR1.setTextureSize(128, 128);
		this.WhiskerR1.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		this.WhiskerR1.setRotationPoint(-0.5F, 13.55453F - 15.88453F, -11.90801F + 3.429656F);

		this.WhiskerR2 = new ModelRenderer(this, 0, 2);
		this.WhiskerR2.setTextureSize(128, 128);
		this.WhiskerR2.addBox(-1F, -0.5F, -1.9F, 1, 1, 2);
		this.WhiskerR2.setRotationPoint(-0.4F, 14.29407F - 15.88453F, -12.12755F + 3.429656F);

		this.WhiskerL1 = new ModelRenderer(this, 0, 2);
		this.WhiskerL1.setTextureSize(128, 128);
		this.WhiskerL1.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		this.WhiskerL1.setRotationPoint(0.5F, 13.54453F - 15.88453F, -10.90801F + 3.429656F);

		this.WhiskerL2 = new ModelRenderer(this, 0, 2);
		this.WhiskerL2.setTextureSize(128, 128);
		this.WhiskerL2.addBox(0F, -0.5F, -1.9F, 1, 1, 2);
		this.WhiskerL2.setRotationPoint(0.4F, 14.29407F - 15.88453F, -11.12755F + 3.429656F);

		this.Nose = new ModelRenderer(this, 0, 0);
		this.Nose.setTextureSize(128, 128);
		this.Nose.addBox(-0.5F, -0.9F, -0.6F, 1, 1, 1);
		this.Nose.setRotationPoint(0F, 13.85954F - 15.88453F, -10.5802F + 3.429656F);

		this.EarR = new ModelRenderer(this, 4, 1);
		this.EarR.setTextureSize(128, 128);
		this.EarR.addBox(-1.5F, -1.1F, -5F, 3, 1, 6);
		this.EarR.setRotationPoint(-2.5F, 10.71813F - 15.88453F, -6.550904F + 3.429656F);

		this.EarL = new ModelRenderer(this, 4, 1);
		this.EarL.setTextureSize(128, 128);
		this.EarL.addBox(-1.5F, -1.1F, -5F, 3, 1, 6);
		this.EarL.setRotationPoint(2.5F, 10.71813F - 15.88453F, -6.550904F + 3.429656F);

		this.Neck1.addChild(this.HeadBase);
		this.Neck1.addChild(this.HeadFront);
		this.Neck1.addChild(this.WhiskerR1);
		this.Neck1.addChild(this.WhiskerR2);
		this.Neck1.addChild(this.WhiskerL1);
		this.Neck1.addChild(this.WhiskerL2);
		this.Neck1.addChild(this.Nose);
		this.Neck1.addChild(this.EarR);
		this.Neck1.addChild(this.EarL);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.LowerBody.rotateAngleX = -0.3186971F;
		this.Tail.rotateAngleX = -1.548105F;
		this.Tail.rotateAngleY = -3.141593F;
		this.Tail.rotateAngleZ = -3.141593F;
		this.BackLegR1.rotateAngleX = 0.3186971F;
		this.BackLegR1.rotateAngleY = -3.141593F;
		this.BackLegR1.rotateAngleZ = 2.884463E-08F;
		this.BackLegR2.rotateAngleX = -1.365852F;
		this.BackLegR2.rotateAngleY = 0.02615925F;
		this.BackLegR2.rotateAngleZ = -0.0163918F;
		this.BackLegL1.rotateAngleX = -0.3186971F;
		this.BackLegL2.rotateAngleX = -1.357649F;
		this.BackLegL2.rotateAngleY = -0.02552362F;
		this.BackLegL2.rotateAngleZ = 0.01577001F;
		this.LegL1.rotateAngleX = -0.004537883F;
		this.LegR1.rotateAngleX = -0.004537883F;
		this.Neck1.rotateAngleX = -0.7740535F;
		this.HeadBase.rotateAngleX = 0.3642503F;
		this.HeadFront.rotateAngleX = 0.4098033F;
		this.WhiskerR1.rotateAngleX = -0.1162607F;
		this.WhiskerR1.rotateAngleY = 1.495321F;
		// WhiskerR1.rotateAngleZ = 0.412645F;
		this.WhiskerR2.rotateAngleX = 0.09258097F;
		this.WhiskerR2.rotateAngleY = 1.58639F;
		// WhiskerR2.rotateAngleZ = 0.4115601F;
		this.WhiskerL1.rotateAngleX = -0.1162607F;
		this.WhiskerL1.rotateAngleY = -1.495321F;
		// WhiskerL1.rotateAngleZ = -0.412645F;
		this.WhiskerL2.rotateAngleX = 0.09258097F;
		this.WhiskerL2.rotateAngleY = -1.58639F;
		// WhiskerL2.rotateAngleZ = -0.4115601F;
		this.Nose.rotateAngleX = 0.2731441F;

		this.EarR.rotateAngleX = -2.424416F;
		this.EarR.rotateAngleY = 0.9441F;
		this.EarR.rotateAngleZ = -1.032535F;

		this.EarL.rotateAngleX = -2.424416F;
		this.EarL.rotateAngleY = -0.9441F;
		this.EarL.rotateAngleZ = 1.032535F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		this.LowerBody.render(scale);
		this.Tail.render(scale);
		this.BackLegR1.render(scale);
		this.BackLegR2.render(scale);
		this.BackLegL1.render(scale);
		this.BackLegL2.render(scale);
		this.LegL1.render(scale);
		this.LegR1.render(scale);
		this.Neck1.render(scale);

	}

	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		// this.Neck1.rotationPointY = 0F +
		// ((RabbitEntityBuckCottontail)LivingEntityIn).getHeadAnchorPointY(partialTickTime)
		// * 0.0F;
		// this.headRotationAngleX =
		// ((RabbitEntityBuckCottontail)LivingEntityIn).getHeadAngleX(partialTickTime);

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		this.Neck1.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.Neck1.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.Neck1.rotateAngleX = this.headRotationAngleX;

		this.Tail.rotateAngleX = (float) Math.PI / 2F;
		// this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) *
		// MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLegL1.rotateAngleX = -0.3186971F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegL2.rotateAngleX = -1.357649F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegR1.rotateAngleX = 0.3186971F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegR2.rotateAngleX = -1.365852F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

		this.LegL1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.LegR1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
	}

}

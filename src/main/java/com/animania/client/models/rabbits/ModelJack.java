package com.animania.client.models.rabbits;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelJack extends ModelBase
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


	public ModelJack()
	{
		this(0.0f);
	}

	public ModelJack(float par1)
	{
		LowerBody = new ModelRenderer( this, 24, 25 );
		LowerBody.setTextureSize( 128, 128 );
		LowerBody.addBox( -3.5F, -2.9F, -3.2F, 7, 7, 13);
		LowerBody.setRotationPoint( 0F, 16.1F, -1.5F + 7F);
		Tail = new ModelRenderer( this, 50, 18 );
		Tail.setTextureSize( 128, 128 );
		Tail.addBox( -1.5F, -2F, -1.7F, 3, 3, 4);
		Tail.setRotationPoint( 0F, 19.29693F, 7.870147F + 7F);
		BackLegR1 = new ModelRenderer( this, 20, 52 );
		BackLegR1.setTextureSize( 128, 128 );
		BackLegR1.addBox( -1F, 0F, -2.5F, 2, 6, 5);
		BackLegR1.setRotationPoint( 3.8F, 16.39402F, 5.77417F + 7F);
		BackLegR2 = new ModelRenderer( this, 0, 43 );
		BackLegR2.setTextureSize( 128, 128 );
		BackLegR2.addBox( -1.02F, -1.4F, -0.7F, 2, 8, 2);
		BackLegR2.setRotationPoint( 3.8F, 22.27699F, 5.412659F + 7F);
		BackLegL1 = new ModelRenderer( this, 20, 52 );
		BackLegL1.setTextureSize( 128, 128 );
		BackLegL1.addBox( -1F, 0F, -2.5F, 2, 6, 5);
		BackLegL1.setRotationPoint( -3.8F, 16.39402F, 5.77417F + 7F);
		BackLegL2 = new ModelRenderer( this, 0, 43 );
		BackLegL2.setTextureSize( 128, 128 );
		BackLegL2.addBox( -0.98F, -1.4F, -0.7F, 2, 8, 2);
		BackLegL2.setRotationPoint( -3.8F, 22.27699F, 5.412659F + 7F);
		LegL1 = new ModelRenderer( this, 0, 54 );
		LegL1.setTextureSize( 128, 128 );
		LegL1.addBox( -1F, 0F, -1.5F, 2, 7, 2);
		LegL1.setRotationPoint( 2.7F, 17.46663F, -3.741055F + 7F);
		LegR1 = new ModelRenderer( this, 0, 54 );
		LegR1.setTextureSize( 128, 128 );
		LegR1.addBox( -1F, 0F, -1.5F, 2, 7, 2);
		LegR1.setRotationPoint( -2.7F, 17.46663F, -3.741055F + 7F);

		Neck1 = new ModelRenderer( this, 0, 27 );
		Neck1.setTextureSize( 128, 128 );
		Neck1.addBox( -2F, -2.2F, -4.8F, 4, 5, 6);
		Neck1.setRotationPoint( 0F, 15.88453F, -3.429656F + 7F);

		HeadBase = new ModelRenderer( this, 0, 16 );
		HeadBase.setTextureSize( 128, 128 );
		HeadBase.addBox( -2.5F, -2.51F, -3.5F, 5, 4, 5);
		HeadBase.setRotationPoint( 0F, 12.30659F - 15.88453F, -5.945282F + 3.429656F);

		HeadFront = new ModelRenderer( this, 0, 9 );
		HeadFront.setTextureSize( 128, 128 );
		HeadFront.addBox( -1.5F, -1.2F, -2.6F, 3, 3, 3);
		HeadFront.setRotationPoint( 0F, 12.70783F - 15.88453F, -8.681887F + 3.429656F);

		WhiskerR1 = new ModelRenderer( this, 0, 2 );
		WhiskerR1.setTextureSize( 128, 128 );
		WhiskerR1.addBox( -0.5F, -0.5F, -2F, 1, 1, 2);
		WhiskerR1.setRotationPoint( -0.5F, 13.55453F - 15.88453F, -11.90801F + 3.429656F);

		WhiskerR2 = new ModelRenderer( this, 0, 2 );
		WhiskerR2.setTextureSize( 128, 128 );
		WhiskerR2.addBox( -1F, -0.5F, -1.9F, 1, 1, 2);
		WhiskerR2.setRotationPoint( -0.4F, 14.29407F - 15.88453F, -12.12755F + 3.429656F );

		WhiskerL1 = new ModelRenderer( this, 0, 2 );
		WhiskerL1.setTextureSize( 128, 128 );
		WhiskerL1.addBox( -0.5F, -0.5F, -2F, 1, 1, 2);
		WhiskerL1.setRotationPoint( 0.5F, 13.54453F - 15.88453F, -10.90801F + 3.429656F);

		WhiskerL2 = new ModelRenderer( this, 0, 2 );
		WhiskerL2.setTextureSize( 128, 128 );
		WhiskerL2.addBox( 0F, -0.5F, -1.9F, 1, 1, 2);
		WhiskerL2.setRotationPoint( 0.4F, 14.29407F - 15.88453F, -11.12755F + 3.429656F );
		
		Nose = new ModelRenderer( this, 0, 0 );
		Nose.setTextureSize( 128, 128 );
		Nose.addBox( -0.5F, -0.9F, -0.6F, 1, 1, 1);
		Nose.setRotationPoint( 0F, 13.85954F - 15.88453F, -10.5802F + 3.429656F);

		EarR = new ModelRenderer( this, 3, 0 );
		EarR.setTextureSize( 128, 128 );
		EarR.addBox( -1.5F, -1.1F, -6.4F, 3, 1, 7);
		EarR.setRotationPoint( -2.5F, 10.71813F - 15.88453F, -6.550904F + 3.429656F);

		EarL = new ModelRenderer( this, 3, 0 );
		EarL.setTextureSize( 128, 128 );
		EarL.addBox( -1.5F, -1.1F, -6.4F, 3, 1, 7);
		EarL.setRotationPoint( 2.5F, 10.71813F - 15.88453F, -6.550904F + 3.429656F);

		this.Neck1.addChild(HeadBase);
		this.Neck1.addChild(HeadFront);
		this.Neck1.addChild(WhiskerR1);
		this.Neck1.addChild(WhiskerR2);
		this.Neck1.addChild(WhiskerL1);
		this.Neck1.addChild(WhiskerL2);
		this.Neck1.addChild(Nose);
		this.Neck1.addChild(EarR);
		this.Neck1.addChild(EarL);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		LowerBody.rotateAngleX = -0.3186971F;
		Tail.rotateAngleX = -1.548105F;
		Tail.rotateAngleY = -3.141593F;
		Tail.rotateAngleZ = -3.141593F;
		BackLegR1.rotateAngleX = 0.3186971F;
		BackLegR1.rotateAngleY = -3.141593F;
		BackLegR1.rotateAngleZ = 2.884463E-08F;
		BackLegR2.rotateAngleX = -1.365852F;
		BackLegR2.rotateAngleY = 0.02615925F;
		BackLegR2.rotateAngleZ = -0.0163918F;
		BackLegL1.rotateAngleX = -0.3186971F;
		BackLegL2.rotateAngleX = -1.357649F;
		BackLegL2.rotateAngleY = -0.02552362F;
		BackLegL2.rotateAngleZ = 0.01577001F;
		LegL1.rotateAngleX = -0.004537883F;
		LegR1.rotateAngleX = -0.004537883F;
		Neck1.rotateAngleX = -0.7740535F;
		HeadBase.rotateAngleX = 0.3642503F;
		HeadFront.rotateAngleX = 0.4098033F;
		WhiskerR1.rotateAngleX = -0.1162607F;
		WhiskerR1.rotateAngleY = 1.495321F;
		//WhiskerR1.rotateAngleZ = 0.412645F;
		WhiskerR2.rotateAngleX = 0.09258097F;
		WhiskerR2.rotateAngleY = 1.58639F;
		//WhiskerR2.rotateAngleZ = 0.4115601F;
		WhiskerL1.rotateAngleX = -0.1162607F;
		WhiskerL1.rotateAngleY = -1.495321F;
		//WhiskerL1.rotateAngleZ = -0.412645F;
		WhiskerL2.rotateAngleX = 0.09258097F;
		WhiskerL2.rotateAngleY = -1.58639F;
		//WhiskerL2.rotateAngleZ = -0.4115601F;
		Nose.rotateAngleX = 0.2731441F;

		EarR.rotateAngleX = -2.424416F;
		EarR.rotateAngleY = 0.9441F;
		EarR.rotateAngleZ = -1.032535F;

		EarL.rotateAngleX = -2.424416F;
		EarL.rotateAngleY = -0.9441F;
		EarL.rotateAngleZ = 1.032535F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		LowerBody.render(scale);
		Tail.render(scale);
		BackLegR1.render(scale);	
		BackLegR2.render(scale);
		BackLegL1.render(scale);	
		BackLegL2.render(scale);
		LegL1.render(scale);
		LegR1.render(scale);
		Neck1.render(scale);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		//this.Neck1.rotationPointY = 0F + ((EntityRabbitBuckCottontail)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 0.0F;
		//this.headRotationAngleX = ((EntityRabbitBuckCottontail)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		this.Neck1.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.Neck1.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.Neck1.rotateAngleX = this.headRotationAngleX;

		this.Tail.rotateAngleX = (float) Math.PI / 2F;
		//this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.BackLegL1.rotateAngleX = -0.3186971F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegL2.rotateAngleX = -1.357649F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegR1.rotateAngleX = 0.3186971F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLegR2.rotateAngleX = -1.365852F + MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		
		this.LegL1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.LegR1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
	}

}

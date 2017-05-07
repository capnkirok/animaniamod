package com.animania.client.models;

import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ModelFerret extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer Head = new ModelRenderer(this, 0, 0);
	ModelRenderer Body;
	ModelRenderer PawRF;
	ModelRenderer PawLF;
	ModelRenderer Neck;
	ModelRenderer EarR;
	ModelRenderer EarL;
	ModelRenderer Body2;
	ModelRenderer PawLB;
	ModelRenderer PawRB;
	ModelRenderer Tail;


	public ModelFerret()
	{
		Body = new ModelRenderer( this, 12, 10 );
		Body.setTextureSize( 64, 32 );
		Body.addBox( -2.01F, -2.01F, -10F, 4, 4, 10);
		Body.setRotationPoint( 0F, 18F, 0F );
		
		PawRF = new ModelRenderer( this, 0, 0 );
		PawRF.setTextureSize( 64, 32 );
		PawRF.addBox( -1F, 0F, -1F, 2, 4, 2);
		PawRF.setRotationPoint( -1F, 19.99997F, -9F );
		PawLF = new ModelRenderer( this, 0, 0 );
		PawLF.setTextureSize( 64, 32 );
		PawLF.addBox( -1F, 0F, -1F, 2, 4, 2);
		PawLF.setRotationPoint( 1F, 19.99997F, -9F );

		Head = new ModelRenderer( this, 0, 10 );
		Head.setTextureSize( 64, 32 );
		Head.addBox( -2.5F, -2F, -3F, 5, 4, 6);
		Head.setRotationPoint( 0F, 14.35004F, -16F );

		EarR = new ModelRenderer( this, 12, 0 );
		EarR.setTextureSize( 64, 32 );
		EarR.addBox( -1F, -1F, -0.5F, 2, 2, 1);
		EarR.setRotationPoint( -2.5F, 12.35004F - 14.35004F, -14F + 16F);

		EarL = new ModelRenderer( this, 12, 0 );
		EarL.setTextureSize( 64, 32 );
		EarL.addBox( -1F, -1F, -0.5F, 2, 2, 1);
		EarL.setRotationPoint( 2.5F, 12.35004F - 14.35004F, -14F + 16F);


		Neck = new ModelRenderer( this, 30, 0 );
		Neck.setTextureSize( 64, 32 );
		Neck.addBox( -2F, -4F, -7F, 4, 4, 7);
		Neck.setRotationPoint( 0F, 19.99997F, -10F);

		Body2 = new ModelRenderer( this, 36, 15 );
		Body2.setTextureSize( 64, 32 );
		Body2.addBox( -2F, -2F, 0F, 4, 4, 10);
		Body2.setRotationPoint( 0F, 18F, 0F );
		
		PawLB = new ModelRenderer( this, 0, 0 );
		PawLB.setTextureSize( 64, 32 );
		PawLB.addBox( -1F, 0F, -1F, 2, 4, 2);
		PawLB.setRotationPoint( 1F, 19.99997F, 9F );
		PawRB = new ModelRenderer( this, 0, 0 );
		PawRB.setTextureSize( 64, 32 );
		PawRB.addBox( -1F, 0F, -1F, 2, 4, 2);
		PawRB.setRotationPoint( -1F, 19.99997F, 9F );
		Tail = new ModelRenderer( this, 44, 3 );
		Tail.setTextureSize( 64, 32 );
		Tail.addBox( -1F, -1F, 0F, 2, 2, 8);
		Tail.setRotationPoint( 0F, 17.5F, 9.5F );

		this.Head.addChild(this.EarR);
		this.Head.addChild(this.EarL);


	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
	{

		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, p_78088_1_);

		this.Head.render(scale);
		this.Neck.render(scale);

		this.Body.render(scale);
		this.Body2.render(scale);
		this.PawLF.render(scale);
		this.PawRF.render(scale);
		this.PawLB.render(scale);
		this.PawRB.render(scale);
		this.Tail.render(scale);


	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityFerretGrey) {
			this.Head.rotationPointY = 14.3F + ((EntityFerretGrey)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 0F; //number should match model Y point
			this.headRotationAngleX = ((EntityFerretGrey)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityFerretWhite) {
			this.Head.rotationPointY = 14.3F + ((EntityFerretWhite)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 0F; //number should match model Y point
			this.headRotationAngleX = ((EntityFerretWhite)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);

		}

	}



	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{

		Neck.rotateAngleX = -0.6108652F;
		Head.rotateAngleX = -3.584692E-08F;
		EarR.rotateAngleX = -3.584692E-08F;
		EarR.rotateAngleY = -6.726944E-16F;
		EarR.rotateAngleZ = -0.7853982F;

		EarL.rotateAngleX = -3.584692E-08F;
		EarL.rotateAngleY = 6.726944E-16F;
		EarL.rotateAngleZ = 0.7853982F;

		Tail.rotateAngleX = -0.526944F;

		float f6 = (180F / (float)Math.PI);
		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;

		this.PawLF.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.PawRF.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.PawLB.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.PawRB.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}






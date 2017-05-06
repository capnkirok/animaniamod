package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;


@SideOnly(Side.CLIENT)
public class ModelCalf extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer Head = new ModelRenderer(this, 0, 0);
	ModelRenderer Body;
	ModelRenderer Horn1;
	ModelRenderer Horn2;
	ModelRenderer Snout;
	ModelRenderer EarL;
	ModelRenderer EarR;
	ModelRenderer TailTop;
	ModelRenderer Tail;
	ModelRenderer TailHair1;
	ModelRenderer TailHair2;
	ModelRenderer Leg0;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	protected float field_78145_g = 8.0F;
	protected float field_78151_h = 4.0F;

	public ModelCalf()
	{
		Body = new ModelRenderer( this, 29, 9 );
		Body.setTextureSize( 64, 64 );
		Body.addBox( -3F, -4.5F, -2.5F, 6, 9, 5);
		Body.setRotationPoint( 0F, 13.5F, 0F );

		Head = new ModelRenderer( this, 3, 4 );
		Head.setTextureSize( 64, 64 );
		Head.addBox( -3.01F, -3F, -2F, 6, 6, 4);
		Head.setRotationPoint( 1.907349E-06F, 11.5F, -6.5F ); //Was 11.5

		EarL = new ModelRenderer( this, 6, 48 );
		EarL.setTextureSize( 64, 64 );
		EarL.addBox( -1F, -1F, -0.5F, 2, 2, 1);
		EarL.setRotationPoint( -4F, -1F, -1F );


		EarR = new ModelRenderer( this, 0, 48 );
		EarR.setTextureSize( 64, 64 );
		EarR.addBox( -1F, -1F, -0.5F, 2, 2, 1);
		EarR.setRotationPoint( 4F, -1F, 0F );

		Snout = new ModelRenderer( this, 50, 51 );
		Snout.setTextureSize( 64, 64 );
		Snout.addBox( -2F, -1.5F, -1F, 4, 3, 2);
		Snout.setRotationPoint( 0F, 2F, -1.5F );

		Horn1 = new ModelRenderer( this, 22, 0 );
		Horn1.setTextureSize( 64, 64 );
		Horn1.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		Horn1.setRotationPoint( -2F, -3.5F, 0F );

		Horn2 = new ModelRenderer( this, 22, 0 );
		Horn2.setTextureSize( 64, 64 );
		Horn2.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		Horn2.setRotationPoint( 2F, -3.5F, 0F );

		TailTop = new ModelRenderer( this, 32, 49 );
		TailTop.setTextureSize( 64, 64 );
		TailTop.addBox( -0.5F, -0.5F, 0F, 1, 1, 1);
		TailTop.setRotationPoint( 0F, 12.1F, 4.5F );

		Tail = new ModelRenderer( this, 28, 15 );
		Tail.setTextureSize( 64, 64 );
		Tail.addBox( -0.5F, -0.5F, -2F, 1, 1, 2);
		Tail.setRotationPoint( 0F, 12F, 4.5F );

		TailHair1 = new ModelRenderer( this, 23, 52 );
		TailHair1.setTextureSize( 64, 64 );
		TailHair1.addBox( -1F, 0F, -3.05F, 2, 0, 3);
		TailHair1.setRotationPoint( 0F, 0.25F, -1.3F );

		TailHair2 = new ModelRenderer( this, 23, 52 );
		TailHair2.setTextureSize( 64, 64 );
		TailHair2.addBox( -1F, 0F, -2.5F, 2, 0, 3);
		TailHair2.setRotationPoint( -1.769535E-16F, 0F, -2.547969F );

		Leg0 = new ModelRenderer( this, 6, 34 );
		Leg0.setTextureSize( 64, 64 );
		Leg0.addBox( -1F, 1F, -1F, 2, 8, 2);
		Leg0.setRotationPoint( -2F, 15F, -3.5F );
		Leg1 = new ModelRenderer( this, 16, 34 );
		Leg1.setTextureSize( 64, 64 );
		Leg1.addBox( -1F, 1F, -1F, 2, 8, 2);
		Leg1.setRotationPoint( 2F, 15F, -3.5F );
		Leg2 = new ModelRenderer( this, 16, 34 );
		Leg2.setTextureSize( 64, 64 );
		Leg2.addBox( -1F, 1F, -1F, 2, 8, 2);
		Leg2.setRotationPoint( -2F, 15F, 3.5F );
		Leg3 = new ModelRenderer( this, 12, 34 );
		Leg3.setTextureSize( 64, 64 );
		Leg3.addBox( -1F, 1F, -1F, 2, 8, 2);
		Leg3.setRotationPoint( 2F, 15F, 3.5F );

		this.Head.addChild(this.Horn1);
		this.Head.addChild(this.Horn2);
		this.Head.addChild(this.Snout);
		this.Head.addChild(this.EarL);
		this.Head.addChild(this.EarR);
		this.Tail.addChild(this.TailHair1);
		this.Tail.addChild(this.TailHair2);


	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
	{

		Tail.rotateAngleX =  -2F;
		Tail.rotateAngleY = -3.141593F;
		Tail.rotateAngleZ = -3.141593F;
		TailHair1.rotateAngleZ = -2.280276F;
		TailHair2.rotateAngleZ = 2.432113F;

		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

		this.Head.render(p_78088_7_);
		this.Body.render(p_78088_7_);
		this.Leg0.render(p_78088_7_);
		this.Leg1.render(p_78088_7_);
		this.Leg2.render(p_78088_7_);
		this.Leg3.render(p_78088_7_);
		this.TailTop.render(p_78088_7_);
		this.Tail.render(p_78088_7_);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityCalfHolstein) {
			this.Head.rotationPointY = 10.0F + ((EntityCalfHolstein)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityCalfHolstein)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityCalfFriesian) {
			this.Head.rotationPointY = 10.0F + ((EntityCalfFriesian)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityCalfFriesian)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityCalfHereford) {
			this.Head.rotationPointY = 10.0F + ((EntityCalfHereford)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityCalfHereford)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityCalfAngus) {
			this.Head.rotationPointY = 10.0F + ((EntityCalfAngus)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 6.0F;
			this.headRotationAngleX = ((EntityCalfAngus)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		}
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = (180F / (float)Math.PI);
		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;
		this.Body.rotateAngleX = ((float)Math.PI / 2F);

		this.TailTop.rotateAngleX = ((float)Math.PI / 2F);
		this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;

		this.Leg0.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}






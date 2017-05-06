package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.common.entities.pigs.EntityHogLargeBlack;

@SideOnly(Side.CLIENT)
public class ModelHogLargeBlack extends ModelBase
{
	private float headRotationAngleX;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Body;
	ModelRenderer Snout;
	ModelRenderer Head;
	ModelRenderer Ear1;
	ModelRenderer Ear1a;
	ModelRenderer Ear1b;
	ModelRenderer Ear2;
	ModelRenderer Ear2a;
	ModelRenderer Ear2b;
	ModelRenderer Tail1;
	ModelRenderer Tail1a;
	ModelRenderer Tail1b;
	ModelRenderer Tail1c;
	ModelRenderer Tail1d;
	ModelRenderer Nipple1;
	ModelRenderer Nipple2;
	ModelRenderer Nipple3;
	ModelRenderer Nipple4;
	ModelRenderer Nipple5;
	ModelRenderer Nipple6;
	ModelRenderer BlockA;
	ModelRenderer BlockB;

	public ModelHogLargeBlack()
	{
		this( 0.0f );
	}

	public ModelHogLargeBlack( float par1 )
	{
		Leg1 = new ModelRenderer( this, 0, 16 );
		Leg1.setTextureSize( 64, 32 );
		Leg1.addBox( -2F, 0F, -2F, 4, 6, 4);
		Leg1.setRotationPoint( 3F, 18F, -5F );
		Leg2 = new ModelRenderer( this, 0, 16 );
		Leg2.setTextureSize( 64, 32 );
		Leg2.addBox( -2F, 0F, -2F, 4, 6, 4);
		Leg2.setRotationPoint( -3F, 18F, -5F );
		Leg3 = new ModelRenderer( this, 0, 16 );
		Leg3.setTextureSize( 64, 32 );
		Leg3.addBox( -2F, 0F, -2F, 4, 6, 4);
		Leg3.setRotationPoint( 3F, 18F, 7F );
		Leg4 = new ModelRenderer( this, 0, 16 );
		Leg4.setTextureSize( 64, 32 );
		Leg4.addBox( -2F, 0F, -2F, 4, 6, 4);
		Leg4.setRotationPoint( -3F, 18F, 7F );

		Body = new ModelRenderer( this, 28, 8 );
		Body.setTextureSize( 64, 32 );
		Body.addBox( -5F, -10F, -7F, 10, 16, 8);
		Body.setRotationPoint( 0F, 11F, 2F );

		Nipple1 = new ModelRenderer( this, 17, 22 );
		Nipple1.setTextureSize( 64, 32 );
		Nipple1.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple1.setRotationPoint( -1.5F, 18.25F, 3.5F );
		Nipple2 = new ModelRenderer( this, 17, 22 );
		Nipple2.setTextureSize( 64, 32 );
		Nipple2.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple2.setRotationPoint( 1.5F, 18.25F, 3.5F );
		Nipple3 = new ModelRenderer( this, 17, 24 );
		Nipple3.setTextureSize( 64, 32 );
		Nipple3.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple3.setRotationPoint( -1.5F, 18.25F, 0.4999999F );
		Nipple4 = new ModelRenderer( this, 17, 24 );
		Nipple4.setTextureSize( 64, 32 );
		Nipple4.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple4.setRotationPoint( 1.5F, 18.25F, 0.4999997F );
		Nipple5 = new ModelRenderer( this, 17, 26 );
		Nipple5.setTextureSize( 64, 32 );
		Nipple5.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple5.setRotationPoint( -1.5F, 18.25F, -2.5F );
		Nipple6 = new ModelRenderer( this, 17, 26 );
		Nipple6.setTextureSize( 64, 32 );
		Nipple6.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Nipple6.setRotationPoint( 1.5F, 18.25F, -2.5F );

		BlockA = new ModelRenderer( this, 38, 0 );
		BlockA.setTextureSize( 64, 32 );
		BlockA.addBox( -1F, -1F, -1.5F, 2, 2, 3);
		BlockA.setRotationPoint( 0F, 18F, 3F );
		BlockB = new ModelRenderer( this, 44, 1 );
		BlockB.setTextureSize( 64, 32 );
		BlockB.addBox( -1F, -1F, -1F, 2, 2, 2);
		BlockB.setRotationPoint( 0F, 19F, 5F );

		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 64, 32 );
		Head.addBox( -4F, -4F, -8F, 8, 8, 8);
		Head.setRotationPoint( 0F, 11F, -6F ); //was -6F

		Snout = new ModelRenderer( this, 16, 16 );
		Snout.setTextureSize( 64, 32 );
		Snout.addBox( -2F, 0F, -9F, 4, 3, 1);
		//Snout.setRotationPoint( 0F, 12F, -6F );
		Snout.setRotationPoint( 0F, 0F, 0F );

		Ear1 = new ModelRenderer( this, 3, 28 );
		Ear1.setTextureSize( 64, 32 );
		Ear1.addBox( -1.5F, -2.5F, -0.5F, 3, 3, 1);
		Ear1.setRotationPoint( 3F, 8.5F - 11F, -12F + 5F );

		Ear1a = new ModelRenderer( this, 2, 27 );
		Ear1a.setTextureSize( 64, 32 );
		Ear1a.addBox( -1.5F, -0.5F, -1F, 3, 1, 2);
		Ear1a.setRotationPoint( 4.291645F, 7.079988F - 11.7F, -14.35907F + 5.8F );

		Ear1b = new ModelRenderer( this, 3, 28 );
		Ear1b.setTextureSize( 64, 32 );
		Ear1b.addBox( -1F, -0.5F, -0.5F, 2, 1, 1);
		Ear1b.setRotationPoint( 3.572421F, 7.88666F - 11.4F, -14.07271F + 6F);

		Ear2 = new ModelRenderer( this, 3, 28 );
		Ear2.setTextureSize( 64, 32 );
		Ear2.addBox( -1.5F, -2.5F, -0.5F, 3, 3, 1);
		Ear2.setRotationPoint( -3F, 8.5F - 11F, -12F + 5F );

		Ear2a = new ModelRenderer( this, 2, 27 );
		Ear2a.setTextureSize( 64, 32 );
		Ear2a.addBox( -1.5F, -0.5F, -1F, 3, 1 , 2);
		Ear2a.setRotationPoint( -4.291645F, 7.079988F - 11.7F, -14.35907F + 5.8F );

		Ear2b = new ModelRenderer( this, 3, 28 );
		Ear2b.setTextureSize( 64, 32 );
		Ear2b.addBox( -1F, -0.5F, -0.5F, 2, 1, 1);
		Ear2b.setRotationPoint( -3.572421F, 7.88666F - 11.4F, -14.07271F + 6F );

		Tail1 = new ModelRenderer( this, 26, 0 );
		Tail1.setTextureSize( 64, 32 );
		Tail1.addBox( -2.5F, -0.5F, -0.5F, 3, 1, 1);
		Tail1.setRotationPoint( 1.5F, 11.5F, 8.499999F );

		Tail1a = new ModelRenderer( this, 25, 0 );
		Tail1a.setTextureSize( 64, 32 );
		Tail1a.addBox( -0.5F, -0.5F, -2.5F, 1, 1, 3);
		Tail1a.setRotationPoint( -1.408867F - 1.5F, 12.49008F - 9.5F , 9.247147F - 9.299999F);

		Tail1b = new ModelRenderer( this, 26, 0 );
		Tail1b.setTextureSize( 64, 32 );
		Tail1b.addBox( 0F, -0.5F, -0.5F, 2, 1, 1);
		Tail1b.setRotationPoint( -0.8336565F - 1.5F, 15.46033F - 11.5F, 9.558231F - 9.299999F);

		Tail1c = new ModelRenderer( this, 26, 0 );
		Tail1c.setTextureSize( 64, 32 );
		Tail1c.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Tail1c.setRotationPoint( 1.635806F - 1.5F, 14.38999F - 11.5F, 9.479872F - 9.299999F);

		Tail1d = new ModelRenderer( this, 26, 0 );
		Tail1d.setTextureSize( 64, 32 );
		Tail1d.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Tail1d.setRotationPoint( 0.6080551F - 1.5F, 13.43201F - 11.5F, 9.318629F - 9.299999F);

		this.Head.addChild(this.Snout);
		this.Head.addChild(this.Ear1);
		this.Head.addChild(this.Ear1a);
		this.Head.addChild(this.Ear1b);
		this.Head.addChild(this.Ear2);
		this.Head.addChild(this.Ear2a);
		this.Head.addChild(this.Ear2b);

		this.Tail1.addChild(this.Tail1a);
		this.Tail1.addChild(this.Tail1b);
		this.Tail1.addChild(this.Tail1c);
		this.Tail1.addChild(this.Tail1d);


	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Leg1.render(scale);
		this.Leg2.render(scale);
		this.Leg3.render(scale);
		this.Leg4.render(scale);
		this.Body.render(scale);

		this.Nipple1.render(scale);
		this.Nipple2.render(scale);
		this.Nipple3.render(scale);
		this.Nipple4.render(scale);
		this.Nipple5.render(scale);
		this.Nipple6.render(scale);

		if (Animania.showParts) {
			this.BlockA.render(scale);
			this.BlockB.render(scale);
		}

		this.Head.render(scale);
		this.Tail1.render(scale);

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityHogLargeBlack) {
			this.Head.rotationPointY = 11.0F + ((EntityHogLargeBlack)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 5.5F; //number should match model Y point
			this.headRotationAngleX = ((EntityHogLargeBlack)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} 

	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{

		Ear1.rotateAngleX = 0.5235987F;
		Ear1.rotateAngleY = 0.5235987F;
		Ear1.rotateAngleZ = 0.8726646F;

		Ear1a.rotateAngleX = 0.5235987F;
		Ear1a.rotateAngleY = 0.5235987F;
		Ear1a.rotateAngleZ = 0.8726646F;

		Ear1b.rotateAngleX = 0.5235987F;
		Ear1b.rotateAngleY = 0.5235987F;
		Ear1b.rotateAngleZ = 0.8726646F;

		Ear2.rotateAngleX = 0.5235987F;
		Ear2.rotateAngleY = -0.5235987F;
		Ear2.rotateAngleZ = -0.8726646F;

		Ear2a.rotateAngleX = 0.5235987F;
		Ear2a.rotateAngleY = -0.5235987F;
		Ear2a.rotateAngleZ = -0.8726646F;

		Ear2b.rotateAngleX = 0.5235987F;
		Ear2b.rotateAngleY = -0.5235987F;
		Ear2b.rotateAngleZ = -0.8726646F;

		Tail1.rotateAngleX = 0.1409582F;
		Tail1.rotateAngleY = 0.2046205F;
		Tail1.rotateAngleZ = 1.882951E-10F;
		Tail1a.rotateAngleX = 1.429837F;
		Tail1a.rotateAngleY = -2.936972F;
		Tail1a.rotateAngleZ = -3.141593F;

		Nipple1.rotateAngleX = 1.570796F;
		Nipple2.rotateAngleX = 1.570796F;
		Nipple3.rotateAngleX = 1.570796F;
		Nipple4.rotateAngleX = 1.570796F;
		Nipple5.rotateAngleX = 1.570796F;
		Nipple6.rotateAngleX = 1.570796F;

		if (Animania.showParts) {
			BlockA.rotateAngleX = 0.2617994F;
			BlockB.rotateAngleX = 0F;
		}

		float f6 = (180F / (float)Math.PI);
		this.Head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.Head.rotateAngleX = this.headRotationAngleX;
		this.Body.rotateAngleX = ((float)Math.PI / 2F);


		this.Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}




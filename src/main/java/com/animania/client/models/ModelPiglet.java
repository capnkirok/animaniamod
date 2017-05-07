package com.animania.client.models;

import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPiglet extends ModelBase
{
	private float headRotationAngleX;
	ModelRenderer Body;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Head;
	ModelRenderer Snout;
	ModelRenderer Ear2;
	ModelRenderer Ear2a;
	ModelRenderer Ear2b;
	ModelRenderer Ear1;
	ModelRenderer Ear1a;
	ModelRenderer Ear1b;
	ModelRenderer Tail1;
	ModelRenderer Tail1a;
	ModelRenderer Tail1b;
	ModelRenderer Tail1c;
	ModelRenderer Tail1d;

	public ModelPiglet()
	{
		this( 0.0f );
	}

	public ModelPiglet( float par1 )
	{
		Leg1 = new ModelRenderer( this, 0, 8 );
		Leg1.setTextureSize( 32, 32 );
		Leg1.addBox( -1F, 0F, -1F, 2, 3, 2);
		Leg1.setRotationPoint( 2F, 21F, -11.5F + 12F );
		Leg2 = new ModelRenderer( this, 0, 8 );
		Leg2.setTextureSize( 32, 32 );
		Leg2.addBox( -1F, 0F, -1F, 2, 3, 2);
		Leg2.setRotationPoint( -2F, 21F, -11.5F + 12F );
		Leg3 = new ModelRenderer( this, 0, 8 );
		Leg3.setTextureSize( 32, 32 );
		Leg3.addBox( -1F, 0F, -1F, 2, 3, 2);
		Leg3.setRotationPoint( 2F, 21F, -5.25F + 12F );
		Leg4 = new ModelRenderer( this, 0, 8 );
		Leg4.setTextureSize( 32, 32 );
		Leg4.addBox( -1F, 0F, -1F, 2, 3, 2);
		Leg4.setRotationPoint( -2F, 21F, -5.25F + 12F );
	
		Body = new ModelRenderer( this, 0, 16 );
		Body.setTextureSize( 32, 32 );
		Body.addBox( -3F, -4F, -2F, 6, 8, 4);
		//Body.setRotationPoint( 0F, 19F, -9F );
		Body.setRotationPoint( 0F, 19F, 3F );
		
		Head = new ModelRenderer( this, 0, 0 );
		Head.setTextureSize( 32, 32 );
		Head.addBox( -2.5F, -2F, -4F, 5, 4, 4);
		Head.setRotationPoint( 0F, 17F, -11F + 12F);

		Snout = new ModelRenderer( this, 10, 9 );
		Snout.setTextureSize( 32, 32 );
		Snout.addBox( -2F, -1.5F, -0.5F, 4, 3, 1);
		Snout.setRotationPoint( 0F, 18F-17F, -15F +11F  );
		
		Ear2 = new ModelRenderer( this, 19, 5 );
		Ear2.setTextureSize( 32, 32 );
		//Ear2.addBox( -1.5F, -2.5F, -0.5F, 3, 3, 1);
		Ear2.addBox( -1.5F, -2.5F, -0.5F, 2, 2, 1);
		Ear2.setRotationPoint( -1.6F + .2F, 16F-17F, -13F+11F );

		Ear2a = new ModelRenderer( this, 18, 4 );
		Ear2a.setTextureSize( 32, 32 );
		//Ear2a.addBox( -1.5F, -0.5F, -1F, 3, 1, 2);
		Ear2a.addBox( -1.5F, -0.5F, -1F, 2, 1, 2);
		Ear2a.setRotationPoint( -2.978925F + .2F, 14.29448F-17F, -13.34608F+11F );

		Ear2b = new ModelRenderer( this, 19, 5 );
		Ear2b.setTextureSize( 32, 32 );
		//Ear2b.addBox( -1F, -0.5F, -0.5F, 2, 1, 1);
		Ear2b.addBox( -1F, -0.5F, -0.5F, 1, 1, 1);
		Ear2b.setRotationPoint( -2.675599F + .5F, 14.84707F-17F, -13.72376F+11F );

		//Right ear
		Ear1 = new ModelRenderer( this, 19, 5 );
		Ear1.setTextureSize( 32, 32 );
		Ear1.addBox( -1.5F, -2.5F, -0.5F, 2, 2, 1);
		Ear1.setRotationPoint( 1.6F+.6F, 16F-17F+.6F, -13F+11F  );

		Ear1a = new ModelRenderer( this, 18, 4 );
		Ear1a.setTextureSize( 32, 32 );
		Ear1a.addBox( -1.5F, -0.5F, -1F, 2, 1, 2);
		Ear1a.setRotationPoint( 2.978925F+.6F, 14.29448F-17F+.6F, -13.34608F+11F );

		Ear1b = new ModelRenderer( this, 19, 5 );
		Ear1b.setTextureSize( 32, 32 );
		Ear1b.addBox( -1F, -0.5F, -0.5F, 1, 1, 1);
		Ear1b.setRotationPoint( 2.277831F+.6F+.1F, 14.47453F-17F+.6F+.5F, -13.47276F+11F );

		Tail1 = new ModelRenderer( this, 19, 0 );
		Tail1.setTextureSize( 32, 32 );
		Tail1.addBox( -2.5F, -0.5F, -0.5F, 3, 1, 1);
		Tail1.setRotationPoint( 1F, 17.5F + 5.0F, -4.8F + 12F + 2F );

		Tail1a = new ModelRenderer( this, 18, 0 );
		Tail1a.setTextureSize( 32, 32 );
		Tail1a.addBox( -0.5F, -0.5F, -2.5F, 1, 1, 3);
		Tail1a.setRotationPoint( -1.168969F -1F, 17.7F-17.5F, -4.431796F + 4.8F);

		Tail1b = new ModelRenderer( this, 20, 1 );
		Tail1b.setTextureSize( 32, 32 );
		Tail1b.addBox( -1F, -0.5F, -0.5F, 2, 1, 1);
		Tail1b.setRotationPoint( 0.005638207F -1F, 19.766F - 17.5F, -4.387788F + 4.8F); //bottom

		Tail1c = new ModelRenderer( this, 24, 0 );
		Tail1c.setTextureSize( 32, 32 );
		Tail1c.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Tail1c.setRotationPoint( 0.8042848F - 1F, 19.06972F-17.5F, -4.6054F + 4.8F); //small

		Tail1d = new ModelRenderer( this, 22, 1 );
		Tail1d.setTextureSize( 32, 32 );
		Tail1d.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Tail1d.setRotationPoint( 0.1375821F -1F, 18.54738F -17.5F, -4.553761F + 4.8F); //small
		
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

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Leg1.render(scale);
		this.Leg2.render(scale);
		this.Leg3.render(scale);
		this.Leg4.render(scale);
		this.Body.render(scale);
	
		this.Head.render(scale);
		this.Tail1.render(scale * .8F);
		
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{

		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);

		if (entitylivingbaseIn instanceof EntityPigletYorkshire) {
			this.Head.rotationPointY = 17.0F + ((EntityPigletYorkshire)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 3.0F; //number should match model Y point
			this.headRotationAngleX = ((EntityPigletYorkshire)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityPigletOldSpot) {
			this.Head.rotationPointY = 17.0F + ((EntityPigletOldSpot)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 3.0F; //number should match model Y point
			this.headRotationAngleX = ((EntityPigletOldSpot)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityPigletDuroc) {
			this.Head.rotationPointY = 17.0F + ((EntityPigletDuroc)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 3.0F; //number should match model Y point
			this.headRotationAngleX = ((EntityPigletDuroc)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		} else if (entitylivingbaseIn instanceof EntityPigletLargeWhite) {
			this.Head.rotationPointY = 17.0F + ((EntityPigletLargeWhite)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 3.0F; //number should match model Y point
			this.headRotationAngleX = ((EntityPigletLargeWhite)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
		}

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		
		Body.rotateAngleX = 1.570796F;
		Leg3.rotateAngleX = -1.421085E-14F;
		Leg4.rotateAngleX = -1.421085E-14F;
		Leg1.rotateAngleX = -1.421085E-14F;
		Leg2.rotateAngleX = -1.421085E-14F;
		Head.rotateAngleX = -1.421085E-14F;
		Snout.rotateAngleX = -1.421085E-14F;
		
		Ear2.rotateAngleX = -0.2617994F;
		Ear2.rotateAngleY = -0.3490658F;
		Ear2.rotateAngleZ = -0.6981317F;
		Ear2a.rotateAngleX = -0.2617995F;
		Ear2a.rotateAngleY = -0.3490659F;
		Ear2a.rotateAngleZ = -0.6981319F;
		Ear2b.rotateAngleX = -0.2617995F;
		Ear2b.rotateAngleY = -0.3490659F;
		Ear2b.rotateAngleZ = -0.6981319F;
		Ear1.rotateAngleX = -0.2617994F;
		Ear1.rotateAngleY = 0.3490658F;
		Ear1.rotateAngleZ = 0.6981317F;
		Ear1a.rotateAngleX = -0.2617995F;
		Ear1a.rotateAngleY = 0.3490659F;
		Ear1a.rotateAngleZ = 0.6981319F;
		Ear1b.rotateAngleX = -0.2617995F;
		Ear1b.rotateAngleY = 0.3490659F;
		Ear1b.rotateAngleZ = 0.6981319F;
		
		Tail1a.rotateAngleY = 1.5F;
		Tail1a.rotateAngleX = 1.5F;
		
		Snout.postRender(par6 * .60f);
		
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




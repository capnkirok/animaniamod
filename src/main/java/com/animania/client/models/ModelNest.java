package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNest extends ModelBase
{
	ModelRenderer Nest1;
	ModelRenderer Fluff3;
	ModelRenderer Fluff1;
	ModelRenderer Nest2;
	ModelRenderer Nest3;
	ModelRenderer Nest4;
	ModelRenderer Nest5;
	ModelRenderer Nest6;
	ModelRenderer Nest7;
	ModelRenderer Nest8;
	ModelRenderer Block;
	ModelRenderer Fluff2;
	ModelRenderer Fluff4;
	ModelRenderer Fluff5;
	ModelRenderer Egg1;
	ModelRenderer Egg1a;
	ModelRenderer Egg1b;
	ModelRenderer Egg1c;
	ModelRenderer Egg2;
	ModelRenderer Egg2a;
	ModelRenderer Egg2b;
	ModelRenderer Egg2c;
	ModelRenderer Egg3;
	ModelRenderer Egg3a;
	ModelRenderer Egg3b;
	ModelRenderer Egg3c;
	
	ModelRenderer BEgg1;
	ModelRenderer BEgg1a;
	ModelRenderer BEgg1b;
	ModelRenderer BEgg1c;
	ModelRenderer BEgg2;
	ModelRenderer BEgg2a;
	ModelRenderer BEgg2b;
	ModelRenderer BEgg2c;
	ModelRenderer BEgg3;
	ModelRenderer BEgg3a;
	ModelRenderer BEgg3b;
	ModelRenderer BEgg3c;
	


	public ModelNest()
	{
		this( 0.0f );
	}

	public ModelNest(float par1)
	{
		Nest1 = new ModelRenderer( this, 0, 0 );
		Nest1.setTextureSize( 128, 64 );
		Nest1.addBox( -3F, -2F, -8F, 6, 4, 3);
		Nest1.setRotationPoint( 0F, 22F, 0F );
		Fluff3 = new ModelRenderer( this, -16, 38 );
		Fluff3.setTextureSize( 128, 64 );
		Fluff3.addBox( -8F, 0F, -8F, 16, 0, 16);
		Fluff3.setRotationPoint( 0F, 21.5F, 0F );
		Fluff1 = new ModelRenderer( this, -16, 38 );
		Fluff1.setTextureSize( 128, 64 );
		Fluff1.addBox( -8F, 0F, -8F, 16, 0, 16);
		Fluff1.setRotationPoint( 0F, 22.5F, 0F );
		Nest2 = new ModelRenderer( this, 0, 7 );
		Nest2.setTextureSize( 128, 64 );
		Nest2.addBox( -3F, -2F, 5F, 6, 4, 3);
		Nest2.setRotationPoint( 0F, 22F, 0F );
		Nest3 = new ModelRenderer( this, 0, 14 );
		Nest3.setTextureSize( 128, 64 );
		Nest3.addBox( -3F, -2F, 5F, 6, 4, 3);
		Nest3.setRotationPoint( 0F, 22F, 0F );
		Nest4 = new ModelRenderer( this, 19, 0 );
		Nest4.setTextureSize( 128, 64 );
		Nest4.addBox( -3F, -2F, 5F, 6, 4, 3);
		Nest4.setRotationPoint( 0F, 22F, 0F );
		Nest5 = new ModelRenderer( this, 18, 7 );
		Nest5.setTextureSize( 128, 64 );
		Nest5.addBox( -3.5F, -2F, 5F, 7, 4, 3);
		Nest5.setRotationPoint( 0F, 21.9F, 0F );
		Nest6 = new ModelRenderer( this, 18, 14 );
		Nest6.setTextureSize( 128, 64 );
		Nest6.addBox( -3.5F, -2F, 5F, 7, 4, 3);
		Nest6.setRotationPoint( 0F, 21.9F, 0F );
		Nest7 = new ModelRenderer( this, 18, 20 );
		Nest7.setTextureSize( 128, 64 );
		Nest7.addBox( -3.5F, -2F, 5F, 7, 4, 3);
		Nest7.setRotationPoint( 0F, 21.9F, 0F );
		Nest8 = new ModelRenderer( this, 41, 0 );
		Nest8.setTextureSize( 128, 64 );
		Nest8.addBox( -3.5F, -2F, 5F, 7, 4, 3);
		Nest8.setRotationPoint( 0F, 21.9F, 0F );
		Block = new ModelRenderer( this, 13, 8 );
		Block.setTextureSize( 128, 64 );
		Block.addBox( -5.5F, -1.5F, -5.5F, 11, 3, 11);
		Block.setRotationPoint( 0F, 22.5F, 0F );
		Fluff2 = new ModelRenderer( this, -16, 38 );
		Fluff2.setTextureSize( 128, 64 );
		Fluff2.addBox( -8F, 0F, -8F, 16, 0, 16);
		Fluff2.setRotationPoint( 0F, 23.8F, 0F );
		Fluff4 = new ModelRenderer( this, 18, 38 );
		Fluff4.setTextureSize( 128, 64 );
		Fluff4.addBox( -8F, 0F, -8F, 16, 0, 16);
		Fluff4.setRotationPoint( 0F, 19.7F, 0F );
		Fluff5 = new ModelRenderer( this, 18, 38 );
		Fluff5.setTextureSize( 128, 64 );
		Fluff5.addBox( -8F, 0F, -8F, 16, 0, 16);
		Fluff5.setRotationPoint( 0F, 19.65F, 0F );
		Egg1 = new ModelRenderer( this, 68, 2 );
		Egg1.setTextureSize( 128, 64 );
		Egg1.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		Egg1.setRotationPoint( -2.5F, 20.5F, 1F );
		Egg1a = new ModelRenderer( this, 71, 4 );
		Egg1a.setTextureSize( 128, 64 );
		Egg1a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg1a.setRotationPoint( -2.768189F, 18.83211F, 1.45686F );
		Egg1b = new ModelRenderer( this, 72, 3 );
		Egg1b.setTextureSize( 128, 64 );
		Egg1b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Egg1b.setRotationPoint( -2.845569F, 18.35441F, 1.582717F );
		Egg1c = new ModelRenderer( this, 71, 6 );
		Egg1c.setTextureSize( 128, 64 );
		Egg1c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg1c.setRotationPoint( -2.270879F, 21.92846F, 0.6037322F );
		Egg2 = new ModelRenderer( this, 73, 4 );
		Egg2.setTextureSize( 128, 64 );
		Egg2.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		Egg2.setRotationPoint( 0F, 20.5F, -2.25F );
		Egg2a = new ModelRenderer( this, 73, 3 );
		Egg2a.setTextureSize( 128, 64 );
		Egg2a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg2a.setRotationPoint( -1.557231F, 19.99435F, -2.867943F );
		Egg2b = new ModelRenderer( this, 75, 6 );
		Egg2b.setTextureSize( 128, 64 );
		Egg2b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Egg2b.setRotationPoint( -2.000357F, 19.84989F, -3.049029F );
		Egg2c = new ModelRenderer( this, 78, 2 );
		Egg2c.setTextureSize( 128, 64 );
		Egg2c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg2c.setRotationPoint( 1.336568F, 20.93341F, -1.724865F );
		Egg3 = new ModelRenderer( this, 76, 3 );
		Egg3.setTextureSize( 128, 64 );
		Egg3.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		Egg3.setRotationPoint( 2F, 20.5F, 2F );
		Egg3a = new ModelRenderer( this, 77, 6 );
		Egg3a.setTextureSize( 128, 64 );
		Egg3a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg3a.setRotationPoint( 3.119674F, 19.40652F, 2.783019F );
		Egg3b = new ModelRenderer( this, 78, 4 );
		Egg3b.setTextureSize( 128, 64 );
		Egg3b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		Egg3b.setRotationPoint( 3.438589F, 19.09068F, 3.003399F );
		Egg3c = new ModelRenderer( this, 75, 2 );
		Egg3c.setTextureSize( 128, 64 );
		Egg3c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		Egg3c.setRotationPoint( 1.039286F, 21.43386F, 1.3255F );

		
		BEgg1 = new ModelRenderer( this, 68, 22 );
		BEgg1.setTextureSize( 128, 64 );
		BEgg1.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		BEgg1.setRotationPoint( -2.5F, 20.5F, 1F );
		BEgg1a = new ModelRenderer( this, 71, 24 );
		BEgg1a.setTextureSize( 128, 64 );
		BEgg1a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg1a.setRotationPoint( -2.768189F, 18.83211F, 1.45686F );
		BEgg1b = new ModelRenderer( this, 72, 23 );
		BEgg1b.setTextureSize( 128, 64 );
		BEgg1b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		BEgg1b.setRotationPoint( -2.845569F, 18.35441F, 1.582717F );
		BEgg1c = new ModelRenderer( this, 71, 26 );
		BEgg1c.setTextureSize( 128, 64 );
		BEgg1c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg1c.setRotationPoint( -2.270879F, 21.92846F, 0.6037322F );
		BEgg2 = new ModelRenderer( this, 73, 24 );
		BEgg2.setTextureSize( 128, 64 );
		BEgg2.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		BEgg2.setRotationPoint( 0F, 20.5F, -2.25F );
		BEgg2a = new ModelRenderer( this, 73, 23 );
		BEgg2a.setTextureSize( 128, 64 );
		BEgg2a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg2a.setRotationPoint( -1.557231F, 19.99435F, -2.867943F );
		BEgg2b = new ModelRenderer( this, 75, 26 );
		BEgg2b.setTextureSize( 128, 64 );
		BEgg2b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		BEgg2b.setRotationPoint( -2.000357F, 19.84989F, -3.049029F );
		BEgg2c = new ModelRenderer( this, 78, 22 );
		BEgg2c.setTextureSize( 128, 64 );
		BEgg2c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg2c.setRotationPoint( 1.336568F, 20.93341F, -1.724865F );
		BEgg3 = new ModelRenderer( this, 76, 23 );
		BEgg3.setTextureSize( 128, 64 );
		BEgg3.addBox( -1.5F, -1.5F, -1.5F, 3, 3, 3);
		BEgg3.setRotationPoint( 2F, 20.5F, 2F );
		BEgg3a = new ModelRenderer( this, 77, 26 );
		BEgg3a.setTextureSize( 128, 64 );
		BEgg3a.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg3a.setRotationPoint( 3.119674F, 19.40652F, 2.783019F );
		BEgg3b = new ModelRenderer( this, 78, 24 );
		BEgg3b.setTextureSize( 128, 64 );
		BEgg3b.addBox( -0.5F, -0.5F, -0.5F, 1, 1, 1);
		BEgg3b.setRotationPoint( 3.438589F, 19.09068F, 3.003399F );
		BEgg3c = new ModelRenderer( this, 75, 22 );
		BEgg3c.setTextureSize( 128, 64 );
		BEgg3c.addBox( -1F, -0.5F, -1F, 2, 1, 2);
		BEgg3c.setRotationPoint( 1.039286F, 21.43386F, 1.3255F );
		
	}

	@Override
	public void render(Entity entityIn, float nestType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{


		Nest1.render(scale);
		Fluff3.render(scale);
		Fluff1.render(scale);
		Nest2.render(scale);
		Nest3.render(scale);
		Nest4.render(scale);
		Nest5.render(scale);
		Nest6.render(scale);
		Nest7.render(scale);
		Nest8.render(scale);
		Block.render(scale);
		Fluff2.render(scale);
		Fluff4.render(scale);
		Fluff5.render(scale);

		if (nestType == 1.0F || nestType == 4.0F || nestType == 7.0F) {

			Egg1.render(scale);
			Egg1a.render(scale);
			Egg1b.render(scale);
			Egg1c.render(scale);

		} else if (nestType == 2.0F || nestType == 5.0F || nestType == 8.0F) {

			Egg1.render(scale);
			Egg1a.render(scale);
			Egg1b.render(scale);
			Egg1c.render(scale);
			Egg2.render(scale);
			Egg2a.render(scale);
			Egg2b.render(scale);
			Egg2c.render(scale);

		} else if (nestType == 3.0F || nestType == 6.0F || nestType == 9.0F) {
			Egg1.render(scale);
			Egg1a.render(scale);
			Egg1b.render(scale);
			Egg1c.render(scale);
			Egg2.render(scale);
			Egg2a.render(scale);
			Egg2b.render(scale);
			Egg2c.render(scale);
			Egg3.render(scale);
			Egg3a.render(scale);
			Egg3b.render(scale);
			Egg3c.render(scale);

		} else if (nestType == 10.0F || nestType == 13.0F) {

			BEgg1.render(scale);
			BEgg1a.render(scale);
			BEgg1b.render(scale);
			BEgg1c.render(scale);
		
		} else if (nestType == 11.0F || nestType == 14.0F) {

			BEgg1.render(scale);
			BEgg1a.render(scale);
			BEgg1b.render(scale);
			BEgg1c.render(scale);
			BEgg2.render(scale);
			BEgg2a.render(scale);
			BEgg2b.render(scale);
			BEgg2c.render(scale);

		} else if (nestType == 12.0F || nestType == 15.0F) {
			BEgg1.render(scale);
			BEgg1a.render(scale);
			BEgg1b.render(scale);
			BEgg1c.render(scale);
			BEgg2.render(scale);
			BEgg2a.render(scale);
			BEgg2b.render(scale);
			BEgg2c.render(scale);
			BEgg3.render(scale);
			BEgg3a.render(scale);
			BEgg3b.render(scale);
			BEgg3c.render(scale);
		}


		this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		Fluff3.rotateAngleY = 1.133858F;
		Nest3.rotateAngleY = 1.570796F;
		Nest4.rotateAngleY = -1.570796F;
		Nest5.rotateAngleY = -0.7853982F;
		Nest6.rotateAngleY = 0.7853982F;
		Nest7.rotateAngleY = 2.356194F;
		Nest8.rotateAngleY = -2.356194F;
		Fluff2.rotateAngleY = 0.5906957F;
		Fluff5.rotateAngleY = 0.3574434F;
		Egg1.rotateAngleX = -0.2400439F;
		Egg1.rotateAngleY = 0.1601751F;
		Egg1.rotateAngleZ = -0.1941436F;
		Egg1a.rotateAngleX = -0.2400439F;
		Egg1a.rotateAngleY = 0.1601751F;
		Egg1a.rotateAngleZ = -0.1941436F;
		Egg1b.rotateAngleX = -0.2400439F;
		Egg1b.rotateAngleY = 0.1601751F;
		Egg1b.rotateAngleZ = -0.1941436F;
		Egg1c.rotateAngleX = -0.2400439F;
		Egg1c.rotateAngleY = 0.1601751F;
		Egg1c.rotateAngleZ = -0.1941436F;
		Egg2.rotateAngleX = -1.666733E-08F;
		Egg2.rotateAngleY = -0.3777635F;
		Egg2.rotateAngleZ = -1.277677F;
		Egg2a.rotateAngleX = -1.666733E-08F;
		Egg2a.rotateAngleY = -0.3777635F;
		Egg2a.rotateAngleZ = -1.277677F;
		Egg2b.rotateAngleX = -1.666733E-08F;
		Egg2b.rotateAngleY = -0.3777635F;
		Egg2b.rotateAngleZ = -1.277677F;
		Egg2c.rotateAngleX = -1.666733E-08F;
		Egg2c.rotateAngleY = -0.3777635F;
		Egg2c.rotateAngleZ = -1.277677F;
		Egg3.rotateAngleX = -0.7743126F;
		Egg3.rotateAngleY = 0.2888342F;
		Egg3.rotateAngleZ = 0.5073518F;
		Egg3a.rotateAngleX = -0.7743126F;
		Egg3a.rotateAngleY = 0.2888342F;
		Egg3a.rotateAngleZ = 0.5073518F;
		Egg3b.rotateAngleX = -0.7743126F;
		Egg3b.rotateAngleY = 0.2888342F;
		Egg3b.rotateAngleZ = 0.5073518F;
		Egg3c.rotateAngleX = -0.7743126F;
		Egg3c.rotateAngleY = 0.2888342F;
		Egg3c.rotateAngleZ = 0.5073518F;
		
		BEgg1.rotateAngleX = -0.2400439F;
		BEgg1.rotateAngleY = 0.1601751F;
		BEgg1.rotateAngleZ = -0.1941436F;
		BEgg1a.rotateAngleX = -0.2400439F;
		BEgg1a.rotateAngleY = 0.1601751F;
		BEgg1a.rotateAngleZ = -0.1941436F;
		BEgg1b.rotateAngleX = -0.2400439F;
		BEgg1b.rotateAngleY = 0.1601751F;
		BEgg1b.rotateAngleZ = -0.1941436F;
		BEgg1c.rotateAngleX = -0.2400439F;
		BEgg1c.rotateAngleY = 0.1601751F;
		BEgg1c.rotateAngleZ = -0.1941436F;
		BEgg2.rotateAngleX = -1.666733E-08F;
		BEgg2.rotateAngleY = -0.3777635F;
		BEgg2.rotateAngleZ = -1.277677F;
		BEgg2a.rotateAngleX = -1.666733E-08F;
		BEgg2a.rotateAngleY = -0.3777635F;
		BEgg2a.rotateAngleZ = -1.277677F;
		BEgg2b.rotateAngleX = -1.666733E-08F;
		BEgg2b.rotateAngleY = -0.3777635F;
		BEgg2b.rotateAngleZ = -1.277677F;
		BEgg2c.rotateAngleX = -1.666733E-08F;
		BEgg2c.rotateAngleY = -0.3777635F;
		BEgg2c.rotateAngleZ = -1.277677F;
		BEgg3.rotateAngleX = -0.7743126F;
		BEgg3.rotateAngleY = 0.2888342F;
		BEgg3.rotateAngleZ = 0.5073518F;
		BEgg3a.rotateAngleX = -0.7743126F;
		BEgg3a.rotateAngleY = 0.2888342F;
		BEgg3a.rotateAngleZ = 0.5073518F;
		BEgg3b.rotateAngleX = -0.7743126F;
		BEgg3b.rotateAngleY = 0.2888342F;
		BEgg3b.rotateAngleZ = 0.5073518F;
		BEgg3c.rotateAngleX = -0.7743126F;
		BEgg3c.rotateAngleY = 0.2888342F;
		BEgg3c.rotateAngleZ = 0.5073518F;

	}



}

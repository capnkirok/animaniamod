package com.animania.client.models;

import com.animania.common.tileentities.TileEntityNest.NestContent;

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

	ModelRenderer BlEgg1;
	ModelRenderer BlEgg1a;
	ModelRenderer BlEgg1b;
	ModelRenderer BlEgg1c;
	ModelRenderer BlEgg2;
	ModelRenderer BlEgg2a;
	ModelRenderer BlEgg2b;
	ModelRenderer BlEgg2c;
	ModelRenderer BlEgg3;
	ModelRenderer BlEgg3a;
	ModelRenderer BlEgg3b;
	ModelRenderer BlEgg3c;

	ModelRenderer WEgg1;
	ModelRenderer WEgg1a;
	ModelRenderer WEgg1b;
	ModelRenderer WEgg1c;
	ModelRenderer WEgg2;
	ModelRenderer WEgg2a;
	ModelRenderer WEgg2b;
	ModelRenderer WEgg2c;
	ModelRenderer WEgg3;
	ModelRenderer WEgg3a;
	ModelRenderer WEgg3b;
	ModelRenderer WEgg3c;

	ModelRenderer Feather1;
	ModelRenderer Feather2;
	ModelRenderer Feather3;
	ModelRenderer Feather4;
	ModelRenderer Feather5;

	public ModelNest()
	{
		this(0.0f);
	}

	public ModelNest(float par1)
	{
		this.Nest1 = new ModelRenderer(this, 0, 0);
		this.Nest1.setTextureSize(128, 64);
		this.Nest1.addBox(-3F, -2F, -8F, 6, 4, 3);
		this.Nest1.setRotationPoint(0F, 22F, 0F);
		this.Fluff3 = new ModelRenderer(this, -16, 38);
		this.Fluff3.setTextureSize(128, 64);
		this.Fluff3.addBox(-8F, 0F, -8F, 16, 0, 16);
		this.Fluff3.setRotationPoint(0F, 21.5F, 0F);
		this.Fluff1 = new ModelRenderer(this, -16, 38);
		this.Fluff1.setTextureSize(128, 64);
		this.Fluff1.addBox(-8F, 0F, -8F, 16, 0, 16);
		this.Fluff1.setRotationPoint(0F, 22.5F, 0F);
		this.Nest2 = new ModelRenderer(this, 0, 7);
		this.Nest2.setTextureSize(128, 64);
		this.Nest2.addBox(-3F, -2F, 5F, 6, 4, 3);
		this.Nest2.setRotationPoint(0F, 22F, 0F);
		this.Nest3 = new ModelRenderer(this, 0, 14);
		this.Nest3.setTextureSize(128, 64);
		this.Nest3.addBox(-3F, -2F, 5F, 6, 4, 3);
		this.Nest3.setRotationPoint(0F, 22F, 0F);
		this.Nest4 = new ModelRenderer(this, 19, 0);
		this.Nest4.setTextureSize(128, 64);
		this.Nest4.addBox(-3F, -2F, 5F, 6, 4, 3);
		this.Nest4.setRotationPoint(0F, 22F, 0F);
		this.Nest5 = new ModelRenderer(this, 18, 7);
		this.Nest5.setTextureSize(128, 64);
		this.Nest5.addBox(-3.5F, -2F, 5F, 7, 4, 3);
		this.Nest5.setRotationPoint(0F, 21.9F, 0F);
		this.Nest6 = new ModelRenderer(this, 18, 14);
		this.Nest6.setTextureSize(128, 64);
		this.Nest6.addBox(-3.5F, -2F, 5F, 7, 4, 3);
		this.Nest6.setRotationPoint(0F, 21.9F, 0F);
		this.Nest7 = new ModelRenderer(this, 18, 20);
		this.Nest7.setTextureSize(128, 64);
		this.Nest7.addBox(-3.5F, -2F, 5F, 7, 4, 3);
		this.Nest7.setRotationPoint(0F, 21.9F, 0F);
		this.Nest8 = new ModelRenderer(this, 41, 0);
		this.Nest8.setTextureSize(128, 64);
		this.Nest8.addBox(-3.5F, -2F, 5F, 7, 4, 3);
		this.Nest8.setRotationPoint(0F, 21.9F, 0F);
		this.Block = new ModelRenderer(this, 13, 8);
		this.Block.setTextureSize(128, 64);
		this.Block.addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11);
		this.Block.setRotationPoint(0F, 22.5F, 0F);
		this.Fluff2 = new ModelRenderer(this, -16, 38);
		this.Fluff2.setTextureSize(128, 64);
		this.Fluff2.addBox(-8F, 0F, -8F, 16, 0, 16);
		this.Fluff2.setRotationPoint(0F, 23.8F, 0F);
		this.Fluff4 = new ModelRenderer(this, 18, 38);
		this.Fluff4.setTextureSize(128, 64);
		this.Fluff4.addBox(-8F, 0F, -8F, 16, 0, 16);
		this.Fluff4.setRotationPoint(0F, 19.7F, 0F);
		this.Fluff5 = new ModelRenderer(this, 18, 38);
		this.Fluff5.setTextureSize(128, 64);
		this.Fluff5.addBox(-8F, 0F, -8F, 16, 0, 16);
		this.Fluff5.setRotationPoint(0F, 19.65F, 0F);

		this.Feather1 = new ModelRenderer(this, 62, 0);
		this.Feather1.setTextureSize(128, 64);
		this.Feather1.addBox(-6.5F, .0F, -20F, 13, 0, 40);
		this.Feather1.setRotationPoint(3F, 60F, 2F);
		this.Feather2 = new ModelRenderer(this, 62, 0);
		this.Feather2.setTextureSize(128, 64);
		this.Feather2.addBox(-6.6F, 0F, -20F, 13, 0, 40);
		this.Feather2.setRotationPoint(2F, 60F, -2F);
		this.Feather3 = new ModelRenderer(this, 62, 0);
		this.Feather3.setTextureSize(128, 64);
		this.Feather3.addBox(-6.4F, .0F, -20F, 13, 0, 40);
		this.Feather3.setRotationPoint(-4F, 60.75F, 0F);

		this.Feather4 = new ModelRenderer(this, 62, 0);
		this.Feather4.setTextureSize(128, 64);
		this.Feather4.addBox(-6.5F, .01F, -20F, 13, 0, 40);
		this.Feather4.setRotationPoint(-1F, 60F, -4F);

		this.Feather5 = new ModelRenderer(this, 62, 0);
		this.Feather5.setTextureSize(128, 64);
		this.Feather5.addBox(-6.5F, .01F, -20F, 13, 0, 40);
		this.Feather5.setRotationPoint(-1F, 60F, 4F);

		this.Egg1 = new ModelRenderer(this, 68, 2);
		this.Egg1.setTextureSize(128, 64);
		this.Egg1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.Egg1.setRotationPoint(-2.5F, 20.5F, 1F);
		this.Egg1a = new ModelRenderer(this, 71, 4);
		this.Egg1a.setTextureSize(128, 64);
		this.Egg1a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg1a.setRotationPoint(-2.768189F, 18.83211F, 1.45686F);
		this.Egg1b = new ModelRenderer(this, 72, 3);
		this.Egg1b.setTextureSize(128, 64);
		this.Egg1b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Egg1b.setRotationPoint(-2.845569F, 18.35441F, 1.582717F);
		this.Egg1c = new ModelRenderer(this, 71, 6);
		this.Egg1c.setTextureSize(128, 64);
		this.Egg1c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg1c.setRotationPoint(-2.270879F, 21.92846F, 0.6037322F);
		this.Egg2 = new ModelRenderer(this, 73, 4);
		this.Egg2.setTextureSize(128, 64);
		this.Egg2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.Egg2.setRotationPoint(0F, 20.5F, -2.25F);
		this.Egg2a = new ModelRenderer(this, 73, 3);
		this.Egg2a.setTextureSize(128, 64);
		this.Egg2a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg2a.setRotationPoint(-1.557231F, 19.99435F, -2.867943F);
		this.Egg2b = new ModelRenderer(this, 75, 6);
		this.Egg2b.setTextureSize(128, 64);
		this.Egg2b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Egg2b.setRotationPoint(-2.000357F, 19.84989F, -3.049029F);
		this.Egg2c = new ModelRenderer(this, 78, 2);
		this.Egg2c.setTextureSize(128, 64);
		this.Egg2c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg2c.setRotationPoint(1.336568F, 20.93341F, -1.724865F);
		this.Egg3 = new ModelRenderer(this, 76, 3);
		this.Egg3.setTextureSize(128, 64);
		this.Egg3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.Egg3.setRotationPoint(2F, 20.5F, 2F);
		this.Egg3a = new ModelRenderer(this, 77, 6);
		this.Egg3a.setTextureSize(128, 64);
		this.Egg3a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg3a.setRotationPoint(3.119674F, 19.40652F, 2.783019F);
		this.Egg3b = new ModelRenderer(this, 78, 4);
		this.Egg3b.setTextureSize(128, 64);
		this.Egg3b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Egg3b.setRotationPoint(3.438589F, 19.09068F, 3.003399F);
		this.Egg3c = new ModelRenderer(this, 75, 2);
		this.Egg3c.setTextureSize(128, 64);
		this.Egg3c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.Egg3c.setRotationPoint(1.039286F, 21.43386F, 1.3255F);

		this.BEgg1 = new ModelRenderer(this, 68, 22);
		this.BEgg1.setTextureSize(128, 64);
		this.BEgg1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BEgg1.setRotationPoint(-2.5F, 20.5F, 1F);
		this.BEgg1a = new ModelRenderer(this, 71, 24);
		this.BEgg1a.setTextureSize(128, 64);
		this.BEgg1a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg1a.setRotationPoint(-2.768189F, 18.83211F, 1.45686F);
		this.BEgg1b = new ModelRenderer(this, 72, 23);
		this.BEgg1b.setTextureSize(128, 64);
		this.BEgg1b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BEgg1b.setRotationPoint(-2.845569F, 18.35441F, 1.582717F);
		this.BEgg1c = new ModelRenderer(this, 71, 26);
		this.BEgg1c.setTextureSize(128, 64);
		this.BEgg1c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg1c.setRotationPoint(-2.270879F, 21.92846F, 0.6037322F);
		this.BEgg2 = new ModelRenderer(this, 73, 24);
		this.BEgg2.setTextureSize(128, 64);
		this.BEgg2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BEgg2.setRotationPoint(0F, 20.5F, -2.25F);
		this.BEgg2a = new ModelRenderer(this, 73, 23);
		this.BEgg2a.setTextureSize(128, 64);
		this.BEgg2a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg2a.setRotationPoint(-1.557231F, 19.99435F, -2.867943F);
		this.BEgg2b = new ModelRenderer(this, 75, 26);
		this.BEgg2b.setTextureSize(128, 64);
		this.BEgg2b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BEgg2b.setRotationPoint(-2.000357F, 19.84989F, -3.049029F);
		this.BEgg2c = new ModelRenderer(this, 78, 22);
		this.BEgg2c.setTextureSize(128, 64);
		this.BEgg2c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg2c.setRotationPoint(1.336568F, 20.93341F, -1.724865F);
		this.BEgg3 = new ModelRenderer(this, 76, 23);
		this.BEgg3.setTextureSize(128, 64);
		this.BEgg3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BEgg3.setRotationPoint(2F, 20.5F, 2F);
		this.BEgg3a = new ModelRenderer(this, 77, 26);
		this.BEgg3a.setTextureSize(128, 64);
		this.BEgg3a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg3a.setRotationPoint(3.119674F, 19.40652F, 2.783019F);
		this.BEgg3b = new ModelRenderer(this, 78, 24);
		this.BEgg3b.setTextureSize(128, 64);
		this.BEgg3b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BEgg3b.setRotationPoint(3.438589F, 19.09068F, 3.003399F);
		this.BEgg3c = new ModelRenderer(this, 75, 22);
		this.BEgg3c.setTextureSize(128, 64);
		this.BEgg3c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BEgg3c.setRotationPoint(1.039286F, 21.43386F, 1.3255F);

		this.BlEgg1 = new ModelRenderer(this, 68, 32);
		this.BlEgg1.setTextureSize(128, 64);
		this.BlEgg1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BlEgg1.setRotationPoint(-2.5F, 20.5F, 1F);
		this.BlEgg1a = new ModelRenderer(this, 71, 34);
		this.BlEgg1a.setTextureSize(128, 64);
		this.BlEgg1a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg1a.setRotationPoint(-2.768189F, 18.83211F, 1.45686F);
		this.BlEgg1b = new ModelRenderer(this, 72, 33);
		this.BlEgg1b.setTextureSize(128, 64);
		this.BlEgg1b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BlEgg1b.setRotationPoint(-2.845569F, 18.35441F, 1.582717F);
		this.BlEgg1c = new ModelRenderer(this, 71, 36);
		this.BlEgg1c.setTextureSize(128, 64);
		this.BlEgg1c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg1c.setRotationPoint(-2.270879F, 21.92846F, 0.6037322F);
		this.BlEgg2 = new ModelRenderer(this, 73, 34);
		this.BlEgg2.setTextureSize(128, 64);
		this.BlEgg2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BlEgg2.setRotationPoint(0F, 20.5F, -2.25F);
		this.BlEgg2a = new ModelRenderer(this, 73, 33);
		this.BlEgg2a.setTextureSize(128, 64);
		this.BlEgg2a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg2a.setRotationPoint(-1.557231F, 19.99435F, -2.867943F);
		this.BlEgg2b = new ModelRenderer(this, 75, 36);
		this.BlEgg2b.setTextureSize(128, 64);
		this.BlEgg2b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BlEgg2b.setRotationPoint(-2.000357F, 19.84989F, -3.049029F);
		this.BlEgg2c = new ModelRenderer(this, 78, 32);
		this.BlEgg2c.setTextureSize(128, 64);
		this.BlEgg2c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg2c.setRotationPoint(1.336568F, 20.93341F, -1.724865F);
		this.BlEgg3 = new ModelRenderer(this, 76, 33);
		this.BlEgg3.setTextureSize(128, 64);
		this.BlEgg3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.BlEgg3.setRotationPoint(2F, 20.5F, 2F);
		this.BlEgg3a = new ModelRenderer(this, 77, 36);
		this.BlEgg3a.setTextureSize(128, 64);
		this.BlEgg3a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg3a.setRotationPoint(3.119674F, 19.40652F, 2.783019F);
		this.BlEgg3b = new ModelRenderer(this, 78, 34);
		this.BlEgg3b.setTextureSize(128, 64);
		this.BlEgg3b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.BlEgg3b.setRotationPoint(3.438589F, 19.09068F, 3.003399F);
		this.BlEgg3c = new ModelRenderer(this, 75, 32);
		this.BlEgg3c.setTextureSize(128, 64);
		this.BlEgg3c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.BlEgg3c.setRotationPoint(1.039286F, 21.43386F, 1.3255F);

		this.WEgg1 = new ModelRenderer(this, 68, 42);
		this.WEgg1.setTextureSize(128, 64);
		this.WEgg1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.WEgg1.setRotationPoint(-2.5F, 20.5F, 1F);
		this.WEgg1a = new ModelRenderer(this, 71, 44);
		this.WEgg1a.setTextureSize(128, 64);
		this.WEgg1a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg1a.setRotationPoint(-2.768189F, 18.83211F, 1.45686F);
		this.WEgg1b = new ModelRenderer(this, 72, 43);
		this.WEgg1b.setTextureSize(128, 64);
		this.WEgg1b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.WEgg1b.setRotationPoint(-2.845569F, 18.35441F, 1.582717F);
		this.WEgg1c = new ModelRenderer(this, 71, 46);
		this.WEgg1c.setTextureSize(128, 64);
		this.WEgg1c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg1c.setRotationPoint(-2.270879F, 21.92846F, 0.6037322F);
		this.WEgg2 = new ModelRenderer(this, 73, 44);
		this.WEgg2.setTextureSize(128, 64);
		this.WEgg2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.WEgg2.setRotationPoint(0F, 20.5F, -2.25F);
		this.WEgg2a = new ModelRenderer(this, 73, 43);
		this.WEgg2a.setTextureSize(128, 64);
		this.WEgg2a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg2a.setRotationPoint(-1.557231F, 19.99435F, -2.867943F);
		this.WEgg2b = new ModelRenderer(this, 75, 46);
		this.WEgg2b.setTextureSize(128, 64);
		this.WEgg2b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.WEgg2b.setRotationPoint(-2.000357F, 19.84989F, -3.049029F);
		this.WEgg2c = new ModelRenderer(this, 78, 42);
		this.WEgg2c.setTextureSize(128, 64);
		this.WEgg2c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg2c.setRotationPoint(1.336568F, 20.93341F, -1.724865F);
		this.WEgg3 = new ModelRenderer(this, 76, 43);
		this.WEgg3.setTextureSize(128, 64);
		this.WEgg3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		this.WEgg3.setRotationPoint(2F, 20.5F, 2F);
		this.WEgg3a = new ModelRenderer(this, 77, 46);
		this.WEgg3a.setTextureSize(128, 64);
		this.WEgg3a.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg3a.setRotationPoint(3.119674F, 19.40652F, 2.783019F);
		this.WEgg3b = new ModelRenderer(this, 78, 44);
		this.WEgg3b.setTextureSize(128, 64);
		this.WEgg3b.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		this.WEgg3b.setRotationPoint(3.438589F, 19.09068F, 3.003399F);
		this.WEgg3c = new ModelRenderer(this, 75, 42);
		this.WEgg3c.setTextureSize(128, 64);
		this.WEgg3c.addBox(-1F, -0.5F, -1F, 2, 1, 2);
		this.WEgg3c.setRotationPoint(1.039286F, 21.43386F, 1.3255F);

	}

	@Override
	public void render(Entity entityIn, float nestType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		this.Nest1.render(scale);
		this.Fluff3.render(scale);
		this.Fluff1.render(scale);
		this.Nest2.render(scale);
		this.Nest3.render(scale);
		this.Nest4.render(scale);
		this.Nest5.render(scale);
		this.Nest6.render(scale);
		this.Nest7.render(scale);
		this.Nest8.render(scale);
		this.Block.render(scale);
		this.Fluff2.render(scale);
		this.Fluff4.render(scale);
		this.Fluff5.render(scale);

		this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
	}

	public void renderEggs(float scale, int count, NestContent type)
	{
		if (type == NestContent.CHICKEN_WHITE)
		{

			this.Egg1.render(scale);
			this.Egg1a.render(scale);
			this.Egg1b.render(scale);
			this.Egg1c.render(scale);
			if (count >= 2)
			{
				this.Egg2.render(scale);
				this.Egg2a.render(scale);
				this.Egg2b.render(scale);
				this.Egg2c.render(scale);
				if (count >= 3)
				{
					this.Egg3.render(scale);
					this.Egg3a.render(scale);
					this.Egg3b.render(scale);
					this.Egg3c.render(scale);
				}
			}
		}
		else if (type == NestContent.CHICKEN_BROWN)
		{

			this.BEgg1.render(scale);
			this.BEgg1a.render(scale);
			this.BEgg1b.render(scale);
			this.BEgg1c.render(scale);
			if (count >= 2)
			{
				this.BEgg2.render(scale);
				this.BEgg2a.render(scale);
				this.BEgg2b.render(scale);
				this.BEgg2c.render(scale);
				if (count >= 3)
				{
					this.BEgg3.render(scale);
					this.BEgg3a.render(scale);
					this.BEgg3b.render(scale);
					this.BEgg3c.render(scale);
				}
			}

		}
		else if (type == NestContent.PEACOCK_BLUE)
		{

			this.BlEgg1.render(scale);
			this.BlEgg1a.render(scale);
			this.BlEgg1b.render(scale);
			this.BlEgg1c.render(scale);
			if (count >= 2)
			{
				this.BlEgg2.render(scale);
				this.BlEgg2a.render(scale);
				this.BlEgg2b.render(scale);
				this.BlEgg2c.render(scale);
				if (count >= 3)
				{
					this.BlEgg3.render(scale);
					this.BlEgg3a.render(scale);
					this.BlEgg3b.render(scale);
					this.BlEgg3c.render(scale);
				}
			}

		}
		else if (type == NestContent.PEACOCK_WHITE)
		{

			this.WEgg1.render(scale);
			this.WEgg1a.render(scale);
			this.WEgg1b.render(scale);
			this.WEgg1c.render(scale);
			if (count >= 2)
			{
				this.WEgg2.render(scale);
				this.WEgg2a.render(scale);
				this.WEgg2b.render(scale);
				this.WEgg2c.render(scale);
				if (count >= 3)
				{
					this.WEgg3.render(scale);
					this.WEgg3a.render(scale);
					this.WEgg3b.render(scale);
					this.WEgg3c.render(scale);
				}
			}
		}
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{

		this.Fluff3.rotateAngleY = 1.133858F;
		this.Nest3.rotateAngleY = 1.570796F;
		this.Nest4.rotateAngleY = -1.570796F;
		this.Nest5.rotateAngleY = -0.7853982F;
		this.Nest6.rotateAngleY = 0.7853982F;
		this.Nest7.rotateAngleY = 2.356194F;
		this.Nest8.rotateAngleY = -2.356194F;
		this.Fluff2.rotateAngleY = 0.5906957F;
		this.Fluff5.rotateAngleY = 0.3574434F;

		// Peacock Feathers
		this.Feather1.rotateAngleX = 0.2803867F;
		this.Feather1.rotateAngleY = 0.5182232F;
		this.Feather1.rotateAngleZ = 0.04484942F;
		this.Feather2.rotateAngleX = 0.2142004F;
		this.Feather2.rotateAngleY = 2.028859F;
		this.Feather2.rotateAngleZ = -0.122275F;
		this.Feather3.rotateAngleX = 0.4055626F;
		this.Feather3.rotateAngleY = -1.776956F;
		this.Feather3.rotateAngleZ = -0.1216153F;
		this.Feather4.rotateAngleX = 0.2880184F;
		this.Feather4.rotateAngleY = -2.569335F;
		this.Feather4.rotateAngleZ = 0.1257959F;
		this.Feather5.rotateAngleX = 0.2764485F;
		this.Feather5.rotateAngleY = -0.3568929F;
		this.Feather5.rotateAngleZ = 0.1771393F;

		this.Egg1.rotateAngleX = -0.2400439F;
		this.Egg1.rotateAngleY = 0.1601751F;
		this.Egg1.rotateAngleZ = -0.1941436F;
		this.Egg1a.rotateAngleX = -0.2400439F;
		this.Egg1a.rotateAngleY = 0.1601751F;
		this.Egg1a.rotateAngleZ = -0.1941436F;
		this.Egg1b.rotateAngleX = -0.2400439F;
		this.Egg1b.rotateAngleY = 0.1601751F;
		this.Egg1b.rotateAngleZ = -0.1941436F;
		this.Egg1c.rotateAngleX = -0.2400439F;
		this.Egg1c.rotateAngleY = 0.1601751F;
		this.Egg1c.rotateAngleZ = -0.1941436F;
		this.Egg2.rotateAngleX = -1.666733E-08F;
		this.Egg2.rotateAngleY = -0.3777635F;
		this.Egg2.rotateAngleZ = -1.277677F;
		this.Egg2a.rotateAngleX = -1.666733E-08F;
		this.Egg2a.rotateAngleY = -0.3777635F;
		this.Egg2a.rotateAngleZ = -1.277677F;
		this.Egg2b.rotateAngleX = -1.666733E-08F;
		this.Egg2b.rotateAngleY = -0.3777635F;
		this.Egg2b.rotateAngleZ = -1.277677F;
		this.Egg2c.rotateAngleX = -1.666733E-08F;
		this.Egg2c.rotateAngleY = -0.3777635F;
		this.Egg2c.rotateAngleZ = -1.277677F;
		this.Egg3.rotateAngleX = -0.7743126F;
		this.Egg3.rotateAngleY = 0.2888342F;
		this.Egg3.rotateAngleZ = 0.5073518F;
		this.Egg3a.rotateAngleX = -0.7743126F;
		this.Egg3a.rotateAngleY = 0.2888342F;
		this.Egg3a.rotateAngleZ = 0.5073518F;
		this.Egg3b.rotateAngleX = -0.7743126F;
		this.Egg3b.rotateAngleY = 0.2888342F;
		this.Egg3b.rotateAngleZ = 0.5073518F;
		this.Egg3c.rotateAngleX = -0.7743126F;
		this.Egg3c.rotateAngleY = 0.2888342F;
		this.Egg3c.rotateAngleZ = 0.5073518F;

		this.BEgg1.rotateAngleX = -0.2400439F;
		this.BEgg1.rotateAngleY = 0.1601751F;
		this.BEgg1.rotateAngleZ = -0.1941436F;
		this.BEgg1a.rotateAngleX = -0.2400439F;
		this.BEgg1a.rotateAngleY = 0.1601751F;
		this.BEgg1a.rotateAngleZ = -0.1941436F;
		this.BEgg1b.rotateAngleX = -0.2400439F;
		this.BEgg1b.rotateAngleY = 0.1601751F;
		this.BEgg1b.rotateAngleZ = -0.1941436F;
		this.BEgg1c.rotateAngleX = -0.2400439F;
		this.BEgg1c.rotateAngleY = 0.1601751F;
		this.BEgg1c.rotateAngleZ = -0.1941436F;
		this.BEgg2.rotateAngleX = -1.666733E-08F;
		this.BEgg2.rotateAngleY = -0.3777635F;
		this.BEgg2.rotateAngleZ = -1.277677F;
		this.BEgg2a.rotateAngleX = -1.666733E-08F;
		this.BEgg2a.rotateAngleY = -0.3777635F;
		this.BEgg2a.rotateAngleZ = -1.277677F;
		this.BEgg2b.rotateAngleX = -1.666733E-08F;
		this.BEgg2b.rotateAngleY = -0.3777635F;
		this.BEgg2b.rotateAngleZ = -1.277677F;
		this.BEgg2c.rotateAngleX = -1.666733E-08F;
		this.BEgg2c.rotateAngleY = -0.3777635F;
		this.BEgg2c.rotateAngleZ = -1.277677F;
		this.BEgg3.rotateAngleX = -0.7743126F;
		this.BEgg3.rotateAngleY = 0.2888342F;
		this.BEgg3.rotateAngleZ = 0.5073518F;
		this.BEgg3a.rotateAngleX = -0.7743126F;
		this.BEgg3a.rotateAngleY = 0.2888342F;
		this.BEgg3a.rotateAngleZ = 0.5073518F;
		this.BEgg3b.rotateAngleX = -0.7743126F;
		this.BEgg3b.rotateAngleY = 0.2888342F;
		this.BEgg3b.rotateAngleZ = 0.5073518F;
		this.BEgg3c.rotateAngleX = -0.7743126F;
		this.BEgg3c.rotateAngleY = 0.2888342F;
		this.BEgg3c.rotateAngleZ = 0.5073518F;

		// Peacocks
		this.BlEgg1.rotateAngleX = -0.2400439F;
		this.BlEgg1.rotateAngleY = 0.1601751F;
		this.BlEgg1.rotateAngleZ = -0.1941436F;
		this.BlEgg1a.rotateAngleX = -0.2400439F;
		this.BlEgg1a.rotateAngleY = 0.1601751F;
		this.BlEgg1a.rotateAngleZ = -0.1941436F;
		this.BlEgg1b.rotateAngleX = -0.2400439F;
		this.BlEgg1b.rotateAngleY = 0.1601751F;
		this.BlEgg1b.rotateAngleZ = -0.1941436F;
		this.BlEgg1c.rotateAngleX = -0.2400439F;
		this.BlEgg1c.rotateAngleY = 0.1601751F;
		this.BlEgg1c.rotateAngleZ = -0.1941436F;
		this.BlEgg2.rotateAngleX = -1.666733E-08F;
		this.BlEgg2.rotateAngleY = -0.3777635F;
		this.BlEgg2.rotateAngleZ = -1.277677F;
		this.BlEgg2a.rotateAngleX = -1.666733E-08F;
		this.BlEgg2a.rotateAngleY = -0.3777635F;
		this.BlEgg2a.rotateAngleZ = -1.277677F;
		this.BlEgg2b.rotateAngleX = -1.666733E-08F;
		this.BlEgg2b.rotateAngleY = -0.3777635F;
		this.BlEgg2b.rotateAngleZ = -1.277677F;
		this.BlEgg2c.rotateAngleX = -1.666733E-08F;
		this.BlEgg2c.rotateAngleY = -0.3777635F;
		this.BlEgg2c.rotateAngleZ = -1.277677F;
		this.BlEgg3.rotateAngleX = -0.7743126F;
		this.BlEgg3.rotateAngleY = 0.2888342F;
		this.BlEgg3.rotateAngleZ = 0.5073518F;
		this.BlEgg3a.rotateAngleX = -0.7743126F;
		this.BlEgg3a.rotateAngleY = 0.2888342F;
		this.BlEgg3a.rotateAngleZ = 0.5073518F;
		this.BlEgg3b.rotateAngleX = -0.7743126F;
		this.BlEgg3b.rotateAngleY = 0.2888342F;
		this.BlEgg3b.rotateAngleZ = 0.5073518F;
		this.BlEgg3c.rotateAngleX = -0.7743126F;
		this.BlEgg3c.rotateAngleY = 0.2888342F;
		this.BlEgg3c.rotateAngleZ = 0.5073518F;
		this.WEgg1.rotateAngleX = -0.2400439F;
		this.WEgg1.rotateAngleY = 0.1601751F;
		this.WEgg1.rotateAngleZ = -0.1941436F;
		this.WEgg1a.rotateAngleX = -0.2400439F;
		this.WEgg1a.rotateAngleY = 0.1601751F;
		this.WEgg1a.rotateAngleZ = -0.1941436F;
		this.WEgg1b.rotateAngleX = -0.2400439F;
		this.WEgg1b.rotateAngleY = 0.1601751F;
		this.WEgg1b.rotateAngleZ = -0.1941436F;
		this.WEgg1c.rotateAngleX = -0.2400439F;
		this.WEgg1c.rotateAngleY = 0.1601751F;
		this.WEgg1c.rotateAngleZ = -0.1941436F;
		this.WEgg2.rotateAngleX = -1.666733E-08F;
		this.WEgg2.rotateAngleY = -0.3777635F;
		this.WEgg2.rotateAngleZ = -1.277677F;
		this.WEgg2a.rotateAngleX = -1.666733E-08F;
		this.WEgg2a.rotateAngleY = -0.3777635F;
		this.WEgg2a.rotateAngleZ = -1.277677F;
		this.WEgg2b.rotateAngleX = -1.666733E-08F;
		this.WEgg2b.rotateAngleY = -0.3777635F;
		this.WEgg2b.rotateAngleZ = -1.277677F;
		this.WEgg2c.rotateAngleX = -1.666733E-08F;
		this.WEgg2c.rotateAngleY = -0.3777635F;
		this.WEgg2c.rotateAngleZ = -1.277677F;
		this.WEgg3.rotateAngleX = -0.7743126F;
		this.WEgg3.rotateAngleY = 0.2888342F;
		this.WEgg3.rotateAngleZ = 0.5073518F;
		this.WEgg3a.rotateAngleX = -0.7743126F;
		this.WEgg3a.rotateAngleY = 0.2888342F;
		this.WEgg3a.rotateAngleZ = 0.5073518F;
		this.WEgg3b.rotateAngleX = -0.7743126F;
		this.WEgg3b.rotateAngleY = 0.2888342F;
		this.WEgg3b.rotateAngleZ = 0.5073518F;
		this.WEgg3c.rotateAngleX = -0.7743126F;
		this.WEgg3c.rotateAngleY = 0.2888342F;
		this.WEgg3c.rotateAngleZ = 0.5073518F;

	}

}

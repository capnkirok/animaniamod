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

    public ModelNest() {
        this(0.0f);
    }

    public ModelNest(float par1) {
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

    }

    @Override
    public void render(Entity entityIn, float nestType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

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

        if (nestType == 1.0F || nestType == 4.0F || nestType == 7.0F) {

            this.Egg1.render(scale);
            this.Egg1a.render(scale);
            this.Egg1b.render(scale);
            this.Egg1c.render(scale);

        }
        else if (nestType == 2.0F || nestType == 5.0F || nestType == 8.0F) {

            this.Egg1.render(scale);
            this.Egg1a.render(scale);
            this.Egg1b.render(scale);
            this.Egg1c.render(scale);
            this.Egg2.render(scale);
            this.Egg2a.render(scale);
            this.Egg2b.render(scale);
            this.Egg2c.render(scale);

        }
        else if (nestType == 3.0F || nestType == 6.0F || nestType == 9.0F) {
            this.Egg1.render(scale);
            this.Egg1a.render(scale);
            this.Egg1b.render(scale);
            this.Egg1c.render(scale);
            this.Egg2.render(scale);
            this.Egg2a.render(scale);
            this.Egg2b.render(scale);
            this.Egg2c.render(scale);
            this.Egg3.render(scale);
            this.Egg3a.render(scale);
            this.Egg3b.render(scale);
            this.Egg3c.render(scale);

        }
        else if (nestType == 10.0F || nestType == 13.0F) {

            this.BEgg1.render(scale);
            this.BEgg1a.render(scale);
            this.BEgg1b.render(scale);
            this.BEgg1c.render(scale);

        }
        else if (nestType == 11.0F || nestType == 14.0F) {

            this.BEgg1.render(scale);
            this.BEgg1a.render(scale);
            this.BEgg1b.render(scale);
            this.BEgg1c.render(scale);
            this.BEgg2.render(scale);
            this.BEgg2a.render(scale);
            this.BEgg2b.render(scale);
            this.BEgg2c.render(scale);

        }
        else if (nestType == 12.0F || nestType == 15.0F) {
            this.BEgg1.render(scale);
            this.BEgg1a.render(scale);
            this.BEgg1b.render(scale);
            this.BEgg1c.render(scale);
            this.BEgg2.render(scale);
            this.BEgg2a.render(scale);
            this.BEgg2b.render(scale);
            this.BEgg2c.render(scale);
            this.BEgg3.render(scale);
            this.BEgg3a.render(scale);
            this.BEgg3b.render(scale);
            this.BEgg3c.render(scale);
        }

        this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
            Entity entityIn) {

        this.Fluff3.rotateAngleY = 1.133858F;
        this.Nest3.rotateAngleY = 1.570796F;
        this.Nest4.rotateAngleY = -1.570796F;
        this.Nest5.rotateAngleY = -0.7853982F;
        this.Nest6.rotateAngleY = 0.7853982F;
        this.Nest7.rotateAngleY = 2.356194F;
        this.Nest8.rotateAngleY = -2.356194F;
        this.Fluff2.rotateAngleY = 0.5906957F;
        this.Fluff5.rotateAngleY = 0.3574434F;
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

    }

}

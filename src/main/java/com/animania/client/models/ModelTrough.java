package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrough extends ModelBase
{
    ModelRenderer Block1;
    ModelRenderer Base2;
    ModelRenderer Base1;
    ModelRenderer Block2;
    ModelRenderer Block3;
    ModelRenderer Block4;
    ModelRenderer Block5;
    ModelRenderer Feed;
    ModelRenderer FeedA;
    ModelRenderer FeedB;
    ModelRenderer FeedC;
    ModelRenderer FeedD;
    ModelRenderer FeedE;
    ModelRenderer FeedF;
    ModelRenderer FeedG;
    ModelRenderer FeedH;

    ModelRenderer Feed1;
    ModelRenderer FeedA1;
    ModelRenderer FeedB1;
    ModelRenderer FeedC1;
    ModelRenderer FeedD1;
    ModelRenderer FeedE1;
    ModelRenderer FeedF1;
    ModelRenderer FeedG1;
    ModelRenderer FeedH1;

    ModelRenderer Feed2;
    ModelRenderer FeedA2;
    ModelRenderer FeedB2;
    ModelRenderer FeedC2;
    ModelRenderer FeedD2;
    ModelRenderer FeedE2;
    ModelRenderer FeedF2;
    ModelRenderer FeedG2;
    ModelRenderer FeedH2;

    ModelRenderer Slop1;
    ModelRenderer Slop2;
    ModelRenderer Slop3;
    ModelRenderer Water1;
    ModelRenderer Water2;
    ModelRenderer Water3;

    public ModelTrough() {
        this(0.0f);
    }

    public ModelTrough(float par1) {
        this.Block1 = new ModelRenderer(this, 2, 2);
        this.Block1.setTextureSize(128, 64);
        this.Block1.addBox(-1F, -5F, -6F, 2, 10, 12);
        this.Block1.setRotationPoint(-7F, 17F, 0F);
        this.Base2 = new ModelRenderer(this, 4, 4);
        this.Base2.setTextureSize(128, 64);
        this.Base2.addBox(-1F, -1F, -5F, 2, 2, 10);
        this.Base2.setRotationPoint(22F, 23F, 0F);
        this.Base1 = new ModelRenderer(this, 4, 4);
        this.Base1.setTextureSize(128, 64);
        this.Base1.addBox(-1F, -1F, -5F, 2, 2, 10);
        this.Base1.setRotationPoint(-6F, 23F, 0F);
        this.Block2 = new ModelRenderer(this, 2, 2);
        this.Block2.setTextureSize(128, 64);
        this.Block2.addBox(-1F, -5F, -6F, 2, 10, 12);
        this.Block2.setRotationPoint(23F, 17F, 0F);
        this.Block3 = new ModelRenderer(this, 1, 26);
        this.Block3.setTextureSize(128, 64);
        this.Block3.addBox(-14F, -4F, -1F, 28, 8, 2);
        this.Block3.setRotationPoint(8F, 18F, -5F);
        this.Block4 = new ModelRenderer(this, 1, 26);
        this.Block4.setTextureSize(128, 64);
        this.Block4.addBox(-14F, -4F, -1F, 28, 8, 2);
        this.Block4.setRotationPoint(8F, 18F, 5F);
        this.Block5 = new ModelRenderer(this, 3, 42);
        this.Block5.setTextureSize(128, 64);
        this.Block5.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Block5.setRotationPoint(8F, 21.5F, 0F);

        this.Feed = new ModelRenderer(this, 56, 1);
        this.Feed.setTextureSize(128, 64);
        this.Feed.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Feed.setRotationPoint(8F, 16F, 0F);
        this.FeedA = new ModelRenderer(this, 72, 36);
        this.FeedA.setTextureSize(128, 64);
        this.FeedA.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedA.setRotationPoint(-1F, 15F, 2.5F);
        this.FeedB = new ModelRenderer(this, 72, 36);
        this.FeedB.setTextureSize(128, 64);
        this.FeedB.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedB.setRotationPoint(8F, 15F, 1.000001F);
        this.FeedC = new ModelRenderer(this, 72, 36);
        this.FeedC.setTextureSize(128, 64);
        this.FeedC.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedC.setRotationPoint(17F, 15F, 2.500001F);
        this.FeedD = new ModelRenderer(this, 72, 36);
        this.FeedD.setTextureSize(128, 64);
        this.FeedD.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedD.setRotationPoint(14F, 15F, -1.999999F);
        this.FeedE = new ModelRenderer(this, 72, 36);
        this.FeedE.setTextureSize(128, 64);
        this.FeedE.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedE.setRotationPoint(2F, 15F, -0.9999991F);
        this.FeedF = new ModelRenderer(this, 72, 36);
        this.FeedF.setTextureSize(128, 64);
        this.FeedF.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedF.setRotationPoint(9.000001F, 14F, -1.999999F);
        this.FeedG = new ModelRenderer(this, 72, 36);
        this.FeedG.setTextureSize(128, 64);
        this.FeedG.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedG.setRotationPoint(1.56835E-07F, 14F, -0.9999991F);
        this.FeedH = new ModelRenderer(this, 72, 36);
        this.FeedH.setTextureSize(128, 64);
        this.FeedH.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedH.setRotationPoint(17F, 14F, -0.9999986F);

        this.Feed1 = new ModelRenderer(this, 56, 1);
        this.Feed1.setTextureSize(128, 64);
        this.Feed1.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Feed1.setRotationPoint(8F, 18F, 0F);
        this.FeedA1 = new ModelRenderer(this, 72, 36);
        this.FeedA1.setTextureSize(128, 64);
        this.FeedA1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedA1.setRotationPoint(-1F, 17F, 2.5F);
        this.FeedB1 = new ModelRenderer(this, 72, 36);
        this.FeedB1.setTextureSize(128, 64);
        this.FeedB1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedB1.setRotationPoint(8F, 17F, 1.000001F);
        this.FeedC1 = new ModelRenderer(this, 72, 36);
        this.FeedC1.setTextureSize(128, 64);
        this.FeedC1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedC1.setRotationPoint(17F, 17F, 2.500001F);
        this.FeedD1 = new ModelRenderer(this, 72, 36);
        this.FeedD1.setTextureSize(128, 64);
        this.FeedD1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedD1.setRotationPoint(14F, 17F, -1.999999F);
        this.FeedE1 = new ModelRenderer(this, 72, 36);
        this.FeedE1.setTextureSize(128, 64);
        this.FeedE1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedE1.setRotationPoint(2F, 17F, -0.9999991F);
        this.FeedF1 = new ModelRenderer(this, 72, 36);
        this.FeedF1.setTextureSize(128, 64);
        this.FeedF1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedF1.setRotationPoint(9.000001F, 16F, -1.999999F);
        this.FeedG1 = new ModelRenderer(this, 72, 36);
        this.FeedG1.setTextureSize(128, 64);
        this.FeedG1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedG1.setRotationPoint(1.56835E-07F, 16F, -0.9999991F);
        this.FeedH1 = new ModelRenderer(this, 72, 36);
        this.FeedH1.setTextureSize(128, 64);
        this.FeedH1.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedH1.setRotationPoint(17F, 16F, -0.9999986F);

        this.Feed2 = new ModelRenderer(this, 56, 1);
        this.Feed2.setTextureSize(128, 64);
        this.Feed2.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Feed2.setRotationPoint(8F, 20F, 0F);
        this.FeedA2 = new ModelRenderer(this, 72, 36);
        this.FeedA2.setTextureSize(128, 64);
        this.FeedA2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedA2.setRotationPoint(-1F, 19F, 2.5F);
        this.FeedB2 = new ModelRenderer(this, 72, 36);
        this.FeedB2.setTextureSize(128, 64);
        this.FeedB2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedB2.setRotationPoint(8F, 19F, 1.000001F);
        this.FeedC2 = new ModelRenderer(this, 72, 36);
        this.FeedC2.setTextureSize(128, 64);
        this.FeedC2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedC2.setRotationPoint(17F, 19F, 2.500001F);
        this.FeedD2 = new ModelRenderer(this, 72, 36);
        this.FeedD2.setTextureSize(128, 64);
        this.FeedD2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedD2.setRotationPoint(14F, 19F, -1.999999F);
        this.FeedE2 = new ModelRenderer(this, 72, 36);
        this.FeedE2.setTextureSize(128, 64);
        this.FeedE2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedE2.setRotationPoint(2F, 19F, -0.9999991F);
        this.FeedF2 = new ModelRenderer(this, 72, 36);
        this.FeedF2.setTextureSize(128, 64);
        this.FeedF2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedF2.setRotationPoint(9.000001F, 18F, -1.999999F);
        this.FeedG2 = new ModelRenderer(this, 72, 36);
        this.FeedG2.setTextureSize(128, 64);
        this.FeedG2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedG2.setRotationPoint(1.56835E-07F, 18F, -0.9999991F);
        this.FeedH2 = new ModelRenderer(this, 72, 36);
        this.FeedH2.setTextureSize(128, 64);
        this.FeedH2.addBox(-4.5F, 0F, -5F, 9, 0, 10);
        this.FeedH2.setRotationPoint(17F, 18F, -0.9999986F);

        this.Slop1 = new ModelRenderer(this, 56, 12);
        this.Slop1.setTextureSize(128, 64);
        this.Slop1.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Slop1.setRotationPoint(8F, 16F, 0F);

        this.Slop2 = new ModelRenderer(this, 56, 12);
        this.Slop2.setTextureSize(128, 64);
        this.Slop2.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Slop2.setRotationPoint(8F, 18F, 0F);

        this.Slop3 = new ModelRenderer(this, 56, 12);
        this.Slop3.setTextureSize(128, 64);
        this.Slop3.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Slop3.setRotationPoint(8F, 20F, 0F);

        this.Water1 = new ModelRenderer(this, 56, 54);
        this.Water1.setTextureSize(128, 64);
        this.Water1.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Water1.setRotationPoint(8F, 16F, 0F);

        this.Water2 = new ModelRenderer(this, 56, 54);
        this.Water2.setTextureSize(128, 64);
        this.Water2.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Water2.setRotationPoint(8F, 18F, 0F);

        this.Water3 = new ModelRenderer(this, 56, 54);
        this.Water3.setTextureSize(128, 64);
        this.Water3.addBox(-14F, -0.5F, -4F, 28, 1, 8);
        this.Water3.setRotationPoint(8F, 20F, 0F);

    }

    @Override
    public void render(Entity entityIn, float troughType, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.Block1.render(scale);
        this.Block2.render(scale);
        this.Block3.render(scale);
        this.Block4.render(scale);
        this.Block5.render(scale);
        this.Base1.render(scale);
        this.Base2.render(scale);

        if (troughType == 1.0F)
            this.Water1.render(scale);
        else if (troughType == 2.0F)
            this.Water2.render(scale);
        else if (troughType == 3.0F)
            this.Water3.render(scale);
        else if (troughType == 4.0F) {
            this.Feed2.render(scale);
            this.FeedA2.render(scale);
            this.FeedB2.render(scale);
            this.FeedC2.render(scale);
            this.FeedD2.render(scale);
            this.FeedE2.render(scale);
            this.FeedF2.render(scale);
            this.FeedG2.render(scale);
            this.FeedH2.render(scale);
        }
        else if (troughType == 5.0F) {
            this.Feed1.render(scale);
            this.FeedA1.render(scale);
            this.FeedB1.render(scale);
            this.FeedC1.render(scale);
            this.FeedD1.render(scale);
            this.FeedE1.render(scale);
            this.FeedF1.render(scale);
            this.FeedG1.render(scale);
            this.FeedH1.render(scale);
        }
        else if (troughType == 6.0F) {
            this.Feed.render(scale);
            this.FeedA.render(scale);
            this.FeedB.render(scale);
            this.FeedC.render(scale);
            this.FeedD.render(scale);
            this.FeedE.render(scale);
            this.FeedF.render(scale);
            this.FeedG.render(scale);
            this.FeedH.render(scale);
        }
        else if (troughType == 7.0F)
            this.Slop1.render(scale);
        else if (troughType == 8.0F)
            this.Slop2.render(scale);
        else if (troughType == 9.0F)
            this.Slop3.render(scale);

        this.setRotationAngles(0.0F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
            Entity entityIn) {

        this.FeedA.rotateAngleX = 0.5566564F;
        this.FeedA.rotateAngleY = 0.6934777F;
        this.FeedA.rotateAngleZ = 0.418777F;

        this.FeedB.rotateAngleX = 0.3321232F;
        this.FeedB.rotateAngleY = 1.126472F;
        this.FeedB.rotateAngleZ = 0.4568707F;

        this.FeedC.rotateAngleX = 0.5561382F;
        this.FeedC.rotateAngleY = 0.6948478F;
        this.FeedC.rotateAngleZ = 0.4195006F;

        this.FeedD.rotateAngleX = 0.329293F;
        this.FeedD.rotateAngleY = -2.02634F;
        this.FeedD.rotateAngleZ = 0.4998787F;

        this.FeedE.rotateAngleX = 0.329293F;
        this.FeedE.rotateAngleY = -2.02634F;
        this.FeedE.rotateAngleZ = 0.3414953F;

        this.FeedF.rotateAngleX = 0.223693F;
        this.FeedF.rotateAngleY = -1.865655F;
        this.FeedF.rotateAngleZ = 0.009323301F;

        this.FeedG.rotateAngleX = 0.2029555F;
        this.FeedG.rotateAngleY = -1.465355F;
        this.FeedG.rotateAngleZ = 0.09517268F;

        this.FeedH.rotateAngleX = 0.286131F;
        this.FeedH.rotateAngleY = 2.831252F;
        this.FeedH.rotateAngleZ = 0.4113167F;

        this.FeedA1.rotateAngleX = 0.5566564F;
        this.FeedA1.rotateAngleY = 0.6934777F;
        this.FeedA1.rotateAngleZ = 0.418777F;

        this.FeedB1.rotateAngleX = 0.3321232F;
        this.FeedB1.rotateAngleY = 1.126472F;
        this.FeedB1.rotateAngleZ = 0.4568707F;

        this.FeedC1.rotateAngleX = 0.5561382F;
        this.FeedC1.rotateAngleY = 0.6948478F;
        this.FeedC1.rotateAngleZ = 0.4195006F;

        this.FeedD1.rotateAngleX = 0.329293F;
        this.FeedD1.rotateAngleY = -2.02634F;
        this.FeedD1.rotateAngleZ = 0.4998787F;

        this.FeedE1.rotateAngleX = 0.329293F;
        this.FeedE1.rotateAngleY = -2.02634F;
        this.FeedE1.rotateAngleZ = 0.3414953F;

        this.FeedF1.rotateAngleX = 0.223693F;
        this.FeedF1.rotateAngleY = -1.865655F;
        this.FeedF1.rotateAngleZ = 0.009323301F;

        this.FeedG1.rotateAngleX = 0.2029555F;
        this.FeedG1.rotateAngleY = -1.465355F;
        this.FeedG1.rotateAngleZ = 0.09517268F;

        this.FeedH1.rotateAngleX = 0.286131F;
        this.FeedH1.rotateAngleY = 2.831252F;
        this.FeedH1.rotateAngleZ = 0.4113167F;

        this.FeedA2.rotateAngleX = 0.5566564F;
        this.FeedA2.rotateAngleY = 0.6934777F;
        this.FeedA2.rotateAngleZ = 0.418777F;

        this.FeedB2.rotateAngleX = 0.3321232F;
        this.FeedB2.rotateAngleY = 1.126472F;
        this.FeedB2.rotateAngleZ = 0.4568707F;

        this.FeedC2.rotateAngleX = 0.5561382F;
        this.FeedC2.rotateAngleY = 0.6948478F;
        this.FeedC2.rotateAngleZ = 0.4195006F;

        this.FeedD2.rotateAngleX = 0.329293F;
        this.FeedD2.rotateAngleY = -2.02634F;
        this.FeedD2.rotateAngleZ = 0.4998787F;

        this.FeedE2.rotateAngleX = 0.329293F;
        this.FeedE2.rotateAngleY = -2.02634F;
        this.FeedE2.rotateAngleZ = 0.3414953F;

        this.FeedF2.rotateAngleX = 0.223693F;
        this.FeedF2.rotateAngleY = -1.865655F;
        this.FeedF2.rotateAngleZ = 0.009323301F;

        this.FeedG2.rotateAngleX = 0.2029555F;
        this.FeedG2.rotateAngleY = -1.465355F;
        this.FeedG2.rotateAngleZ = 0.09517268F;

        this.FeedH2.rotateAngleX = 0.286131F;
        this.FeedH2.rotateAngleY = 2.831252F;
        this.FeedH2.rotateAngleZ = 0.4113167F;

    }

}

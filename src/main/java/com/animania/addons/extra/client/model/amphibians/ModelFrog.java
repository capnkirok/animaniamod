package com.animania.addons.extra.client.model.amphibians;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFrog extends ModelBase
{
    ModelRenderer MainBody;
    ModelRenderer HindLegR;
    ModelRenderer HindFootR;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Snout1;
    ModelRenderer Snout2;
    ModelRenderer EyeL;
    ModelRenderer EyeR;
    ModelRenderer FrontLegRTop;
    ModelRenderer FrontLegRMiddle;
    ModelRenderer FrontFootR;
    ModelRenderer FrontLegLTop;
    ModelRenderer FrontLegLMiddle;
    ModelRenderer FrontFootL;
    ModelRenderer HindLegL;
    ModelRenderer HindFootL;

    public ModelFrog() {
        this(0.0f);
    }

    public ModelFrog(float par1) {
        this.textureHeight = 32;
        this.textureWidth = 32;
        this.MainBody = new ModelRenderer(this, 0, 0);
        this.MainBody.setTextureSize(32, 32);
        this.MainBody.addBox(-2F, 0F, -5F, 4, 3, 5);
        this.MainBody.setRotationPoint(-2.25725E-07F, 19F, -2.581994F);
        this.HindLegR = new ModelRenderer(this, 20, 0);
        this.HindLegR.setTextureSize(32, 32);
        this.HindLegR.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.HindLegR.setRotationPoint(-2.5F, 22.47402F, 5.702166E-07F);
        this.HindFootR = new ModelRenderer(this, 21, 6);
        this.HindFootR.setTextureSize(32, 32);
        this.HindFootR.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
        this.HindFootR.setRotationPoint(-2.5F, 23.47402F, -0.4999994F);
        this.Neck = new ModelRenderer(this, 2, 19);
        this.Neck.setTextureSize(32, 32);
        this.Neck.addBox(-2F, -1F, 0F, 4, 1, 2);
        this.Neck.setRotationPoint(-3.615531E-07F, 21.56633F, -4.135685F);
        this.Head = new ModelRenderer(this, 1, 8);
        this.Head.setTextureSize(32, 32);
        this.Head.addBox(-2F, -2F, 0F, 4, 2, 3);
        this.Head.setRotationPoint(-0.01000047F, 17.94402F, -5.39F);
        this.Snout1 = new ModelRenderer(this, 2, 13);
        this.Snout1.setTextureSize(32, 32);
        this.Snout1.addBox(-2F, 0F, 0F, 4, 1, 2);
        this.Snout1.setRotationPoint(-4.719492E-07F, 17.94402F, -5.39F);
        this.Snout2 = new ModelRenderer(this, 2, 16);
        this.Snout2.setTextureSize(32, 32);
        this.Snout2.addBox(-2F, 0F, -2F, 4, 1, 2);
        this.Snout2.setRotationPoint(0.009999371F, 18.83004F, -7.183031F);
        this.EyeL = new ModelRenderer(this, 0, 8);
        this.EyeL.setTextureSize(32, 32);
        this.EyeL.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.EyeL.setRotationPoint(1.299999F, 17.97402F, -5.900001F);
        this.EyeR = new ModelRenderer(this, 0, 8);
        this.EyeR.setTextureSize(32, 32);
        this.EyeR.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.EyeR.setRotationPoint(-1.3F, 17.97402F, -5.9F);
        this.FrontLegRTop = new ModelRenderer(this, 24, 11);
        this.FrontLegRTop.setTextureSize(32, 32);
        this.FrontLegRTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.FrontLegRTop.setRotationPoint(-2.5F, 19.97402F, -4.5F);
        this.FrontLegRMiddle = new ModelRenderer(this, 24, 14);
        this.FrontLegRMiddle.setTextureSize(32, 32);
        this.FrontLegRMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
        this.FrontLegRMiddle.setRotationPoint(-2.49F, 21.42906F, -3.039574F);
        this.FrontFootR = new ModelRenderer(this, 23, 18);
        this.FrontFootR.setTextureSize(32, 32);
        this.FrontFootR.addBox(-0.5F, -1F, 0F, 1, 1, 2);
        this.FrontFootR.setRotationPoint(-2.49F, 24.03089F, -4.533064F);
        this.FrontLegLTop = new ModelRenderer(this, 24, 11);
        this.FrontLegLTop.setTextureSize(32, 32);
        this.FrontLegLTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.FrontLegLTop.setRotationPoint(2.5F, 19.97402F, -4.5F);
        this.FrontLegLMiddle = new ModelRenderer(this, 24, 14);
        this.FrontLegLMiddle.setTextureSize(32, 32);
        this.FrontLegLMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
        this.FrontLegLMiddle.setRotationPoint(2.49F, 21.42906F, -3.039574F);
        this.FrontFootL = new ModelRenderer(this, 23, 18);
        this.FrontFootL.setTextureSize(32, 32);
        this.FrontFootL.addBox(-0.5F, -1F, 0F, 1, 1, 2);
        this.FrontFootL.setRotationPoint(2.49F, 24.03089F, -4.533064F);
        this.HindLegL = new ModelRenderer(this, 20, 0);
        this.HindLegL.setTextureSize(32, 32);
        this.HindLegL.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.HindLegL.setRotationPoint(2.5F, 22.47402F, 1.253854E-07F);
        this.HindFootL = new ModelRenderer(this, 21, 6);
        this.HindFootL.setTextureSize(32, 32);
        this.HindFootL.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
        this.HindFootL.setRotationPoint(2.5F, 23.47402F, -0.4999997F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.MainBody.rotateAngleX = 0.5443909F;
        this.MainBody.rotateAngleY = -3.141593F;
        this.MainBody.rotateAngleZ = -3.529419E-16F;
        this.MainBody.renderWithRotation(scale);

        this.HindLegR.rotateAngleX = 0.3026092F;
        this.HindLegR.rotateAngleY = -3.141593F;
        this.HindLegR.rotateAngleZ = 8.267461E-18F;
        this.HindLegR.renderWithRotation(scale);

        this.HindFootR.rotateAngleX = -1.947392E-07F;
        this.HindFootR.rotateAngleY = -3.141593F;
        this.HindFootR.rotateAngleZ = 7.89182E-18F;
        this.HindFootR.renderWithRotation(scale);

        this.Neck.rotateAngleX = 0.8451216F;
        this.Neck.rotateAngleY = -3.141593F;
        this.Neck.rotateAngleZ = 8.215925E-16F;
        this.Neck.renderWithRotation(scale);

        this.Head.rotateAngleX = -0.3597011F;
        this.Head.rotateAngleY = 8.74228E-08F;
        this.Head.rotateAngleZ = -3.141593F;
        this.Head.renderWithRotation(scale);

        this.Snout1.rotateAngleX = -0.458957F;
        this.Snout1.rotateAngleY = -3.141593F;
        this.Snout1.rotateAngleZ = 2.203421E-16F;
        this.Snout1.renderWithRotation(scale);

        this.Snout2.rotateAngleX = 0.1700074F;
        this.Snout2.rotateAngleY = -3.141593F;
        this.Snout2.rotateAngleZ = 1.505328E-15F;
        this.Snout2.renderWithRotation(scale);

        this.EyeL.rotateAngleX = -1.248631E-07F;
        this.EyeL.rotateAngleY = 2.792527F;
        this.EyeL.rotateAngleZ = 4.380097E-08F;
        this.EyeL.renderWithRotation(scale);

        this.EyeR.rotateAngleX = -1.248631E-07F;
        this.EyeR.rotateAngleY = -2.792527F;
        this.EyeR.rotateAngleZ = -4.380097E-08F;
        this.EyeR.renderWithRotation(scale);

        this.FrontLegRTop.rotateAngleX = -0.5422653F;
        this.FrontLegRTop.rotateAngleY = -3.141593F;
        this.FrontLegRTop.rotateAngleZ = -2.302643E-15F;
        this.FrontLegRTop.renderWithRotation(scale);

        this.FrontLegRMiddle.rotateAngleX = 0.5210952F;
        this.FrontLegRMiddle.rotateAngleY = -3.141593F;
        this.FrontLegRMiddle.rotateAngleZ = -2.330141E-15F;
        this.FrontLegRMiddle.renderWithRotation(scale);

        this.FrontFootR.rotateAngleX = -1.766048E-07F;
        this.FrontFootR.rotateAngleY = -3.141593F;
        this.FrontFootR.rotateAngleZ = -2.020871E-15F;
        this.FrontFootR.renderWithRotation(scale);

        this.FrontLegLTop.rotateAngleX = -0.5422653F;
        this.FrontLegLTop.rotateAngleY = -3.141593F;
        this.FrontLegLTop.rotateAngleZ = -2.302643E-15F;
        this.FrontLegLTop.renderWithRotation(scale);

        this.FrontLegLMiddle.rotateAngleX = 0.5210952F;
        this.FrontLegLMiddle.rotateAngleY = -3.141593F;
        this.FrontLegLMiddle.rotateAngleZ = -2.330141E-15F;
        this.FrontLegLMiddle.renderWithRotation(scale);

        this.FrontFootL.rotateAngleX = -1.766048E-07F;
        this.FrontFootL.rotateAngleY = -3.141593F;
        this.FrontFootL.rotateAngleZ = -2.020871E-15F;
        this.FrontFootL.renderWithRotation(scale);

        this.HindLegL.rotateAngleX = 0.3026092F;
        this.HindLegL.rotateAngleY = -3.141593F;
        this.HindLegL.rotateAngleZ = 8.267461E-18F;
        this.HindLegL.renderWithRotation(scale);

        this.HindFootL.rotateAngleX = -1.947392E-07F;
        this.HindFootL.rotateAngleY = -3.141593F;
        this.HindFootL.rotateAngleZ = 7.89182E-18F;
        this.HindFootL.renderWithRotation(scale);

    }

}

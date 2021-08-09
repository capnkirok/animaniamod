package com.animania.addons.extra.client.model.amphibians;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToad extends ModelBase
{
    ModelRenderer MainBody;
    ModelRenderer Chubs;
    ModelRenderer Head;
    ModelRenderer Snout1;
    ModelRenderer Snout2;
    ModelRenderer EyeL;
    ModelRenderer EyeR;
    ModelRenderer Neck;
    ModelRenderer HindLegR;
    ModelRenderer HindFootR;
    ModelRenderer HindLegL;
    ModelRenderer HindFootL;
    ModelRenderer FrontLegLTop;
    ModelRenderer FrontLegLMiddle;
    ModelRenderer FrontFootL;
    ModelRenderer FrontLegRTop;
    ModelRenderer FrontLegRMiddle;
    ModelRenderer FrontFootR;

    public ModelToad() {
        this(0.0f);
    }

    public ModelToad(float par1) {
        this.MainBody = new ModelRenderer(this, 0, 0);
        this.MainBody.setTextureSize(32, 32);
        this.MainBody.addBox(-2F, 0F, -7F, 4, 3, 7);
        this.MainBody.setRotationPoint(-2.25725E-07F, 19F, -2.581994F);
        this.Chubs = new ModelRenderer(this, 7, 22);
        this.Chubs.setTextureSize(32, 32);
        this.Chubs.addBox(-3F, 0F, -5.5F, 6, 3, 6);
        this.Chubs.setRotationPoint(-1.577356E-07F, 19.80322F, -1.804284F);
        this.Head = new ModelRenderer(this, 0, 10);
        this.Head.setTextureSize(32, 32);
        this.Head.addBox(-2F, -2F, -3F, 4, 2, 3);
        this.Head.setRotationPoint(-0.01000023F, 19F, -2.581994F);
        this.Snout1 = new ModelRenderer(this, 0, 15);
        this.Snout1.setTextureSize(32, 32);
        this.Snout1.addBox(-2F, 0F, 0F, 4, 1, 2);
        this.Snout1.setRotationPoint(-4.854712E-07F, 18.5422F, -5.546859F);
        this.Snout2 = new ModelRenderer(this, 0, 21);
        this.Snout2.setTextureSize(32, 32);
        this.Snout2.addBox(-2F, 0F, -2F, 4, 1, 2);
        this.Snout2.setRotationPoint(0.00999938F, 19.77704F, -7.120125F);
        this.EyeL = new ModelRenderer(this, 1, 2);
        this.EyeL.setTextureSize(32, 32);
        this.EyeL.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.EyeL.setRotationPoint(1.3F, 18.67613F, -6.039873F);
        this.EyeR = new ModelRenderer(this, 1, 2);
        this.EyeR.setTextureSize(32, 32);
        this.EyeR.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.EyeR.setRotationPoint(-1.300001F, 18.67613F, -6.039873F);
        this.Neck = new ModelRenderer(this, 0, 18);
        this.Neck.setTextureSize(32, 32);
        this.Neck.addBox(-2F, -1F, 0F, 4, 1, 2);
        this.Neck.setRotationPoint(-3.141467E-07F, 21.83037F, -3.576481F);
        this.HindLegR = new ModelRenderer(this, 20, 0);
        this.HindLegR.setTextureSize(32, 32);
        this.HindLegR.addBox(0F, -1F, 0F, 2, 2, 4);
        this.HindLegR.setRotationPoint(-2.5F, 23F, 2F);
        this.HindFootR = new ModelRenderer(this, 21, 6);
        this.HindFootR.setTextureSize(32, 32);
        this.HindFootR.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
        this.HindFootR.setRotationPoint(-3.90315F, 23.4039F, -0.1988889F);
        this.HindLegL = new ModelRenderer(this, 20, 0);
        this.HindLegL.setTextureSize(32, 32);
        this.HindLegL.addBox(-2F, -1F, 0F, 2, 2, 4);
        this.HindLegL.setRotationPoint(2.5F, 23F, 2F);
        this.HindFootL = new ModelRenderer(this, 21, 6);
        this.HindFootL.setTextureSize(32, 32);
        this.HindFootL.addBox(-0.5F, -0.5F, -2F, 1, 1, 4);
        this.HindFootL.setRotationPoint(3.90315F, 23.4039F, -0.1988887F);
        this.FrontLegLTop = new ModelRenderer(this, 24, 11);
        this.FrontLegLTop.setTextureSize(32, 32);
        this.FrontLegLTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.FrontLegLTop.setRotationPoint(2.5F, 19.97F, -4.5F);
        this.FrontLegLMiddle = new ModelRenderer(this, 24, 14);
        this.FrontLegLMiddle.setTextureSize(32, 32);
        this.FrontLegLMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
        this.FrontLegLMiddle.setRotationPoint(2.49F, 21.42505F, -3.039574F);
        this.FrontFootL = new ModelRenderer(this, 23, 18);
        this.FrontFootL.setTextureSize(32, 32);
        this.FrontFootL.addBox(-0.5F, -1F, 0F, 1, 1, 2);
        this.FrontFootL.setRotationPoint(2.49F, 24.02687F, -4.533063F);
        this.FrontLegRTop = new ModelRenderer(this, 24, 11);
        this.FrontLegRTop.setTextureSize(32, 32);
        this.FrontLegRTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
        this.FrontLegRTop.setRotationPoint(-2.5F, 19.97F, -4.5F);
        this.FrontLegRMiddle = new ModelRenderer(this, 24, 14);
        this.FrontLegRMiddle.setTextureSize(32, 32);
        this.FrontLegRMiddle.addBox(-0.5F, 0F, 0F, 1, 3, 1);
        this.FrontLegRMiddle.setRotationPoint(-2.51F, 21.42505F, -3.039574F);
        this.FrontFootR = new ModelRenderer(this, 23, 18);
        this.FrontFootR.setTextureSize(32, 32);
        this.FrontFootR.addBox(-0.5F, -1F, 0F, 1, 1, 2);
        this.FrontFootR.setRotationPoint(-2.51F, 24.02687F, -4.533063F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.MainBody.rotateAngleX = 0.3378883F;
        this.MainBody.rotateAngleY = -3.141593F;
        this.MainBody.rotateAngleZ = -9.023148E-16F;
        this.MainBody.renderWithRotation(par7);

        this.Chubs.rotateAngleX = 0.3378883F;
        this.Chubs.rotateAngleY = -3.141593F;
        this.Chubs.rotateAngleZ = -9.023148E-16F;
        this.Chubs.renderWithRotation(par7);

        this.Head.rotateAngleX = -0.1531985F;
        this.Head.rotateAngleY = 8.742279E-08F;
        this.Head.rotateAngleZ = -3.141593F;
        this.Head.renderWithRotation(par7);

        this.Snout1.rotateAngleX = -0.6654596F;
        this.Snout1.rotateAngleY = -3.141593F;
        this.Snout1.rotateAngleZ = -4.153653E-15F;
        this.Snout1.renderWithRotation(par7);

        this.Snout2.rotateAngleX = -0.03649529F;
        this.Snout2.rotateAngleY = -3.141593F;
        this.Snout2.rotateAngleZ = -3.182659E-15F;
        this.Snout2.renderWithRotation(par7);

        this.EyeL.rotateAngleX = -0.1938854F;
        this.EyeL.rotateAngleY = 2.785568F;
        this.EyeL.rotateAngleZ = 0.07152723F;
        this.EyeL.renderWithRotation(par7);

        this.EyeR.rotateAngleX = -0.1938854F;
        this.EyeR.rotateAngleY = -2.785568F;
        this.EyeR.rotateAngleZ = -0.07152724F;
        this.EyeR.renderWithRotation(par7);

        this.Neck.rotateAngleX = 0.6386186F;
        this.Neck.rotateAngleY = -3.141593F;
        this.Neck.rotateAngleZ = -4.058466E-15F;
        this.Neck.renderWithRotation(par7);

        this.HindLegR.rotateAngleX = 0.3026401F;
        this.HindLegR.rotateAngleY = -2.96706F;
        this.HindLegR.rotateAngleZ = -1.009837E-08F;
        this.HindLegR.renderWithRotation(par7);

        this.HindFootR.rotateAngleX = 3.076197E-05F;
        this.HindFootR.rotateAngleY = -2.96706F;
        this.HindFootR.rotateAngleZ = -9.639443E-09F;
        this.HindFootR.renderWithRotation(par7);

        this.HindLegL.rotateAngleX = 0.3026401F;
        this.HindLegL.rotateAngleY = 2.96706F;
        this.HindLegL.rotateAngleZ = 1.009837E-08F;
        this.HindLegL.renderWithRotation(par7);

        this.HindFootL.rotateAngleX = 3.076197E-05F;
        this.HindFootL.rotateAngleY = 2.96706F;
        this.HindFootL.rotateAngleZ = 9.639443E-09F;
        this.HindFootL.renderWithRotation(par7);

        this.FrontLegLTop.rotateAngleX = -0.5422654F;
        this.FrontLegLTop.rotateAngleY = -3.141593F;
        this.FrontLegLTop.rotateAngleZ = -6.446634E-16F;
        this.FrontLegLTop.renderWithRotation(par7);

        this.FrontLegLMiddle.rotateAngleX = 0.521095F;
        this.FrontLegLMiddle.rotateAngleY = -3.141593F;
        this.FrontLegLMiddle.rotateAngleZ = -2.761143E-15F;
        this.FrontLegLMiddle.renderWithRotation(par7);

        this.FrontFootL.rotateAngleX = -2.610812E-07F;
        this.FrontFootL.rotateAngleY = -3.141593F;
        this.FrontFootL.rotateAngleZ = -2.39467E-15F;
        this.FrontFootL.renderWithRotation(par7);

        this.FrontLegRTop.rotateAngleX = -0.5422654F;
        this.FrontLegRTop.rotateAngleY = -3.141593F;
        this.FrontLegRTop.rotateAngleZ = -6.446634E-16F;
        this.FrontLegRTop.renderWithRotation(par7);

        this.FrontLegRMiddle.rotateAngleX = 0.521095F;
        this.FrontLegRMiddle.rotateAngleY = -3.141593F;
        this.FrontLegRMiddle.rotateAngleZ = -2.761143E-15F;
        this.FrontLegRMiddle.renderWithRotation(par7);

        this.FrontFootR.rotateAngleX = -2.610812E-07F;
        this.FrontFootR.rotateAngleY = -3.141593F;
        this.FrontFootR.rotateAngleZ = -2.39467E-15F;
        this.FrontFootR.renderWithRotation(par7);

    }

}

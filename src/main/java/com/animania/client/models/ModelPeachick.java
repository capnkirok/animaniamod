package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelPeachick extends ModelBase
{
    private float lastLimb;
    ModelRenderer RootNode;
    ModelRenderer Neck;
    ModelRenderer Neck2;
    ModelRenderer Neck3;
    ModelRenderer Head;
    ModelRenderer BeakBottom;
    ModelRenderer BeakTop;
    ModelRenderer Body1;
    ModelRenderer leg1Top;
    ModelRenderer leg1top2;
    ModelRenderer leg1bottom;
    ModelRenderer Foot1;
    ModelRenderer Foot1b;
    ModelRenderer leg2Top;
    ModelRenderer leg2top2;
    ModelRenderer leg2bottom;
    ModelRenderer Foot2;
    ModelRenderer Foot2b;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Wing1;
    ModelRenderer Wing1a;
    ModelRenderer Wing1b;
    ModelRenderer Wing2;
    ModelRenderer Wing2a;
    ModelRenderer Wing2b;

    public ModelPeachick() {
        this(0.0f);
    }

    public ModelPeachick(float par1) {
        this.RootNode = new ModelRenderer(this, 16, 16);
        this.RootNode.setTextureSize(64, 64);
        this.RootNode.addBox(0F, 0F, 0F, 0, 0, 0);
        this.RootNode.setRotationPoint(0F, 11.5F, 0F);

        this.Neck = new ModelRenderer(this, 3, 19);
        this.Neck.setTextureSize(64, 64);
        this.Neck.addBox(-2.5F, -2.5F, -2F, 5, 5, 4);
        this.Neck.setRotationPoint(0F, 13.3998F, -1F);

        this.Neck2 = new ModelRenderer(this, 21, 21);
        this.Neck2.setTextureSize(64, 64);
        this.Neck2.addBox(-2F, -2F, -1.5F, 4, 4, 3);
        this.Neck2.setRotationPoint(0F, 11.90154F - 13.3998F, -3.01686F + 1F);

        this.Neck3 = new ModelRenderer(this, 5, 30);
        this.Neck3.setTextureSize(64, 64);
        this.Neck3.addBox(-1.5F, -1.5F, -5F, 3, 3, 4);
        this.Neck3.setRotationPoint(0F, 12.19275F - 13.3998F, -3.423303F + 1F);

        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setTextureSize(64, 64);
        this.Head.addBox(-1.51F, -2.5F, -2F, 3, 3, 4);
        this.Head.setRotationPoint(0F, 7.300148F - 13.3998F, -4.456411F + 1F);

        this.BeakBottom = new ModelRenderer(this, 19, 0);
        this.BeakBottom.setTextureSize(64, 64);
        this.BeakBottom.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.BeakBottom.setRotationPoint(0F, 6.662474F - 13.3998F, -7.51361F + 1F);

        this.BeakTop = new ModelRenderer(this, 19, 0);
        this.BeakTop.setTextureSize(64, 64);
        this.BeakTop.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
        this.BeakTop.setRotationPoint(0F, 6.360796F - 13.3998F, -7.418793F + 1F);

        this.Body1 = new ModelRenderer(this, 0, 7);
        this.Body1.setTextureSize(64, 64);
        this.Body1.addBox(-3F, -3F, -3F, 6, 6, 6);
        this.Body1.setRotationPoint(0F, 14.5F, 2F);

        this.leg1Top = new ModelRenderer(this, 39, 0);
        this.leg1Top.setTextureSize(64, 64);
        this.leg1Top.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.leg1Top.setRotationPoint(-1.5F, 17.79997F, 1.75F);

        this.leg1top2 = new ModelRenderer(this, 35, 0);
        this.leg1top2.setTextureSize(64, 64);
        this.leg1top2.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1);
        this.leg1top2.setRotationPoint(-1.5F + 1.5F, 20.75261F - 17.79997F, 2.602251F - 1.75F);

        this.leg1bottom = new ModelRenderer(this, 35, 0);
        this.leg1bottom.setTextureSize(64, 64);
        this.leg1bottom.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1);
        this.leg1bottom.setRotationPoint(-1.5F + 1.5F, 23.5F - 17.79997F, 2.25F - 1.75F);

        this.Foot1 = new ModelRenderer(this, 25, 0);
        this.Foot1.setTextureSize(64, 64);
        this.Foot1.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
        this.Foot1.setRotationPoint(-2.25F + 1.5F, 23.95373F - 17.79997F, 1.195186F - 1.75F);

        this.Foot1b = new ModelRenderer(this, 25, 0);
        this.Foot1b.setTextureSize(64, 64);
        this.Foot1b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
        this.Foot1b.setRotationPoint(-0.75F + 1.5F, 23.95373F - 17.79997F, 1.195186F - 1.75F);

        this.leg2Top = new ModelRenderer(this, 39, 0);
        this.leg2Top.setTextureSize(64, 64);
        this.leg2Top.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.leg2Top.setRotationPoint(1.5F, 17.79997F, 1.75F);

        this.leg2top2 = new ModelRenderer(this, 35, 0);
        this.leg2top2.setTextureSize(64, 64);
        this.leg2top2.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
        this.leg2top2.setRotationPoint(1.5F - 1.5F, 21.23324F - 17.79997F, 2.74007F - 1.75F);

        this.leg2bottom = new ModelRenderer(this, 35, 0);
        this.leg2bottom.setTextureSize(64, 64);
        this.leg2bottom.addBox(-0.5F, -2.5F, -0.5F, 1, 3, 1);
        this.leg2bottom.setRotationPoint(1.5F - 1.5F, 23.5F - 17.79997F, 2.25F - 1.75F);

        this.Foot2 = new ModelRenderer(this, 25, 0);
        this.Foot2.setTextureSize(64, 64);
        this.Foot2.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
        this.Foot2.setRotationPoint(0.75F - 1.5F, 23.99486F - 17.79997F, 1.198088F - 1.75F);

        this.Foot2b = new ModelRenderer(this, 25, 0);
        this.Foot2b.setTextureSize(64, 64);
        this.Foot2b.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3);
        this.Foot2b.setRotationPoint(2.25F - 1.5F, 23.99486F - 17.79997F, 1.198088F - 1.75F);

        this.Tail1 = new ModelRenderer(this, 36, 20);
        this.Tail1.setTextureSize(64, 64);
        this.Tail1.addBox(-2.5F, -2F, -2F, 5, 4, 4);
        this.Tail1.setRotationPoint(0F, 15.10777F, 5.446827F);

        this.Tail2 = new ModelRenderer(this, 25, 14);
        this.Tail2.setTextureSize(64, 64);
        this.Tail2.addBox(-2F, -1F, -1.5F, 4, 2, 3);
        this.Tail2.setRotationPoint(0F, 15.25801F - 15.10777F, 8.135215F - 5.446827F);

        this.Wing1 = new ModelRenderer(this, 1, 41);
        this.Wing1.setTextureSize(64, 64);
        this.Wing1.addBox(-0.5F, -1.5F, -1.5F, 1, 3, 7);
        this.Wing1.setRotationPoint(-3.5F, 13.6603F - 0.8F, 0.1172086F);

        this.Wing1a = new ModelRenderer(this, 4, 41);
        this.Wing1a.setTextureSize(64, 64);
        this.Wing1a.addBox(-0.5F, -1F, 1F, 1, 2, 2);
        this.Wing1a.setRotationPoint(-3.5F + 3.5F, 14.96746F - 13.6603F - 1.3F, 4.423172F - 0.1172086F);

        this.Wing1b = new ModelRenderer(this, 2, 41);
        this.Wing1b.setTextureSize(64, 64);
        this.Wing1b.addBox(-0.5F, -0.5F, -1F, 1, 1, 6);
        this.Wing1b.setRotationPoint(-3.5F + 3.5F, 15.57406F - 13.6603F, -0.4637532F - 0.1172086F);

        this.Wing2 = new ModelRenderer(this, 1, 41);
        this.Wing2.setTextureSize(64, 64);
        this.Wing2.addBox(-0.5F, -1.5F, -1.5F, 1, 3, 7);
        this.Wing2.setRotationPoint(3.5F, 13.6603F - 0.8F, 0.1172086F);

        this.Wing2a = new ModelRenderer(this, 4, 41);
        this.Wing2a.setTextureSize(64, 64);
        this.Wing2a.addBox(-0.5F, -1F, 1F, 1, 2, 2);
        this.Wing2a.setRotationPoint(3.5F - 3.5F, 14.96746F - 13.6603F - 1.3F, 4.423172F - 0.1172086F);

        this.Wing2b = new ModelRenderer(this, 2, 41);
        this.Wing2b.setTextureSize(64, 64);
        this.Wing2b.addBox(-0.5F, -0.5F, -1F, 1, 1, 6);
        this.Wing2b.setRotationPoint(3.5F - 3.5F, 15.57406F - 13.6603F, -0.4637532F - 0.1172086F);

        this.Neck.addChild(this.Neck2);
        this.Neck.addChild(this.Neck3);
        this.Neck.addChild(this.Head);
        this.Neck.addChild(this.BeakTop);
        this.Neck.addChild(this.BeakBottom);

        this.leg1Top.addChild(this.leg1top2);
        this.leg1Top.addChild(this.leg1bottom);
        this.leg1Top.addChild(this.Foot1);
        this.leg1Top.addChild(this.Foot1b);

        this.leg2Top.addChild(this.leg2top2);
        this.leg2Top.addChild(this.leg2bottom);
        this.leg2Top.addChild(this.Foot2);
        this.leg2Top.addChild(this.Foot2b);

        this.Wing1.addChild(this.Wing1a);
        this.Wing1.addChild(this.Wing1b);
        this.Wing2.addChild(this.Wing2a);
        this.Wing2.addChild(this.Wing2b);

        this.Tail1.addChild(this.Tail2);

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        this.Neck.render(scale);
        this.Body1.render(scale);
        this.leg1Top.render(scale);
        this.leg2Top.render(scale);
        this.Wing1.render(scale);
        this.Wing2.render(scale);
        this.Tail1.render(scale);

        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {

        super.setLivingAnimations(entitylivingbaseIn, limbSwingAmount, ageInTicks, partialTickTime);
        if (limbSwingAmount > this.lastLimb) {
            // this.Neck.rotateAngleX = Math.abs(((30F / (float)Math.PI)) * 1.4F
            // * limbSwingAmount);
        }
        else {
            // do nothing
        }

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
            Entity entityIn) {

        this.Neck.rotateAngleX = -0.539251F;
        this.Neck2.rotateAngleX = -0.9490896F;
        this.Neck3.rotateAngleX = -1.376694F;
        this.Head.rotateAngleX = 0.07594994F;
        this.BeakBottom.rotateAngleX = 0.07594992F;
        this.BeakTop.rotateAngleX = 0.3341772F;
        this.Body1.rotateAngleX = -0.1745329F;
        this.leg1Top.rotateAngleX = 0.2792527F;
        this.leg1top2.rotateAngleX = 0.2792527F;
        this.leg1bottom.rotateAngleX = -0.1745331F;
        this.Foot1.rotateAngleX = -1.588343E-07F;
        this.Foot1b.rotateAngleX = -1.692242E-07F;
        this.leg2Top.rotateAngleX = 0.2792527F;
        this.leg2top2.rotateAngleX = 0.2792527F;
        this.leg2bottom.rotateAngleX = -0.1745331F;
        this.Foot2.rotateAngleX = -1.588343E-07F;
        this.Foot2b.rotateAngleX = -1.692242E-07F;
        this.Tail1.rotateAngleX = -0.4363323F;
        this.Tail2.rotateAngleX = -0.121238F;
        this.Wing1.rotateAngleX = -0.2947292F;
        // Wing1a.rotateAngleX = -0.2947292F;
        // Wing1b.rotateAngleX = -0.2947292F;
        this.Wing2.rotateAngleX = -0.2947292F;
        // Wing2a.rotateAngleX = -0.2947292F;
        // Wing2b.rotateAngleX = -0.2947292F;

        this.Neck.rotateAngleX = Math.abs(headPitch / (180F / (float) Math.PI) * 1.4F * limbSwingAmount);
        this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float) Math.PI));

        this.Body1.rotateAngleX = (float) Math.PI / 2F;
        // this.leg1Top.rotateAngleX = ((float)Math.PI / 2F);
        // this.leg2Top.rotateAngleX = ((float)Math.PI / 2F);
        this.leg1Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.Wing1.rotateAngleZ = ageInTicks;
        this.Wing2.rotateAngleZ = -ageInTicks;
    }

}

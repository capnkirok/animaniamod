package com.animania.addons.farm.client.model.chicken;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelChick extends ModelBase
{
    private float lastLimb;
    ModelRenderer RootNode;
    ModelRenderer Body;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer wing3;
    ModelRenderer wing4;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer BeakTop;
    ModelRenderer leg2Top;
    ModelRenderer leg2;
    ModelRenderer foot2;
    ModelRenderer leg1Top;
    ModelRenderer leg1;
    ModelRenderer foot1;

    public ModelChick() {
        this(0.0f);
    }

    public ModelChick(float par1) {
        this.RootNode = new ModelRenderer(this, 16, 16);
        this.RootNode.setTextureSize(16, 32);
        this.RootNode.addBox(0F, 0F, 0F, 0, 0, 0);
        this.RootNode.setRotationPoint(0F, 24F, 0F);

        this.Body = new ModelRenderer(this, 0, 10);
        this.Body.setTextureSize(16, 32);
        this.Body.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
        this.Body.setRotationPoint(0F, 20.5F, 0F);

        this.tail1 = new ModelRenderer(this, 2, 16);
        this.tail1.setTextureSize(16, 32);
        this.tail1.addBox(-1F, -1F, -1F, 2, 2, 2);
        this.tail1.setRotationPoint(0F, 20.39043F, 1.525777F);

        this.tail2 = new ModelRenderer(this, 3, 20);
        this.tail2.setTextureSize(16, 32);
        this.tail2.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
        // tail2.setRotationPoint( 0F, 19.6524F, 2.637223F );
        this.tail2.setRotationPoint(0F, -073803F, 1.1111F);

        this.wing1 = new ModelRenderer(this, 2, 23);
        this.wing1.setTextureSize(16, 32);
        this.wing1.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
        this.wing1.setRotationPoint(1.5F, 20.17379F, -1.547121F);

        this.wing2 = new ModelRenderer(this, 2, 26);
        this.wing2.setTextureSize(16, 32);
        this.wing2.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
        this.wing2.setRotationPoint(1.4F, 20.66941F, -1.480338F);

        this.wing3 = new ModelRenderer(this, 2, 23);
        this.wing3.setTextureSize(16, 32);
        this.wing3.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
        this.wing3.setRotationPoint(-1.5F, 20.17379F, -1.547121F);

        this.wing4 = new ModelRenderer(this, 2, 26);
        this.wing4.setTextureSize(16, 32);
        this.wing4.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
        this.wing4.setRotationPoint(-1.4F, 20.66941F, -1.480338F);

        this.Neck = new ModelRenderer(this, 0, 5);
        this.Neck.setTextureSize(16, 32);
        this.Neck.addBox(-1F, -1F, -1.5F, 2, 2, 3);
        // Neck.setRotationPoint( 0F, 20.1F, -0.7F );
        this.Neck.setRotationPoint(0F, 20.1F, -0.7F);

        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setTextureSize(16, 32);
        this.Head.addBox(-1F, -1F, -1F, 2, 2, 2);
        // Head.setRotationPoint( 0F, 18.98694F, -2.118133F );
        this.Head.setRotationPoint(0F, -1.11309F, -1.418133F);

        this.BeakTop = new ModelRenderer(this, 9, 1);
        this.BeakTop.setTextureSize(16, 32);
        this.BeakTop.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        // BeakTop.setRotationPoint( 0F, 19.06768F, -3.020065F );
        this.BeakTop.setRotationPoint(0F, -1.03F, -2.32F);

        this.leg2Top = new ModelRenderer(this, 11, 8);
        this.leg2Top.setTextureSize(16, 32);
        this.leg2Top.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.leg2Top.setRotationPoint(0.75F, 22.1F, -2.89661E-07F);

        this.leg2 = new ModelRenderer(this, 9, 2);
        this.leg2.setTextureSize(16, 32);
        this.leg2.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        // leg2.setRotationPoint( 0.75F, 23.93978F, -0.1833103F );
        this.leg2.setRotationPoint(0.0F, 1.83978F, -0.1833103F + 2.89661E-07F);

        this.foot2 = new ModelRenderer(this, 9, 2);
        this.foot2.setTextureSize(16, 32);
        this.foot2.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
        // foot2.setRotationPoint( 0.75F, 23.6983F, -0.1186056F );
        this.foot2.setRotationPoint(0.0F, 1.5983F, -0.1186056F + 2.89661E-07F);

        this.leg1Top = new ModelRenderer(this, 11, 8);
        this.leg1Top.setTextureSize(16, 32);
        this.leg1Top.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.leg1Top.setRotationPoint(-0.75F, 22.1F, -2.89661E-07F);

        this.leg1 = new ModelRenderer(this, 9, 2);
        this.leg1.setTextureSize(16, 32);
        this.leg1.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
        // leg1.setRotationPoint( -0.75F, 23.93978F, -0.1833104F );
        this.leg1.setRotationPoint(0F, 1.83978F, -0.1833104F + 2.89661E-07F);

        this.foot1 = new ModelRenderer(this, 9, 2);
        this.foot1.setTextureSize(16, 32);
        this.foot1.addBox(-0.5F, -1F, -0.5F, 1, 1, 1);
        // foot1.setRotationPoint( -0.75F, 23.6983F, -0.1186057F );
        this.foot1.setRotationPoint(0F, 1.5983F, -0.1186057F + 2.89661E-07F);

        this.Neck.addChild(this.Head);
        this.Neck.addChild(this.BeakTop);

        this.leg1Top.addChild(this.leg1);
        this.leg1Top.addChild(this.foot1);

        this.leg2Top.addChild(this.leg2);
        this.leg2Top.addChild(this.foot2);

        this.tail1.addChild(this.tail2);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        this.Neck.render(scale);
        this.Body.render(scale);
        this.leg1Top.render(scale);
        this.leg2Top.render(scale);
        this.wing1.render(scale);
        this.wing2.render(scale);
        this.wing3.render(scale);
        this.wing4.render(scale);
        this.tail1.render(scale);

        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    }

    @Override
    public void setLivingAnimations(LivingEntity LivingEntityIn, float limbSwingAmount, float ageInTicks, float partialTickTime) {

        super.setLivingAnimations(LivingEntityIn, limbSwingAmount, ageInTicks, partialTickTime);
        if (limbSwingAmount > this.lastLimb)
            this.Neck.rotateAngleX = Math.abs(30F / (float) Math.PI * 1.4F * limbSwingAmount);
        else {
            // do nothing
        }

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
            Entity entityIn) {

        this.Body.rotateAngleX = -0.1257044F;
        this.tail1.rotateAngleX = 0.3593722F;
        this.tail2.rotateAngleX = 0.6340498F;
        this.wing1.rotateAngleX = 0.1139416F;
        this.wing2.rotateAngleX = 0.1139416F;
        this.wing3.rotateAngleX = 0.1139416F;
        this.wing4.rotateAngleX = 0.1139416F;
        this.Neck.rotateAngleX = -0.7853984F;
        this.Head.rotateAngleX = -0.0213736F;
        this.BeakTop.rotateAngleX = 0.7268012F;
        this.leg2Top.rotateAngleX = 0.1745329F;
        this.leg2.rotateAngleX = -0.2617995F;
        this.foot2.rotateAngleX = 1.570796F;
        this.leg1Top.rotateAngleX = 0.1745329F;
        this.leg1.rotateAngleX = -0.2617994F;
        this.foot1.rotateAngleX = 1.570796F;

        this.Neck.rotateAngleX = Math.abs(headPitch / (180F / (float) Math.PI) * 1.4F * limbSwingAmount);
        this.Neck.rotateAngleX = Math.abs(netHeadYaw / (180F / (float) Math.PI));
        this.Body.rotateAngleX = (float) Math.PI / 2F;
        this.leg1Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2Top.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        this.wing1.rotateAngleZ = ageInTicks;
        this.wing2.rotateAngleZ = -ageInTicks;
        this.wing3.rotateAngleZ = ageInTicks;
        this.wing4.rotateAngleZ = -ageInTicks;
    }

}

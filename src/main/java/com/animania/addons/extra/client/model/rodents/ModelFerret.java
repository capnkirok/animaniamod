package com.animania.addons.extra.client.model.rodents;

import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.addons.extra.common.entity.rodents.EntityFerretWhite;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class ModelFerret extends ModelBase
{
    private float        headRotationAngleX;
    public ModelRenderer Head = new ModelRenderer(this, 0, 0);
    ModelRenderer        Body;
    ModelRenderer        PawRF;
    ModelRenderer        PawLF;
    ModelRenderer        Neck;
    ModelRenderer        EarR;
    ModelRenderer        EarL;
    ModelRenderer        Body2;
    ModelRenderer        PawLB;
    ModelRenderer        PawRB;
    ModelRenderer        Tail;

    public ModelFerret() {
        this.Body = new ModelRenderer(this, 12, 10);
        this.Body.setTextureSize(64, 32);
        this.Body.addBox(-2.01F, -2.01F, -10F, 4, 4, 10);
        this.Body.setRotationPoint(0F, 18F, 0F);

        this.PawRF = new ModelRenderer(this, 0, 0);
        this.PawRF.setTextureSize(64, 32);
        this.PawRF.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.PawRF.setRotationPoint(-1F, 19.99997F, -9F);
        this.PawLF = new ModelRenderer(this, 0, 0);
        this.PawLF.setTextureSize(64, 32);
        this.PawLF.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.PawLF.setRotationPoint(1F, 19.99997F, -9F);

        this.Head = new ModelRenderer(this, 0, 10);
        this.Head.setTextureSize(64, 32);
        this.Head.addBox(-2.5F, -2F, -3F, 5, 4, 6);
        this.Head.setRotationPoint(0F, 14.35004F, -16F);

        this.EarR = new ModelRenderer(this, 12, 0);
        this.EarR.setTextureSize(64, 32);
        this.EarR.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        this.EarR.setRotationPoint(-2.5F, 12.35004F - 14.35004F, -14F + 16F);

        this.EarL = new ModelRenderer(this, 12, 0);
        this.EarL.setTextureSize(64, 32);
        this.EarL.addBox(-1F, -1F, -0.5F, 2, 2, 1);
        this.EarL.setRotationPoint(2.5F, 12.35004F - 14.35004F, -14F + 16F);

        this.Neck = new ModelRenderer(this, 30, 0);
        this.Neck.setTextureSize(64, 32);
        this.Neck.addBox(-2F, -4F, -7F, 4, 4, 7);
        this.Neck.setRotationPoint(0F, 19.99997F, -10F);

        this.Body2 = new ModelRenderer(this, 36, 15);
        this.Body2.setTextureSize(64, 32);
        this.Body2.addBox(-2F, -2F, 0F, 4, 4, 10);
        this.Body2.setRotationPoint(0F, 18F, 0F);

        this.PawLB = new ModelRenderer(this, 0, 0);
        this.PawLB.setTextureSize(64, 32);
        this.PawLB.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.PawLB.setRotationPoint(1F, 19.99997F, 9F);
        this.PawRB = new ModelRenderer(this, 0, 0);
        this.PawRB.setTextureSize(64, 32);
        this.PawRB.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.PawRB.setRotationPoint(-1F, 19.99997F, 9F);
        this.Tail = new ModelRenderer(this, 44, 3);
        this.Tail.setTextureSize(64, 32);
        this.Tail.addBox(-1F, -1F, 0F, 2, 2, 8);
        this.Tail.setRotationPoint(0F, 17.5F, 9.5F);

        this.Head.addChild(this.EarR);
        this.Head.addChild(this.EarL);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {

        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, p_78088_1_);

        this.Head.render(scale);
        this.Neck.render(scale);

        this.Body.render(scale);
        this.Body2.render(scale);
        this.PawLF.render(scale);
        this.PawRF.render(scale);
        this.PawLB.render(scale);
        this.PawRB.render(scale);
        this.Tail.render(scale);

    }

    @Override
    public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {

        super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

        if (LivingEntityIn instanceof EntityFerretGrey) {
            EntityFerretGrey ef = (EntityFerretGrey) LivingEntityIn;
            this.Head.rotationPointY = 14.3F + ef.getHeadAnchorPointY(partialTickTime) * 0F; // number
            this.headRotationAngleX = ef.getHeadAngleX(partialTickTime);

            if (ef.isSitting()) {
                this.Body2.setRotationPoint(0.0F, 18.0F, -1.0F);
                this.Body2.rotateAngleX = -0.326944F;
                this.Tail.setRotationPoint(0F, 20.0F, 8.5F);
            }
            else {
                this.Body2.setRotationPoint(0.0F, 18.0F, 0.0F);
                this.Body2.rotateAngleX = (float) Math.PI * 0F;
                this.Tail.setRotationPoint(0F, 17.5F, 9.5F);
            }

        }
        else if (LivingEntityIn instanceof EntityFerretWhite) {
        	EntityFerretWhite ef = (EntityFerretWhite) LivingEntityIn;
            this.Head.rotationPointY = 14.3F + ef.getHeadAnchorPointY(partialTickTime) * 0F; // number
            this.headRotationAngleX = ef.getHeadAngleX(partialTickTime);

            if (ef.isSitting()) {
                this.Body2.setRotationPoint(0.0F, 18.0F, -1.0F);
                this.Body2.rotateAngleX = -0.326944F;
                this.Tail.setRotationPoint(0F, 20.5F, 9.5F);
            }
            else {
                this.Body2.setRotationPoint(0.0F, 18.0F, 0.0F);
                this.Body2.rotateAngleX = (float) Math.PI * 0F;
                this.Tail.setRotationPoint(0F, 17.5F, 9.5F);
            }
        }

    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {

        this.Neck.rotateAngleX = -0.6108652F;
        this.Head.rotateAngleX = -3.584692E-08F;
        this.EarR.rotateAngleX = -3.584692E-08F;
        this.EarR.rotateAngleY = -6.726944E-16F;
        this.EarR.rotateAngleZ = -0.7853982F;

        this.EarL.rotateAngleX = -3.584692E-08F;
        this.EarL.rotateAngleY = 6.726944E-16F;
        this.EarL.rotateAngleZ = 0.7853982F;

        this.Tail.rotateAngleX = -0.526944F;

        float f6 = 180F / (float) Math.PI;
        this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.Head.rotateAngleX = this.headRotationAngleX;

        this.PawLF.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.PawRF.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.PawLB.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.PawRB.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
    }
}

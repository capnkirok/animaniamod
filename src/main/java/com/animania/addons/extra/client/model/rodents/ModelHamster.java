package com.animania.addons.extra.client.model.rodents;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.common.entity.rodents.EntityHamster;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelHamster extends ModelBase
{
    public ModelRenderer     hamsterHead;
    public ModelRenderer     hamsterBody;
    public ModelRenderer     hamsterLegBackRight;
    public ModelRenderer     hamsterLegBackLeft;
    public ModelRenderer     hamsterLegFrontRight;
    public ModelRenderer     hamsterLegFrontLeft;
    public ModelRenderer     hamsterNose;
    public ModelRenderer     hamsterEarRight;
    public ModelRenderer     hamsterEarLeft;
    public ModelRenderer     hamsterTail;
    public ModelRenderer     hamsterCheekRight[];
    public ModelRenderer     hamsterCheekLeft[];
    // public boolean isRiding;

    public ModelRendererBall ball;

    public ModelHamster() {
        float f = 0.5F;
        this.hamsterHead = new ModelRenderer(this, 0, 0);
        this.hamsterHead.addBox(-2.51F, -1.5F, -5F, 5, 5, 5, f);
        this.hamsterHead.setRotationPoint(0.0F, 16F, -2F);
        this.hamsterNose = new ModelRenderer(this, 0, 25);
        this.hamsterNose.addBox(-1.5F, 1.5F, -6F, 3, 2, 1, f);
        this.hamsterNose.setRotationPoint(0.0F, 16F, -2F);
        this.hamsterEarRight = new ModelRenderer(this, 10, 15);
        this.hamsterEarRight.addBox(-2.5F, -3F, -4.5F, 1, 1, 1, f);
        this.hamsterEarRight.setRotationPoint(0.0F, 16F, -2F);
        this.hamsterEarLeft = new ModelRenderer(this, 10, 18);
        this.hamsterEarLeft.addBox(1.5F, -3F, -4.5F, 1, 1, 1, f);
        this.hamsterEarLeft.setRotationPoint(0.0F, 16F, -2F);
        this.hamsterCheekRight = new ModelRenderer[5];
        for (int i = 0; i < this.hamsterCheekRight.length; i++) {
            this.hamsterCheekRight[i] = new ModelRenderer(this, 10, 21);
            this.hamsterCheekRight[i].addBox(-3.5F, 1.5F, -2.5F, 1, 1, 1, i * 0.4F);
            this.hamsterCheekRight[i].setRotationPoint(0.0F, 16F, -2F);
        }

        this.hamsterCheekLeft = new ModelRenderer[5];
        for (int j = 0; j < this.hamsterCheekLeft.length; j++) {
            this.hamsterCheekLeft[j] = new ModelRenderer(this, 10, 24);
            this.hamsterCheekLeft[j].addBox(2.5F, 1.5F, -2.5F, 1, 1, 1, j * 0.4F);
            this.hamsterCheekLeft[j].setRotationPoint(0.0F, 16F, -2F);
        }

        this.hamsterBody = new ModelRenderer(this, 28, 8);
        this.hamsterBody.addBox(-4.02F, -3F, -2F, 5, 8, 5, f);
        this.hamsterBody.setRotationPoint(0.0F, 19F, 0.0F);
        this.hamsterTail = new ModelRenderer(this, 10, 15);
        this.hamsterTail.addBox(-2F, 4F, -4F, 1, 1, 1, f);
        this.hamsterTail.setRotationPoint(0.0F, 15F, 2.0F);
        this.hamsterLegBackRight = new ModelRenderer(this, 0, 16);
        this.hamsterLegBackRight.addBox(-2F, 0.0F, -2.01F, 1, 2, 1, f);
        this.hamsterLegBackRight.setRotationPoint(-2F, 21F, 6F);
        this.hamsterLegBackLeft = new ModelRenderer(this, 0, 16);
        this.hamsterLegBackLeft.addBox(-2F, 0.0F, -2.01F, 1, 2, 1, f);
        this.hamsterLegBackLeft.setRotationPoint(2.0F, 21F, 6F);
        this.hamsterLegFrontRight = new ModelRenderer(this, 0, 16);
        this.hamsterLegFrontRight.addBox(-2.0F, 0.0F, -2F, 1, 2, 1, f);
        this.hamsterLegFrontRight.setRotationPoint(-2F, 21F, -0.5F);
        this.hamsterLegFrontLeft = new ModelRenderer(this, 0, 16);
        this.hamsterLegFrontLeft.addBox(-2.0F, 0.0F, -2F, 1, 2, 1, f);
        this.hamsterLegFrontLeft.setRotationPoint(2.0F, 21F, -0.5F);

        this.ball = new ModelRendererBall(this, 0, 0);
    }

    @Override
    public void setLivingAnimations(LivingEntity LivingEntity, float f, float f1, float f2) {
        EntityHamster entityhamster = (EntityHamster) LivingEntity;

        /*
         * if (entityhamster.setHamsterFlag) { entityhamster.entityId =
         * entityhamster.spawnEntityId;
         * entityhamster.mountEntity(entityhamster.spawnRidingEntity);
         * entityhamster.setHamsterTamed(entityhamster.spawnHamsterTamed);
         * entityhamster.setHamsterAngry(entityhamster.spawnHamsterAngry);
         * entityhamster.setHamsterStanding(entityhamster.spawnHamsterStanding);
         * entityhamster.setHamsterSitting(entityhamster.spawnHamsterSitting);
         * entityhamster.setHamsterFlag = false; }
         */

        // mod_Hamster.mDebug("setLivingAnimations
        // isHamsterSitting()="+entityhamster.isHamsterSitting());
        for (int i = 0; i < this.hamsterCheekRight.length; i++)
            if (i < entityhamster.getFoodStackCount())
                this.hamsterCheekRight[i].showModel = true;
            else
                this.hamsterCheekRight[i].showModel = false;

        for (int j = 0; j < this.hamsterCheekLeft.length; j++)
            if (j < entityhamster.getFoodStackCount())
                this.hamsterCheekLeft[j].showModel = true;
            else
                this.hamsterCheekLeft[j].showModel = false;

        float f9 = 0.0F;
        // float f9 = isChild ? 25.0F : 0.0F;

        if (entityhamster.isSitting() | entityhamster.isRiding()
        // | entityhamster.isRidingCreature()
        // | entityhamster.isRidingHamster()
        ) {
            float f3 = 0.0F;

            /*
             *
             * if (entityhamster.ridingEntity != null) { //mod_Hamster.
             * mDebug("setLivingAnimations entityhamster.ridingEntity.height="
             * +entityhamster.ridingEntity.height); if
             * (entityhamster.isRidingPlayer()) { //Player //mod_Hamster.
             * mDebug("setLivingAnimations isRidingEntity() entityhamster.ridingEntity.height="
             * +entityhamster.ridingEntity.height); } else { //mod_Hamster.
             * mDebug("setLivingAnimations entityhamster.ridingEntity.height="
             * +entityhamster.ridingEntity.height); if
             * (entityhamster.ridingEntity.height < 0.5F) f3 -= 0.0F; else if
             * (entityhamster.ridingEntity.height < 1.0F) f3 -= 5.0F; else if
             * (entityhamster.ridingEntity.height < 1.5F) f3 -= 12.5F; else if
             * (entityhamster.ridingEntity.height < 2.0F) f3 -= 20.0F; else if
             * (entityhamster.ridingEntity.height < 2.5F) f3 -= 45.0F; else if
             * (entityhamster.ridingEntity.height >= 2.5F) f3 -= 60.0F +
             * ((entityhamster.ridingEntity.height - 2.5F) * 5.0F); if
             * (entityhamster.ridingEntity.ridingEntity != null &&
             * !(entityhamster.ridingEntity.ridingEntity instanceof
             * PlayerEntitySP)) { //mod_Hamster.
             * mDebug("setLivingAnimations entityhamster.ridingEntity.ridingEntity.height="
             * +entityhamster.ridingEntity.ridingEntity.height); if
             * (entityhamster.ridingEntity.ridingEntity.height < 0.5F) f3 -=
             * 0.0F; else if (entityhamster.ridingEntity.ridingEntity.height <
             * 1.0F) f3 -= 5.0F; else if
             * (entityhamster.ridingEntity.ridingEntity.height < 1.5F) f3 -=
             * 12.5F; else if (entityhamster.ridingEntity.ridingEntity.height <
             * 2.0F) f3 -= 20.0F; else if
             * (entityhamster.ridingEntity.ridingEntity.height < 2.5F) f3 -=
             * 45.0F; else if (entityhamster.ridingEntity.ridingEntity.height >=
             * 2.5F) f3 -= 60.0F +
             * ((entityhamster.ridingEntity.ridingEntity.height - 2.5F) * 5.0F);
             * } } }
             */

            this.hamsterBody.rotateAngleX = 1.0F;
            this.hamsterHead.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            this.hamsterNose.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            this.hamsterEarRight.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            this.hamsterEarLeft.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            for (int k = 0; k < this.hamsterCheekRight.length; k++)
                this.hamsterCheekRight[k].setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);

            for (int l = 0; l < this.hamsterCheekLeft.length; l++)
                this.hamsterCheekLeft[l].setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);

            this.hamsterBody.setRotationPoint(0.0F, 19F + f3 + f9, 0.0F);
            this.hamsterLegBackRight.setRotationPoint(-3.5F, 24.5F + f3 + f9, 2.0F);
            this.hamsterLegBackLeft.setRotationPoint(2.5F, 24.5F + f3 + f9, 3.5F);
            this.hamsterLegBackRight.rotateAngleY = 0.8F;
            this.hamsterLegBackLeft.rotateAngleY = -0.8F;
            this.hamsterLegBackRight.rotateAngleX = -1.570796F;
            this.hamsterLegBackLeft.rotateAngleX = -1.570796F;
            this.hamsterLegFrontRight.rotateAngleX = 0.0F;
            this.hamsterLegFrontLeft.rotateAngleX = 0.0F;
            this.hamsterLegFrontRight.setRotationPoint(-2F, 21F + f3 + f9, -0.5F);
            this.hamsterLegFrontLeft.setRotationPoint(2.0F, 21F + f3 + f9, -0.5F);
            this.hamsterTail.setRotationPoint(0.0F, 17F + f3 + f9, 2.0F);
        }
        else if (entityhamster.isHamsterStanding()) {
            f9 = -0.4F;
            this.hamsterBody.rotateAngleX = 0.0F;
            this.hamsterHead.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            this.hamsterNose.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            this.hamsterEarRight.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            this.hamsterEarLeft.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            for (int i1 = 0; i1 < this.hamsterCheekRight.length; i1++)
                this.hamsterCheekRight[i1].setRotationPoint(-1.5F, 10F + f9, 4.5F);

            for (int j1 = 0; j1 < this.hamsterCheekLeft.length; j1++)
                this.hamsterCheekLeft[j1].setRotationPoint(-1.5F, 10F + f9, 4.5F);

            this.hamsterBody.setRotationPoint(0.0F, 15.5F + f9, 4.5F);
            this.hamsterBody.rotateAngleX = MathHelper.cos((float) Math.toRadians(80D));
            this.hamsterLegBackRight.setRotationPoint(-2F, 21F + f9, 6F);
            this.hamsterLegBackLeft.setRotationPoint(2.0F, 21F + f9, 6F);
            this.hamsterLegFrontRight.setRotationPoint(-2F, 15F + f9, 3F);
            this.hamsterLegFrontLeft.setRotationPoint(2.0F, 15F + f9, 3F);
            this.hamsterLegBackRight.rotateAngleY = 0.0F;
            this.hamsterLegBackLeft.rotateAngleY = 0.0F;
            this.hamsterLegBackRight.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            this.hamsterLegBackLeft.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            this.hamsterLegFrontRight.rotateAngleX = MathHelper.cos((float) Math.toRadians(150D));
            this.hamsterLegFrontLeft.rotateAngleX = MathHelper.cos((float) Math.toRadians(150D));
            this.hamsterLegFrontRight.rotateAngleY = MathHelper.sin((float) Math.toRadians(-10D));
            this.hamsterLegFrontLeft.rotateAngleY = MathHelper.sin((float) Math.toRadians(10D));
            this.hamsterTail.setRotationPoint(0.0F, 15F + f9, 2.0F);
        }
        else {
            this.hamsterBody.rotateAngleX = 1.570796F;
            this.hamsterHead.setRotationPoint(-1.5F, 16F + f9, -2F);
            this.hamsterNose.setRotationPoint(-1.5F, 16F + f9, -2F);
            this.hamsterEarRight.setRotationPoint(-1.5F, 16F + f9, -2F);
            this.hamsterEarLeft.setRotationPoint(-1.5F, 16F + f9, -2F);
            for (int k1 = 0; k1 < this.hamsterCheekRight.length; k1++)
                this.hamsterCheekRight[k1].setRotationPoint(-1.5F, 16F + f9, -2F);

            for (int l1 = 0; l1 < this.hamsterCheekLeft.length; l1++)
                this.hamsterCheekLeft[l1].setRotationPoint(-1.5F, 16F + f9, -2F);

            this.hamsterBody.setRotationPoint(0.0F, 19F + f9, 0.0F);
            this.hamsterLegBackRight.setRotationPoint(-2F, 21F + f9, 6F);
            this.hamsterLegBackLeft.setRotationPoint(2.0F, 21F + f9, 6F);
            this.hamsterLegBackRight.rotateAngleY = 0.0F;
            this.hamsterLegBackLeft.rotateAngleY = 0.0F;
            this.hamsterLegBackRight.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            this.hamsterLegBackLeft.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            this.hamsterLegFrontRight.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            this.hamsterLegFrontLeft.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            this.hamsterLegFrontRight.setRotationPoint(-2F, 21F + f9, -0.5F);
            this.hamsterLegFrontLeft.setRotationPoint(2.0F, 21F + f9, -0.5F);
            this.hamsterTail.setRotationPoint(0.0F, 15F + f9, 2.0F);
        }
        if (entityhamster.isTamed()) {
            float f3 = entityhamster.ticksExisted + f2;
            this.hamsterTail.rotateAngleZ = MathHelper.sin(f3 * 3.141593F * 0.05F) * MathHelper.sin(f3 * 3.141593F * 11F * 0.05F) * 0.15F * 3.141593F;
        }
        else
            this.hamsterTail.rotateAngleZ = 0.0F;
        float f4 = entityhamster.getInterestedAngle(f2);
        this.hamsterHead.rotateAngleZ = f4;
        this.hamsterNose.rotateAngleZ = f4;
        this.hamsterEarRight.rotateAngleZ = f4;
        this.hamsterEarLeft.rotateAngleZ = f4;
        for (int i2 = 0; i2 < this.hamsterCheekRight.length; i2++)
            this.hamsterCheekRight[i2].rotateAngleZ = f4;

        for (int j2 = 0; j2 < this.hamsterCheekLeft.length; j2++)
            this.hamsterCheekLeft[j2].rotateAngleZ = f4;

    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        if (this.isChild) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
        
        this.hamsterHead.renderWithRotation(par7);
        this.hamsterNose.renderWithRotation(par7);
        this.hamsterEarRight.renderWithRotation(par7);
        this.hamsterEarLeft.renderWithRotation(par7);
        for (int i = 0; i < this.hamsterCheekRight.length; i++)
            this.hamsterCheekRight[i].renderWithRotation(par7);

        for (int j = 0; j < this.hamsterCheekLeft.length; j++)
            this.hamsterCheekLeft[j].renderWithRotation(par7);

        this.hamsterBody.renderWithRotation(par7);
        this.hamsterTail.render(par7);
        this.hamsterLegBackRight.render(par7);
        this.hamsterLegBackLeft.render(par7);
        this.hamsterLegFrontRight.render(par7);
        this.hamsterLegFrontLeft.render(par7);
        if (this.isChild)
            GL11.glPopMatrix();

        GlStateManager.color(1,1,1);
        
        if (((EntityHamster) par1Entity).isInBall()) {
            this.ball.color = ((EntityHamster) par1Entity).getBallColor();
            this.ball.render(par7);
        }
  
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.ball.rotation = (int) par1;
        this.hamsterHead.rotateAngleX = par5 / 57.29578F;
        this.hamsterHead.rotateAngleY = par4 / 57.29578F;
        this.hamsterNose.rotateAngleX = this.hamsterHead.rotateAngleX;
        this.hamsterNose.rotateAngleY = this.hamsterHead.rotateAngleY;
        this.hamsterEarRight.rotateAngleX = this.hamsterHead.rotateAngleX;
        this.hamsterEarRight.rotateAngleY = this.hamsterHead.rotateAngleY;
        this.hamsterEarLeft.rotateAngleX = this.hamsterHead.rotateAngleX;
        this.hamsterEarLeft.rotateAngleY = this.hamsterHead.rotateAngleY;
        for (int i = 0; i < this.hamsterCheekRight.length; i++) {
            this.hamsterCheekRight[i].rotateAngleX = this.hamsterHead.rotateAngleX;
            this.hamsterCheekRight[i].rotateAngleY = this.hamsterHead.rotateAngleY;
        }

        for (int j = 0; j < this.hamsterCheekLeft.length; j++) {
            this.hamsterCheekLeft[j].rotateAngleX = this.hamsterHead.rotateAngleX;
            this.hamsterCheekLeft[j].rotateAngleY = this.hamsterHead.rotateAngleY;
        }

        this.hamsterTail.rotateAngleX = 1.570796F;
    }

}

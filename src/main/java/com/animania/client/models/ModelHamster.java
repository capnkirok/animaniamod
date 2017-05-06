package com.animania.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.animania.common.entities.rodents.EntityHamster;

public class ModelHamster extends ModelBase
{
    public ModelRenderer hamsterHead;
    public ModelRenderer hamsterBody;
    public ModelRenderer hamsterLegBackRight;
    public ModelRenderer hamsterLegBackLeft;
    public ModelRenderer hamsterLegFrontRight;
    public ModelRenderer hamsterLegFrontLeft;
    public ModelRenderer hamsterNose;
    public ModelRenderer hamsterEarRight;
    public ModelRenderer hamsterEarLeft;
    public ModelRenderer hamsterTail;
    public ModelRenderer hamsterCheekRight[];
    public ModelRenderer hamsterCheekLeft[];
    //public boolean isRiding;
    
    public ModelRendererBall ball;

    public ModelHamster()
    {
        float f = 0.5F;
        hamsterHead = new ModelRenderer(this, 0, 0);
        hamsterHead.addBox(-2.51F, -1.5F, -5F, 5, 5, 5, f);
        hamsterHead.setRotationPoint(0.0F, 16F, -2F);
        hamsterNose = new ModelRenderer(this, 0, 25);
        hamsterNose.addBox(-1.5F, 1.5F, -6F, 3, 2, 1, f);
        hamsterNose.setRotationPoint(0.0F, 16F, -2F);
        hamsterEarRight = new ModelRenderer(this, 10, 15);
        hamsterEarRight.addBox(-2.5F, -3F, -4.5F, 1, 1, 1, f);
        hamsterEarRight.setRotationPoint(0.0F, 16F, -2F);
        hamsterEarLeft = new ModelRenderer(this, 10, 18);
        hamsterEarLeft.addBox(1.5F, -3F, -4.5F, 1, 1, 1, f);
        hamsterEarLeft.setRotationPoint(0.0F, 16F, -2F);
        hamsterCheekRight = new ModelRenderer[5];
        for(int i = 0; i < hamsterCheekRight.length; i++)
        {
            hamsterCheekRight[i] = new ModelRenderer(this, 10, 21);
            hamsterCheekRight[i].addBox(-3.5F, 1.5F, -2.5F, 1, 1, 1, (float)i * 0.4F);
            hamsterCheekRight[i].setRotationPoint(0.0F, 16F, -2F);
        }

        hamsterCheekLeft = new ModelRenderer[5];
        for(int j = 0; j < hamsterCheekLeft.length; j++)
        {
            hamsterCheekLeft[j] = new ModelRenderer(this, 10, 24);
            hamsterCheekLeft[j].addBox(2.5F, 1.5F, -2.5F, 1, 1, 1, (float)j * 0.4F);
            hamsterCheekLeft[j].setRotationPoint(0.0F, 16F, -2F);
        }

        hamsterBody = new ModelRenderer(this, 28, 8);
        hamsterBody.addBox(-4.02F, -3F, -2F, 5, 8, 5, f);
        hamsterBody.setRotationPoint(0.0F, 19F, 0.0F);
        hamsterTail = new ModelRenderer(this, 10, 15);
        hamsterTail.addBox(-2F, 4F, -4F, 1, 1, 1, f);
        hamsterTail.setRotationPoint(0.0F, 15F, 2.0F);
        hamsterLegBackRight = new ModelRenderer(this, 0, 16);
        hamsterLegBackRight.addBox(-2F, 0.0F, -2.01F, 1, 2, 1, f);
        hamsterLegBackRight.setRotationPoint(-2F, 21F, 6F);
        hamsterLegBackLeft = new ModelRenderer(this, 0, 16);
        hamsterLegBackLeft.addBox(-2F, 0.0F, -2.01F, 1, 2, 1, f);
        hamsterLegBackLeft.setRotationPoint(2.0F, 21F, 6F);
        hamsterLegFrontRight = new ModelRenderer(this, 0, 16);
        hamsterLegFrontRight.addBox(-2.0F, 0.0F, -2F, 1, 2, 1, f);
        hamsterLegFrontRight.setRotationPoint(-2F, 21F, -0.5F);
        hamsterLegFrontLeft = new ModelRenderer(this, 0, 16);
        hamsterLegFrontLeft.addBox(-2.0F, 0.0F, -2F, 1, 2, 1, f);
        hamsterLegFrontLeft.setRotationPoint(2.0F, 21F, -0.5F);
        
        ball = new ModelRendererBall(this, 0, 0);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2)
    {
        EntityHamster entityhamster = (EntityHamster)entityliving;

        /*
        if (entityhamster.setHamsterFlag) {
        	entityhamster.entityId = entityhamster.spawnEntityId;
        	entityhamster.mountEntity(entityhamster.spawnRidingEntity);
        	entityhamster.setHamsterTamed(entityhamster.spawnHamsterTamed);
        	entityhamster.setHamsterAngry(entityhamster.spawnHamsterAngry);
        	entityhamster.setHamsterStanding(entityhamster.spawnHamsterStanding);
        	entityhamster.setHamsterSitting(entityhamster.spawnHamsterSitting);
        	entityhamster.setHamsterFlag = false;
        }
        */

        //mod_Hamster.mDebug("setLivingAnimations isHamsterSitting()="+entityhamster.isHamsterSitting());
        for(int i = 0; i < hamsterCheekRight.length; i++)
        {
            if(i < entityhamster.getFoodStackCount())
            {
                hamsterCheekRight[i].showModel = true;
            } else
            {
                hamsterCheekRight[i].showModel = false;
            }
        }

        for(int j = 0; j < hamsterCheekLeft.length; j++)
        {
            if(j < entityhamster.getFoodStackCount())
            {
                hamsterCheekLeft[j].showModel = true;
            } else
            {
                hamsterCheekLeft[j].showModel = false;
            }
        }

        float f9 = 0.0F;
        //float f9 = isChild ? 25.0F : 0.0F;

        if(entityhamster.isSitting()
        		| entityhamster.isRiding()
        		//| entityhamster.isRidingCreature()
        		//| entityhamster.isRidingHamster()
        		) {
        	float f3 = 0.0F;

        	/*
        	
        	if (entityhamster.ridingEntity != null) {
        		//mod_Hamster.mDebug("setLivingAnimations entityhamster.ridingEntity.height="+entityhamster.ridingEntity.height);
        		if (entityhamster.isRidingPlayer()) {
        			//Player
        			//mod_Hamster.mDebug("setLivingAnimations isRidingEntity() entityhamster.ridingEntity.height="+entityhamster.ridingEntity.height);
        		} else {
        			//mod_Hamster.mDebug("setLivingAnimations entityhamster.ridingEntity.height="+entityhamster.ridingEntity.height);
        			if (entityhamster.ridingEntity.height < 0.5F) f3 -= 0.0F;
        			else if (entityhamster.ridingEntity.height < 1.0F) f3 -= 5.0F;
        			else if (entityhamster.ridingEntity.height < 1.5F) f3 -= 12.5F;
        			else if (entityhamster.ridingEntity.height < 2.0F) f3 -= 20.0F;
        			else if (entityhamster.ridingEntity.height < 2.5F) f3 -= 45.0F;
        			else if (entityhamster.ridingEntity.height >= 2.5F) f3 -= 60.0F + ((entityhamster.ridingEntity.height - 2.5F) * 5.0F);
        			if (entityhamster.ridingEntity.ridingEntity != null
        					&& !(entityhamster.ridingEntity.ridingEntity instanceof EntityPlayerSP)) {
        				//mod_Hamster.mDebug("setLivingAnimations entityhamster.ridingEntity.ridingEntity.height="+entityhamster.ridingEntity.ridingEntity.height);
        				if (entityhamster.ridingEntity.ridingEntity.height < 0.5F) f3 -= 0.0F;
        				else if (entityhamster.ridingEntity.ridingEntity.height < 1.0F) f3 -= 5.0F;
        				else if (entityhamster.ridingEntity.ridingEntity.height < 1.5F) f3 -= 12.5F;
        				else if (entityhamster.ridingEntity.ridingEntity.height < 2.0F) f3 -= 20.0F;
        				else if (entityhamster.ridingEntity.ridingEntity.height < 2.5F) f3 -= 45.0F;
        				else if (entityhamster.ridingEntity.ridingEntity.height >= 2.5F) f3 -= 60.0F + ((entityhamster.ridingEntity.ridingEntity.height - 2.5F) * 5.0F);
        			}
        		}
        	}
        	*/

            hamsterBody.rotateAngleX = 1.0F;
            hamsterHead.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            hamsterNose.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            hamsterEarRight.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            hamsterEarLeft.setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            for(int k = 0; k < hamsterCheekRight.length; k++)
            {
                hamsterCheekRight[k].setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            }

            for(int l = 0; l < hamsterCheekLeft.length; l++)
            {
                hamsterCheekLeft[l].setRotationPoint(-1.5F, 16F + f3 + f9, -1.5F);
            }

            hamsterBody.setRotationPoint(0.0F, 19F + f3 + f9, 0.0F);
            hamsterLegBackRight.setRotationPoint(-3.5F, 24.5F + f3 + f9, 2.0F);
            hamsterLegBackLeft.setRotationPoint(2.5F, 24.5F + f3 + f9, 3.5F);
            hamsterLegBackRight.rotateAngleY = 0.8F;
            hamsterLegBackLeft.rotateAngleY = -0.8F;
            hamsterLegBackRight.rotateAngleX = -1.570796F;
            hamsterLegBackLeft.rotateAngleX = -1.570796F;
            hamsterLegFrontRight.rotateAngleX = 0.0F;
            hamsterLegFrontLeft.rotateAngleX = 0.0F;
            hamsterLegFrontRight.setRotationPoint(-2F, 21F + f3 + f9, -0.5F);
            hamsterLegFrontLeft.setRotationPoint(2.0F, 21F + f3 + f9, -0.5F);
            hamsterTail.setRotationPoint(0.0F, 17F + f3 + f9, 2.0F);
        } else
        if(entityhamster.isHamsterStanding())
        {
            f9 = -0.4F;
        	hamsterBody.rotateAngleX = 0.0F;
            hamsterHead.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            hamsterNose.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            hamsterEarRight.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            hamsterEarLeft.setRotationPoint(-1.5F, 10F + f9, 4.5F);
            for(int i1 = 0; i1 < hamsterCheekRight.length; i1++)
            {
                hamsterCheekRight[i1].setRotationPoint(-1.5F, 10F + f9, 4.5F);
            }

            for(int j1 = 0; j1 < hamsterCheekLeft.length; j1++)
            {
                hamsterCheekLeft[j1].setRotationPoint(-1.5F, 10F + f9, 4.5F);
            }

            hamsterBody.setRotationPoint(0.0F, 15.5F + f9, 4.5F);
            hamsterBody.rotateAngleX = MathHelper.cos((float)Math.toRadians(80D));
            hamsterLegBackRight.setRotationPoint(-2F, 21F + f9, 6F);
            hamsterLegBackLeft.setRotationPoint(2.0F, 21F + f9, 6F);
            hamsterLegFrontRight.setRotationPoint(-2F, 15F + f9, 3F);
            hamsterLegFrontLeft.setRotationPoint(2.0F, 15F + f9, 3F);
            hamsterLegBackRight.rotateAngleY = 0.0F;
            hamsterLegBackLeft.rotateAngleY = 0.0F;
            hamsterLegBackRight.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            hamsterLegBackLeft.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            hamsterLegFrontRight.rotateAngleX = MathHelper.cos((float)Math.toRadians(150D));
            hamsterLegFrontLeft.rotateAngleX = MathHelper.cos((float)Math.toRadians(150D));
            hamsterLegFrontRight.rotateAngleY = MathHelper.sin((float)Math.toRadians(-10D));
            hamsterLegFrontLeft.rotateAngleY = MathHelper.sin((float)Math.toRadians(10D));
            hamsterTail.setRotationPoint(0.0F, 15F + f9, 2.0F);
        } else
        {
            hamsterBody.rotateAngleX = 1.570796F;
            hamsterHead.setRotationPoint(-1.5F, 16F + f9, -2F);
            hamsterNose.setRotationPoint(-1.5F, 16F + f9, -2F);
            hamsterEarRight.setRotationPoint(-1.5F, 16F + f9, -2F);
            hamsterEarLeft.setRotationPoint(-1.5F, 16F + f9, -2F);
            for(int k1 = 0; k1 < hamsterCheekRight.length; k1++)
            {
                hamsterCheekRight[k1].setRotationPoint(-1.5F, 16F + f9, -2F);
            }

            for(int l1 = 0; l1 < hamsterCheekLeft.length; l1++)
            {
                hamsterCheekLeft[l1].setRotationPoint(-1.5F, 16F + f9, -2F);
            }

            hamsterBody.setRotationPoint(0.0F, 19F + f9, 0.0F);
            hamsterLegBackRight.setRotationPoint(-2F, 21F + f9, 6F);
            hamsterLegBackLeft.setRotationPoint(2.0F, 21F + f9, 6F);
            hamsterLegBackRight.rotateAngleY = 0.0F;
            hamsterLegBackLeft.rotateAngleY = 0.0F;
            hamsterLegBackRight.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            hamsterLegBackLeft.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            hamsterLegFrontRight.rotateAngleX = MathHelper.cos(f * 1.5F + 3.141593F) * 1.4F * f1;
            hamsterLegFrontLeft.rotateAngleX = MathHelper.cos(f * 1.5F) * 1.4F * f1;
            hamsterLegFrontRight.setRotationPoint(-2F, 21F + f9, -0.5F);
            hamsterLegFrontLeft.setRotationPoint(2.0F, 21F + f9, -0.5F);
            hamsterTail.setRotationPoint(0.0F, 15F + f9, 2.0F);
        }
        if(entityhamster.getIsTamed())
        {
            float f3 = (float)entityhamster.ticksExisted + f2;
            hamsterTail.rotateAngleZ = MathHelper.sin(f3 * 3.141593F * 0.05F) * MathHelper.sin(f3 * 3.141593F * 11F * 0.05F) * 0.15F * 3.141593F;
        } else
        {
            hamsterTail.rotateAngleZ = 0.0F;
        }
        float f4 = entityhamster.getInterestedAngle(f2);
        hamsterHead.rotateAngleZ = f4;
        hamsterNose.rotateAngleZ = f4;
        hamsterEarRight.rotateAngleZ = f4;
        hamsterEarLeft.rotateAngleZ = f4;
        for(int i2 = 0; i2 < hamsterCheekRight.length; i2++)
        {
            hamsterCheekRight[i2].rotateAngleZ = f4;
        }

        for(int j2 = 0; j2 < hamsterCheekLeft.length; j2++)
        {
            hamsterCheekLeft[j2].rotateAngleZ = f4;
        }

    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        if (isChild)
        {
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
        hamsterHead.renderWithRotation(par7);
        hamsterNose.renderWithRotation(par7);
        hamsterEarRight.renderWithRotation(par7);
        hamsterEarLeft.renderWithRotation(par7);
        for(int i = 0; i < hamsterCheekRight.length; i++)
        {
            hamsterCheekRight[i].renderWithRotation(par7);
        }

        for(int j = 0; j < hamsterCheekLeft.length; j++)
        {
            hamsterCheekLeft[j].renderWithRotation(par7);
        }

        hamsterBody.renderWithRotation(par7);
        hamsterTail.render(par7);
        hamsterLegBackRight.render(par7);
        hamsterLegBackLeft.render(par7);
        hamsterLegFrontRight.render(par7);
        hamsterLegFrontLeft.render(par7);
        if (isChild)
        {
            GL11.glPopMatrix();
        }
        
        if(((EntityHamster)par1Entity).isInBall()){
        	ball.color = ((EntityHamster)par1Entity).getBallColor();
        	ball.render(par7);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        ball.rotation = (int) par1;
        hamsterHead.rotateAngleX = par5 / 57.29578F;
        hamsterHead.rotateAngleY = par4 / 57.29578F;
        hamsterNose.rotateAngleX = hamsterHead.rotateAngleX;
        hamsterNose.rotateAngleY = hamsterHead.rotateAngleY;
        hamsterEarRight.rotateAngleX = hamsterHead.rotateAngleX;
        hamsterEarRight.rotateAngleY = hamsterHead.rotateAngleY;
        hamsterEarLeft.rotateAngleX = hamsterHead.rotateAngleX;
        hamsterEarLeft.rotateAngleY = hamsterHead.rotateAngleY;
        for(int i = 0; i < hamsterCheekRight.length; i++)
        {
            hamsterCheekRight[i].rotateAngleX = hamsterHead.rotateAngleX;
            hamsterCheekRight[i].rotateAngleY = hamsterHead.rotateAngleY;
        }

        for(int j = 0; j < hamsterCheekLeft.length; j++)
        {
            hamsterCheekLeft[j].rotateAngleX = hamsterHead.rotateAngleX;
            hamsterCheekLeft[j].rotateAngleY = hamsterHead.rotateAngleY;
        }

        hamsterTail.rotateAngleX = 1.570796F;
    }

}


package com.animania.common.entities.pigs.ai;

import com.animania.common.entities.pigs.EntityAnimaniaPig;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookIdlePig extends EntityAIBase
{
    private final EntityAnimaniaPig idleEntity;
    private double lookX;
    private double lookZ;
    private int idleTime;

    public EntityAILookIdlePig(EntityAnimaniaPig entitylivingIn)
    {
        this.idleEntity = entitylivingIn;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
       if (this.idleEntity.getMuddy()) {
    	   return false;
       }
    	
    	return this.idleEntity.getRNG().nextFloat() < 0.02F;
    }

    public boolean shouldContinueExecuting()
    {
        return this.idleTime >= 0;
    }

    public void startExecuting()
    {
        double d0 = (Math.PI * 2D) * this.idleEntity.getRNG().nextDouble();
        this.lookX = Math.cos(d0);
        this.lookZ = Math.sin(d0);
        this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
    }

    public void updateTask()
    {
        --this.idleTime;
        this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + (double)this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, (float)this.idleEntity.getHorizontalFaceSpeed(), (float)this.idleEntity.getVerticalFaceSpeed());
    }
}
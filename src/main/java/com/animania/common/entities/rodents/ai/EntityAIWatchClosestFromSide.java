package com.animania.common.entities.rodents.ai;

import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIWatchClosestFromSide extends EntityAIBase
{
    protected EntityLiving            theWatcher;
    protected Entity                  closestEntity;
    protected float                   maxDistanceForPlayer;
    private int                       lookTime;
    private final float               chance;
    protected Class<? extends Entity> watchedClass;

    public EntityAIWatchClosestFromSide(EntityLiving entitylivingIn, Class<? extends Entity> watchTargetClass, float maxDistance) {
        this.theWatcher = entitylivingIn;
        this.watchedClass = watchTargetClass;
        this.maxDistanceForPlayer = maxDistance;
        this.chance = 0.02F;
        this.setMutexBits(2);
    }

    public EntityAIWatchClosestFromSide(EntityLiving entitylivingIn, Class<? extends Entity> watchTargetClass, float maxDistance, float chanceIn) {
        this.theWatcher = entitylivingIn;
        this.watchedClass = watchTargetClass;
        this.maxDistanceForPlayer = maxDistance;
        this.chance = chanceIn;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute() {

        if (this.theWatcher.isRiding())
            return false;

        boolean isSleeping = false;
		if (this.theWatcher instanceof EntityHedgehogBase) {
			EntityHedgehogBase entityChk = (EntityHedgehogBase) this.theWatcher;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}
		
		if (this.theWatcher instanceof EntityFerretBase) {
			EntityFerretBase entityChk = (EntityFerretBase) this.theWatcher;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}
		
		if (this.theWatcher instanceof EntityHamster) {
			EntityHamster entityChk = (EntityHamster) this.theWatcher;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}
		
		if (this.theWatcher instanceof EntityAnimaniaRabbit) {
			EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) this.theWatcher;
			if (entityChk.getSleeping()) {
				isSleeping = true;
			}
		}
		
		if (isSleeping) {
			return false;
		}
        
        if (this.theWatcher.getRNG().nextFloat() >= this.chance)
            return false;
        else {
            if (this.theWatcher.getAttackTarget() != null)
                this.closestEntity = this.theWatcher.getAttackTarget();

            if (this.watchedClass == EntityPlayer.class)
                this.closestEntity = this.theWatcher.world.getClosestPlayerToEntity(this.theWatcher, this.maxDistanceForPlayer);
            else
                this.closestEntity = this.theWatcher.world.findNearestEntityWithinAABB(this.watchedClass,
                        this.theWatcher.getEntityBoundingBox().expand(this.maxDistanceForPlayer, 3.0D, this.maxDistanceForPlayer), this.theWatcher);

            return this.closestEntity != null;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.closestEntity.isEntityAlive() ? false
                : this.theWatcher.getDistanceSq(this.closestEntity) > this.maxDistanceForPlayer * this.maxDistanceForPlayer ? false
                        : this.lookTime > 0;
    }

    @Override
    public void startExecuting() {
        this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
    }

    @Override
    public void resetTask() {
        this.closestEntity = null;
    }

    @Override
    public void updateTask() {

        this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.posX + 10F, this.closestEntity.posY + this.closestEntity.getEyeHeight() + 5F,
                this.closestEntity.posZ, this.theWatcher.getHorizontalFaceSpeed(), this.theWatcher.getVerticalFaceSpeed());
        --this.lookTime;
    }
}
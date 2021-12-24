package com.animania.addons.farm.common.entity.chickens.ai;

import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class WatchClosestFromSideGoal extends Goal
{
    protected LivingEntity            theWatcher;
    protected Entity                  closestEntity;
    protected float                   maxDistanceForPlayer;
    private int                       lookTime;
    private final float               chance;
    protected Class<? extends Entity> watchedClass;

    public WatchClosestFromSideGoal(LivingEntity LivingEntityIn, Class<? extends Entity> watchTargetClass, float maxDistance) {
        this.theWatcher = LivingEntityIn;
        this.watchedClass = watchTargetClass;
        this.maxDistanceForPlayer = maxDistance;
        this.chance = 0.02F;
        this.setMutexBits(2);
    }

    public WatchClosestFromSideGoal(LivingEntity LivingEntityIn, Class<? extends Entity> watchTargetClass, float maxDistance, float chanceIn) {
        this.theWatcher = LivingEntityIn;
        this.watchedClass = watchTargetClass;
        this.maxDistanceForPlayer = maxDistance;
        this.chance = chanceIn;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute() {
		if (!this.theWatcher.level.isDay() && this.theWatcher instanceof EntityAnimaniaChicken && ((EntityAnimaniaChicken) this.theWatcher).getSleeping())
			return false;
    	
        if (this.theWatcher.getRandom().nextFloat() >= this.chance)
            return false;
        else {
            if (this.theWatcher.getAttackTarget() != null)
                this.closestEntity = this.theWatcher.getAttackTarget();

            if (this.watchedClass == PlayerEntity.class)
                this.closestEntity = this.theWatcher.level.getClosestPlayerToEntity(this.theWatcher, this.maxDistanceForPlayer);
            else
                this.closestEntity = this.theWatcher.level.findNearestEntityWithinAABB(this.watchedClass,
                        this.theWatcher.getEntityBoundingBox().expand(this.maxDistanceForPlayer, 3.0D, this.maxDistanceForPlayer), this.theWatcher);

            return this.closestEntity != null;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.closestEntity.isAlive() ? false
                : this.theWatcher.getDistanceSq(this.closestEntity) > this.maxDistanceForPlayer * this.maxDistanceForPlayer ? false
                        : this.lookTime > 0;
    }

    @Override
    public void startExecuting() {
        this.lookTime = 40 + this.theWatcher.getRandom().nextInt(40);
    }

    @Override
    public void resetTask() {
        this.closestEntity = null;
    }

    @Override
    public void updateTask() {

        this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.getX(), this.closestEntity.getY() + this.closestEntity.getEyeHeight() + 5F,
                this.closestEntity.getZ(), this.theWatcher.getHorizontalFaceSpeed(), this.theWatcher.getVerticalFaceSpeed());
        --this.lookTime;
    }
}
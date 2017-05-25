package com.animania.common.entities.cows.ai;

import java.util.Random;

import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowParentCows extends EntityAIBase
{
    /** The child that is following its parent. */
    EntityAnimal childAnimal;
    EntityAnimal parentAnimal;
    double       moveSpeed;
    private int  delayCounter;
    Random       rand = new Random();

    public EntityAIFollowParentCows(EntityAnimal animal, double speed) {
        this.childAnimal = animal;
        this.moveSpeed = speed;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */

    @Override
    public boolean shouldExecute() {

        this.delayCounter++;
        if (this.delayCounter > 60)
            if (this.childAnimal instanceof EntityCalfHolstein && this.rand.nextInt(20) != 0) {
                EntityCalfHolstein ec = (EntityCalfHolstein) this.childAnimal;
                if (ec.getParentUniqueId() == null)
                    return false;
                else {

                    int esize = this.childAnimal.world.loadedEntityList.size();

                    for (int k = 0; k <= esize - 1; k++) {

                        Entity entity = this.childAnimal.world.loadedEntityList.get(k);

                        double xt = entity.posX;
                        double yt = entity.posY;
                        double zt = entity.posZ;
                        int x1 = MathHelper.floor(this.childAnimal.posX);
                        int y1 = MathHelper.floor(this.childAnimal.posY);
                        int z1 = MathHelper.floor(this.childAnimal.posZ);
                        double x2 = Math.abs(xt - x1);
                        double y2 = Math.abs(yt - y1);
                        double z2 = Math.abs(zt - z1);

                        if (entity != null
                                && entity.getPersistentID().toString()
                                        .equalsIgnoreCase(((EntityCalfHolstein) this.childAnimal).getParentUniqueId().toString())
                                && x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3) {
                            this.parentAnimal = (EntityAnimal) entity;
                            return true;
                        }

                    }

                }

            }
            else if (this.childAnimal instanceof EntityCalfAngus && this.rand.nextInt(20) != 0) {
                EntityCalfAngus ec = (EntityCalfAngus) this.childAnimal;
                if (ec.getParentUniqueId() == null)
                    return false;
                else {

                    int esize = this.childAnimal.world.loadedEntityList.size();

                    for (int k = 0; k <= esize - 1; k++) {

                        Entity entity = this.childAnimal.world.loadedEntityList.get(k);

                        double xt = entity.posX;
                        double yt = entity.posY;
                        double zt = entity.posZ;
                        int x1 = MathHelper.floor(this.childAnimal.posX);
                        int y1 = MathHelper.floor(this.childAnimal.posY);
                        int z1 = MathHelper.floor(this.childAnimal.posZ);
                        double x2 = Math.abs(xt - x1);
                        double y2 = Math.abs(yt - y1);
                        double z2 = Math.abs(zt - z1);

                        if (entity != null
                                && entity.getPersistentID().toString()
                                        .equalsIgnoreCase(((EntityCalfAngus) this.childAnimal).getParentUniqueId().toString())
                                && x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3) {
                            this.parentAnimal = (EntityAnimal) entity;
                            return true;

                        }

                    }

                }
            }
            else if (this.childAnimal instanceof EntityCalfFriesian && this.rand.nextInt(20) != 0) {
                EntityCalfFriesian ec = (EntityCalfFriesian) this.childAnimal;
                if (ec.getParentUniqueId() == null)
                    return false;
                else {

                    int esize = this.childAnimal.world.loadedEntityList.size();

                    for (int k = 0; k <= esize - 1; k++) {

                        Entity entity = this.childAnimal.world.loadedEntityList.get(k);

                        double xt = entity.posX;
                        double yt = entity.posY;
                        double zt = entity.posZ;
                        int x1 = MathHelper.floor(this.childAnimal.posX);
                        int y1 = MathHelper.floor(this.childAnimal.posY);
                        int z1 = MathHelper.floor(this.childAnimal.posZ);
                        double x2 = Math.abs(xt - x1);
                        double y2 = Math.abs(yt - y1);
                        double z2 = Math.abs(zt - z1);

                        if (entity != null
                                && entity.getPersistentID().toString()
                                        .equalsIgnoreCase(((EntityCalfFriesian) this.childAnimal).getParentUniqueId().toString())
                                && x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3) {
                            this.parentAnimal = (EntityAnimal) entity;
                            return true;

                        }

                    }

                }
            }
            else if (this.childAnimal instanceof EntityCalfHereford && this.rand.nextInt(20) != 0) {
                EntityCalfHereford ec = (EntityCalfHereford) this.childAnimal;
                if (ec.getParentUniqueId() == null)
                    return false;
                else {

                    int esize = this.childAnimal.world.loadedEntityList.size();

                    for (int k = 0; k <= esize - 1; k++) {

                        Entity entity = this.childAnimal.world.loadedEntityList.get(k);

                        double xt = entity.posX;
                        double yt = entity.posY;
                        double zt = entity.posZ;
                        int x1 = MathHelper.floor(this.childAnimal.posX);
                        int y1 = MathHelper.floor(this.childAnimal.posY);
                        int z1 = MathHelper.floor(this.childAnimal.posZ);
                        double x2 = Math.abs(xt - x1);
                        double y2 = Math.abs(yt - y1);
                        double z2 = Math.abs(zt - z1);

                        if (entity != null
                                && entity.getPersistentID().toString()
                                        .equalsIgnoreCase(((EntityCalfHereford) this.childAnimal).getParentUniqueId().toString())
                                && x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3) {
                            this.parentAnimal = (EntityAnimal) entity;
                            return true;

                        }

                    }

                }

            }
            else if (this.childAnimal instanceof EntityCalfLonghorn && this.rand.nextInt(20) != 0) {
                EntityCalfLonghorn ec = (EntityCalfLonghorn) this.childAnimal;
                if (ec.getParentUniqueId() == null)
                    return false;
                else {

                    int esize = this.childAnimal.world.loadedEntityList.size();

                    for (int k = 0; k <= esize - 1; k++) {

                        Entity entity = this.childAnimal.world.loadedEntityList.get(k);

                        double xt = entity.posX;
                        double yt = entity.posY;
                        double zt = entity.posZ;
                        int x1 = MathHelper.floor(this.childAnimal.posX);
                        int y1 = MathHelper.floor(this.childAnimal.posY);
                        int z1 = MathHelper.floor(this.childAnimal.posZ);
                        double x2 = Math.abs(xt - x1);
                        double y2 = Math.abs(yt - y1);
                        double z2 = Math.abs(zt - z1);

                        if (entity != null
                                && entity.getPersistentID().toString()
                                        .equalsIgnoreCase(((EntityCalfLonghorn) this.childAnimal).getParentUniqueId().toString())
                                && x2 <= 20 && y2 <= 8 && z2 <= 20 && x2 >= 3 && z2 >= 3) {
                            this.parentAnimal = (EntityAnimal) entity;
                            return true;

                        }

                    }

                }
            }

        return false;

    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        if (!this.parentAnimal.isEntityAlive())
            return false;
        else {
            double d0 = this.childAnimal.getDistanceSqToEntity(this.parentAnimal);
            return d0 >= 9.0D && d0 <= 256.0D;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.delayCounter = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.parentAnimal = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 40;
            this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
        }
    }
}
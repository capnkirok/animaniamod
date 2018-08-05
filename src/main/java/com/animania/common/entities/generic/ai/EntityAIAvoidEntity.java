package com.animania.common.entities.generic.ai;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

public class EntityAIAvoidEntity<T extends Entity> extends EntityAIBase
{
    private final Predicate<Entity> canBeSeenSelector;
    /** The entity we are attached to */
    protected EntityCreature entity;
    private double farSpeed;
    private double nearSpeed;
    protected T closestLivingEntity;
    private final float avoidDistance;
    /** The PathEntity of our entity */
    private Path path;
    /** The PathNavigate of our entity */
    private final PathNavigate navigation;
    /** Class of entity this behavior seeks to avoid */
    private final Class<T> classToAvoid;
    private final Predicate <? super T > avoidTargetSelector;

    public EntityAIAvoidEntity(EntityCreature entityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
    }

    public EntityAIAvoidEntity(EntityCreature entityIn, Class<T> classToAvoidIn, Predicate <? super T > avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this.canBeSeenSelector = new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity p_apply_1_)
            {
               // return p_apply_1_.isEntityAlive() && EntityAIAvoidEntity.this.entity.getEntitySenses().canSee(p_apply_1_) && !EntityAIAvoidEntity.this.entity.isOnSameTeam(p_apply_1_);
                return true;
            }
        };
        this.entity = entityIn;
        this.classToAvoid = classToAvoidIn;
        this.avoidTargetSelector = avoidTargetSelectorIn;
        this.avoidDistance = avoidDistanceIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.navigation = entityIn.getNavigator();
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
       
    	
    	
    	List<T> list = this.entity.world.<T>getEntitiesWithinAABB(this.classToAvoid, this.entity.getEntityBoundingBox().grow((double)this.avoidDistance, 10.0D, (double)this.avoidDistance), Predicates.and(EntitySelectors.CAN_AI_TARGET, this.canBeSeenSelector, this.avoidTargetSelector));

        if (list.isEmpty())
        {
            return false;
        }
        else
        {
           
        	this.closestLivingEntity = list.get(0);
            
           
            if (this.entity instanceof EntityAnimaniaGoat) {
    			EntityAnimaniaGoat entityChk = (EntityAnimaniaGoat) this.entity;
    			if (entityChk.getSleeping()) {
    				entityChk.setSleeping(false);
    			}
    		}
            
            if (this.entity instanceof EntityAnimaniaSheep) {
    			EntityAnimaniaSheep entityChk = (EntityAnimaniaSheep) this.entity;
    			if (entityChk.getSleeping()) {
    				entityChk.setSleeping(false);
    			}
    		}
            
            if (this.entity instanceof EntityAnimaniaRabbit) {
    			EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) this.entity;
    			if (entityChk.getSleeping()) {
    				entityChk.setSleeping(false);
    			}
    		}
            
            if (this.entity instanceof EntityHedgehogBase) {
    			EntityHedgehogBase entityChk = (EntityHedgehogBase) this.entity;
    			if (entityChk.getSleeping()) {
    				entityChk.setSleeping(false);
    			}
    		}
    		
            
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 30, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
            //Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 40, 4);

            
            if (vec3d == null)
            {
                return false;
            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
            {
                return false;
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
        }
    }

    
    public boolean shouldContinueExecuting()
    {
        return !this.navigation.noPath();
    }

    public void startExecuting()
    {
        this.navigation.setPath(this.path, this.farSpeed);
    }

    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    public void updateTask()
    {
        if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0D)
        {
            this.entity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.entity.getNavigator().setSpeed(this.farSpeed);
        }
    }
}
package com.animania.common.entities.sheep.ai;

import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.sheep.EntityEweBase;
import com.animania.common.entities.sheep.EntityLambBase;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAISheepLeapAtTarget extends EntityAIBase
{
	EntityLiving leaper;
	EntityLivingBase leapTarget;
	float leapMotionY;

	public EntityAISheepLeapAtTarget(EntityLivingBase leapingEntity, float leapMotionYIn)
	{
		this.leaper = (EntityLiving) leapingEntity;
		this.leapMotionY = leapMotionYIn;
		this.setMutexBits(5);
	}

	public boolean shouldExecute()
	{
		
		if (this.leaper instanceof EntityEweBase || this.leaper instanceof EntityLambBase) {
			return false;
		}
		
		this.leapTarget = this.leaper.getAttackTarget();

		EntityBuckBase thisBuck = (EntityBuckBase)this.leaper;
		
		if (!thisBuck.getFighting() || this.leapTarget == null)
		{
			return false;
		} else if (thisBuck.getFighting()) {
			double d0 = this.leaper.getDistanceSqToEntity(this.leapTarget);
			return d0 >= 0.0D && d0 <= 4.0D ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(20) == 0) : false;

		} else {
			double d0 = this.leaper.getDistanceSqToEntity(this.leapTarget);
			return d0 >= 0.0D && d0 <= 46.0D ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(20) == 0) : false;
		}
	}
	public boolean shouldContinueExecuting()
	{
		return !this.leaper.onGround;
	}

	public void startExecuting()
	{
		double d0 = this.leapTarget.posX - this.leaper.posX;
		double d1 = this.leapTarget.posZ - this.leaper.posZ;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		if ((double)f >= 1.0E-4D)
		{
			this.leaper.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
			this.leaper.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
		}

		this.leaper.motionY = (double)this.leapMotionY;
	}
}
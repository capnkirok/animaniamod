package com.animania.addons.farm.common.entity.goats.ai;

import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.EntityBuckBase;
import com.animania.addons.farm.common.entity.goats.EntityDoeBase;
import com.animania.addons.farm.common.entity.goats.EntityKidBase;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIGoatsLeapAtTarget extends EntityAIBase
{
	LivingEntity leaper;
	LivingEntity leapTarget;
	float leapMotionY;

	public EntityAIGoatsLeapAtTarget(LivingEntity leapingEntity, float leapMotionYIn)
	{
		this.leaper = (LivingEntity) leapingEntity;
		this.leapMotionY = leapMotionYIn;
		this.setMutexBits(5);
	}

	public boolean shouldExecute()
	{

		if (this.leaper instanceof EntityDoeBase || this.leaper instanceof EntityKidBase) {
			return false;
		}

		if (this.leaper instanceof EntityAnimaniaGoat) {
			EntityAnimaniaGoat AnimalEntity = (EntityAnimaniaGoat) this.leaper;
			if (AnimalEntity.getSleeping()) {
				return false;
			}
		}

		this.leapTarget = this.leaper.getAttackTarget();

		EntityBuckBase thisBuck = (EntityBuckBase)this.leaper;

		if (!thisBuck.getFighting() || this.leapTarget == null)
		{
			return false;
		} else if (thisBuck.getFighting()) {
			double d0 = this.leaper.getDistanceSq(this.leapTarget);
			return d0 >= 0.0D && d0 <= 4.0D ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(20) == 0) : false;

		} else {
			double d0 = this.leaper.getDistanceSq(this.leapTarget);
			return d0 >= 0.0D && d0 <= 46.0D ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(20) == 0) : false;
		}
	}
	public boolean shouldContinueExecuting()
	{
		return !this.leaper.onGround;
	}

	public void startExecuting()
	{
		double d0 = this.leapTarget.getX() - this.leaper.getX();
		double d1 = this.leapTarget.getZ() - this.leaper.getZ();
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		if ((double)f >= 1.0E-4D)
		{
			this.leaper.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
			this.leaper.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
		}

		this.leaper.motionY = (double)this.leapMotionY;
	}
}
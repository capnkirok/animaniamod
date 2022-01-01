package common.entity.goats.ai;

import common.entity.goats.EntityAnimaniaGoat;
import common.entity.goats.EntityBuckBase;
import common.entity.goats.EntityDoeBase;
import common.entity.goats.EntityKidBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class GoatsLeapAtTargetGoal extends Goal
{
	LivingEntity leaper;
	LivingEntity leapTarget;
	float leapMotionY;

	public GoatsLeapAtTargetGoal(LivingEntity leapingEntity, float leapMotionYIn)
	{
		this.leaper = leapingEntity;
		this.leapMotionY = leapMotionYIn;
		this.setMutexBits(5);
	}

	public boolean shouldExecute()
	{

		if (this.leaper instanceof EntityDoeBase || this.leaper instanceof EntityKidBase || this.leaper instanceof EntityAnimaniaGoat AnimalEntity && AnimalEntity.getSleeping())
		{
			return false;
		}

		this.leapTarget = this.leaper.getAttackTarget();

		EntityBuckBase thisBuck = (EntityBuckBase) this.leaper;

		if (!thisBuck.getFighting() || this.leapTarget == null)
		{
			return false;
		}
		else if (thisBuck.getFighting())
		{
			double d0 = this.leaper.getDistanceSq(this.leapTarget);
			return d0 >= 0.0D && d0 <= 4.0D ? !this.leaper.onGround ? false : this.leaper.getRandom().nextInt(20) == 0 : false;

		}
		else
		{
			double d0 = this.leaper.getDistanceSq(this.leapTarget);
			return d0 >= 0.0D && d0 <= 46.0D ? !this.leaper.onGround ? false : this.leaper.getRandom().nextInt(20) == 0 : false;
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

		if (f >= 1.0E-4D)
		{
			this.leaper.motionX += d0 / f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
			this.leaper.motionZ += d1 / f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
		}

		this.leaper.motionY = this.leapMotionY;
	}
}
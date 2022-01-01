package common.entity.horses.ai;

import com.animania.common.helper.AnimaniaHelper;
import common.entity.pullables.EntityCart;
import common.entity.pullables.EntityWagon;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;

public class LookIdleHorsesGoal extends Goal
{
	/** The entity that is looking idle. */
	private final PathfinderMob idleEntity;
	/** X offset to look at */
	private double lookX;
	/** Z offset to look at */
	private double lookZ;
	/**
	 * A decrementing tick that stops the entity from being idle once it reaches
	 * 0.
	 */
	private int idleTime;

	public LookIdleHorsesGoal(PathfinderMob LivingEntityIn)
	{
		this.idleEntity = LivingEntityIn;
		this.setMutexBits(3);
	}

	/**
	 * Returns whether the Goal should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{

		if (!this.idleentity.level.isDay())
		{
			int delayCounter = 0;
			return false;
		}

		boolean pullingFlag = false;
		List entities = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, idleentity.level, this.idleEntity);
		if (!entities.isEmpty())
		{
			EntityCart checkCart = (EntityCart) entities.get(0);
			if (checkCart.puller == this.idleEntity)
			{
				pullingFlag = true;
			}
		}

		entities = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, idleentity.level, this.idleEntity);
		if (!entities.isEmpty())
		{
			EntityWagon checkWagon = (EntityWagon) entities.get(0);
			if (checkWagon.puller == this.idleEntity)
			{
				pullingFlag = true;
			}
		}

		return this.idleEntity.getRandom().nextFloat() < 0.02F;

	}

	/**
	 * Returns whether an in-progress Goal should continue executing
	 */
	@Override
	public boolean shouldContinueExecuting()
	{
		return this.idleTime >= 0;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		double d0 = Math.PI * 2D * this.idleEntity.getRandom().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.idleEntity.getRandom().nextInt(20);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	@Override
	public void updateTask()
	{
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.getX() + this.lookX, this.idleEntity.getY() + this.idleEntity.getEyeHeight(), this.idleEntity.getZ() + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
	}
}

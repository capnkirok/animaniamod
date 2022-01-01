package common.entity.peafowl.ai;

import common.entity.peafowl.EntityAnimaniaPeacock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class WatchClosestFromSideGoal extends Goal
{
	protected LivingEntity theWatcher;
	protected Entity closestEntity;
	protected float maxDistanceForPlayer;
	private int lookTime;
	private final float chance;
	protected Class<? extends Entity> watchedClass;

	public WatchClosestFromSideGoal(LivingEntity LivingEntityIn, Class<? extends Entity> watchTargetClass, float maxDistance)
	{
		this.theWatcher = LivingEntityIn;
		this.watchedClass = watchTargetClass;
		this.maxDistanceForPlayer = maxDistance;
		this.chance = 0.02F;
		this.setMutexBits(2);
	}

	public WatchClosestFromSideGoal(LivingEntity LivingEntityIn, Class<? extends Entity> watchTargetClass, float maxDistance, float chanceIn)
	{
		this.theWatcher = LivingEntityIn;
		this.watchedClass = watchTargetClass;
		this.maxDistanceForPlayer = maxDistance;
		this.chance = chanceIn;
		this.setMutexBits(2);
	}

	@Override
	public boolean canUse()
	{

		boolean isSleeping = false;
		if (this.theWatcher instanceof EntityAnimaniaPeacock entityChk && entityChk.getSleeping())
		{
			isSleeping = true;
		}

		if (!this.theWatcher.level.isDay() || isSleeping)
		{
			return false;
		}

		if (this.theWatcher.getRandom().nextFloat() >= this.chance)
			return false;
		else
		{
			if (this.theWatcher.getAttackTarget() != null)
				this.closestEntity = this.theWatcher.getAttackTarget();

			if (this.watchedClass == Player.class)
				this.closestEntity = this.theWatcher.level.getNearestPlayer(this.theWatcher, this.maxDistanceForPlayer);
			else
				this.closestEntity = this.theWatcher.level.getNearestEntity(this.watchedClass, this.theWatcher.getBoundingBox().expandTowards(this.maxDistanceForPlayer, 3.0D, this.maxDistanceForPlayer), this.theWatcher);

			return this.closestEntity != null;
		}
	}

	@Override
	public boolean canContinueToUse()
	{
		return this.closestEntity.isAlive() && (!(this.theWatcher.distanceToSqr(this.closestEntity) > this.maxDistanceForPlayer * this.maxDistanceForPlayer) && this.lookTime > 0);
	}

	@Override
	public void start()
	{
		this.lookTime = 40 + this.theWatcher.getRandom().nextInt(40);
	}

	@Override
	public void stop()
	{
		this.closestEntity = null;
	}

	@Override
	public void tick()
	{

		this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.getX() + 20F, this.closestEntity.getY() + this.closestEntity.getEyeHeight(), this.closestEntity.getZ(), this.theWatcher.getHorizontalFaceSpeed(), this.theWatcher.getVerticalFaceSpeed());
		--this.lookTime;
	}
}
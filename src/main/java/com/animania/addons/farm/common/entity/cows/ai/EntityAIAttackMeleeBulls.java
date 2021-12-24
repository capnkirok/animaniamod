package com.animania.addons.farm.common.entity.cows.ai;

import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.cows.EntityBullBase;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class AttackMeleeBullsGoal extends Goal
{
	Level                    levelObj;
	protected PathfinderMob attacker;
	protected int            attackTick;
	double                   speedTowardsTarget;
	boolean                  longMemory;
	Path                     entityPathEntity;
	private int              delayCounter;
	private double           targetX;
	private double           targetY;
	private double           targetZ;
	protected final int      attackInterval           = 20;
	private int              failedPathFindingPenalty = 0;
	private boolean          canPenalize              = false;

	public AttackMeleeBullsGoal(EntityAnimaniaCow creature, double speedIn, boolean useLongMemory) {
		this.attacker = creature;
		this.levelObj = creature.level;
		this.speedTowardsTarget = speedIn;
		this.longMemory = useLongMemory;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		LivingEntity LivingEntity = this.attacker.getAttackTarget();

		if (LivingEntity == null)
			return false;
		else if (!LivingEntity.isAlive() || LivingEntity instanceof SkeletonEntity)
			return false;
		else {

			if (this.attacker instanceof EntityBullBase) {

				EntityBullBase eb = (EntityBullBase) this.attacker;
				if (eb.getSleeping()) {
					eb.setSleeping(false);
				} else {
					eb.setFighting(true);
					eb.entityAIEatGrass.eatingGrassTimer = 0;
				}
			}
			this.entityPathEntity = this.attacker.getNavigation().getPathToLivingEntity(LivingEntity);
			return this.entityPathEntity != null;

		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		LivingEntity LivingEntity = this.attacker.getAttackTarget();
		return LivingEntity == null ? false
				: !LivingEntity.isAlive() ? false
						: !this.longMemory ? !this.attacker.getNavigation().noPath()
								: !this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(LivingEntity)) ? false
										: !(LivingEntity instanceof PlayerEntity) || !((PlayerEntity) LivingEntity).isSpectator()
										&& !((PlayerEntity) LivingEntity).isCreative();
	}

	@Override
	public void startExecuting() {
		this.attacker.getNavigation().setPath(this.entityPathEntity, this.speedTowardsTarget);
		this.delayCounter = 0;
	}

	@Override
	public void resetTask() {
		LivingEntity LivingEntity = this.attacker.getAttackTarget();

		if (LivingEntity instanceof PlayerEntity
				&& (((PlayerEntity) LivingEntity).isSpectator() || ((PlayerEntity) LivingEntity).isCreative()))
			this.attacker.setAttackTarget((LivingEntity) null);

		this.attacker.getNavigation().stop();
		if (this.attacker instanceof EntityBullBase) {
			EntityBullBase eb = (EntityBullBase) this.attacker;
			eb.setFighting(false);
		}
	}

	@Override
	public void updateTask() {
		LivingEntity LivingEntity = this.attacker.getAttackTarget();

		if (LivingEntity != null) {

			this.attacker.getLookHelper().setLookPositionWithEntity(LivingEntity, 20.0F, 20.0F);
			double d0 = this.attacker.getDistanceSq(LivingEntity.getX(), LivingEntity.getEntityBoundingBox().minY, LivingEntity.getZ());
			--this.delayCounter;

			if ((this.longMemory || this.attacker.getEntitySenses().canSee(LivingEntity)) && this.delayCounter <= 0
					&& (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D
					|| LivingEntity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D
					|| this.attacker.getRandom().nextFloat() < 0.05F)) {
				this.targetX = LivingEntity.getX();
				this.targetY = LivingEntity.getEntityBoundingBox().minY;
				this.targetZ = LivingEntity.getZ();
				this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);

				if (this.canPenalize) {
					this.delayCounter += this.failedPathFindingPenalty;
					if (this.attacker.getNavigation().getPath() != null) {
						net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigation().getPath().getFinalPathPoint();
						if (finalPathPoint != null
								&& LivingEntity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
							this.failedPathFindingPenalty = 0;
						else
							this.failedPathFindingPenalty += 10;
					}
					else
						this.failedPathFindingPenalty += 10;
				}

				if (d0 > 1024.0D)
					this.delayCounter += 10;
				else if (d0 > 256.0D)
					this.delayCounter += 5;

				if (!this.attacker.getNavigation().tryMoveToLivingEntity(LivingEntity, this.speedTowardsTarget))
					this.delayCounter += 15;
			}

			this.attackTick = Math.max(this.attackTick - 1, 0);
			this.checkAndPerformAttack(LivingEntity, d0);
		}

	}

	protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_) {
		double d0 = this.getAttackReachSqr(p_190102_1_);

		if (p_190102_2_ <= d0 && this.attackTick <= 0) {
			this.attackTick = 20;
			this.attacker.swingArm(EnumHand.MAIN_HAND);
			this.attacker.attackEntityAsMob(p_190102_1_);
		}
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return this.attacker.width * 2.0F * this.attacker.width * 2.0F + attackTarget.width;
	}
}
package com.animania.common.entities.cows.ai;

import java.util.List;
import java.util.UUID;

import com.animania.Animania;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityCalfBase;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAIMateCows extends EntityAIBase
{
	private final EntityAnimaniaCow theAnimal;
	World                      theWorld;
	private EntityAnimal       targetMate;
	int                        courtshipTimer;
	double                     moveSpeed;
	private int                delayCounter;

	public EntityAIMateCows(EntityAnimaniaCow animal, double speedIn) {
		this.theAnimal = animal;
		this.theWorld = animal.world;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;

	}

	@Override
	public boolean shouldExecute() {

		
		this.delayCounter++;

		//System.out.println(delayCounter);

		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (theAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
			
			if (this.theAnimal instanceof EntityCalfBase || this.theAnimal instanceof EntityCowBase || this.theAnimal.isInWater()) {
				this.delayCounter = 0;
				return false;
			}

			EntityAnimaniaCow thisAnimal = this.theAnimal;

			if (AnimaniaConfig.careAndFeeding.feedToBreed) {
				if (!thisAnimal.getHandFed()) {
					this.delayCounter = 0;
					return false;
				}
			} else {

				List list = this.theWorld.loadedEntityList;

				int cowCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof EntityAnimaniaCow) {
						num++;
					}
				}
				cowCount = num;

				if (cowCount > AnimaniaConfig.spawn.spawnLimitCows && !thisAnimal.getHandFed()) {
					this.delayCounter = 0;
					return false;
				}
			}


			this.targetMate = this.getNearbyMate();

			if (this.targetMate != null && Animania.RANDOM.nextInt(20) == 0) {
				this.delayCounter = 0;
				this.resetTask();
				return false;
			}

			return this.targetMate != null;

		}
		else
			return false;

	}

	@Override
	public boolean shouldContinueExecuting() {
		if (targetMate != null) {
			return this.targetMate.isEntityAlive();
		} else {
			return false;
		}
	}

	@Override
	public void resetTask() {
		this.targetMate = null;
	}

	@Override
	public void updateTask() {

		if (this.targetMate != null) {
			EntityCowBase tm = (EntityCowBase) this.targetMate;
			if (!tm.getPregnant() && tm.getFertile()) {
				this.targetMate = this.getNearbyMate();
			} else {
				this.theAnimal.resetInLove();
				this.resetTask();
				this.theAnimal.getNavigator().clearPath();
				this.delayCounter = 0;
			}
		}
	}

	private EntityAnimal getNearbyMate() {


		if (this.theAnimal instanceof EntityBullBase) {

			UUID mateID = null;

			EntityBullBase entity2 = (EntityBullBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId();
			}


			if (mateID != null) {

				List entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 3, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {

					EntityCowBase entity = (EntityCowBase)entities.get(k); 
					boolean allowBreeding = true;
					if (AnimaniaConfig.careAndFeeding.feedToBreed && !entity.getHandFed()) {
						allowBreeding = false;
					}

					if (entity.getPersistentID().equals(mateID) && entity.getFertile() && !entity.getPregnant() && !entity.getSleeping() && allowBreeding && entity.canEntityBeSeen(entity2)) {

						this.courtshipTimer--;
						if (this.courtshipTimer < 0) {
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = entities.size();
							entity.setPregnant(true);
							entity.setFertile(false);
							entity.setHandFed(false);

							delayCounter = 0;
							return (EntityAnimal) entity;
						} else if (allowBreeding) {
							k = entities.size();
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							entity.getLookHelper().setLookPositionWithEntity(this.theAnimal, 10.0F, entity.getVerticalFaceSpeed());
							entity.getNavigator().tryMoveToEntityLiving(this.theAnimal, this.moveSpeed);
							return null;
						}
					}
				}
			} else {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 5, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityCowBase entity = (EntityCowBase)entities.get(k); 

					boolean allowBreeding = true;
					if (AnimaniaConfig.careAndFeeding.feedToBreed && !entity.getHandFed()) {
						allowBreeding = false;
					}

					this.courtshipTimer--;

					if (entity.getMateUniqueId() == null && this.courtshipTimer < 0 && entity.getFertile() && !entity.getSleeping() && !entity.getPregnant() && allowBreeding && entity.canEntityBeSeen(entity2)) {

						((EntityBullBase) this.theAnimal).setMateUniqueId(entity.getPersistentID());
						entity.setMateUniqueId(this.theAnimal.getPersistentID());
						this.theAnimal.setInLove(null);
						this.courtshipTimer = 20;
						k = entities.size();
						entity.setPregnant(true);
						entity.setHandFed(false);
						entity.setFertile(false);
						delayCounter = 0;
						return (EntityAnimal) entity;
					} else if (entity.getMateUniqueId() == null && !entity.getPregnant() && entity.getFertile() && !entity.getSleeping() && allowBreeding && entity.canEntityBeSeen(entity2)) {
						k = entities.size();
						this.theAnimal.setInLove(null);
						this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
						this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
						entity.getLookHelper().setLookPositionWithEntity(this.theAnimal, 10.0F, entity.getVerticalFaceSpeed());
						entity.getNavigator().tryMoveToEntityLiving(this.theAnimal, this.moveSpeed);
						return null;

					}
				}
			}
		}

		delayCounter = 0;
		return null;
	}
}
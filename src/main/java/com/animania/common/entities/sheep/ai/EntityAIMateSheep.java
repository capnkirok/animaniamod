package com.animania.common.entities.sheep.ai;

import java.util.List;
import java.util.UUID;

import com.animania.Animania;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.entities.sheep.EntityEweBase;
import com.animania.common.entities.sheep.EntityLambBase;
import com.animania.common.entities.sheep.EntityRamBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAIMateSheep extends EntityAIBase
{
	private final EntityAnimaniaSheep theAnimal;
	World                     		  theWorld;
	private EntityAnimaniaSheep	      targetMate;
	int                  		      courtshipTimer;
	double           		          moveSpeed;
	private int      		          delayCounter;

	public EntityAIMateSheep(EntityAnimal animal, double speedIn) {
		this.theAnimal = (EntityAnimaniaSheep) animal;
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

			if (this.theAnimal instanceof EntityLambBase || this.theAnimal instanceof EntityEweBase || this.theAnimal.isInWater()) {
				this.delayCounter = 0;
				return false;
			}

			if (!this.theAnimal.world.isDaytime() || this.theAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
			
			EntityAnimaniaSheep thisAnimal = (EntityAnimaniaSheep) this.theAnimal;

			if (AnimaniaConfig.careAndFeeding.manualBreeding) {
				if (!thisAnimal.getHandFed()) {
					this.delayCounter = 0;
					return false;
				}
			} else {

				List list = this.theWorld.loadedEntityList;
				int cowCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof EntityAnimaniaSheep) {
						num++;
					}
				}
				cowCount = num;

				if (cowCount > AnimaniaConfig.spawn.spawnLimitSheep && !thisAnimal.getHandFed()) {
					this.delayCounter = 0;
					return false;
				}
			}

			this.targetMate = (EntityAnimaniaSheep) this.getNearbyMate();

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
			EntityEweBase tm = (EntityEweBase) this.targetMate;
			if (!tm.getPregnant() && tm.getFertile()) {
				this.targetMate = (EntityAnimaniaSheep) this.getNearbyMate();
			} else {
				this.theAnimal.resetInLove();
				this.resetTask();
				this.theAnimal.getNavigator().clearPath();
				this.delayCounter = 0;
			}
		}
	}

	private EntityAnimal getNearbyMate() {


		if (this.theAnimal instanceof EntityRamBase) {

			UUID mateID = null;

			EntityRamBase entity2 = (EntityRamBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId();
			}

			if (mateID != null) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityEweBase.class, 3, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityEweBase entity = (EntityEweBase)entities.get(k); 

					boolean allowBreeding = true;
					if (AnimaniaConfig.careAndFeeding.manualBreeding && !entity.getHandFed()) {
						allowBreeding = false;
					}

					if (entity.getPersistentID().equals(mateID) && entity.getFertile() && !entity.getSleeping() && !entity.getPregnant() && allowBreeding && entity.canEntityBeSeen(entity2)) {


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
				List entities = AnimaniaHelper.getEntitiesInRange(EntityEweBase.class, 5, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityEweBase entity = (EntityEweBase)entities.get(k); 

					boolean allowBreeding = true;
					if (AnimaniaConfig.careAndFeeding.manualBreeding && !entity.getHandFed()) {
						allowBreeding = false;
					}

					this.courtshipTimer--;
					if (entity.getMateUniqueId() == null && this.courtshipTimer < 0 && !entity.getSleeping() && entity.getFertile() && !entity.getPregnant() && allowBreeding && entity.canEntityBeSeen(entity2)) {

						((EntityRamBase) this.theAnimal).setMateUniqueId(entity.getPersistentID());
						entity.setMateUniqueId(this.theAnimal.getPersistentID());
						this.theAnimal.setInLove(null);
						this.courtshipTimer = 20;
						k = entities.size();
						entity.setPregnant(true);
						entity.setFertile(false);
						entity.setHandFed(false);
						delayCounter = 0;
						return (EntityAnimal) entity;
					} else if (entity.getMateUniqueId() == null && !entity.getPregnant() && !entity.getSleeping() && entity.getFertile() && allowBreeding && entity.canEntityBeSeen(entity2)) {

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
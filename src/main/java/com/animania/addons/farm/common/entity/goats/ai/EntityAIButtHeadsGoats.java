package com.animania.addons.farm.common.entity.goats.ai;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.EntityBuckBase;
import com.animania.addons.farm.common.entity.goats.EntityDoeBase;
import com.animania.addons.farm.common.entity.goats.EntityKidBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class ButtHeadsGoatsGoal extends Goal
{
	private final EntityAnimaniaGoat theAnimal;
	Level theWorld;
	private Animal targetMate;
	int fightTimer;
	double moveSpeed;
	private int delayCounter;


	public ButtHeadsGoatsGoal(EntityAnimaniaGoat animal, double speedIn)
	{
		this.theAnimal = animal;
		this.theWorld = animal.level;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.fightTimer = 100 + Animania.RANDOM.nextInt(50);
		this.delayCounter = 0;

	}

	@Override
	public boolean shouldExecute() {

		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings * 20) {

			if (this.theAnimal instanceof EntityDoeBase || this.theAnimal instanceof EntityKidBase) {
				return false;
			}
			
			if (!this.theAnimal.level.isDay() || this.theAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
			
			this.targetMate = this.getNearbyRival();

			if (this.targetMate != null && Animania.RANDOM.nextInt(20) == 0) {
				this.delayCounter = 0;
				this.resetTask();
				return false;
			}

			EntityBuckBase thisEntity = (EntityBuckBase) this.theAnimal;

			return (this.targetMate != null && thisEntity.getFighting());

		}
		else
			return false;

	}

	public boolean shouldContinueExecuting()
	{
		if (targetMate != null) {
			return this.targetMate.isAlive();
		} else {
			return false;
		}
	}

	public void resetTask()
	{
		this.targetMate = null;
	}

	public void updateTask()
	{

		EntityBuckBase thisEntity = (EntityBuckBase) this.theAnimal;
		if (thisEntity.getFighting()) {
			this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
			this.theAnimal.getNavigation().tryMoveToLivingEntity(this.targetMate, this.moveSpeed);
		} else {

			thisEntity.getNavigation().stop();
		}

	}

	private AnimalEntity getNearbyRival() {

		if (this.theAnimal instanceof EntityBuckBase) {
		
			EntityBuckBase thisEntity = (EntityBuckBase) this.theAnimal;
			EntityBuckBase foundEntity = null;

			List entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 10, this.theAnimal.level, this.theAnimal);
			
			if (entities != null) {
				for (int k = 0; k < entities.size(); k++) {

					EntityBuckBase entity = (EntityBuckBase)entities.get(k); 

					if (thisEntity.getRivalUniqueId() == null && entity.getRivalUniqueId() == null && thisEntity != entity) {
						foundEntity = entity;
						foundEntity.setRivalUniqueId(thisEntity.getUUID());
						thisEntity.setRivalUniqueId(foundEntity.getUUID());
						thisEntity.setFighting(true);
						foundEntity.setFighting(true);
						thisEntity.setAttackTarget(foundEntity);
						foundEntity.setAttackTarget(thisEntity);
						k = entities.size();
						break;
					} else if (thisEntity.getRivalUniqueId() != null && thisEntity.getRivalUniqueId() == entity.getUUID() && thisEntity != entity) {
						foundEntity = entity;	
						foundEntity.setRivalUniqueId(thisEntity.getUUID());
						thisEntity.setRivalUniqueId(foundEntity.getUUID());
						thisEntity.setAttackTarget(foundEntity);
						foundEntity.setAttackTarget(thisEntity);
						k = entities.size();
						break;
					} 
				}
			}
			
			this.fightTimer--;
			if (foundEntity != null) {
				
				if (this.fightTimer < 0 && thisEntity.getFighting()) {
					this.fightTimer = 100 + Animania.RANDOM.nextInt(50);
					this.targetMate = null;
					thisEntity.setFighting(false);
					foundEntity.setFighting(false);
					foundEntity.setRivalUniqueId(null);
					thisEntity.setRivalUniqueId(null);
					thisEntity.setAttackTarget(null);
					foundEntity.setAttackTarget(null);
					this.delayCounter = 0;
					return null;

				} else {
					
					thisEntity.getLookHelper().setLookPositionWithEntity(foundEntity, 10.0F, thisEntity.getVerticalFaceSpeed());
					thisEntity.getNavigation().tryMoveToLivingEntity(foundEntity, this.moveSpeed);
		
					foundEntity.getLookHelper().setLookPositionWithEntity(thisEntity, 10.0F, foundEntity.getVerticalFaceSpeed());
					foundEntity.getNavigation().tryMoveToLivingEntity(thisEntity, this.moveSpeed);
					
					return null;

				} 
			} else if (foundEntity == null || fightTimer < 0) {
				this.fightTimer = 100 + Animania.RANDOM.nextInt(50);
				this.targetMate = null;
				thisEntity.setFighting(false);
				thisEntity.setRivalUniqueId(null);
				thisEntity.setAttackTarget(null);
				this.delayCounter = 0;
				return null;
			}
		}

		return null;
	}
}
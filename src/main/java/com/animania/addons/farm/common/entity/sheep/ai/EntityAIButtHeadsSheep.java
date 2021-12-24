package com.animania.addons.farm.common.entity.sheep.ai;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.sheep.EntityEweBase;
import com.animania.addons.farm.common.entity.sheep.EntityLambBase;
import com.animania.addons.farm.common.entity.sheep.EntityRamBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class ButtHeadsSheepGoal extends Goal
{
	private final Animal theAnimal;
	Level theWorld;
	private Animal targetMate;
	int fightTimer;
	double moveSpeed;
	private int delayCounter;


	public ButtHeadsSheepGoal(Animal animal, double speedIn)
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
		if (this.delayCounter > 1000) {

			if (this.theAnimal instanceof EntityEweBase || this.theAnimal instanceof EntityLambBase) {
				return false;
			}
			
			if (!this.theAnimal.level.isDay()) {
				this.delayCounter = 0;
				return false;
			}
			
			this.targetMate = this.getNearbyRival();

			if (this.targetMate != null && Animania.RANDOM.nextInt(20) == 0) {
				this.delayCounter = 0;
				this.resetTask();
				return false;
			}

			EntityRamBase thisEntity = (EntityRamBase) this.theAnimal;

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

		EntityRamBase thisEntity = (EntityRamBase) this.theAnimal;
		if (thisEntity.getFighting()) {
			this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
			this.theAnimal.getNavigation().tryMoveToLivingEntity(this.targetMate, this.moveSpeed);
		} else {

			thisEntity.getNavigation().stop();
		}

	}

	private AnimalEntity getNearbyRival() {

		if (this.theAnimal instanceof EntityRamBase) {
		
			EntityRamBase thisEntity = (EntityRamBase) this.theAnimal;
			EntityRamBase foundEntity = null;

			List entities = AnimaniaHelper.getEntitiesInRange(EntityRamBase.class, 10, this.theAnimal.level, this.theAnimal);
			
			if (entities != null) {
				for (int k = 0; k < entities.size(); k++) {

					EntityRamBase entity = (EntityRamBase)entities.get(k); 

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
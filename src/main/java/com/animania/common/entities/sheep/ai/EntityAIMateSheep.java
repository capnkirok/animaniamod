package com.animania.common.entities.sheep.ai;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
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
	private final EntityAnimal theAnimal;
	World                      theWorld;
	private EntityAnimal       targetMate;
	int                        courtshipTimer;
	double                     moveSpeed;
	private int                delayCounter;
	private Random			   rand;

	public EntityAIMateSheep(EntityAnimal animal, double speedIn) {
		this.theAnimal = animal;
		this.theWorld = animal.world;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;
		this.rand = new Random();

	}

	@Override
	public boolean shouldExecute() {

		this.delayCounter++;

		//System.out.println(delayCounter);

		if (this.delayCounter > 100) {

			if (this.theAnimal instanceof EntityLambBase || this.theAnimal instanceof EntityEweBase || this.theAnimal.isInWater()) {
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

				int animalCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof EntityAnimaniaSheep) {
						num++;
					}
				}
				animalCount = num;

				if (animalCount > AnimaniaConfig.spawn.spawnLimitSheep && !thisAnimal.getHandFed()) {
					this.delayCounter = 0;
					return false;
				}
			}

			this.targetMate = this.getNearbyMate();

			Random rand = new Random();
			if (this.targetMate != null && rand.nextInt(20) == 0) {
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
	public boolean continueExecuting() {
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
				this.targetMate = this.getNearbyMate();
			} else {
				this.theAnimal.resetInLove();
				this.resetTask();
				this.theAnimal.getNavigator().clearPathEntity();
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

					if (entity.getPersistentID().equals(mateID) && entity.getFertile() && !entity.getPregnant() && allowBreeding) {

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
					if (AnimaniaConfig.careAndFeeding.manualBreeding && !entity.getHandFed() && allowBreeding) {
						allowBreeding = false;
					}

					this.courtshipTimer--;
					if (entity.getMateUniqueId() == null && this.courtshipTimer < 0 && entity.getFertile() && !entity.getPregnant()) {
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
					} else if (entity.getMateUniqueId() == null && !entity.getPregnant() && entity.getFertile() && allowBreeding) {
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
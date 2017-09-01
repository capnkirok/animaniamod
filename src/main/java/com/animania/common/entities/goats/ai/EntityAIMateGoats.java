package com.animania.common.entities.goats.ai;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.goats.EntityDoeBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAIMateGoats extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World                      theWorld;
	private EntityAnimal       targetMate;
	int                        courtshipTimer;
	double                     moveSpeed;
	private int                delayCounter;
	private Random			   rand;

	public EntityAIMateGoats(EntityAnimal animal, double speedIn) {
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

		if (this.delayCounter > 100) {

			if (this.theAnimal instanceof EntityDoeBase) {
				EntityDoeBase ec = (EntityDoeBase) this.theAnimal;
				System.out.println("F: "+ ec.getFertile());
				System.out.println("P: "+ ec.getPregnant());
				
				if (ec.getFertile() == false || ec.getPregnant() == true) {
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
		return this.targetMate.isEntityAlive();
	}

	@Override
	public void resetTask() {
		this.targetMate = null;
	}

	@Override
	public void updateTask() {
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);

	}

	private EntityAnimal getNearbyMate() {

		System.out.println("yep");
		
		if (this.theAnimal instanceof EntityDoeBase) {

			System.out.println(1);
			
			UUID mateID = null;

			EntityDoeBase entity2 = (EntityDoeBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId();
			}

			if (mateID != null) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 2, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityBuckBase entity = (EntityBuckBase)entities.get(k); 

					if (entity.getPersistentID().equals(mateID) && entity2.getFertile() && !entity2.getPregnant()) {

						this.courtshipTimer--;

						if (this.courtshipTimer < 0) {
							((EntityDoeBase) this.theAnimal).setMateUniqueId(entity.getPersistentID());
							entity.setMateUniqueId(this.theAnimal.getPersistentID());
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = entities.size();
							entity2.setPregnant(true);
							entity2.setFertile(false);
							return (EntityAnimal) entity;
						} else {
							k = entities.size();
							this.theAnimal.setInLove(null);

							//Male pursues female on reconnect
							entity.getLookHelper().setLookPositionWithEntity(this.theAnimal, 2.0F, entity.getVerticalFaceSpeed());
							entity.getNavigator().tryMoveToEntityLiving(this.theAnimal, this.moveSpeed);

							return null;
						}
					} 
				}
			} else {

				List entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 5, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityBuckBase entity = (EntityBuckBase)entities.get(k); 

					this.courtshipTimer--;

					if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
						this.theAnimal.setInLove(null);
						this.courtshipTimer = 20;
						k = entities.size();
						entity2.setPregnant(true);
						entity2.setFertile(false);
						return (EntityAnimal) entity;
					} else if (entity.getMateUniqueId() == null) {
						k = entities.size();
						this.theAnimal.setInLove(null);
						this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
						this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
						return null;

					} 
				}

			} 

		} else if (this.theAnimal instanceof EntityBuckBase) {

			UUID mateID = null;
			System.out.println(2);
			EntityBuckBase entity2 = (EntityBuckBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId();
			}

			if (mateID != null) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 3, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityDoeBase entity = (EntityDoeBase)entities.get(k); 

					if (entity.getPersistentID().equals(mateID) && entity.getFertile() && !entity.getPregnant()) {

						this.courtshipTimer--;

						if (this.courtshipTimer < 0) {
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = entities.size();
							entity.setPregnant(true);
							entity.setFertile(false);
							return (EntityAnimal) entity;
						} else {
							k = entities.size();
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 3.0F, this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					}
				}
			} else {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 5, this.theAnimal.world, this.theAnimal);

				for (int k = 0; k <= entities.size() - 1; k++) {
					EntityDoeBase entity = (EntityDoeBase)entities.get(k); 

					this.courtshipTimer--;

					if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
						((EntityBuckBase) this.theAnimal).setMateUniqueId(entity.getPersistentID());
						entity.setMateUniqueId(this.theAnimal.getPersistentID());
						this.theAnimal.setInLove(null);
						this.courtshipTimer = 20;
						k = entities.size();
						entity.setPregnant(true);
						entity.setFertile(false);
						return (EntityAnimal) entity;
					} else if (entity.getMateUniqueId() == null) {
						k = entities.size();
						this.theAnimal.setInLove(null);
						this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
						this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
						return null;

					}
				}
			}
		}


		return null;
	}
}
package com.animania.common.entities.cows.ai;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAIMateCows extends EntityAIBase
{
    private final EntityAnimal theAnimal;
    World                      theWorld;
    private EntityAnimal       targetMate;
    int                        courtshipTimer;
    double                     moveSpeed;
    private int                delayCounter;

    public EntityAIMateCows(EntityAnimal animal, double speedIn) {
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
		if (this.delayCounter > 100) {

			if (this.theAnimal instanceof EntityCowBase) {
				EntityCowBase ec = (EntityCowBase) this.theAnimal;
				if (ec.getMateUniqueId() != null)
					return false;
			} else if (this.theAnimal instanceof EntityBullBase) {
				EntityBullBase ec = (EntityBullBase) this.theAnimal;
				if (ec.getMateUniqueId() != null)
					return false;
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

		if (this.theAnimal instanceof EntityCowBase) {

			String mateID = "";

			EntityCowBase entity2 = (EntityCowBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}


			List entities = AnimaniaHelper.getEntitiesInRange(EntityBullBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntityBullBase entity = (EntityBullBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntityCowBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
					entity.setMateUniqueId(this.theAnimal.getUniqueID());

					this.theAnimal.setInLove(null);
					this.courtshipTimer = 20;
					k = entities.size();
					return (EntityAnimal) entity;
				} else if (entity.getMateUniqueId() == null) {
					k = entities.size();
					this.theAnimal.setInLove(null);
					this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, this.theAnimal.getVerticalFaceSpeed());
					this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
					return null;

				} 

			} 

		} else if (this.theAnimal instanceof EntityBullBase) {

			String mateID = "";

			EntityBullBase entity2 = (EntityBullBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}

			List entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntityCowBase entity = (EntityCowBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntityBullBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
					entity.setMateUniqueId(this.theAnimal.getUniqueID());
					this.theAnimal.setInLove(null);
					this.courtshipTimer = 20;
					k = entities.size();
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


		return null;
	}
}
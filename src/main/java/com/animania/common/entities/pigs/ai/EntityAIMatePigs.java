package com.animania.common.entities.pigs.ai;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIMatePigs extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World                      theWorld;
	private EntityAnimal       targetMate;
	int                        courtshipTimer;
	double                     moveSpeed;
	private int                delayCounter;

	public EntityAIMatePigs(EntityAnimal animal, double speedIn) {
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

			if (this.theAnimal instanceof EntitySowBase) {
				EntitySowBase ec = (EntitySowBase) this.theAnimal;
				if (ec.getMateUniqueId() != null)
					return false;
			} else if (this.theAnimal instanceof EntityHogBase) {
				EntityHogBase ec = (EntityHogBase) this.theAnimal;
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

		if (this.theAnimal instanceof EntitySowBase) {

			String mateID = "";

			EntitySowBase entity2 = (EntitySowBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}


			List entities = AnimaniaHelper.getEntitiesInRange(EntityHogBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntityHogBase entity = (EntityHogBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntitySowBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
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

		} else if (this.theAnimal instanceof EntityHogBase) {

			String mateID = "";

			EntityHogBase entity2 = (EntityHogBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}

			List entities = AnimaniaHelper.getEntitiesInRange(EntitySowBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntitySowBase entity = (EntitySowBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntityHogBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
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
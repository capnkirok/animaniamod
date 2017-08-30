package com.animania.common.entities.sheep.ai;

import java.util.List;
import java.util.Random;

import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.common.entities.goats.EntityDoeBase;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityAIMateGoats extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World theWorld;
	private EntityAnimal targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;


	public EntityAIMateGoats(EntityAnimal animal, double speedIn)
	{
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

			if (this.theAnimal instanceof EntityDoeBase) {
				EntityDoeBase ec = (EntityDoeBase) this.theAnimal;
				if (ec.getMateUniqueId() != null)
					return false;
			} else if (this.theAnimal instanceof EntityBuckBase) {
				EntityBuckBase ec = (EntityBuckBase) this.theAnimal;
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

	public boolean continueExecuting()
	{
		return this.targetMate.isEntityAlive();
	}

	public void resetTask()
	{
		this.targetMate = null;
	}

	public void updateTask()
	{
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);

	}

	private EntityAnimal getNearbyMate() {

		if (this.theAnimal instanceof EntityDoeBase) {

			String mateID = "";

			EntityDoeBase entity2 = (EntityDoeBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}


			List entities = AnimaniaHelper.getEntitiesInRange(EntityBuckBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntityBuckBase entity = (EntityBuckBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntityDoeBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
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

		} else if (this.theAnimal instanceof EntityBuckBase) {

			String mateID = "";

			EntityBuckBase entity2 = (EntityBuckBase) this.theAnimal;
			if (entity2.getMateUniqueId() != null) {
				mateID = entity2.getMateUniqueId().toString();
			}

			List entities = AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 5, this.theAnimal.world, this.theAnimal);

			for (int k = 0; k <= entities.size() - 1; k++) {
				EntityDoeBase entity = (EntityDoeBase)entities.get(k); 

				this.courtshipTimer--;

				if (entity.getMateUniqueId() == null && this.courtshipTimer < 0) {
					((EntityBuckBase) this.theAnimal).setMateUniqueId(entity.getUniqueID());
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
package com.animania.common.entities.horses.ai;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIMateHorses extends EntityAIBase
{
	private final EntityAnimal theAnimal;
	World theWorld;
	private EntityAnimal targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;


	public EntityAIMateHorses(EntityAnimal animal, double speedIn)
	{
		this.theAnimal = animal;
		this.theWorld = animal.worldObj;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;

	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		delayCounter++;
		if (delayCounter > 100) {

			if (this.theAnimal instanceof EntityMareDraftHorse) {
				EntityMareDraftHorse ec = (EntityMareDraftHorse)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
					return false;
				}
			} else if (this.theAnimal instanceof EntityStallionDraftHorse) {
				EntityStallionDraftHorse ec = (EntityStallionDraftHorse)this.theAnimal;
				if (ec.getMateUniqueId() != null) {
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
		} else {
			return false;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return this.targetMate.isEntityAlive();
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.targetMate = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
		this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);

	}


	private EntityAnimal getNearbyMate()
	{

		if (this.theAnimal instanceof EntityMareDraftHorse) {

			String mateID = "";
			if (this.theAnimal instanceof EntityStallionDraftHorse) {
				EntityStallionDraftHorse entity2 = (EntityStallionDraftHorse)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} 

			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor_double(this.theAnimal.posX);
				int y1 = MathHelper.floor_double(this.theAnimal.posY);
				int z1 = MathHelper.floor_double(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);


				if (entity !=null && (entity instanceof EntityStallionDraftHorse) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;
					if (entity instanceof EntityStallionDraftHorse) {
						EntityStallionDraftHorse entity3 = (EntityStallionDraftHorse) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0) {
							if (this.theAnimal instanceof EntityMareDraftHorse) {
								((EntityMareDraftHorse) this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							}
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					
					}
				} 
			}
		} else if (this.theAnimal instanceof EntityStallionDraftHorse) {


			String mateID = "";
			if (this.theAnimal instanceof EntityStallionDraftHorse) {
				EntityStallionDraftHorse entity2 = (EntityStallionDraftHorse)this.theAnimal;
				if (entity2.getMateUniqueId() != null) {
					mateID = entity2.getMateUniqueId().toString();
				}
			} 

			int esize = this.theWorld.loadedEntityList.size();
			for (int k = 0; k <= esize - 1; k++) {
				Entity entity = (Entity) this.theWorld.loadedEntityList.get(k);

				double xt = entity.posX;
				double yt = entity.posY;
				double zt = entity.posZ;
				int x1 = MathHelper.floor_double(this.theAnimal.posX);
				int y1 = MathHelper.floor_double(this.theAnimal.posY);
				int z1 = MathHelper.floor_double(this.theAnimal.posZ);
				double x2 = Math.abs(xt - x1);
				double y2 = Math.abs(yt - y1);
				double z2 = Math.abs(zt - z1);


				if (entity !=null && (entity instanceof EntityMareDraftHorse) && x2 <= 4 && y2 <=2 && z2 <=4) {

					this.courtshipTimer--;

					if (entity instanceof EntityMareDraftHorse) {
						EntityMareDraftHorse entity3 = (EntityMareDraftHorse) entity;
						if (entity3.getMateUniqueId() == null && this.courtshipTimer < 0 )  {
							if (this.theAnimal instanceof EntityStallionDraftHorse) {
								((EntityStallionDraftHorse)this.theAnimal).setMateUniqueId(entity.getUniqueID());
								entity3.setMateUniqueId(this.theAnimal.getUniqueID());
							} 
							this.theAnimal.setInLove(null);
							this.courtshipTimer = 20;
							k = esize;
							return (EntityAnimal) entity;
						} else if (entity3.getMateUniqueId() == null) {
							k = esize;
							this.theAnimal.setInLove(null);
							this.theAnimal.getLookHelper().setLookPositionWithEntity(entity, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
							this.theAnimal.getNavigator().tryMoveToEntityLiving(entity, this.moveSpeed);
							return null;
						}
					}

				} 
			}
		} 

		return null;
	}


}
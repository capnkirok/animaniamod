package com.animania.common.entities.cows.ai;

import java.util.List;

import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMateCows extends EntityAIBase
{
	EntityAnimaniaCow thisAnimal;
	EntityAnimaniaCow mateAnimal;
	double       moveSpeed;
	private int  delayCounter;

	public EntityAIFollowMateCows(EntityAnimaniaCow animal, double speed) {
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	@Override
	public boolean shouldExecute() {
		
		
		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			
			if (thisAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
			
			if (this.thisAnimal instanceof EntityBullBase) {
				EntityBullBase ec = (EntityBullBase) this.thisAnimal;
				if (ec.getMateUniqueId() == null)
					return false;
				else {

					List entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 40, this.thisAnimal.world, this.thisAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntityCowBase entitycow = (EntityCowBase)entities.get(k);
						if (entities.get(k) != null && entitycow.getPersistentID().equals(((EntityBullBase) this.thisAnimal).getMateUniqueId())) {

							double xt = entitycow.posX;
							double yt = entitycow.posY;
							double zt = entitycow.posZ;
							int x1 = MathHelper.floor(this.thisAnimal.posX);
							int y1 = MathHelper.floor(this.thisAnimal.posY);
							int z1 = MathHelper.floor(this.thisAnimal.posZ);
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);

							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.mateAnimal = entitycow;
								return true;
							} else {
								return false;
							}

						}

					}

				}

			}

		return false;

	}

	@Override
	public boolean shouldContinueExecuting() {
		if (!this.mateAnimal.isEntityAlive())
			return false;
		else {
			double d0 = this.thisAnimal.getDistanceSq(this.mateAnimal);
			return d0 >= 9.0D && d0 <= 256.0D;
		}
	}

	@Override
	public void startExecuting() {
		this.delayCounter = 0;
	}


	@Override
	public void resetTask() {
		this.mateAnimal = null;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = 60;
			this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.mateAnimal, this.moveSpeed);
		}
	}
}
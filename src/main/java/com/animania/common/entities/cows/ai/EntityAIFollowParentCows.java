package com.animania.common.entities.cows.ai;

import java.util.List;

import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.cows.EntityCalfBase;
import com.animania.common.entities.cows.EntityCowBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowParentCows extends EntityAIBase
{
	/** The child that is following its parent. */
	EntityAnimaniaCow childAnimal;
	EntityAnimaniaCow parentAnimal;
	double       moveSpeed;
	private int  delayCounter;

	public EntityAIFollowParentCows(EntityAnimaniaCow animal, double speed) {
		this.childAnimal = animal;
		this.moveSpeed = speed;
	}

	@Override
	public boolean shouldExecute() {
		
		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {
			
			if (childAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}
			
			if (this.childAnimal instanceof EntityCalfBase) {
				EntityCalfBase ec = (EntityCalfBase) this.childAnimal;
				if (ec.getParentUniqueId() == null) {
					return false;
				} else {

					List entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 40, this.childAnimal.world, this.childAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntityCowBase entity = (EntityCowBase)entities.get(k);

						if (entities.get(k) != null && entity.getPersistentID().equals(((EntityCalfBase) this.childAnimal).getParentUniqueId())) {
							double xt = entity.posX;
							double yt = entity.posY;
							double zt = entity.posZ;
							int x1 = MathHelper.floor(this.childAnimal.posX);
							int y1 = MathHelper.floor(this.childAnimal.posY);
							int z1 = MathHelper.floor(this.childAnimal.posZ);
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);

							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.parentAnimal = entity;
								return true;
							} else {
								return false;
							}
						}
					}
				}
			}
		}

		return false;

	}

	@Override
	public boolean shouldContinueExecuting() {
		if (!this.parentAnimal.isEntityAlive())
			return false;
		else {
			double d0 = this.childAnimal.getDistanceSq(this.parentAnimal);
			return d0 >= 9.0D && d0 <= 256.0D;
		}
	}

	@Override
	public void startExecuting() {
		this.delayCounter = 0;
	}

	@Override
	public void resetTask() {
		this.parentAnimal = null;
	}

	@Override
	public void updateTask() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = 40;
			this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
		}
	}
}
package com.animania.common.entities.pigs.ai;

import java.util.List;

import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.math.MathHelper;

public class EntityAIFollowMatePigs extends EntityAIBase
{

	EntityAnimaniaPig thisAnimal;
	EntityAnimaniaPig mateAnimal;
	double       moveSpeed;
	private int  delayCounter;

	public EntityAIFollowMatePigs(EntityAnimaniaPig animal, double speed) {
		this.thisAnimal = animal;
		this.moveSpeed = speed;
	}

	public boolean shouldExecute() {
		this.delayCounter++;
		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {

			if (!thisAnimal.world.isDaytime() || thisAnimal.getSleeping()) {
				this.delayCounter = 0;
				return false;
			}

			if (this.thisAnimal instanceof EntityHogBase) {
				EntityHogBase ec = (EntityHogBase) this.thisAnimal;
				if (ec.getMateUniqueId() == null)
					return false;
				else {

					List entities = AnimaniaHelper.getEntitiesInRange(EntitySowBase.class, 40, this.thisAnimal.world, this.thisAnimal);

					for (int k = 0; k <= entities.size() - 1; k++) {

						EntitySowBase entitysow = (EntitySowBase)entities.get(k);
						if (entities.get(k) != null && entitysow.getPersistentID().equals(((EntityHogBase) this.thisAnimal).getMateUniqueId())) {

							double xt = entitysow.posX;
							double yt = entitysow.posY;
							double zt = entitysow.posZ;
							int x1 = MathHelper.floor(this.thisAnimal.posX);
							int y1 = MathHelper.floor(this.thisAnimal.posY);
							int z1 = MathHelper.floor(this.thisAnimal.posZ);
							double x2 = Math.abs(xt - x1);
							double y2 = Math.abs(yt - y1);
							double z2 = Math.abs(zt - z1);

							if (x2 <= 20 && y2 <=8 && z2 <=20 && x2 >= 3 && z2 >= 3) {
								this.mateAnimal = (EntityAnimaniaPig) entitysow;
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

	@Override
	public void updateTask() {
		if (--this.delayCounter <= 0) {
			this.delayCounter = 60;
			this.thisAnimal.getNavigator().tryMoveToEntityLiving(this.mateAnimal, this.moveSpeed);
		}
	}
}
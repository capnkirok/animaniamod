package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import com.animania.common.entities.IFoodEating;
import com.animania.common.entities.IMateable;
import com.animania.common.entities.ISleeping;
import com.animania.common.entities.ISterilizable;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

public class GenericAIMate<T extends EntityCreature & IMateable & IFoodEating & ISleeping, O extends EntityCreature & IMateable & IFoodEating & ISleeping> extends EntityAIBase
{
	private final T entity;
	World theWorld;
	private O targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;
	private Random rand;

	private Class female;
	private Class child;
	private Class base;
	
	public GenericAIMate(T animal, double speedIn, Class other, Class child, Class base)
	{
		this.entity = animal;
		this.theWorld = animal.world;
		this.moveSpeed = speedIn;
		this.setMutexBits(3);
		this.courtshipTimer = 20;
		this.delayCounter = 0;
		this.rand = new Random();
		this.female = other;
		this.child = child;
		this.base = base;
	}
	
	@Override
	public boolean shouldExecute()
	{

		this.delayCounter++;

		// System.out.println(delayCounter);

		if (this.delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings)
		{
			if(entity instanceof ISterilizable)
			{
				if(((ISterilizable) entity).getSterilized())
				{
					this.delayCounter = 0;
					return false;
				}
			}
			
			if (entity instanceof ISleeping)
			{
				if (!entity.world.isDaytime() || ((ISleeping) entity).getSleeping())
				{
					this.delayCounter = 0;
					return false;
				}
			}

			if (child.isInstance(this.entity) || female.isInstance(this.entity) || this.entity.isInWater())
			{
				this.delayCounter = 0;
				return false;
			}

			List similarAnimalsInRange = AnimaniaHelper.getEntitiesInRange(base, 15, theWorld, entity);
			if(similarAnimalsInRange.size() >= AnimaniaConfig.careAndFeeding.entityBreedingLimit)
			{
				this.delayCounter = 0;
				return false;
			}
			
			if (AnimaniaConfig.careAndFeeding.manualBreeding)
			{
				if (entity instanceof IFoodEating && !((IFoodEating) entity).getHandFed())
				{
					this.delayCounter = 0;
					return false;
				}
			}
			

			this.targetMate = this.getNearbyMate();

			Random rand = new Random();
			if (this.targetMate != null && rand.nextInt(20) == 0)
			{
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
	public boolean shouldContinueExecuting()
	{
		if (targetMate != null)
		{
			return this.targetMate.isEntityAlive();
		}
		else
		{
			return false;
		}
	}

	@Override
	public void resetTask()
	{
		this.targetMate = null;
	}

	@Override
	public void updateTask()
	{

		if (this.targetMate != null)
		{
			if (!targetMate.getPregnant() && targetMate.getFertile())
			{
				this.targetMate = this.getNearbyMate();
			}
			else
			{
				((EntityAnimal) this.entity).resetInLove();
				this.resetTask();
				this.entity.getNavigator().clearPath();
				this.delayCounter = 0;
			}
		}
	}

	private O getNearbyMate()
	{

		T male = this.entity;
		UUID mateID = null;

		if (male.getMateUniqueId() != null)
		{
			mateID = male.getMateUniqueId();
		}

		if (mateID != null)
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 3, this.entity.world, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.manualBreeding && female instanceof IFoodEating && !((IFoodEating) female).getHandFed())
				{
					allowBreeding = false;
				}

				if (female.getPersistentID().equals(mateID) && female.getFertile() && (female instanceof ISleeping && !((ISleeping) female).getSleeping()) && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male))
				{

					this.courtshipTimer--;
					if (this.courtshipTimer < 0)
					{
						this.entity.setInLove(null);
						this.courtshipTimer = 20;
						k = entities.size();
						female.setPregnant(true);
						female.setFertile(false);
						female.setHandFed(false);
						delayCounter = 0;
						return female;
					}
					else if (allowBreeding)
					{
						k = entities.size();
						this.entity.setInLove(null);
						this.entity.getLookHelper().setLookPositionWithEntity(female, 10.0F, this.entity.getVerticalFaceSpeed());
						this.entity.getNavigator().tryMoveToEntityLiving(female, this.moveSpeed);
						female.getLookHelper().setLookPositionWithEntity(this.entity, 10.0F, female.getVerticalFaceSpeed());
						female.getNavigator().tryMoveToEntityLiving(this.entity, this.moveSpeed);

						return null;
					}
				}
			}
		}
		else
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 5, this.entity.world, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.manualBreeding && !female.getHandFed())
				{
					allowBreeding = false;
				}

				this.courtshipTimer--;
				if (female.getMateUniqueId() == null && this.courtshipTimer < 0 && female.getFertile() && !female.getSleeping() && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male))
				{

					this.entity.setMateUniqueId(female.getPersistentID());
					female.setMateUniqueId(this.entity.getPersistentID());
					this.entity.setInLove(null);
					this.courtshipTimer = 20;
					k = entities.size();
					female.setPregnant(true);
					female.setFertile(false);
					female.setHandFed(false);
					delayCounter = 0;
					return female;
				}
				else if (female.getMateUniqueId() == null && !female.getPregnant() && !female.getSleeping() && female.getFertile() && allowBreeding && female.canEntityBeSeen(male))
				{

					k = entities.size();
					this.entity.setInLove(null);
					this.entity.getLookHelper().setLookPositionWithEntity(female, 10.0F, this.entity.getVerticalFaceSpeed());
					this.entity.getNavigator().tryMoveToEntityLiving(female, this.moveSpeed);
					female.getLookHelper().setLookPositionWithEntity(this.entity, 10.0F, female.getVerticalFaceSpeed());
					female.getNavigator().tryMoveToEntityLiving(this.entity, this.moveSpeed);
					return null;

				}
			}
		}

		delayCounter = 0;
		return null;
	}
}

package com.animania.common.entities.generic.ai;

import java.util.List;
import java.util.UUID;

import com.animania.Animania;
import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISleeping;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

import CreatureEntity;

public class GenericAIMate<T extends CreatureEntity & IMateable & IFoodEating & ISleeping, O extends CreatureEntity & IMateable & IFoodEating & ISleeping & IImpregnable> extends EntityAIBase
{
	private final T entity;
	World theWorld;
	private O targetMate;
	int courtshipTimer;
	double moveSpeed;
	private int delayCounter;

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
			if (entity instanceof ISterilizable)
			{
				if (((ISterilizable) entity).getSterilized())
				{
					this.delayCounter = 0;
					return false;
				}
			}

			if (entity.getSleeping())
			{
				this.delayCounter = 0;
				return false;
			}

			if (child.isInstance(this.entity) || female.isInstance(this.entity) || this.entity.isInWater())
			{
				this.delayCounter = 0;
				return false;
			}

			List similarAnimalsInRange = AnimaniaHelper.getEntitiesInRange(base, AnimaniaConfig.gameRules.animalCapSearchRange, theWorld, entity);
			if (similarAnimalsInRange.size() + 1 >= AnimaniaConfig.careAndFeeding.entityBreedingLimit)
			{
				//+ 1 for the child that will be born
				this.delayCounter = 0;
				return false;
			}

			if (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? !entity.getInteracted() : false)
			{
				this.delayCounter = 0;
				return false;
			}

			if (AnimaniaConfig.careAndFeeding.feedToBreed)
			{
				if (!entity.getHandFed())
				{
					this.delayCounter = 0;
					return false;
				}
			}
			
			if(!entity.getFed() || !entity.getWatered())
			{
				this.delayCounter = 0;
				return false;
			}

			this.targetMate = this.getNearbyMate();

			if (this.targetMate != null && Animania.RANDOM.nextInt(20) == 0)
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
			UUID targetMateUUID = targetMate.getMateUniqueId();
			boolean preg = targetMate.getPregnant();
			boolean fertile = targetMate.getFertile();
			boolean uuidbool = (targetMateUUID == null ? false : !targetMateUUID.equals(entity.getUniqueID()));

			if (uuidbool || preg || !fertile)
			{
				targetMate.getNavigator().clearPath();
				this.courtshipTimer = 200;
				this.resetTask();
				this.entity.getNavigator().clearPath();
				return;
			}

			this.courtshipTimer--;

			if (courtshipTimer >= 0)
			{

				if (courtshipTimer % 20 == 0)
				{
					this.entity.getLookHelper().setLookPositionWithEntity(targetMate, 10.0F, this.entity.getVerticalFaceSpeed());
					this.entity.getNavigator().tryMoveToLivingEntity(targetMate, this.moveSpeed);
					targetMate.getLookHelper().setLookPositionWithEntity(this.entity, 10.0F, targetMate.getVerticalFaceSpeed());
					targetMate.getNavigator().tryMoveToLivingEntity(this.entity, this.moveSpeed);
				}

				double distance = entity.getDistance(targetMate);

				if (distance <= 1.8)
				{
					this.entity.setInLove(null);
					this.entity.setMateUniqueId(targetMate.getPersistentID());
					targetMate.setInLove(null);

					targetMate.setPregnant(true);
					targetMate.setFertile(false);
					targetMate.setHandFed(false);
					targetMate.setInteracted(this.entity.getInteracted());
					targetMate.setMateUniqueId(entity.getPersistentID());

					targetMate.getNavigator().clearPath();
					this.courtshipTimer = 200;
					this.resetTask();
					this.entity.getNavigator().clearPath();

				}
			}
			else
			{
				targetMate.getNavigator().clearPath();
				this.courtshipTimer = 200;
				this.resetTask();
				this.entity.getNavigator().clearPath();

				// If mating fails, we give a 2000 tick cooldown
				this.delayCounter = -2000;
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

		if (AnimaniaConfig.careAndFeeding.malesMateMultipleFemales)
			mateID = null;

		if (mateID != null)
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 5, this.entity.world, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				if (female == null)
					continue;

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.feedToBreed && female instanceof IFoodEating && !((IFoodEating) female).getHandFed())
				{
					allowBreeding = false;
				}

				if (female.getPersistentID().equals(mateID) && female.getFertile() && (female instanceof ISleeping && !((ISleeping) female).getSleeping()) && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male) && female.getWatered() && female.getFed())
				{
					this.courtshipTimer = 200;
					return female;
				}
			}
		}
		else
		{
			List entities = AnimaniaHelper.getEntitiesInRange(this.female, 8, this.entity.world, this.entity);

			for (int k = 0; k <= entities.size() - 1; k++)
			{
				O female = (O) entities.get(k);

				boolean allowBreeding = true;
				if (AnimaniaConfig.careAndFeeding.feedToBreed && !female.getHandFed())
				{
					allowBreeding = false;
				}

				this.courtshipTimer--;
				if ((AnimaniaConfig.careAndFeeding.malesMateMultipleFemales ? (female.getMateUniqueId() == null ? true : female.getMateUniqueId().equals(entity.getPersistentID())) : female.getMateUniqueId() == null) && female.getFertile() && !female.getSleeping() && !female.getPregnant() && allowBreeding && female.canEntityBeSeen(male) && female.getWatered() && female.getFed())
				{
					this.courtshipTimer = 200;
					return female;
				}
			}
		}

		delayCounter = 0;
		return null;
	}
}

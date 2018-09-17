package com.animania.common.entities.generic.ai;

import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.AxisAlignedBB;

public class GenericAIHurtByTarget extends EntityAITarget
{
	private final boolean entityCallsForHelp;
	/** Store the previous revengeTimer value */
	private int revengeTimerOld;
	private final Class<?>[] excludedReinforcementTypes;

	public GenericAIHurtByTarget(EntityCreature creatureIn, boolean entityCallsForHelpIn, Class<?>... excludedReinforcementTypes)
	{
		super(creatureIn, true);
		this.entityCallsForHelp = entityCallsForHelpIn;
		this.excludedReinforcementTypes = excludedReinforcementTypes;
		this.setMutexBits(1);
	}

	public boolean shouldExecute()
	{

		int i = this.taskOwner.getRevengeTimer();

		EntityLivingBase entitylivingbase = this.taskOwner.getRevengeTarget();
		return i != this.revengeTimerOld && entitylivingbase != null && this.isSuitableTarget(entitylivingbase, false);
	}

	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.taskOwner.getRevengeTarget());
		this.target = this.taskOwner.getAttackTarget();
		this.revengeTimerOld = this.taskOwner.getRevengeTimer();
		this.unseenMemoryTicks = 300;

		super.startExecuting();
	}

	protected void alertOthers()
	{
		double d0 = this.getTargetDistance();

		for (EntityCreature entitycreature : this.taskOwner.world.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).grow(d0, 10.0D, d0)))
		{
			if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && (!(this.taskOwner instanceof EntityTameable) || ((EntityTameable)this.taskOwner).getOwner() == ((EntityTameable)entitycreature).getOwner()) && !entitycreature.isOnSameTeam(this.taskOwner.getRevengeTarget()))
			{
				boolean flag = false;

				for (Class<?> oclass : this.excludedReinforcementTypes)
				{
					if (entitycreature.getClass() == oclass)
					{
						flag = true;
						break;
					}
				}

				if (!flag)
				{
					this.setEntityAttackTarget(entitycreature, this.taskOwner.getRevengeTarget());
				}
			}
		}
	}

	protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
	{
		creatureIn.setAttackTarget(entityLivingBaseIn);
	}
}
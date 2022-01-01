package com.animania.addons.extra.common.entity.rodents.ai;

import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHamster;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;

public class LookIdleRodentGoal extends Goal
{
	private final Animal idleEntity;
	private double lookX;
	private double lookZ;
	private int idleTime;

	public LookIdleRodentGoal(Animal LivingEntityIn)
	{
		this.idleEntity = LivingEntityIn;
		this.setMutexBits(3);
	}

	@Override
	public boolean canUse()
	{

		if (this.idleEntity instanceof EntityHamster er && er.getSleeping() || this.idleEntity instanceof EntityFerretBase erb && erb.getSleeping())
		{
			return false;
		}

		if (this.idleEntity instanceof EntityHedgehogBase er && er.getSleeping())
		{
			return false;
		}

		return this.idleEntity.getRandom().nextFloat() < 0.02F && !this.idleEntity.isPassenger();
	}

	@Override
	public boolean canContinueToUse()
	{
		return this.idleTime >= 0;
	}

	@Override
	public void start()
	{
		double d0 = Math.PI * 2D * this.idleEntity.getRandom().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.idleEntity.getRandom().nextInt(20);
	}

	@Override
	public void tick()
	{
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.getX() + this.lookX, this.idleEntity.getY() + this.idleEntity.getEyeHeight(), this.idleEntity.getZ() + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
	}
}
package com.animania.addons.catsdogs.common.entity.felids.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.OcelotAttackGoal;

public class CatAttackGoal<T extends LivingEntity & ISleeping> extends OcelotAttackGoal
{
	private T entity;

	public CatAttackGoal(T entity)
	{
		super(entity);
		this.entity = entity;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.entity.getSleeping())
			return false;

		return super.shouldExecute();
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		if (this.entity.getSleeping())
			return false;

		return super.shouldContinueExecuting();
	}

}

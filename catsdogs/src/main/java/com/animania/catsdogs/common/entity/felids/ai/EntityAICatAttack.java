package com.animania.catsdogs.common.entity.felids.ai;

import com.animania.api.interfaces.ISleeping;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;

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

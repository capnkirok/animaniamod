package com.animania.addons.catsdogs.common.entity.felids.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAIOcelotAttack;

import LivingEntity;

public class EntityAICatAttack<T extends LivingEntity & ISleeping> extends EntityAIOcelotAttack
{
	private T entity;

	public EntityAICatAttack(T entity)
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
